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
import org.kuali.kfs.module.ar.document.web.struts.CustomerCreditMemoDocumentAction;
import org.kuali.kfs.module.ar.document.web.struts.CustomerCreditMemoDocumentForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing CustomerCreditMemoDocumentAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/customerCreditMemoDocument")
public class CustomerCreditMemoDocumentController {

    private final CustomerCreditMemoDocumentAction delegate = new CustomerCreditMemoDocumentAction();

    @RequestMapping(params = "methodToCall=clearInitTab")
    public String clearInitTab(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=continueCreditMemo")
    public String continueCreditMemo(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculateCustomerCreditMemoDetail")
    public String recalculateCustomerCreditMemoDetail(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshCustomerCreditMemoDetail")
    public String refreshCustomerCreditMemoDetail(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refreshCustomerCreditMemoDocument")
    public String refreshCustomerCreditMemoDocument(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculateCustomerCreditMemoDocument")
    public String recalculateCustomerCreditMemoDocument(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=print")
    public String print(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printCreditMemoPDF")
    public void printCreditMemoPDF(@ModelAttribute("CustomerCreditMemoDocumentForm") CustomerCreditMemoDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }
}
