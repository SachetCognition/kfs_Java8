# AGENTS.md — KFS (Kuali Financial System)

## Repository Overview

KFS is an enterprise financial management system for higher education (accounting, procurement, travel, assets, grants, budgeting). It is a **Maven multi-module Java project** with a document-centric workflow model built on the Kuali Rice middleware framework.

**Dual-branch structure:**

| Branch | State | Stack |
|--------|-------|-------|
| `master` | Pre-migration baseline | Java 8, OJB, Struts 1, Log4j 1.x, Spring XML |
| `post-migration-final` | Migrated (PR #69) | Java 21, JPA/Hibernate 6.4, Spring Boot 3.3.5, Spring MVC, Thymeleaf, Log4j 2.x |

**Do NOT push directly to `master` or `post-migration-final`.** Always create a feature branch.

---

## Build & Run

### Prerequisites

- Java 8 JDK (for `master` branch) or Java 21 (for `post-migration-final`)
- Maven 3+
- MySQL 8+ (or Oracle 11g+ with `-Poracle`)
- Maven `settings.xml` must mirror `kuali.nexus` to Maven Central (the Kuali Nexus server is defunct)

### Critical Build Flags

Every `mvn` invocation **must** include:

```bash
-Denforcer.phase=none -Drice.version=2.1.10
```

- `-Denforcer.phase=none` — disables the Maven enforcer plugin (parent POM references unavailable artifacts)
- `-Drice.version=2.1.10` — overrides `rice.version` from 2.1.9 (never published to Maven Central) to 2.1.10

### Common Commands

```bash
# Full build (skip tests)
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && export PATH=$JAVA_HOME/bin:$PATH
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10

# Run tests (unit tests only — integration tests require Kuali Rice DB schema)
mvn test -Denforcer.phase=none -Drice.version=2.1.10

# Run a single module's tests
mvn test -pl kfs-core -Denforcer.phase=none -Drice.version=2.1.10

# Run a specific test class
mvn test -pl kfs-core -Dtest=DynamicCollectionComparatorTest -Denforcer.phase=none -Drice.version=2.1.10

# Run the application (master branch)
mvn tomcat7:run-war -Denforcer.phase=none -Drice.version=2.1.10
```

### Stub JARs

Seven artifacts from the defunct Kuali Nexus must be installed as stubs or replacements in the local Maven repo before building. These are handled by the environment blueprint's `initialize` section. If you get "Could not resolve artifact" errors, check `~/.m2/repository/` for these:

| GroupId | ArtifactId | Version | Notes |
|---------|-----------|---------|-------|
| `org.jacorb` | `jacorb` / `jacorb-idl` | `2.2.3-jonas-patch-*` | Empty stub |
| `org.kuali.kfs` | `kfs-help` | `5.4.0` | Empty stub |
| `p6spy` | `p6spy` | `1.3-patched` | Empty stub |
| `xapool` | `xapool` | `1.5.0-patch4` | Real `com.experlog:xapool:1.5.0` |
| `commons-net-ftp` | `commons-net-ftp` | `2.0` | Real `commons-net:commons-net:2.0` |
| `org.directwebremoting` | `dwr` | `3.0.RC2` | Merged 2.x + 3.x JARs |

---

## Module Architecture

11 Maven modules. All functional modules depend on `kfs-core`. `kfs-web` is the WAR assembly point.

```
kfs-core     Foundation: COA, GL, FP, PDP, VND, SEC, SYS
kfs-ar       Accounts Receivable
kfs-purap    Purchasing & Accounts Payable
kfs-tem      Travel & Entertainment
kfs-cam      Capital Asset Management
kfs-bc       Budget Construction
kfs-cg       Contracts & Grants
kfs-kc       Kuali Coeus Integration
kfs-ld       Labor Distribution
kfs-ec       Effort Certification
kfs-web      WAR assembly (JSPs/Thymeleaf, Struts/Spring MVC configs)
```

### Module Internal Structure Pattern

```
module/
├── batch/              # Quartz-scheduled batch steps/jobs
├── businessobject/     # Business Objects (OJB on master, JPA on post-migration)
│   ├── datadictionary/ # XML data dictionary definitions
│   ├── lookup/         # Lookupable helper services
│   └── options/        # Values finders for dropdowns
├── dataaccess/         # DAO interfaces + implementations
├── document/           # Document classes + authorization + validation
│   ├── service/        # Service interfaces + impls
│   ├── validation/     # Business rules & validation (pure Java — DO NOT CHANGE)
│   └── web/struts/     # Struts Action + Form (master) or Spring MVC (post-migration)
├── identity/           # KIM role type services
├── report/             # Report data holders + services
├── service/            # Module service interfaces + impls
└── spring-*.xml        # Spring bean definitions
```

### Cross-Module Integration

Modules integrate via **externalizable business objects**: `kfs-core` defines integration interfaces in `org.kuali.kfs.integration.*`, and each functional module maps them to concrete implementations in its `spring-*.xml`. Core code references only the interfaces.

---

## Code Conventions

### Zero Business Logic Loss Rule

**Service interfaces** (in `service/` and `dataaccess/` packages) **MUST NOT** change method signatures. Only implementations may be swapped. Business rule classes in `document/validation/` are pure Java and require zero changes during technology migrations.

### Testing

- **Unit tests**: Work without a database. Named `*Test.java` or `*UnitTest.java`.
- **Integration tests**: Require the full Kuali Rice + KFS database schema (hundreds of tables). The schema DDL was distributed via the now-defunct Kuali Nexus impex artifacts and is **not available in this repo**.
- Framework: JUnit 5 + Mockito 5 + AssertJ (post-migration), JUnit 4 (master).
- JaCoCo target: >= 85% branch coverage per module (post-migration).
- Surefire JVM args include `--add-opens` for CGLIB compatibility on Java 21.

### Naming

- Branches: `devin/<timestamp>-<description>` for feature branches
- Migration branches followed: `migration/S-<phase>-<module>` (e.g., `migration/S-2-COA`)
- Test classes: `<ClassName>Test.java` or `<ClassName>UnitTest.java`

### Spring Configuration

- `master`: XML-based Spring config (`spring-*.xml` per module)
- `post-migration-final`: 14 `@Configuration` classes replacing XML, plus `KfsApplication` Spring Boot entry point

### ORM

- `master`: Apache OJB with `ojb-*.xml` mapping files
- `post-migration-final`: JPA with `@Entity`/`@Table`/`@Column`/`@Id` annotations, `persistence.xml`, `JpaConfig`

### Logging

- `master`: SLF4J -> Log4j 1.x
- `post-migration-final`: SLF4J + Log4j 2.x native (`log4j2.xml`)

---

## Key Files Reference

| File | Purpose |
|------|---------|
| `pom.xml` (root) | All dependency versions, module list, profiles, plugin management |
| `DEVELOPER_GUIDE.md` | Comprehensive onboarding guide (architecture, metrics, glossary) |
| `REVIEW.md` | Review agent checkpoint criteria for every migration milestone |
| `COVERAGE_BASELINE.md` | Pre-migration test coverage baseline |
| `kfs-core/src/main/resources/kfs-default-config.properties` | Default app config |
| `kfs-core/src/main/resources/META-INF/persistence.xml` | JPA persistence unit (post-migration) |
| `kfs-core/src/main/java/org/kuali/kfs/sys/context/JpaConfig.java` | JPA configuration (post-migration) |
| `kfs-web/src/main/java/org/kuali/kfs/KfsApplication.java` | Spring Boot entry point (post-migration) |
| `kfs-core/src/main/resources/log4j2.xml` | Log4j 2 configuration (post-migration) |

---

## Migration Context

The `post-migration-final` branch represents a complete 4-phase migration executed across 33+ sessions and 20+ PRs:

| Phase | What | Key Changes |
|-------|------|-------------|
| 0 | Build modernization | Java 21 target, JaCoCo, JUnit 5, Mockito 5, Testcontainers |
| 1 | Unit tests | ~2,900 new tests across all 14 modules (>= 85% branch coverage) |
| 2 | OJB -> JPA | 4,636 JPA annotations, OJB XML removed, persistence.xml + JpaConfig |
| 3 | Spring + Logging | Rice adapter layer, 14 @Configuration classes, 1,200 files Log4j 1->2 |
| 4 | Web layer | 15 Spring MVC controllers, 174 Thymeleaf templates, Spring Boot entry point |

Metrics: 8,378 -> 9,051 files (+673), 624 -> 975 test files (+351), zero service interfaces removed, zero BOs deleted.

---

## Skills

See `.devin/skills/` for reusable automation skills:

| Skill | Purpose |
|-------|---------|
| `build-and-test.md` | Standard build + test workflow for both branches |
| `add-unit-tests.md` | Adding JUnit 5 unit tests to a KFS module |
| `jpa-annotate-module.md` | Adding JPA annotations to a module's business objects |
| `migrate-struts-to-spring-mvc.md` | Converting Struts Action/Form to Spring MVC controller + Thymeleaf |
| `comparative-analysis.md` | Running pre/post migration comparison |
