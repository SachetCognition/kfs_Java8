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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.bc.businessobject.BudgetConstructionHeader;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionRequestMove;
import org.kuali.kfs.module.bc.document.dataaccess.ImportRequestDao;
import org.kuali.rice.krad.bo.BusinessObject;

public class ImportRequestDaoJpa implements ImportRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BudgetConstructionHeader getHeaderRecord(BudgetConstructionRequestMove record, Integer budgetYear) {
        TypedQuery<BudgetConstructionHeader> query = entityManager.createQuery(
            "SELECT h FROM BudgetConstructionHeader h WHERE h.chartOfAccountsCode = :chart " +
            "AND h.accountNumber = :account AND h.subAccountNumber = :subAccount " +
            "AND h.universityFiscalYear = :fiscalYear", BudgetConstructionHeader.class);
        query.setParameter("chart", record.getChartOfAccountsCode());
        query.setParameter("account", record.getAccountNumber());
        query.setParameter("subAccount", record.getSubAccountNumber());
        query.setParameter("fiscalYear", budgetYear);
        List<BudgetConstructionHeader> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<BudgetConstructionRequestMove> findAllNonErrorCodeRecords(String principalId) {
        TypedQuery<BudgetConstructionRequestMove> query = entityManager.createQuery(
            "SELECT r FROM BudgetConstructionRequestMove r WHERE r.requestUpdateErrorCode IS NULL " +
            "AND r.principalId = :principalId", BudgetConstructionRequestMove.class);
        query.setParameter("principalId", principalId);
        return query.getResultList();
    }

    @Override
    public void save(BusinessObject businessObject, boolean isUpdate) {
        if (isUpdate) {
            entityManager.merge(businessObject);
        } else {
            entityManager.persist(businessObject);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
