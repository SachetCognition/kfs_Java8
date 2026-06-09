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
package org.kuali.kfs.module.ld.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.dataaccess.AccountingLineDao;

public class ExpenseTransferAccountingLineDaoJpa implements AccountingLineDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteAccountingLine(AccountingLine line) {
        if (entityManager.contains(line)) {
            entityManager.remove(line);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList findByDocumentHeaderId(Class clazz, String id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(clazz);
        Root root = query.from(clazz);
        query.where(cb.equal(root.get("documentNumber"), id));
        return new ArrayList<>(entityManager.createQuery(query).getResultList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findByDocumentHeaderIdAndLineType(Class clazz, String id, String lineType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(clazz);
        Root root = query.from(clazz);
        query.where(cb.and(
            cb.equal(root.get("documentNumber"), id),
            cb.equal(root.get("financialDocumentLineTypeCode"), lineType)
        ));
        return entityManager.createQuery(query).getResultList();
    }
}
