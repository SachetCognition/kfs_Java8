package org.kuali.kfs.module.bc.exception;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.LockStatus;
import org.kuali.kfs.module.bc.BCKeyConstants;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionLockStatus;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BudgetConstructionExceptionTest extends KfsUnitTestBase {

    @Test
    void budgetPositionAlreadyExistsException_holdsMessageKey() {
        BudgetPositionAlreadyExistsException ex = new BudgetPositionAlreadyExistsException(2024, "POS001");
        assertThat(ex.getMessageKey()).isEqualTo(BCKeyConstants.ERROR_BUDGET_POSITION_ALREADY_EXISTS);
        assertThat(ex.getMessageParameters()).containsExactly("2024", "POS001");
    }

    @Test
    void budgetIncumbentAlreadyExistsException_holdsMessageKey() {
        BudgetIncumbentAlreadyExistsException ex = new BudgetIncumbentAlreadyExistsException("EMP001");
        assertThat(ex.getMessageKey()).isEqualTo(BCKeyConstants.ERROR_BUDGET_INCUMBENT_ALREADY_EXISTS);
        assertThat(ex.getMessageParameters()).containsExactly("EMP001");
    }

    @Test
    void positionNotFoundException_holdsMessageKey() {
        PositionNotFoundException ex = new PositionNotFoundException(2024, "POS002");
        assertThat(ex.getMessageKey()).isEqualTo(BCKeyConstants.ERROR_EXTERNAL_POSITION_NOT_FOUND);
        assertThat(ex.getMessageParameters()).containsExactly("2024", "POS002");
    }

    @Test
    void incumbentNotFoundException_holdsMessageKey() {
        IncumbentNotFoundException ex = new IncumbentNotFoundException("EMP002");
        assertThat(ex.getMessageKey()).isEqualTo(BCKeyConstants.ERROR_EXTERNAL_INCUMBENT_NOT_FOUND);
        assertThat(ex.getMessageParameters()).containsExactly("EMP002");
    }

    @Test
    void budgetConstructionLockUnavailableException_holdsLockStatus() {
        BudgetConstructionLockStatus lockStatus = new BudgetConstructionLockStatus();
        lockStatus.setLockStatus(LockStatus.BY_OTHER);
        lockStatus.setAccountLockOwner("otherUser");

        BudgetConstructionLockUnavailableException ex = new BudgetConstructionLockUnavailableException(lockStatus);
        assertThat(ex.getLockStatus()).isSameAs(lockStatus);
        assertThat(ex.getLockStatus().getLockStatus()).isEqualTo(LockStatus.BY_OTHER);
        assertThat(ex.getLockStatus().getAccountLockOwner()).isEqualTo("otherUser");
    }

    @Test
    void budgetConstructionLockUnavailableException_isRuntimeException() {
        BudgetConstructionLockStatus lockStatus = new BudgetConstructionLockStatus();
        BudgetConstructionLockUnavailableException ex = new BudgetConstructionLockUnavailableException(lockStatus);
        assertThat(ex).isInstanceOf(RuntimeException.class);
    }

    @Test
    void budgetConstructionDocumentAuthorizationException_pickListMode_returnsRiceKey() {
        BudgetConstructionDocumentAuthorizationException ex =
                new BudgetConstructionDocumentAuthorizationException("user1", "edit", "DOC001", "locked", true);
        assertThat(ex.getErrorMessageKey()).isEqualTo(RiceKeyConstants.AUTHORIZATION_ERROR_DOCUMENT);
    }

    @Test
    void budgetConstructionDocumentAuthorizationException_nonPickListMode_returnsBCKey() {
        BudgetConstructionDocumentAuthorizationException ex =
                new BudgetConstructionDocumentAuthorizationException("user1", "edit", "DOC001", "locked", false);
        assertThat(ex.getErrorMessageKey()).isEqualTo(BCKeyConstants.ERROR_BUDGET_AUTHORIZATION_DOCUMENT);
    }

    @Test
    void positionNotFoundException_isRuntimeException() {
        assertThatThrownBy(() -> {
            throw new PositionNotFoundException(2024, "P1");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void incumbentNotFoundException_isRuntimeException() {
        assertThatThrownBy(() -> {
            throw new IncumbentNotFoundException("E1");
        }).isInstanceOf(RuntimeException.class);
    }
}
