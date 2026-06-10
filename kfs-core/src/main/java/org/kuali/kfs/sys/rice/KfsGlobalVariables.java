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
package org.kuali.kfs.sys.rice;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * KFS-owned adapter for Rice GlobalVariables. Delegates to the Rice GlobalVariables
 * static methods to minimize direct Rice coupling across KFS source files.
 */
public final class KfsGlobalVariables {

    private KfsGlobalVariables() {
        // static utility class
    }

    public static UserSession getUserSession() {
        return GlobalVariables.getUserSession();
    }

    public static void setUserSession(UserSession userSession) {
        GlobalVariables.setUserSession(userSession);
    }

    public static MessageMap getMessageMap() {
        return GlobalVariables.getMessageMap();
    }

    public static void setMessageMap(MessageMap messageMap) {
        GlobalVariables.setMessageMap(messageMap);
    }

    public static Person getUserSessionPerson() {
        UserSession session = getUserSession();
        return session != null ? session.getPerson() : null;
    }

    public static String getUserSessionPrincipalId() {
        UserSession session = getUserSession();
        return session != null ? session.getPrincipalId() : null;
    }
}
