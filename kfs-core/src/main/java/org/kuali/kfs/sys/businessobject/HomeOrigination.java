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

package org.kuali.kfs.sys.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.sys.KFSConstants;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 
 */
@Entity
@Table(name = "FS_HOME_ORIGIN_T")
public class HomeOrigination extends PersistableBusinessObjectBase {

    public static final String CACHE_NAME = KFSConstants.APPLICATION_NAMESPACE_CODE + "/" + "HomeOrigination";
    
    @Id
    @Column(name = "FS_HOME_ORIGIN_CD")
    private String finSystemHomeOriginationCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FS_HOME_ORIGIN_CD", insertable = false, updatable = false)
    private OriginationCode originationCode;


    /**
     * Default no-arg constructor.
     */
    public HomeOrigination() {
        super();
    }

    /**
     * Gets the finSystemHomeOriginationCode attribute.
     * 
     * @return Returns the finSystemHomeOriginationCode
     */
    public String getFinSystemHomeOriginationCode() {
        return finSystemHomeOriginationCode;
    }


    /**
     * Sets the finSystemHomeOriginationCode attribute.
     * 
     * @param finSystemHomeOriginationCode The finSystemHomeOriginationCode to set.
     */
    public void setFinSystemHomeOriginationCode(String finSystemHomeOriginationCode) {
        this.finSystemHomeOriginationCode = finSystemHomeOriginationCode;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("finSystemHomeOriginationCode", this.finSystemHomeOriginationCode);
        return m;
    }

    public OriginationCode getOriginationCode() {
        return originationCode;
    }

    public void setOriginationCode(OriginationCode originationCode) {
        this.originationCode = originationCode;
    }
}
