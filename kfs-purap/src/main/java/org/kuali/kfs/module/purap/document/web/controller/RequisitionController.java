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
package org.kuali.kfs.module.purap.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing RequisitionAction.
 * Stub controller for phased migration from Struts Action.
 */
@Controller
@RequestMapping("/requisition")
public class RequisitionController {


    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=setAsDefaultBuilding")
    public String setAsDefaultBuilding(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=refresh")
    public String refresh(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addAsset")
    public String addAsset(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=displayB2BRequisition")
    public String displayB2BRequisition(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearVendor")
    public String clearVendor(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=blanketApprove")
    public String blanketApprove(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addItem")
    public String addItem(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("KualiDocumentFormBase") KualiDocumentFormBase form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
