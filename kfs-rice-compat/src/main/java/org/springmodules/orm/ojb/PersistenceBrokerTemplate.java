package org.springmodules.orm.ojb;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
public class PersistenceBrokerTemplate {
    public PersistenceBrokerTemplate() {}
    public Collection getCollectionByQuery(Query query) { return new ArrayList(); }
    public Object getObjectByQuery(Query query) { return null; }
    public int getCount(Query query) { return 0; }
    public Iterator getIteratorByQuery(Query query) { return Collections.emptyIterator(); }
    public Iterator getReportQueryIteratorByQuery(org.apache.ojb.broker.query.Query query) { return Collections.emptyIterator(); }
    public void store(Object obj) {}
    public void delete(Object obj) {}
    public void deleteByQuery(Query query) {}
    public void clearCache() {}
    public Object getObjectById(Class arg0, java.lang.Integer arg1) { return null; }
}
