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
    @SuppressWarnings("unchecked")
    public List<Balance> getBalancesForCostCategory(Integer fiscalYear, String chartOfAccountsCode, String accountNumber, String balanceType, Collection<String> objectType, CostCategory costCategory) {
        List<Balance> results = new ArrayList<Balance>();

        if (costCategory == null || !costCategory.isActive()) {
            return results;
        }

        // Collect object codes directly from cost category
        List<String> objectCodes = new ArrayList<String>();
        if (costCategory.getObjectCodes() != null) {
            for (CostCategoryObjectCode code : costCategory.getObjectCodes()) {
                if (code.isActive() && chartOfAccountsCode.equals(code.getChartOfAccountsCode())) {
                    objectCodes.add(code.getFinancialObjectCode());
                }
            }
        }

        // Collect object level codes from cost category
        List<String> objectLevelCodes = new ArrayList<String>();
        if (costCategory.getObjectLevels() != null) {
            for (CostCategoryObjectLevel level : costCategory.getObjectLevels()) {
                if (level.isActive() && chartOfAccountsCode.equals(level.getChartOfAccountsCode())) {
                    objectLevelCodes.add(level.getFinancialObjectLevelCode());
                }
            }
        }

        // Collect consolidation codes from cost category
        List<String> consolidationCodes = new ArrayList<String>();
        if (costCategory.getObjectConsolidations() != null) {
            for (CostCategoryObjectConsolidation consol : costCategory.getObjectConsolidations()) {
                if (consol.isActive() && chartOfAccountsCode.equals(consol.getChartOfAccountsCode())) {
                    consolidationCodes.add(consol.getFinConsolidationObjectCode());
                }
            }
        }

        if (objectCodes.isEmpty() && objectLevelCodes.isEmpty() && consolidationCodes.isEmpty()) {
            return results;
        }

        // Use native SQL to build OR across object codes, levels, and consolidations
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.* FROM GL_BALANCE_T b ");
        sql.append("WHERE b.UNIV_FISCAL_YR = ?1 ");
        sql.append("AND b.FIN_COA_CD = ?2 ");
        sql.append("AND b.ACCOUNT_NBR = ?3 ");
        sql.append("AND b.FIN_BALANCE_TYP_CD = ?4 ");
        if (objectType != null && !objectType.isEmpty()) {
            sql.append("AND b.FIN_OBJ_TYP_CD IN (?5) ");
        }
        sql.append("AND ( ");

        boolean hasOrClause = false;
        if (!objectCodes.isEmpty()) {
            sql.append("b.FIN_OBJECT_CD IN (?6) ");
            hasOrClause = true;
        }
        if (!objectLevelCodes.isEmpty()) {
            if (hasOrClause) sql.append("OR ");
            sql.append("b.FIN_OBJECT_CD IN (SELECT oc.FIN_OBJECT_CD FROM CA_OBJECT_CODE_T oc ");
            sql.append("WHERE oc.FIN_COA_CD = ?2 AND oc.UNIV_FISCAL_YR = ?1 AND oc.FIN_OBJ_LEVEL_CD IN (?7)) ");
            hasOrClause = true;
        }
        if (!consolidationCodes.isEmpty()) {
            if (hasOrClause) sql.append("OR ");
            sql.append("b.FIN_OBJECT_CD IN (SELECT oc2.FIN_OBJECT_CD FROM CA_OBJECT_CODE_T oc2 ");
            sql.append("JOIN CA_OBJ_LEVEL_T lvl ON oc2.FIN_COA_CD = lvl.FIN_COA_CD AND oc2.FIN_OBJ_LEVEL_CD = lvl.FIN_OBJ_LEVEL_CD ");
            sql.append("WHERE oc2.FIN_COA_CD = ?2 AND oc2.UNIV_FISCAL_YR = ?1 AND lvl.FIN_CONS_OBJ_CD IN (?8)) ");
        }
        sql.append(") ");

        javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(), Balance.class);
        query.setParameter(1, fiscalYear);
        query.setParameter(2, chartOfAccountsCode);
        query.setParameter(3, accountNumber);
        query.setParameter(4, balanceType);
        if (objectType != null && !objectType.isEmpty()) {
            query.setParameter(5, objectType);
        }
        if (!objectCodes.isEmpty()) {
            query.setParameter(6, objectCodes);
        }
        if (!objectLevelCodes.isEmpty()) {
            query.setParameter(7, objectLevelCodes);
        }
        if (!consolidationCodes.isEmpty()) {
            query.setParameter(8, consolidationCodes);
        }

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
