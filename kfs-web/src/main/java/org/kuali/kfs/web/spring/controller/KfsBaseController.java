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

import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base controller providing common utilities for KFS Spring MVC controllers.
 * Mirrors the dispatch pattern from Struts 1 KualiAction where methodToCall
 * parameter determines which handler executes.
 */
public abstract class KfsBaseController {

    protected static final String METHOD_TO_CALL_PARAM = "methodToCall";

    protected String getMethodToCall(HttpServletRequest request) {
        String methodToCall = request.getParameter(METHOD_TO_CALL_PARAM);
        if (methodToCall == null || methodToCall.isBlank()) {
            return "start";
        }
        return methodToCall;
    }

    protected ModelAndView forward(String viewPath) {
        return new ModelAndView(viewPath);
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }

    protected void checkAuthorization(HttpServletRequest request) {
        // Authorization check delegated to existing KFS/Rice permission services
        // via the KfsSessionInterceptor
    }
}
