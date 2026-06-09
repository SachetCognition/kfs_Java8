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
 * Replaces Struts action mappings for Accounts Receivable module.
 * Original wildcard: /ar* → org.kuali.kfs.module.ar.document.web.struts.{1}Action
 * Plus explicit AR report actions.
 */
@Controller
public class AccountsReceivableController extends KfsBaseController {

    @RequestMapping("/arTicklersReport.do")
    public ModelAndView ticklersReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/collectionActivityReportLookup.do")
    public ModelAndView collectionActivityReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsAgingReportLookup.do")
    public ModelAndView contractsGrantsAgingReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsAgingReportLookup.jsp");
    }

    @RequestMapping("/arPaymentApplicationDocument.do")
    public ModelAndView paymentApplication(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/PaymentApplicationDocument.jsp");
    }

    @RequestMapping("/arContractsGrantsCollectionActivityDocument.do")
    public ModelAndView contractsGrantsCollectionActivity(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsCollectionActivityDocument.jsp");
    }

    @RequestMapping("/arCashControlDocument.do")
    public ModelAndView cashControl(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CashControlDocument.jsp");
    }

    @RequestMapping("/arCustomerCreditMemoDocument.do")
    public ModelAndView customerCreditMemo(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerCreditMemoDocument.jsp");
    }

    @RequestMapping("/arCustomerInvoiceDocument.do")
    public ModelAndView customerInvoice(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerInvoiceDocument.jsp");
    }

    @RequestMapping("/arCustomerInvoiceWriteoffDocument.do")
    public ModelAndView customerInvoiceWriteoff(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerInvoiceWriteoffDocument.jsp");
    }

    @RequestMapping("/arContractsGrantsInvoiceDocument.do")
    public ModelAndView contractsGrantsInvoice(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsInvoiceDocument.jsp");
    }

    @RequestMapping("/arFinalBilledIndicatorDocument.do")
    public ModelAndView finalBilledIndicator(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/FinalBilledIndicatorDocument.jsp");
    }

    @RequestMapping("/arContractsGrantsLetterOfCreditReviewDocument.do")
    public ModelAndView contractsGrantsLetterOfCreditReview(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsLetterOfCreditReviewDocument.jsp");
    }

    @RequestMapping("/arAccountsReceivableInvoiceTemplateUpload.do")
    public ModelAndView invoiceTemplateUpload(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/AccountsReceivableInvoiceTemplateUpload.jsp");
    }

    @RequestMapping("/arAccountsReceivableDunningLetterTemplateUpload.do")
    public ModelAndView dunningLetterTemplateUpload(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/AccountsReceivableDunningLetterTemplateUpload.jsp");
    }

    @RequestMapping("/arFederalFinancialReport.do")
    public ModelAndView federalFinancialReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/FederalFinancialReport.jsp");
    }

    @RequestMapping("/arTransmitContractsAndGrantsInvoices.do")
    public ModelAndView transmitContractsAndGrantsInvoices(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/TransmitContractsAndGrantsInvoicesLookup.jsp");
    }

    @RequestMapping("/arContractsGrantsInvoiceSummary.do")
    public ModelAndView contractsGrantsInvoiceSummary(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsInvoiceSummary.jsp");
    }

    @RequestMapping("/arContractsGrantsInvoiceLookup.do")
    public ModelAndView contractsGrantsInvoiceLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/ContractsGrantsInvoiceLookup.jsp");
    }

    @RequestMapping("/arGenerateDunningLettersSummary.do")
    public ModelAndView generateDunningLettersSummary(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/GenerateDunningLettersSummary.jsp");
    }

    @RequestMapping("/arGenerateDunningLettersLookup.do")
    public ModelAndView generateDunningLettersLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/GenerateDunningLettersLookup.jsp");
    }

    @RequestMapping("/arCustomerOpenItemReportLookup.do")
    public ModelAndView customerOpenItemReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerOpenItemReportLookup.jsp");
    }

    @RequestMapping("/arCustomerAgingReportLookup.do")
    public ModelAndView customerAgingReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerAgingReportLookup.jsp");
    }

    @RequestMapping("/arContractsGrantsAgingOpenInvoicesReportLookup.do")
    public ModelAndView contractsGrantsAgingOpenInvoicesReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/arCustomerInvoiceWriteoffLookup.do")
    public ModelAndView customerInvoiceWriteoffLookup(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerInvoiceWriteoffLookup.jsp");
    }

    @RequestMapping("/arCustomerInvoiceWriteoffLookupSummary.do")
    public ModelAndView customerInvoiceWriteoffLookupSummary(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerInvoiceWriteoffLookupSummary.jsp");
    }

    @RequestMapping("/arCustomerStatement.do")
    public ModelAndView customerStatement(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerStatement.jsp");
    }

    @RequestMapping("/arCustomerInvoice.do")
    public ModelAndView customerInvoiceReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/ar/CustomerInvoice.jsp");
    }

    // Contracts & Grants report actions
    @RequestMapping("/contractsGrantsInvoiceDocumentErrorLogReport.do")
    public ModelAndView invoiceDocumentErrorLogReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsInvoiceReport.do")
    public ModelAndView invoiceReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsPaymentHistoryReport.do")
    public ModelAndView paymentHistoryReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsLOCReport.do")
    public ModelAndView locReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsLOCAmountsNotDrawnReport.do")
    public ModelAndView locAmountsNotDrawnReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsSuspendedInvoiceSummaryReport.do")
    public ModelAndView suspendedInvoiceSummaryReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsSuspendedInvoiceDetailReport.do")
    public ModelAndView suspendedInvoiceDetailReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }

    @RequestMapping("/contractsGrantsMilestoneReport.do")
    public ModelAndView milestoneReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/common/ContractsGrantsReportLookup.jsp");
    }
}
