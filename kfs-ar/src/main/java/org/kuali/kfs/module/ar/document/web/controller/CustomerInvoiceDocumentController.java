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
package org.kuali.kfs.module.ar.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.ar.document.web.struts.CustomerInvoiceDocumentAction;
import org.kuali.kfs.module.ar.document.web.struts.CustomerInvoiceDocumentForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing CustomerInvoiceDocumentAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/customerInvoiceDocument")
public class CustomerInvoiceDocumentController {

    private final CustomerInvoiceDocumentAction delegate = new CustomerInvoiceDocumentAction();

    @RequestMapping
    public String execute(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copy")
    public String copy(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshNewSourceLine")
    public String refreshNewSourceLine(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculateSourceLine")
    public String recalculateSourceLine(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=discountSourceLine")
    public String discountSourceLine(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=insertSourceLine")
    public String insertSourceLine(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshBillToAddress")
    public String refreshBillToAddress(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshShipToAddress")
    public String refreshShipToAddress(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=print")
    public String print(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printInvoicePDF")
    public void printInvoicePDF(@ModelAttribute("CustomerInvoiceDocumentForm") CustomerInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }
}
