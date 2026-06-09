package org.kuali.rice.core.api.mo.common.active;
public interface InactivatableFromTo extends Inactivatable {
    java.sql.Timestamp getActiveFromDate();
    java.sql.Timestamp getActiveToDate();
}
