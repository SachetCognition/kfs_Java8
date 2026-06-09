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
package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.kuali.kfs.pdp.businessobject.ACHBank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AchBankServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AchBankServiceImpl achBankService;

    @Test
    void getByPrimaryId_delegatesToBusinessObjectService() {
        ACHBank expected = new ACHBank();
        when(businessObjectService.findByPrimaryKey(eq(ACHBank.class), any(Map.class))).thenReturn(expected);

        ACHBank result = achBankService.getByPrimaryId("071000013");

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(ACHBank.class), any(Map.class));
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(ACHBank.class), any(Map.class))).thenReturn(null);

        ACHBank result = achBankService.getByPrimaryId("000000000");

        assertThat(result).isNull();
    }

    @Test
    void reloadTable_withNonExistentFile_returnsFalse() {
        boolean result = achBankService.reloadTable("/tmp/nonexistent_file_achbank_test.txt");

        assertThat(result).isFalse();
    }

    @TempDir
    Path tempDir;

    @Test
    void reloadTable_withEmptyFile_returnsTrue() throws IOException {
        File emptyFile = tempDir.resolve("empty.txt").toFile();
        emptyFile.createNewFile();

        boolean result = achBankService.reloadTable(emptyFile.getAbsolutePath());

        assertThat(result).isTrue();
        verify(businessObjectService).deleteMatching(eq(ACHBank.class), any(Map.class));
    }
}
