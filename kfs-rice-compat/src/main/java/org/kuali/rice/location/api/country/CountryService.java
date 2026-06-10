package org.kuali.rice.location.api.country;
import java.util.List;
public interface CountryService {
    Country getCountry(String code);
    Country getCountryByAlternateCode(String alternateCode);
    List<Country> findAllCountries();
    List<Country> findAllCountriesNotRestricted();
    Country getDefaultCountry();
}
