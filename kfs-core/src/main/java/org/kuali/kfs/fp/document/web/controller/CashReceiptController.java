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
package org.kuali.kfs.fp.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.fp.document.web.struts.CashReceiptForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing CashReceiptAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/cashReceipt")
public class CashReceiptController {


    @RequestMapping
    public String execute(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printCoverSheet")
    public void printCoverSheet(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=addCheck")
    public String addCheck(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addConfirmedCheck")
    public String addConfirmedCheck(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteCheck")
    public String deleteCheck(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteConfirmedCheck")
    public String deleteConfirmedCheck(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=changeCheckEntryMode")
    public String changeCheckEntryMode(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copyAllChecks")
    public String copyAllChecks(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copyAllCurrencyAndCoin")
    public String copyAllCurrencyAndCoin(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copyAllChangeCurrencyAndCoin")
    public String copyAllChangeCurrencyAndCoin(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copy")
    public String copy(@ModelAttribute("CashReceiptForm") CashReceiptForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
