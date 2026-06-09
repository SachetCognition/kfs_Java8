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

import java.net.URI;
import java.net.URLClassLoader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kuali.kfs.sys.KFSConstants;

public class Log4jConfigurer {
    private static final Logger LOG = LoggerFactory.getLogger(Log4jConfigurer.class);

    public static final void configureLogging(boolean doStartupStatsLogging) {
        String settingsFile = PropertyLoadingFactoryBean.getBaseProperty(KFSConstants.LOG4J_SETTINGS_FILE_KEY);
        if (settingsFile != null && !settingsFile.isEmpty()) {
            try {
                Configurator.reconfigure(URI.create(settingsFile));
            } catch (Exception e) {
                LOG.warn("Could not reconfigure Log4j from {}, using default configuration", settingsFile, e);
            }
        }
        printClasspath();
    }

    private static void printClasspath() {
        StringBuilder classpath = new StringBuilder("Classpath is:\n");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        if (classloader instanceof URLClassLoader) {
            java.net.URL[] urls = ((URLClassLoader) classloader).getURLs();
            for (int i = 0; i < urls.length; i++) {
                classpath.append(urls[i].getFile()).append("; ");
            }
        } else {
            String cp = System.getProperty("java.class.path");
            if (cp != null) {
                Arrays.stream(cp.split(System.getProperty("path.separator")))
                    .forEach(entry -> classpath.append(entry).append("; "));
            } else {
                classpath.append("(unavailable)");
            }
        }
        LOG.info(classpath.toString());
    }
}
