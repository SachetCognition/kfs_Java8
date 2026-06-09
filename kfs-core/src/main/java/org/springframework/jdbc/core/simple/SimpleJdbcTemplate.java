package org.springframework.jdbc.core.simple;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Compatibility shim for the removed SimpleJdbcTemplate class.
 * Delegates all operations to JdbcTemplate. This bridges legacy
 * Kuali Rice code that was compiled against Spring 3.x.
 */
public class SimpleJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public SimpleJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcOperations() {
        return jdbcTemplate;
    }

    public int queryForInt(String sql, Object... args) {
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, args);
        return result != null ? result : 0;
    }

    public long queryForLong(String sql, Object... args) {
        Long result = jdbcTemplate.queryForObject(sql, Long.class, args);
        return result != null ? result : 0L;
    }

    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    public Map<String, Object> queryForMap(String sql, Object... args) {
        return jdbcTemplate.queryForMap(sql, args);
    }

    public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
        return jdbcTemplate.queryForObject(sql, requiredType, args);
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.query(sql, rowMapper, args);
    }

    public int update(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }
}
