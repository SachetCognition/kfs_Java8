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
 * Replaces Struts action mappings for Purchasing and Accounts Payable (PURAP) module.
 * Original wildcard: /purap* → org.kuali.kfs.module.purap.document.web.struts.{1}Action
 * Plus explicit actions: /purapPrint, /purapElectronicInvoiceTestFileGeneration, /b2b
 */
@Controller
public class PurchasingAccountsPayableController extends KfsBaseController {

    @RequestMapping("/purapRequisition.do")
    public ModelAndView requisition(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/Requisition.jsp");
    }

    @RequestMapping("/purapPurchaseOrder.do")
    public ModelAndView purchaseOrder(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/PurchaseOrder.jsp");
    }

    @RequestMapping("/purapPaymentRequest.do")
    public ModelAndView paymentRequest(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/PaymentRequest.jsp");
    }

    @RequestMapping("/purapVendorCreditMemo.do")
    public ModelAndView vendorCreditMemo(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/VendorCreditMemo.jsp");
    }

    @RequestMapping("/purapLineItemReceiving.do")
    public ModelAndView lineItemReceiving(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/LineItemReceiving.jsp");
    }

    @RequestMapping("/purapCorrectionReceiving.do")
    public ModelAndView correctionReceiving(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/CorrectionReceiving.jsp");
    }

    @RequestMapping("/purapBulkReceiving.do")
    public ModelAndView bulkReceiving(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/BulkReceiving.jsp");
    }

    @RequestMapping("/purapElectronicInvoiceReject.do")
    public ModelAndView electronicInvoiceReject(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/ElectronicInvoiceReject.jsp");
    }

    @RequestMapping("/purapContractManagerAssignment.do")
    public ModelAndView contractManagerAssignment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/ContractManagerAssignment.jsp");
    }

    @RequestMapping("/purapPrint.do")
    public ModelAndView print(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/PrintForward.jsp");
    }

    @RequestMapping("/purapElectronicInvoiceTestFileGeneration.do")
    public ModelAndView electronicInvoiceTest(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/ElectronicInvoiceTest.jsp");
    }

    @RequestMapping("/b2b.do")
    public ModelAndView b2b(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/purap/ShopCatalogs.jsp");
    }
}
