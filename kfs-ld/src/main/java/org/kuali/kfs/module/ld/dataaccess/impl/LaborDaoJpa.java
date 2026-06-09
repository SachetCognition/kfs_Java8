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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.ld.businessobject.AccountStatusCurrentFunds;
import org.kuali.kfs.module.ld.businessobject.July1PositionFunding;
import org.kuali.kfs.module.ld.dataaccess.LaborDao;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.BusinessObject;

public class LaborDaoJpa implements LaborDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getEncumbranceTotal(Map fieldValues) {
        return KualiDecimal.ZERO;
    }

    @Override
    public Iterator getCurrentFunds(Map fieldValues, boolean isConsolidated) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Collection<July1PositionFunding> getJuly1PositionFunding(Map<String, String> fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public Collection getJuly1(Map fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public void insert(BusinessObject businessObject) {
        entityManager.persist(businessObject);
    }
}
