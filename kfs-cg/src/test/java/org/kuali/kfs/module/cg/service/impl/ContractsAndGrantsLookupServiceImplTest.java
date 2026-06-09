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
package org.kuali.kfs.module.cg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

public class ContractsAndGrantsLookupServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ContractsAndGrantsLookupServiceImpl service;

    @Test
    public void testSetupSearchFields_blankUsername() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("lookupPerson.principalName", "");

        boolean result = service.setupSearchFields(fieldValues, "lookupPerson.principalName", "principalId");

        assertTrue(result);
        assertEquals("", fieldValues.get("lookupPerson.principalName"));
    }

    @Test
    public void testSetupSearchFields_nullUsername() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("lookupPerson.principalName", null);

        boolean result = service.setupSearchFields(fieldValues, "lookupPerson.principalName", "principalId");

        assertTrue(result);
    }

    @Test
    public void testSetupSearchFields_noUsernameKey() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("otherField", "someValue");

        boolean result = service.setupSearchFields(fieldValues, "lookupPerson.principalName", "principalId");

        assertTrue(result);
        assertEquals("someValue", fieldValues.get("otherField"));
    }

    @Test
    public void testSetupSearchFields_blankUsernamePreservesOtherFields() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("lookupPerson.principalName", "");
        fieldValues.put("chartOfAccountsCode", "BL");
        fieldValues.put("accountNumber", "1234567");

        boolean result = service.setupSearchFields(fieldValues, "lookupPerson.principalName", "principalId");

        assertTrue(result);
        assertEquals("BL", fieldValues.get("chartOfAccountsCode"));
        assertEquals("1234567", fieldValues.get("accountNumber"));
    }

    @Test
    public void testSetupSearchFields_emptyMapReturnsTrue() {
        Map<String, String> fieldValues = new HashMap<>();

        boolean result = service.setupSearchFields(fieldValues, "lookupPerson.principalName", "principalId");

        assertTrue(result);
        assertTrue(fieldValues.isEmpty());
    }
}
