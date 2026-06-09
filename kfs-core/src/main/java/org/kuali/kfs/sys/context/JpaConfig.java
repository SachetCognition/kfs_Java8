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

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JPA infrastructure configuration for the OJB &rarr; JPA migration.
 *
 * <p>Bootstraps a Jakarta Persistence 3.1 {@link EntityManagerFactory} backed by
 * Hibernate 6.4. This configuration coexists with the legacy OJB
 * {@code PersistenceBroker} &mdash; both persistence layers remain active during
 * the incremental per-module migration.</p>
 *
 * <p>JDBC connection properties are read from the Rice {@link ConfigContext} at
 * runtime (the same source used by the existing OJB/Spring datasource beans in
 * {@code kfs-RiceDataSourceSpringBeans.xml}), so they resolve correctly
 * regardless of Maven resource filtering.</p>
 *
 * <h3>Spring integration roadmap</h3>
 * <p>The current KFS classpath contains Spring Framework 3.1.x (via Rice 2.1.x),
 * whose {@code LocalContainerEntityManagerFactoryBean} and
 * {@code JpaTransactionManager} use the {@code javax.persistence} namespace.
 * Full Spring-managed JPA integration ({@code @EnableJpaRepositories},
 * {@code LocalContainerEntityManagerFactoryBean}, {@code JpaTransactionManager})
 * will be activated in <strong>S-4A</strong> when the Spring Boot 3.3.x BOM
 * replaces Rice's Spring 3.1.x with Spring Framework 6.x.</p>
 */
@Configuration
public class JpaConfig {

    /**
     * Creates the JPA {@link EntityManagerFactory} for the {@code "kfs"}
     * persistence unit defined in {@code META-INF/persistence.xml}.
     *
     * <p>Connection properties are resolved from the Rice
     * {@link ConfigContext} so that JPA uses the same datasource
     * configuration as the rest of KFS.</p>
     */
    @Bean(destroyMethod = "close")
    public EntityManagerFactory entityManagerFactory() {
        Config cfg = ConfigContext.getCurrentContextConfig();

        Map<String, String> overrides = new HashMap<>();
        overrides.put("jakarta.persistence.jdbc.url", cfg.getProperty("kfs.datasource.url"));
        overrides.put("jakarta.persistence.jdbc.user", cfg.getProperty("kfs.datasource.username"));
        overrides.put("jakarta.persistence.jdbc.password", cfg.getProperty("kfs.datasource.password"));
        overrides.put("jakarta.persistence.jdbc.driver", cfg.getProperty("kfs.datasource.driver.name"));

        return Persistence.createEntityManagerFactory("kfs", overrides);
    }
}
