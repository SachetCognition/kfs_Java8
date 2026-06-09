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
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: objectLevel's consolidation code must match
        predicates.add(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE).in(
            entityManager.createNativeQuery("SELECT FIN_OBJ_LEVEL_CD FROM CA_OBJ_LEVEL_T WHERE FIN_COA_CD = ?1 AND FIN_CONS_OBJ_CD = ?2 AND ROW_ACTV_IND = 'Y'")
                .setParameter(1, consolidation.getChartOfAccountsCode())
                .setParameter(2, consolidation.getFinConsolidationObjectCode())
                .getResultList()));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: objectCode's level's consolidation code must match
        predicates.add(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_CODE).in(
            entityManager.createNativeQuery(
                "SELECT oc.FIN_OBJECT_CD FROM CA_OBJECT_CODE_T oc " +
                "JOIN CA_OBJ_LEVEL_T lvl ON oc.FIN_COA_CD = lvl.FIN_COA_CD AND oc.FIN_OBJ_LEVEL_CD = lvl.FIN_OBJ_LEVEL_CD " +
                "WHERE oc.FIN_COA_CD = ?1 AND lvl.FIN_CONS_OBJ_CD = ?2")
                .setParameter(1, consolidation.getChartOfAccountsCode())
                .setParameter(2, consolidation.getFinConsolidationObjectCode())
                .getResultList()));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: consolidation code must match the level's parent consolidation
        predicates.add(root.get(KFSPropertyConstants.FIN_CONSOLIDATION_OBJECT_CODE).in(
            entityManager.createNativeQuery("SELECT FIN_CONS_OBJ_CD FROM CA_OBJ_LEVEL_T WHERE FIN_COA_CD = ?1 AND FIN_OBJ_LEVEL_CD = ?2 AND ROW_ACTV_IND = 'Y'")
                .setParameter(1, level.getChartOfAccountsCode())
                .setParameter(2, level.getFinancialObjectLevelCode())
                .getResultList()));
        predicates.add(cb.equal(root.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: objectCode's level code must match
        predicates.add(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_CODE).in(
            entityManager.createNativeQuery("SELECT FIN_OBJECT_CD FROM CA_OBJECT_CODE_T WHERE FIN_COA_CD = ?1 AND FIN_OBJ_LEVEL_CD = ?2")
                .setParameter(1, level.getChartOfAccountsCode())
                .setParameter(2, level.getFinancialObjectLevelCode())
                .getResultList()));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: level code must match the objectCode's level
        predicates.add(root.get(KFSPropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE).in(
            entityManager.createNativeQuery("SELECT FIN_OBJ_LEVEL_CD FROM CA_OBJECT_CODE_T WHERE FIN_COA_CD = ?1 AND FIN_OBJECT_CD = ?2")
                .setParameter(1, objectCode.getChartOfAccountsCode())
                .setParameter(2, objectCode.getFinancialObjectCode())
                .getResultList()));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Filter: consolidation code must match the objectCode's consolidation
        predicates.add(root.get(KFSPropertyConstants.FIN_CONSOLIDATION_OBJECT_CODE).in(
            entityManager.createNativeQuery(
                "SELECT lvl.FIN_CONS_OBJ_CD FROM CA_OBJECT_CODE_T oc " +
                "JOIN CA_OBJ_LEVEL_T lvl ON oc.FIN_COA_CD = lvl.FIN_COA_CD AND oc.FIN_OBJ_LEVEL_CD = lvl.FIN_OBJ_LEVEL_CD " +
                "WHERE oc.FIN_COA_CD = ?1 AND oc.FIN_OBJECT_CD = ?2")
                .setParameter(1, objectCode.getChartOfAccountsCode())
                .setParameter(2, objectCode.getFinancialObjectCode())
                .getResultList()));
        predicates.add(root.get(ArPropertyConstants.CATEGORY_CODE).in(getActiveCostCategoryCodes()));

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
        // Dynamically expand collection parameters into individual placeholders
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        int paramIdx = 1;

        sql.append("SELECT b.* FROM GL_BALANCE_T b ");
        sql.append("WHERE b.UNIV_FISCAL_YR = ?" + paramIdx);
        params.add(fiscalYear);
        paramIdx++;

        sql.append(" AND b.FIN_COA_CD = ?" + paramIdx);
        params.add(chartOfAccountsCode);
        int chartParamIdx = paramIdx;
        paramIdx++;

        sql.append(" AND b.ACCOUNT_NBR = ?" + paramIdx);
        params.add(accountNumber);
        paramIdx++;

        sql.append(" AND b.FIN_BALANCE_TYP_CD = ?" + paramIdx);
        params.add(balanceType);
        paramIdx++;

        if (objectType != null && !objectType.isEmpty()) {
            List<String> objTypeList = new ArrayList<String>(objectType);
            sql.append(" AND b.FIN_OBJ_TYP_CD IN (");
            sql.append(buildPlaceholders(paramIdx, objTypeList.size()));
            for (String ot : objTypeList) { params.add(ot); }
            paramIdx += objTypeList.size();
            sql.append(")");
        }

        sql.append(" AND ( ");
        boolean hasOrClause = false;

        if (!objectCodes.isEmpty()) {
            sql.append("b.FIN_OBJECT_CD IN (");
            sql.append(buildPlaceholders(paramIdx, objectCodes.size()));
            for (String oc : objectCodes) { params.add(oc); }
            paramIdx += objectCodes.size();
            sql.append(")");
            hasOrClause = true;
        }
        if (!objectLevelCodes.isEmpty()) {
            if (hasOrClause) sql.append(" OR ");
            sql.append("b.FIN_OBJECT_CD IN (SELECT oc.FIN_OBJECT_CD FROM CA_OBJECT_CODE_T oc ");
            sql.append("WHERE oc.FIN_COA_CD = ?" + chartParamIdx + " AND oc.UNIV_FISCAL_YR = ?1 AND oc.FIN_OBJ_LEVEL_CD IN (");
            sql.append(buildPlaceholders(paramIdx, objectLevelCodes.size()));
            for (String lc : objectLevelCodes) { params.add(lc); }
            paramIdx += objectLevelCodes.size();
            sql.append("))");
            hasOrClause = true;
        }
        if (!consolidationCodes.isEmpty()) {
            if (hasOrClause) sql.append(" OR ");
            sql.append("b.FIN_OBJECT_CD IN (SELECT oc2.FIN_OBJECT_CD FROM CA_OBJECT_CODE_T oc2 ");
            sql.append("JOIN CA_OBJ_LEVEL_T lvl ON oc2.FIN_COA_CD = lvl.FIN_COA_CD AND oc2.FIN_OBJ_LEVEL_CD = lvl.FIN_OBJ_LEVEL_CD ");
            sql.append("WHERE oc2.FIN_COA_CD = ?" + chartParamIdx + " AND oc2.UNIV_FISCAL_YR = ?1 AND lvl.FIN_CONS_OBJ_CD IN (");
            sql.append(buildPlaceholders(paramIdx, consolidationCodes.size()));
            for (String cc : consolidationCodes) { params.add(cc); }
            paramIdx += consolidationCodes.size();
            sql.append("))");
        }
        sql.append(" ) ");

        javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(), Balance.class);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }

        return query.getResultList();
    }

    @Override
    public CostCategory getCostCategoryForObjectCode(Integer universityFiscalYear, String chartOfAccountsCode, String financialObjectCode) {
        // Path 1: Direct match via CostCategoryObjectCode
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

        // Path 2: Match via object level - look up level code for the object code, then find category by level
        String levelSql = "SELECT oc.FIN_OBJ_LEVEL_CD FROM CA_OBJECT_CODE_T oc " +
                "WHERE oc.UNIV_FISCAL_YR = ?1 AND oc.FIN_COA_CD = ?2 AND oc.FIN_OBJECT_CD = ?3";
        javax.persistence.Query levelQuery = entityManager.createNativeQuery(levelSql);
        levelQuery.setParameter(1, universityFiscalYear);
        levelQuery.setParameter(2, chartOfAccountsCode);
        levelQuery.setParameter(3, financialObjectCode);
        @SuppressWarnings("unchecked")
        List<String> levelResults = levelQuery.getResultList();
        if (!levelResults.isEmpty()) {
            String levelCode = levelResults.get(0);
            CriteriaQuery<CostCategoryObjectLevel> lcq = cb.createQuery(CostCategoryObjectLevel.class);
            Root<CostCategoryObjectLevel> lroot = lcq.from(CostCategoryObjectLevel.class);
            List<Predicate> lPreds = new ArrayList<Predicate>();
            lPreds.add(cb.equal(lroot.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), chartOfAccountsCode));
            lPreds.add(cb.equal(lroot.get(KFSPropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE), levelCode));
            lPreds.add(cb.equal(lroot.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));
            lcq.where(lPreds.toArray(new Predicate[0]));
            lcq.select(lroot);
            TypedQuery<CostCategoryObjectLevel> lQuery = entityManager.createQuery(lcq);
            lQuery.setMaxResults(1);
            List<CostCategoryObjectLevel> lResults = lQuery.getResultList();
            if (!lResults.isEmpty()) {
                return entityManager.find(CostCategory.class, lResults.get(0).getCategoryCode());
            }

            // Path 3: Match via consolidation - look up consolidation code for the level, then find category
            String consolSql = "SELECT lvl.FIN_CONS_OBJ_CD FROM CA_OBJ_LEVEL_T lvl " +
                    "WHERE lvl.FIN_COA_CD = ?1 AND lvl.FIN_OBJ_LEVEL_CD = ?2";
            javax.persistence.Query consolQuery = entityManager.createNativeQuery(consolSql);
            consolQuery.setParameter(1, chartOfAccountsCode);
            consolQuery.setParameter(2, levelCode);
            @SuppressWarnings("unchecked")
            List<String> consolResults = consolQuery.getResultList();
            if (!consolResults.isEmpty()) {
                String consolCode = consolResults.get(0);
                CriteriaQuery<CostCategoryObjectConsolidation> ccq = cb.createQuery(CostCategoryObjectConsolidation.class);
                Root<CostCategoryObjectConsolidation> croot = ccq.from(CostCategoryObjectConsolidation.class);
                List<Predicate> cPreds = new ArrayList<Predicate>();
                cPreds.add(cb.equal(croot.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), chartOfAccountsCode));
                cPreds.add(cb.equal(croot.get(KFSPropertyConstants.FIN_CONSOLIDATION_OBJECT_CODE), consolCode));
                cPreds.add(cb.equal(croot.get(KFSPropertyConstants.ACTIVE), Boolean.TRUE));
                ccq.where(cPreds.toArray(new Predicate[0]));
                ccq.select(croot);
                TypedQuery<CostCategoryObjectConsolidation> cQuery = entityManager.createQuery(ccq);
                cQuery.setMaxResults(1);
                List<CostCategoryObjectConsolidation> cResults = cQuery.getResultList();
                if (!cResults.isEmpty()) {
                    return entityManager.find(CostCategory.class, cResults.get(0).getCategoryCode());
                }
            }
        }

        return null;
    }

    private String buildPlaceholders(int startIdx, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append("?" + (startIdx + i));
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private List<String> getActiveCostCategoryCodes() {
        return entityManager.createNativeQuery("SELECT CTGRY_CD FROM AR_CST_CTGRY_T WHERE ROW_ACTV_IND = 'Y'").getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
