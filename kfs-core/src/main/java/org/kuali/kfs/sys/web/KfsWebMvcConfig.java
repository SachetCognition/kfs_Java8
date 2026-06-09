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
package org.kuali.kfs.sys.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration for KFS (Phase 4 migration: Struts to Spring MVC).
 * Enables annotation-driven controllers across all KFS modules.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "org.kuali.kfs.sys.web.controller",
    "org.kuali.kfs.fp.document.web.controller",
    "org.kuali.kfs.gl.web.controller",
    "org.kuali.kfs.gl.document.web.controller",
    "org.kuali.kfs.vnd.web.controller",
    "org.kuali.kfs.pdp.web.controller",
    "org.kuali.kfs.module.ar.web.controller",
    "org.kuali.kfs.module.ar.document.web.controller",
    "org.kuali.kfs.module.bc.document.web.controller",
    "org.kuali.kfs.module.purap.document.web.controller",
    "org.kuali.kfs.module.cam.web.controller",
    "org.kuali.kfs.module.cg.document.web.controller",
    "org.kuali.kfs.module.ec.document.web.controller",
    "org.kuali.kfs.module.ld.web.controller",
    "org.kuali.kfs.module.ld.document.web.controller",
    "org.kuali.kfs.module.tem.web.controller",
    "org.kuali.kfs.module.tem.document.web.controller"
})
public class KfsWebMvcConfig implements WebMvcConfigurer {
}
