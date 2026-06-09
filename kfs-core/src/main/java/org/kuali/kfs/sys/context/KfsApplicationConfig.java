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

import org.kuali.kfs.coa.context.CoaConfig;
import org.kuali.kfs.fp.context.FpConfig;
import org.kuali.kfs.gl.context.GlConfig;
import org.kuali.kfs.pdp.context.PdpConfig;
import org.kuali.kfs.vnd.context.VndConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Root Spring Java configuration for the KFS application.
 *
 * <p>Aggregates all module-level {@link Configuration} classes and imports
 * the remaining Rice infrastructure XML files. External modules (AR, CAM,
 * CG, etc.) define their own @Configuration classes in their respective
 * Maven modules and are discovered via classpath scanning when those
 * modules are present.</p>
 *
 * <p>This class serves as the entry point for Java-based configuration,
 * gradually replacing the XML-first approach as the migration to
 * Spring Boot 3.3.5 / Spring Framework 6.1 progresses.</p>
 */
@Configuration
@Import({
    JpaConfig.class,
    SysConfig.class,
    CoaConfig.class,
    GlConfig.class,
    FpConfig.class,
    PdpConfig.class,
    VndConfig.class
})
@ImportResource({
    "classpath:kfs-RiceSpringBeans.xml",
    "classpath:kfs-RiceDataSourceSpringBeans.xml",
    "classpath:kfs-RiceJTASpringBeans.xml",
    "classpath:spring-kfs-batch.xml",
    "classpath:spring-kfs-imported-rice-beans.xml",
    "classpath:spring-additional-rice-beans.xml",
    "classpath:org/kuali/kfs/sys/spring-dev-env-beans.xml",
    "classpath:org/kuali/kfs/integration/spring-integration.xml",
    "classpath:org/kuali/kfs/sec/spring-sec.xml"
})
public class KfsApplicationConfig {
}
