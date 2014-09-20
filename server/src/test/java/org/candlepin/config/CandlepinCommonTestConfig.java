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
package org.candlepin.config;

import org.candlepin.common.config.MapConfiguration;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

/**
 * CandlepinCommonTestConfig
 */
public class CandlepinCommonTestConfig extends MapConfiguration {

    public CandlepinCommonTestConfig() {
        super();
        loadProperties();
    }

    protected Map<String, String> loadProperties() {
        Map<String, String> properties = new TreeMap<String, String>();

        // set ssl cert/key path for testing
        try {
            String cert = getClass().getResource("candlepin-ca.crt").toURI().getPath();
            String key = getClass().getResource("candlepin-ca.key").toURI().getPath();

            setProperty(ConfigProperties.CA_CERT, cert);
            setProperty(ConfigProperties.CA_CERT_UPSTREAM,
                "target/test/resources/certs/upstream");
            setProperty(ConfigProperties.CA_KEY, key);
            setProperty(ConfigProperties.SYNC_WORK_DIR, "/tmp");
            setProperty(ConfigProperties.HORNETQ_LARGE_MSG_SIZE, "0");
        }
        catch (URISyntaxException e) {
            throw new RuntimeException("Error loading cert/key resources!", e);
        }

        return properties;
    }
}
