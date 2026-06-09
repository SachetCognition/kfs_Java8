package org.kuali.rice.core.api.lifecycle;
public interface Lifecycle {
    boolean isStarted();
    void start() throws Exception;
    void stop() throws Exception;
}
