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
package org.kuali.kfs.module.ld.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.ld.document.web.struts.BenefitExpenseTransferForm;
import org.kuali.kfs.sys.KFSConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing BenefitExpenseTransferAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/benefitExpenseTransfer")
public class BenefitExpenseTransferController {


    @RequestMapping(params = "methodToCall=performLookup")
    public String performLookup(@ModelAttribute("BenefitExpenseTransferForm") BenefitExpenseTransferForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteAllSourceAccountingLines")
    public String deleteAllSourceAccountingLines(@ModelAttribute("BenefitExpenseTransferForm") BenefitExpenseTransferForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteAllTargetAccountingLines")
    public String deleteAllTargetAccountingLines(@ModelAttribute("BenefitExpenseTransferForm") BenefitExpenseTransferForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
