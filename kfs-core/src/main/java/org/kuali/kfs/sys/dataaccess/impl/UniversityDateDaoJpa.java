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
package org.kuali.kfs.sys.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.sql.Date;
import java.util.Collection;

import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.dataaccess.UniversityDateDao;

/**
 * The JPA/Hibernate implementation of the UniversityDateDao
 */
public class UniversityDateDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements UniversityDateDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(UniversityDateDaoJpa.class);

    /**
     * Converts a java.util.Date to a java.sql.Date
     * 
     * @param date a java.util.Date to convert
     * @return a java.sql.Date
     */
    protected java.sql.Date convertDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    /**
     * Returns the last university date for a given fiscal year
     * 
     * @param fiscalYear the fiscal year to find the last date for
     * @return a UniversityDate record for the last day in the given fiscal year, or null if nothing can be found
     * @see org.kuali.kfs.sys.dataaccess.UniversityDateDao#getLastFiscalYearDate(java.lang.Integer)
     */
    @Override
    public UniversityDate getLastFiscalYearDate(Integer fiscalYear) {
        ReportQueryByCriteria subQuery;
        Criteria subCrit = new Criteria();
        Criteria crit = new Criteria();

        subCrit.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, fiscalYear);
        subQuery = QueryFactory.newReportQuery(UniversityDate.class, subCrit);
        subQuery.setAttributes(new String[] { "max(univ_dt)" });

        crit.addGreaterOrEqualThan(KFSPropertyConstants.UNIVERSITY_DATE, subQuery);

        QueryByCriteria qbc = QueryFactory.newQuery(UniversityDate.class, crit);

        return (UniversityDate) entityManager.createQuery(qbc).getSingleResult();
    }

    /**
     * Returns the first university date for a given fiscal year
     * 
     * @param fiscalYear the fiscal year to find the first date for
     * @return a UniversityDate record for the first day of the given fiscal year, or null if nothing can be found
     * @see org.kuali.kfs.sys.dataaccess.UniversityDateDao#getFirstFiscalYearDate(java.lang.Integer)
     */
    @Override
    public UniversityDate getFirstFiscalYearDate(Integer fiscalYear) {
        ReportQueryByCriteria subQuery;
        Criteria subCrit = new Criteria();
        Criteria crit = new Criteria();

        subCrit.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, fiscalYear);
        subQuery = QueryFactory.newReportQuery(UniversityDate.class, subCrit);
        subQuery.setAttributes(new String[] { "min(univ_dt)" });

        crit.addGreaterOrEqualThan(KFSPropertyConstants.UNIVERSITY_DATE, subQuery);

        QueryByCriteria qbc = QueryFactory.newQuery(UniversityDate.class, crit);

        return (UniversityDate) entityManager.createQuery(qbc).getSingleResult();
    }

}
