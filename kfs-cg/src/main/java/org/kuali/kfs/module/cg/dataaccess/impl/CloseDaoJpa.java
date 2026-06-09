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
package org.kuali.kfs.module.cg.dataaccess.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.module.cg.businessobject.Proposal;
import org.kuali.kfs.module.cg.dataaccess.CloseDao;
import org.kuali.kfs.module.cg.document.ProposalAwardCloseDocument;
import org.kuali.kfs.sys.KFSConstants;

/**
 * JPA/Hibernate implementation of {@link CloseDao}.
 */
public class CloseDaoJpa implements CloseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getMaxApprovedClose(Date currentSqlMidnight) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM ProposalAwardCloseDocument d " +
                "WHERE d.userInitiatedCloseDate = :closeDate " +
                "AND d.documentHeader.workflowDocumentStatusCode = :statusCode " +
                "ORDER BY d.documentNumber DESC",
                String.class);
        query.setParameter("closeDate", currentSqlMidnight);
        query.setParameter("statusCode", KFSConstants.DocumentStatusCodes.ENROUTE);
        query.setMaxResults(1);

        List<String> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public String getMostRecentClose(Date currentSqlMidnight) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM ProposalAwardCloseDocument d " +
                "WHERE d.userInitiatedCloseDate = :closeDate " +
                "AND d.documentHeader.workflowDocumentStatusCode = :statusCode " +
                "ORDER BY d.documentNumber DESC",
                String.class);
        query.setParameter("closeDate", currentSqlMidnight);
        query.setParameter("statusCode", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setMaxResults(1);

        List<String> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Collection<Proposal> getProposalsToClose(ProposalAwardCloseDocument c) {
        TypedQuery<Proposal> query = entityManager.createQuery(
                "SELECT p FROM Proposal p " +
                "WHERE p.proposalClosingDate IS NULL " +
                "AND p.proposalSubmissionDate <= :closeDate " +
                "AND p.proposalStatusCode <> :excludedStatus",
                Proposal.class);
        query.setParameter("closeDate", c.getCloseOnOrBeforeDate());
        query.setParameter("excludedStatus", "U");

        return query.getResultList();
    }

    @Override
    public Collection<Award> getAwardsToClose(ProposalAwardCloseDocument c) {
        TypedQuery<Award> query = entityManager.createQuery(
                "SELECT a FROM Award a " +
                "WHERE a.awardClosingDate IS NULL " +
                "AND a.awardEntryDate <= :closeDate " +
                "AND a.awardStatusCode <> :excludedStatus",
                Award.class);
        query.setParameter("closeDate", c.getCloseOnOrBeforeDate());
        query.setParameter("excludedStatus", "U");

        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
