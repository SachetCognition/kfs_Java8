package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.LockStatus;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionLockStatusTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_setsLockStatusToNoDoor() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        assertThat(status.getLockStatus()).isEqualTo(LockStatus.NO_DOOR);
    }

    @Test
    void defaultConstructor_setsOwnersToNull() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        assertThat(status.getAccountLockOwner()).isNull();
        assertThat(status.getPositionLockOwner()).isNull();
        assertThat(status.getTransactionLockOwner()).isNull();
        assertThat(status.getFundingLocks()).isNull();
        assertThat(status.getBudgetConstructionHeader()).isNull();
    }

    @Test
    void setAndGetLockStatus() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        status.setLockStatus(LockStatus.SUCCESS);
        assertThat(status.getLockStatus()).isEqualTo(LockStatus.SUCCESS);

        status.setLockStatus(LockStatus.BY_OTHER);
        assertThat(status.getLockStatus()).isEqualTo(LockStatus.BY_OTHER);

        status.setLockStatus(LockStatus.OPTIMISTIC_EX);
        assertThat(status.getLockStatus()).isEqualTo(LockStatus.OPTIMISTIC_EX);
    }

    @Test
    void setAndGetAccountLockOwner() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        status.setAccountLockOwner("user1");
        assertThat(status.getAccountLockOwner()).isEqualTo("user1");
    }

    @Test
    void setAndGetPositionLockOwner() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        status.setPositionLockOwner("user2");
        assertThat(status.getPositionLockOwner()).isEqualTo("user2");
    }

    @Test
    void setAndGetTransactionLockOwner() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        status.setTransactionLockOwner("user3");
        assertThat(status.getTransactionLockOwner()).isEqualTo("user3");
    }

    @Test
    void setAndGetFundingLocks() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        SortedSet<BudgetConstructionFundingLock> locks = new TreeSet<>();
        status.setFundingLocks(locks);
        assertThat(status.getFundingLocks()).isSameAs(locks);
    }

    @Test
    void setAndGetBudgetConstructionHeader() {
        BudgetConstructionLockStatus status = new BudgetConstructionLockStatus();
        BudgetConstructionHeader header = new BudgetConstructionHeader();
        status.setBudgetConstructionHeader(header);
        assertThat(status.getBudgetConstructionHeader()).isSameAs(header);
    }

    @Test
    void lockStatusEnum_containsExpectedValues() {
        assertThat(LockStatus.values())
                .extracting(Enum::name)
                .containsExactlyInAnyOrder("SUCCESS", "BY_OTHER", "NO_DOOR", "OPTIMISTIC_EX", "FLOCK_FOUND");
    }
}
