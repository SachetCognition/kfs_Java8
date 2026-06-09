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
package org.kuali.kfs.module.purap.document.dataaccess.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.purap.businessobject.NegativePaymentRequestApprovalLimit;
import org.kuali.kfs.module.purap.document.dataaccess.NegativePaymentRequestApprovalLimitDao;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class NegativePaymentRequestApprovalLimitDaoJpa implements NegativePaymentRequestApprovalLimitDao {

    private static Logger LOG = Logger.getLogger(NegativePaymentRequestApprovalLimitDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<NegativePaymentRequestApprovalLimit> findByChart(String chartCode) {
        LOG.debug("Entering findByChart(String)");
        return entityManager.createQuery(
            "SELECT n FROM NegativePaymentRequestApprovalLimit n WHERE n.chartOfAccountsCode = :chart AND n.organizationCode IS NULL AND n.accountNumber IS NULL AND n.active = true",
            NegativePaymentRequestApprovalLimit.class)
            .setParameter("chart", chartCode)
            .getResultList();
    }

    @Override
    public Collection<NegativePaymentRequestApprovalLimit> findByChartAndAccount(String chartCode, String accountNumber) {
        LOG.debug("Entering findByChartAndAccount(String, String)");
        return entityManager.createQuery(
            "SELECT n FROM NegativePaymentRequestApprovalLimit n WHERE n.chartOfAccountsCode = :chart AND n.accountNumber = :acct AND n.organizationCode IS NULL AND n.active = true",
            NegativePaymentRequestApprovalLimit.class)
            .setParameter("chart", chartCode)
            .setParameter("acct", accountNumber)
            .getResultList();
    }

    @Override
    public Collection<NegativePaymentRequestApprovalLimit> findByChartAndOrganization(String chartCode, String organizationCode) {
        LOG.debug("Entering findByChartAndOrganization(String, String)");
        return entityManager.createQuery(
            "SELECT n FROM NegativePaymentRequestApprovalLimit n WHERE n.chartOfAccountsCode = :chart AND n.organizationCode = :org AND n.accountNumber IS NULL AND n.active = true",
            NegativePaymentRequestApprovalLimit.class)
            .setParameter("chart", chartCode)
            .setParameter("org", organizationCode)
            .getResultList();
    }

    @Override
    public Collection<NegativePaymentRequestApprovalLimit> findAboveLimit(KualiDecimal limit) {
        LOG.debug("Entering findAboveLimit(KualiDecimal)");
        return entityManager.createQuery(
            "SELECT n FROM NegativePaymentRequestApprovalLimit n WHERE n.negativePaymentRequestApprovalLimitAmount >= :limit AND n.active = true",
            NegativePaymentRequestApprovalLimit.class)
            .setParameter("limit", limit)
            .getResultList();
    }

    @Override
    public Collection<NegativePaymentRequestApprovalLimit> findBelowLimit(KualiDecimal limit) {
        LOG.debug("Entering findBelowLimit(KualiDecimal)");
        return entityManager.createQuery(
            "SELECT n FROM NegativePaymentRequestApprovalLimit n WHERE n.negativePaymentRequestApprovalLimitAmount <= :limit AND n.active = true",
            NegativePaymentRequestApprovalLimit.class)
            .setParameter("limit", limit)
            .getResultList();
    }
}
