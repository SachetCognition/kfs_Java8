package org.kuali.kfs.module.ec.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LedgerBalanceWithMessageTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("Default constructor")
    class DefaultConstructor {

        @Test
        void shouldInitializeAllFieldsToEmpty() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();

            assertThat(obj.getChartOfAccountsCode()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getAccountNumber()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getSubAccountNumber()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getFinancialObjectCode()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getPositionNumber()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getEmplid()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getEmployeeName()).isEqualTo(KFSConstants.EMPTY_STRING);
            assertThat(obj.getMessage()).isEqualTo(KFSConstants.EMPTY_STRING);
        }
    }

    @Nested
    @DisplayName("Three-arg constructor")
    class ThreeArgConstructor {

        @Test
        void shouldSetIdNameAndMessage() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage("E001", "John Doe", "Test Message");

            assertThat(obj.getEmplid()).isEqualTo("E001");
            assertThat(obj.getEmployeeName()).isEqualTo("John Doe");
            assertThat(obj.getMessage()).isEqualTo("Test Message");
            assertThat(obj.getChartOfAccountsCode()).isEqualTo(KFSConstants.EMPTY_STRING);
        }
    }

    @Nested
    @DisplayName("Getter/Setter tests")
    class GetterSetterTests {

        @Test
        void shouldSetAndGetChartOfAccountsCode() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setChartOfAccountsCode("BL");
            assertThat(obj.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void shouldSetAndGetAccountNumber() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setAccountNumber("1234567");
            assertThat(obj.getAccountNumber()).isEqualTo("1234567");
        }

        @Test
        void shouldSetAndGetSubAccountNumber() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setSubAccountNumber("SUB01");
            assertThat(obj.getSubAccountNumber()).isEqualTo("SUB01");
        }

        @Test
        void shouldSetAndGetFinancialObjectCode() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setFinancialObjectCode("4000");
            assertThat(obj.getFinancialObjectCode()).isEqualTo("4000");
        }

        @Test
        void shouldSetAndGetPositionNumber() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setPositionNumber("POS001");
            assertThat(obj.getPositionNumber()).isEqualTo("POS001");
        }

        @Test
        void shouldSetAndGetEmplid() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setEmplid("EMP001");
            assertThat(obj.getEmplid()).isEqualTo("EMP001");
        }

        @Test
        void shouldSetAndGetEmployeeName() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setEmployeeName("Jane Smith");
            assertThat(obj.getEmployeeName()).isEqualTo("Jane Smith");
        }

        @Test
        void shouldSetAndGetMessage() {
            LedgerBalanceWithMessage obj = new LedgerBalanceWithMessage();
            obj.setMessage("Error occurred");
            assertThat(obj.getMessage()).isEqualTo("Error occurred");
        }
    }
}
