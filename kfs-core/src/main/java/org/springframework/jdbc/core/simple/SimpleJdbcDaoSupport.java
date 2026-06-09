/*
 * Compatibility shim – SimpleJdbcDaoSupport was removed in Spring 4.0.
 * This stub extends JdbcDaoSupport (which still exists in Spring 6.x)
 * and provides getSimpleJdbcTemplate() so that legacy Rice/KFS DAO classes
 * continue to compile and run against Spring 6.x.
 */
package org.springframework.jdbc.core.simple;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class SimpleJdbcDaoSupport extends JdbcDaoSupport {

    private SimpleJdbcTemplate simpleJdbcTemplate;

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        if (this.simpleJdbcTemplate == null && getJdbcTemplate() != null) {
            this.simpleJdbcTemplate = new SimpleJdbcTemplate(getJdbcTemplate());
        }
        return this.simpleJdbcTemplate;
    }
}
