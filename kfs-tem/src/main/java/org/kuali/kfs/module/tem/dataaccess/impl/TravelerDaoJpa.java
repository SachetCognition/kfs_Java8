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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.log4j.Logger;
import org.kuali.kfs.integration.ar.AccountsReceivableCustomer;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleService;
import org.kuali.kfs.module.tem.dataaccess.TravelerDao;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.dao.LookupDao;

public class TravelerDaoJpa implements TravelerDao {

    public static Logger LOG = Logger.getLogger(TravelerDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    private LookupDao lookupDao;
    private AccountsReceivableModuleService accountsReceivableModuleService;

    private static final String CUSTOMER_ADDRESSES_ATTR_PREFIX = "customerAddresses.";

    @Override
    public Collection<AccountsReceivableCustomer> findCustomersBy(final Map<String, String> criteria) {
        Class<? extends AccountsReceivableCustomer> customerClass =
                getAccountsReceivableModuleService().createCustomer().getClass();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(customerClass);
        Root root = cq.from(customerClass);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Join addressJoin = null;

        for (final Map.Entry<String, String> entry : criteria.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.contains(CUSTOMER_ADDRESSES_ATTR_PREFIX)) {
                if (addressJoin == null) {
                    addressJoin = root.join("customerAddresses", JoinType.INNER);
                }
                String addressField = key.substring(CUSTOMER_ADDRESSES_ATTR_PREFIX.length());
                if (value.contains("*") || value.contains("%")) {
                    predicates.add(cb.like(addressJoin.get(addressField), value.replace('*', '%')));
                } else {
                    predicates.add(cb.equal(addressJoin.get(addressField), value));
                }
            } else {
                if (value.contains("*") || value.contains("%")) {
                    predicates.add(cb.like(root.get(key), value.replace('*', '%')));
                } else {
                    predicates.add(cb.equal(root.get(key), value));
                }
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }
        cq.distinct(true);

        return entityManager.createQuery(cq).getResultList();
    }

    public void setLookupDao(final LookupDao lookupDao) {
        this.lookupDao = lookupDao;
    }

    protected LookupDao getLookupDao() {
        return lookupDao;
    }

    protected AccountsReceivableModuleService getAccountsReceivableModuleService() {
        if (accountsReceivableModuleService == null) {
            this.accountsReceivableModuleService = SpringContext.getBean(AccountsReceivableModuleService.class);
        }
        return accountsReceivableModuleService;
    }

    public void setAccountsReceivableModuleService(AccountsReceivableModuleService accountsReceivableModuleService) {
        this.accountsReceivableModuleService = accountsReceivableModuleService;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
