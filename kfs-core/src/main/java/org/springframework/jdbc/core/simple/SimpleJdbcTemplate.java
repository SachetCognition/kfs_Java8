/*
 * Compatibility shim – SimpleJdbcTemplate was removed in Spring 4.0.
 * This stub delegates to JdbcTemplate so that legacy Rice/KFS DAO classes
 * continue to compile against Spring 6.x.
 */
package org.springframework.jdbc.core.simple;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SimpleJdbcTemplate {

    private final JdbcTemplate classicJdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SimpleJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.classicJdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public SimpleJdbcTemplate(DataSource dataSource) {
        this(new JdbcTemplate(dataSource));
    }

    public JdbcOperations getJdbcOperations() {
        return classicJdbcTemplate;
    }

    public NamedParameterJdbcOperations getNamedParameterJdbcOperations() {
        return namedParameterJdbcTemplate;
    }

    public int update(String sql, Object... args) {
        return classicJdbcTemplate.update(sql, args);
    }

    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        if (args == null || args.length == 0) {
            return classicJdbcTemplate.queryForList(sql);
        }
        return classicJdbcTemplate.queryForList(sql, args);
    }

    public Map<String, Object> queryForMap(String sql, Object... args) {
        return classicJdbcTemplate.queryForMap(sql, args);
    }

    public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
        return classicJdbcTemplate.queryForObject(sql, requiredType, args);
    }

    public <T> T queryForObject(String sql, RowMapper<T> rm, Object... args) {
        return classicJdbcTemplate.queryForObject(sql, rm, args);
    }

    public <T> List<T> query(String sql, RowMapper<T> rm, Object... args) {
        return classicJdbcTemplate.query(sql, rm, args);
    }

    public int queryForInt(String sql, Object... args) {
        Integer result = classicJdbcTemplate.queryForObject(sql, Integer.class, args);
        return result != null ? result : 0;
    }

    public long queryForLong(String sql, Object... args) {
        Long result = classicJdbcTemplate.queryForObject(sql, Long.class, args);
        return result != null ? result : 0L;
    }

    public int[] batchUpdate(String sql, List<Object[]> batchArgs) {
        return classicJdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
        return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public int update(String sql, SqlParameterSource paramSource) {
        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public <T> T queryForObject(String sql, SqlParameterSource paramSource, Class<T> requiredType) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, requiredType);
    }

    public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rm) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, rm);
    }

    public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rm) {
        return namedParameterJdbcTemplate.query(sql, paramSource, rm);
    }
}
