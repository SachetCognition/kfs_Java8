package org.springframework.ui.jasperreports;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Compatibility shim for the removed Spring JasperReportsUtils class.
 * Provides PDF rendering and data conversion using JasperReports API directly.
 */
@SuppressWarnings({"deprecation", "unchecked"})
public final class JasperReportsUtils {

    private JasperReportsUtils() {
    }

    public static JRDataSource convertReportData(Object value) throws IllegalArgumentException {
        if (value instanceof JRDataSource) {
            return (JRDataSource) value;
        }
        else if (value instanceof Collection) {
            return new JRBeanCollectionDataSource((Collection<?>) value);
        }
        else if (value instanceof Object[]) {
            return new JRBeanArrayDataSource((Object[]) value);
        }
        else {
            throw new IllegalArgumentException("Value [" + value + "] cannot be converted to a JRDataSource");
        }
    }

    public static void renderAsPdf(JasperReport report, Map parameters,
            JRDataSource dataSource, OutputStream outputStream) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }
}
