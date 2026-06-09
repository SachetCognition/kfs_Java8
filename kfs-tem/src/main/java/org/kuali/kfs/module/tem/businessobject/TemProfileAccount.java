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
package org.kuali.kfs.module.tem.businessobject;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "TEM_PROFILE_ACCOUNT_T")
public class TemProfileAccount extends PersistableBusinessObjectBase implements MutableInactivatable {

    @Column(name = "PROFILE_ID", nullable = false)
    private Integer profileId;
    private TemProfile profile;
    @Column(name = "CREDIT_CARD_AGENCY_CODE")
    private String creditCardOrAgencyCode;
    @Id
    @GeneratedValue(generator = "TEM_PROFILE_ACCOUNT_ID_SEQ")
    @SequenceGenerator(name = "TEM_PROFILE_ACCOUNT_ID_SEQ", sequenceName = "TEM_PROFILE_ACCOUNT_ID_SEQ")
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "ACCOUNT_NBR", nullable = false)
    private String accountNumber;
    @Column(name = "EXP_DATE")
    private Date expirationDate;
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "ACTV_IND", nullable = false, length = 1)
    private Boolean active = Boolean.TRUE;

    private CreditCardAgency creditCardAgency;
    private String creditCardOrAgencyName;

    /**
     * Gets the accountId attribute.
     *
     * @return Returns the accountId.
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * Sets the accountId attribute value.
     *
     * @param accountId The accountId to set.
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the name attribute.
     *
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name attribute value.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the accountNumber attribute.
     *
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute value.
     *
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the effectiveDate attribute.
     *
     * @return Returns the expirationDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the expirationDate attribute value.
     *
     * @param expirationDate The expirationDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Gets the expirationDate attribute.
     *
     * @return Returns the expirationDate.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expirationDate attribute value.
     *
     * @param expirationDate The expirationDate to set.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the note attribute.
     *
     * @return Returns the note.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note attribute value.
     *
     * @param note The note to set.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the active attribute.
     *
     * @return Returns the active.
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     *
     * @param active The active to set.
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the profileId attribute.
     *
     * @return Returns the profileId.
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     * Sets the profileId attribute value.
     *
     * @param profileId The profileId to set.
     */
    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets the profile attribute.
     *
     * @return Returns the profile.
     */
    public TemProfile getProfile() {
        return profile;
    }

    /**
     * Sets the profile attribute value.
     *
     * @param profile The profile to set.
     */
    public void setProfile(TemProfile profile) {
        this.profile = profile;
    }

    /**
     * Gets the creditCardOrAgencyCode attribute.
     *
     * @return Returns the creditCardAgencyCode.
     */
    public String getCreditCardOrAgencyCode() {
        return creditCardOrAgencyCode;
    }

    /**
     * Sets the creditCardOrAgencyCode attribute value.
     *
     * @param creditCardOrAgencyCode The creditCardOrAgencyCode to set.
     */
    public void setCreditCardOrAgencyCode(String creditCardOrAgencyCode) {
        this.creditCardOrAgencyCode = creditCardOrAgencyCode;
    }

    /**
     * Gets the creditCardAgency attribute.
     *
     * @return Returns the creditCardAgency.
     */
    public CreditCardAgency getCreditCardAgency() {
        return creditCardAgency;
    }

    public void setCreditCardAgency(CreditCardAgency creditCardAgency) {
        this.creditCardAgency = creditCardAgency;
    }

    public String getCreditCardOrAgencyName() {
        return creditCardOrAgencyName;
    }

    public void setCreditCardOrAgencyName(String creditCardOrAgencyName) {
        this.creditCardOrAgencyName = creditCardOrAgencyName;
    }

    @SuppressWarnings("rawtypes")
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("accountId", accountId);
        map.put("name", name);
        map.put("accountNumber", accountNumber);

        return map;
    }


}
