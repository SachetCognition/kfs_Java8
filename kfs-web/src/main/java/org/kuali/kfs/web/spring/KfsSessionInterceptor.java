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
package org.kuali.kfs.web.spring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC interceptor that bridges KFS/Rice session management.
 * Ensures the user session is available for controller methods that delegate
 * to service-layer code expecting it. Uses reflection to avoid compile-time
 * dependency on Rice's javax-based UserSession/GlobalVariables classes.
 */
public class KfsSessionInterceptor implements HandlerInterceptor {

    private static final String USER_SESSION_KEY = "kualiUserSession";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object userSession = session.getAttribute(USER_SESSION_KEY);
            if (userSession != null) {
                // Bridge to Rice GlobalVariables via reflection to avoid
                // jakarta/javax namespace conflict at compile time
                try {
                    Class<?> globalVarsClass = Class.forName("org.kuali.rice.krad.util.GlobalVariables");
                    java.lang.reflect.Method setter = globalVarsClass.getMethod("setUserSession",
                            Class.forName("org.kuali.rice.krad.UserSession"));
                    setter.invoke(null, userSession);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    // Rice not on classpath – running in pure Spring Boot mode
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // no-op
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            Class<?> globalVarsClass = Class.forName("org.kuali.rice.krad.util.GlobalVariables");
            java.lang.reflect.Method clear = globalVarsClass.getMethod("clear");
            clear.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            // Rice not on classpath – running in pure Spring Boot mode
        }
    }
}
