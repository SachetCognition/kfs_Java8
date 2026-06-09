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
 * Replaces Struts action mappings for Budget Construction module.
 * Original wildcard: /budget* → org.kuali.kfs.module.bc.document.web.struts.{1}Action
 * Plus explicit actions for organization selection, salary settings, etc.
 */
@Controller
public class BudgetConstructionController extends KfsBaseController {

    @RequestMapping("/budgetBudgetConstruction.do")
    public ModelAndView budgetConstruction(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/BudgetConstruction.jsp");
    }

    @RequestMapping("/budgetOrganizationSelectionTree.do")
    public ModelAndView organizationSelectionTree(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/OrganizationSelectionTree.jsp");
    }

    @RequestMapping("/budgetOrganizationReportSelection.do")
    public ModelAndView organizationReportSelection(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/OrganizationReportSelection.jsp");
    }

    @RequestMapping("/budgetMonthlyBudget.do")
    public ModelAndView monthlyBudget(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/MonthlyBudget.jsp");
    }

    @RequestMapping("/budgetQuickSalarySetting.do")
    public ModelAndView quickSalarySetting(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/QuickSalarySetting.jsp");
    }

    @RequestMapping("/budgetPositionSalarySetting.do")
    public ModelAndView positionSalarySetting(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/PositionSalarySetting.jsp");
    }

    @RequestMapping("/budgetIncumbentSalarySetting.do")
    public ModelAndView incumbentSalarySetting(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/IncumbentSalarySetting.jsp");
    }

    @RequestMapping("/budgetTempListLookup.do")
    public ModelAndView tempListLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/TempListLookup.jsp");
    }

    @RequestMapping("/budgetReportExport.do")
    public ModelAndView reportExport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/ReportExport.jsp");
    }

    @RequestMapping("/budgetReportRunner.do")
    public ModelAndView reportRunner(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/ReportRunner.jsp");
    }

    @RequestMapping("/budgetPayrateImportExport.do")
    public ModelAndView payrateImportExport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/PayrateImportExport.jsp");
    }

    @RequestMapping("/budgetBudgetExpansion.do")
    public ModelAndView budgetExpansion(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/bc/BudgetExpansion.jsp");
    }
}
