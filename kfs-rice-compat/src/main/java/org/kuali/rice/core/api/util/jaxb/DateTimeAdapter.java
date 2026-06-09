package org.kuali.rice.core.api.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeAdapter extends XmlAdapter<String, Timestamp> {
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        Date d = sdf.parse(v);
        return new Timestamp(d.getTime());
    }

    @Override
    public String marshal(Timestamp v) throws Exception {
        if (v == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(v);
    }
}
