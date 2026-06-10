package org.kuali.rice.core.framework.persistence.ojb.dao;

public abstract class PlatformAwareDaoBaseOjb extends org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport implements org.kuali.rice.core.framework.persistence.dao.PlatformAwareDao {
    public PlatformAwareDaoBaseOjb() {}


    public org.kuali.rice.core.framework.persistence.platform.DatabasePlatform getDbPlatform() { return null; }
    public void setDbPlatform(org.kuali.rice.core.framework.persistence.platform.DatabasePlatform p0) {  }

    public org.springmodules.orm.ojb.PersistenceBrokerTemplate getPersistenceBrokerTemplate() { return new org.springmodules.orm.ojb.PersistenceBrokerTemplate(); }
}
