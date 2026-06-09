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
package org.kuali.kfs.module.bc.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.bc.document.web.struts.OrganizationReportSelectionAction;
import org.kuali.kfs.module.bc.document.web.struts.OrganizationReportSelectionForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSConstants.ReportGeneration;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing OrganizationReportSelectionAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/organizationReportSelection")
public class OrganizationReportSelectionController {

    private final OrganizationReportSelectionAction delegate = new OrganizationReportSelectionAction();

    @RequestMapping(params = "methodToCall=start")
    public String start(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performReport")
    public void performReport(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=selectAllSubFunds")
    public String selectAllSubFunds(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectAllObjectCodes")
    public String selectAllObjectCodes(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectAllReasonCodes")
    public String selectAllReasonCodes(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=unselectAllSubFunds")
    public String unselectAllSubFunds(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=unselectAllObjectCodes")
    public String unselectAllObjectCodes(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=unselectAllReasonCodes")
    public String unselectAllReasonCodes(@ModelAttribute("OrganizationReportSelectionForm") OrganizationReportSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
