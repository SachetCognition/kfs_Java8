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
import org.kuali.kfs.module.purap.document.web.struts.LineItemReceivingAction;
import org.kuali.kfs.module.purap.document.web.struts.LineItemReceivingForm;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC controller replacing LineItemReceivingAction.
 * Delegates to the original Struts Action for business logic.
 */
@Controller
@RequestMapping("/lineItemReceiving")
public class LineItemReceivingController {

    private final LineItemReceivingAction delegate = new LineItemReceivingAction();

    @RequestMapping(params = "methodToCall=continueReceivingLine")
    public String continueReceivingLine(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=createReceivingCorrection")
    public String createReceivingCorrection(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearInitFields")
    public String clearInitFields(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=addItem")
    public String addItem(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=clearQty")
    public String clearQty(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=loadQty")
    public String loadQty(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }

    @RequestMapping(params = "methodToCall=showAddUnorderedItem")
    public String showAddUnorderedItem(@ModelAttribute("LineItemReceivingForm") LineItemReceivingForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "basic";
    }
}
