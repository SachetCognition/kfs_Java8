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
import org.kuali.kfs.fp.document.web.struts.DisbursementVoucherAction;
import org.kuali.kfs.fp.document.web.struts.DisbursementVoucherForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing DisbursementVoucherAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/disbursementVoucher")
public class DisbursementVoucherController {

    private final DisbursementVoucherAction delegate = new DisbursementVoucherAction();

    @RequestMapping
    public String execute(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=approve")
    public String approve(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printDisbursementVoucherCoverSheet")
    public String printDisbursementVoucherCoverSheet(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=calculateTravelPerDiem")
    public String calculateTravelPerDiem(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearTravelPerDiem")
    public String clearTravelPerDiem(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=calculateTravelMileageAmount")
    public String calculateTravelMileageAmount(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearTravelMileageAmount")
    public String clearTravelMileageAmount(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addNonEmployeeExpenseLine")
    public String addNonEmployeeExpenseLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addPrePaidNonEmployeeExpenseLine")
    public String addPrePaidNonEmployeeExpenseLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteNonEmployeeExpenseLine")
    public String deleteNonEmployeeExpenseLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deletePrePaidEmployeeExpenseLine")
    public String deletePrePaidEmployeeExpenseLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addPreConfRegistrantLine")
    public String addPreConfRegistrantLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deletePreConfRegistrantLine")
    public String deletePreConfRegistrantLine(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=generateNonResidentAlienTaxLines")
    public String generateNonResidentAlienTaxLines(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearNonResidentAlienTaxLines")
    public String clearNonResidentAlienTaxLines(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearNonResidentAlienTaxInfo")
    public String clearNonResidentAlienTaxInfo(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performLookup")
    public String performLookup(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=extractNow")
    public String extractNow(@ModelAttribute("DisbursementVoucherForm") DisbursementVoucherForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
