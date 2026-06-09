package org.kuali.rice.core.api.lifecycle;
public class BaseLifecycle implements Lifecycle {
    private boolean started = false;
    public boolean isStarted() { return started; }
    public void start() throws Exception { started = true; }
    public void stop() throws Exception { started = false; }
}
