package org.springframework.jdbc.core.simple;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Compatibility shim for the removed SimpleJdbcDaoSupport class.
 * Extends JdbcDaoSupport and exposes a SimpleJdbcTemplate wrapper.
 * This bridges legacy Kuali Rice code (PlatformAwareDaoBaseJdbc) that
 * was compiled against Spring 3.x's SimpleJdbcDaoSupport.
 */
public abstract class SimpleJdbcDaoSupport extends JdbcDaoSupport {

    private SimpleJdbcTemplate simpleJdbcTemplate;

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        if (this.simpleJdbcTemplate == null && getJdbcTemplate() != null) {
            this.simpleJdbcTemplate = new SimpleJdbcTemplate(getJdbcTemplate());
        }
        return this.simpleJdbcTemplate;
    }
}
