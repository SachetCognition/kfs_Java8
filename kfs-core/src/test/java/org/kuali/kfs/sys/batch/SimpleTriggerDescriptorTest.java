package org.kuali.kfs.sys.batch;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SimpleTriggerDescriptorTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    private SimpleTriggerDescriptor createDescriptor() {
        when(dateTimeService.getCurrentDate()).thenReturn(new Date());
        return new SimpleTriggerDescriptor("testTrigger", "testGroup", "testJob", dateTimeService);
    }

    @Test
    void getTrigger_returnsSimpleTrigger() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger).isInstanceOf(SimpleTrigger.class);
    }

    @Test
    void getTrigger_setsJobName() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger.getJobKey().getName()).isEqualTo("testJob");
    }

    @Test
    void getTrigger_setsJobGroup() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger.getJobKey().getGroup()).isEqualTo("testGroup");
    }

    @Test
    void getTrigger_setsStartTime() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger.getStartTime()).isNotNull();
    }

    @Test
    void getTrigger_testMode_setsStartTimeInFuture() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        descriptor.setTestMode(true);
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger.getStartTime()).isAfter(new Date());
    }

    @Test
    void getTrigger_normalMode_usesStartDelay() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        long delay = 5000L;
        descriptor.setStartDelay(delay);
        descriptor.setStartTime(new Date());
        Trigger trigger = descriptor.getTrigger();
        assertThat(trigger.getStartTime()).isNotNull();
    }

    @Test
    void setRepeatCount_appliedInNormalMode() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        descriptor.setRepeatCount(3);
        Trigger trigger = descriptor.getTrigger();
        assertThat(((SimpleTrigger) trigger).getRepeatCount()).isEqualTo(3);
    }

    @Test
    void setRepeatCount_notAppliedInTestMode() {
        SimpleTriggerDescriptor descriptor = createDescriptor();
        descriptor.setRepeatCount(3);
        descriptor.setTestMode(true);
        Trigger trigger = descriptor.getTrigger();
        assertThat(((SimpleTrigger) trigger).getRepeatCount()).isZero();
    }

    @Test
    void isTestMode_defaultFalse() {
        SimpleTriggerDescriptor fresh = new SimpleTriggerDescriptor();
        assertThat(fresh.isTestMode()).isFalse();
    }

    @Test
    void defaultConstructor_works() {
        SimpleTriggerDescriptor fresh = new SimpleTriggerDescriptor();
        assertThat(fresh).isNotNull();
    }
}
