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
package org.kuali.kfs.module.ld.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kuali.kfs.module.ld.businessobject.AccountStatusBaseFunds;
import org.kuali.kfs.module.ld.businessobject.EmployeeFunding;
import org.kuali.kfs.module.ld.businessobject.LaborCalculatedSalaryFoundationTracker;
import org.kuali.kfs.module.ld.dataaccess.LaborCalculatedSalaryFoundationTrackerDao;

public class LaborCalculatedSalaryFoundationTrackerDaoJpa implements LaborCalculatedSalaryFoundationTrackerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LaborCalculatedSalaryFoundationTracker> findCSFTrackers(Map fieldValues, boolean isConsolidated) {
        return new ArrayList<>();
    }

    @Override
    public List<AccountStatusBaseFunds> findCSFTrackersAsAccountStatusBaseFunds(Map fieldValues, boolean isConsolidated) {
        return new ArrayList<>();
    }

    @Override
    public List<EmployeeFunding> findCSFTrackersAsEmployeeFunding(Map fieldValues, boolean isConsolidated) {
        return new ArrayList<>();
    }
}
