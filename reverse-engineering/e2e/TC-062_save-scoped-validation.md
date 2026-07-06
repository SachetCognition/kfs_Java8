# TC-062 — Save runs only save-scoped validation

**Slug:** `save-scoped-validation`
**Parent Task:** URS Group 1 — Document Lifecycle
**Linked URS ID:** URS-002 (document save must persist a work-in-progress without enforcing full route-time integrity checks)

> All `file:line` citations were verified against `master` (commit `65ea4639f2`). Where the inlined spec quoted approximate line numbers, the verified values are given below and any drift is called out.

---

## 1. Overview

KFS accounting documents are gated by **composite validation chains** that are selected per *workflow event* (Save, Route, Approve, BlanketApprove, AddAccountingLine, …). The chain to run for a given event is resolved from the document's data-dictionary `validationMap`, then executed by the accounting rule engine.

This test proves that **saving an in-progress accounting document runs only the save-scoped chain** — which contains a single, effectively-inert validation (`accountingLineGroupTotalsUnchangedValidation`) — and that the **balance** (debits == credits) and **required-accounting-line-count** checks are **NOT** enforced on Save. Those checks only run when the document is **Routed** (submitted).

Concretely this means a user may:
- save a document with **zero** accounting lines,
- save a document whose debits and credits **do not balance**,

…and the Save succeeds (document persists in `SAVED` status, no `MessageMap` hard errors), whereas the same document is **rejected at Route** with specific error keys.

### Document under test
**Transfer of Funds (`TF`)** is used as the concrete example because it wires the *default* save chain and a *full* route chain (required source + required target lines + debits/credits balance), giving the cleanest happy/negative contrast. The behavior generalizes to every `AccountingDocument` whose data dictionary points its Save event at `AccountingDocument-SaveDocument-DefaultValidation` (e.g. `DI`, `GEC`, `TF`, `PCDO`, `BA` all reuse the shared save default via their `*-saveDocumentValidation` beans).

---

## 2. Event → validation-chain wiring (verified)

### 2.1 Rule engine dispatch
`AccountingRuleEngineRuleBase` is the `businessRulesClass` for FP transactional documents (e.g. `TransferOfFundsDocument.xml:28`).

- On **Save**, Rice calls `processCustomSaveDocumentBusinessRules(...)`, which fires an `AttributedSaveDocumentEvent`:
  - `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/AccountingRuleEngineRuleBase.java:156-163`
- On **Route**, `processCustomRouteDocumentBusinessRules(...)` fires an `AttributedRouteDocumentEvent`:
  - `AccountingRuleEngineRuleBase.java:143-151`
- `validateForEvent(...)` looks up the bean for the event class in the DD `validationMap`; **if the event class is not in the map it returns `true` (no validation)**:
  - `AccountingRuleEngineRuleBase.java:64-77` (map lookup at `:66-72`, `return true` at `:69`)

### 2.2 Data-dictionary map (Transfer of Funds)
`kfs-core/src/main/resources/org/kuali/kfs/fp/document/datadictionary/TransferOfFundsDocument.xml`
- `AttributedRouteDocumentEvent` → `TransferOfFunds-routeDocumentValidation` (`:63-64`)
- `AttributedSaveDocumentEvent` → `TransferOfFunds-saveDocumentValidation` (`:67-68`)
- `AttributedApproveDocumentEvent` → `TransferOfFunds-approveDocumentValidation` (`:71-72`)
- `AttributedBlanketApproveDocumentEvent` → `TransferOfFunds-blanketApproveDocumentValidation` (`:75-76`)

### 2.3 TF chain beans
`kfs-core/src/main/resources/org/kuali/kfs/fp/document/validation/configuration/TransferOfFundsValidation.xml`
- `TransferOfFunds-saveDocumentValidation` → composes **only** `AccountingDocument-SaveDocument-DefaultValidation` (`:74-82`, ref at `:79`)
- `TransferOfFunds-routeDocumentValidation` → required **source** lines (min 1), required **target** lines (min 1), `debitsAndCreditsBalanceValidation`, plus TF-specific `transferTotalsBalanceValidation` and `fundGroupsBalancedValidation` (`:26-72`)

---

## 3. The Save vs Route composite chains — exact contents

`kfs-core/src/main/resources/org/kuali/kfs/sys/document/validation/configuration/FinancialSystemValidators.xml`

### 3.1 Save chain (verified `:202-214`)
`AccountingDocument-SaveDocument-DefaultValidation` contains **exactly one** validation:

| # | Validation bean | Purpose |
|---|-----------------|---------|
| 1 | `AccountingDocument-accountingLineGroupTotalsUnchangedValidation` (`:205`; abstract def `:67`) | Ensures per-section totals are unchanged **only when the document is already enroute** |

**Critical behavior:** `AccountingLineGroupTotalsUnchangedValidation.validate(...)` **short-circuits and returns `true`** for a Save event whenever the document is **not enroute** (or completion is requested):
- `kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/AccountingLineGroupTotalsUnchangedValidation.java:50-52`

Therefore, for an **in-progress** document (INITIATED / SAVED, pre-route) the entire Save chain is a **no-op** — no balance check, no line-count check, no totals check.

### 3.2 Route chain — default (verified `:148-178`)
`AccountingDocument-RouteDocument-DefaultValidation`:

| # | Validation bean | Config | Error key on failure |
|---|-----------------|--------|----------------------|
| 1 | `requiredAccountingLinesCountValidation` (`:151-159`) | `accountingLineGroupName=source`, `minimumNumber=1` | `error.document.sourceSectionNoAccountingLines` |
| 2 | `requiredAccountingLinesCountValidation` (`:160-168`) | `accountingLineGroupName=target`, `minimumNumber=1` | `error.document.targetSectionNoAccountingLines` |
| 3 | `debitsAndCreditsBalanceValidation` (`:169-175`) | — | `error.document.balance` |

> TF adds two more route validations on top of the equivalent set (`transferTotalsBalanceValidation`, `fundGroupsBalancedValidation`) — see §2.3. The three rows above are the ones this TC contrasts against Save.

### 3.3 Other chains (for completeness / enumeration)
- `AccountingDocument-ApproveDocument-DefaultValidation` (`:216-228`): only `accountingLineGroupTotalsUnchangedValidation` (same single validation as Save).
- `AccountingDocument-BlanketApproveDocument-DefaultValidation` (`:230-235`): **empty** list — `<!-- NO EXTRA VALIDATIONS -->`.
- `AccountingDocument-RouteDocument-SourceOnlyValidation` (`:180-200`): one-sided variant (`oneSidedRequiredAccountingLinesCountValidation` + balance) used by source-only docs.

### 3.4 Side-by-side: what runs where

| Validation | Save | Route (default) |
|------------|:----:|:---------------:|
| `accountingLineGroupTotalsUnchangedValidation` | ✔ (inert unless enroute) | ✘ |
| required **source** line count ≥ 1 | ✘ | ✔ |
| required **target** line count ≥ 1 | ✘ | ✔ |
| debits == credits balance | ✘ | ✔ |

---

## 4. Validation internals (oracles)

### 4.1 `DebitsAndCreditsBalanceValidation`
`kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/DebitsAndCreditsBalanceValidation.java`
- Generates GLPEs, sums non-offset credits and debits into `KualiDecimal` buckets (`:57-73`).
- Balance decided by exact `KualiDecimal.compareTo == 0` — i.e. equal **to the cent** (`:75`).
- On imbalance puts `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` under `KFSConstants.ACCOUNTING_LINE_ERRORS` (`:77-79`).
- `ERROR_DOCUMENT_BALANCE = "error.document.balance"` — `kfs-core/src/main/java/org/kuali/kfs/sys/KFSKeyConstants.java:68`.

### 4.2 `RequiredAccountingLinesCountValidation`
`kfs-core/src/main/java/org/kuali/kfs/sys/document/validation/impl/RequiredAccountingLinesCountValidation.java`
- Fails when `group.size() < minimumNumber`, putting the error under the group property (`:49-55`).
- Error key derived as `"error.document." + accountingLineGroupName + "SectionNoAccountingLines"` (`:114`) ⇒ `sourceSectionNoAccountingLines` / `targetSectionNoAccountingLines` (constants `KFSKeyConstants.java:77,81`).

### 4.3 `AccountingLineGroupTotalsUnchangedValidation`
`.../AccountingLineGroupTotalsUnchangedValidation.java`
- Save short-circuit at `:50-52` (returns `true` when not enroute).
- When enroute, compares persisted vs current source/target totals via `KualiDecimal.compareTo` (`:71,78`); on change puts `ERROR_DOCUMENT_SINGLE_ACCOUNTING_LINE_SECTION_TOTAL_CHANGED = "error.document.singleAccountingLineSectionTotalChanged"` (`:118`; constant `KFSKeyConstants.java:126`).

---

## 5. Preconditions & test data

### 5.1 Environment / fixtures
- KFS + Kuali Rice schema loaded; a functional user with initiate rights for `TF`.
- Chart / accounts / object codes that exist and pass DD & value-allowed checks so that the **only** reasons Route can fail are the ones under test (missing lines, imbalance).

### 5.2 Concrete accounting data (Transfer of Funds)

| Field | Source line A | Target line B |
|-------|---------------|---------------|
| Chart | `BL` | `BL` |
| Account | `1031400` | `1031400` |
| Object code | `1500` (income/transfer object valid for TF) | `5000` (expense/transfer object valid for TF) |
| Amount | `100.00` | `100.00` |

> A **balanced** pair (equal debit/credit GLPE totals) is used for the happy path. Negative paths deliberately violate exactly one route rule at a time.

### 5.3 Roles & route nodes (`TF`)
`kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml` — `TF` doc type at `:794-860`. Linear route path (`:816-824`):
`AdHoc` → `Account` → `AccountingOrganizationHierarchy` → `SubFund` → `Award`.
All role nodes use `DataDictionaryQualifierResolver` with pre-approval activation `P` (`:826-858`).

---

## 6. Detailed step-by-step actions

### Path A — Happy path: save a valid TF, then route/approve through every node
1. **Initiate** `TF` as the initiator. Status `INITIATED`.
2. **Add accounting lines**: source A and target B from §5.2 (each Add fires `AddAccountingLineEvent` → `TransferOfFunds-addAccountingLineValidation`).
3. **Save** (fires `AttributedSaveDocumentEvent`). ⇒ save chain runs, totals-unchanged short-circuits (`:50-52`). Status `SAVED`, no errors.
4. **Route/submit** (fires `AttributedRouteDocumentEvent`). Route chain passes (2 lines present, balanced). Document enters workflow at node `Account`.
5. **Approve at `Account`** (acting role: `Account`) → advances to `AccountingOrganizationHierarchy`.
6. **Approve at `AccountingOrganizationHierarchy`** → advances to `SubFund`.
7. **Approve at `SubFund`** → advances to `Award`.
8. **Approve at `Award`** (final role node) → no further nodes ⇒ document `PROCESSED`/`FINAL`.

### Path B — Negative: save with NO accounting lines succeeds; route fails
1. Initiate `TF`.
2. Do **not** add any accounting lines.
3. **Save** ⇒ succeeds, status `SAVED`, no line-count error (line-count check is route-scoped only).
4. **Route** ⇒ fails; `MessageMap` contains `error.document.sourceSectionNoAccountingLines` (and `...targetSectionNoAccountingLines`); document stays pre-route (not enroute).

### Path C — Negative: save an UNBALANCED TF succeeds; route fails
1. Initiate `TF`; add source A `100.00` and target B `90.00` (debits ≠ credits).
2. **Save** ⇒ succeeds, status `SAVED`, no balance error (balance check is route-scoped only).
3. **Route** ⇒ fails; `MessageMap` contains `error.document.balance`; document not enroute.

### Path D — Exception aspect: totals-unchanged only bites once enroute
1. Route a valid TF so it is **ENROUTE**.
2. As an approver, alter a section total and **Save** ⇒ save chain now actually evaluates totals-unchanged (no longer short-circuited, `:50-52`); a total change yields `error.document.singleAccountingLineSectionTotalChanged`.

---

## 7. Acceptance criteria (Given / When / Then)

**AC-1 — Save chain contains only the totals-unchanged validation**
- *Given* the shared save composite chain
- *When* inspected
- *Then* `AccountingDocument-SaveDocument-DefaultValidation` lists exactly one child, `accountingLineGroupTotalsUnchangedValidation`.
- *Verifies:* `FinancialSystemValidators.xml:202-214` (child at `:205`); TF wiring `TransferOfFundsValidation.xml:74-82`.

**AC-2 — Save is a no-op on an in-progress document**
- *Given* an INITIATED/SAVED (not enroute) TF
- *When* it is saved
- *Then* the totals-unchanged validation returns `true` without evaluating totals.
- *Verifies:* `AccountingLineGroupTotalsUnchangedValidation.java:50-52`.

**AC-3 — Required-line-count is NOT enforced on Save but IS on Route**
- *Given* a TF with zero accounting lines
- *When* saved *Then* Save succeeds; *When* routed *Then* Route fails with `error.document.sourceSectionNoAccountingLines` / `error.document.targetSectionNoAccountingLines`.
- *Verifies:* Route chain `FinancialSystemValidators.xml:151-168`; `RequiredAccountingLinesCountValidation.java:49-55,114`; keys `KFSKeyConstants.java:77,81`. Save chain absence: `:202-214`.

**AC-4 — Balance (debits==credits) is NOT enforced on Save but IS on Route**
- *Given* an unbalanced TF
- *When* saved *Then* Save succeeds; *When* routed *Then* Route fails with `error.document.balance`.
- *Verifies:* Route chain `FinancialSystemValidators.xml:169-175`; `DebitsAndCreditsBalanceValidation.java:57-79` (compare `:75`, error `:78`); key `KFSKeyConstants.java:68`.

**AC-5 — Event→chain dispatch is data-dictionary driven**
- *Given* an `AttributedSaveDocumentEvent`
- *When* the rule engine resolves the chain
- *Then* it uses `TransferOfFunds-saveDocumentValidation`; an unmapped event would return `true`.
- *Verifies:* `AccountingRuleEngineRuleBase.java:156-163,64-77`; `TransferOfFundsDocument.xml:63-68`.

**AC-6 — Route path advances role-node by role-node to a final state**
- *Given* a valid routed TF
- *When* approved at `Account` → `AccountingOrganizationHierarchy` → `SubFund` → `Award`
- *Then* it reaches `PROCESSED`/`FINAL`.
- *Verifies:* `FinancialProcessingTransactionalDocuments.xml:816-858`.

---

## 8. Automation notes

### 8.1 Legacy stack (Java 8 / Rice 2.1.x)
- Extend `KualiTestBase`; build the TF with `DocumentTestUtils`/`DocumentService`.
- **Unit-level (no DB) oracle for AC-1..AC-2** — the highest-value, DB-free assertions:
  - Load the Spring bean `AccountingDocument-SaveDocument-DefaultValidation`, assert its `validations` list has size 1 and the element type is `AccountingLineGroupTotalsUnchangedValidation`.
  - Instantiate `AccountingLineGroupTotalsUnchangedValidation`, feed a mock doc whose `workflowDocument.isEnroute()==false`, assert `validate(new AttributedSaveDocumentEvent(doc)) == true` regardless of totals.
- **Integration oracle for AC-3/AC-4** (needs schema):
  - `documentService.saveDocument(tf)` → assert no `GlobalVariables.getMessageMap()` errors and `docStatus == SAVED`.
  - `documentService.routeDocument(tf)` → assert `false`/`ValidationException`, and `MessageMap.getErrorMessages()` contains the exact keys (`error.document.balance` for Path C; `error.document.sourceSectionNoAccountingLines`/`targetSectionNoAccountingLines` for Path B).
  - For balance, also assert the GLPE oracle directly: sum non-offset `GL_CREDIT_CODE` vs debit `transactionLedgerEntryAmount` and compare with `KualiDecimal.compareTo`.
- **AC-6**: drive `WorkflowDocument` approvals via test users at each node and assert `getRouteHeader().getDocRouteStatus()` transitions to `PROCESSED`/`FINAL`.

### 8.2 Modernization target (Spring Boot 3 / JPA)
- Model the chain as an ordered list of `Validation` beans keyed by a `DocumentEvent` enum (mirrors the DD `validationMap`), so the Save vs Route difference stays declarative and unit-testable.
- Tests:
  - `@SpringBootTest` slice asserting `saveChain == [TotalsUnchangedValidation]`.
  - Persist via JPA repository; assert a save of an unbalanced/empty doc commits with status `SAVED` and no `BindingResult`/error-map entries.
  - A route-service test asserting rejection + the equivalent i18n error codes.
  - Balance oracle: reuse `KualiDecimal`-equivalent (`BigDecimal` with fixed scale + `compareTo`) so cent-level equality semantics are preserved.

---

## 9. Traceability

| TC step / AC | URS ID | Source file:line |
|--------------|--------|------------------|
| AC-1 (save chain = totals-unchanged only) | URS-002 | `FinancialSystemValidators.xml:202-214`; `TransferOfFundsValidation.xml:74-82` |
| AC-2 (save no-op pre-route) | URS-002 | `AccountingLineGroupTotalsUnchangedValidation.java:50-52` |
| AC-3 (line count route-only) | URS-002 | `FinancialSystemValidators.xml:151-168`; `RequiredAccountingLinesCountValidation.java:49-55,114`; `KFSKeyConstants.java:77,81` |
| AC-4 (balance route-only) | URS-002 | `FinancialSystemValidators.xml:169-175`; `DebitsAndCreditsBalanceValidation.java:75,78`; `KFSKeyConstants.java:68` |
| AC-5 (event dispatch) | URS-002 | `AccountingRuleEngineRuleBase.java:64-77,156-163`; `TransferOfFundsDocument.xml:63-68` |
| AC-6 (route node progression) | URS-002 | `FinancialProcessingTransactionalDocuments.xml:816-858` |
| Path D (totals-changed enroute) | URS-002 | `AccountingLineGroupTotalsUnchangedValidation.java:71,78,118`; `KFSKeyConstants.java:126` |

---

## 10. Open questions
- **URS-002 wording:** the URS source lives in PR #72 (`reverse-engineering/KFS_User_Requirements_Specification.md`), not on `master`. The URS ID is mapped here from the inlined spec; the exact URS prose was not re-read from `master`.
- **Object codes / accounts (§5.2)** are representative KFS demo values; concrete valid combinations must be confirmed against the target instance's Chart/parameter data (`OBJECT_CODES`, `FUND_GROUPS`, etc.) so route-time value-allowed checks don't mask the checks under test.
- **`transferTotalsBalanceValidation` / `fundGroupsBalancedValidation`** (TF-specific route validations, `TransferOfFundsValidation.xml:56-69`) are out of scope for TC-062 but are additional route-only gates; not asserted here.
