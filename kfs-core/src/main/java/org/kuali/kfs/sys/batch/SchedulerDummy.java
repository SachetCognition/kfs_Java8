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
package org.kuali.kfs.sys.batch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.quartz.ListenerManager;
import org.quartz.Scheduler;

/**
 * No-op Scheduler proxy for use when use.quartz.scheduling=false.
 * Returns safe defaults for all Scheduler interface methods.
 * Updated for Quartz 2.x API (S-4A migration).
 */
public class SchedulerDummy {

    private static final Scheduler INSTANCE = createNoOpScheduler();

    public static Scheduler getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private static Scheduler createNoOpScheduler() {
        return (Scheduler) Proxy.newProxyInstance(
                Scheduler.class.getClassLoader(),
                new Class<?>[]{Scheduler.class},
                new InvocationHandler() {
                    private final ListenerManager listenerManager = createNoOpListenerManager();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        if ("getListenerManager".equals(name)) {
                            return listenerManager;
                        }
                        Class<?> returnType = method.getReturnType();
                        if (returnType == boolean.class) return false;
                        if (returnType == int.class) return 0;
                        if (returnType == List.class) return Collections.emptyList();
                        if (returnType == Set.class) return Collections.emptySet();
                        if (returnType == String.class && "getSchedulerName".equals(name)) return "NoOpScheduler";
                        return null;
                    }
                });
    }

    private static ListenerManager createNoOpListenerManager() {
        return (ListenerManager) Proxy.newProxyInstance(
                ListenerManager.class.getClassLoader(),
                new Class<?>[]{ListenerManager.class},
                (proxy, method, args) -> null);
    }
}
