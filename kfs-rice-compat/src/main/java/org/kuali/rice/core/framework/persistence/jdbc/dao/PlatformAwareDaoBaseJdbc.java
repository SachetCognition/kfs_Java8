package org.kuali.rice.core.framework.persistence.jdbc.dao;

public abstract class PlatformAwareDaoBaseJdbc extends org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport implements org.kuali.rice.core.framework.persistence.dao.PlatformAwareDao {
    public PlatformAwareDaoBaseJdbc() {}


    public org.kuali.rice.core.framework.persistence.platform.DatabasePlatform getDbPlatform() { return null; }
    public void setDbPlatform(org.kuali.rice.core.framework.persistence.platform.DatabasePlatform p0) {  }
}
