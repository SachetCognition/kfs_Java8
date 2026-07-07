# Kuali Financial System (KFS) — End-to-End (E2E) Test Specifications

> Code-grounded E2E test suites with explicit, verifiable Given/When/Then acceptance criteria. Companion to `reverse-engineering/KFS_User_Requirements_Specification.md` (URS-###). Every acceptance criterion cites the source file and line range it verifies.

---

## Document Control

| Property | Value |
|---|---|
| **Title** | KFS E2E Test Specifications with Acceptance Criteria |
| **Version** | 1.0 |
| **Status** | Draft for review |
| **Date** | 2026-07-06 |
| **Scope** | Financial document lifecycle, accounting-line balancing, conditional routing, allowed-value enforcement, authorization, financial correctness, exception paths. |
| **Convention** | Test IDs `TC-###`; each links URS ID(s); acceptance criteria in Given/When/Then. `nextAppDocStatus` values are the literal strings from the workflow XML. |

### Source key (file:line references)

| Ref | Path |
|---|---|
| R3 | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml` |
| R4 | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/ChartMaintenanceDocuments.xml` |
| R5 | `kfs-core/src/main/config/workflow/010_accounts_receivable/AccountsReceivableTransactionalDocuments.xml` |
| R6 | `kfs-core/src/main/config/workflow/120_travel_and_entertainment/TemTransactionalDocuments.xml` |
| R7 | `kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml` |
| DCB | `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/DebitsAndCreditsBalanceValidation.java` |
| AVB | `kfs-core/src/main/java/org/kuali/kfs/fp/document/validation/impl/AuxiliaryVoucherAccountingLinesBalanceValidation.java` |
| JVA | `kfs-core/src/main/java/org/kuali/kfs/fp/document/validation/impl/JournalVoucherAccountingLineAmountValidation.java` |
| DDS | `kfs-core/src/main/java/org/kuali/kfs/sys/document/service/impl/DebitDeterminerServiceImpl.java` |

---

## Suite A — Financial Processing document lifecycle & routing

### TC-001 — Transfer of Funds (TF) happy-path lifecycle
- **URS:** URS-001, URS-003, URS-004, URS-011, URS-012
- **Preconditions:** User is Fiscal Officer for the accounts used; TF is active (R3:810-811).
- **Test data:** Balanced source/target lines (e.g. source $100.00 credit, target $100.00 debit) with allowed object codes.
- **Steps:** initiate TF → add 1 source + 1 target line → save → route → approve at `Account` → `AccountingOrganizationHierarchy` → `SubFund` → `Award`.
- **Acceptance criteria:**
  - **Given** a saved TF with ≥1 source and ≥1 target line, **When** routed, **Then** route validation passes (R7:151-168) and the document enters the `Account` node (R3:818-819).
  - **Given** approval at each role node in order, **When** the acting approver approves, **Then** the document advances `Account → AccountingOrganizationHierarchy → SubFund → Award` (R3:816-823) and finally reaches `PROCESSED`/`FINAL`.
  - **Given** balanced debits/credits, **When** routed, **Then** no `ERROR_DOCUMENT_BALANCE` is raised (DCB:75-79).

### TC-002 — Distribution of Income & Expense (DI) happy path
- **URS:** URS-004, URS-011, URS-012
- **Preconditions:** DI active (R3:392-393).
- **Steps:** initiate → add balanced lines → route → approve `Account → AccountingOrganizationHierarchy → SubFund → Award`.
- **Acceptance criteria:**
  - **Given** a balanced DI, **When** routed, **Then** node sequence equals R3:398-405 and final state is `FINAL`.

### TC-003 — Budget Adjustment (BA) full-approval branch (TRUE)
- **URS:** URS-020
- **Preconditions:** BA active (R3:306-308); accounting line has a non-zero base-budget amount.
- **Steps:** initiate BA → add line with non-zero base amount → route.
- **Acceptance criteria:**
  - **Given** a BA where `requiresFullApproval()` returns true (any non-zero base-budget amount — `BudgetAdjustmentDocument.java:944-946`), **When** the `RequiresFullApproval` split evaluates (R3:315), **Then** the **True** branch is taken and the document routes `Account → AccountingOrganizationHierarchy → SubFund → Award → JoinRequiresFullApproval` (R3:316-321).
  - **Given** node `RequiresFullApproval`, **When** `answerSplitNodeQuestion` is called, **Then** it delegates to `requiresFullApproval()` (`BudgetAdjustmentDocument.java:920-925`).

### TC-004 — Budget Adjustment (BA) auto-approval branch (FALSE)
- **URS:** URS-020
- **Preconditions:** Single account, only current adjustments, non-C&G fund, balanced current increase/decrease (per Javadoc `BudgetAdjustmentDocument.java:927-932`).
- **Steps:** initiate BA meeting all auto-approval conditions → route.
- **Acceptance criteria:**
  - **Given** `requiresFullApproval()` returns false, **When** the split evaluates, **Then** the **False** branch (`Do Nothing` NoOp) is taken to `JoinRequiresFullApproval` (R3:322-325) and the document auto-approves without visiting role nodes.

### TC-005 — Procurement Card (PCDO) auto-approval branch
- **URS:** URS-021
- **Preconditions:** PCDO active (R3:718-719).
- **Steps:** initiate PCDO → route through `AccountFullEdit → Account → AccountingOrganizationHierarchy → SubFund → RequiresAutoApprovalNotification`.
- **Acceptance criteria:**
  - **Given** `isAutoApprovedIndicator()` is true, **When** the `RequiresAutoApprovalNotification` split evaluates (R3:731), **Then** the **True** branch routes to the `AutoApprove` role node then `joinOnAutoApproval` (R3:732-738).
  - **Given** `isAutoApprovedIndicator()` is false, **When** the split evaluates, **Then** the **False** branch (`NoOpAutoApproval`) is taken (R3:735-737).
  - **Given** split node name resolution, **Then** `answerSplitNodeQuestion` matches constant `IS_DOCUMENT_AUTO_APPROVED` = `"RequiresAutoApprovalNotification"` (`ProcurementCardDocument.java:277-280`, `KFSConstants.java:628`).

---

## Suite B — Negative / validation cases

### TC-010 — Unbalanced debits/credits rejected at route
- **URS:** URS-012
- **Preconditions:** Standard accounting document (e.g. TF).
- **Test data:** source $100.00, target $90.00 (unbalanced).
- **Steps:** initiate → add unbalanced lines → route.
- **Acceptance criteria:**
  - **Given** debit total ≠ credit total, **When** routed, **Then** `DebitsAndCreditsBalanceValidation.validate` returns false and puts error `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` on `ACCOUNTING_LINE_ERRORS` (DCB:75-79); the route is blocked.
  - **Given** offset entries, **When** balancing, **Then** entries with `isTransactionEntryOffsetIndicator()` are excluded from the sums (DCB:63-65).

### TC-011 — Missing required source/target line rejected
- **URS:** URS-011
- **Steps:** initiate → add only a source line (no target) → route.
- **Acceptance criteria:**
  - **Given** zero target lines, **When** routed, **Then** the target `requiredAccountingLinesCountValidation` (minimumNumber=1) fails (R7:160-168) and the route is blocked.

### TC-012 — Disallowed object code / type / fund group rejected (parameter-gated)
- **URS:** URS-030
- **Controlling parameters:** `OBJECT_CODES`, `OBJECT_TYPES`, `FUND_GROUPS`, `SUB_FUND_GROUPS`.
- **Steps:** add a line whose object code is excluded by the `OBJECT_CODES` parameter for that doc type → attempt add/route.
- **Acceptance criteria:**
  - **Given** an object code not permitted by parameter `OBJECT_CODES`, **When** the line is validated, **Then** `AccountingDocument-IsObjectCodeAllowed-DefaultValidation` fails (R7:96-100).
  - **Given** a fund group excluded by `FUND_GROUPS`, **Then** `IsFundGroupAllowed` fails checking `account.subFundGroup.fundGroupCode` (R7:108-112).

### TC-013 — Auxiliary Voucher zero/unbalanced amounts rejected
- **URS:** URS-013
- **Steps:** initiate AV → add lines whose credit total ≠ debit total → route.
- **Acceptance criteria:**
  - **Given** AV credit total ≠ debit total, **When** routed, **Then** `AuxiliaryVoucherAccountingLinesBalanceValidation` returns false and raises `ERROR_DOCUMENT_BALANCE_CONSIDERING_CREDIT_AND_DEBIT_AMOUNTS` with `[credit, debit]` params (AVB:45-49).
  - **Given** AV, **When** routed, **Then** it uses one-sided line-count validation `requiredMinimumCount=1` (`AuxiliaryVoucherValidation.xml:36-43`).

### TC-014 — Journal Voucher zero amount rejected
- **URS:** URS-014
- **Controlling parameter:** `FP_BUDGET_BALANCE_TYPES`.
- **Steps:** initiate JV → add a $0.00 line → validate.
- **Acceptance criteria:**
  - **Given** balance type with offset generation and a $0.00 amount, **When** validated, **Then** `ERROR_ZERO_OR_NEGATIVE_AMOUNT` is raised on both debit and credit paths (JVA:72-77).
  - **Given** a non-offset balance type and a $0.00 amount, **Then** `ERROR_ZERO_AMOUNT` is raised (JVA:92-94).
  - **Given** a negative amount and a non-budget balance type (not in `FP_BUDGET_BALANCE_TYPES`), **Then** `ERROR_NEGATIVE_NON_BUDGET_AMOUNTS` is raised (JVA:96-100, :113-116).

### TC-015 — Journal Voucher negative amount with offset balance type rejected
- **URS:** URS-014
- **Steps:** balance type generates offsets → add a negative line → validate.
- **Acceptance criteria:**
  - **Given** `isFinancialOffsetGenerationIndicator()` true and a negative amount, **When** validated, **Then** `ERROR_ZERO_OR_NEGATIVE_AMOUNT` is raised against the debit or credit field based on debit/credit code (JVA:78-88).

---

## Suite C — Financial correctness (debit/credit determination)

### TC-020 — Debit/credit by object type and section
- **URS:** URS-015
- **Acceptance criteria:**
  - **Given** a positive-amount **source** line of income/liability OR expense/asset type, **When** `isDebitConsideringSection` runs, **Then** the line is a **credit** (`isDebit = !isPositiveAmount`) (DDS:150-160).
  - **Given** a positive-amount **target** line, **Then** the line is a **debit** (`isDebit = isPositiveAmount`) (DDS:162-170).
  - **Given** a zero amount, **Then** `IllegalStateException` is thrown (DDS:146-148).

### TC-021 — Positive-only section+type debit rule
- **URS:** URS-015
- **Acceptance criteria:**
  - **Given** a positive **source** line, **Then** debit iff income/liability (DDS:198-200).
  - **Given** a positive **target** line, **Then** debit iff expense/asset (DDS:208-210).
  - **Given** a non-positive amount on a non-error-correction document, **Then** `IllegalStateException` (DDS:190-193).

### TC-022 — GL pending entries balance to the cent
- **URS:** URS-012, URS-050
- **Acceptance criteria:**
  - **Given** an approved balanced document, **When** GLPEs are generated, **Then** `sum(debit) == sum(credit)` compared as `KualiDecimal` with `compareTo == 0` (DCB:58-75) — exact-to-the-cent equality, no floating error.

### TC-023 — Error-correction negative amounts permitted
- **URS:** URS-016
- **Acceptance criteria:**
  - **Given** an error-correction document, **When** debit determination runs with a non-positive amount, **Then** no `IllegalStateException` is thrown (guard skipped — DDS:107, :191).
  - **Given** a document where error correction is disallowed, **When** `disallowErrorCorrectionDocumentCheck` runs on an error-correction, **Then** `IllegalStateException` is thrown (DDS:54-56).

---

## Suite D — Accounts Receivable conditional routing

### TC-030 — Cash Control electronic-payment branch
- **URS:** URS-022
- **Acceptance criteria:**
  - **Given** a Cash Control (CTRL) associated with an electronic payment, **When** the `AssociatedWithElectronicPayment` split evaluates (R5:73-77), **Then** the **True** branch routes to the `ElectronicPayment` role node then `Join` (R5:53-59); the `Lockbox` node is `mandatoryRoute` (R5:69-71).
  - **Given** no electronic payment, **Then** the **False** branch (`NoOp`) is taken (R5:56-58).

### TC-031 — Customer Invoice Writeoff requires-approval branch
- **URS:** URS-022
- **Acceptance criteria:**
  - **Given** an INVW requiring approval, **When** the `RequiresApproval` split evaluates (R5:192-195), **Then** the **True** branch routes to the `Account` role node (R5:180-182); else `NoOp` (R5:183-185).

### TC-032 — Customer Invoice recurrence branch
- **URS:** URS-022
- **Acceptance criteria:**
  - **Given** a recurring INV, **When** the `HasReccurence` split evaluates (R5:299-302), **Then** the **True** branch routes `Account → Recurrence → Join` (R5:286-289); else `NoOp` (R5:290-292).

### TC-033 — Contracts & Grants Invoice funds-manager branch + authorizer
- **URS:** URS-022, URS-041
- **Acceptance criteria:**
  - **Given** a CINV requiring approval, **When** the `RequiresApprovalSplit` evaluates (R5:372-375), **Then** the **True** branch routes to the `FundsManager` role node then `RequiresApprovalJoin` (R5:360-366); else `NoOpNode` (R5:363-365).
  - **Given** any CINV action, **Then** authorization is enforced by `ContractsGrantsInvoiceWorkflowDocumentAuthorizer` (R5:353-355).

---

## Suite E — Travel Authorization multi-branch routing & statuses

### TC-040 — TA happy path with full status transitions
- **URS:** URS-023
- **Acceptance criteria (status sequence, all from R6):**
  - **Given** a routed TA, **Then** initial status is `Awaiting Traveler Review` (R6:141-142).
  - **When** traveler review required is true, **Then** `Traveler` node → `Awaiting Fiscal Officer Review` (R6:144); else NoOp → same status (R6:147).
  - **Then** `Account` → `Awaiting Organization Review` (R6:151); Division split → `Awaiting Division Review` (R6:153).
  - **Then** Special Request → `Awaiting Special Request Review` / `Awaiting Risk Management Review` (R6:162-164); Risk Management → `Awaiting International Travel Review` (R6:171-173); International → `Awaiting Sub-Fund Review` (R6:180-182).
  - **Then** `SubFund` → `Awaiting Award Review` (R6:189); `Award` → `Awaiting Budget Review` (R6:190); Budget split → `Awaiting Separation Of Duties Review` (R6:191-193).
  - **Then** Separation of Duties → `Awaiting Travel Manager Review` (R6:200-202); Advance Requested True → `Travel`/`PaymentMethod` → `Awaiting Disbursement Method Review` → `Open For Reimbursement` (R6:209-215).

### TC-041 — TA split-node predicates
- **URS:** URS-023
- **Acceptance criteria:**
  - **Given** node `REQUIRES_TRAVELER_REVIEW`, **Then** `answerSplitNodeQuestion` returns `requiresTravelerApprovalRouting()` (`TravelRelocationDocument.java:295-296`).
  - **Given** node `SPECIAL_REQUEST` / `TAX_MANAGER_APPROVAL_REQUIRED` / `REQUIRES_BUDGET_REVIEW` / `SEPARATION_OF_DUTIES`, **Then** the corresponding boolean method is returned (`:298-308`).
  - **Given** an unknown node name, **Then** `UnsupportedOperationException` is thrown (`:310`).

---

## Suite F — Chart maintenance & authorization

### TC-050 — Account (ACCT) maintenance routing
- **URS:** URS-004, URS-005, URS-040
- **Acceptance criteria:**
  - **Given** a routed ACCT maintenance document, **Then** it routes `Account → OrganizationHierarchy → SubFund → Award → Chart` (R4:535-543) with each node using `DataDictionaryQualifierResolver` (R4:548-586).

### TC-051 — Unauthorized approval blocked at role node
- **URS:** URS-040
- **Acceptance criteria:**
  - **Given** a user whose KIM qualifiers do not match the document's chart/account/org, **When** they attempt to approve at a role node, **Then** the action is denied (qualifier resolution via `DataDictionaryQualifierResolver`, R3:337-339 / R4:548-550).

### TC-052 — Field-level edit mode / masking honored
- **URS:** URS-042
- **Acceptance criteria:**
  - **Given** a PCDO at a node other than `AccountFullEdit`, **When** a non-privileged user views the document, **Then** only permitted fields are editable (edit-mode gating via the `AccountFullEdit` role node, R3:726-727,744-748).

---

## Suite G — Exception paths

### TC-060 — Blanket approval bypasses intermediate validation
- **URS:** URS-005
- **Acceptance criteria:**
  - **Given** an authorized blanket-approver, **When** blanket approve is invoked, **Then** the blanket-approve validation composite runs no extra validations (R7:230-235) and the document reaches `FINAL`.

### TC-061 — Disapprove / cancel / recall / ad-hoc
- **URS:** URS-006
- **Acceptance criteria:**
  - **Given** a document in a routable state, **When** an approver disapproves, **Then** the document goes to `DISAPPROVED` and no GLPEs post.
  - **Given** an initiator, **When** they cancel a saved document, **Then** it goes to `CANCELED`.
  - **Given** an ad-hoc recipient added at the `AdHoc` node (present on all types, e.g. R3:330, R5:64, R6:222), **Then** the recipient receives an action request.

### TC-062 — Save runs only save-scoped validation
- **URS:** URS-002
- **Acceptance criteria:**
  - **Given** an in-progress document, **When** saved, **Then** only `accountingLineGroupTotalsUnchangedValidation` runs (R7:202-214) — balance/line-count are NOT enforced on save.

---

## Requirements Traceability Matrix (URS ↔ TC ↔ source)

| URS | Test cases | Primary source (file:line) |
|---|---|---|
| URS-001 | TC-001 | R3:312-314 |
| URS-002 | TC-062 | R7:202-214 |
| URS-003 | TC-001, TC-010, TC-011 | R7:148-178 |
| URS-004 | TC-001, TC-002, TC-050 | R3:398-405; R4:535-543 |
| URS-005 | TC-060 | R7:230-235 |
| URS-006 | TC-061 | R3:330; R5:64; R6:222 |
| URS-010 | TC-012 | R7:237-288 |
| URS-011 | TC-001, TC-011 | R7:151-168 |
| URS-012 | TC-001, TC-010, TC-022 | DCB:57-81 |
| URS-013 | TC-013 | AVB:41-50 |
| URS-014 | TC-014, TC-015 | JVA:64-116 |
| URS-015 | TC-020, TC-021 | DDS:141-219 |
| URS-016 | TC-023 | DDS:50-59,104-113,190-193 |
| URS-020 | TC-003, TC-004 | R3:315-374; `BudgetAdjustmentDocument.java:920-949` |
| URS-021 | TC-005 | R3:730-767; `ProcurementCardDocument.java:277-302` |
| URS-022 | TC-030..033 | R5:52-388 |
| URS-023 | TC-040, TC-041 | R6:141-218; `TravelRelocationDocument.java:294-311` |
| URS-030 | TC-012 | R7:96-146 |
| URS-031 | TC-012 | R7:249-315 |
| URS-040 | TC-051, TC-050 | R3:337-339; R4:548-550 |
| URS-041 | TC-033 | R5:353-355 |
| URS-042 | TC-052 | R3:726-748 |
| URS-050 | TC-022 | DCB:52-61 |
| URS-051 | — (manual/report suite) | R1 §8.5 |

---

## OPEN QUESTIONS

- **OQ-1:** Exact final `nextAppDocStatus`/`FINAL` string for FP documents (BA/TF/DI/PCDO) is not declared inline in R3 (only TEM declares `nextAppDocStatus` on nodes); confirm the terminal status names during rebuild.
- **OQ-2:** `answerSplitNodeQuestion` for TA is implemented in `TravelRelocationDocument.java`; verify the `TravelAuthorizationDocument` override chain uses the same node constants for all eight TA splits (R6) before finalizing TC-040/041 assertions.
- **OQ-3:** Some AR documents (APP, CCA, LCR, FBI) have trivial single-node route paths (R5:113-116,136-149,409-416,435-442); confirm whether these are intentionally auto-final or awaiting workflow that was never authored (possible dead config).
