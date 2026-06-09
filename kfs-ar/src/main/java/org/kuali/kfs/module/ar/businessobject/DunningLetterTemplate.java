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
package org.kuali.kfs.module.ar.businessobject;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.type.YesNoConverter;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

/**
 * Defines the letter templates that will be assigned to the appropriate dunning letter campaigns.
 *
 */
@Entity
@Table(name = "AR_DUN_LTR_TMPLT_T")
public class DunningLetterTemplate extends TemplateBase implements MutableInactivatable {

    @Id
    @Column(name = "DUN_LTR_TMPLT_CD")
    private String dunningLetterTemplateCode;
    @Column(name = "DUN_LTR_TMPLT_DESC")
    private String dunningLetterTemplateDescription;

    /**
     * Gets the dunningLetterTemplateCode attribute.
     *
     * @return Returns dunningLetterTemplateCode.
     */
    public String getDunningLetterTemplateCode() {
        return dunningLetterTemplateCode;
    }

    /**
     * Sets the dunningLetterTemplateCode attribute.
     *
     * @param dunningLetterTemplateCode The dunningLetterTemplateCode attribute to set.
     */
    public void setDunningLetterTemplateCode(String dunningLetterTemplateCode) {
        this.dunningLetterTemplateCode = dunningLetterTemplateCode;
    }

    /**
     * Gets the dunningLetterTemplateDescription attribute.
     *
     * @return Returns the dunningLetterTemplateDescription attribute.
     */
    public String getDunningLetterTemplateDescription() {
        return dunningLetterTemplateDescription;
    }

    /**
     * Sets the dunningLetterTemplateDescription attribute.
     *
     * @param dunningLetterTemplateDescription The dunningLetterTemplateDescription attribute to set.
     */
    public void setDunningLetterTemplateDescription(String dunningLetterTemplateDescription) {
        this.dunningLetterTemplateDescription = dunningLetterTemplateDescription;
    }

}
