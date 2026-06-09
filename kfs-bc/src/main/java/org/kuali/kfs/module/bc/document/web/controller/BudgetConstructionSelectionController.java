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
import org.kuali.kfs.module.bc.document.web.struts.BudgetConstructionSelectionForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing BudgetConstructionSelectionAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/budgetConstructionSelection")
public class BudgetConstructionSelectionController {


    @RequestMapping
    public String execute(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadExpansionScreen")
    public String loadExpansionScreen(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performBCDocumentOpen")
    public String performBCDocumentOpen(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=returnToCaller")
    public String returnToCaller(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performOrgSalarySetting")
    public String performOrgSalarySetting(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performReportDump")
    public String performReportDump(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performRequestImport")
    public String performRequestImport(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performPayrateImportExport")
    public String performPayrateImportExport(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performLockMonitor")
    public String performLockMonitor(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performOrgPullup")
    public String performOrgPullup(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performOrgPushdown")
    public String performOrgPushdown(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMyAccounts")
    public String performMyAccounts(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMyOrganization")
    public String performMyOrganization(@ModelAttribute("BudgetConstructionSelectionForm") BudgetConstructionSelectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
