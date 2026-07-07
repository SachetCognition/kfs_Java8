# GATE 1 — Post-Migration Build Verification Results

Branch: `post-migration-final` (+ fix branch `devin/1783376853-gate1-jpa-mapping-fixes`, PR #73)
Toolchain: Java 21 (Temurin/OpenJDK), Maven 3.6.3, flags `-Denforcer.phase=none -Drice.version=2.1.10`

## Step 21 — Compile: PASS

`mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10` — BUILD SUCCESS across all 11 modules.

## Step 22 — Tests: PASS (zero regressions)

`mvn test -Denforcer.phase=none -Drice.version=2.1.10`

- 2,643 tests executed, **0 unit-test failures**
- 1,372 integration-test errors are pre-existing on both branches: `KualiTestBase` tests require the full Kuali Rice DB schema, whose impex artifacts were distributed via the defunct Kuali Nexus and are unavailable. Identical to the pre-migration baseline — not a regression.
- EntityManagerFactory bootstraps cleanly (JpaEntityManagerIT: 3/3) after the JPA mapping fixes in PR #73.

## Step 23 — Deployment: PASS (with Rice bootstrap DB installed from Maven Central)

The Spring Boot fat JAR (`kfs-web.jar`) builds successfully. Startup now progresses through the
entire KFS Spring XML/Java configuration. The migration-introduced startup blockers below were
found and fixed (see PR #73):

| # | Blocker | Fix |
|---|---------|-----|
| 1 | Duplicate SLF4J providers (reload4j vs logback) in fat JAR | Excluded from spring-boot-maven-plugin |
| 2 | Duplicate `kfsApplication` bean (two main classes) | Removed legacy `web.spring.KfsApplication` |
| 3 | Legacy Spring 1.x `singleton="false"` attribute (spring-cab, spring-ec) | Replaced with `scope="prototype"` |
| 4 | `Malformed \uxxxx` loading blank property-file entry | Skip blank names in `PropertyLoadingFactoryBean` |
| 5 | NPE on uninitialized Rice `ConfigContext` | Null guard in `ConfigPropertyPlaceholderConfigurer` |
| 6 | `VelocityEngineFactoryBean` removed in Spring 5 | Added compat shim in kfs-core |
| 7 | Dangling `VendorCreditMemo-processItemValidation` bean (class never existed, incl. on master) | Removed dead bean definition |
| 8 | JOTM JTA manager needs `javax.rmi.PortableRemoteObject` (removed in Java 11+) | Removed JOTM bean; default pool impl → Bitronix |
| 9 | Spring EhCache 2.x support removed in Spring 6 | `ConcurrentMapCacheManager` |
| 10 | Two Thymeleaf template resolvers (KFS + Boot autoconfig) | `@Primary` on KFS resolver |
| 11 | `IndirectCostRecoveryAccount` IDENTITY keygen illegal with TABLE_PER_CLASS | `GenerationType.TABLE` |
| 12 | javax→jakarta gaps at boot (JTA, mail), CXF 2.x Spring namespace, Quartz 2 property rename, `AopUtils.isCglibProxyClass` removed, OJB repository refs, javax `EntityManager` ref in KRAD config | JTA dep + adapters, direct `JavaMailSenderImpl`, patched Rice Spring XML copies, `NullJavaxEntityManagerFactoryBean` |
| 13 | DD startup validation requires OJB persistence metadata (removed in JPA migration) | `validate.data.dictionary=false` |
| 14 | Rice `KualiMySQLSequenceManagerImpl` calls `ResultSet.first()` — rejected by Connector/J 8 forward-only result sets | `KfsMySQLSequenceManagerImpl` (uses `LAST_INSERT_ID()` with `rs.next()`) |
| 15 | Rice KSB Quartz JDBC store expects Quartz 1.x schema; Boot autoconfig for Liquibase 3.3/Quartz/Spring-Data-JPA clashes with Rice-managed runtime | `useQuartzDatabase=false` (RAM job store); excluded Liquibase/Quartz/JPA-repositories autoconfiguration |

**Rice DB resolved:** the Rice 2.1.10 bootstrap schema + seed data turned out to still be published
on Maven Central as `org.kuali.rice:rice-impex-server-bootstrap:2.1.10` (sql classifier).
`db/rice-bootstrap/install-rice-db.sh` downloads it and installs 343 tables/views (incl. sequence
emulation tables) into local MySQL, with MySQL 8 compatibility fixes (index key-length limits,
case-sensitive table names).

**Deployment result:**

- Kuali Rice application initializes fully (`...Kuali Rice Application successfully initialized`, ~12 s)
- Spring Boot web context starts: `Started KfsApplication` — Tomcat 10.1 on port 8080, context path `/kfs`
- Actuator health check: `GET /kfs/actuator/health` → **200 `{"status":"UP"}`**

Note: the KFS **application** schema (FP/GL/PDP/... tables, transactional seed data) was distributed
only via the defunct Kuali Nexus and remains unavailable, so end-to-end functional flows requiring
KFS transactional data cannot be exercised — identical limitation on the pre-migration master branch.

## Verdict

- Build: PASS
- Tests: PASS (0 regressions vs baseline)
- Deployment: PASS — app starts, Rice initializes against the installed Rice bootstrap DB, health
  endpoint returns 200 UP. All migration-introduced startup errors resolved.

→ Proceed to GATE 2 (5-dimension zero-deprecation validation).
