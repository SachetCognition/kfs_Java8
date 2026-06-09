package org.springframework.ui.jasperreports;

import java.util.Collection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Compatibility shim for Spring 6.x migration (S-4A).
 * Spring's JasperReportsUtils was removed in Spring 5.x; this stub provides
 * the convertReportData method used by KFS.
 *
 * @deprecated Provided only for backward compatibility.
 *             New code should use JasperReports API directly.
 */
@Deprecated
public abstract class JasperReportsUtils {

    @SuppressWarnings("unchecked")
    public static JRDataSource convertReportData(Object value) {
        if (value instanceof JRDataSource) {
            return (JRDataSource) value;
        }
        if (value instanceof Collection) {
            return new JRBeanCollectionDataSource((Collection<?>) value);
        }
        throw new IllegalArgumentException(
                "Value [" + value + "] cannot be converted to a JRDataSource");
    }
}
