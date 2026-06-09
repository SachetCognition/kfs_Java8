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
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.purap.businessobject.ReceivingAddress;
import org.kuali.kfs.module.purap.document.dataaccess.ReceivingAddressDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ReceivingAddressDaoJpa implements ReceivingAddressDao {

    private static Logger LOG = Logger.getLogger(ReceivingAddressDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<ReceivingAddress> findActiveByChartOrg(String chartCode, String orgCode) {
        LOG.debug("Entering findActiveByChartOrg(String,String)");
        String jpql;
        TypedQuery<ReceivingAddress> query;
        if (orgCode == null) {
            jpql = "SELECT r FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode IS NULL AND r.active = true";
            query = entityManager.createQuery(jpql, ReceivingAddress.class);
        } else {
            jpql = "SELECT r FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode = :org AND r.active = true";
            query = entityManager.createQuery(jpql, ReceivingAddress.class);
            query.setParameter("org", orgCode);
        }
        query.setParameter("chart", chartCode);
        return query.getResultList();
    }

    @Override
    public Collection<ReceivingAddress> findDefaultByChartOrg(String chartCode, String orgCode) {
        LOG.debug("Entering findDefaultByChartOrg(String,String)");
        String jpql;
        TypedQuery<ReceivingAddress> query;
        if (orgCode == null) {
            jpql = "SELECT r FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode IS NULL AND r.defaultIndicator = true AND r.active = true";
            query = entityManager.createQuery(jpql, ReceivingAddress.class);
        } else {
            jpql = "SELECT r FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode = :org AND r.defaultIndicator = true AND r.active = true";
            query = entityManager.createQuery(jpql, ReceivingAddress.class);
            query.setParameter("org", orgCode);
        }
        query.setParameter("chart", chartCode);
        return query.getResultList();
    }

    @Override
    public int countActiveByChartOrg(String chartCode, String orgCode) {
        LOG.debug("Entering countActiveByChartOrg(String,String)");
        String jpql;
        TypedQuery<Long> query;
        if (orgCode == null) {
            jpql = "SELECT COUNT(r) FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode IS NULL AND r.active = true";
            query = entityManager.createQuery(jpql, Long.class);
        } else {
            jpql = "SELECT COUNT(r) FROM ReceivingAddress r WHERE r.chartOfAccountsCode = :chart AND r.organizationCode = :org AND r.active = true";
            query = entityManager.createQuery(jpql, Long.class);
            query.setParameter("org", orgCode);
        }
        query.setParameter("chart", chartCode);
        return query.getSingleResult().intValue();
    }
}
