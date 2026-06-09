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
package org.kuali.kfs.module.bc.document.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kuali.kfs.module.bc.document.web.struts.TempListLookupAction;
import org.kuali.kfs.module.bc.document.web.struts.TempListLookupForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing TempListLookupAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/tempListLookup")
public class TempListLookupController {

    private final TempListLookupAction delegate = new TempListLookupAction();

    @RequestMapping
    public String execute(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=start")
    public String start(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=cancel")
    public String cancel(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performExtendedPositionSearch")
    public String performExtendedPositionSearch(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=getNewPosition")
    public String getNewPosition(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performExtendedIncumbentSearch")
    public String performExtendedIncumbentSearch(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=getNewIncumbent")
    public String getNewIncumbent(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=submitReport")
    public String submitReport(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=unlock")
    public String unlock(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=performLookup")
    public String performLookup(@ModelAttribute("TempListLookupForm") TempListLookupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
