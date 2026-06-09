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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionFundingLock;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionHeader;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDao;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionLockDao;

public class BudgetConstructionLockDaoJpa implements BudgetConstructionLockDao {

    @PersistenceContext
    private EntityManager entityManager;

    private BudgetConstructionDao budgetConstructionDao;

    @Override
    public List<BudgetConstructionHeader> getAllAccountLocks(String lockUnivId) {
        String jpql;
        if (StringUtils.isNotBlank(lockUnivId)) {
            jpql = "SELECT h FROM BudgetConstructionHeader h WHERE h.budgetLockUserIdentifier = :lockUser ORDER BY h.universityFiscalYear, h.chartOfAccountsCode, h.accountNumber, h.subAccountNumber";
        } else {
            jpql = "SELECT h FROM BudgetConstructionHeader h WHERE h.budgetLockUserIdentifier IS NOT NULL ORDER BY h.universityFiscalYear, h.chartOfAccountsCode, h.accountNumber, h.subAccountNumber";
        }
        TypedQuery<BudgetConstructionHeader> query = entityManager.createQuery(jpql, BudgetConstructionHeader.class);
        if (StringUtils.isNotBlank(lockUnivId)) {
            query.setParameter("lockUser", lockUnivId);
        }
        return query.getResultList();
    }

    @Override
    public List<BudgetConstructionHeader> getAllTransactionLocks(String lockUnivId) {
        String jpql;
        if (StringUtils.isNotBlank(lockUnivId)) {
            jpql = "SELECT h FROM BudgetConstructionHeader h WHERE h.budgetTransactionLockUserIdentifier = :lockUser ORDER BY h.universityFiscalYear, h.chartOfAccountsCode, h.accountNumber, h.subAccountNumber";
        } else {
            jpql = "SELECT h FROM BudgetConstructionHeader h WHERE h.budgetTransactionLockUserIdentifier IS NOT NULL ORDER BY h.universityFiscalYear, h.chartOfAccountsCode, h.accountNumber, h.subAccountNumber";
        }
        TypedQuery<BudgetConstructionHeader> query = entityManager.createQuery(jpql, BudgetConstructionHeader.class);
        if (StringUtils.isNotBlank(lockUnivId)) {
            query.setParameter("lockUser", lockUnivId);
        }
        return query.getResultList();
    }

    @Override
    public List<BudgetConstructionFundingLock> getOrphanedFundingLocks(String lockUnivId) {
        String jpql = "SELECT f FROM BudgetConstructionFundingLock f " +
            "WHERE NOT EXISTS (SELECT 1 FROM BudgetConstructionPosition p " +
            "WHERE p.universityFiscalYear = f.universityFiscalYear " +
            "AND p.positionLockUserIdentifier = f.appointmentFundingLockUserId)";
        if (StringUtils.isNotBlank(lockUnivId)) {
            jpql += " AND f.appointmentFundingLockUserId = :lockUser";
        }
        TypedQuery<BudgetConstructionFundingLock> query = entityManager.createQuery(jpql, BudgetConstructionFundingLock.class);
        if (StringUtils.isNotBlank(lockUnivId)) {
            query.setParameter("lockUser", lockUnivId);
        }
        return query.getResultList();
    }

    @Override
    public List<PendingBudgetConstructionAppointmentFunding> getAllPositionFundingLocks(String lockUnivId) {
        String jpql = "SELECT DISTINCT f FROM PendingBudgetConstructionAppointmentFunding f, BudgetConstructionPosition p " +
            "WHERE p.universityFiscalYear = f.universityFiscalYear AND p.positionNumber = f.positionNumber " +
            "AND p.positionLockUserIdentifier IS NOT NULL";
        if (StringUtils.isNotBlank(lockUnivId)) {
            jpql += " AND p.positionLockUserIdentifier = :lockUser";
        }
        jpql += " ORDER BY f.universityFiscalYear, f.chartOfAccountsCode, f.accountNumber, f.subAccountNumber";
        TypedQuery<PendingBudgetConstructionAppointmentFunding> query = entityManager.createQuery(jpql, PendingBudgetConstructionAppointmentFunding.class);
        if (StringUtils.isNotBlank(lockUnivId)) {
            query.setParameter("lockUser", lockUnivId);
        }
        return query.getResultList();
    }

    @Override
    public List<BudgetConstructionPosition> getOrphanedPositionLocks(String lockUnivId) {
        String jpql = "SELECT p FROM BudgetConstructionPosition p " +
            "WHERE p.positionLockUserIdentifier IS NOT NULL " +
            "AND NOT EXISTS (SELECT 1 FROM BudgetConstructionFundingLock f " +
            "WHERE f.universityFiscalYear = p.universityFiscalYear " +
            "AND f.appointmentFundingLockUserId = p.positionLockUserIdentifier)";
        if (StringUtils.isNotBlank(lockUnivId)) {
            jpql += " AND p.positionLockUserIdentifier = :lockUser";
        }
        TypedQuery<BudgetConstructionPosition> query = entityManager.createQuery(jpql, BudgetConstructionPosition.class);
        if (StringUtils.isNotBlank(lockUnivId)) {
            query.setParameter("lockUser", lockUnivId);
        }
        return query.getResultList();
    }

    public void setBudgetConstructionDao(BudgetConstructionDao budgetConstructionDao) {
        this.budgetConstructionDao = budgetConstructionDao;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
