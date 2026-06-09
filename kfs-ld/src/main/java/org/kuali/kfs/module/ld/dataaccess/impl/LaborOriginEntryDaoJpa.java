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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kuali.kfs.gl.businessobject.OriginEntryFull;
import org.kuali.kfs.gl.businessobject.OriginEntryGroup;
import org.kuali.kfs.gl.businessobject.OriginEntryInformation;
import org.kuali.kfs.module.ld.businessobject.LaborOriginEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborOriginEntryDao;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class LaborOriginEntryDaoJpa implements LaborOriginEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    // -- LaborOriginEntryDao specific methods --

    @Override
    public Iterator<LaborOriginEntry> getEntriesByGroups(Collection<OriginEntryGroup> groups) {
        return new ArrayList<LaborOriginEntry>().iterator();
    }

    @Override
    public Iterator<Object[]> getConsolidatedEntriesByGroup(OriginEntryGroup group) {
        return new ArrayList<Object[]>().iterator();
    }

    @Override
    public int getCountOfEntriesInGroups(Collection<OriginEntryGroup> groups) {
        return 0;
    }

    @Override
    public Collection<LaborOriginEntry> testingLaborGetAllEntries() {
        return new ArrayList<>();
    }

    @Override
    public Iterator<LaborOriginEntry> getLaborEntriesByGroup(OriginEntryGroup oeg, int sort) {
        return new ArrayList<LaborOriginEntry>().iterator();
    }

    @Override
    public Collection getMatchingEntriesByCollection(Map searchCriteria) {
        return new ArrayList<>();
    }

    @Override
    public Collection<LaborOriginEntry> getEntryCollectionByGroup(OriginEntryGroup group) {
        return new ArrayList<>();
    }

    @Override
    public Collection getLaborBackupGroups(Date groupDate) {
        return new ArrayList<>();
    }

    @Override
    public Collection getLaborGroupsToBackup(Date groupDate) {
        return new ArrayList<>();
    }

    // -- OriginEntryDao methods --

    @Override
    public KualiDecimal getGroupTotal(Integer groupId, boolean isCredit) {
        return KualiDecimal.ZERO;
    }

    @Override
    public Integer getGroupCount(Integer groupId) {
        return 0;
    }

    @Override
    public Iterator getGroupCounts() {
        return new ArrayList<>().iterator();
    }

    @Override
    public void deleteEntry(OriginEntryInformation oe) {
        if (entityManager.contains(oe)) {
            entityManager.remove(oe);
        }
    }

    @Override
    public Iterator getDocumentsByGroup(OriginEntryGroup oeg) {
        return new ArrayList<>().iterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Iterator<T> getEntriesByGroup(OriginEntryGroup oeg, int sort) {
        return (Iterator<T>) new ArrayList<LaborOriginEntry>().iterator();
    }

    @Override
    public Iterator<OriginEntryFull> getBadBalanceEntries(Collection groups) {
        return new ArrayList<OriginEntryFull>().iterator();
    }

    @Override
    public Iterator getMatchingEntries(Map searchCriteria) {
        return new ArrayList<>().iterator();
    }

    @Override
    public void deleteMatchingEntries(Map searchCriteria) {
    }

    @Override
    public void deleteGroups(Collection<OriginEntryGroup> groups) {
    }

    @Override
    public OriginEntryFull getExactMatchingEntry(Integer entryId) {
        return null;
    }

    @Override
    public Iterator getSummaryByGroupId(Collection groupIdList) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Collection testingGetAllEntries() {
        return new ArrayList<>();
    }

    @Override
    public Iterator getPosterOutputSummaryByGroupId(Collection groups) {
        return new ArrayList<>().iterator();
    }
}
