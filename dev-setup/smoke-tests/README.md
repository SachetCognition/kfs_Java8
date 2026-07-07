# KFS HTTP Smoke Tests

A self-contained, runnable test suite that verifies KFS business functionality
end-to-end over HTTP against a locally running instance. It replaces the four
manual browser scenarios previously used for before/after migration
verification, and does not depend on the legacy Rice JUnit test harness (which
the repo's 624 JUnit test files require and which cannot run).

The tests drive the real KNS/Struts UI the same way a browser does: one
`requests.Session` carries the JSESSIONID cookie, and postbacks send
form-encoded `methodToCall.*` parameters to the same `.do` actions
(`documentWebScope=session`, `formKey` scraped from the initiation screen).

## Prerequisites

- A running KFS instance with the demo database (see `../README.md` for full
  local setup: MySQL `kuldev` demo db, `kfs-config.properties`, Rice keystore,
  and `mvn tomcat7:run-war`). On JDK 21, start Tomcat with
  `JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64` and the `MAVEN_OPTS` flags
  from `../java21-runtime-flags.txt`.
- The dev build auto-logs-in as `khuntley` (DummyLoginFilter), so no
  credentials are needed.
- Python 3.8+.

## Run

```bash
cd dev-setup/smoke-tests
python3 -m pip install -r requirements.txt
python3 -m pytest -v
```

By default the suite targets `http://localhost:8080/kfs-web-dev`. Override
with `--base-url` or the `KFS_BASE_URL` environment variable:

```bash
python3 -m pytest -v --base-url http://otherhost:8080/kfs-web-dev
```

## Coverage

| File | What it verifies |
| --- | --- |
| `test_01_portal.py` | Portal loads (HTTP 200), auto-login as `khuntley`, main navigation tabs (Main Menu / Maintenance / Administration) present. |
| `test_02_account_lookup.py` | Account Lookup (kfs-coa): chart `BL` + name `PSYCH*` returns exactly 11 rows; a non-matching lookup returns 0 rows. |
| `test_03_transfer_of_funds.py` | Transfer of Funds (kfs-fp): initiate a TF doc, add a From line (BL/1031400/1663/$50) and a To line (BL/1031420/1663/$50), route it, and assert workflow status ENROUTE. Negative path: object code 1800 is rejected with the "Mandatory Transfer" / "Non-Mandatory Transfer" object-sub-type business rule error. |
| `test_04_document_search.py` | Document Search (KEW): finds the routed TF document by document id with route status ENROUTE. |
| `test_05_module_smoke.py` | Broad read-only coverage: Vendor (kfs-vnd), Chart, Organization, Object Code (kfs-coa) lookups return rows; Chart `BL` inquiry renders; Disbursement Voucher (kfs-fp) and Requisition (kfs-purap) initiation screens load with the expected form markers and no incident report. |

Notes:

- `test_03` and `test_04` share one routed TF document (a session-scoped
  fixture), so each full run creates exactly one TF document plus one
  discarded TF document used for the negative-validation check.
- The suite is read-only except for the Transfer of Funds documents it
  creates.
