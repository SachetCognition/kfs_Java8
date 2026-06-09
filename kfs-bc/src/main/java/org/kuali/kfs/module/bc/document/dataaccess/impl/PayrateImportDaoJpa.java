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
package org.kuali.kfs.module.bc.document.dataaccess.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPayRateHolding;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.document.dataaccess.PayrateImportDao;

public class PayrateImportDaoJpa implements PayrateImportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PendingBudgetConstructionAppointmentFunding> getFundingRecords(BudgetConstructionPayRateHolding holdingRecord, Integer budgetYear, Collection<String> objectCodeValues) {
        TypedQuery<PendingBudgetConstructionAppointmentFunding> query = entityManager.createQuery(
            "SELECT f FROM PendingBudgetConstructionAppointmentFunding f " +
            "WHERE f.universityFiscalYear = :budgetYear AND f.emplid = :emplid " +
            "AND f.positionNumber = :positionNumber AND f.financialObjectCode IN :objectCodes " +
            "AND f.appointmentFundingDeleteIndicator = false",
            PendingBudgetConstructionAppointmentFunding.class);
        query.setParameter("budgetYear", budgetYear);
        query.setParameter("emplid", holdingRecord.getEmplid());
        query.setParameter("positionNumber", holdingRecord.getPositionNumber());
        query.setParameter("objectCodes", objectCodeValues);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
