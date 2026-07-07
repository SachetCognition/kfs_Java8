/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.FactoryBean;

/**
 * Supplies a no-op javax.persistence.EntityManager for Rice 2.1.10 KRAD beans
 * that require an EntityManager reference even though Rice runs on OJB (JPA
 * disabled). Spring 6's SharedEntityManagerBean can no longer wrap Rice's
 * javax-based NullEntityManagerFactory, so this stands in for it.
 */
public class NullJavaxEntityManagerFactoryBean implements FactoryBean<EntityManager> {

    @Override
    public EntityManager getObject() {
        return (EntityManager) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class<?>[]{EntityManager.class},
                new NullEntityManagerHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return EntityManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private static class NullEntityManagerHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            switch (method.getName()) {
                case "isOpen":
                    return Boolean.FALSE;
                case "close":
                case "clear":
                case "flush":
                    return null;
                case "toString":
                    return "Null javax.persistence.EntityManager (Rice JPA disabled)";
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "equals":
                    return proxy == args[0];
                default:
                    throw new UnsupportedOperationException(
                            "JPA is not enabled for Rice KRAD; EntityManager." + method.getName() + " is unavailable");
            }
        }
    }
}
