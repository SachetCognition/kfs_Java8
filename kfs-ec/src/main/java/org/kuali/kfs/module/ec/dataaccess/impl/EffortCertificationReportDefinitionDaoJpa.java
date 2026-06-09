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
package org.kuali.kfs.module.ec.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.integration.ec.EffortCertificationReport;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportPosition;
import org.kuali.kfs.module.ec.dataaccess.EffortCertificationReportDefinitionDao;

/**
 * JPA implementation of {@link EffortCertificationReportDefinitionDao}.
 */
public class EffortCertificationReportDefinitionDaoJpa implements EffortCertificationReportDefinitionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EffortCertificationReportDefinition> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EffortCertificationReportDefinition> cq = cb.createQuery(EffortCertificationReportDefinition.class);
        cq.from(EffortCertificationReportDefinition.class);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<EffortCertificationReportDefinition> getAllOtherActiveByType(EffortCertificationReportDefinition effortCertificationReportDefinition) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EffortCertificationReportDefinition> cq = cb.createQuery(EffortCertificationReportDefinition.class);
        Root<EffortCertificationReportDefinition> root = cq.from(EffortCertificationReportDefinition.class);

        Predicate sameType = cb.equal(root.get("effortCertificationReportTypeCode"),
                effortCertificationReportDefinition.getEffortCertificationReportTypeCode());
        Predicate isActive = cb.equal(root.get("active"), true);

        Predicate differentYear = cb.notEqual(root.get("universityFiscalYear"),
                effortCertificationReportDefinition.getUniversityFiscalYear());
        Predicate differentReportNumber = cb.notEqual(root.get("effortCertificationReportNumber"),
                effortCertificationReportDefinition.getEffortCertificationReportNumber());

        Predicate notSameRecord = cb.or(differentYear, differentReportNumber);

        cq.where(cb.and(sameType, isActive, notSameRecord));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<EffortCertificationReport> getAllByYearAndPositionCode(Integer fiscalYear, String positionObjectCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EffortCertificationReportDefinition> cq = cb.createQuery(EffortCertificationReportDefinition.class);
        Root<EffortCertificationReportDefinition> root = cq.from(EffortCertificationReportDefinition.class);

        Join<EffortCertificationReportDefinition, EffortCertificationReportPosition> positionsJoin =
                root.join("effortCertificationReportPositions");

        Predicate beginYear = cb.equal(root.get("effortCertificationReportBeginFiscalYear"), fiscalYear);
        Predicate endYear = cb.equal(root.get("effortCertificationReportEndFiscalYear"), fiscalYear);
        Predicate yearMatch = cb.or(beginYear, endYear);

        Predicate positionMatch = cb.equal(
                positionsJoin.get("effortCertificationReportPositionObjectGroupCode"), positionObjectCode);

        cq.where(cb.and(yearMatch, positionMatch));
        cq.distinct(true);

        List<EffortCertificationReportDefinition> results = entityManager.createQuery(cq).getResultList();

        return new ArrayList<EffortCertificationReport>(results);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
