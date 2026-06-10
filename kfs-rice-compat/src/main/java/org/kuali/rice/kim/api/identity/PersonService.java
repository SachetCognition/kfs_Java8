package org.kuali.rice.kim.api.identity;
public interface PersonService {
    Person getPerson(String principalId);
    Person getPersonByPrincipalName(String principalName);
    Person getPersonByEmployeeId(String employeeId);
    java.util.List<Person> findPeople(java.util.Map<String, String> criteria);
    Person updatePersonIfNecessary(String principalId, Person person);
    java.util.List<org.kuali.rice.kim.api.identity.Person> getPersonByExternalIdentifier(String externalIdentifierTypeCode, String externalId);
}
