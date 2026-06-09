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
package org.kuali.kfs.module.tem.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.tem.document.web.struts.TemCorrectionAction;
import org.kuali.kfs.module.tem.document.web.struts.TemCorrectionForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing TemCorrectionAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/temCorrection")
public class TemCorrectionController {

    private final TemCorrectionAction delegate = new TemCorrectionAction();

    @RequestMapping(params = "methodToCall=switchToPage")
    public String switchToPage(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=sort")
    public String sort(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectSystemEditMethod")
    public String selectSystemEditMethod(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping
    public String execute(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=editManualEntry")
    public String editManualEntry(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addManualEntry")
    public String addManualEntry(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteManualEntry")
    public String deleteManualEntry(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=saveManualEntry")
    public String saveManualEntry(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadGroup")
    public String loadGroup(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=blanketApprove")
    public String blanketApprove(@ModelAttribute("TemCorrectionForm") TemCorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
