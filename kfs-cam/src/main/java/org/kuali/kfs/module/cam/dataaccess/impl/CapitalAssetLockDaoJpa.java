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
package org.kuali.kfs.module.cam.dataaccess.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.cam.dataaccess.CapitalAssetLockDao;

public class CapitalAssetLockDaoJpa implements CapitalAssetLockDao {

    private EntityManager entityManager;

    @Override
    public List<String> getLockingDocumentNumbers(Collection capitalAssetNumbers, Collection documentTypeNames, String documentNumber) {
        StringBuilder jpql = new StringBuilder("SELECT al.documentNumber FROM AssetLock al WHERE al.capitalAssetNumber IN :assetNumbers");

        if (documentTypeNames != null && !documentTypeNames.isEmpty()) {
            jpql.append(" AND al.documentTypeName IN :docTypeNames");
        }

        if (StringUtils.isNotBlank(documentNumber)) {
            jpql.append(" AND al.documentNumber <> :docNumber");
        }

        TypedQuery<String> query = entityManager.createQuery(jpql.toString(), String.class);
        query.setParameter("assetNumbers", capitalAssetNumbers);

        if (documentTypeNames != null && !documentTypeNames.isEmpty()) {
            query.setParameter("docTypeNames", documentTypeNames);
        }

        if (StringUtils.isNotBlank(documentNumber)) {
            query.setParameter("docNumber", documentNumber);
        }

        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
