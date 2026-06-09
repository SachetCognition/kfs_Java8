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
package org.kuali.kfs.web.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Replaces Struts action mappings for Effort Certification module.
 * Original wildcard: /effort* → org.kuali.kfs.module.ec.document.web.struts.{1}Action
 */
@Controller
public class EffortCertificationController extends KfsBaseController {

    @RequestMapping("/effortEffortCertification.do")
    public ModelAndView effortCertification(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ec/EffortCertification.jsp");
    }

    @RequestMapping("/effortCertificationRecreate.do")
    public ModelAndView certificationRecreate(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ec/CertificationRecreate.jsp");
    }

    @RequestMapping("/effortCertificationReport.do")
    public ModelAndView certificationReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ec/CertificationReport.jsp");
    }
}
