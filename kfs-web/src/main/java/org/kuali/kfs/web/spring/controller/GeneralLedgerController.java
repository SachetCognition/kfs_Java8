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
 * Replaces Struts action mappings for General Ledger operations.
 * Original Actions:
 *   - /generalLedger* wildcard → CorrectionAction
 *   - /glBalanceInquiry → BalanceInquiryAction
 *   - /glBalanceInquiryLookup → BalanceInquiryLookupAction
 *   - /glModifiedInquiry → BalanceInquiryAction
 *   - /glAccountBalanceByConsolidationLookup → BalanceInquiryAction
 *   - /glTrialBalance → TrialBalanceReportAction
 */
@Controller
public class GeneralLedgerController extends KfsBaseController {

    @RequestMapping("/generalLedgerCorrection.do")
    public ModelAndView correction(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/Correction.jsp");
    }

    @RequestMapping("/glBalanceInquiry.do")
    public ModelAndView balanceInquiry(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/BalanceInquiry.jsp");
    }

    @RequestMapping("/glBalanceInquiryLookup.do")
    public ModelAndView balanceInquiryLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/BalanceInquiryLookup.jsp");
    }

    @RequestMapping("/glModifiedInquiry.do")
    public ModelAndView modifiedInquiry(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/ModifiedInquiry.jsp");
    }

    @RequestMapping("/glAccountBalanceByConsolidationLookup.do")
    public ModelAndView accountBalanceByConsolidationLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/AccountBalanceByConsolidationLookup.jsp");
    }

    @RequestMapping("/glTrialBalance.do")
    public ModelAndView trialBalance(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/TrialBalanceReportLookup.jsp");
    }
}
