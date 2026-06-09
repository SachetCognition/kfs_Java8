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
package org.kuali.kfs.sys.rice;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * KFS-owned business object service interface. Mirrors Rice BusinessObjectService
 * to decouple KFS from direct Rice API dependency.
 */
public interface KfsBusinessObjectService {

    <T extends PersistableBusinessObject> T save(T bo);

    List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects);

    PersistableBusinessObject linkAndSave(PersistableBusinessObject bo);

    List<? extends PersistableBusinessObject> linkAndSave(List<? extends PersistableBusinessObject> businessObjects);

    <T extends BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object primaryKey);

    <T extends BusinessObject> T findByPrimaryKey(Class<T> clazz, Map<String, ?> primaryKeys);

    PersistableBusinessObject retrieve(PersistableBusinessObject object);

    <T extends BusinessObject> Collection<T> findAll(Class<T> clazz);

    <T extends BusinessObject> Collection<T> findAllOrderBy(Class<T> clazz, String sortField, boolean sortAscending);

    <T extends BusinessObject> Collection<T> findMatching(Class<T> clazz, Map<String, ?> fieldValues);

    int countMatching(Class clazz, Map<String, ?> fieldValues);

    int countMatching(Class clazz, Map<String, ?> positiveFieldValues, Map<String, ?> negativeFieldValues);

    <T extends BusinessObject> Collection<T> findMatchingOrderBy(Class<T> clazz, Map<String, ?> fieldValues, String sortField, boolean sortAscending);

    void delete(PersistableBusinessObject bo);

    void delete(List<? extends PersistableBusinessObject> boList);

    void deleteMatching(Class clazz, Map<String, ?> fieldValues);

    BusinessObject getReferenceIfExists(BusinessObject bo, String referenceName);

    void linkUserFields(PersistableBusinessObject bo);

    void linkUserFields(List<PersistableBusinessObject> bos);

    PersistableBusinessObject manageReadOnly(PersistableBusinessObject bo);
}
