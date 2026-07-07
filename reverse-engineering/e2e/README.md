# KFS E2E Test Designs — URS Group 1 (Document Lifecycle)

Implementation-ready, code-grounded E2E test-design documents for URS Group 1 (URS-001..006), expanded from the E2E Test Specification (`../KFS_E2E_Test_Specifications.md`). Each doc verifies its acceptance criteria against exact source `file:line` on `master` and includes legacy (`KualiTestBase`) and Java 25 LTS / Spring Boot 4 (Spring Framework 7) / JPA automation notes.

| Test case | Document / concern | JIRA sub-task | File |
|---|---|---|---|
| TC-001 | Transfer of Funds (TF) happy-path lifecycle | SS-124 | [TC-001_transfer-of-funds-happy-path.md](TC-001_transfer-of-funds-happy-path.md) |
| TC-002 | Distribution of Income & Expense (DI) happy path | SS-125 | [TC-002_distribution-income-expense-happy-path.md](TC-002_distribution-income-expense-happy-path.md) |
| TC-060 | Blanket approval bypasses intermediate validation | SS-126 | [TC-060_blanket-approval.md](TC-060_blanket-approval.md) |
| TC-061 | Disapprove / cancel / recall / ad-hoc routing | SS-127 | [TC-061_exception-paths-disapprove-cancel-recall-adhoc.md](TC-061_exception-paths-disapprove-cancel-recall-adhoc.md) |
| TC-062 | Save runs only save-scoped validation | SS-128 | [TC-062_save-scoped-validation.md](TC-062_save-scoped-validation.md) |

JIRA container (sub-board): Workstream SS-117 → Task SS-118 (URS Group 1).
