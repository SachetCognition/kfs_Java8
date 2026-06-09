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
package org.kuali.kfs.module.ar.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAwardAccount;
import org.kuali.kfs.module.ar.businessobject.AwardAccountObjectCodeTotalBilled;
import org.kuali.kfs.module.ar.dataaccess.AwardAccountObjectCodeTotalBilledDao;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.framework.persistence.jpa.criteria.QueryByCriteria.QueryByCriteriaType;

public class AwardAccountObjectCodeTotalBilledDaoJpa implements AwardAccountObjectCodeTotalBilledDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AwardAccountObjectCodeTotalBilled> getAwardAccountObjectCodeTotalBuildByProposalNumberAndAccount(List<ContractsAndGrantsBillingAwardAccount> awardAccounts) {
        if (awardAccounts == null || awardAccounts.isEmpty()) {
            return new ArrayList<AwardAccountObjectCodeTotalBilled>();
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AwardAccountObjectCodeTotalBilled> cq = cb.createQuery(AwardAccountObjectCodeTotalBilled.class);
        Root<AwardAccountObjectCodeTotalBilled> root = cq.from(AwardAccountObjectCodeTotalBilled.class);

        List<Predicate> orPredicates = new ArrayList<Predicate>();
        for (ContractsAndGrantsBillingAwardAccount awardAccount : awardAccounts) {
            Predicate chartPredicate = cb.equal(root.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE), awardAccount.getChartOfAccountsCode());
            Predicate accountPredicate = cb.equal(root.get(KFSPropertyConstants.ACCOUNT_NUMBER), awardAccount.getAccountNumber());
            Predicate proposalPredicate = cb.equal(root.get(KFSPropertyConstants.PROPOSAL_NUMBER), awardAccount.getProposalNumber());
            orPredicates.add(cb.and(chartPredicate, accountPredicate, proposalPredicate));
        }

        cq.where(cb.or(orPredicates.toArray(new Predicate[0])));
        TypedQuery<AwardAccountObjectCodeTotalBilled> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
