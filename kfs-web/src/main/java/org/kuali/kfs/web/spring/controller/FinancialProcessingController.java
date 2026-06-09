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
 * Replaces Struts wildcard mapping: /financial* → {1}Action
 * Original: org.kuali.kfs.fp.document.web.struts.*Action
 *
 * Covers all Financial Processing document actions including:
 * - CashReceipt, CreditCardReceipt, AdvanceDeposit
 * - InternalBilling, ServiceBilling, TransferOfFunds
 * - BudgetAdjustment, DisbursementVoucher, JournalVoucher
 * - GeneralErrorCorrection, DistributionOfIncomeAndExpense
 * - ProcurementCard, PreEncumbrance, IndirectCostAdjustment
 * - NonCheckDisbursement, AuxiliaryVoucher, IntraAccountAdjustment
 * - Year-end variants of the above
 */
@Controller
public class FinancialProcessingController extends KfsBaseController {

    @RequestMapping("/financialCashReceipt.do")
    public ModelAndView cashReceipt(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/CashReceipt.jsp");
    }

    @RequestMapping("/financialCreditCardReceipt.do")
    public ModelAndView creditCardReceipt(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/CreditCardReceipt.jsp");
    }

    @RequestMapping("/financialAdvanceDeposit.do")
    public ModelAndView advanceDeposit(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/AdvanceDeposit.jsp");
    }

    @RequestMapping("/financialInternalBilling.do")
    public ModelAndView internalBilling(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/InternalBilling.jsp");
    }

    @RequestMapping("/financialServiceBilling.do")
    public ModelAndView serviceBilling(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/ServiceBilling.jsp");
    }

    @RequestMapping("/financialTransferOfFunds.do")
    public ModelAndView transferOfFunds(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/TransferOfFunds.jsp");
    }

    @RequestMapping("/financialYearEndTransferOfFunds.do")
    public ModelAndView yearEndTransferOfFunds(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/YearEndTransferOfFunds.jsp");
    }

    @RequestMapping("/financialGeneralErrorCorrection.do")
    public ModelAndView generalErrorCorrection(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/GeneralErrorCorrection.jsp");
    }

    @RequestMapping("/financialYearEndGeneralErrorCorrection.do")
    public ModelAndView yearEndGeneralErrorCorrection(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/YearEndGeneralErrorCorrection.jsp");
    }

    @RequestMapping("/financialDistributionOfIncomeAndExpense.do")
    public ModelAndView distributionOfIncomeAndExpense(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/DistributionOfIncomeAndExpense.jsp");
    }

    @RequestMapping("/financialYearEndDistributionOfIncomeAndExpense.do")
    public ModelAndView yearEndDistributionOfIncomeAndExpense(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/YearEndDistributionOfIncomeAndExpense.jsp");
    }

    @RequestMapping("/financialAuxiliaryVoucher.do")
    public ModelAndView auxiliaryVoucher(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/AuxiliaryVoucher.jsp");
    }

    @RequestMapping("/financialJournalVoucher.do")
    public ModelAndView journalVoucher(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/JournalVoucher.jsp");
    }

    @RequestMapping("/financialIndirectCostAdjustment.do")
    public ModelAndView indirectCostAdjustment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/IndirectCostAdjustment.jsp");
    }

    @RequestMapping("/financialNonCheckDisbursement.do")
    public ModelAndView nonCheckDisbursement(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/NonCheckDisbursement.jsp");
    }

    @RequestMapping("/financialPreEncumbrance.do")
    public ModelAndView preEncumbrance(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/PreEncumbrance.jsp");
    }

    @RequestMapping("/financialDisbursementVoucher.do")
    public ModelAndView disbursementVoucher(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/DisbursementVoucher.jsp");
    }

    @RequestMapping("/financialCashManagement.do")
    public ModelAndView cashManagement(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/CashManagement.jsp");
    }

    @RequestMapping("/financialProcurementCard.do")
    public ModelAndView procurementCard(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/ProcurementCard.jsp");
    }

    @RequestMapping("/financialBudgetAdjustment.do")
    public ModelAndView budgetAdjustment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/BudgetAdjustment.jsp");
    }

    @RequestMapping("/financialYearEndBudgetAdjustment.do")
    public ModelAndView yearEndBudgetAdjustment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/YearEndBudgetAdjustment.jsp");
    }

    @RequestMapping("/financialIntraAccountAdjustment.do")
    public ModelAndView intraAccountAdjustment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/IntraAccountAdjustment.jsp");
    }

    @RequestMapping("/dvPerDiem.do")
    public ModelAndView dvPerDiem(HttpServletRequest request, HttpServletResponse response) {
        return forward("/kr/WEB-INF/jsp/core/KualiHelp.jsp");
    }

    @RequestMapping("/depositWizard.do")
    public ModelAndView depositWizard(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/DepositWizard.jsp");
    }

    @RequestMapping("/cashManagementStatus.do")
    public ModelAndView cashManagementStatus(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/CashManagementStatus.jsp");
    }
}
