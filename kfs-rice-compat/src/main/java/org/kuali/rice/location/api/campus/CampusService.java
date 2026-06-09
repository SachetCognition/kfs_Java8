package org.kuali.rice.location.api.campus;

import java.util.List;

public interface CampusService {
    org.kuali.rice.location.api.campus.Campus getCampus(String code);
    List<org.kuali.rice.location.api.campus.Campus> findAllCampuses();
    org.kuali.rice.location.api.campus.CampusType getCampusType(String code);
    List<org.kuali.rice.location.api.campus.CampusType> findAllCampusTypes();
}
