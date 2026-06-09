package org.kuali.rice.location.api.state;

import java.util.List;

public interface StateService {
    State getState(String countryCode, String code) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
    List<State> findAllStatesInCountry(String countryCode) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
    List<State> findAllStatesInCountryByAltCode(String countryCode) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException, org.kuali.rice.core.api.exception.RiceIllegalStateException;
    StateQueryResults findStates(org.kuali.rice.core.api.criteria.QueryByCriteria query) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
}
