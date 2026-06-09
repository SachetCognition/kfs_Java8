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
package org.kuali.kfs.module.tem.dataaccess.impl;

import static org.kuali.kfs.module.tem.TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.ImportedExpense;
import org.kuali.kfs.module.tem.businessobject.PerDiem;
import org.kuali.kfs.module.tem.businessobject.TravelAdvance;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TEMReimbursementDocument;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.document.TravelEntertainmentDocument;
import org.kuali.kfs.module.tem.document.TravelReimbursementDocument;
import org.kuali.kfs.module.tem.document.TravelRelocationDocument;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSConstants.DocumentStatusCodes;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.krad.util.ObjectUtils;

public class TravelDocumentDaoJpa implements TravelDocumentDao {

    public static Logger LOG = Logger.getLogger(TravelDocumentDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TravelDocument> findDocuments(final Class<?> travelDocumentClass, final String travelDocumentNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(travelDocumentClass);
        Root root = cq.from(travelDocumentClass);
        cq.where(cb.equal(root.get("travelDocumentIdentifier"), travelDocumentNumber));

        List<TravelDocument> retval = new ArrayList<TravelDocument>();
        retval.addAll(entityManager.createQuery(cq).getResultList());
        return retval;
    }

    @Override
    public List<String> findDocumentNumbers(final Class<?> travelDocumentClass, final String travelDocumentNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(travelDocumentClass);
        Root root = cq.from(travelDocumentClass);
        cq.where(cb.equal(root.get("travelDocumentIdentifier"), travelDocumentNumber));

        List<String> retval = new ArrayList<String>();
        for (Object doc : entityManager.createQuery(cq).getResultList()) {
            retval.add(((TravelDocument) doc).getDocumentNumber());
        }
        return retval;
    }

    @Override
    public List<PerDiem> findEffectivePerDiems(int primaryDestinationId, java.sql.Date effectiveDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PerDiem> cq = cb.createQuery(PerDiem.class);
        Root<PerDiem> root = cq.from(PerDiem.class);

        Predicate destPred = cb.equal(root.get("primaryDestinationId"), primaryDestinationId);

        Predicate dateBetween = cb.and(
            cb.greaterThanOrEqualTo(root.<java.sql.Date>get("effectiveToDate"), effectiveDate),
            cb.lessThanOrEqualTo(root.<java.sql.Date>get("effectiveFromDate"), effectiveDate)
        );
        Predicate dateNull = cb.isNull(root.get("effectiveToDate"));
        Predicate dateOr = cb.or(dateBetween, dateNull);

        cq.where(destPred, dateOr);

        TypedQuery<PerDiem> query = entityManager.createQuery(cq);
        return new ArrayList<PerDiem>(query.getResultList());
    }

    @Override
    public List<TravelAdvance> getOutstandingTravelAdvanceByInvoice(Set<String> arInvoiceDocNumbers) {
        if (ObjectUtils.isNull(arInvoiceDocNumbers) || arInvoiceDocNumbers.isEmpty()) {
            return new ArrayList<TravelAdvance>();
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TravelAdvance> cq = cb.createQuery(TravelAdvance.class);
        Root<TravelAdvance> root = cq.from(TravelAdvance.class);

        cq.where(
            root.get("arInvoiceDocNumber").in(arInvoiceDocNumbers),
            cb.isNull(root.get("taxRamificationNotificationDate"))
        );

        TypedQuery<TravelAdvance> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Object[] findLatestTaxableRamificationNotificationDate() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<java.sql.Date> cq = cb.createQuery(java.sql.Date.class);
        Root<TravelAdvance> root = cq.from(TravelAdvance.class);

        cq.select(cb.greatest(root.<java.sql.Date>get("taxRamificationNotificationDate")));
        cq.where(cb.isNotNull(root.get("taxRamificationNotificationDate")));

        TypedQuery<java.sql.Date> query = entityManager.createQuery(cq);
        List<java.sql.Date> results = query.getResultList();
        if (results != null && !results.isEmpty() && results.get(0) != null) {
            return new Object[] { results.get(0) };
        }
        return null;
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getReimbursementDocumentsByHeaderStatus(String statusCode, boolean immediatesOnly) {
        return getTravelDocumentsByHeaderStatus(TravelReimbursementDocument.class, statusCode, immediatesOnly, "travelPayment");
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getRelocationDocumentsByHeaderStatus(String statusCode, boolean immediatesOnly) {
        return getTravelDocumentsByHeaderStatus(TravelRelocationDocument.class, statusCode, immediatesOnly, "travelPayment");
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getEntertainmentDocumentsByHeaderStatus(String statusCode, boolean immediatesOnly) {
        return getTravelDocumentsByHeaderStatus(TravelEntertainmentDocument.class, statusCode, immediatesOnly, "travelPayment");
    }

    @Override
    public Collection<? extends TravelAuthorizationDocument> getAuthorizationsAndAmendmentsByHeaderStatus(String statusCode, boolean immediatesOnly) {
        List<TravelAuthorizationDocument> documents = new ArrayList<TravelAuthorizationDocument>();
        documents.addAll(getTravelDocumentsByHeaderStatus(TravelAuthorizationDocument.class, statusCode, immediatesOnly, "advanceTravelPayment"));
        return documents;
    }

    @SuppressWarnings("unchecked")
    protected <T extends TravelDocument> Collection<T> getTravelDocumentsByHeaderStatus(Class<T> documentClazz, String statusCode, boolean immediatesOnly, String travelPaymentProperty) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(documentClazz);
        Root<T> root = cq.from(documentClazz);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get("documentHeader").get("financialDocumentStatusCode"), statusCode));
        predicates.add(cb.equal(root.get(travelPaymentProperty).get("paymentMethodCode"), KFSConstants.PaymentSourceConstants.PAYMENT_METHOD_CHECK));

        if (immediatesOnly) {
            predicates.add(cb.equal(root.get(travelPaymentProperty).get("immediatePaymentIndicator"), Boolean.TRUE));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getReimbursementDocumentsNeedingCorporateCardExtraction() {
        return getUnExtractedCorporateCardTravelDocuments(TravelReimbursementDocument.class);
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getEntertainmentDocumentsNeedingCorporateCardExtraction() {
        return getUnExtractedCorporateCardTravelDocuments(TravelEntertainmentDocument.class);
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> getRelocationDocumentsNeedingCorporateCardExtraction() {
        return getUnExtractedCorporateCardTravelDocuments(TravelRelocationDocument.class);
    }

    @SuppressWarnings("unchecked")
    protected <T extends TravelDocument> Collection<T> getUnExtractedCorporateCardTravelDocuments(Class<T> documentClazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(documentClazz);
        Root<T> root = cq.from(documentClazz);

        List<String> statusCodes = new ArrayList<String>();
        statusCodes.add(KFSConstants.DocumentStatusCodes.Payments.EXTRACTED);
        statusCodes.add(KFSConstants.DocumentStatusCodes.APPROVED);

        Predicate statusPred = root.get("documentHeader").get("financialDocumentStatusCode").in(statusCodes);
        Predicate nullExtract = cb.isNull(root.get("corporateCardPaymentExtractDate"));

        Subquery<String> subquery = cq.subquery(String.class);
        Root<ImportedExpense> expRoot = subquery.from(ImportedExpense.class);
        subquery.select(expRoot.<String>get("documentNumber"));
        subquery.where(
            cb.equal(expRoot.get("cardType"), TemConstants.TRAVEL_TYPE_CORP),
            cb.equal(expRoot.get("expenseLineTypeCode"), TemConstants.EXPENSE_IMPORTED),
            cb.equal(expRoot.get("historicalTravelExpense").get("creditCardAgency").get("paymentIndicator"), Boolean.TRUE)
        );

        cq.where(statusPred, nullExtract, root.get("documentNumber").in(subquery));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Collection<? extends TEMReimbursementDocument> findMatchingTrips(Integer temProfileId, Timestamp tripBegin, Timestamp tripEnd) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TravelReimbursementDocument> cq = cb.createQuery(TravelReimbursementDocument.class);
        Root<TravelReimbursementDocument> root = cq.from(TravelReimbursementDocument.class);

        Predicate profileAndBegin = cb.and(
            cb.equal(root.get("temProfileId"), temProfileId),
            cb.equal(root.get("tripBegin"), tripBegin)
        );
        Predicate endPred = cb.equal(root.get("tripEnd"), tripEnd);
        Predicate matchingTrip = cb.or(profileAndBegin, endPred);
        Predicate notInitiated = cb.not(root.get("documentHeader").get("financialDocumentStatusCode").in(Arrays.asList(DocumentStatusCodes.INITIATED)));

        cq.where(matchingTrip, notInitiated);

        return entityManager.createQuery(cq).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
