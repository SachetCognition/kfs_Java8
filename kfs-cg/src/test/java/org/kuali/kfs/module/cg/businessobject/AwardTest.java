package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AwardTest extends KfsUnitTestBase {

    private Award award;

    @BeforeEach
    void setUp() {
        award = new Award();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(award.getAwardProjectDirectors()).isNotNull();
        assertThat(award.getAwardFundManagers()).isNotNull();
        assertThat(award.getAwardAccounts()).isNotNull();
        assertThat(award.getAwardSubcontractors()).isNotNull();
        assertThat(award.getAwardOrganizations()).isNotNull();
    }

    @Test
    void testProposalNumber() {
        award.setProposalNumber(12345L);
        assertThat(award.getProposalNumber()).isEqualTo(12345L);
    }

    @Test
    void testAwardBeginningDate() {
        Date date = Date.valueOf("2024-01-01");
        award.setAwardBeginningDate(date);
        assertThat(award.getAwardBeginningDate()).isEqualTo(date);
    }

    @Test
    void testAwardEndingDate() {
        Date date = Date.valueOf("2025-12-31");
        award.setAwardEndingDate(date);
        assertThat(award.getAwardEndingDate()).isEqualTo(date);
    }

    @Test
    void testAwardEntryDateNull() {
        assertThat(award.getAwardEntryDate()).isNull();
    }

    @Test
    void testAwardTotalAmount() {
        KualiDecimal directCost = new KualiDecimal(50000);
        KualiDecimal indirectCost = new KualiDecimal(10000);
        award.setAwardDirectCostAmount(directCost);
        award.setAwardIndirectCostAmount(indirectCost);
        assertThat(award.getAwardTotalAmount()).isEqualTo(new KualiDecimal(60000));
    }

    @Test
    void testAwardTotalAmountWithNullDirect() {
        award.setAwardDirectCostAmount(null);
        award.setAwardIndirectCostAmount(new KualiDecimal(10000));
        assertThat(award.getAwardTotalAmount()).isNull();
    }

    @Test
    void testAwardTotalAmountWithNullIndirect() {
        award.setAwardDirectCostAmount(new KualiDecimal(50000));
        award.setAwardIndirectCostAmount(null);
        assertThat(award.getAwardTotalAmount()).isNull();
    }

    @Test
    void testAwardTotalAmountBothNull() {
        award.setAwardDirectCostAmount(null);
        award.setAwardIndirectCostAmount(null);
        assertThat(award.getAwardTotalAmount()).isNull();
    }

    @Test
    void testSetAwardTotalAmountDoesNothing() {
        award.setAwardDirectCostAmount(null);
        award.setAwardIndirectCostAmount(null);
        award.setAwardTotalAmount(new KualiDecimal(75000));
        // setter is a no-op, total is computed from direct + indirect
        assertThat(award.getAwardTotalAmount()).isNull();
    }

    @Test
    void testAwardAddendumNumber() {
        award.setAwardAddendumNumber("ADD-001");
        assertThat(award.getAwardAddendumNumber()).isEqualTo("ADD-001");
    }

    @Test
    void testAwardAllocatedUniversityComputingServicesAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        award.setAwardAllocatedUniversityComputingServicesAmount(amount);
        assertThat(award.getAwardAllocatedUniversityComputingServicesAmount()).isEqualTo(amount);
    }

    @Test
    void testFederalPassThroughFundedAmount() {
        KualiDecimal amount = new KualiDecimal(25000);
        award.setFederalPassThroughFundedAmount(amount);
        assertThat(award.getFederalPassThroughFundedAmount()).isEqualTo(amount);
    }

    @Test
    void testAwardEntryDate() {
        Date date = Date.valueOf("2024-03-01");
        award.setAwardEntryDate(date);
        assertThat(award.getAwardEntryDate()).isEqualTo(date);
    }

    @Test
    void testAgencyFutureAmounts() {
        KualiDecimal amt1 = new KualiDecimal(1000);
        KualiDecimal amt2 = new KualiDecimal(2000);
        KualiDecimal amt3 = new KualiDecimal(3000);
        award.setAgencyFuture1Amount(amt1);
        award.setAgencyFuture2Amount(amt2);
        award.setAgencyFuture3Amount(amt3);
        assertThat(award.getAgencyFuture1Amount()).isEqualTo(amt1);
        assertThat(award.getAgencyFuture2Amount()).isEqualTo(amt2);
        assertThat(award.getAgencyFuture3Amount()).isEqualTo(amt3);
    }

    @Test
    void testAwardDocumentNumber() {
        award.setAwardDocumentNumber("DOC-123");
        assertThat(award.getAwardDocumentNumber()).isEqualTo("DOC-123");
    }

    @Test
    void testAwardLastUpdateDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        award.setAwardLastUpdateDate(ts);
        assertThat(award.getAwardLastUpdateDate()).isEqualTo(ts);
    }

    @Test
    void testFederalPassThroughIndicator() {
        award.setFederalPassThroughIndicator(true);
        assertThat(award.getFederalPassThroughIndicator()).isTrue();

        award.setFederalPassThroughIndicator(false);
        assertThat(award.getFederalPassThroughIndicator()).isFalse();
    }

    @Test
    void testOldProposalNumber() {
        award.setOldProposalNumber("OLD-999");
        assertThat(award.getOldProposalNumber()).isEqualTo("OLD-999");
    }

    @Test
    void testAwardDirectCostAmount() {
        KualiDecimal amount = new KualiDecimal(40000);
        award.setAwardDirectCostAmount(amount);
        assertThat(award.getAwardDirectCostAmount()).isEqualTo(amount);
    }

    @Test
    void testAwardIndirectCostAmount() {
        KualiDecimal amount = new KualiDecimal(8000);
        award.setAwardIndirectCostAmount(amount);
        assertThat(award.getAwardIndirectCostAmount()).isEqualTo(amount);
    }

    @Test
    void testFederalFundedAmount() {
        KualiDecimal amount = new KualiDecimal(100000);
        award.setFederalFundedAmount(amount);
        assertThat(award.getFederalFundedAmount()).isEqualTo(amount);
    }

    @Test
    void testAwardCreateTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        award.setAwardCreateTimestamp(ts);
        assertThat(award.getAwardCreateTimestamp()).isEqualTo(ts);
    }

    @Test
    void testAwardClosingDate() {
        Date date = Date.valueOf("2025-06-30");
        award.setAwardClosingDate(date);
        assertThat(award.getAwardClosingDate()).isEqualTo(date);
    }

    @Test
    void testProposalAwardTypeCode() {
        award.setProposalAwardTypeCode("G");
        assertThat(award.getProposalAwardTypeCode()).isEqualTo("G");
    }

    @Test
    void testAwardStatusCode() {
        award.setAwardStatusCode("A");
        assertThat(award.getAwardStatusCode()).isEqualTo("A");
    }

    @Test
    void testLetterOfCreditFundCode() {
        award.setLetterOfCreditFundCode("LOC01");
        assertThat(award.getLetterOfCreditFundCode()).isEqualTo("LOC01");
    }

    @Test
    void testGrantDescriptionCode() {
        award.setGrantDescriptionCode("RES");
        assertThat(award.getGrantDescriptionCode()).isEqualTo("RES");
    }

    @Test
    void testAgencyNumber() {
        award.setAgencyNumber("AG001");
        assertThat(award.getAgencyNumber()).isEqualTo("AG001");
    }

    @Test
    void testFederalPassThroughAgencyNumber() {
        award.setFederalPassThroughAgencyNumber("FED001");
        assertThat(award.getFederalPassThroughAgencyNumber()).isEqualTo("FED001");
    }

    @Test
    void testAgencyAnalystName() {
        award.setAgencyAnalystName("John Doe");
        assertThat(award.getAgencyAnalystName()).isEqualTo("John Doe");
    }

    @Test
    void testAnalystTelephoneNumber() {
        award.setAnalystTelephoneNumber("555-1234");
        assertThat(award.getAnalystTelephoneNumber()).isEqualTo("555-1234");
    }

    @Test
    void testBillingFrequencyCode() {
        award.setBillingFrequencyCode("MNTH");
        assertThat(award.getBillingFrequencyCode()).isEqualTo("MNTH");
    }

    @Test
    void testAwardProjectTitle() {
        award.setAwardProjectTitle("Research Project Alpha");
        assertThat(award.getAwardProjectTitle()).isEqualTo("Research Project Alpha");
    }

    @Test
    void testAwardPurposeCode() {
        award.setAwardPurposeCode("R");
        assertThat(award.getAwardPurposeCode()).isEqualTo("R");
    }

    @Test
    void testActive() {
        award.setActive(true);
        assertThat(award.isActive()).isTrue();

        award.setActive(false);
        assertThat(award.isActive()).isFalse();
    }

    @Test
    void testAwardProjectDirectors() {
        List<AwardProjectDirector> directors = new ArrayList<>();
        AwardProjectDirector director = new AwardProjectDirector();
        directors.add(director);
        award.setAwardProjectDirectors(directors);
        assertThat(award.getAwardProjectDirectors()).hasSize(1);
    }

    @Test
    void testAwardAccounts() {
        List<AwardAccount> accounts = new ArrayList<>();
        AwardAccount account = new AwardAccount();
        accounts.add(account);
        award.setAwardAccounts(accounts);
        assertThat(award.getAwardAccounts()).hasSize(1);
    }

    @Test
    void testAwardSubcontractors() {
        List<AwardSubcontractor> subcontractors = new ArrayList<>();
        AwardSubcontractor sub = new AwardSubcontractor();
        subcontractors.add(sub);
        award.setAwardSubcontractors(subcontractors);
        assertThat(award.getAwardSubcontractors()).hasSize(1);
    }

    @Test
    void testAwardOrganizations() {
        List<AwardOrganization> orgs = new ArrayList<>();
        AwardOrganization org = new AwardOrganization();
        orgs.add(org);
        award.setAwardOrganizations(orgs);
        assertThat(award.getAwardOrganizations()).hasSize(1);
    }

    @Test
    void testAwardFundManagers() {
        List<AwardFundManager> managers = new ArrayList<>();
        AwardFundManager manager = new AwardFundManager();
        managers.add(manager);
        award.setAwardFundManagers(managers);
        assertThat(award.getAwardFundManagers()).hasSize(1);
    }

    @Test
    void testProposalAwardType() {
        ProposalAwardType type = new ProposalAwardType();
        award.setProposalAwardType(type);
        assertThat(award.getProposalAwardType()).isSameAs(type);
    }

    @Test
    void testAwardStatus() {
        AwardStatus status = new AwardStatus();
        award.setAwardStatus(status);
        assertThat(award.getAwardStatus()).isSameAs(status);
    }

    @Test
    void testGrantDescription() {
        GrantDescription desc = new GrantDescription();
        award.setGrantDescription(desc);
        assertThat(award.getGrantDescription()).isSameAs(desc);
    }

    @Test
    void testAgency() {
        Agency agency = new Agency();
        award.setAgency(agency);
        assertThat(award.getAgency()).isSameAs(agency);
    }

    @Test
    void testBillingFrequency() {
        BillingFrequency freq = new BillingFrequency();
        award.setBillingFrequency(freq);
        assertThat(award.getBillingFrequency()).isSameAs(freq);
    }

    @Test
    void testKimGroupNames() {
        award.setKimGroupNames("GROUP1;GROUP2");
        assertThat(award.getKimGroupNames()).isEqualTo("GROUP1;GROUP2");
    }

    @Test
    void testAwardPrimaryProjectDirectorWhenListEmpty() {
        award.setAwardProjectDirectors(new ArrayList<>());
        assertThat(award.getAwardPrimaryProjectDirector()).isNull();
    }

    @Test
    void testAwardPrimaryProjectDirectorWhenPrimaryExists() {
        List<AwardProjectDirector> directors = new ArrayList<>();
        AwardProjectDirector primary = new AwardProjectDirector();
        primary.setAwardPrimaryProjectDirectorIndicator(true);
        directors.add(primary);
        award.setAwardProjectDirectors(directors);
        assertThat(award.getAwardPrimaryProjectDirector()).isSameAs(primary);
    }

    @Test
    void testAwardPrimaryFundManagerWhenListEmpty() {
        award.setAwardFundManagers(new ArrayList<>());
        assertThat(award.getAwardPrimaryFundManager()).isNull();
    }

    @Test
    void testAwardPrimaryFundManagerWhenPrimaryExists() {
        List<AwardFundManager> managers = new ArrayList<>();
        AwardFundManager primary = new AwardFundManager();
        primary.setPrimaryFundManagerIndicator(true);
        managers.add(primary);
        award.setAwardFundManagers(managers);
        assertThat(award.getAwardPrimaryFundManager()).isSameAs(primary);
    }
}
