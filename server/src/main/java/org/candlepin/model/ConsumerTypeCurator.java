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
package org.candlepin.model;

import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

/**
 * ConsumerTypeCurator
 */
public class ConsumerTypeCurator extends AbstractHibernateCurator<ConsumerType> {

    public ConsumerTypeCurator() {
        super(ConsumerType.class);
    }

    /**
     * lookup the ConsumerType by its label.
     *
     * @param label
     *            type to lookup
     * @return ConsumerType whose label matches the given label.
     */
    public ConsumerType lookupByLabel(String label) {
        return (ConsumerType) currentSession().createCriteria(
            ConsumerType.class).add(Restrictions.eq("label", label))
            .uniqueResult();
    }

    /**
     * look up consumer types by their labels
     * @param labels
     * @return all types matching the specified labels;
     */
    @SuppressWarnings("unchecked")
    public List<ConsumerType> lookupByLabels(Collection<String> labels) {
        return (List<ConsumerType>) currentSession().createCriteria(ConsumerType.class)
            .add(Restrictions.in("label", labels)).list();
    }

}
