package org.springmodules.orm.ojb.support;
import org.springmodules.orm.ojb.PersistenceBrokerTemplate;
public class PersistenceBrokerDaoSupport {
    private PersistenceBrokerTemplate persistenceBrokerTemplate = new PersistenceBrokerTemplate();
    public PersistenceBrokerDaoSupport() {}
    public PersistenceBrokerTemplate getPersistenceBrokerTemplate() { return persistenceBrokerTemplate; }
    public void setPersistenceBrokerTemplate(PersistenceBrokerTemplate template) { this.persistenceBrokerTemplate = template; }
}
