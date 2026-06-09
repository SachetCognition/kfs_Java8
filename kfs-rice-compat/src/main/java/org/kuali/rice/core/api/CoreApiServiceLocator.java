package org.kuali.rice.core.api;

public class CoreApiServiceLocator {
    public CoreApiServiceLocator() {}

    public static final java.lang.String XML_EXPORTER_SERVICE = "";
    public static final java.lang.String XML_INGESTER_SERVICE = "";

    public static org.kuali.rice.core.api.impex.xml.XmlExporterService getXmlExporterService() { return null; }
    public static org.kuali.rice.core.api.impex.xml.XmlIngesterService getXmlIngesterService() { return null; }
    public static org.kuali.rice.core.api.encryption.EncryptionService getEncryptionService() { return null; }
    public static org.kuali.rice.core.api.datetime.DateTimeService getDateTimeService() { return null; }
    public static org.kuali.rice.core.api.mail.Mailer getMailer() { return null; }
}
