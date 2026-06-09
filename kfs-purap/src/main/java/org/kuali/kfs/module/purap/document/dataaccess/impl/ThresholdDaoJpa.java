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
import org.kuali.kfs.module.purap.businessobject.ReceivingThreshold;
import org.kuali.kfs.module.purap.document.dataaccess.ThresholdDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ThresholdDaoJpa implements ThresholdDao {

    private static Logger LOG = Logger.getLogger(ThresholdDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<ReceivingThreshold> findByChart(String chartCode) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndFund(String chartCode, String fund) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.accountTypeCode = :fund AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("fund", fund)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndSubFund(String chartCode, String subFund) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.subFundGroupCode = :subFund AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("subFund", subFund)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndCommodity(String chartCode, String commodityCode) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.purchasingCommodityCode = :commodity AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("commodity", commodityCode)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndObjectCode(String chartCode, String objectCode) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.financialObjectCode = :objCode AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("objCode", objectCode)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndOrg(String chartCode, String org) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.organizationCode = :org AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("org", org)
            .getResultList();
    }

    @Override
    public Collection<ReceivingThreshold> findByChartAndVendor(String chartCode, String vendorHeaderGeneratedIdentifier, String vendorDetailAssignedIdentifier) {
        return entityManager.createQuery(
            "SELECT t FROM ReceivingThreshold t WHERE t.chartOfAccountsCode = :chart AND t.vendorHeaderGeneratedIdentifier = :vh AND t.vendorDetailAssignedIdentifier = :vd AND t.active = true",
            ReceivingThreshold.class)
            .setParameter("chart", chartCode)
            .setParameter("vh", Integer.valueOf(vendorHeaderGeneratedIdentifier))
            .setParameter("vd", Integer.valueOf(vendorDetailAssignedIdentifier))
            .getResultList();
    }
}
