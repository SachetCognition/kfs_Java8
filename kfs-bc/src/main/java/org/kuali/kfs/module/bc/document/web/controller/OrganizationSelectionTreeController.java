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
import org.kuali.kfs.module.bc.document.web.struts.OrganizationSelectionTreeAction;
import org.kuali.kfs.module.bc.document.web.struts.OrganizationSelectionTreeForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing OrganizationSelectionTreeAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/organizationSelectionTree")
public class OrganizationSelectionTreeController {

    private final OrganizationSelectionTreeAction delegate = new OrganizationSelectionTreeAction();

    @RequestMapping
    public String execute(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadExpansionScreen")
    public String loadExpansionScreen(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=returnToCaller")
    public String returnToCaller(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performBuildPointOfView")
    public String performBuildPointOfView(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=navigateDown")
    public String navigateDown(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=navigateUp")
    public String navigateUp(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectAll")
    public String selectAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearAll")
    public String clearAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPullOrgAll")
    public String selectPullOrgAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPullSubOrgAll")
    public String selectPullSubOrgAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPullBothAll")
    public String selectPullBothAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPushOrgLevAll")
    public String selectPushOrgLevAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPushMgrLevAll")
    public String selectPushMgrLevAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPushOrgMgrLevAll")
    public String selectPushOrgMgrLevAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPushLevOneAll")
    public String selectPushLevOneAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectPushLevZeroAll")
    public String selectPushLevZeroAll(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performPositionPick")
    public String performPositionPick(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performIncumbentPick")
    public String performIncumbentPick(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performShowBudgetDocs")
    public String performShowBudgetDocs(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performPullUp")
    public String performPullUp(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performShowPullUpBudgetDocs")
    public String performShowPullUpBudgetDocs(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performPushDown")
    public String performPushDown(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performShowPushDownBudgetDocs")
    public String performShowPushDownBudgetDocs(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performReport")
    public String performReport(@ModelAttribute("OrganizationSelectionTreeForm") OrganizationSelectionTreeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
