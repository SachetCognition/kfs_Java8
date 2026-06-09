/*
 * Compatibility shim – JasperReportsUtils was removed in Spring 5.0.
 * This stub provides the convertReportData method used by KFS
 * report generation service.
 */
package org.springframework.ui.jasperreports;

import java.util.Collection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class JasperReportsUtils {

    @SuppressWarnings("unchecked")
    public static JRDataSource convertReportData(Object value) {
        if (value instanceof JRDataSource) {
            return (JRDataSource) value;
        } else if (value instanceof Collection) {
            return new JRBeanCollectionDataSource((Collection<?>) value);
        } else if (value instanceof Object[]) {
            return new JRBeanArrayDataSource((Object[]) value);
        } else {
            throw new IllegalArgumentException(
                    "Value [" + value + "] cannot be converted to a JRDataSource");
        }
    }
}
