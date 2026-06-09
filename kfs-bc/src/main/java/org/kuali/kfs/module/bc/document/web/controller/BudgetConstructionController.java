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
import org.kuali.kfs.module.bc.document.web.struts.BudgetConstructionAction;
import org.kuali.kfs.module.bc.document.web.struts.BudgetConstructionForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing BudgetConstructionAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/budgetConstruction")
public class BudgetConstructionController {

    private final BudgetConstructionAction delegate = new BudgetConstructionAction();

    @RequestMapping
    public String execute(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=docHandler")
    public String docHandler(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=close")
    public String close(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performShowBenefits")
    public String performShowBenefits(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performBalanceInquiryForRevenueLine")
    public String performBalanceInquiryForRevenueLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performBalanceInquiryForExpenditureLine")
    public String performBalanceInquiryForExpenditureLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMonthlyRevenueBudget")
    public String performMonthlyRevenueBudget(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMonthlyExpenditureBudget")
    public String performMonthlyExpenditureBudget(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performSalarySetting")
    public String performSalarySetting(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertRevenueLine")
    public String insertRevenueLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertExpenditureLine")
    public String insertExpenditureLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteRevenueLine")
    public String deleteRevenueLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteExpenditureLine")
    public String deleteExpenditureLine(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=showDetails")
    public String showDetails(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=hideDetails")
    public String hideDetails(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=toggleAdjustmentMeasurement")
    public String toggleAdjustmentMeasurement(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=adjustRevenueLinePercent")
    public String adjustRevenueLinePercent(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=adjustExpenditureLinePercent")
    public String adjustExpenditureLinePercent(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=adjustAllRevenueLinesPercent")
    public String adjustAllRevenueLinesPercent(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=adjustAllExpenditureLinesPercent")
    public String adjustAllExpenditureLinesPercent(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performAccountPullup")
    public String performAccountPullup(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performAccountPushdown")
    public String performAccountPushdown(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performReportDump")
    public String performReportDump(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performPercentChange")
    public String performPercentChange(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performRevMonthSpread")
    public String performRevMonthSpread(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performExpMonthSpread")
    public String performExpMonthSpread(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performRevMonthDelete")
    public String performRevMonthDelete(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performExpMonthDelete")
    public String performExpMonthDelete(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performCalculateBenefits")
    public String performCalculateBenefits(@ModelAttribute("BudgetConstructionForm") BudgetConstructionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
