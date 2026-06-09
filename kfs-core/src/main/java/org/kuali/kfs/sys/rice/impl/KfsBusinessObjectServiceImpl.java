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
package org.kuali.kfs.sys.rice.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kfs.sys.rice.KfsBusinessObjectService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Delegates to Rice BusinessObjectService.
 */
public class KfsBusinessObjectServiceImpl implements KfsBusinessObjectService {

    private BusinessObjectService businessObjectService;

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    @Override
    public <T extends PersistableBusinessObject> T save(T bo) {
        return businessObjectService.save(bo);
    }

    @Override
    public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects) {
        return businessObjectService.save(businessObjects);
    }

    @Override
    public PersistableBusinessObject linkAndSave(PersistableBusinessObject bo) {
        return businessObjectService.linkAndSave(bo);
    }

    @Override
    public List<? extends PersistableBusinessObject> linkAndSave(List<? extends PersistableBusinessObject> businessObjects) {
        return businessObjectService.linkAndSave(businessObjects);
    }

    @Override
    public <T extends BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object primaryKey) {
        return businessObjectService.findBySinglePrimaryKey(clazz, primaryKey);
    }

    @Override
    public <T extends BusinessObject> T findByPrimaryKey(Class<T> clazz, Map<String, ?> primaryKeys) {
        return businessObjectService.findByPrimaryKey(clazz, primaryKeys);
    }

    @Override
    public PersistableBusinessObject retrieve(PersistableBusinessObject object) {
        return businessObjectService.retrieve(object);
    }

    @Override
    public <T extends BusinessObject> Collection<T> findAll(Class<T> clazz) {
        return businessObjectService.findAll(clazz);
    }

    @Override
    public <T extends BusinessObject> Collection<T> findAllOrderBy(Class<T> clazz, String sortField, boolean sortAscending) {
        return businessObjectService.findAllOrderBy(clazz, sortField, sortAscending);
    }

    @Override
    public <T extends BusinessObject> Collection<T> findMatching(Class<T> clazz, Map<String, ?> fieldValues) {
        return businessObjectService.findMatching(clazz, fieldValues);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public int countMatching(Class clazz, Map<String, ?> fieldValues) {
        return businessObjectService.countMatching(clazz, fieldValues);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public int countMatching(Class clazz, Map<String, ?> positiveFieldValues, Map<String, ?> negativeFieldValues) {
        return businessObjectService.countMatching(clazz, positiveFieldValues, negativeFieldValues);
    }

    @Override
    public <T extends BusinessObject> Collection<T> findMatchingOrderBy(Class<T> clazz, Map<String, ?> fieldValues, String sortField, boolean sortAscending) {
        return businessObjectService.findMatchingOrderBy(clazz, fieldValues, sortField, sortAscending);
    }

    @Override
    public void delete(PersistableBusinessObject bo) {
        businessObjectService.delete(bo);
    }

    @Override
    public void delete(List<? extends PersistableBusinessObject> boList) {
        businessObjectService.delete(boList);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void deleteMatching(Class clazz, Map<String, ?> fieldValues) {
        businessObjectService.deleteMatching(clazz, fieldValues);
    }

    @Override
    public BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName) {
        return businessObjectService.getReferenceIfExists(bo, referenceName);
    }

    @Override
    public void linkUserFields(PersistableBusinessObject bo) {
        businessObjectService.linkUserFields(bo);
    }

    @Override
    public void linkUserFields(List<PersistableBusinessObject> bos) {
        businessObjectService.linkUserFields(bos);
    }

    @Override
    public PersistableBusinessObject manageReadOnly(PersistableBusinessObject bo) {
        return businessObjectService.manageReadOnly(bo);
    }
}
