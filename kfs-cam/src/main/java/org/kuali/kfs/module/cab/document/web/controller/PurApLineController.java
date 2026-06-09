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
package org.kuali.kfs.module.cab.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.cab.document.web.struts.PurApLineAction;
import org.kuali.kfs.module.cab.document.web.struts.PurApLineForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing PurApLineAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/purApLine")
public class PurApLineController {

    private final PurApLineAction delegate = new PurApLineAction();

    @RequestMapping(params = "methodToCall=start")
    public String start(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=reload")
    public String reload(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=save")
    public String save(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=close")
    public String close(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=split")
    public String split(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=merge")
    public String merge(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=percentPayment")
    public String percentPayment(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=allocate")
    public String allocate(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=applyPayment")
    public String applyPayment(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=createAsset")
    public String createAsset(@ModelAttribute("PurApLineForm") PurApLineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
