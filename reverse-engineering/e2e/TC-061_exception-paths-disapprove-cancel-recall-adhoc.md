# TC-061 — Exception paths: Disapprove / Cancel / Recall / Ad-hoc routing

- **Test case ID:** TC-061
- **Slug:** exception-paths-disapprove-cancel-recall-adhoc
- **JIRA:** SS-127 (sub-task of SS-118 "URS Group 1 — Document Lifecycle")
- **Linked URS ID:** URS-006 (Document lifecycle exception / termination paths)
- **Repository / ref:** `SachetCognition/kfs_Java8` @ `master`
- **Module under test:** `kfs-core` — Financial Processing (FP) transactional documents, KEW workflow, GLPE posting

> All `file:line` citations below were verified against the working tree on `master`
> (HEAD `65ea4639f2`). Where the inlined spec quoted approximate line numbers, the
> verified numbers are given and any drift is noted inline.

---

## 1. Overview

TC-061 exercises the **non-happy-path lifecycle transitions** of KFS Financial Processing
accounting documents, and the **ad-hoc routing** entry that is common to every FP document
type. The four behaviours under test are:

1. **Disapprove** — an approver rejects an en-route document. The document moves to
   workflow status **DISAPPROVED** and any General Ledger Pending Entries (GLPEs) that were
   generated for balance-checking are **removed** (never posted / approved).
2. **Cancel** — the initiator abandons a document (typically SAVED / not yet routed). The
   document moves to **CANCELED** and GLPEs are removed.
3. **Recall** — the initiator pulls back a document they already routed. The document is
   recalled and GLPEs are removed (Rice 2.x recall semantics).
4. **Ad-hoc routing** — an ad-hoc recipient added at the `AdHoc` start node (present on every
   FP doc type) receives a KEW action request (FYI / ACKNOWLEDGE / APPROVE / COMPLETE).

The unifying invariant is the ledger-safety rule: **GLPEs only become "approved"/postable when
the document is `PROCESSED`; for every terminal exception path they are deleted.** This is
implemented centrally so it holds for all FP doc types (BA, DI, TF, PCDO, IB, GEC, ICA, SB, ND,
PE, AV, DV, JV, AD, CR, CCR, CMD, FPYE).

### Ground-truth anchors

| Concern | Source | Verified location |
|---|---|---|
| FP doc types, route paths, AdHoc start node, SimpleBooleanSplitNode | `kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml` | BA `documentType` line 290; DI line 376; PCDO line 702; TF line 794; DV line 971 |
| Composite validation chains (Route/Save/Approve/BlanketApprove) | `kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml` | Route chain 148–178; Save chain 202–214; Approve chain 216–228; BlanketApprove (empty) 230–235 |
| Debit == Credit to the cent (KualiDecimal) | `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/DebitsAndCreditsBalanceValidation.java` | GLPE generation 53; credit/debit buckets 67–72; `compareTo == 0` 75; error put 78 |
| GLPE lifecycle on route-status change | `kfs-core/src/main/java/org/kuali/kfs/sys/document/GeneralLedgerPostingDocumentBase.java` | `doRouteStatusChange` 124–137; approve-on-processed 127–128; **remove on cancel/disapprove/recall** 131–132 |
| KFS document-status mapping on status change | `kfs-core/src/main/java/org/kuali/kfs/sys/document/FinancialSystemTransactionalDocumentBase.java` | `doRouteStatusChange` 157–180; CANCELLED 163–164; DISAPPROVED 169–170; PROCESSED->APPROVED 172–173 |
| Status-code constants | `kfs-core/src/main/java/org/kuali/kfs/sys/KFSConstants.java` | `DocumentStatusCodes` 644–651 (CANCELLED "X" 646, DISAPPROVED "D" 648, APPROVED "A" 649) |
| Balance error message key | `kfs-core/src/main/java/org/kuali/kfs/sys/KFSKeyConstants.java` | `ERROR_DOCUMENT_BALANCE = "error.document.balance"` 68 |

---

## 2. Preconditions & Test Data

### 2.1 Environment / fixtures
- KFS running against a populated Rice (KEW/KIM/KNS) + KFS schema (COA, object codes,
  parameters, KIM roles/responsibilities loaded).
- Legacy integration harness: `KualiTestBase` (transactional, rolls back per test) plus the FP
  document test utilities. The modernization target substitutes a Java 25 LTS / Spring Boot 4 (Spring Framework 7) + JPA slice.

### 2.2 Representative document under test
Primary happy-then-disapprove flow uses a **Transfer of Funds (TF)** document because it is a
two-sided, fully-routed balancing document (route path: `AdHoc -> Account ->
AccountingOrganizationHierarchy -> SubFund -> Award`, `FinancialProcessingTransactionalDocuments.xml:816-823`).
Cancel is demonstrated on a **saved** TF; recall on a **routed** TF; ad-hoc on any FP doc
(all share the `AdHoc` start node).

### 2.3 Concrete accounting data (balanced two-sided document)
| Field | Source (from) line | Target (to) line |
|---|---|---|
| Chart | `BL` | `BL` |
| Account Number | `1031400` | `1031420` |
| Object Code | `5000` (expense) | `5000` (expense) |
| Amount | `100.00` (credit / decrease) | `100.00` (debit / increase) |
| Line description | "TC-061 exception path" | "TC-061 exception path" |

Debits total (`100.00`) must equal credits total (`100.00`) — enforced by
`DebitsAndCreditsBalanceValidation` (`:75`). Use accounts/object codes valid for the seeded
`OBJECT_CODES` / `FUND_GROUPS` parameters (`FinancialSystemValidators.xml:96-136`) so the
happy-path route validation passes before we drive it into an exception state.

### 2.4 Roles / actors (KIM)
| Actor | Role at node |
|---|---|
| Initiator | document initiator (creates, saves, routes, cancels, recalls) |
| Fiscal Officer / Account approver | `Account` role node (`FinancialProcessingTransactionalDocuments.xml:819`, `827`) |
| Org Review approver | `AccountingOrganizationHierarchy` role node (`:820`, `835`) |
| SubFund approver | `SubFund` role node (`:821`, `843`) |
| Award approver | `Award` role node (`:822`, `851`) |
| Ad-hoc recipient | arbitrary KIM principal added at `AdHoc` (`:826`) |

### 2.5 Parameters (by name)
- `OBJECT_CODES`, `OBJECT_TYPES`, `FUND_GROUPS`, `SUB_FUND_GROUPS`, `OBJECT_SUB_TYPES`,
  `OBJECT_LEVELS`, `OBJECT_CONSOLIDATIONS` — value-allowed checks
  (`FinancialSystemValidators.xml:96-136`).
- `KFS-SYS / Document / UPDATE_TOTAL_AMOUNT_IN_POST_PROCESSING_IND` — governs post-processing
  total recompute (`FinancialSystemTransactionalDocumentBase.java:196-197`); not required for
  the exception assertions but should be in a known state.

---

## 3. Detailed step-by-step actions

### Scenario A — Disapprove (en-route document -> DISAPPROVED, no GLPEs post)
| # | Actor | Action | routeNode |
|---|---|---|---|
| A1 | Initiator | Initiate TF document | (pre-route; status INITIATED) |
| A2 | Initiator | Add source line (BL/1031400/5000/100.00) and target line (BL/1031420/5000/100.00) | — |
| A3 | Initiator | **Save**; assert save-time validation passes | Save chain `FinancialSystemValidators.xml:202-214` |
| A4 | Initiator | **Route**; route validation generates GLPEs and confirms debits==credits | `AdHoc` -> Route chain `:148-178` |
| A5 | KEW | Document is ENROUTE; first role request activates | `Account` (`FinancialProcessingTransactionalDocuments.xml:819`) |
| A6 | Account approver | Open the approval request, then **Disapprove** | `Account` |
| A7 | KEW/System | `doRouteStatusChange` fires with new status DISAPPROVED | — |

### Scenario B — Cancel (saved document -> CANCELED)
| # | Actor | Action | routeNode |
|---|---|---|---|
| B1 | Initiator | Initiate TF, add balanced lines | (pre-route) |
| B2 | Initiator | **Save** (do NOT route) | Save chain `:202-214` |
| B3 | Initiator | **Cancel** the saved document | — |
| B4 | System | `doRouteStatusChange` fires with new status CANCELED | — |

### Scenario C — Recall (routed document pulled back)
| # | Actor | Action | routeNode |
|---|---|---|---|
| C1 | Initiator | Initiate + save + **route** a balanced TF | `AdHoc` -> `Account` |
| C2 | Initiator | From the document (still ENROUTE, no approver acted) invoke **Recall** (with or without cancel) | `Account` (pending) |
| C3 | System | `doRouteStatusChange` fires; `workflowDocument.isRecalled()` true | — |

### Scenario D — Ad-hoc routing (recipient receives an action request)
| # | Actor | Action | routeNode |
|---|---|---|---|
| D1 | Initiator | Initiate a balanced FP doc (e.g. TF or DI) | `AdHoc` start node (`FinancialProcessingTransactionalDocuments.xml:826` / DI `:408`) |
| D2 | Initiator | Add an **ad-hoc recipient** (person) with action-requested = FYI, ACKNOWLEDGE, APPROVE or COMPLETE | `AdHoc` |
| D3 | Initiator | **Route** | `AdHoc` -> next role node |
| D4 | KEW | Ad-hoc recipient's action request is generated at the `AdHoc` node | `AdHoc` |
| D5 | Ad-hoc recipient | Sees the action item in their Action List | — |

---

## 4. Given / When / Then acceptance criteria (with source citations)

### AC-1 — Disapprove terminates the document and removes GLPEs
- **Given** an en-route routable TF document with balanced accounting lines (route chain ran:
  `FinancialSystemValidators.xml:148-178`; balance enforced
  `DebitsAndCreditsBalanceValidation.java:75`),
- **When** an approver at the `Account` role node
  (`FinancialProcessingTransactionalDocuments.xml:819,827`) **disapproves**,
- **Then** `doRouteStatusChange` sets the KFS status to `DISAPPROVED`
  (`FinancialSystemTransactionalDocumentBase.java:169-170`; code `"D"`
  `KFSConstants.java:648`), **and** the GLPEs are removed via `removeGeneralLedgerPendingEntries()`
  because the doc is disapproved (`GeneralLedgerPostingDocumentBase.java:131-132`), so **no
  ledger entry is ever flagged approved** (the approve branch at `:127-128` is not taken).

### AC-2 — Cancel of a saved document -> CANCELED, no posting
- **Given** an initiator who saved (but did not route) a TF document (save chain
  `FinancialSystemValidators.xml:202-214`, which does **not** generate GLPEs — only
  `accountingLineGroupTotalsUnchangedValidation`),
- **When** the initiator **cancels** the document,
- **Then** the KFS status becomes `CANCELLED` (`FinancialSystemTransactionalDocumentBase.java:163-164`;
  code `"X"` `KFSConstants.java:646`) **and** any GLPEs are removed
  (`GeneralLedgerPostingDocumentBase.java:131-132`).

### AC-3 — Recall removes GLPEs
- **Given** a routed (ENROUTE) TF document with no approver action yet,
- **When** the initiator **recalls** it,
- **Then** `workflowDocument.isRecalled()` is true and GLPEs are removed
  (`GeneralLedgerPostingDocumentBase.java:131-132`, which explicitly enumerates
  `isCanceled() || isDisapproved() || isRecalled()`; note the code comment
  "general ledger pending entries are getting removed on ReCall" `:130`).

### AC-4 — Ad-hoc recipient receives an action request
- **Given** an FP document at its `AdHoc` start node (present on **all** FP doc types — e.g.
  BA `FinancialProcessingTransactionalDocuments.xml:330`, DI `:408`, PCDO `:743`, TF `:826`,
  DV `:1043`),
- **When** the initiator adds an ad-hoc recipient and routes,
- **Then** KEW generates an action request for that recipient at the `AdHoc` node; the request
  type (FYI / ACK / APPROVE / COMPLETE) matches the selected ad-hoc action-requested code and
  appears in the recipient's Action List. (The `AdHoc` node is the KEW-standard ad-hoc handler;
  KFS declares it as the `<start name="AdHoc"/>` route node on every FP `documentType`.)

### AC-5 — Positive contrast: only PROCESSED posts to the ledger
- **Given** the same balanced TF document,
- **When** it is fully approved through all role nodes to `PROCESSED`,
- **Then** GLPEs are marked approved (`GeneralLedgerPostingDocumentBase.java:127-128`) and the
  KFS status becomes `APPROVED`/`"A"` (`FinancialSystemTransactionalDocumentBase.java:172-173`).
  This is the control case proving the exception paths (AC-1..AC-3) correctly *avoid* posting.

### AC-6 — Balance gate blocks routing (negative)
- **Given** a TF with unbalanced lines (e.g. source 100.00, target 99.99),
- **When** the initiator routes,
- **Then** `DebitsAndCreditsBalanceValidation` computes `debitAmount.compareTo(creditAmount) != 0`
  (`:75`) and puts `error.document.balance` on `KFSConstants.ACCOUNTING_LINE_ERRORS`
  (`:78`; key `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` `KFSKeyConstants.java:68`); the document
  stays editable and does not go ENROUTE.

---

## 5. KEW action-request semantics per exception path

| Path | KEW action | Effect on requests / doc | GLPE effect |
|---|---|---|---|
| Disapprove | `disapprove` on the active APPROVE request at a role node | doc -> DISAPPROVED; outstanding requests deactivated; initiator gets acknowledge/notification | removed (`GeneralLedgerPostingDocumentBase.java:131-132`) |
| Cancel | `cancel` (initiator, pre/at route) | doc -> CANCELED; no further routing | removed (`:131-132`) |
| Recall | `recall` (initiator on own routed doc) | doc pulled back (recalled); pending requests deactivated | removed (`:131-132`) |
| Ad-hoc | `adHocRouteRequest` at `AdHoc` node | new action request (FYI/ACK/APPROVE/COMPLETE) to named principal/group | none by itself |

---

## 6. Automation notes

### 6.1 Legacy stack (Java 8 / Rice 2.1.x)
- Extend `KualiTestBase` (transactional; auto rollback). Build the TF via the FP document
  test utilities / `DocumentService`.
- **Disapprove:** route the doc as initiator, `changeCurrentUser` to the `Account` approver,
  then `documentService.disapproveDocument(doc, "TC-061 disapprove")`. Oracles:
  - `doc.getDocumentHeader().getWorkflowDocument().isDisapproved()` == true;
  - `financialSystemDocumentHeader.getFinancialDocumentStatusCode()` == `"D"`
    (`KFSConstants.DocumentStatusCodes.DISAPPROVED`);
  - `SpringContext.getBean(GeneralLedgerPendingEntryService.class)` returns **no** GLPEs for
    `doc.getDocumentNumber()` (they were removed at `GeneralLedgerPostingDocumentBase.java:132`).
- **Cancel:** `documentService.saveDocument(doc)` then `documentService.cancelDocument(doc, "TC-061 cancel")`.
  Oracle: status code `"X"`; GLPE count == 0.
- **Recall:** route, then `documentService.recallDocument(doc, "TC-061 recall", false)`.
  Oracle: `workflowDocument.isRecalled()` true; GLPE count == 0.
- **Ad-hoc:** add an `AdHocRoutePerson` (actionRequested = FYI/APPROVE) to the doc before
  routing; after route, assert the recipient has an action item via `WorkflowDocumentService` /
  action-list lookup for that principal on `doc.getDocumentNumber()`.
- **Balance gate (AC-6):** set target amount to `99.99`, call route, assert
  `GlobalVariables.getMessageMap()` contains key `error.document.balance` under
  `KFSConstants.ACCOUNTING_LINE_ERRORS` and the doc did not go ENROUTE.
- **Debit/credit oracle (AC-5):** after generating GLPEs, sum
  `getTransactionLedgerEntryAmount()` split by `getTransactionDebitCreditCode()` (skip offset
  entries, `DebitsAndCreditsBalanceValidation.java:63`) and assert
  `debitSum.compareTo(creditSum) == 0`.

### 6.2 Modernization target (Java 25 LTS / Spring Boot 4 / JPA)
- **Java 25 test-runtime specifics:**
  - Toolchain: JUnit 5 (Jupiter) on JDK 25; Mockito 5.x with ByteBuddy ≥ 1.15 (class-file major version 69); Testcontainers ≥ 1.20; build on Gradle 9 / recent Maven compiler plugin with `--release 25`.
  - Launch the test JVM with `--enable-native-access=ALL-UNNAMED` (plus any `--add-opens` still required by retained Rice/OJB code); the Security Manager is removed, so drop any `-Djava.security.manager` test config.
  - Prefer virtual-thread executors for concurrent disapprove/cancel/recall/ad-hoc transitions; avoid `synchronized` around blocking calls to prevent carrier-thread pinning.
  - Use records for fixtures and pattern-matching `switch` over sealed action-request / route-status types when asserting each exception transition.
- Model the document lifecycle as a service-layer state machine; back GLPEs with a JPA entity
  and repository. Reproduce the invariant in a `@Transactional` `@SpringBootTest` slice:
  - a `WorkflowService.disapprove/cancel/recall` transition method mirroring
    `doRouteStatusChange` (`GeneralLedgerPostingDocumentBase.java:124-137`);
  - assert repository query `glpeRepository.findByDocumentNumber(...)` is empty after each
    exception transition, and non-empty + `approvedCode == "A"` after a processed transition;
  - map error keys to a `BindingResult`/`Errors` (or a `MessageMap` equivalent) and assert the
    `error.document.balance` key for the unbalanced case;
  - use `BigDecimal.compareTo(...) == 0` (the JPA analogue of `KualiDecimal.compareTo`) for the
    to-the-cent balance oracle.

---

## 7. Traceability

| TC step / AC | URS ID | Source file:line |
|---|---|---|
| A4 route validation | URS-006 | `FinancialSystemValidators.xml:148-178` |
| A3/B2 save validation | URS-006 | `FinancialSystemValidators.xml:202-214` |
| AC-1 disapprove status | URS-006 | `FinancialSystemTransactionalDocumentBase.java:169-170`; `KFSConstants.java:648` |
| AC-1/AC-2/AC-3 GLPE removal | URS-006 | `GeneralLedgerPostingDocumentBase.java:131-132` |
| AC-2 cancel status | URS-006 | `FinancialSystemTransactionalDocumentBase.java:163-164`; `KFSConstants.java:646` |
| AC-3 recall | URS-006 | `GeneralLedgerPostingDocumentBase.java:130-132` |
| AC-4 ad-hoc node (all doc types) | URS-006 | `FinancialProcessingTransactionalDocuments.xml:330,408,743,826,1043` |
| AC-5 processed -> posts | URS-006 | `GeneralLedgerPostingDocumentBase.java:127-128`; `FinancialSystemTransactionalDocumentBase.java:172-173` |
| AC-6 balance error | URS-006 | `DebitsAndCreditsBalanceValidation.java:75,78`; `KFSKeyConstants.java:68` |

---

## 8. OPEN QUESTIONS

1. **Recall + GLPE-generated docs:** GLPE removal on recall is confirmed
   (`GeneralLedgerPostingDocumentBase.java:131-132`), but exact recall *availability* (who may
   recall and at which route states) is governed by KIM permissions / Rice recall rules not
   fully traced in `kfs-core` — verify against the KIM permission config before asserting the
   negative "approver cannot recall".
2. **Ad-hoc action-request activation type:** the concrete request-type enum values and their
   Action-List rendering come from Rice KEW (`org.kuali.rice.kew`), external to this repo; the
   assertion in 6.1 relies on the KEW action-list API, whose exact method signatures should be
   pinned to the resolved Rice `2.1.10` artifact.
3. **DISAPPROVED GLPE audit trail:** entries are *deleted* (`removeGeneralLedgerPendingEntries()`
   -> `glpeService.delete(...)`, `:151-153`) rather than reversed; confirm whether any audit
   record of the removed pending entries is expected by URS-006 or only the absence of posting.
