/**
 * Copyright (c) 2009 - 2012 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package org.candlepin.common.config;

import com.google.inject.Inject;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Sets the log4j logging levels dynamically based on values from the candlepin.conf file.
 * This removes the need to crack the log4j.properties file.
 *
 * Since we are actually adjusting logging configuration, we have to access the
 * underlying logger implementation instead of going through slf4j.
 *
 * See http://slf4j.org/faq.html#when
 */
public class LoggingConfig extends ConfigurationParser {

    public static final String PREFIX = "log4j.logger.";

    @Inject
    public LoggingConfig(Configuration config) {
        configure(config);
    }

    public void configure(Configuration config) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Map<String, Object> logLevels = config.getNamespaceMap(PREFIX);
        for (Entry<String, Object> entry : logLevels.entrySet()) {
            String key = entry.getKey().replace(PREFIX, "");
            lc.getLogger(key).setLevel(Level.toLevel((String) entry.getValue()));
        }
    }

    public String getPrefix() {
        return PREFIX;
    }
}
