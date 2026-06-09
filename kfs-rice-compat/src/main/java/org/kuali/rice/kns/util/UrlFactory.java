package org.kuali.rice.kns.util;
import java.util.Properties;
public class UrlFactory {
    public static String parameterizeUrl(String baseUrl, Properties parameters) { return baseUrl; }
    public static String buildHtmlLink(String href, String displayText) { return "<a href='" + href + "'>" + displayText + "</a>"; }
}
