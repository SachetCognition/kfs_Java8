# TC-002 — Distribution of Income & Expense (DI): Happy-Path E2E Test Design

> Implementation-ready E2E test design for the **Distribution of Income and Expense (DI)** document,
> covering the balanced-lines *happy path* through the full KEW route path, plus the negative and
> exception aspects that gate that path.
>
> Parent Task: **SS-118** — *URS Group 1 — Document Lifecycle*. Sub-task: **SS-125**.
>
> All citations are `file:line` references verified against `master` of `SachetCognition/kfs_Java8`
> at authoring time. Paths are repo-relative.

---

## 1. Overview & linked URS IDs

The DI document distributes accumulated income/expense (or assets/liabilities) across accounts. It is
a two-sided accounting document (From / To) that generates balanced General Ledger Pending Entries
(GLPEs) and routes serially through four KIM role nodes before reaching `FINAL`/processed.

| URS ID  | Requirement (as reverse-engineered)                                                                 |
|---------|------------------------------------------------------------------------------------------------------|
| URS-004 | A transactional financial document must be balanced (Σ debits == Σ credits, to the cent) before it can route. |
| URS-011 | A DI document must traverse its configured KEW route path (Account → AccountingOrganizationHierarchy → SubFund → Award) and reach `FINAL`. |
| URS-012 | Composite Spring-configured validation chains gate each document action (Save / Route / Approve / BlanketApprove / AddAccountingLine). |

**Document under test**: `DistributionOfIncomeAndExpenseDocument`
(`kfs-core/src/main/java/org/kuali/kfs/fp/document/DistributionOfIncomeAndExpenseDocument.java:42`),
document type name `DI` mapped in the data dictionary at
`kfs-core/src/main/resources/org/kuali/kfs/fp/document/datadictionary/DistributionOfIncomeAndExpenseDocument.xml:25-26`.

---

## 2. DI documentType & route path (ground truth)

Workflow definition file:
`kfs-core/src/main/config/workflow/003_kfs_core_module_docs/FinancialProcessingTransactionalDocuments.xml`

- `<documentType>` block for DI: **lines 376–442**.
- Name `DI`, parent `FP`, label "Distribution Of Income And Expense": lines 377–385.
- `<active>true</active>`: **lines 392–394** (spec said ~392-393; verified 392–394 including the closing tag).
- `<routingVersion>2</routingVersion>`: lines 395–397.
- `<routePaths>/<routePath>`: **lines 398–406** (spec said ~398-405; the `<routePath>` open/close spans 399–405, the `<routePaths>` wrapper 398–406).

Route path (serial, no split) — lines 399–405:

```
start AdHoc                       -> Account                        (line 400)
role  Account                     -> AccountingOrganizationHierarchy(line 401)
role  AccountingOrganizationHierarchy -> SubFund                    (line 402)
role  SubFund                     -> Award                          (line 403)
role  Award                       (terminal)                        (line 404)
```

`<routeNodes>` (lines 407–441): `AdHoc` start node (408); four `role` nodes — `Account` (409–416),
`AccountingOrganizationHierarchy` (417–424), `SubFund` (425–432), `Award` (433–440). Every role node
uses `qualifierResolverClass = org.kuali.rice.krad.workflow.attribute.DataDictionaryQualifierResolver`
and `activationType = P` (parallel activation within a node; nodes themselves are sequential).

**Expected node sequence for a DI (happy path):**
`AdHoc → Account → AccountingOrganizationHierarchy → SubFund → Award → (FINAL)`.

---

## 3. How DI differs from TF (and BA) — verified

The inlined spec asked to document how DI differs from TF. Verified findings:

### 3.1 Route path — identical to TF, both mandatory (no conditional split)
- DI route path: `FinancialProcessingTransactionalDocuments.xml:398-406`.
- TF (`TF`) `<documentType>` is **lines 794–860**; its route path (**lines 816–824**) is **byte-for-byte
  the same** node sequence as DI: `AdHoc → Account → AccountingOrganizationHierarchy → SubFund → Award`.
- Therefore DI and TF **route identically** and both route **unconditionally** (mandatory routing — the
  route path has no `<split>`).
- **Contrast with BA** (`BA` `<documentType>` lines 290–375): BA wraps the four role nodes inside a
  `<split name="RequiresFullApproval">` of type
  `org.kuali.kfs.sys.document.workflow.SimpleBooleanSplitNode`
  (`FinancialProcessingTransactionalDocuments.xml:315-326, 331-335`), so small BAs can short-circuit
  straight to the join. DI has **no such split** — it is the "mandatory routing" case. (This corrects a
  potential misreading of the inlined spec: DI's *mandatory* routing distinguishes it from **BA**, not
  from TF, which is equally mandatory.)

### 3.2 Object-type rules — the real DI-vs-TF difference is in business rules, not routing
- **TF restricts accounting lines to income/expense object types.**
  `TransferOfFundsDocument.isDebit(...)` throws `IllegalStateException` if a line is neither income nor
  expense (`kfs-core/src/main/java/org/kuali/kfs/fp/document/TransferOfFundsDocument.java:139-145`), and
  `customizeExplicitGeneralLedgerPendingEntry(...)` stamps the GLPE object-type as *Transfer Income* /
  *Transfer Expense* (`TransferOfFundsDocument.java:104-121`). TF's route chain additionally enforces
  `TransferOfFunds-transferTotalsBalanceValidation` and `TransferOfFunds-fundGroupsBalancedValidation`
  (`.../configuration/TransferOfFundsValidation.xml:56-69`) on top of the debit/credit balance check.
- **DI imposes no income/expense object-type restriction and no fund-group-balancing rule.** DI extends
  `CapitalAccountingLinesDocumentBase` (`DistributionOfIncomeAndExpenseDocument.java:42`) — i.e. it layers
  in capital-asset handling — and its route validation is *more permissive* about line sidedness
  (see §5.3). This is why DI is used for general redistribution while TF is the constrained cash-transfer
  document.

### 3.3 Validation-chain composition (per action)

| Action                | TF route chain                                                                 | DI route chain                                                                                     |
|-----------------------|--------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| Required lines        | Requires **≥1 source AND ≥1 target** (`TransferOfFundsValidation.xml:31-48`)   | `optionalOneSidedDocumentAccountingLinesCountValidation` (`DistributionOfIncomeAndExpenseValidation.xml:40-47`) — more permissive |
| Balance               | `debitsAndCreditsBalanceValidation` (`TransferOfFundsValidation.xml:49-55`)    | `debitsAndCreditsBalanceValidation` (`DistributionOfIncomeAndExpenseValidation.xml:32-38`)          |
| Extra balancing rules | transferTotalsBalance + fundGroupsBalanced (`TransferOfFundsValidation.xml:56-69`) | none                                                                                            |
| Capital-asset rules   | none                                                                           | capitalAssetLineUniqueness + capitalAccountingLines + capitalAssetInformation (`DistributionOfIncomeAndExpenseValidation.xml:49-55`) |

---

## 4. Preconditions & test data

### 4.1 System / configuration preconditions
| # | Precondition | Source / oracle |
|---|--------------|-----------------|
| P1 | DI document type is **active** in KEW. | `FinancialProcessingTransactionalDocuments.xml:392-394` |
| P2 | The four role nodes resolve to at least one eligible KIM approver each (Account, Org Hierarchy, SubFund, Award reviewers), else the node auto-approves/skips. | route nodes `FinancialProcessingTransactionalDocuments.xml:409-440` |
| P3 | DI validation beans are wired to document events via the data-dictionary `validationMap`. | `datadictionary/DistributionOfIncomeAndExpenseDocument.xml:38, 56-97` |
| P4 | Parameters `OBJECT_CODES`, `OBJECT_TYPES`, `OBJECT_SUB_TYPES`, `FUND_GROUPS`, `SUB_FUND_GROUPS`, `OBJECT_LEVELS`, `OBJECT_CONSOLIDATIONS` permit the chosen object codes for the DI doc type (used by the AddAccountingLine value-allowed chain). | `FinancialSystemValidators.xml:96-146` (bean `AccountingDocument-defaultAccountingLineValuesAllowedValidation`) |
| P5 | Current-year `SystemOptions` and a valid university fiscal year exist for GLPE generation. | `DebitsAndCreditsBalanceValidation.java:53` |

### 4.2 Concrete test data (illustrative — substitute chart/account values valid in the target DB)
The exact chart of accounts is DB-seeded and not in the repo (see **OPEN QUESTIONS**). Use two balanced
lines that net to zero across From/To:

| Field | From (source) line | To (target) line |
|-------|--------------------|-------------------|
| Chart code | `BL` | `BL` |
| Account number | `1031400` | `1031420` |
| Object code | `1500` (income/expense object valid for DI) | `1500` |
| Amount | `100.00` | `100.00` |
| Line description | "TC-002 DI happy path — from" | "TC-002 DI happy path — to" |

Balancing invariant: the GLPEs generated from these lines must yield **Σ debit == Σ credit** to the cent
(`DebitsAndCreditsBalanceValidation.java:58-75`). With one From and one To line of equal amount, the
explicit + offset entries balance.

Roles required at route nodes (acting principals): an approver qualifying for **Account**, then
**AccountingOrganizationHierarchy**, then **SubFund**, then **Award** — resolved via
`DataDictionaryQualifierResolver` (`FinancialProcessingTransactionalDocuments.xml:410-412` etc.).

---

## 5. Detailed step-by-step actions

Each step names the acting role and the exact `routeNode` (KEW node name) in effect.

### 5.1 Initiate & enter lines (initiator, pre-route — document status `INITIATED`/`SAVED`)
1. **Initiator** creates a DI document (`docHandler`:
   `FinancialProcessingTransactionalDocuments.xml:386-388`). Document class instantiated:
   `DistributionOfIncomeAndExpenseDocument` (`datadictionary/...DistributionOfIncomeAndExpenseDocument.xml:26`).
2. **Initiator** adds the **From** (source) accounting line → fires `AddAccountingLineEvent` →
   `DistributionOfIncomeAndExpense-addAccountingLineValidation`
   (`datadictionary/...DistributionOfIncomeAndExpenseDocument.xml:78-79`;
   bean `DistributionOfIncomeAndExpenseValidation.xml:94-100`). Each add runs the grouped chain
   (business-object DD check, positive-amount, line DD check, values-allowed) —
   `FinancialSystemValidators.xml:237-288`.
3. **Initiator** adds the **To** (target) accounting line (same event/chain).
4. **Initiator** **Saves** → fires `AttributedSaveDocumentEvent` →
   `DistributionOfIncomeAndExpense-saveDocumentValidation`
   (`datadictionary/...DistributionOfIncomeAndExpenseDocument.xml:66-67`), which wraps
   `AccountingDocument-SaveDocument-DefaultValidation` = accounting-line-group-totals-unchanged only
   (`FinancialSystemValidators.xml:202-214`). **Save does not enforce balance** — balance is a route-time
   gate. Document status → `SAVED`.

### 5.2 Route (initiator → workflow engine)
5. **Initiator** clicks **Route/Submit** → fires `AttributedRouteDocumentEvent` →
   `DistributionOfIncomeAndExpense-routeDocumentValidation`
   (`datadictionary/...DistributionOfIncomeAndExpenseDocument.xml:62-63`;
   bean `DistributionOfIncomeAndExpenseValidation.xml:26-58`). This chain:
   - generates GLPEs and asserts **Σ debit == Σ credit** (`debitsAndCreditsBalanceValidation`,
     `DistributionOfIncomeAndExpenseValidation.xml:32-38` → `DebitsAndCreditsBalanceValidation.java:49-82`);
   - asserts at least one accounting line via the one-sided optional count
     (`DistributionOfIncomeAndExpenseValidation.xml:40-47`);
   - runs capital-asset line validations (`DistributionOfIncomeAndExpenseValidation.xml:49-55`).
   On success KEW advances from `start AdHoc` to the first role node. Document status → `ENROUTE`.

### 5.3 Approvals (one role per node, in order)
6. **Account reviewer** approves at `routeNode` **`Account`** → fires `AttributedApproveDocumentEvent` →
   `DistributionOfIncomeAndExpense-approveDocumentValidation`
   (`datadictionary/...DistributionOfIncomeAndExpenseDocument.xml:70-71`;
   bean `DistributionOfIncomeAndExpenseValidation.xml:70-79` = totals-unchanged + capital-asset-line-uniqueness).
   KEW advances to `AccountingOrganizationHierarchy`.
7. **Org-hierarchy reviewer** approves at `routeNode` **`AccountingOrganizationHierarchy`** (same event/chain).
   KEW advances to `SubFund`.
8. **SubFund reviewer** approves at `routeNode` **`SubFund`** (same event/chain). KEW advances to `Award`.
9. **Award reviewer** approves at `routeNode` **`Award`** (terminal role node). No `nextNode`
   (`FinancialProcessingTransactionalDocuments.xml:404`).

### 5.4 Finalization (workflow engine)
10. After the terminal `Award` approval, KEW sets the document to **`FINAL`** (`PROCESSED` then `FINAL`).
    `doRouteStatusChange(...)` fires and releases capital-asset locks
    (`DistributionOfIncomeAndExpenseDocument.java:101-105`). GLPEs are eligible for GL posting by batch.

---

## 6. Acceptance criteria (Given/When/Then, code-grounded)

### AC-1 — Route path node sequence (URS-011)
- **Given** a balanced DI with ≥1 From and ≥1 To line,
- **When** it is routed and approved at each node,
- **Then** the traversed KEW node sequence is exactly
  `AdHoc → Account → AccountingOrganizationHierarchy → SubFund → Award`.
- **Verifies:** `FinancialProcessingTransactionalDocuments.xml:399-405` (routePath),
  `:407-441` (routeNodes).

### AC-2 — Final state is FINAL (URS-011)
- **Given** the sequence in AC-1 completes with an approval at the terminal `Award` node,
- **When** the last approval is taken,
- **Then** the document status becomes `FINAL` and capital-asset locks are cleared.
- **Verifies:** terminal role `FinancialProcessingTransactionalDocuments.xml:404`;
  `DistributionOfIncomeAndExpenseDocument.java:101-105` (`doRouteStatusChange`).

### AC-3 — Balance is enforced at Route, to the cent (URS-004)
- **Given** a DI whose GLPE debit total equals its credit total,
- **When** Route is invoked,
- **Then** `DebitsAndCreditsBalanceValidation` returns `true` (no `error.document.balance`), and routing proceeds.
- **And Given** an *unbalanced* DI, **When** Route is invoked, **Then** validation returns `false`,
  a `MessageMap` error keyed `error.document.balance` is placed on `ACCOUNTING_LINE_ERRORS`, and the
  document does **not** leave the `AdHoc` node.
- **Verifies:** `DebitsAndCreditsBalanceValidation.java:75` (`debitAmount.compareTo(creditAmount) == 0`),
  `:78` (`putError(..., ERROR_DOCUMENT_BALANCE)`); key `error.document.balance` =
  `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` (`kfs-core/src/main/java/org/kuali/kfs/sys/KFSKeyConstants.java:68`);
  wired into DI route chain at `DistributionOfIncomeAndExpenseValidation.xml:32-38`.

### AC-4 — Save does not require balance (URS-012)
- **Given** an in-progress DI (possibly unbalanced),
- **When** Save is invoked,
- **Then** only `AccountingLineGroupTotalsUnchanged` runs; the balance check is *not* applied and the doc saves.
- **Verifies:** `FinancialSystemValidators.xml:202-214` (SaveDocument default chain has no balance validation);
  DI save wiring `DistributionOfIncomeAndExpenseValidation.xml:60-68`.

### AC-5 — Route requires at least one accounting line (URS-012)
- **Given** a DI with no accounting lines,
- **When** Route is invoked,
- **Then** the one-sided required-count validation fails (`quitOnFail=true`) and routing is blocked.
- **Verifies:** `DistributionOfIncomeAndExpenseValidation.xml:40-47`
  (`optionalOneSidedDocumentAccountingLinesCountValidation`, `quitOnFail=true`).

### AC-6 — AddAccountingLine value gating (URS-012, negative)
- **Given** an accounting line with an object code/type not permitted by the configured parameters,
- **When** the line is added,
- **Then** the AddAccountingLine grouped chain fails (business-object DD, positive-amount, or values-allowed).
- **Verifies:** DI add chain `DistributionOfIncomeAndExpenseValidation.xml:94-100`;
  shared grouped chain `FinancialSystemValidators.xml:237-288` (value-allowed beans `:96-146`).

### AC-7 — BlanketApprove short-circuits node-by-node routing (exception/alt path, URS-011)
- **Given** a user with blanket-approve authority,
- **When** BlanketApprove is invoked on a balanced DI,
- **Then** the default blanket-approve chain adds **no extra document-level validations** (capital-asset
  checks still run per DI wiring) and the document goes straight to `FINAL`, bypassing intermediate role stops.
- **Verifies:** `FinancialSystemValidators.xml:230-235` (empty default BlanketApprove list);
  DI blanket-approve wiring `DistributionOfIncomeAndExpenseValidation.xml:81-92`.

### AC-8 — Approve re-checks totals unchanged (URS-012)
- **Given** the document is at a role node,
- **When** a reviewer approves,
- **Then** `AccountingLineGroupTotalsUnchanged` + capital-asset-line-uniqueness run (no debit/credit
  regeneration at approve).
- **Verifies:** `FinancialSystemValidators.xml:216-228` (Approve default chain);
  DI approve wiring `DistributionOfIncomeAndExpenseValidation.xml:70-79`.

---

## 7. Automation notes

### 7.1 Legacy stack (Java 8 / Rice 2.1.x) integration test
- Base class: extend `KualiTestBase` (Spring-context + transactional rollback) and build the document via
  `DocumentTestUtils` / `DocumentService.getNewDocument("DI")`.
- **Arrange:** populate one `SourceAccountingLine` and one `TargetAccountingLine` (§4.2), add via
  `document.addSourceAccountingLine(...)` / `addTargetAccountingLine(...)`.
- **Act & oracle — balance:** call the route rule (or `KualiRuleService.applyRules(new RouteDocumentEvent(document))`)
  and assert the return is `true`; assert `GlobalVariables.getMessageMap().hasNoErrors()`. For the negative
  case, unbalance one line and assert `getMessageMap().getErrorMessagesForProperty(KFSConstants.ACCOUNTING_LINE_ERRORS)`
  contains key `KFSKeyConstants.ERROR_DOCUMENT_BALANCE` (`"error.document.balance"`).
- **Direct GLPE oracle:** after `GeneralLedgerPendingEntryService.generateGeneralLedgerPendingEntries(doc)`,
  sum `getGeneralLedgerPendingEntries()` into debit vs credit buckets (skip offset entries via
  `isTransactionEntryOffsetIndicator()`) exactly as `DebitsAndCreditsBalanceValidation.java:57-75`, and
  assert `debit.compareTo(credit) == 0` (`KualiDecimal`, cent precision).
- **Route & status oracle:** `documentService.routeDocument(doc, "TC-002", null)`; drive approvals with a
  test `WorkflowDocument` per node principal; after the `Award` approval assert
  `doc.getDocumentHeader().getWorkflowDocument().isFinal()`. Optionally assert node progression via
  `WorkflowDocumentService`/route-node history equals the AC-1 sequence.

### 7.2 Modernization target (Java 25 LTS / Spring Boot 4 / JPA)
- **Java 25 test-runtime specifics:**
  - Toolchain: JUnit 5 (Jupiter) on JDK 25; Mockito 5.x with ByteBuddy ≥ 1.15 (class-file major version 69); Testcontainers ≥ 1.20; build on Gradle 9 / recent Maven compiler plugin with `--release 25`.
  - Launch the test JVM with `--enable-native-access=ALL-UNNAMED` (plus any `--add-opens` still required by retained Rice/OJB code); the Security Manager is removed, so drop any `-Djava.security.manager` test config.
  - Prefer virtual-thread executors (`Executors.newVirtualThreadPerTaskExecutor()`) for concurrent route/approve steps; avoid `synchronized` around blocking calls to prevent carrier-thread pinning.
  - Use records for fixtures (accounting-line / GLPE DTOs) and pattern-matching `switch` over sealed document/route-node types when asserting the node sequence.
- Replace `KualiTestBase` with `@SpringBootTest` + `@Transactional`; seed BOs via JPA repositories / Testcontainers.
- Re-express the composite validation chain as an ordered list of validator beans (or a Spring
  `Validator` pipeline) that yields a structured error collection; the oracle asserts an error code
  `document.balance` (mapped from `error.document.balance`) rather than a Rice `MessageMap`.
- Model KEW routing as a state machine / workflow service (e.g. Flowable/Camunda or a bespoke
  `RouteNode` sequence); assert the ordered node names equal AC-1 and terminal status `FINAL`.
- GLPE oracle unchanged in intent: assert `sum(debit) == sum(credit)` using `BigDecimal` (scale 2).

### 7.3 Concrete assertions / oracles summary
| Oracle | Legacy source of truth |
|--------|------------------------|
| Debit sum == credit sum | `DebitsAndCreditsBalanceValidation.java:75` |
| Error key on imbalance | `error.document.balance` (`KFSKeyConstants.java:68`), placed on `ACCOUNTING_LINE_ERRORS` (`DebitsAndCreditsBalanceValidation.java:78`) |
| Node sequence | `FinancialProcessingTransactionalDocuments.xml:399-405` |
| Final status | `DistributionOfIncomeAndExpenseDocument.java:101-105` + terminal node `:404` |

---

## 8. Traceability matrix

| TC step / AC | Action | URS ID | Source file:line |
|--------------|--------|--------|------------------|
| P1 | DI active | URS-011 | `FinancialProcessingTransactionalDocuments.xml:392-394` |
| §5.1 s1 | Initiate DI (docHandler) | URS-011 | `FinancialProcessingTransactionalDocuments.xml:386-388`; `datadictionary/DistributionOfIncomeAndExpenseDocument.xml:26` |
| §5.1 s2-3 / AC-6 | Add From/To lines | URS-012 | `datadictionary/DistributionOfIncomeAndExpenseDocument.xml:78-79`; `DistributionOfIncomeAndExpenseValidation.xml:94-100`; `FinancialSystemValidators.xml:237-288` |
| §5.1 s4 / AC-4 | Save (no balance gate) | URS-012 | `datadictionary/DistributionOfIncomeAndExpenseDocument.xml:66-67`; `FinancialSystemValidators.xml:202-214` |
| §5.2 s5 / AC-3 | Route: balance to the cent | URS-004 | `DistributionOfIncomeAndExpenseValidation.xml:32-38`; `DebitsAndCreditsBalanceValidation.java:49-82`; `KFSKeyConstants.java:68` |
| §5.2 s5 / AC-5 | Route: ≥1 line | URS-012 | `DistributionOfIncomeAndExpenseValidation.xml:40-47` |
| §5.3 s6-9 / AC-1 | Approve per role node in order | URS-011 | `FinancialProcessingTransactionalDocuments.xml:399-405, 407-441`; `datadictionary/...:70-71`; `DistributionOfIncomeAndExpenseValidation.xml:70-79` |
| §5.3 / AC-8 | Approve totals-unchanged | URS-012 | `FinancialSystemValidators.xml:216-228` |
| §5.4 s10 / AC-2 | Reach FINAL | URS-011 | `FinancialProcessingTransactionalDocuments.xml:404`; `DistributionOfIncomeAndExpenseDocument.java:101-105` |
| AC-7 | BlanketApprove alt path | URS-011 | `FinancialSystemValidators.xml:230-235`; `DistributionOfIncomeAndExpenseValidation.xml:81-92` |
| §3.2 | DI vs TF object-type rules | URS-012 | `TransferOfFundsDocument.java:104-121, 139-145`; `TransferOfFundsValidation.xml:56-69` |

---

## 9. OPEN QUESTIONS (unverifiable from source alone)

1. **Concrete chart/account/object-code values** — the example data in §4.2 (`BL`, `1031400`, `1500`, …)
   is illustrative. The valid chart of accounts is DB-seeded and not committed to the repo (per the
   environment blueprint, the Kuali impex schema/data is unavailable). The real values must come from the
   target test database fixture.
2. **Parameter values** — the actual allowed lists for `OBJECT_CODES`/`OBJECT_TYPES`/`SUB_FUND_GROUPS`
   etc. for DI are stored as KFS `Parameter` rows, not in the workflow/validation XML; the AddAccountingLine
   assertions depend on the seeded parameter set.
3. **Node auto-approval behavior** — whether a role node with no eligible approver auto-approves/skips vs.
   stalls depends on runtime KIM role membership; not determinable from static config. Test fixtures must
   seed at least one principal per role node (P2).
4. **`answerSplitNodeQuestion`** — DI has **no** `SimpleBooleanSplitNode` in its route path (unlike BA), so
   DI does not implement a split-node question hook. Confirmed by absence of a `<split>` in DI's route path
   (`FinancialProcessingTransactionalDocuments.xml:398-406`) and absence of the method on
   `DistributionOfIncomeAndExpenseDocument`.
