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
package org.kuali.kfs.integration.cam.businessobject;

import org.kuali.kfs.integration.cam.CapitalAssetManagementAsset;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name = "CM_CPTLAST_T")
public class Asset extends PersistableBusinessObjectBase implements CapitalAssetManagementAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CPTLAST_NBR")
    protected Long capitalAssetNumber;
    @Column(name = "CPTLAST_DESC")
    protected String capitalAssetDescription;
    @Column(name = "CPTLAST_TYP_CD")
    protected String capitalAssetTypeCode;
    @Column(name = "CPTLAST_VENDOR_NM")
    protected String vendorName;
    @Column(name = "CAMPUS_CD")
    protected String campusCode;
    @Column(name = "BLDG_CD")
    protected String buildingCode;
    @Column(name = "BLDG_ROOM_NBR")
    protected String buildingRoomNumber;
    @Column(name = "BLDG_SUB_ROOM_NBR")
    protected String buildingSubRoomNumber;
    @Column(name = "CPTLAST_TAG_NBR")
    protected String campusTagNumber;
    @Column(name = "CPTLAST_MFR_NM")
    protected String manufacturerName;
    @Column(name = "CPTLAST_MFRMDL_NBR")
    protected String manufacturerModelNumber;
    @Column(name = "CPTLAST_SERIAL_NBR")
    protected String serialNumber;
    @Transient
    protected Integer quantity;

    /**
     * Default constructor.
     */
    public Asset() {
    }

    /**
     * Gets the capitalAssetNumber attribute.
     *
     * @return Returns the capitalAssetNumber
     */
    @Override
    public Long getCapitalAssetNumber() {
        return capitalAssetNumber;
    }

    /**
     * Sets the capitalAssetNumber attribute.
     *
     * @param capitalAssetNumber The capitalAssetNumber to set.
     */
    public void setCapitalAssetNumber(Long capitalAssetNumber) {
        this.capitalAssetNumber = capitalAssetNumber;
    }

    /**
     * Gets the capitalAssetDescription attribute
     *
     * @return Returns the capitalAssetDescription
     */
    @Override
    public String getCapitalAssetDescription() {
        return capitalAssetDescription;
    }

    /**
     * Sets the capitalAssetDescription attribute.
     *
     * @param capitalAssetDescription The capitalAssetDescription to set.
     */
    public void setCapitalAssetDescription(String capitalAssetDescription) {
        this.capitalAssetDescription = capitalAssetDescription;
    }


    /**
     * Gets the capitalAssetTypeCode attribute.
     *
     * @return Returns the capitalAssetTypeCode
     */
    @Override
    public String getCapitalAssetTypeCode() {
        return capitalAssetTypeCode;
    }

    /**
     * Sets the capitalAssetTypeCode attribute.
     *
     * @param capitalAssetTypeCode The capitalAssetTypeCode to set.
     */
    public void setCapitalAssetTypeCode(String capitalAssetTypeCode) {
        this.capitalAssetTypeCode = capitalAssetTypeCode;
    }

    /**
     * Gets the vendorName attribute.
     *
     * @return Returns the vendorName
     */
    @Override
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Sets the vendorName attribute.
     *
     * @param vendorName The vendorName to set.
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    /**
     * Gets the campusCode attribute.
     *
     * @return Returns the campusCode
     */
    @Override
    public String getCampusCode() {
        return campusCode;
    }

    /**
     * Sets the campusCode attribute.
     *
     * @param campusCode The campusCode to set.
     */
    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }


    /**
     * Gets the buildingCode attribute.
     *
     * @return Returns the buildingCode
     */
    @Override
    public String getBuildingCode() {
        return buildingCode;
    }

    /**
     * Sets the buildingCode attribute.
     *
     * @param buildingCode The buildingCode to set.
     */
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }


    /**
     * Gets the buildingRoomNumber attribute.
     *
     * @return Returns the buildingRoomNumber
     */
    @Override
    public String getBuildingRoomNumber() {
        return buildingRoomNumber;
    }

    /**
     * Sets the buildingRoomNumber attribute.
     *
     * @param buildingRoomNumber The buildingRoomNumber to set.
     */
    public void setBuildingRoomNumber(String buildingRoomNumber) {
        this.buildingRoomNumber = buildingRoomNumber;
    }


    /**
     * Gets the buildingSubRoomNumber attribute.
     *
     * @return Returns the buildingSubRoomNumber
     */
    @Override
    public String getBuildingSubRoomNumber() {
        return buildingSubRoomNumber;
    }

    /**
     * Sets the buildingSubRoomNumber attribute.
     *
     * @param buildingSubRoomNumber The buildingSubRoomNumber to set.
     */
    public void setBuildingSubRoomNumber(String buildingSubRoomNumber) {
        this.buildingSubRoomNumber = buildingSubRoomNumber;
    }

    /**
     * Gets the campusTagNumber attribute.
     *
     * @return Returns the campusTagNumber
     */
    @Override
    public String getCampusTagNumber() {
        return campusTagNumber;
    }

    /**
     * Sets the campusTagNumber attribute.
     *
     * @param campusTagNumber The campusTagNumber to set.
     */
    public void setCampusTagNumber(String campusTagNumber) {
        this.campusTagNumber = campusTagNumber;
    }
    /**
     * Gets the manufacturerName attribute.
     *
     * @return Returns the manufacturerName
     */
    @Override
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * Sets the manufacturerName attribute.
     *
     * @param manufacturerName The manufacturerName to set.
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }


    /**
     * Gets the manufacturerModelNumber attribute.
     *
     * @return Returns the manufacturerModelNumber
     */
    @Override
    public String getManufacturerModelNumber() {
        return manufacturerModelNumber;
    }

    /**
     * Sets the manufacturerModelNumber attribute.
     *
     * @param manufacturerModelNumber The manufacturerModelNumber to set.
     */
    public void setManufacturerModelNumber(String manufacturerModelNumber) {
        this.manufacturerModelNumber = manufacturerModelNumber;
    }


    /**
     * Gets the serialNumber attribute.
     *
     * @return Returns the serialNumber
     */
    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serialNumber attribute.
     *
     * @param serialNumber The serialNumber to set.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public void refresh() {}

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
