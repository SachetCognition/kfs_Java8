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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Bridges existing KFS Spring XML configurations into the Spring Boot context.
 *
 * This configuration imports all legacy XML bean definitions so that the
 * transition to Spring Boot can proceed incrementally. Each XML file can
 * be converted to a Java @Configuration class in a subsequent migration phase.
 */
@Configuration
@ImportResource({
    "classpath:spring-kfs-imported-rice-beans.xml",
    "classpath:spring-additional-rice-beans.xml",
    "classpath:kfs-cache-config.xml",
    "classpath:spring-kfs-batch.xml",
    "classpath:org/kuali/kfs/sys/spring-sys.xml",
    "classpath:org/kuali/kfs/coa/spring-coa.xml",
    "classpath:org/kuali/kfs/fp/spring-fp.xml",
    "classpath:org/kuali/kfs/gl/spring-gl.xml",
    "classpath:org/kuali/kfs/pdp/spring-pdp.xml",
    "classpath:org/kuali/kfs/vnd/spring-vnd.xml",
    "classpath:org/kuali/kfs/integration/spring-integration.xml"
})
public class KfsCoreSpringConfiguration {
}
