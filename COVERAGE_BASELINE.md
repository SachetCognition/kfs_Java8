# JaCoCo Coverage Baseline

**Date:** 2026-06-09  
**Java Version:** 21 (OpenJDK 21.0.11)  
**JaCoCo Version:** 0.8.12  
**Build:** `mvn test -Denforcer.phase=none -Drice.version=2.1.10 -Dmaven.test.failure.ignore=true`

## Current State (Pre-Migration)

The existing test suite consists of ~2,600 test methods across all modules. The vast majority
(~2,450) are integration tests that require the full Kuali Rice + KFS database schema (hundreds
of tables distributed via the now-defunct Kuali Nexus impex artifacts — not available in this repo).

### Pure Unit Tests (pass without DB)

| Module | Tests Run | Passed | Failed | Errors | Coverage |
|--------|-----------|--------|--------|--------|----------|
| kfs-core | 5 | 5 | 0 | 0 | < 1% (DynamicCollectionComparatorTest only) |
| kfs-ar | 58 | 58 | 0 | 0 | < 1% (batch digester + billing period tests) |
| kfs-ld | 17 | 17 | 0 | 0 | < 1% (ObjectUtilTest) |
| kfs-ec | 4 | 4 | 0 | 0 | < 1% (AccountingPeriodMonthTest) |
| kfs-purap | 0 | - | - | - | 0% |
| kfs-cam | 0 | - | - | - | 0% |
| kfs-cg | 0 | - | - | - | 0% |
| kfs-bc | 0 | - | - | - | 0% |
| kfs-tem | 0 | - | - | - | 0% |
| kfs-kc | 0 | - | - | - | 0% |

### Integration Tests (require Rice DB — currently error out)

| Module | Total Tests | Errors (no DB) |
|--------|-------------|----------------|
| kfs-core | 1,410 | 1,405 |
| kfs-purap | 356 | 356 |
| kfs-ar | 368 | 310 |
| kfs-cam | 74 | 74 |
| kfs-cg | 23 | 23 |
| kfs-ld | 96 | 79 |
| kfs-ec | 113 | 109 |
| kfs-bc | 32 | 32 |
| kfs-tem | 63 | 63 |

### Summary

- **Total test methods:** ~2,619
- **Pure unit tests passing:** 84
- **Integration tests erroring (no DB):** ~2,451
- **Effective branch coverage:** < 1% across all modules
- **Target (Phase 1):** ≥ 85% branch coverage per module

## Migration Target

After Phase 1 (14 parallel test-writing sessions), each module must achieve ≥85% branch
coverage measured by JaCoCo. The JaCoCo check rule is configured in the root pom.xml to
enforce this at the `verify` phase.

## Notes

- The `--add-opens` JVM flags in surefire configuration are required for OJB CGLIB proxies
  (PersistentFieldDirectImpl). These will be removed in S-2-CLEANUP after OJB is gone.
- JUnit Vintage Engine is included to run the existing JUnit 4 tests alongside new JUnit 5 tests.
- The JaCoCo 85% enforcement is currently configured but will fail until Phase 1 tests are written.
  To run the build without the coverage gate, use: `mvn verify -Djacoco.skip=true`
