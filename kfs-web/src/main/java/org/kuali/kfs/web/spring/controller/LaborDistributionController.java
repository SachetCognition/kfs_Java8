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
 * Replaces Struts action mappings for Labor Distribution module.
 * Original wildcard: /labor* → org.kuali.kfs.module.ld.document.web.struts.{1}Action
 * Plus explicit actions: /laborLedgerCorrection, /laborLongRowTableInquiry,
 *   /fringeBenefitInquiry, /laborGLLaborEntrySummarizationInquiry
 */
@Controller
public class LaborDistributionController extends KfsBaseController {

    @RequestMapping("/laborSalaryExpenseTransfer.do")
    public ModelAndView salaryExpenseTransfer(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/SalaryExpenseTransfer.jsp");
    }

    @RequestMapping("/laborYearEndSalaryExpenseTransfer.do")
    public ModelAndView yearEndSalaryExpenseTransfer(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/YearEndSalaryExpenseTransfer.jsp");
    }

    @RequestMapping("/laborBenefitExpenseTransfer.do")
    public ModelAndView benefitExpenseTransfer(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/BenefitExpenseTransfer.jsp");
    }

    @RequestMapping("/laborLedgerCorrection.do")
    public ModelAndView laborLedgerCorrection(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/Correction.jsp");
    }

    @RequestMapping("/laborLongRowTableInquiry.do")
    public ModelAndView longRowTableInquiry(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/LongRowTableInquiry.jsp");
    }

    @RequestMapping("/fringeBenefitInquiry.do")
    public ModelAndView fringeBenefitInquiry(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/FringeBenefitInquiry.jsp");
    }

    @RequestMapping("/laborGLLaborEntrySummarizationInquiry.do")
    public ModelAndView glLaborEntrySummarizationInquiry(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ld/GLLaborEntrySummarizationInquiry.jsp");
    }
}
