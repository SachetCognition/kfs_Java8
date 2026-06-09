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
package org.kuali.kfs.coa.businessobject;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.KualiCodeBase;

/**
 * 
 */
@Entity
@Table(name = "CA_PROJECT_T")

public class ProjectCode extends KualiCodeBase implements MutableInactivatable {

    private static final long serialVersionUID = 4529316062843227897L;

    public static final String CACHE_NAME = KFSConstants.APPLICATION_NAMESPACE_CODE + "/" + "ProjectCode";
    
    @Column(name = "PROJECT_DESC")
    private String projectDescription;
    @Column(name = "PROJ_MGR_UNVL_ID")
    private String projectManagerUniversalId;
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;
    @Column(name = "ORG_CD")
    private String organizationCode;

    private Person projectManagerUniversal;
    @Transient
    private Chart chartOfAccounts;
    private Organization organization;

    /**
     * Default no-arg constructor.
     */
    public ProjectCode() {
    }

    /**
     * Gets the projectDescription attribute.
     * 
     * @return Returns the projectDescription
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the projectDescription attribute.
     * 
     * @param projectDescription The projectDescription to set.
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Person getProjectManagerUniversal() {
        projectManagerUniversal = SpringContext.getBean(org.kuali.rice.kim.api.identity.PersonService.class).updatePersonIfNecessary(projectManagerUniversalId, projectManagerUniversal);
        return projectManagerUniversal;
    }

    /**
     * Sets the projectManagerUniversal attribute.
     * 
     * @param projectManagerUniversal The projectManagerUniversal to set.
     */
    public void setProjectManagerUniversal(Person projectManagerUniversal) {
        this.projectManagerUniversal = projectManagerUniversal;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return Returns the chartOfAccounts
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * Sets the chartOfAccounts attribute.
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the organization attribute.
     * 
     * @return Returns the organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization attribute.
     * 
     * @param organization The organization to set.
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @return Returns the organizationCode.
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * @param organizationCode The organizationCode to set.
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * @return Returns the projectManagerUniversalId.
     */
    public String getProjectManagerUniversalId() {
        return projectManagerUniversalId;
    }

    /**
     * @param projectManagerUniversalId The projectManagerUniversalId to set.
     */
    public void setProjectManagerUniversalId(String projectManagerUniversalId) {
        this.projectManagerUniversalId = projectManagerUniversalId;
    }

}

