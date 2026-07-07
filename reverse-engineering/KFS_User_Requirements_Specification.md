# Kuali Financial System (KFS) — User Requirements Specification (URS)

> Grounded reverse-engineering artifact for rebuilding KFS on a modern Java stack (Java 25 LTS / Spring Boot 4 / Spring Framework 7). Every requirement cites the exact source file and line range it was derived from. This URS is a companion to `reverse-engineering/KFS_Reverse_Engineering_Specification.md` and to `reverse-engineering/KFS_E2E_Test_Specifications.md`.

---

## Document Control

| Property | Value |
|---|---|
| **Title** | KFS User Requirements Specification |
| **Version** | 1.0 |
| **Status** | Draft for review |
| **Author** | Reverse-engineering effort (automated, code-grounded) |
| **Date** | 2026-07-06 |
| **Source system** | Kuali Financial System 6.0.1-SNAPSHOT (`pom.xml:43`), Java 8, Kuali Rice 2.1.9 |
| **Target system** | Java 25 LTS / Spring Boot 4 (Spring Framework 7), JPA (Hibernate ORM 7 / Jakarta EE 11), pluggable workflow engine |
| **Scope** | `kfs-core` (SYS/COA/GL/FP/PDP/VND/SEC) foundational document lifecycle, accounting-line entry & balancing, conditional approval routing, allowed-value enforcement, authorization, batch/GL posting, reporting; representative depth into `kfs-ar` and `kfs-tem`. |
| **Method** | Direct filesystem reading of KEW workflow XML, Spring validation configuration XML, and `*Validation`/`*Document`/service Java classes. No reliance on partial index content. |

### References to source artifacts

| Ref | Artifact | Path |
|---|---|---|
| R1 | Reverse Engineering Specification | `reverse-engineering/KFS_Reverse_Engineering_Specification.md` (branch `devin/1783373876-reverse-engineering-artifacts`) |
| R2 | Developer Guide | `DEVELOPER_GUIDE.md` |
| R3 | FP transactional workflow | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml` |
| R4 | Chart maintenance workflow | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/ChartMaintenanceDocuments.xml` |
| R5 | AR transactional workflow | `kfs-core/src/main/config/workflow/010_accounts_receivable/AccountsReceivableTransactionalDocuments.xml` |
| R6 | TEM transactional workflow | `kfs-core/src/main/config/workflow/120_travel_and_entertainment/TemTransactionalDocuments.xml` |
| R7 | Core validators | `kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml` |
| R8 | Debit/credit service | `kfs-core/src/main/java/org/kuali/kfs/sys/document/service/impl/DebitDeterminerServiceImpl.java` |

---

## 1. Introduction

### 1.1 Purpose

This URS captures **what users need the system to do**, expressed as numbered, traceable requirements derived from the as-is KFS implementation. It is written so that a modernization team can rebuild equivalent behavior on Spring Boot 4 without access to the running legacy system, and so that the companion E2E test specification can verify each requirement.

### 1.2 System context

KFS is a document-centric financial ERP for higher education. Every financial or maintenance action is a **Document** that is created, saved, routed through **Kuali Enterprise Workflow (KEW)**, approved by roles resolved through **Kuali Identity Management (KIM)**, and — for financial documents — posted to the **General Ledger** as balanced pending entries (GLPEs). The prior reverse-engineering spec (R1) documents the full architecture; this URS focuses on user-facing requirements.

### 1.3 MoSCoW priority key

- **M** — Must have (core, non-negotiable behavior present in the legacy system)
- **S** — Should have (important, strongly implied by the code)
- **C** — Could have (config-driven or optional behavior)
- **W** — Won't have this release (explicitly out of scope / legacy-only)

### 1.4 Target-stack note (Java 25 LTS)

The functional requirements in this document are **JVM-version-independent** — they describe business behavior (balancing, routing, allowed-value gating, authorization) derived from the legacy code, and are unaffected by the choice of target JDK. Only the *target-state* mapping changes when modernizing to **Java 25 LTS** rather than Java 21:

- **Framework baseline:** Spring Boot 3.2 tops out at JDK 21 and 3.4/3.5 at JDK 23/24; full Java 25 support lands with **Spring Boot 4.0 / Spring Framework 7** (baseline JDK 17, tested on 25). Hence the target is Spring Boot 4, not a literal "Spring Boot 3" number bump. This cascades to **Hibernate ORM 7**, **Jakarta EE 11**, and JDK-25-capable build tooling (Gradle 9 / recent Maven compiler plugin; class-file major version 69).
- **Legacy-library risk (the real migration hazard):** Apache OJB, CGLIB, and older ByteBuddy/Mockito/Kuali Rice lean on `sun.misc.Unsafe` (deprecated-for-removal in JDK 23+) and deep reflective access. The OJB→JPA rewrite removes most of this, but any retained dependency must have a JDK-25-capable release.
- **Removed/disabled platform features:** the Security Manager is disabled/removed; finalization, dynamic agent loading, and unrestricted JNI now warn/fail (`--enable-native-access` required). Audit legacy code paths that rely on these.
- **Runtime:** Generational ZGC is default — relevant to the batch/GL-posting performance profile.
- **Additive opportunities (not requirements):** virtual threads for batch/GL and PDF generation; records for accounting-line/GLPE DTOs; sealed interfaces + pattern-matching `switch` to model document types and `SimpleBooleanSplitNode` branches; scoped values (finalized in JDK 25) for workflow/request context.
- The `KualiDecimal → BigDecimal` (scale 2, `compareTo` never `equals`) money-correctness guidance is unchanged.

---

## 2. User Personas / Roles

Roles are derived from KEW `<role>` route nodes in the workflow XML (qualifier resolution via `org.kuali.rice.krad.workflow.attribute.DataDictionaryQualifierResolver`) and from KIM role services.

| ID | Persona | Derived from (role node / source) | Primary responsibilities |
|---|---|---|---|
| P1 | **Initiator / Fiscal Officer** | `Account` role node — R3:317, R3:401, R3:468, R3:819; R4:538 | Create/save documents; first approval at the account/fiscal-officer node |
| P2 | **Organization Reviewer** | `AccountingOrganizationHierarchy` / `OrganizationHierarchy` — R3:318, R3:402, R4:509, R4:555 | Org-hierarchy content approval |
| P3 | **Sub-Fund Reviewer** | `SubFund` role node — R3:319, R3:403, R4:539 | Sub-fund-group-level approval |
| P4 | **Award / Funds Manager** | `Award` role node — R3:320, R3:404; `FundsManager` — R5:361, R5:377 | Award/grant approval; C&G invoice approval |
| P5 | **Chart Manager** | `Chart` role node — R4:541, R4:579 | Final chart-level approval on Account maintenance |
| P6 | **Traveler** | `Traveler` role node — R6:144, R6:230 | Initiate/attest travel documents |
| P7 | **Tax / Travel Manager** | `Travel` role node — R6:211; TEM `answerSplitNodeQuestion` — `kfs-tem/.../TravelRelocationDocument.java:301` | Tax/travel-manager review of disbursements |
| P8 | **Approver (generic)** | any `<role>`/`<split>` branch requiring action | Approve/disapprove at an assigned route node |
| P9 | **Auto-Approve / Electronic-Payment agent** | `AutoApprove` — R3:754; `ElectronicPayment` — R5:78 | System-routed conditional nodes |
| P10 | **System / Batch operator** | GL scrubber/poster & batch jobs (R1 §6) | Run scheduled GL/PDP batch, parameter config |

---

## 3. User Requirements

Requirement fields: **Statement**, **Rationale**, **Source** (file:line), **Priority** (MoSCoW), **Preconditions**.

### 3.1 Document lifecycle (initiate / save / route / approve)

#### URS-001 — Initiate a financial document
- **Statement:** A user in an initiator-capable role shall be able to create a new instance of any active document type and receive a system-assigned document number in `SAVED`/`INITIATED` state.
- **Rationale:** Every workflow `routePath` begins at `<start name="AdHoc" …>` — R3:314, R5:50, R6:141.
- **Source:** R3:312-314; R6:139-141.
- **Priority:** M
- **Preconditions:** Document type `<active>true</active>` (e.g. R3:306-308).

#### URS-002 — Save a document without routing
- **Statement:** A user shall be able to save an in-progress document; on save the system shall run save-scoped validation only (accounting-line group totals unchanged), not full route validation.
- **Rationale:** `AccountingDocument-SaveDocument-DefaultValidation` contains only `accountingLineGroupTotalsUnchangedValidation`.
- **Source:** R7:202-214.
- **Priority:** M
- **Preconditions:** Document exists.

#### URS-003 — Route a document for approval
- **Statement:** On submit/route, the system shall execute the route-scoped validation composite (≥1 source line, ≥1 target line, debit/credit balance) and then drive the document through its KEW `routePath`.
- **Rationale:** `AccountingDocument-RouteDocument-DefaultValidation` requires source ≥1, target ≥1, and debit/credit balance.
- **Source:** R7:148-178.
- **Priority:** M
- **Preconditions:** URS-001 satisfied.

#### URS-004 — Sequential role-node approval
- **Statement:** A routed document shall visit its role nodes in the declared order, requiring approval at each active node before advancing.
- **Rationale:** e.g. DI routes `Account → AccountingOrganizationHierarchy → SubFund → Award`.
- **Source:** R3:398-405 (DI); R3:816-823 (TF); R4:535-543 (ACCT).
- **Priority:** M
- **Preconditions:** URS-003 satisfied.

#### URS-005 — Blanket approval
- **Statement:** An authorized user shall be able to blanket-approve a document, bypassing intermediate node validation.
- **Rationale:** `AccountingDocument-BlanketApproveDocument-DefaultValidation` has an empty validation list (`<!-- NO EXTRA VALIDATIONS -->`).
- **Source:** R7:230-235.
- **Priority:** S
- **Preconditions:** User holds blanket-approve permission.

#### URS-006 — Exception actions (disapprove / cancel / recall / ad-hoc)
- **Statement:** Users shall be able to disapprove, cancel, recall, and ad-hoc-route documents; every route path exposes an `AdHoc` start node for ad-hoc recipients.
- **Rationale:** `<start name="AdHoc"/>` present on every document type; KEW standard actions.
- **Source:** R3:330; R5:64; R6:222.
- **Priority:** M
- **Preconditions:** Document in an actionable state.

### 3.2 Accounting-line entry & balancing

#### URS-010 — Add accounting lines
- **Statement:** Users shall be able to add source and target accounting lines; each added line is validated (data dictionary, positive amount, allowed values, accessibility) before acceptance.
- **Rationale:** `AddAccountingLine` composite chains business-object DD validation, positive-amount, allowed-values, and accessibility validations.
- **Source:** R7:237-288.
- **Priority:** M
- **Preconditions:** URS-001.

#### URS-011 — Require at least one source and one target line
- **Statement:** On route, a standard accounting document shall have at least one source line and at least one target line.
- **Rationale:** two `requiredAccountingLinesCountValidation` beans, each `minimumNumber=1`, on source and target.
- **Source:** R7:151-168.
- **Priority:** M
- **Preconditions:** URS-003.
- **Note:** One-sided documents (e.g. Auxiliary Voucher) instead use `oneSidedRequiredAccountingLinesCountValidation` with `requiredMinimumCount=1` — `AuxiliaryVoucherValidation.xml:36-43`.

#### URS-012 — Debits must equal credits (balance to the cent)
- **Statement:** On route, the sum of debit GLPE amounts shall equal the sum of credit GLPE amounts; otherwise the route is rejected with error `ERROR_DOCUMENT_BALANCE`.
- **Rationale:** `DebitsAndCreditsBalanceValidation` generates GLPEs, sums debit vs credit via `KualiDecimal`, and errors if `debitAmount.compareTo(creditAmount) != 0`.
- **Source:** `DebitsAndCreditsBalanceValidation.java:57-81` (error key at :78).
- **Priority:** M
- **Preconditions:** GLPE generation succeeds (:53-55).

#### URS-013 — Auxiliary Voucher must balance credits vs debits
- **Statement:** An Auxiliary Voucher shall balance its credit total against its debit total; otherwise error `ERROR_DOCUMENT_BALANCE_CONSIDERING_CREDIT_AND_DEBIT_AMOUNTS` (with credit/debit params).
- **Rationale:** `AuxiliaryVoucherAccountingLinesBalanceValidation` compares `getCreditTotal()` vs `getDebitTotal()`.
- **Source:** `AuxiliaryVoucherAccountingLinesBalanceValidation.java:41-50`.
- **Priority:** M
- **Preconditions:** AV route.

#### URS-014 — Journal Voucher amount rules
- **Statement:** Journal Voucher accounting lines shall be non-zero; when the balance type generates GL offsets, negative amounts are rejected; otherwise negatives are allowed only for budget balance types.
- **Rationale:** `JournalVoucherAccountingLineAmountValidation` errors `ERROR_ZERO_OR_NEGATIVE_AMOUNT` / `ERROR_ZERO_AMOUNT` / `ERROR_NEGATIVE_NON_BUDGET_AMOUNTS` based on balance type; negatives allowed only when balance type ∈ parameter `FP_BUDGET_BALANCE_TYPES`.
- **Source:** `JournalVoucherAccountingLineAmountValidation.java:64-116` (parameter at :114).
- **Priority:** M
- **Controlling parameter:** `FP_BUDGET_BALANCE_TYPES` (component `JournalVoucherDocument`).
- **Preconditions:** JV route.

#### URS-015 — Correct debit/credit determination by object type & section
- **Statement:** The system shall determine whether an accounting line posts as a debit or credit based on object type (income/liability vs expense/asset) and the source/target section, per the sign rules in `DebitDeterminerService`.
- **Rationale:** `isDebitConsideringSection`: source line debit = amount not positive for income/liability/expense/asset; target line debit = amount positive. `isDebitConsideringSectionAndTypePositiveOnly`: source positive → debit iff income/liability; target positive → debit iff expense/asset. Zero amount throws `IllegalStateException`.
- **Source:** `DebitDeterminerServiceImpl.java:141-178` and `:183-219` (zero-amount guard at :146-148).
- **Priority:** M
- **Preconditions:** Object code resolves to a known object type.

#### URS-016 — Error-correction sign handling
- **Statement:** For error-correction documents the system shall relax the positive-amount rule (negatives permitted) but shall block error-correction where disallowed.
- **Rationale:** `isDebitConsideringNothingPositiveOnly` / `…PositiveOnly` throw only for non-error-correction documents; `disallowErrorCorrectionDocumentCheck` throws when error correction is not allowed.
- **Source:** `DebitDeterminerServiceImpl.java:50-59`, `:104-113`, `:190-193`.
- **Priority:** S
- **Preconditions:** Document is an error-correction of a posted document.

### 3.3 Conditional approval routing

#### URS-020 — Budget Adjustment full-approval branch
- **Statement:** A Budget Adjustment shall route through the full approval chain (Account → Org Hierarchy → SubFund → Award) only when full approval is required; otherwise it shall bypass to the join.
- **Rationale:** `RequiresFullApproval` split (`SimpleBooleanSplitNode`) with True branch (roles) / False branch (`Do Nothing` NoOp). `answerSplitNodeQuestion` delegates to `requiresFullApproval()` (returns true if any base-budget amount is non-zero, or multiple accounts/objects, etc.).
- **Source:** R3:315-374; `BudgetAdjustmentDocument.java:73,920-925,933-949`.
- **Priority:** M
- **Preconditions:** BA routed.

#### URS-021 — Procurement Card auto-approval branch
- **Statement:** A Procurement Card document shall route to the `AutoApprove` role node when auto-approval applies, else to a NoOp.
- **Rationale:** `RequiresAutoApprovalNotification` split; `answerSplitNodeQuestion` returns `isAutoApprovedIndicator()` for node `IS_DOCUMENT_AUTO_APPROVED` (= constant value `"RequiresAutoApprovalNotification"`).
- **Source:** R3:730-767; `ProcurementCardDocument.java:277-282,299-302`; `KFSConstants.java:628`.
- **Priority:** M
- **Preconditions:** PCDO routed.

#### URS-022 — AR conditional routing branches
- **Statement:** AR documents shall take conditional branches: Cash Control routes to `ElectronicPayment` only when associated with an electronic payment; Customer Invoice Writeoff routes to `Account` only when approval required; Customer Invoice routes to `Recurrence` only when recurring; C&G Invoice routes to `FundsManager` only when approval required.
- **Rationale:** four `SimpleBooleanSplitNode` splits: `AssociatedWithElectronicPayment`, `RequiresApproval`, `HasReccurence`, `RequiresApprovalSplit`.
- **Source:** R5:52-88 (CTRL), R5:179-211 (INVW), R5:285-326 (INV), R5:359-388 (CINV).
- **Priority:** M
- **Preconditions:** respective AR document routed.

#### URS-023 — Travel Authorization multi-branch routing with status transitions
- **Statement:** A Travel Authorization shall route through up to eight conditional review branches (Traveler, Division, Special Request, Risk Management, International, Budget, Separation of Duties, Advance Requested), advancing `nextAppDocStatus` at each branch, and each condition shall be answered by the document's `answerSplitNodeQuestion`.
- **Rationale:** TA route path declares splits with explicit `nextAppDocStatus` (e.g. `Awaiting Traveler Review` → `Awaiting Fiscal Officer Review` → … → `Open For Reimbursement`); TEM `answerSplitNodeQuestion` maps node names to boolean methods.
- **Source:** R6:141-218; `TravelRelocationDocument.java:294-311`.
- **Priority:** M
- **Preconditions:** TA routed.

### 3.4 Allowed-value enforcement (parameter-gated)

#### URS-030 — Object code / type / fund group / sub-fund group allowed-value checks
- **Statement:** On add/route the system shall reject accounting lines whose object code, object type, fund group, or sub-fund group is not permitted for the document type, per configurable parameters.
- **Rationale:** `accountingLineValueAllowedValidation` beans check `financialObjectCode`, `objectCode.financialObjectTypeCode`, `account.subFundGroup.fundGroupCode`, `account.subFundGroupCode` against parameters `OBJECT_CODES`, `OBJECT_TYPES`, `FUND_GROUPS`, `SUB_FUND_GROUPS`.
- **Source:** R7:96-118 (bundled in the `defaultAccountingLineValuesAllowedValidation` hutch at R7:138-146).
- **Priority:** M
- **Controlling parameters:** `OBJECT_CODES`, `OBJECT_TYPES`, `FUND_GROUPS`, `SUB_FUND_GROUPS` (plus `OBJECT_SUB_TYPES`, `OBJECT_LEVELS`, `OBJECT_CONSOLIDATIONS` — R7:120-136).
- **Preconditions:** Parameters configured per document type.

#### URS-031 — Positive-amount enforcement on line entry
- **Statement:** When adding/reviewing an accounting line, the amount shall be positive (except where a document permits error-correction negatives).
- **Rationale:** `accountingLineAmountPositiveValidation` with `quitOnFail=true` in the add/review chains.
- **Source:** R7:249-257, R7:308-315.
- **Priority:** M
- **Preconditions:** URS-010.

### 3.5 Authorization & security

#### URS-040 — Role-qualified node authorization
- **Statement:** Approval at a role node shall be restricted to users whose KIM qualifiers (chart/account/org) match the document, resolved via `DataDictionaryQualifierResolver`.
- **Rationale:** every `<role>` node names this resolver class.
- **Source:** R3:337-339; R4:548-550; R6 role nodes.
- **Priority:** M
- **Preconditions:** KIM role membership configured.

#### URS-041 — Document-type authorizers
- **Statement:** Specific document types shall enforce a custom workflow authorizer restricting who can act/edit.
- **Rationale:** CINV declares `<authorizer>…ContractsGrantsInvoiceWorkflowDocumentAuthorizer</authorizer>`; TEM uses `TravelWorkflowDocumentAuthorizer`.
- **Source:** R5:353-355.
- **Priority:** M
- **Preconditions:** Authorizer class registered.

#### URS-042 — Field-level edit modes & masking
- **Statement:** The UI shall honor state-driven edit modes and access-security field masking so unauthorized users cannot view/edit protected fields.
- **Rationale:** KFS-SEC access-security model; PCDO uses an `AccountFullEdit` role node gating edit scope.
- **Source:** R3:726-727,744-748; R1 §8.1.
- **Priority:** S
- **Preconditions:** Access-security definitions configured.

### 3.6 Batch / GL posting & reporting

#### URS-050 — GL pending entries generation & posting
- **Statement:** Approved financial documents shall generate balanced GLPEs that the GL batch pipeline (scrubber → poster) posts to the ledger.
- **Rationale:** GLPE generation is invoked during balance validation and by the GL batch jobs.
- **Source:** `DebitsAndCreditsBalanceValidation.java:52-61`; R1 §6.1.
- **Priority:** M
- **Preconditions:** Document approved/processed.

#### URS-051 — Reporting / PDF output
- **Statement:** The system shall produce PDF outputs (e.g. invoices, disbursement documents) via the reporting subsystem.
- **Rationale:** JasperReports/iText reporting (R1 §8.5).
- **Source:** R1 §8.5.
- **Priority:** S
- **Preconditions:** Report templates available.

---

## 4. Traceability: URS → Source → Target-state component

| URS | Source file(s) (file:line) | Target-state component (Spring Boot 4 / Spring Framework 7) |
|---|---|---|
| URS-001..006 | R3:312-330; R7:202-235 | Document lifecycle service + workflow engine adapter |
| URS-010..016 | R7:148-388; `DebitsAndCreditsBalanceValidation.java`; `AuxiliaryVoucherAccountingLinesBalanceValidation.java`; `JournalVoucherAccountingLineAmountValidation.java`; `DebitDeterminerServiceImpl.java` | `AccountingLineValidationService`, `DebitDeterminerService` (domain service), `BigDecimal`/money type |
| URS-020..023 | R3:315-374,730-767; R5:52-388; R6:141-218; `BudgetAdjustmentDocument.java`, `ProcurementCardDocument.java`, `TravelRelocationDocument.java` | Conditional routing predicates (`RoutingDecisionService`) + workflow definitions |
| URS-030..031 | R7:96-146,249-315 | `AllowedValueService` reading config parameters (externalized config) |
| URS-040..042 | R3:337-339; R5:353-355; R4:548-550 | Spring Security + method-level authorization + field masking aspect |
| URS-050..051 | `DebitsAndCreditsBalanceValidation.java:52-61`; R1 §6,§8.5 | Batch (Spring Batch) GL pipeline + reporting service |

---

## 5. OPEN QUESTIONS

- **OQ-1 (JIRA sub-board):** `Sach_Sales` (key `SS`) is a team-managed *business* project whose issue types are Workstream/Task/Sub-task (no Epic/Story/Test) and the JIRA MCP integration cannot create new projects/boards. The requested "new Scrum/Kanban board backed by a new project" cannot be created programmatically; a Workstream container inside `SS` is used as the sub-board equivalent unless a new empty project key is supplied.
- **OQ-2 (Confluence):** Whether these artifacts should also be published to Confluence (as in the prior task) was left unanswered.
- **OQ-3 (PCDO split naming):** The workflow split node is named `RequiresAutoApprovalNotification` while `ProcurementCardDocument.answerSplitNodeQuestion` matches constant `IS_DOCUMENT_AUTO_APPROVED`; these resolve to the same string (`KFSConstants.java:628`) but the naming is inconsistent and should be normalized on rebuild.
- **OQ-4 (auto-approval predicate details):** `BudgetAdjustmentDocument.requiresFullApproval()` documents five auto-approval conditions in its Javadoc (`:927-932`); the full boolean beyond the base-amount short-circuit (`:944-946`) should be re-validated against business intent during rebuild.
