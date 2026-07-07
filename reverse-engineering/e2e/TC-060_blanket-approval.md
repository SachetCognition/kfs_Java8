# TC-060 — Blanket Approval Bypasses Intermediate Validation

## Overview

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-060 |
| **URS Requirement** | URS-005 |
| **JIRA Sub-Task** | SS-126 (parent: SS-118 "URS Group 1 — Document Lifecycle") |
| **Document Under Test** | Transfer of Funds (TF) — representative two-sided FP doc with multi-node route path |
| **Objective** | Verify that when an authorized blanket-approver invokes blanket approve, the document's blanket-approve validation composite runs **no extra validations** (empty list) and the document reaches FINAL status, skipping all intermediate route nodes (Account, AccountingOrganizationHierarchy, SubFund, Award). |

## Background: What Blanket Approve Does in KEW

In Kuali Enterprise Workflow (KEW), **blanket approve** is a privileged action that:

1. **Skips all intermediate route nodes**: The document transitions directly from ENROUTE to FINAL (or PROCESSED → FINAL) without generating action requests at each role node. For the TF document this means the `Account`, `AccountingOrganizationHierarchy`, `SubFund`, and `Award` role nodes are bypassed entirely.
2. **Runs a separate validation composite**: Rather than the route-time validations (which include debit/credit balance checks, required accounting line counts), blanket approve dispatches an `AttributedBlanketApproveDocumentEvent` that maps to the `BlanketApproveDocument` composite bean — which in the default configuration contains **no validations**.
3. **Requires KIM permission**: Only principals holding the Rice `Blanket Approve Document` permission (PERM_ID 148 in the Rice permission table, template `KR-SYS: Blanket Approve Document`) for the applicable document type may invoke it. This is typically granted to the `KFS-SYS Workflow Administrator` role or similar.

## Preconditions & Test Data

### Roles / Users

| Actor | KIM Role / Permission | Purpose |
|-------|----------------------|---------|
| **Initiator** | Any user with `Initiate Document` permission for doc type `TF` | Creates and routes the document |
| **BlanketApprover** | User holding `Blanket Approve Document` permission for doc type `TF` (e.g. `KFS-SYS Workflow Administrator` or `KFS-SYS Operations`) | Performs blanket approve |
| **Non-BlanketApprover** | User who does NOT hold blanket-approve permission for `TF` | Negative test — verifies action is unavailable |

### Accounts & Object Codes

| Parameter | Value | Notes |
|-----------|-------|-------|
| **Chart** | `BL` (Bloomington) | Standard chart code |
| **Source Account** | `1031400` | Active, unrestricted, sufficient budget |
| **Target Account** | `1031420` | Active, unrestricted |
| **Object Code (From)** | `5000` (Transfer Out) | Expense-type object code, mandatory transfer |
| **Object Code (To)** | `5000` (Transfer In) | Income-type object code |
| **Amount** | `$1,000.00` | Must match on both sides for debit/credit balance |
| **Fiscal Year** | Current fiscal year | |
| **Sub-Fund Group** | (inherited from account) | Determines SubFund route node activation |

### System Parameters

| Parameter | Namespace | Value |
|-----------|-----------|-------|
| `OBJECT_TYPES` | `KFS-FP` / `TransferOfFunds` | Must allow the chosen object type |
| `FUND_GROUPS` | `KFS-FP` / `TransferOfFunds` | Must allow the account's fund group |

---

## Detailed Step-by-Step Actions

### Scenario A: Happy Path — Blanket Approve Skips Intermediate Nodes

| Step | Actor | Action | Route Node | Expected Result |
|------|-------|--------|------------|-----------------|
| 1 | Initiator | Navigate to Financial Processing → Transfer of Funds. Create new TF document. | — | New TF document in INITIATED status |
| 2 | Initiator | Enter description: "TC-060 blanket approval test" | — | Description saved |
| 3 | Initiator | Add **Source** (From) accounting line: Chart=BL, Account=1031400, Object=5000, Amount=$1,000.00 | — | Line added (passes `AddAccountingLine` validation) |
| 4 | Initiator | Add **Target** (To) accounting line: Chart=BL, Account=1031420, Object=5000, Amount=$1,000.00 | — | Line added |
| 5 | Initiator | Click **Save** | — | Document saved. `SaveDocument` validation runs `accountingLineGroupTotalsUnchangedValidation` only. Status: SAVED |
| 6 | Initiator | Click **Submit/Route** | AdHoc → Account | Route validation fires: `requiredAccountingLinesCountValidation` (source ≥1, target ≥1), `debitsAndCreditsBalanceValidation`, `transferTotalsBalanceValidation`, `fundGroupsBalancedValidation`. Status: ENROUTE. Action requests generated at `Account` node. |
| 7 | BlanketApprover | Open the document and click **Blanket Approve** | Account (current) | `AttributedBlanketApproveDocumentEvent` dispatched. `BlanketApproveDocument-DefaultValidation` composite fires — **empty list, no validations run**. KEW transitions document directly to FINAL, bypassing `Account`, `AccountingOrganizationHierarchy`, `SubFund`, `Award` nodes. |
| 8 | — | Verify document status | — | Document status = **FINAL**. All pending action requests cancelled. Route log shows blanket-approve action taken. |

### Scenario B: Blanket Approve from INITIATED (Before Route)

| Step | Actor | Action | Expected Result |
|------|-------|--------|-----------------|
| 1-4 | Initiator | Same as Scenario A steps 1-4 | Document with valid accounting lines |
| 5 | BlanketApprover | Click **Blanket Approve** directly (without routing first) | The `blanketApprove` action on `KualiAccountingDocumentActionBase` calls `checkSalesTaxRequiredAllLines` then delegates to `super.blanketApprove()`. KEW: document moves from INITIATED → ENROUTE → FINAL in one action. The **route** validation fires first (Rice infrastructure requirement), then blanket-approve composite (empty). Document lands at FINAL. |
| 6 | — | Verify document status | Status = FINAL. No intermediate action requests were ever generated. |

### Scenario C: Negative — Unauthorized User Cannot Blanket Approve

| Step | Actor | Action | Expected Result |
|------|-------|--------|-----------------|
| 1-6 | Initiator | Create and route TF document (same as Scenario A steps 1-6) | Document ENROUTE |
| 7 | Non-BlanketApprover | Open document | **Blanket Approve** button is NOT rendered (presentation controller `canBlanketApprove()` returns false for users without the KIM permission) |

### Scenario D: Contrast — Route Validation Catches Imbalance (Blanket Approve Would Not)

| Step | Actor | Action | Expected Result |
|------|-------|--------|-----------------|
| 1-2 | Initiator | Create new TF document | Document INITIATED |
| 3 | Initiator | Add Source line: $1,000.00 | Line added |
| 4 | Initiator | Add Target line: $500.00 (deliberate imbalance) | Line added |
| 5 | Initiator | Click **Route** | `DebitsAndCreditsBalanceValidation.validate()` fires → debits ($1,000) ≠ credits ($500) → returns false → `ERROR_DOCUMENT_BALANCE` added to `GlobalVariables.getMessageMap()` → route rejected |
| 6 | BlanketApprover | (Hypothetical) If blanket approve were invoked on same document | The blanket-approve composite is empty — it would NOT catch this imbalance. However, Rice infrastructure still runs route-level validation before the document can enter workflow, so in practice the imbalance would be caught at the route step that blanket-approve implicitly triggers. |

---

## Acceptance Criteria (Given/When/Then)

### AC-1: Blanket-Approve Validation Composite Is Empty

```gherkin
Given the Spring context loads FinancialSystemValidators.xml
When the bean "AccountingDocument-BlanketApproveDocument-DefaultValidation" is resolved
Then its "validations" property is an empty list (no child validation beans)
```

**Source verification**: `kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml:230-235`

### AC-2: TF Blanket-Approve Inherits Empty Default

```gherkin
Given the Spring context loads TransferOfFundsValidation.xml
When the bean "TransferOfFunds-blanketApproveDocumentValidation-parentBean" is resolved
Then it contains only a reference to "AccountingDocument-BlanketApproveDocument-DefaultValidation"
  And that parent has an empty validations list
```

**Source verification**: `kfs-core/src/main/resources/org/kuali/kfs/fp/document/validation/configuration/TransferOfFundsValidation.xml:96-101`

### AC-3: Route Validation Contains Substantive Checks (Contrast)

```gherkin
Given the Spring context loads TransferOfFundsValidation.xml
When the bean "TransferOfFunds-routeDocumentValidation-parentBean" is resolved
Then it contains validations:
  - requiredAccountingLinesCountValidation (source >= 1)
  - requiredAccountingLinesCountValidation (target >= 1)
  - debitsAndCreditsBalanceValidation
  - transferTotalsBalanceValidation
  - fundGroupsBalancedValidation
```

**Source verification**: `kfs-core/src/main/resources/org/kuali/kfs/fp/document/validation/configuration/TransferOfFundsValidation.xml:28-71`

### AC-4: Blanket Approve Event Is Dispatched Separately

```gherkin
Given a blanket-approve action is invoked on a TF document
When AccountingRuleEngineRuleBase.processCustomApproveDocumentBusinessRules() executes
Then it detects the event is an instance of BlanketApproveDocumentEvent (line 131)
  And creates an AttributedBlanketApproveDocumentEvent (line 132)
  And validates against the blanket-approve composite (not the route composite)
```

**Source verification**: `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/AccountingRuleEngineRuleBase.java:131-132`

### AC-5: Document Reaches FINAL Skipping Intermediate Nodes

```gherkin
Given a TF document is in ENROUTE status at route node "Account"
  And the TF route path is: AdHoc → Account → AccountingOrganizationHierarchy → SubFund → Award
When an authorized blanket-approver invokes blanket approve
Then the document status transitions to FINAL
  And no action requests are generated at AccountingOrganizationHierarchy, SubFund, or Award nodes
  And the route log records a "Blanket Approve" action
```

**Source verification (route path)**: `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml:816-823`

### AC-6: DebitsAndCreditsBalanceValidation NOT Invoked During Blanket Approve

```gherkin
Given a TF document with balanced source/target lines
When blanket approve is invoked
Then DebitsAndCreditsBalanceValidation.validate() is NOT called as part of the blanket-approve composite
  (it IS still called during the implicit route phase if the document was not already routed)
```

**Source verification**: `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/DebitsAndCreditsBalanceValidation.java:49-81` (the method itself) — absence from composite at `FinancialSystemValidators.xml:233`

### AC-7: KIM Authorization Controls Access

```gherkin
Given a user without "Blanket Approve Document" KIM permission for doc type TF
When they view an ENROUTE TF document
Then the Blanket Approve button is not available (canBlanketApprove returns false)
```

**Source verification (permission)**: `kfs-core/src/main/resources/org/kuali/kfs/db/upgrades/4.1.1_5.0/rice_server/rice_20_final_mysql.sql:1319` — `KRIM_PERM_T.PERM_ID = '148'` with NM='Blanket Approve RICE Document'

---

## Automation Notes

### Legacy Stack (KualiTestBase / DocumentTestUtils)

```java
/**
 * Integration test skeleton for TC-060.
 * Extends KualiTestBase which bootstraps Spring context + KFS test infrastructure.
 */
@ConfigureContext(session = UserNameFixture.khuntley) // blanket-approve user
public class TC060_BlanketApprovalBypassTest extends KualiTestBase {

    private DocumentService documentService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        documentService = SpringContext.getBean(DocumentService.class);
    }

    public void testBlanketApproveSkipsIntermediateValidation() throws Exception {
        // 1. Create TF document
        TransferOfFundsDocument tfDoc = DocumentTestUtils.createDocument(
            documentService, TransferOfFundsDocument.class);

        // 2. Add balanced accounting lines
        SourceAccountingLine source = new SourceAccountingLine();
        source.setChartOfAccountsCode("BL");
        source.setAccountNumber("1031400");
        source.setFinancialObjectCode("5000");
        source.setAmount(new KualiDecimal("1000.00"));
        source.setPostingYear(currentFiscalYear);
        tfDoc.addSourceAccountingLine(source);

        TargetAccountingLine target = new TargetAccountingLine();
        target.setChartOfAccountsCode("BL");
        target.setAccountNumber("1031420");
        target.setFinancialObjectCode("5000");
        target.setAmount(new KualiDecimal("1000.00"));
        target.setPostingYear(currentFiscalYear);
        tfDoc.addTargetAccountingLine(target);

        // 3. Blanket approve
        documentService.blanketApproveDocument(tfDoc, "TC-060 blanket approve", null);

        // 4. Assert FINAL status
        Document reloaded = documentService.getByDocumentHeaderId(tfDoc.getDocumentNumber());
        assertTrue("Document should be FINAL after blanket approve",
            reloaded.getDocumentHeader().getWorkflowDocument().isFinal());

        // 5. Assert no errors in message map (validation composite was empty)
        assertTrue("No validation errors expected",
            GlobalVariables.getMessageMap().hasNoErrors());
    }

    public void testBlanketApproveCompositeIsEmpty() {
        // Verify Spring wiring
        CompositeValidation blanketValidation = SpringContext.getBean(
            CompositeValidation.class,
            "TransferOfFunds-blanketApproveDocumentValidation");
        // The inner composite should have an empty validations list
        assertNotNull(blanketValidation);
        // Drill into the nested bean
        List<Validation> validations = blanketValidation.getValidations();
        // The only child is the Default which itself is empty
        for (Validation v : validations) {
            if (v instanceof CompositeValidation) {
                assertTrue("BlanketApprove default should have no validations",
                    ((CompositeValidation) v).getValidations().isEmpty());
            }
        }
    }
}
```

**Key assertions/oracles:**
- `WorkflowDocument.isFinal()` == true
- `GlobalVariables.getMessageMap().hasNoErrors()` == true
- Route log does NOT contain action requests for `Account`, `AccountingOrganizationHierarchy`, `SubFund`, `Award` nodes (can query via `KEWServiceLocator.getActionRequestService()`)
- GLPE sums: `debitAmount.compareTo(creditAmount) == 0` (verified pre-blanket-approve)

### Modernization Target (Java 25 LTS / Spring Boot 4 / JPA)

```java
@SpringBootTest
@Transactional
class TC060_BlanketApprovalBypassTest {

    @Autowired DocumentService documentService;
    @Autowired ValidationService validationService;
    @Autowired WorkflowEngine workflowEngine;

    @Test
    void blanketApprove_skipsIntermediateNodes_reachesFinal() {
        // Given
        TransferOfFundsDocument doc = createBalancedTFDocument();

        // When
        documentService.blanketApprove(doc, "TC-060");

        // Then
        assertThat(doc.getStatus()).isEqualTo(DocumentStatus.FINAL);
        assertThat(workflowEngine.getActionRequests(doc.getId()))
            .filteredOn(ar -> ar.getNodeName().equals("Account")
                || ar.getNodeName().equals("AccountingOrganizationHierarchy")
                || ar.getNodeName().equals("SubFund")
                || ar.getNodeName().equals("Award"))
            .allMatch(ActionRequest::isCancelled);
    }

    @Test
    void blanketApproveValidationComposite_hasNoValidations() {
        // Given
        ValidationComposite composite = validationService
            .getComposite("TransferOfFunds", DocumentEvent.BLANKET_APPROVE);

        // Then
        assertThat(composite.getValidations()).isEmpty();
    }

    @Test
    void routeValidationComposite_containsBalanceCheck() {
        ValidationComposite composite = validationService
            .getComposite("TransferOfFunds", DocumentEvent.ROUTE);

        assertThat(composite.getValidations())
            .anyMatch(v -> v instanceof DebitsAndCreditsBalanceValidation);
    }
}
```

**Error keys to assert on failure:**
- `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` = `"error.document.balance"` — would appear in MessageMap if debit/credit imbalance detected (only on route, never on blanket approve)

---

## Traceability Table

| TC Step | URS ID | Source File : Line | What Is Verified |
|---------|--------|-------------------|-----------------|
| A.7 (Blanket approve fires empty composite) | URS-005 | `FinancialSystemValidators.xml:230-235` | BlanketApprove composite has empty `<list>` |
| A.7 (Event dispatch) | URS-005 | `AccountingRuleEngineRuleBase.java:131-132` | `BlanketApproveDocumentEvent` dispatches to blanket composite |
| A.6 (Route validation contrast) | URS-005 | `TransferOfFundsValidation.xml:28-71` | Route composite includes debits/credits balance + line count |
| A.7 (TF-specific blanket composite) | URS-005 | `TransferOfFundsValidation.xml:96-101` | TF blanket approve references only the empty default |
| A.8 (Document reaches FINAL) | URS-005 | `FinancialProcessingTransactionalDocuments.xml:816-823` | TF route path: AdHoc→Account→OrgHierarchy→SubFund→Award; all skipped |
| D.5 (Debit/credit check detail) | URS-005 | `DebitsAndCreditsBalanceValidation.java:58-75` | `KualiDecimal` debit/credit accumulation and comparison |
| D.5 (Error key on failure) | URS-005 | `DebitsAndCreditsBalanceValidation.java:78` | `ERROR_DOCUMENT_BALANCE` put to `GlobalVariables.getMessageMap()` |
| A.7 (KEW action) | URS-005 | `KualiAccountingDocumentActionBase.java:770-778` | `blanketApprove()` checks sales tax then delegates to Rice |
| B.5 (Split nodes irrelevant for TF) | URS-005 | `SimpleBooleanSplitNode.java:35-53` | Split node calls `answerSplitNodeQuestion` — TF has no split, linear path |
| C.7 (KIM authorization) | URS-005 | `rice_20_final_mysql.sql:1319` | PERM_ID 148 = 'Blanket Approve RICE Document' |
| A.7 (AttributedBlanketApproveDocumentEvent) | URS-005 | `AttributedBlanketApproveDocumentEvent.java:23-41` | Event class with `"blanketApprove"` event name |
| A.7 (Base answerSplitNodeQuestion) | URS-005 | `FinancialSystemTransactionalDocumentBase.java:231-232` | Default throws `UnsupportedOperationException` if split node not handled |

---

## Open Questions

1. **Exact KIM role mapping for TF blanket approve**: The Rice permission `PERM_ID=148` is for Rice documents generically. KFS likely has a separate `Blanket Approve Document` permission scoped to `documentTypeName=TF` or the parent `FP` type. The specific role assignment could not be verified from code alone — it is configured in the database (KRIM_ROLE_PERM_T). Integration tests use `UserNameFixture` to simulate authorized users.

2. **Does Rice infrastructure still run route validation on blanket-approve of an INITIATED document?** The `DocumentServiceImpl.blanketApproveDocument()` in Rice calls `document.validateBusinessRules(new BlanketApproveDocumentEvent(...))` which dispatches to the blanket-approve composite. However, it also internally routes the document first, which may trigger route validation. The exact sequencing depends on the Rice 2.1.x implementation and whether the document was already in ENROUTE state.

3. **DV special case**: The `DisbursementVoucherDocumentPresentationController` explicitly returns `false` for `canBlanketApprove()` (line 41-42), meaning DV documents cannot be blanket-approved regardless of KIM permissions. This is a document-type-specific override. TF does NOT have such an override.
