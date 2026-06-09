package org.kuali.rice.kim.api.identity;

public interface AuthenticationService {
    String getPrincipalName(javax.servlet.http.HttpServletRequest request);
}
