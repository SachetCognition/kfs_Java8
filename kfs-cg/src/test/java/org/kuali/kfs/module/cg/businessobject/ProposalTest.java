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

class ProposalTest extends KfsUnitTestBase {

    private Proposal proposal;

    @BeforeEach
    void setUp() {
        proposal = new Proposal();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(proposal.getProposalSubcontractors()).isNotNull().isEmpty();
        assertThat(proposal.getProposalOrganizations()).isNotNull().isEmpty();
        assertThat(proposal.getProposalProjectDirectors()).isNotNull().isEmpty();
        assertThat(proposal.getProposalResearchRisks()).isNotNull().isEmpty();
    }

    @Test
    void testProposalNumber() {
        proposal.setProposalNumber(98765L);
        assertThat(proposal.getProposalNumber()).isEqualTo(98765L);
    }

    @Test
    void testProposalBeginningDate() {
        Date date = Date.valueOf("2024-01-15");
        proposal.setProposalBeginningDate(date);
        assertThat(proposal.getProposalBeginningDate()).isEqualTo(date);
    }

    @Test
    void testProposalEndingDate() {
        Date date = Date.valueOf("2025-01-14");
        proposal.setProposalEndingDate(date);
        assertThat(proposal.getProposalEndingDate()).isEqualTo(date);
    }

    @Test
    void testProposalTotalAmount() {
        KualiDecimal direct = new KualiDecimal(30000);
        KualiDecimal indirect = new KualiDecimal(5000);
        proposal.setProposalDirectCostAmount(direct);
        proposal.setProposalIndirectCostAmount(indirect);
        assertThat(proposal.getProposalTotalAmount()).isEqualTo(new KualiDecimal(35000));
    }

    @Test
    void testProposalTotalAmountWithNullDirect() {
        proposal.setProposalDirectCostAmount(null);
        proposal.setProposalIndirectCostAmount(new KualiDecimal(5000));
        assertThat(proposal.getProposalTotalAmount()).isNull();
    }

    @Test
    void testProposalTotalAmountWithNullIndirect() {
        proposal.setProposalDirectCostAmount(new KualiDecimal(30000));
        proposal.setProposalIndirectCostAmount(null);
        assertThat(proposal.getProposalTotalAmount()).isNull();
    }

    @Test
    void testProposalTotalAmountBothNull() {
        proposal.setProposalDirectCostAmount(null);
        proposal.setProposalIndirectCostAmount(null);
        assertThat(proposal.getProposalTotalAmount()).isNull();
    }

    @Test
    void testProposalDirectCostAmount() {
        KualiDecimal amount = new KualiDecimal(30000);
        proposal.setProposalDirectCostAmount(amount);
        assertThat(proposal.getProposalDirectCostAmount()).isEqualTo(amount);
    }

    @Test
    void testProposalIndirectCostAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        proposal.setProposalIndirectCostAmount(amount);
        assertThat(proposal.getProposalIndirectCostAmount()).isEqualTo(amount);
    }

    @Test
    void testProposalRejectedDate() {
        Date date = Date.valueOf("2024-02-01");
        proposal.setProposalRejectedDate(date);
        assertThat(proposal.getProposalRejectedDate()).isEqualTo(date);
    }

    @Test
    void testProposalLastUpdateDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        proposal.setProposalLastUpdateDate(ts);
        assertThat(proposal.getProposalLastUpdateDate()).isEqualTo(ts);
    }

    @Test
    void testProposalDueDate() {
        Date date = Date.valueOf("2024-03-15");
        proposal.setProposalDueDate(date);
        assertThat(proposal.getProposalDueDate()).isEqualTo(date);
    }

    @Test
    void testProposalTotalProjectAmount() {
        KualiDecimal amount = new KualiDecimal(100000);
        proposal.setProposalTotalProjectAmount(amount);
        assertThat(proposal.getProposalTotalProjectAmount()).isEqualTo(amount);
    }

    @Test
    void testProposalSubmissionDate() {
        Date date = Date.valueOf("2024-01-10");
        proposal.setProposalSubmissionDate(date);
        assertThat(proposal.getProposalSubmissionDate()).isEqualTo(date);
    }

    @Test
    void testProposalFederalPassThroughIndicator() {
        proposal.setProposalFederalPassThroughIndicator(true);
        assertThat(proposal.getProposalFederalPassThroughIndicator()).isTrue();

        proposal.setProposalFederalPassThroughIndicator(false);
        assertThat(proposal.getProposalFederalPassThroughIndicator()).isFalse();
    }

    @Test
    void testOldProposalNumber() {
        proposal.setOldProposalNumber("OLD-123");
        assertThat(proposal.getOldProposalNumber()).isEqualTo("OLD-123");
    }

    @Test
    void testGrantNumber() {
        proposal.setGrantNumber("GNT-456");
        assertThat(proposal.getGrantNumber()).isEqualTo("GNT-456");
    }

    @Test
    void testProposalClosingDate() {
        Date date = Date.valueOf("2025-12-31");
        proposal.setProposalClosingDate(date);
        assertThat(proposal.getProposalClosingDate()).isEqualTo(date);
    }

    @Test
    void testProposalAwardTypeCode() {
        proposal.setProposalAwardTypeCode("G");
        assertThat(proposal.getProposalAwardTypeCode()).isEqualTo("G");
    }

    @Test
    void testAgencyNumber() {
        proposal.setAgencyNumber("AG-789");
        assertThat(proposal.getAgencyNumber()).isEqualTo("AG-789");
    }

    @Test
    void testProposalStatusCode() {
        proposal.setProposalStatusCode("A");
        assertThat(proposal.getProposalStatusCode()).isEqualTo("A");
    }

    @Test
    void testFederalPassThroughAgencyNumber() {
        proposal.setFederalPassThroughAgencyNumber("FED-001");
        assertThat(proposal.getFederalPassThroughAgencyNumber()).isEqualTo("FED-001");
    }

    @Test
    void testCfdaNumber() {
        proposal.setCfdaNumber("12.345");
        assertThat(proposal.getCfdaNumber()).isEqualTo("12.345");
    }

    @Test
    void testProposalFellowName() {
        proposal.setProposalFellowName("Dr. Smith");
        assertThat(proposal.getProposalFellowName()).isEqualTo("Dr. Smith");
    }

    @Test
    void testProposalPurposeCode() {
        proposal.setProposalPurposeCode("R");
        assertThat(proposal.getProposalPurposeCode()).isEqualTo("R");
    }

    @Test
    void testProposalProjectTitle() {
        proposal.setProposalProjectTitle("Research Proposal XYZ");
        assertThat(proposal.getProposalProjectTitle()).isEqualTo("Research Proposal XYZ");
    }

    @Test
    void testActive() {
        proposal.setActive(true);
        assertThat(proposal.isActive()).isTrue();

        proposal.setActive(false);
        assertThat(proposal.isActive()).isFalse();
    }

    @Test
    void testProposalSubcontractors() {
        List<ProposalSubcontractor> subs = new ArrayList<>();
        ProposalSubcontractor sub = new ProposalSubcontractor();
        subs.add(sub);
        proposal.setProposalSubcontractors(subs);
        assertThat(proposal.getProposalSubcontractors()).hasSize(1);
    }

    @Test
    void testProposalOrganizations() {
        List<ProposalOrganization> orgs = new ArrayList<>();
        ProposalOrganization org = new ProposalOrganization();
        orgs.add(org);
        proposal.setProposalOrganizations(orgs);
        assertThat(proposal.getProposalOrganizations()).hasSize(1);
    }

    @Test
    void testProposalProjectDirectors() {
        List<ProposalProjectDirector> directors = new ArrayList<>();
        ProposalProjectDirector dir = new ProposalProjectDirector();
        directors.add(dir);
        proposal.setProposalProjectDirectors(directors);
        assertThat(proposal.getProposalProjectDirectors()).hasSize(1);
    }

    @Test
    void testProposalResearchRisks() {
        List<ProposalResearchRisk> risks = new ArrayList<>();
        ProposalResearchRisk risk = new ProposalResearchRisk();
        risks.add(risk);
        proposal.setProposalResearchRisks(risks);
        assertThat(proposal.getProposalResearchRisks()).hasSize(1);
    }

    @Test
    void testProposalAwardType() {
        ProposalAwardType type = new ProposalAwardType();
        proposal.setProposalAwardType(type);
        assertThat(proposal.getProposalAwardType()).isSameAs(type);
    }

    @Test
    void testProposalStatus() {
        ProposalStatus status = new ProposalStatus();
        proposal.setProposalStatus(status);
        assertThat(proposal.getProposalStatus()).isSameAs(status);
    }

    @Test
    void testProposalPurpose() {
        ProposalPurpose purpose = new ProposalPurpose();
        proposal.setProposalPurpose(purpose);
        assertThat(proposal.getProposalPurpose()).isSameAs(purpose);
    }

    @Test
    void testCfda() {
        CFDA cfda = new CFDA();
        proposal.setCfda(cfda);
        assertThat(proposal.getCfda()).isSameAs(cfda);
    }

    @Test
    void testRoutingOrg() {
        proposal.setRoutingOrg("ORG1");
        assertThat(proposal.getRoutingOrg()).isEqualTo("ORG1");
    }

    @Test
    void testRoutingChart() {
        proposal.setRoutingChart("UA");
        assertThat(proposal.getRoutingChart()).isEqualTo("UA");
    }
}
