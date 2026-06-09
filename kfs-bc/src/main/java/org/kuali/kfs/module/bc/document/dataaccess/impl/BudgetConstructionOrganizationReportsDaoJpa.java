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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.bc.businessobject.BudgetConstructionOrganizationReports;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionOrganizationReportsDao;

public class BudgetConstructionOrganizationReportsDaoJpa implements BudgetConstructionOrganizationReportsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Collection getBySearchCriteria(Class cls, Map searchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(cls);
        Root root = cq.from(cls);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Object entry : searchCriteria.entrySet()) {
            Map.Entry<String, Object> e = (Map.Entry<String, Object>) entry;
            predicates.add(cb.equal(root.get(e.getKey()), e.getValue()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection getBySearchCriteriaWithOrderByList(Class cls, Map searchCriteria, List<String> orderList) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(cls);
        Root root = cq.from(cls);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Object entry : searchCriteria.entrySet()) {
            Map.Entry<String, Object> e = (Map.Entry<String, Object>) entry;
            predicates.add(cb.equal(root.get(e.getKey()), e.getValue()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        if (orderList != null && !orderList.isEmpty()) {
            List<javax.persistence.criteria.Order> orders = new ArrayList<javax.persistence.criteria.Order>();
            for (String field : orderList) {
                orders.add(cb.asc(root.get(field)));
            }
            cq.orderBy(orders);
        }
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List getActiveChildOrgs(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<BudgetConstructionOrganizationReports> query = entityManager.createQuery(
            "SELECT r FROM BudgetConstructionOrganizationReports r " +
            "WHERE r.reportsToChartOfAccountsCode = :chart AND r.reportsToOrganizationCode = :org " +
            "AND NOT (r.chartOfAccountsCode = r.reportsToChartOfAccountsCode AND r.organizationCode = r.reportsToOrganizationCode)",
            BudgetConstructionOrganizationReports.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public boolean isLeafOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(r) FROM BudgetConstructionOrganizationReports r " +
            "WHERE r.reportsToChartOfAccountsCode = :chart AND r.reportsToOrganizationCode = :org " +
            "AND NOT (r.chartOfAccountsCode = :chart AND r.organizationCode = :org)", Long.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getSingleResult() == 0;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
