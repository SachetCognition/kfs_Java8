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

import org.kuali.kfs.coa.businessobject.IndirectCostRecoveryType;
import org.kuali.kfs.coa.dataaccess.IndirectCostRecoveryTypeDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * This class implements the {@link IndirectCostRecoveryExclusionTypeDao} data access methods using Ojb
 */
public class IndirectCostRecoveryTypeDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements IndirectCostRecoveryTypeDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(IndirectCostRecoveryTypeDaoJpa.class);

    /**
     * @see org.kuali.kfs.coa.dataaccess.IndirectCostRecoveryTypeDao#getByPrimaryKey(java.lang.String)
     */
    public IndirectCostRecoveryType getByPrimaryKey(String code) {
        LOG.debug("getByPrimaryKey() started");

        Criteria crit = new Criteria();
        crit.addEqualTo(KFSPropertyConstants.CODE, code);

        QueryByCriteria qbc = QueryFactory.newQuery(IndirectCostRecoveryType.class, crit);
        return (IndirectCostRecoveryType) entityManager.createQuery(qbc).getSingleResult();
    }
}
