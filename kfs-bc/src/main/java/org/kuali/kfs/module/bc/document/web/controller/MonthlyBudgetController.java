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
import org.kuali.kfs.module.bc.document.web.struts.MonthlyBudgetAction;
import org.kuali.kfs.module.bc.document.web.struts.MonthlyBudgetForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing MonthlyBudgetAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/monthlyBudget")
public class MonthlyBudgetController {

    private final MonthlyBudgetAction delegate = new MonthlyBudgetAction();

    @RequestMapping
    public String execute(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadExpansionScreen")
    public String loadExpansionScreen(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=close")
    public String close(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMonthlySpread")
    public String performMonthlySpread(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMonthlyZero")
    public String performMonthlyZero(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performMonthlyDelete")
    public String performMonthlyDelete(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=showDetails")
    public String showDetails(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=hideDetails")
    public String hideDetails(@ModelAttribute("MonthlyBudgetForm") MonthlyBudgetForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
