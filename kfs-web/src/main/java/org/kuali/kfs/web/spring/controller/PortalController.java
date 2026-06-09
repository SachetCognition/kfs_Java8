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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Replaces Struts action mappings for /portal.do and /index.do.
 * Original: org.kuali.rice.kns.web.struts.action.KualiPortalAction
 */
@Controller
public class PortalController extends KfsBaseController {

    @RequestMapping("/portal.do")
    public ModelAndView portal(HttpServletRequest request, HttpServletResponse response) {
        return forward("/portal.jsp");
    }

    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return forward("/portal.jsp");
    }

    @RequestMapping("/backdoorlogin.do")
    public ModelAndView backdoorLogin(HttpServletRequest request, HttpServletResponse response) {
        String methodToCall = getMethodToCall(request);
        if ("logout".equals(methodToCall)) {
            return redirect("/logout.do");
        }
        return forward("/portal.jsp");
    }

    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return redirect("/portal.do");
    }

    @RequestMapping("/updateTextArea.do")
    public ModelAndView updateTextArea(HttpServletRequest request, HttpServletResponse response) {
        return forward("/kr/WEB-INF/jsp/TextArea.jsp");
    }

    @RequestMapping("/SessionInvalidateAction.do")
    public ModelAndView sessionInvalidate(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/SessionExpiration.jsp");
    }
}
