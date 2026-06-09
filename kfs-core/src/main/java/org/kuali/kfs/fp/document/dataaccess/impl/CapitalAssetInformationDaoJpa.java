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
package org.kuali.kfs.fp.document.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.log4j.Logger;

import org.kuali.kfs.fp.businessobject.CapitalAssetInformation;
import org.kuali.kfs.fp.document.dataaccess.CapitalAssetInformationDao;
import org.kuali.kfs.sys.util.TransactionalServiceUtils;

import org.springmodules.orm.ojb.PersistenceBrokerTemplate;

public class CapitalAssetInformationDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CapitalAssetInformationDao {
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

    public void setJcdAlias(String jcdAlias) {
        // no-op: JPA does not use OJB jcdAlias
    }


    protected static final Logger LOG = Logger.getLogger(CapitalAssetInformation.class);

    @Override
    public int getNextCapitalAssetLineNumber(String financialDocumentNumber) {
        final PersistenceBrokerTemplate temp = getPersistenceBrokerTemplate();
        final String queryString = "";
        final Criteria cri = new Criteria();
        final ReportQueryByCriteria query = new ReportQueryByCriteria(CapitalAssetInformation.class, new String[]{"max(capitalAssetLineNumber)"}, cri );
        cri.addEqualTo("documentNumber", financialDocumentNumber);
        query.addGroupBy("documentNumber");
        final Iterator<Object> iterator = temp.getReportQueryIteratorByQuery(query);

        BigDecimal value = new BigDecimal(0);
        if (iterator.hasNext()) {
            Object[] data = (Object[]) TransactionalServiceUtils.retrieveFirstAndExhaustIterator(iterator);
            if (data[0] != null) {
                value = (BigDecimal)data[0];
            }
        }
        //do the add in BigDecimal so that we can accurately check for lost information *after* the add
        value = value.add(new BigDecimal(1));
        int ret = value.intValueExact();
        return ret;
    }
}
