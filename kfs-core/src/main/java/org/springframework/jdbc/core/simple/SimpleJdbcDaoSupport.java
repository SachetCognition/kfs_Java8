package org.springframework.jdbc.core.simple;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Compatibility shim for Spring 6.x migration (S-4A).
 * SimpleJdbcDaoSupport was removed in Spring 4.x; this stub restores the class
 * signature so that Rice 2.1.10 stubs (compiled against Spring 3.x) continue
 * to resolve at compile time.
 *
 * @deprecated Provided only for backward compatibility with Rice 2.1.10.
 *             New code should use JdbcDaoSupport directly.
 */
@Deprecated
public abstract class SimpleJdbcDaoSupport extends JdbcDaoSupport {

    /**
     * Returns the JdbcTemplate (replaces the removed SimpleJdbcTemplate).
     */
    public JdbcTemplate getSimpleJdbcTemplate() {
        return getJdbcTemplate();
    }
}
