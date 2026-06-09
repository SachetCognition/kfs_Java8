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
import org.kuali.kfs.gl.document.web.struts.CorrectionForm;
import org.kuali.kfs.module.ld.document.web.struts.LaborCorrectionAction;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing LaborCorrectionAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/laborCorrection")
public class LaborCorrectionController {

    private final LaborCorrectionAction delegate = new LaborCorrectionAction();

    @RequestMapping
    public String execute(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=docHandler")
    public String docHandler(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=saveManualEntry")
    public String saveManualEntry(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addManualEntry")
    public String addManualEntry(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=editManualEntry")
    public String editManualEntry(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=sort")
    public String sort(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=selectSystemEditMethod")
    public String selectSystemEditMethod(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadGroup")
    public String loadGroup(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=confirmDeleteDocument")
    public String confirmDeleteDocument(@ModelAttribute("CorrectionForm") CorrectionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
