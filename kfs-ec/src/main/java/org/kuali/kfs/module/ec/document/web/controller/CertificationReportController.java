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
package org.kuali.kfs.module.ec.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.ec.document.web.struts.CertificationReportAction;
import org.kuali.kfs.module.ec.document.web.struts.EffortCertificationForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing CertificationReportAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/certificationReport")
public class CertificationReportController {

    private final CertificationReportAction delegate = new CertificationReportAction();

    @RequestMapping(params = "methodToCall=recalculate")
    public String recalculate(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=add")
    public String add(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=delete")
    public String delete(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=revert")
    public String revert(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping
    public String execute(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=sortDetailLineByColumn")
    public String sortDetailLineByColumn(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculateSummarizedDetailLine")
    public String recalculateSummarizedDetailLine(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addSummarizedDetailLine")
    public String addSummarizedDetailLine(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteSummarizedDetailLine")
    public String deleteSummarizedDetailLine(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=revertSummarizedDetailLine")
    public String revertSummarizedDetailLine(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertBONote")
    public String insertBONote(@ModelAttribute("EffortCertificationForm") EffortCertificationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
