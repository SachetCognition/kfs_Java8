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
import org.kuali.kfs.fp.document.web.struts.AdvanceDepositForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing AdvanceDepositAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/advanceDeposit")
public class AdvanceDepositController {


    @RequestMapping
    public String execute(@ModelAttribute("AdvanceDepositForm") AdvanceDepositForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=copy")
    public String copy(@ModelAttribute("AdvanceDepositForm") AdvanceDepositForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addAdvanceDeposit")
    public String addAdvanceDeposit(@ModelAttribute("AdvanceDepositForm") AdvanceDepositForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteAdvanceDeposit")
    public String deleteAdvanceDeposit(@ModelAttribute("AdvanceDepositForm") AdvanceDepositForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
