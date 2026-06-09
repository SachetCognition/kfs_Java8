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
 * Bridges KFS functional module Spring XML configurations into the Spring Boot context.
 *
 * Each module's Spring XML defines its service beans, data dictionary entries,
 * and OJB repository mappings. These are imported wholesale so that module
 * functionality is preserved during the Boot migration.
 */
@Configuration
@ImportResource({
    "classpath:org/kuali/kfs/module/cg/spring-cg.xml",
    "classpath:org/kuali/kfs/module/ar/spring-ar.xml",
    "classpath:org/kuali/kfs/module/purap/spring-purap.xml",
    "classpath:org/kuali/kfs/module/cam/spring-cam.xml",
    "classpath:org/kuali/kfs/module/cab/spring-cab.xml",
    "classpath:org/kuali/kfs/module/ld/spring-ld.xml",
    "classpath:org/kuali/kfs/module/bc/spring-bc.xml",
    "classpath:org/kuali/kfs/module/ec/spring-ec.xml",
    "classpath:org/kuali/kfs/module/tem/spring-tem.xml"
})
public class KfsModuleSpringConfiguration {
}
