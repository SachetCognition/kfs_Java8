package org.kuali.kfs.sys.context;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JPA integration tests verifying that the persistence unit bootstraps
 * correctly and that entity mappings are valid against a live MySQL schema.
 */
class JpaEntityManagerIT extends KfsIntegrationTestBase {

    private static EntityManagerFactory emf;

    @BeforeAll
    static void createEntityManagerFactory() {
        Map<String, String> props = new HashMap<>();
        props.put("jakarta.persistence.jdbc.url", mysql.getJdbcUrl());
        props.put("jakarta.persistence.jdbc.user", mysql.getUsername());
        props.put("jakarta.persistence.jdbc.password", mysql.getPassword());
        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props.put("hibernate.hbm2ddl.auto", "create-drop");
        props.put("hibernate.show_sql", "false");

        emf = Persistence.createEntityManagerFactory("kfs", props);
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    void entityManagerFactoryIsCreated() {
        assertNotNull(emf, "EntityManagerFactory should be created from persistence unit 'kfs'");
    }

    @Test
    void entityManagerCanBeObtained() {
        EntityManager em = emf.createEntityManager();
        assertNotNull(em, "EntityManager should be obtainable");
        em.close();
    }

    @Test
    void nativeQueryAgainstChartEntity() {
        EntityManager em = emf.createEntityManager();
        assertDoesNotThrow(() -> {
            em.createNativeQuery("SELECT 1 FROM CA_CHART_T").getResultList();
        }, "Native query against Chart table (CA_CHART_T) should succeed");
        em.close();
    }
}
