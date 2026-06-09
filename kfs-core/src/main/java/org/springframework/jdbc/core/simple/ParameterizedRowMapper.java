/*
 * Compatibility shim – ParameterizedRowMapper was removed in Spring 4.0.
 * It was just a type-safe extension of RowMapper.
 */
package org.springframework.jdbc.core.simple;

import org.springframework.jdbc.core.RowMapper;

public interface ParameterizedRowMapper<T> extends RowMapper<T> {
}
