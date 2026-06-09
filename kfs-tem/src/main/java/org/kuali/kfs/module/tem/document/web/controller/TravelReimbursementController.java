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
import org.kuali.kfs.module.tem.document.web.struts.TravelReimbursementAction;
import org.kuali.kfs.module.tem.document.web.struts.TravelReimbursementForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing TravelReimbursementAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/travelReimbursement")
public class TravelReimbursementController {

    private final TravelReimbursementAction delegate = new TravelReimbursementAction();

    @RequestMapping(params = "methodToCall=printCoversheet")
    public void printCoversheet(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=viewExpenseSummary")
    public void viewExpenseSummary(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=viewSummaryByDay")
    public void viewSummaryByDay(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=viewNonEmployeeForms")
    public void viewNonEmployeeForms(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addOtherExpenseDetailLine")
    public String addOtherExpenseDetailLine(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteOtherExpenseDetailLine")
    public String deleteOtherExpenseDetailLine(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping
    public String execute(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearPerDiem")
    public String clearPerDiem(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearPerDiemExpenses")
    public String clearPerDiemExpenses(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculate")
    public String recalculate(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=blanketApprove")
    public String blanketApprove(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copy")
    public String copy(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=newReimbursement")
    public String newReimbursement(@ModelAttribute("TravelReimbursementForm") TravelReimbursementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
