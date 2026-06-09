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
package org.kuali.kfs.module.purap.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.purap.document.web.struts.PurchaseOrderAction;
import org.kuali.kfs.module.purap.document.web.struts.PurchaseOrderForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing PurchaseOrderAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    private final PurchaseOrderAction delegate = new PurchaseOrderAction();

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=inactivateItem")
    public String inactivateItem(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=closePo")
    public String closePo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=paymentHoldPo")
    public String paymentHoldPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=removeHoldPo")
    public String removeHoldPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=reopenPo")
    public String reopenPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=amendPo")
    public String amendPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=voidPo")
    public String voidPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=splitPo")
    public String splitPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=continuePurchaseOrderSplit")
    public String continuePurchaseOrderSplit(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancelPurchaseOrderSplit")
    public String cancelPurchaseOrderSplit(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=assignSensitiveData")
    public String assignSensitiveData(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=submitSensitiveData")
    public String submitSensitiveData(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancelSensitiveData")
    public String cancelSensitiveData(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addSensitiveData")
    public String addSensitiveData(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteSensitiveData")
    public String deleteSensitiveData(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=firstTransmitPrintPo")
    public String firstTransmitPrintPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "printPurchaseOrderPDF";
    }

    @RequestMapping(params = "methodToCall=printPurchaseOrderPDFOnly")
    public void printPurchaseOrderPDFOnly(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=printPoQuote")
    public void printPoQuote(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=printPoQuoteList")
    public String printPoQuoteList(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "printPOQuoteListPDF";
    }

    @RequestMapping(params = "methodToCall=printPoQuoteListOnly")
    public void printPoQuoteListOnly(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=transmitPurchaseOrderQuote")
    public String transmitPurchaseOrderQuote(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectAllForRetransmit")
    public String selectAllForRetransmit(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deselectAllForRetransmit")
    public String deselectAllForRetransmit(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=retransmitPo")
    public String retransmitPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printingPreviewPo")
    public String printingPreviewPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "printPurchaseOrderPDF";
    }

    @RequestMapping(params = "methodToCall=printingRetransmitPo")
    public String printingRetransmitPo(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "retransmitPurchaseOrderPDF";
    }

    @RequestMapping(params = "methodToCall=printingRetransmitPoOnly")
    public void printingRetransmitPoOnly(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=addStipulation")
    public String addStipulation(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteStipulation")
    public String deleteStipulation(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=docHandler")
    public String docHandler(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=initiateQuote")
    public String initiateQuote(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addVendor")
    public String addVendor(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteVendor")
    public String deleteVendor(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=completeQuote")
    public String completeQuote(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancelQuote")
    public String cancelQuote(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addAsset")
    public String addAsset(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=removeAlternateVendor")
    public String removeAlternateVendor(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=createReceivingLine")
    public String createReceivingLine(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=resendPoCxml")
    public String resendPoCxml(@ModelAttribute("PurchaseOrderForm") PurchaseOrderForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
