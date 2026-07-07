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
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.KualiCodeBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;

@Entity
@Table(name = "CA_ACCT_SF_T")
@AttributeOverrides({
    @AttributeOverride(name = "code", column = @Column(name = "ACCT_SF_CD")),
    @AttributeOverride(name = "name", column = @Column(name = "ACCT_SF_NM")),
    @AttributeOverride(name = "active", column = @Column(name = "ROW_ACTV_IND"))
})

public class SufficientFundsCode extends KualiCodeBase implements MutableInactivatable {


}
