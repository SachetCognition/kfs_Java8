package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class CalculatedSalaryFoundationTrackerOverrideServiceImplTest extends KfsUnitTestBase {

    private final CalculatedSalaryFoundationTrackerOverrideServiceImpl service =
            new CalculatedSalaryFoundationTrackerOverrideServiceImpl();

    @Test
    public void testIsValidAppointment_nullEmplid() {
        assertFalse(service.isValidAppointment(2024, "P001", null));
    }

    @Test
    public void testIsValidAppointment_vacantEmplid() {
        assertTrue(service.isValidAppointment(2024, "P001", "VACANT"));
    }

    @Test
    public void testIsValidAppointment_regularEmplid() {
        assertTrue(service.isValidAppointment(2024, "P001", "12345"));
    }

    @Test
    public void testIsValidPosition_nullPositionNumber() {
        assertFalse(service.isValidPosition(2024, null));
    }

    @Test
    public void testIsValidPosition_nullFiscalYear() {
        assertFalse(service.isValidPosition(null, "P001"));
    }

    @Test
    public void testIsValidPosition_bothNull() {
        assertFalse(service.isValidPosition(null, null));
    }

    @Test
    public void testIsValidPosition_validInputs() {
        assertTrue(service.isValidPosition(2024, "P001"));
    }
}
