/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.context;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Base class for integration tests that need a real database.
 * Uses Testcontainers to spin up MySQL.
 */
@Testcontainers
public abstract class KfsIntegrationTestBase {
    @Container
    protected static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7")
        .withDatabaseName("kfs_test")
        .withUsername("kfs")
        .withPassword("kfs");

    @BeforeAll
    static void setupDatabase() {
        System.setProperty("datasource.url", mysql.getJdbcUrl());
        System.setProperty("datasource.username", mysql.getUsername());
        System.setProperty("datasource.password", mysql.getPassword());
    }
}
