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
package org.kuali.kfs.fp.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.fp.document.web.struts.CashManagementAction;
import org.kuali.kfs.fp.document.web.struts.CashManagementForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSConstants.CashDrawerConstants;
import org.kuali.kfs.sys.KFSConstants.DepositConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing CashManagementAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/cashManagement")
public class CashManagementController {

    private final CashManagementAction delegate = new CashManagementAction();

    @RequestMapping
    public String execute(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addInterimDeposit")
    public String addInterimDeposit(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addFinalDeposit")
    public String addFinalDeposit(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancelDeposit")
    public String cancelDeposit(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=reload")
    public String reload(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshSummary")
    public String refreshSummary(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=openCashDrawer")
    public String openCashDrawer(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=finalizeLastInterimDeposit")
    public String finalizeLastInterimDeposit(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=applyCashieringTransaction")
    public String applyCashieringTransaction(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=correctCashDrawer")
    public String correctCashDrawer(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addCheck")
    public String addCheck(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteCheck")
    public String deleteCheck(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("CashManagementForm") CashManagementForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
