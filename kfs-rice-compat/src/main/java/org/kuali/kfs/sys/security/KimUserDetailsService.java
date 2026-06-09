package org.kuali.kfs.sys.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KimUserDetailsService implements UserDetailsService {

    private DataSource dataSource;

    private static final String FIND_PRINCIPAL_SQL =
        "SELECT PRNCPL_ID, PRNCPL_NM, PRNCPL_PSWD, ACTV_IND " +
        "FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = ?";

    private static final String FIND_ROLES_SQL =
        "SELECT r.ROLE_NM FROM KRIM_ROLE_MBR_T rm " +
        "JOIN KRIM_ROLE_T r ON rm.ROLE_ID = r.ROLE_ID " +
        "WHERE rm.MBR_ID = ? AND rm.MBR_TYP_CD = 'P' " +
        "AND (rm.ACTV_FRM_DT IS NULL OR rm.ACTV_FRM_DT <= CURRENT_TIMESTAMP) " +
        "AND (rm.ACTV_TO_DT IS NULL OR rm.ACTV_TO_DT > CURRENT_TIMESTAMP)";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (dataSource == null) {
            return createDevUser(username);
        }

        try (Connection conn = dataSource.getConnection()) {
            String principalId = null;
            String password = "";
            boolean active = true;

            try (PreparedStatement ps = conn.prepareStatement(FIND_PRINCIPAL_SQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        principalId = rs.getString("PRNCPL_ID");
                        password = rs.getString("PRNCPL_PSWD");
                        if (password == null) password = "";
                        active = "Y".equals(rs.getString("ACTV_IND"));
                    } else {
                        throw new UsernameNotFoundException("User not found: " + username);
                    }
                }
            }

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            try (PreparedStatement ps = conn.prepareStatement(FIND_ROLES_SQL)) {
                ps.setString(1, principalId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + rs.getString("ROLE_NM")));
                    }
                }
            }

            return new User(username, password, active, true, true, true, authorities);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("Error loading user: " + username, e);
        }
    }

    private UserDetails createDevUser(String username) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_KFS-SYS_MANAGER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_KFS-SYS_USER"));
        return new User(username, "", true, true, true, true, authorities);
    }
}
