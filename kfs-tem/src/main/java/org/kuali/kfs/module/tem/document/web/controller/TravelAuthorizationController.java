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
package org.kuali.kfs.module.tem.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.tem.document.web.struts.TravelAuthorizationForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing TravelAuthorizationAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/travelAuthorization")
public class TravelAuthorizationController {


    @RequestMapping
    public String execute(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertAdvanceAccountingLine")
    public String insertAdvanceAccountingLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteAdvanceAccountingLine")
    public String deleteAdvanceAccountingLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performBalanceInquiryForAdvanceAccountingLine")
    public String performBalanceInquiryForAdvanceAccountingLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performLookup")
    public String performLookup(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addEmergencyContactLine")
    public String addEmergencyContactLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteEmergencyContactLine")
    public String deleteEmergencyContactLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=updatePerDiemExpenses")
    public String updatePerDiemExpenses(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculate")
    public String recalculate(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=newReimbursement")
    public String newReimbursement(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertSourceLine")
    public String insertSourceLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteSourceLine")
    public String deleteSourceLine(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=disapprove")
    public String disapprove(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=close")
    public String close(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=blanketApprove")
    public String blanketApprove(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearAdvance")
    public String clearAdvance(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copy")
    public String copy(@ModelAttribute("TravelAuthorizationForm") TravelAuthorizationForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
