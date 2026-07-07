# TC-001 — Transfer of Funds (TF) Happy-Path Lifecycle

- **Test Case ID:** TC-001
- **Slug:** transfer-of-funds-happy-path
- **JIRA:** SS-124 (parent Task SS-118 — "URS Group 1 — Document Lifecycle")
- **Document type under test:** `TF` (Transfer Of Funds), child of the `FP` (Financial Processing) document family
- **Repository / ref:** `SachetCognition/kfs_Java8` @ `master`
- **Linked URS IDs:** URS-001, URS-003, URS-004, URS-011, URS-012

> All `file:line` citations below were verified against the actual files on `master`. Where the inlined TC-001 spec's approximate line numbers drifted from the source, the corrected line numbers are noted inline (see the **Line-number reconciliation** callouts). The dominant correction: the spec's generic route-chain references (`FinancialSystemValidators.xml ~151-168`) are the *abstract template* chain; the **effective** route chain that actually runs for TF is the concrete override in `TransferOfFundsValidation.xml`. Both are cited.

---

## 1. Overview

The Transfer of Funds (TF) document moves cash between accounts. A source ("From") accounting line and a target ("To") accounting line are entered; the document must be balanced (total debits == total credits, to the cent) before it can route. On routing it traverses a linear KEW role-node path — **Account → AccountingOrganizationHierarchy → SubFund → Award** — and, once approved at every node, reaches status **FINAL** and generates posted GL entries.

This test validates the *complete document lifecycle* for the happy path: initiate → add balanced accounting lines → save → route → approve at each of the four role nodes in order → FINAL. It also anchors the negative/exception assertions that share the same validation code (unbalanced document → `ERROR_DOCUMENT_BALANCE`; missing source/target line → `SectionNoAccountingLines`).

### Document & routing facts (verified)

| Fact | Value | Source |
| --- | --- | --- |
| Document type name | `TF` | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml:796` |
| Parent doc type | `FP` | `FinancialProcessingTransactionalDocuments.xml:799` |
| Label | `Transfer Of Funds` | `FinancialProcessingTransactionalDocuments.xml:802-804` |
| Active | `true` | `FinancialProcessingTransactionalDocuments.xml:810-812` |
| Routing version | `2` | `FinancialProcessingTransactionalDocuments.xml:813-815` |
| Route path | `AdHoc → Account → AccountingOrganizationHierarchy → SubFund → Award` | `FinancialProcessingTransactionalDocuments.xml:816-823` |
| Route node qualifier resolver | `org.kuali.rice.krad.workflow.attribute.DataDictionaryQualifierResolver` (each role) | `FinancialProcessingTransactionalDocuments.xml:827-858` |
| Route node activation type | `P` (parallel, all-must-approve within node) | `FinancialProcessingTransactionalDocuments.xml` role blocks |

> **Line-number reconciliation (workflow XML):** the inlined spec's approximations (`TF ~794`, active `~810-811`, Account node `~818-819`, route path `~816-823`) are **accurate on `master`**. Exact values: `<documentType>` opens at line **794**, `<name>TF</name>` at **796**, `<active>true</active>` at **810-812**, and the `<routePath>` block runs **817-823** with the four role transitions on lines **818 (start→Account), 819 (Account→AccountingOrganizationHierarchy), 820 (AccountingOrganizationHierarchy→SubFund), 821 (SubFund→Award), 822 (Award, terminal)**.

### TF full route path (verified `FinancialProcessingTransactionalDocuments.xml:817-823`)

```xml
<routePath>
  <start name="AdHoc" nextNode="Account"/>                                  <!-- line 818 -->
  <role name="Account" nextNode="AccountingOrganizationHierarchy"/>         <!-- line 819 -->
  <role name="AccountingOrganizationHierarchy" nextNode="SubFund"/>         <!-- line 820 -->
  <role name="SubFund" nextNode="Award"/>                                   <!-- line 821 -->
  <role name="Award"/>                                                      <!-- line 822 (terminal) -->
</routePath>
```

Note: unlike `PCDO` (Procurement Card, which uses a `SimpleBooleanSplitNode` conditional split — see `FinancialProcessingTransactionalDocuments.xml:105-146`) and `BA` (Budget Adjustment, `RequiresFullApproval` split at `312-328`), **TF has no conditional split node** — its path is strictly linear. `TransferOfFundsDocument` therefore defines **no `answerSplitNodeQuestion(...)`** hook (see `kfs-core/src/main/java/org/kuali/kfs/fp/document/TransferOfFundsDocument.java`, full class 1-159 — only `customizeOffsetGeneralLedgerPendingEntry`, `customizeExplicitGeneralLedgerPendingEntry`, and `isDebit` are overridden).

---

## 2. Preconditions & Test Data

### Actors / roles
| Role | Purpose in this test | Notes |
| --- | --- | --- |
| Initiator / Fiscal Officer | Creates the TF, enters lines, saves, routes | Same person may be Fiscal Officer of both accounts for the happy path |
| Account role approver | Approves at the `Account` route node | Resolved via `DataDictionaryQualifierResolver` (`FinancialProcessingTransactionalDocuments.xml:827-834`) |
| AccountingOrganizationHierarchy approver | Approves at the `AccountingOrganizationHierarchy` node | `FinancialProcessingTransactionalDocuments.xml:835-842` |
| SubFund approver | Approves at the `SubFund` node | `FinancialProcessingTransactionalDocuments.xml:843-850` |
| Award approver | Approves at the terminal `Award` node | `FinancialProcessingTransactionalDocuments.xml:851-858` |

For the pure happy path a super-user / single approver holding all four qualified roles may be used so the document walks the whole path; the assertions below still verify the node **sequence**.

### Parameters
- TF document type must be **active** — `<active>true</active>` at `FinancialProcessingTransactionalDocuments.xml:810-812`.
- Current fiscal-year `SystemOptions` must define `financialObjectTypeTransferIncomeCd` and `financialObjectTypeTransferExpenseCd`; these drive GLPE object-type stamping in `TransferOfFundsDocument.customizeExplicitGeneralLedgerPendingEntry` (`TransferOfFundsDocument.java:106-121`).

### Concrete test data (balanced, single source + single target)
| Field | Source ("From") line | Target ("To") line |
| --- | --- | --- |
| Chart | `BL` | `BL` |
| Account | `1031400` | `1031420` |
| Object code | income/transfer object code, e.g. `1800` (allowed by TF object-code rule) | income/transfer object code, e.g. `1800` |
| Amount | `$100.00` | `$100.00` |
| Resulting debit/credit | **credit** $100.00 (source, per `isDebit` = `isDebitConsideringNothingPositiveOnly`) | **debit** $100.00 (target = opposite of source) |

The object codes must satisfy TF's income/expense-only rule — `TransferOfFundsDocument.isDebit` throws `IllegalStateException` if the line is neither income nor expense (`TransferOfFundsDocument.java:143-145`). Choose object codes whose object type is income or expense.

> Amounts are entered as positive; debit/credit *side* is derived, not typed. Source line side = `isDebitConsideringNothingPositiveOnly(...)`; target line side = the **negation** of that (`TransferOfFundsDocument.java:147-152`). This is what guarantees a single balanced source+target pair nets to zero.

---

## 3. Detailed Step-by-Step Actions

| # | Actor | Action | KEW routeNode / status | Grounding |
| --- | --- | --- | --- | --- |
| 1 | Fiscal Officer | Initiate a new `TF` document (`financialTransferOfFunds.do?methodToCall=docHandler`) | status `INITIATED`; sits before `AdHoc`/`start` | docHandler `FinancialProcessingTransactionalDocuments.xml:804-806`; start node `818` |
| 2 | Fiscal Officer | Add **1 source** line (BL-1031400, obj 1800, $100.00) | still pre-route | required-source rule `TransferOfFundsValidation.xml:31-39` |
| 3 | Fiscal Officer | Add **1 target** line (BL-1031420, obj 1800, $100.00) | still pre-route | required-target rule `TransferOfFundsValidation.xml:40-48` |
| 4 | Fiscal Officer | **Save** | status `SAVED`; save chain runs | save chain `TransferOfFundsValidation.xml:74-82` → `FinancialSystemValidators.xml:202-214` |
| 5 | Fiscal Officer | **Route** | route chain runs; on success enters `Account` node, status `ENROUTE` | route chain `TransferOfFundsValidation.xml:26-72`; enters Account `FinancialProcessingTransactionalDocuments.xml:818-819` |
| 6 | Account approver | **Approve** at `Account` | advances to `AccountingOrganizationHierarchy` | `FinancialProcessingTransactionalDocuments.xml:819`; approve chain `TransferOfFundsValidation.xml:84-92` |
| 7 | AcctgOrgHierarchy approver | **Approve** at `AccountingOrganizationHierarchy` | advances to `SubFund` | `FinancialProcessingTransactionalDocuments.xml:820` |
| 8 | SubFund approver | **Approve** at `SubFund` | advances to `Award` | `FinancialProcessingTransactionalDocuments.xml:821` |
| 9 | Award approver | **Approve** at `Award` (terminal) | no next node → status `FINAL` | `FinancialProcessingTransactionalDocuments.xml:822` |
| 10 | (system) | GLPEs posted on FINAL | GL pending entries flushed to GL | GLPE generation `DebitsAndCreditsBalanceValidation.java:53`; TF GLPE customization `TransferOfFundsDocument.java:88-122` |

At each approve step (6-9) the approve validation chain `AccountingDocument-ApproveDocument-DefaultValidation` runs (`FinancialSystemValidators.xml:216-228`) — for TF this is only `accountingLineGroupTotalsUnchangedValidation`, i.e. the approver cannot have silently changed line totals.

---

## 4. Acceptance Criteria (Given / When / Then)

Each criterion cites the exact `file:line` on `master` that it verifies.

### AC-1 — Route validation passes for a balanced doc with ≥1 source & ≥1 target (URS-001, URS-003)
- **Given** a `SAVED` TF with exactly one source line and one target line, balanced to the cent,
- **When** the Fiscal Officer routes it,
- **Then** the route composite validation succeeds and the document enters the `Account` route node (`ENROUTE`).

Verifies:
- Effective TF route chain (source-count ≥1, target-count ≥1, debits==credits, TF transfer-totals, fund-groups): `kfs-core/src/main/resources/org/kuali/kfs/fp/document/validation/configuration/TransferOfFundsValidation.xml:28-72`
  - required **source** ≥ 1: `TransferOfFundsValidation.xml:31-39`
  - required **target** ≥ 1: `TransferOfFundsValidation.xml:40-48`
  - debits/credits balance: `TransferOfFundsValidation.xml:49-55`
- Generic abstract template equivalent (the spec's `~151-168`): `kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml:148-178` (source count `151-159`, target count `160-168`, balance `169-175`)
- Enters Account node: `FinancialProcessingTransactionalDocuments.xml:818-819`
- Event→validation wiring (RouteDocumentEvent → `TransferOfFunds-routeDocumentValidation`): `kfs-core/src/main/resources/org/kuali/kfs/fp/document/datadictionary/TransferOfFundsDocument.xml:62-65`

> **Line-number reconciliation (validators):** the spec's `FinancialSystemValidators.xml ~151-168` correctly points at the *abstract* `AccountingDocument-RouteDocument-DefaultValidation` (bean opens line **148**, closes **178**). However TF does **not** consume that abstract bean directly — `TransferOfFundsDocument.xml:64` binds the RouteDocumentEvent to `TransferOfFunds-routeDocumentValidation`, whose concrete chain is `TransferOfFundsValidation.xml:28-72` and adds two TF-specific validations (transfer-totals `56-62`, fund-groups `63-69`) on top of the same three generic ones.

### AC-2 — Ordered node advancement to FINAL (URS-004, URS-011)
- **Given** approvals performed at each role node in order,
- **When** each approver approves,
- **Then** the document advances `Account → AccountingOrganizationHierarchy → SubFund → Award` and reaches `FINAL` after the terminal Award approval.

Verifies:
- Ordered transitions: `FinancialProcessingTransactionalDocuments.xml:818` (start→Account), `:819` (Account→AcctgOrgHierarchy), `:820` (AcctgOrgHierarchy→SubFund), `:821` (SubFund→Award), `:822` (Award terminal → FINAL)
- Role node definitions & parallel activation (`P`): `FinancialProcessingTransactionalDocuments.xml:827-858`

### AC-3 — Balanced debits/credits raise no balance error (URS-003, URS-012)
- **Given** balanced debits and credits (source credit $100.00 == target debit $100.00),
- **When** the document is routed (GLPEs generated),
- **Then** `DebitsAndCreditsBalanceValidation` returns `true` and **no** `ERROR_DOCUMENT_BALANCE` is added to the `MessageMap`.

Verifies:
- Balance comparison to the cent via `KualiDecimal.compareTo(...) == 0`: `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/DebitsAndCreditsBalanceValidation.java:75`
- Debit/credit bucketing loop (skips offset entries; `GL_CREDIT_CODE` → credit bucket, else debit): `DebitsAndCreditsBalanceValidation.java:62-73`
- Error only on imbalance: `DebitsAndCreditsBalanceValidation.java:77-79` (`putError(ACCOUNTING_LINE_ERRORS, ERROR_DOCUMENT_BALANCE)`)
- Constant values: `ERROR_DOCUMENT_BALANCE = "error.document.balance"` (`KFSKeyConstants.java:68`); `ACCOUNTING_LINE_ERRORS = "document.accountingLines"` (`KFSConstants.java:515`)

> **Line-number reconciliation (balance validation):** spec said `~75-79`; verified exactly — the `isValid = debitAmount.compareTo(creditAmount) == 0` line is **75** and the `putError(...ERROR_DOCUMENT_BALANCE)` is **77-79**.

### AC-4 — TF GLPE side derivation keeps a single pair balanced (URS-003)
- **Given** a source and a target line for the same amount,
- **When** GLPEs are generated,
- **Then** the source posts on one side and the target posts on the opposite side, so debit total == credit total.

Verifies:
- Source side derived, target side = negation of source: `TransferOfFundsDocument.java:146-152`
- Income/expense-only guard (throws if neither): `TransferOfFundsDocument.java:143-145`
- Explicit entry object-type stamping (transfer income/expense): `TransferOfFundsDocument.java:106-121`
- Offset entry balance-type = ACTUAL: `TransferOfFundsDocument.java:88-91`

---

## 5. Negative / Exception aspects (same code paths)

These are not the happy path but are gated by the same validators; include them as sibling test cases to prove the happy-path assertions are meaningful.

| Scenario | Expected result | Error key | Source |
| --- | --- | --- | --- |
| Route with **no source** line | route blocked | `error.document.sourceSectionNoAccountingLines` | count rule `TransferOfFundsValidation.xml:31-39`; key built in `RequiredAccountingLinesCountValidation.java:114`, error raised at `:51-53` |
| Route with **no target** line | route blocked | `error.document.targetSectionNoAccountingLines` | `TransferOfFundsValidation.xml:40-48`; `RequiredAccountingLinesCountValidation.java:51-53,114` |
| Route **unbalanced** (source $100 / target $90) | route blocked | `error.document.balance` (`ERROR_DOCUMENT_BALANCE`) | `DebitsAndCreditsBalanceValidation.java:75-79` |
| Route with mandatory-transfer totals not balanced | route blocked | `ERROR_DOCUMENT_TOF_MANDATORY_TRANSFERS_DO_NOT_BALANCE` | `TransferOfFundsTransferTotalsBalancedValidation.java:87-89` |
| Route with non-mandatory-transfer totals not balanced | route blocked | `ERROR_DOCUMENT_TOF_NON_MANDATORY_TRANSFERS_DO_NOT_BALANCE` | `TransferOfFundsTransferTotalsBalancedValidation.java:92-94` |
| Line with non-income/non-expense object code | rule error (illegal state) | `IllegalStateException` from debit determiner | `TransferOfFundsDocument.java:143-145` |
| **Blanket approve** a balanced TF | succeeds with no extra validations (bypasses per-node approvals to FINAL) | — (empty validation list) | `TransferOfFundsValidation.xml:96-102` → `FinancialSystemValidators.xml:230-235` (`<!-- NO EXTRA VALIDATIONS -->`) |

---

## 6. Automation Notes

### 6a. Legacy stack (Kuali Rice 2.1.x / KFS, Java 8)
- **Base class:** extend `org.kuali.kfs.sys.context.KualiTestBase` (Spring + OJB + KEW bootstrapped against the KFS schema). Use `DocumentTestUtils` / `AccountingDocumentTestUtils` to build a `TransferOfFundsDocument` with source+target lines.
- **Lifecycle drivers:**
  - `DocumentService.saveDocument(doc, ...)` → asserts SAVED (exercises `TransferOfFunds-saveDocumentValidation`).
  - `DocumentService.routeDocument(doc, ...)` → exercises `TransferOfFunds-routeDocumentValidation` (`TransferOfFundsValidation.xml:28-72`).
  - Walk nodes with `WorkflowDocumentService` / super-user approvals; assert node names via `doc.getDocumentHeader().getWorkflowDocument().getCurrentNodeNames()`.
- **Oracles / assertions:**
  - **Status:** `getWorkflowDocument().isSaved()`, `isEnroute()`, `isFinal()` after each stage.
  - **Node order:** after route → current node contains `"Account"`; after each approve → `"AccountingOrganizationHierarchy"`, then `"SubFund"`, then `"Award"`; after final approve → `isFinal()` true.
  - **Balance:** assert `GlobalVariables.getMessageMap()` has **no** error under `KFSConstants.ACCOUNTING_LINE_ERRORS` (`"document.accountingLines"`) with key `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` (`"error.document.balance"`).
  - **GLPE debit vs credit:** call `GeneralLedgerPendingEntryService.generateGeneralLedgerPendingEntries(doc)` then sum `doc.getGeneralLedgerPendingEntries()` — skip offset entries (`isTransactionEntryOffsetIndicator()`), bucket by `GL_CREDIT_CODE`, assert `debitTotal.compareTo(creditTotal) == 0` (mirrors `DebitsAndCreditsBalanceValidation.java:62-75`).
- **Negative tests:** build an unbalanced doc and assert `routeDocument` throws `ValidationException` / the MessageMap contains `ERROR_DOCUMENT_BALANCE`; build a source-only doc and assert `error.document.targetSectionNoAccountingLines`.

### 6b. Modernization target (Java 25 LTS / Spring Boot 4 / JPA)
- Replace `KualiTestBase` with `@SpringBootTest` + Testcontainers (MySQL/Postgres); replace OJB BOs with JPA entities for `AccountingLine` / `GeneralLedgerPendingEntry`.
- Model the route path as an explicit state machine / workflow service; assert the ordered transitions `Account → AccountingOrganizationHierarchy → SubFund → Award → FINAL` as enum states.
- Port `DebitsAndCreditsBalanceValidation` as a `Validator` bean returning a typed `ValidationResult`; keep `KualiDecimal` semantics using `BigDecimal` with scale 2 and `compareTo(...) == 0` (never `equals`, to avoid scale mismatch false-negatives).
- Assertions/oracles: HTTP status of route endpoint (200 vs 422), a structured error body carrying the same message keys (`error.document.balance`, `error.document.<group>SectionNoAccountingLines`), and a GLPE-sum assertion identical to the legacy one.

---

## 7. Traceability Table

| TC step / AC | URS ID | Source `file:line` |
| --- | --- | --- |
| Step 1 (initiate), doc active | URS-001 | `FinancialProcessingTransactionalDocuments.xml:794,796,810-812` |
| Step 2 (source line) / AC-1 | URS-001, URS-003 | `TransferOfFundsValidation.xml:31-39` |
| Step 3 (target line) / AC-1 | URS-001, URS-003 | `TransferOfFundsValidation.xml:40-48` |
| Step 4 (save) | URS-011 | `TransferOfFundsValidation.xml:74-82`; `FinancialSystemValidators.xml:202-214` |
| Step 5 (route) / AC-1 | URS-001, URS-003 | `TransferOfFundsValidation.xml:28-72`; `FinancialSystemValidators.xml:148-178`; `FinancialProcessingTransactionalDocuments.xml:818-819` |
| Steps 6-9 (ordered approvals → FINAL) / AC-2 | URS-004, URS-011 | `FinancialProcessingTransactionalDocuments.xml:818-822,827-858`; approve chain `TransferOfFundsValidation.xml:84-92` |
| Balanced → no balance error / AC-3 | URS-003, URS-012 | `DebitsAndCreditsBalanceValidation.java:62-79`; `KFSKeyConstants.java:68`; `KFSConstants.java:515` |
| GLPE side derivation / AC-4 | URS-003 | `TransferOfFundsDocument.java:88-91,106-121,143-158` |
| Event → validation mapping | URS-011 | `TransferOfFundsDocument.xml:62-93` |
| Negative: unbalanced | URS-003, URS-012 | `DebitsAndCreditsBalanceValidation.java:75-79` |
| Negative: missing source/target | URS-001 | `RequiredAccountingLinesCountValidation.java:51-53,114` |
| Blanket approve (no extra validations) | URS-011 | `TransferOfFundsValidation.xml:96-102`; `FinancialSystemValidators.xml:230-235` |

---

## 8. OPEN QUESTIONS

1. **URS text is not on `master`.** URS-001/003/004/011/012 live in `reverse-engineering/KFS_User_Requirements_Specification.md` on branch `devin/1783376079-urs-e2e-specs` (PR #72), not on `master`. The URS mappings above are inferred from the inlined TC-001 spec (lifecycle / balance / routing requirements). Confirm exact URS wording when that branch merges.
2. **Concrete chart/account/object-code seed data** (`BL / 1031400 / 1031420 / 1800`) is illustrative — the KFS demo COA + object-code tables are distributed via the (defunct) Kuali impex artifacts and are **not** in this repo (see repo blueprint `test` note). Real values must come from whatever COA the integration DB is seeded with; the only *hard* code constraint verified here is "object type must be income or expense" (`TransferOfFundsDocument.java:143-145`).
3. **Node-level approver identities / KIM role membership** are environment-specific (resolved by `DataDictionaryQualifierResolver`). This design asserts node *sequence*, not specific principals; a super-user walk is assumed for the happy path.
4. **GL posting on FINAL** (step 10) is asserted at the pending-entry (GLPE) level. Full batch GL posting (`poster` jobs) is out of scope for this document-lifecycle test and would require the batch scheduler + GL schema.
