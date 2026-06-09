package org.kuali.rice.location.api.county;

import java.util.List;

public interface CountyService {
    org.kuali.rice.location.api.county.County getCounty(String countryCode, String stateCode, String code);
    List<org.kuali.rice.location.api.county.County> findAllCountiesInCountryAndState(String countryCode, String stateCode);
}
