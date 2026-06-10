# REVIEW.md — KFS Migration Review Checkpoints

> Coding standard enforcement guide for review agents. Each milestone defines
> what changed, what to verify, and specific pass/fail criteria.

---

## How to Use This Document

This migration is structured as **5 phases, 20 milestones**. Each milestone
produces a PR. The review agent should evaluate every PR against the checkpoint
criteria listed for that milestone **before** the PR is merged.

**Verdict vocabulary:**
- **PASS** — criterion fully met
- **FLAG** — potential issue found, needs human judgment
- **FAIL** — criterion violated, PR must not merge until fixed

---

## Phase 0 — Build Modernization

### Milestone 0A: Test Harness + Java 21 Target

**Scope:** Root `pom.xml` changes only. No source code changes.

**What changes:**
- `maven.compiler.source/target/release` → `21`
- Add JUnit 5 (`junit-jupiter`), Mockito 5, Testcontainers, AssertJ dependencies
- Add JaCoCo plugin with 85% branch coverage minimum
- Add `surefire.jvm.args` with `--add-opens` flags for CGLIB on Java 21
- Add default `<argLine/>` property so `${argLine}` resolves when JaCoCo is skipped

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | Java version target | `<maven.compiler.source>`, `<maven.compiler.target>`, `<maven.compiler.release>` all set to `21` |
| 2 | JUnit 5 dependency | `junit-jupiter` in `<dependencyManagement>` with scope `test` |
| 3 | JUnit 4 not removed | JUnit 4 dependency remains (legacy tests still need it) |
| 4 | Mockito version | Mockito 5.x (not 4.x — required for Java 21 `--add-opens` compatibility) |
| 5 | JaCoCo plugin | `jacoco-maven-plugin` in `<pluginManagement>` with `prepare-agent` + `report` goals |
| 6 | Surefire `--add-opens` | `<surefire.jvm.args>` contains `--add-opens` for `java.lang`, `java.lang.reflect`, `java.util`, `java.math`, `java.io` |
| 7 | `argLine` default | `<argLine/>` (empty) property defined so `${argLine}` doesn't break when JaCoCo skipped |
| 8 | Child module propagation | Surefire `<argLine>` in `<pluginManagement>` uses `${argLine} ${surefire.jvm.args}` |
| 9 | No source changes | Zero `.java` files modified |
| 10 | Build succeeds | `mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |

---

### Milestone 0B: Dependency Coordinate Migration

**Scope:** Root `pom.xml` dependency updates. No source code changes.

**What changes:**
- Update MySQL connector coordinates (`mysql:mysql-connector-java` → `com.mysql:mysql-connector-j`)
- Add Jakarta namespace dependencies (persistence, servlet, mail, inject, annotation, activation, xml.bind)
- Add Hibernate 6.4 dependency
- Add Spring Boot 3.3.5 BOM
- Pin OpenRewrite plugin for automated recipe execution

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | MySQL connector | `<mysql.groupId>com.mysql</mysql.groupId>`, `<mysql.artifactId>mysql-connector-j</mysql.artifactId>` |
| 2 | Jakarta dependencies | `jakarta.persistence-api`, `jakarta.servlet-api`, `jakarta.mail-api`, `jakarta.inject-api`, `jakarta.annotation-api` present |
| 3 | No `javax.*` additions | No new `javax.*` dependencies added (only Jakarta equivalents) |
| 4 | Hibernate version | `<hibernate.version>6.4.x.Final</hibernate.version>` |
| 5 | Spring Boot BOM | `<spring-boot.version>3.3.x</spring-boot.version>` |
| 6 | No source changes | Zero `.java` files modified |
| 7 | Version properties | All new versions managed via `<properties>`, not hard-coded in dependency blocks |
| 8 | Build succeeds | `mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |

---

## Phase 1 — Unit Test Coverage

> Phase 1 consists of **one milestone per module** (up to 14 modules).
> All module milestones share the same review checklist. They can be
> executed in parallel.

### Milestones 1A–1N: Unit Tests per Module

**Modules:** GL, COA, SYS, FP, PDP, VND, PURAP, AR, CAM, CG, EC, LD, TEM, BC

**Scope per milestone:** New `src/test/java/` files in one module only.

**What changes:**
- New JUnit 5 test classes for business objects, services, validation rules, batch steps
- No production source changes

**Review Checklist (apply to each module PR):**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | JUnit 5 only | All new tests use `org.junit.jupiter.api.*`, not `org.junit.Test` |
| 2 | No JUnit 4 imports | No `import org.junit.Test`, `import org.junit.Before`, etc. in new files |
| 3 | Mockito annotations | Tests use `@Mock` + `MockitoAnnotations.openMocks(this)` or `@ExtendWith(MockitoExtension.class)`, not `@RunWith` |
| 4 | AssertJ preferred | Assertions use `assertThat(...)` (AssertJ) over `assertEquals(...)` (JUnit) where possible |
| 5 | No production changes | Zero files changed under `src/main/` |
| 6 | Test naming | Classes end with `Test` or `UnitTest`; methods use descriptive names (`should...`, `when...Then...`) |
| 7 | No Spring context | Tests do NOT load Spring application context (`@SpringBootTest`, `SpringContext.getBean()`) — pure unit tests only |
| 8 | Mocking boundaries | External dependencies (services, DAOs) are mocked, not instantiated |
| 9 | Coverage target | JaCoCo branch coverage >= 85% for the module (check `target/site/jacoco/index.html`) |
| 10 | No test data in prod | Test data/fixtures are in `src/test/resources/`, not `src/main/resources/` |
| 11 | `@DisplayName` usage | Tests have descriptive `@DisplayName` annotations |
| 12 | Edge cases covered | Null inputs, empty collections, boundary values are tested |
| 13 | Tests compile | `mvn test-compile -pl <module> -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |
| 14 | Tests pass | `mvn test -pl <module> -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |

---

## Phase 2 — OJB to JPA Migration

### Milestone 2-SETUP: JPA/Hibernate Infrastructure

**Scope:** JPA infrastructure files in `kfs-core` only.

**What changes:**
- `persistence.xml` in `META-INF/`
- `JpaConfig.java` — `@Configuration` class for EntityManagerFactory, transaction manager
- `BooleanYNConverter.java` — JPA `AttributeConverter` for `Y`/`N` char booleans
- Hibernate and Jakarta Persistence dependencies wired in `pom.xml`

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | `persistence.xml` location | Exists at `kfs-core/src/main/resources/META-INF/persistence.xml` |
| 2 | No hardcoded dialect | `persistence.xml` does NOT contain `<property name="hibernate.dialect" .../>` — let Hibernate auto-detect |
| 3 | JpaConfig class | `@Configuration` class with `@Bean` methods for `LocalContainerEntityManagerFactoryBean` and `PlatformTransactionManager` |
| 4 | DataSource injection | `JpaConfig` injects `DataSource`, does NOT create its own connection pool |
| 5 | BooleanYNConverter | Implements `AttributeConverter<Boolean, String>`, converts `true`↔`"Y"`, `false`↔`"N"` |
| 6 | No JDBC placeholders | No `${...}` placeholders in `persistence.xml` that would fail without Spring resolution |
| 7 | Build succeeds | Full build passes |

---

### Milestones 2A–2K: JPA Annotations per Module

**Modules:** COA, GL, FP, AR, SYS, CAM, CG, PURAP, PDP, VND, LD, EC, TEM, BC, KC

**Scope per milestone:** JPA annotations added to all business objects in one module.

**Review Checklist (apply to each module PR):**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | `@Entity` on all BOs | Every class in `businessobject/` that has an OJB mapping has `@Entity` |
| 2 | `@Table` correct | `@Table(name = "...")` matches the table name from the corresponding `ojb-*.xml` `<class-descriptor>` |
| 3 | `@Id` on primary keys | All primary key fields (from OJB `primarykey="true"`) have `@Id` |
| 4 | `@Column` names match | `@Column(name = "...")` matches OJB `<field-descriptor column="...">` |
| 5 | Boolean converter | `Y`/`N` boolean fields use `@Convert(converter = BooleanYNConverter.class)` |
| 6 | Lazy fetch on relations | `@ManyToOne(fetch = FetchType.LAZY)`, `@OneToMany` with `mappedBy` |
| 7 | `insertable/updatable` | Foreign key `@JoinColumn` annotations use `insertable = false, updatable = false` to avoid duplicate column mapping |
| 8 | `@Transient` for non-persisted | Fields previously not in OJB mapping are annotated `@Transient` |
| 9 | Composite keys | Tables with composite PKs use `@IdClass` or `@EmbeddedId` correctly |
| 10 | No method signature changes | Zero changes to any method signature in service interfaces or validation classes |
| 11 | No business logic changes | Only annotation additions — no logic modifications in getters/setters/methods |
| 12 | `persistence.xml` updated | All new `@Entity` classes registered in `persistence.xml` |
| 13 | Jakarta imports | Uses `jakarta.persistence.*`, not `javax.persistence.*` |
| 14 | Existing tests pass | All pre-existing unit tests still pass |
| 15 | Build succeeds | `mvn compile -pl <module> -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |

---

## Phase 3 — Spring Configuration + Logging Migration

### Milestone 3A: Rice Adapter Layer

**Scope:** New adapter classes in `kfs-core` that decouple KFS from `org.kuali.rice.*`.

**What changes:**
- Adapter interfaces/classes in `org.kuali.kfs.sys.adapter` (or similar)
- Wrap Rice APIs behind KFS-owned interfaces so Rice can be swapped later
- No existing class signatures changed

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | Adapter pattern | Each adapter wraps a Rice API behind a KFS-owned interface |
| 2 | No Rice API leakage | Adapter interfaces do NOT expose `org.kuali.rice.*` types in their method signatures |
| 3 | Delegation only | Adapter implementations delegate to Rice — zero business logic in adapters |
| 4 | No service interface changes | All existing `service/` interfaces unchanged |
| 5 | No validation changes | Zero files changed in `document/validation/` |
| 6 | Adapter tests | New adapter classes have unit tests verifying delegation |
| 7 | Build succeeds | Full build passes |

---

### Milestone 3B: Spring XML → Java @Configuration

**Scope:** Convert XML-based Spring bean definitions to `@Configuration` classes.

**What changes:**
- New `@Configuration` classes (one per module) replacing `spring-*.xml` files
- Bean definitions moved from XML `<bean>` elements to `@Bean` methods
- XML files may be removed or kept as fallback

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | One config per module | Each module has exactly one `@Configuration` class (or a small, justified set) |
| 2 | `@Bean` method names | Bean method names match the original XML `id` attributes for compatibility |
| 3 | Scope preservation | Beans that were `scope="prototype"` in XML are `@Scope("prototype")` in Java config |
| 4 | Dependency injection | Constructor injection preferred; `@Autowired` on fields only if matching XML pattern |
| 5 | No circular dependencies | No `@Lazy` annotations masking circular dependency issues |
| 6 | Property injection | `@Value("${property.name}")` replaces XML `<property value="${...}"/>` |
| 7 | Module registration | `FinancialSystemModuleConfiguration` beans correctly declare `namespaceCode`, `packagePrefixes`, `dataDictionaryPackages` |
| 8 | Externalizable BO maps | `externalizableBusinessObjectImplementations` maps are faithfully reproduced |
| 9 | Batch job beans | Quartz job/trigger beans correctly wired with `jobNames`/`triggerNames` |
| 10 | No service interface changes | Zero changes to any `*Service.java` interface |
| 11 | No validation changes | Zero changes to `document/validation/` |
| 12 | Build succeeds | Full build passes |

---

### Milestone 3C: Log4j 1.x → Log4j 2.x / SLF4J

**Scope:** Logging framework migration across all modules.

**What changes:**
- Replace `import org.apache.log4j.Logger` with `import org.slf4j.Logger` + `import org.slf4j.LoggerFactory`
- Replace `Logger.getLogger(Class)` with `LoggerFactory.getLogger(Class)`
- Add `log4j2.xml` configuration file
- Add Log4j 2 dependencies, add `log4j-1.2-api` bridge for transitive deps
- Remove direct Log4j 1.x dependency (or mark as `provided`)

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | No Log4j 1.x imports | Zero `import org.apache.log4j.*` in any changed file |
| 2 | SLF4J API only | All logger declarations use `org.slf4j.Logger` and `org.slf4j.LoggerFactory` |
| 3 | Logger declaration pattern | `private static final Logger LOG = LoggerFactory.getLogger(ClassName.class)` |
| 4 | No `Logger.getLogger()` | Zero calls to `Logger.getLogger()` (Log4j 1.x API) in changed files |
| 5 | Parameterized logging | String concatenation in log calls replaced with `{}` placeholders: `LOG.debug("Value: {}", val)` not `LOG.debug("Value: " + val)` |
| 6 | Log4j 2 config | `log4j2.xml` exists in `kfs-core/src/main/resources/` |
| 7 | Bridge dependency | `log4j-1.2-api` in `pom.xml` to bridge Log4j 1.x calls from Rice |
| 8 | Log4j 1.x scope | Direct `log4j:log4j` dependency is `<scope>provided</scope>` to avoid classpath conflict |
| 9 | No business logic changes | Only logging-related lines changed — no method signature or logic changes |
| 10 | Build succeeds | Full build passes |
| 11 | No duplicate log4j | `mvn dependency:tree` shows no duplicate log4j JARs on the classpath |

---

## Phase 4 — Web Layer Migration

### Milestone 4A: Struts 1 → Spring MVC Controllers

**Scope:** New Spring MVC controllers in `kfs-web`, replacing Struts Action classes.

**What changes:**
- New `@Controller` classes in `kfs-web/src/main/java/org/kuali/kfs/web/spring/controller/`
- One controller per functional module plus a base controller and portal controller
- Controllers delegate to existing service layer — zero business logic in controllers

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | `@Controller` annotation | Every controller class has `@Controller` |
| 2 | `@RequestMapping` | Module-level URL prefixes match Struts action paths |
| 3 | Base controller | `KfsBaseController` exists as a shared parent with common functionality |
| 4 | No business logic | Controllers contain ONLY request mapping + delegation to services — zero calculations, zero validation logic |
| 5 | Service delegation | Controllers call existing `*Service` interfaces, not concrete implementations |
| 6 | Model attributes | Data passed to views via `Model.addAttribute()`, not `request.setAttribute()` |
| 7 | Return types | Methods return `String` (template name) or `redirect:` prefix — no raw `void` + response writing |
| 8 | No service changes | Zero changes to any `*Service.java` interface or implementation |
| 9 | No validation changes | Zero changes to `document/validation/` |
| 10 | Input validation | `@Valid` or manual validation calls present for form submissions |
| 11 | Error handling | `@ExceptionHandler` or error forwarding for service exceptions |
| 12 | Build succeeds | `mvn compile -pl kfs-web -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |

---

### Milestone 4B: JSP → Thymeleaf Templates

**Scope:** New Thymeleaf HTML templates in `kfs-web/src/main/resources/templates/`.

**What changes:**
- 174 Thymeleaf `.html` templates replacing JSP views
- Templates use `th:text`, `th:each`, `th:if`, `th:href`, `th:field` etc.
- CAS (authentication) templates for login flow
- Shared fragment templates (header, footer, navigation)

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | Template location | All templates under `kfs-web/src/main/resources/templates/` |
| 2 | Thymeleaf namespace | All HTML files declare `xmlns:th="http://www.thymeleaf.org"` |
| 3 | No JSP syntax | Zero `<c:out>`, `<c:forEach>`, `<c:if>`, `<bean:message>`, `<html:link>` in new templates |
| 4 | No scriptlets | Zero `<% %>` Java scriptlets in templates |
| 5 | Fragment reuse | Common elements (header, footer, nav) use `th:replace` or `th:insert` from fragment files |
| 6 | Form binding | Form inputs use `th:field="*{property}"` for model binding |
| 7 | URL expressions | Links use `th:href="@{/path}"` (context-relative), not hardcoded paths |
| 8 | Message keys | Labels use `th:text="#{key}"` for i18n, not hardcoded text (where the JSP used `<bean:message>`) |
| 9 | XSS safety | User-supplied data rendered with `th:text` (escaped), not `th:utext` (unescaped) unless justified |
| 10 | Functional parity | Each JSP has a corresponding Thymeleaf template with the same visible UI elements |
| 11 | No business logic | Templates contain ONLY presentation — no Java/Spring EL business calculations |

---

### Milestone 4C: Spring Boot Entry Point + Packaging

**Scope:** Spring Boot application class, executable JAR packaging, Spring 6.x compatibility.

**What changes:**
- `KfsApplication.java` with `@SpringBootApplication`
- `spring-boot-maven-plugin` for executable JAR packaging
- `application.properties` or `application.yml` for Boot configuration
- Rice framework decoupling (adapter layer replacing direct Rice class usage)

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | `@SpringBootApplication` | `KfsApplication` class annotated with `@SpringBootApplication` |
| 2 | Main method | `public static void main(String[] args) { SpringApplication.run(...) }` |
| 3 | Component scan | `@ComponentScan` base packages include `org.kuali.kfs` |
| 4 | No auto-config conflicts | Appropriate `@SpringBootApplication(exclude = {...})` for incompatible auto-configs |
| 5 | Properties file | `application.properties` or `application.yml` present with database, server config |
| 6 | Boot plugin | `spring-boot-maven-plugin` in `kfs-web/pom.xml` for executable JAR |
| 7 | Embedded server | Tomcat or equivalent embedded server configured (replaces `tomcat7-maven-plugin`) |
| 8 | Rice adapters | All Rice framework classes accessed through KFS adapter interfaces |
| 9 | No service changes | Zero changes to any `*Service.java` interface |
| 10 | No validation changes | Zero changes to `document/validation/` |
| 11 | Application starts | `java -jar kfs-web/target/kfs-web-*.jar` starts without fatal errors |
| 12 | Build succeeds | Full reactor build produces the executable JAR |

---

## Phase 5 — Post-Migration Verification

### Milestone 5A: Merge + Integration Verification

**Scope:** Merge all phase branches into `post-migration-final`. Build and test the fully integrated branch.

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | Clean merge | No unresolved merge conflicts |
| 2 | Full build | `mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10` exits 0 |
| 3 | All tests pass | `mvn test -Denforcer.phase=none -Drice.version=2.1.10` — all unit tests pass |
| 4 | No `javax.persistence` | Zero `import javax.persistence.*` in any file (all migrated to `jakarta.persistence.*`) |
| 5 | No Log4j 1.x | Zero `import org.apache.log4j.*` in any source file |
| 6 | No Struts actions | No new Struts `Action` classes (existing legacy may remain) |
| 7 | Master untouched | `master` branch has zero new commits since migration started |

---

### Milestone 5B: Comparative Analysis

**Scope:** Side-by-side comparison of `master` (pre) vs `post-migration-final` (post).

**Review Checklist:**
| # | Check | Pass Criteria |
|---|-------|---------------|
| 1 | Business Object parity | Same number of BOs in both branches (514 expected) |
| 2 | Document type parity | Same number of document types (52 expected) |
| 3 | Service interface parity | Same number of service interfaces with identical method signatures |
| 4 | Batch job parity | Same number of batch jobs registered |
| 5 | Zero deleted files | No production source files deleted (only infrastructure files like OJB XML, web.xml) |
| 6 | Test improvement | Post-migration has strictly more test files than pre-migration |
| 7 | Gap analysis report | Written report covering coding standards, architecture, functional capabilities |

---

## Global Enforcement Rules (Apply to EVERY Milestone)

These rules apply across all milestones. Flag any violation.

### Zero Business Logic Loss

| # | Rule | How to Verify |
|---|------|---------------|
| G1 | Service interfaces unchanged | `diff` all `*Service.java` files in `service/` packages — zero method signature changes |
| G2 | Validation rules unchanged | `diff` all files in `document/validation/` — zero logic changes |
| G3 | Batch step logic unchanged | `diff` all `*Step.java` files — only framework wiring changes, no business logic |
| G4 | Business object behavior preserved | BO getters/setters/computed methods return same values |
| G5 | No service deletion | No `*Service.java` or `*ServiceImpl.java` file deleted |

### Code Quality Standards

| # | Rule | How to Verify |
|---|------|---------------|
| Q1 | No `System.out.println` | Zero `System.out.println` or `System.err.println` in production code |
| Q2 | No `e.printStackTrace()` | Exception handling uses logger, not `printStackTrace()` |
| Q3 | No `@SuppressWarnings` without comment | Every `@SuppressWarnings` annotation has a code comment explaining why |
| Q4 | No wildcard imports | Zero `import foo.bar.*` — all imports fully qualified |
| Q5 | No raw types | Zero raw generic types (e.g., `List` instead of `List<String>`) in new code |
| Q6 | No `TODO` without ticket | `// TODO` comments reference a ticket/issue number or are flagged |
| Q7 | Consistent formatting | New code follows surrounding file's indentation/bracing style |
| Q8 | No dead code | No commented-out code blocks larger than 3 lines |

### Dependency Hygiene

| # | Rule | How to Verify |
|---|------|---------------|
| D1 | Version in properties | All dependency versions declared as `<properties>`, not inline |
| D2 | No snapshot deps | No `SNAPSHOT` versions except the project's own version |
| D3 | Scope correctness | Test dependencies have `<scope>test</scope>` |
| D4 | No duplicate deps | `mvn dependency:analyze` shows no duplicate declarations |

### Git Hygiene

| # | Rule | How to Verify |
|---|------|---------------|
| X1 | Atomic commits | Each commit addresses one logical change |
| X2 | Descriptive messages | Commit messages reference the milestone (e.g., `S-2-COA:`) |
| X3 | No target/ committed | Zero files from `target/` or build output directories |
| X4 | No secrets | Zero `.env`, credentials, API keys, passwords in committed files |
| X5 | No merge to master | `master` branch has zero new commits |

---

## Milestone Dependency Graph

```
Phase 0:  0A ──► 0B
                  │
Phase 1:          ├──► 1A (GL) ──┐
                  ├──► 1B (COA)──┤
                  ├──► 1C (SYS)──┤
                  ├──► 1D (FP) ──┤
                  ├──► 1E (PDP)──┤
                  ├──► 1F (VND)──┤
                  ├──► 1G (PURAP)┤
                  ├──► 1H (AR) ──┤
                  ├──► 1I (CAM)──┤
                  ├──► 1J (CG) ──┤
                  ├──► 1K (EC) ──┤
                  ├──► 1L (LD) ──┤
                  ├──► 1M (TEM)──┤
                  └──► 1N (BC) ──┤
                                 │
Phase 2:     2-SETUP ◄───────────┘
                  │
                  ├──► 2A (COA)──┐
                  ├──► 2B (GL) ──┤
                  ├──► 2C (FP) ──┤
                  ├──► 2D (AR) ──┤
                  ├──► 2E (SYS)──┤
                  ├──► 2F (CAM)──┤
                  ├──► 2G (CG) ──┤
                  ├──► 2H (PURAP)┤
                  ├──► 2I (PDP)──┤
                  ├──► 2J (VND)──┤
                  └──► 2K (...)──┤
                                 │
Phase 3:          3A ◄───────────┘
                  │
                  3B
                  │
                  3C
                  │
Phase 4:          4A
                  │
                  4B
                  │
                  4C
                  │
Phase 5:          5A ──► 5B
```

**Key:**
- `──►` = sequential dependency (must complete before next starts)
- Parallel branches within a phase can execute concurrently
- Phase 1 modules (1A–1N) are fully independent of each other
- Phase 2 modules (2A–2K) are fully independent of each other
- Phase 3–4 milestones are sequential within the phase
