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
import org.kuali.kfs.module.purap.document.web.struts.PaymentRequestForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing PaymentRequestAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/paymentRequest")
public class PaymentRequestController {


    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=continuePREQ")
    public String continuePREQ(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearInitFields")
    public String clearInitFields(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addHoldOnPayment")
    public String addHoldOnPayment(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=removeHoldFromPayment")
    public String removeHoldFromPayment(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=requestCancelOnPayment")
    public String requestCancelOnPayment(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=removeCancelRequestFromPayment")
    public String removeCancelRequestFromPayment(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=useAlternateVendor")
    public String useAlternateVendor(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=useOriginalVendor")
    public String useOriginalVendor(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=changeUseTaxIndicator")
    public String changeUseTaxIndicator(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearTaxInfo")
    public String clearTaxInfo(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearQty")
    public String clearQty(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadQty")
    public String loadQty(@ModelAttribute("PaymentRequestForm") PaymentRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
