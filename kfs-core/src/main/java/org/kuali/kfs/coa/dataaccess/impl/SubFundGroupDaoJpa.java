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
package org.kuali.kfs.coa.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.SubFundGroup;
import org.kuali.kfs.coa.dataaccess.SubFundGroupDao;

/**
 * This class implements the {@link SubFundGroupDao} data access methods using Ojb
 */
public class SubFundGroupDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements SubFundGroupDao {
    @PersistenceContext
    private EntityManager entityManager;

    private org.kuali.rice.core.framework.persistence.platform.DatabasePlatform dbPlatform;

    @Override
    public org.kuali.rice.core.framework.persistence.platform.DatabasePlatform getDbPlatform() {
        return dbPlatform;
    }

    public void setDbPlatform(org.kuali.rice.core.framework.persistence.platform.DatabasePlatform dbPlatform) {
        this.dbPlatform = dbPlatform;
    }

    /**
     * @see org.kuali.kfs.coa.dataaccess.SubFundGroupDao#getByChartAndAccount(java.lang.String, java.lang.String)
     */
    public SubFundGroup getByChartAndAccount(String chartCode, String accountNumber) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartCode);
        criteria.addEqualTo("accountNumber", accountNumber);

        Account account = (Account) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(Account.class, criteria));
        criteria = new Criteria();
        criteria.addEqualTo("subFundGroupCode", account.getSubFundGroupCode());

        return (SubFundGroup) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(SubFundGroup.class, criteria));
    }

}
