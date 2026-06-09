package org.kuali.rice.krad.bo;

public interface Exporter {
    void export(java.lang.Class<?> p0, java.util.List<? extends java.lang.Object> p1, java.lang.String p2, java.io.OutputStream p3) throws java.io.IOException, org.kuali.rice.krad.exception.ExportNotSupportedException;
    java.util.List<java.lang.String> getSupportedFormats(java.lang.Class<?> p0);
}
