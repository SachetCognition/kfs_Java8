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
import org.kuali.kfs.module.tem.document.web.struts.TravelEntertainmentAction;
import org.kuali.kfs.module.tem.document.web.struts.TravelEntertainmentForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing TravelEntertainmentAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/travelEntertainment")
public class TravelEntertainmentController {

    private final TravelEntertainmentAction delegate = new TravelEntertainmentAction();

    @RequestMapping(params = "methodToCall=docHandler")
    public String docHandler(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping
    public String execute(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=recalculate")
    public String recalculate(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=printCoversheet")
    public void printCoversheet(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=viewNonEmployeeForms")
    public void viewNonEmployeeForms(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=viewEntertainmentCertification")
    public void viewEntertainmentCertification(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Delegates to original Struts action (streaming/download response)
    }

    @RequestMapping(params = "methodToCall=route")
    public String route(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addAttendeeLine")
    public String addAttendeeLine(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=deleteAttendeeLine")
    public String deleteAttendeeLine(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=importAttendees")
    public String importAttendees(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=newEntertainment")
    public String newEntertainment(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=downloadBOAttachment")
    public String downloadBOAttachment(@ModelAttribute("TravelEntertainmentForm") TravelEntertainmentForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
