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
import org.kuali.kfs.module.ar.document.web.struts.ContractsGrantsInvoiceDocumentForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing ContractsGrantsInvoiceDocumentAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/contractsGrantsInvoiceDocument")
public class ContractsGrantsInvoiceDocumentController {


    @RequestMapping
    public String execute(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculateTotalAmountBilledToDate")
    public String recalculateTotalAmountBilledToDate(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=prorateBill")
    public String prorateBill(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearInitialTransmissionDate")
    public String clearInitialTransmissionDate(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=setInitialTransmissionDate")
    public String setInitialTransmissionDate(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("ContractsGrantsInvoiceDocumentForm") ContractsGrantsInvoiceDocumentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
