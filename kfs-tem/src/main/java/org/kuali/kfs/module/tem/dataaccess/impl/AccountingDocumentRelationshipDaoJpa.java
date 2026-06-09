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
package org.kuali.kfs.module.tem.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.tem.businessobject.AccountingDocumentRelationship;
import org.kuali.kfs.module.tem.dataaccess.AccountingDocumentRelationshipDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountingDocumentRelationshipDaoJpa implements AccountingDocumentRelationshipDao {

    private static final Logger LOG = Logger.getLogger(AccountingDocumentRelationshipDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountingDocumentRelationship> findAccountingDocumentRelationshipByDocumentNumber(String value) {
        return findAccountingDocumentRelationshipByDocumentNumber(null, value);
    }

    @Override
    public List<AccountingDocumentRelationship> findAccountingDocumentRelationshipByDocumentNumber(String attribute, String value) {
        if (value == null) {
            return null;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountingDocumentRelationship> cq = cb.createQuery(AccountingDocumentRelationship.class);
        Root<AccountingDocumentRelationship> root = cq.from(AccountingDocumentRelationship.class);

        if (attribute != null) {
            cq.where(cb.equal(root.get(attribute), value));
        } else {
            Predicate docNbrPred = cb.equal(root.get(AccountingDocumentRelationship.DOC_NBR), value);
            Predicate relDocNbrPred = cb.equal(root.get(AccountingDocumentRelationship.REL_DOC_NBR), value);
            cq.where(cb.or(docNbrPred, relDocNbrPred));
        }

        TypedQuery<AccountingDocumentRelationship> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<AccountingDocumentRelationship> findAccountingDocumentRelationship(AccountingDocumentRelationship adr) {
        if (adr == null) {
            return null;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountingDocumentRelationship> cq = cb.createQuery(AccountingDocumentRelationship.class);
        Root<AccountingDocumentRelationship> root = cq.from(AccountingDocumentRelationship.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (adr.getId() != null) {
            predicates.add(cb.equal(root.get(AccountingDocumentRelationship.ID), adr.getId()));
        }
        if (adr.getDocumentNumber() != null) {
            predicates.add(cb.equal(root.get(AccountingDocumentRelationship.DOC_NBR), adr.getDocumentNumber()));
        }
        if (adr.getRelDocumentNumber() != null) {
            predicates.add(cb.equal(root.get(AccountingDocumentRelationship.REL_DOC_NBR), adr.getRelDocumentNumber()));
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<AccountingDocumentRelationship> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(AccountingDocumentRelationship accountingDocumentRelationship) {
        if (accountingDocumentRelationship.getDocumentNumber() != null &&
                accountingDocumentRelationship.getRelDocumentNumber() != null &&
                !accountingDocumentRelationship.getDocumentNumber().equals(accountingDocumentRelationship.getRelDocumentNumber())) {

            List<AccountingDocumentRelationship> adrList = findAccountingDocumentRelationship(accountingDocumentRelationship);
            if (!adrList.isEmpty()) {
                if (adrList.size() > 1) {
                    LOG.error("Found multiple AccountingDocumentRelationships with the same data. This should never happen.");
                }
                return;
            }

            adrList = findAccountingDocumentRelationship(new AccountingDocumentRelationship(accountingDocumentRelationship.getRelDocumentNumber(), accountingDocumentRelationship.getDocumentNumber()));
            if (!adrList.isEmpty()) {
                if (adrList.size() > 1) {
                    LOG.error("Found multiple AccountingDocumentRelationships with the same data. This should never happen.");
                }
                return;
            }

            entityManager.persist(accountingDocumentRelationship);
        } else {
            LOG.warn("Bad accountingDocumentRelationship. " + accountingDocumentRelationship.toStringMapper_RICE20_REFACTORME());
        }
    }

    @Override
    public void delete(AccountingDocumentRelationship accountingDocumentRelationship) {
        AccountingDocumentRelationship managed = entityManager.merge(accountingDocumentRelationship);
        entityManager.remove(managed);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
