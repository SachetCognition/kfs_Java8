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
package org.apache.ojb.broker.platforms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.metadata.FieldDescriptor;
import org.apache.ojb.broker.util.sequence.AbstractSequenceManager;
import org.apache.ojb.broker.util.sequence.SequenceManagerException;

/**
 * MySQL sequence manager emulating sequences via AUTO_INCREMENT tables.
 * Replaces Rice's KualiMySQLSequenceManagerImpl, which navigates the result
 * set with ResultSet.first() — not permitted on the TYPE_FORWARD_ONLY result
 * sets returned by MySQL Connector/J 8.
 */
public class KfsMySQLSequenceManagerImpl extends AbstractSequenceManager {

    public KfsMySQLSequenceManagerImpl(PersistenceBroker broker) {
        super(broker);
    }

    @Override
    protected long getUniqueLong(FieldDescriptor field) throws SequenceManagerException {
        String sequenceName = calculateSequenceName(field);
        try {
            Connection connection = getBrokerForClass().serviceConnectionManager().getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO " + sequenceName + " VALUES (NULL);");
                try (ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
                    if (!resultSet.next()) {
                        throw new SequenceManagerException("No value returned for sequence: " + sequenceName);
                    }
                    return resultSet.getLong(1);
                }
            }
        } catch (SequenceManagerException e) {
            throw e;
        } catch (Exception e) {
            throw new SequenceManagerException("Unable to execute for sequence name: " + sequenceName, e);
        }
    }
}
