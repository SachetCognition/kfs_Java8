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
package org.kuali.kfs.module.ar.dataaccess.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.gl.businessobject.Balance;
import org.kuali.kfs.module.ar.ArPropertyConstants;
import org.kuali.kfs.module.ar.businessobject.CostCategory;
import org.kuali.kfs.module.ar.businessobject.CostCategoryDetail;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectCode;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectConsolidation;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectLevel;
import org.kuali.kfs.module.ar.dataaccess.CostCategoryDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

public class CostCategoryDaoJpa implements CostCategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryConsolidationAmongConsolidations(CostCategoryObjectConsolidation objectConsolidation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectConsolidation> cq = cb.createQuery(CostCategoryObjectConsolidation.class);
        Root<CostCategoryObjectConsolidation> root = cq.from(CostCategoryObjectConsolidation.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(objectConsolidation.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), objectConsolidation.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), objectConsolidation.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.FIN_CONSOLIDATION_OBJECT_CODE), objectConsolidation.getFinConsolidationObjectCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectConsolidation> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectConsolidation> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryConsolidationAmongLevels(CostCategoryObjectConsolidation consolidation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectLevel> cq = cb.createQuery(CostCategoryObjectLevel.class);
        Root<CostCategoryObjectLevel> root = cq.from(CostCategoryObjectLevel.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(consolidation.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), consolidation.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), consolidation.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectLevel> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectLevel> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryConsolidationAmongCodes(CostCategoryObjectConsolidation consolidation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectCode> cq = cb.createQuery(CostCategoryObjectCode.class);
        Root<CostCategoryObjectCode> root = cq.from(CostCategoryObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(consolidation.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), consolidation.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), consolidation.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectCode> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectCode> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryLevelAmongLevels(CostCategoryObjectLevel objectLevel) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectLevel> cq = cb.createQuery(CostCategoryObjectLevel.class);
        Root<CostCategoryObjectLevel> root = cq.from(CostCategoryObjectLevel.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(objectLevel.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), objectLevel.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), objectLevel.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE), objectLevel.getFinancialObjectLevelCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectLevel> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectLevel> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryLevelAmongConsolidations(CostCategoryObjectLevel level) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectConsolidation> cq = cb.createQuery(CostCategoryObjectConsolidation.class);
        Root<CostCategoryObjectConsolidation> root = cq.from(CostCategoryObjectConsolidation.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(level.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), level.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), level.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectConsolidation> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectConsolidation> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryLevelAmongCodes(CostCategoryObjectLevel level) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectCode> cq = cb.createQuery(CostCategoryObjectCode.class);
        Root<CostCategoryObjectCode> root = cq.from(CostCategoryObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(level.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), level.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), level.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectCode> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectCode> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryObjectCodeAmongCodes(CostCategoryObjectCode objectCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectCode> cq = cb.createQuery(CostCategoryObjectCode.class);
        Root<CostCategoryObjectCode> root = cq.from(CostCategoryObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(objectCode.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), objectCode.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), objectCode.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_CODE), objectCode.getFinancialObjectCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectCode> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectCode> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveMatchingCostCategoryObjectCodeAmongLevels(CostCategoryObjectCode objectCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectLevel> cq = cb.createQuery(CostCategoryObjectLevel.class);
        Root<CostCategoryObjectLevel> root = cq.from(CostCategoryObjectLevel.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(objectCode.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), objectCode.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), objectCode.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectLevel> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectLevel> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CostCategoryDetail retrieveCostCategoryObjectCodeAmongConsolidations(CostCategoryObjectCode objectCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectConsolidation> cq = cb.createQuery(CostCategoryObjectConsolidation.class);
        Root<CostCategoryObjectConsolidation> root = cq.from(CostCategoryObjectConsolidation.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!StringUtils.isBlank(objectCode.getCategoryCode())) {
            predicates.add(cb.notEqual(root.get(ArPropertyConstants.CATEGORY_CODE), objectCode.getCategoryCode()));
        }
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), objectCode.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectConsolidation> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectConsolidation> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<Balance> getBalancesForCostCategory(Integer fiscalYear, String chartOfAccountsCode, String accountNumber, String balanceType, Collection<String> objectType, CostCategory costCategory) {
        List<Balance> results = new ArrayList<Balance>();

        if (costCategory == null) {
            return results;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Balance> cq = cb.createQuery(Balance.class);
        Root<Balance> root = cq.from(Balance.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR), fiscalYear));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), chartOfAccountsCode));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACCOUNT_NUMBER), accountNumber));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.BALANCE_TYPE_CODE), balanceType));
        if (objectType != null && !objectType.isEmpty()) {
            predicates.add(root.get(KFSPropertyConstants.OBJECT_TYPE_CODE).in(objectType));
        }

        // Get object codes from cost category
        List<String> objectCodes = new ArrayList<String>();
        if (costCategory.getObjectCodes() != null) {
            for (CostCategoryObjectCode code : costCategory.getObjectCodes()) {
                if (code.isActive()) {
                    objectCodes.add(code.getFinancialObjectCode());
                }
            }
        }
        if (!objectCodes.isEmpty()) {
            predicates.add(root.get(KFSPropertyConstants.OBJECT_CODE).in(objectCodes));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Balance> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public CostCategory getCostCategoryForObjectCode(Integer universityFiscalYear, String chartOfAccountsCode, String financialObjectCode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CostCategoryObjectCode> cq = cb.createQuery(CostCategoryObjectCode.class);
        Root<CostCategoryObjectCode> root = cq.from(CostCategoryObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), chartOfAccountsCode));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_CODE), financialObjectCode));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.select(root);

        TypedQuery<CostCategoryObjectCode> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CostCategoryObjectCode> results = query.getResultList();
        if (!results.isEmpty()) {
            String categoryCode = results.get(0).getCategoryCode();
            return entityManager.find(CostCategory.class, categoryCode);
        }
        return null;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
