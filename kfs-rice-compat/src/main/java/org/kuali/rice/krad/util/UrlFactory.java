package org.kuali.rice.krad.util;

import java.util.Properties;

public class UrlFactory {
    private UrlFactory() {}

    public static String parameterizeUrl(String baseUrl, Properties params) {
        if (baseUrl == null) return "";
        if (params == null || params.isEmpty()) return baseUrl;
        StringBuilder sb = new StringBuilder(baseUrl);
        boolean first = !baseUrl.contains("?");
        for (String key : params.stringPropertyNames()) {
            sb.append(first ? "?" : "&");
            sb.append(key).append("=").append(params.getProperty(key));
            first = false;
        }
        return sb.toString();
    }

    public static String encode(String value) {
        if (value == null) return "";
        try { return java.net.URLEncoder.encode(value, "UTF-8"); }
        catch (Exception e) { return value; }
    }
}
