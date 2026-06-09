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
 * Replaces Struts action mappings for Travel and Expense Module (TEM).
 * Original wildcard: /tem* → org.kuali.kfs.module.tem.document.web.struts.{1}Action
 * Plus explicit: /temDV, /temCorrectionDocument, /tem*CardApplication
 */
@Controller
public class TravelExpenseController extends KfsBaseController {

    @RequestMapping("/temTravelAuthorization.do")
    public ModelAndView travelAuthorization(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TravelAuthorization.jsp");
    }

    @RequestMapping("/temTravelReimbursement.do")
    public ModelAndView travelReimbursement(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TravelReimbursement.jsp");
    }

    @RequestMapping("/temTravelEntertainment.do")
    public ModelAndView travelEntertainment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TravelEntertainment.jsp");
    }

    @RequestMapping("/temTravelRelocation.do")
    public ModelAndView travelRelocation(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TravelRelocation.jsp");
    }

    @RequestMapping("/temTravelArranger.do")
    public ModelAndView travelArranger(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TravelArranger.jsp");
    }

    @RequestMapping("/temDV.do")
    public ModelAndView travelDisbursementVoucher(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/fp/DisbursementVoucher.jsp");
    }

    @RequestMapping("/temCorrectionDocument.do")
    public ModelAndView temCorrection(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TemCorrectionDocument.jsp");
    }

    @RequestMapping("/temCardApplication.do")
    public ModelAndView temCardApplication(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/CardApplicationDocument.jsp");
    }

    @RequestMapping("/temReport.do")
    public ModelAndView temReport(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/Report.jsp");
    }

    @RequestMapping("/temTaxableRamification.do")
    public ModelAndView taxableRamification(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/tem/TaxableRamification.jsp");
    }
}
