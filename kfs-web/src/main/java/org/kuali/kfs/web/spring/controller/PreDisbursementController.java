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
 * Replaces Struts action mappings for Pre-Disbursement Processor (PDP) module.
 * Original actions:
 *   /pdp/paymentdetail → PaymentDetailAction
 *   /batchDetail → BatchAction
 *   /pdp/format → FormatAction
 */
@Controller
public class PreDisbursementController extends KfsBaseController {

    @RequestMapping("/pdp/paymentdetail.do")
    public ModelAndView paymentDetail(HttpServletRequest request, HttpServletResponse response) {
        // PaymentDetailAction has no default forward; returns null
        return null;
    }

    @RequestMapping("/batchDetail.do")
    public ModelAndView batchDetail(HttpServletRequest request, HttpServletResponse response) {
        // BatchAction has no default forward; returns null
        return null;
    }

    @RequestMapping("/pdp/format.do")
    public ModelAndView format(HttpServletRequest request, HttpServletResponse response) {
        String methodToCall = getMethodToCall(request);
        if ("continue".equals(methodToCall)) {
            return forward("/jsp/pdp/format/formatContinue.jsp");
        } else if ("running".equals(methodToCall)) {
            return forward("/jsp/pdp/format/processRunning.jsp");
        }
        return forward("/jsp/pdp/format/formatSelection.jsp");
    }
}
