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
package org.kuali.kfs.module.purap.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kfs.module.purap.PurapPropertyConstants;
import org.kuali.kfs.module.purap.businessobject.AccountsPayableSummaryAccount;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.dataaccess.PurApAccountingDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PurApAccountingDaoJpa implements PurApAccountingDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List getAccountingLinesForItem(PurApItem item) {
        Class clazz = item.getAccountingLineClass();
        String jpql = "SELECT a FROM " + clazz.getSimpleName() + " a WHERE a.itemIdentifier = :itemId";
        if (item instanceof PurchaseOrderItem) {
            jpql += " AND a.documentNumber = :docNum";
        }
        Query query = entityManager.createQuery(jpql);
        query.setParameter("itemId", item.getItemIdentifier());
        if (item instanceof PurchaseOrderItem) {
            query.setParameter("docNum", ((PurchaseOrderItem) item).getDocumentNumber());
        }
        return new ArrayList(query.getResultList());
    }

    @Override
    public void deleteSummaryAccountsbyPaymentRequestIdentifier(Integer paymentRequestIdentifier) {
        if (paymentRequestIdentifier != null) {
            entityManager.createQuery(
                "DELETE FROM AccountsPayableSummaryAccount a WHERE a.paymentRequestIdentifier = :id")
                .setParameter("id", paymentRequestIdentifier)
                .executeUpdate();
            entityManager.flush();
            entityManager.clear();
        }
    }

    @Override
    public void deleteSummaryAccountsbyCreditMemoIdentifier(Integer creditMemoIdentifier) {
        if (creditMemoIdentifier != null) {
            entityManager.createQuery(
                "DELETE FROM AccountsPayableSummaryAccount a WHERE a.creditMemoIdentifier = :id")
                .setParameter("id", creditMemoIdentifier)
                .executeUpdate();
            entityManager.flush();
            entityManager.clear();
        }
    }
}
