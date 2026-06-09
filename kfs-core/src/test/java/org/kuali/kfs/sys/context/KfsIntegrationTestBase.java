package org.kuali.kfs.sys.context;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class KfsIntegrationTestBase {

    @Container
    protected static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
        .withDatabaseName("kfs_test")
        .withUsername("kfs")
        .withPassword("kfs");

    @BeforeAll
    static void setupDatabase() {
        System.setProperty("kfs.datasource.url", mysql.getJdbcUrl());
        System.setProperty("kfs.datasource.username", mysql.getUsername());
        System.setProperty("kfs.datasource.password", mysql.getPassword());
        System.setProperty("rice.datasource.url", mysql.getJdbcUrl());
        System.setProperty("rice.datasource.username", mysql.getUsername());
        System.setProperty("rice.datasource.password", mysql.getPassword());
    }
}
