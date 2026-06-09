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
package org.kuali.kfs.module.cam.document.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.cam.businessobject.AssetComponent;
import org.kuali.kfs.module.cam.document.dataaccess.AssetComponentDao;

public class AssetComponentDaoJpa implements AssetComponentDao {

    private EntityManager entityManager;

    @Override
    public Integer getMaxSquenceNumber(AssetComponent assetComponent) {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT MAX(ac.componentNumber) FROM AssetComponent ac WHERE ac.capitalAssetNumber = :assetNumber",
                Integer.class);
        query.setParameter("assetNumber", assetComponent.getCapitalAssetNumber());
        Integer result = query.getSingleResult();
        return result != null ? result : Integer.valueOf(0);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
