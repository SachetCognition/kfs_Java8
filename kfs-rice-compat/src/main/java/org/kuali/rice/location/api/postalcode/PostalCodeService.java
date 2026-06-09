package org.kuali.rice.location.api.postalcode;

public interface PostalCodeService {
    org.kuali.rice.location.api.postalcode.PostalCode getPostalCode(java.lang.String p0, java.lang.String p1) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
    java.util.List<org.kuali.rice.location.api.postalcode.PostalCode> findAllPostalCodesInCountry(java.lang.String p0) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
    org.kuali.rice.location.api.postalcode.PostalCodeQueryResults findPostalCodes(org.kuali.rice.core.api.criteria.QueryByCriteria p0) throws org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
}
