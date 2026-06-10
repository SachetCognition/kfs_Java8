# Skill: Run Pre/Post Migration Comparative Analysis

Compare the pre-migration (`master`) and post-migration (`post-migration-final`) branches to verify zero business logic loss and assess migration quality.

## Prerequisites

- Both `master` and `post-migration-final` branches available locally
- Java 8 JDK and Java 21 JDK installed
- Maven 3+ with stub JARs in local repo

## Steps

### 1. Build Both Branches

**Pre-migration (master):**
```bash
git checkout master
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 && export PATH=$JAVA_HOME/bin:$PATH
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
```

**Post-migration:**
```bash
git checkout post-migration-final
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 && export PATH=$JAVA_HOME/bin:$PATH
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
```

### 2. Analyze Five Dimensions

#### A. Coding Standards

Compare across both branches:
- Java version and language features used
- Logging framework (Log4j 1.x vs Log4j 2.x / SLF4J)
- Testing framework (JUnit 4 vs JUnit 5)
- Dependency management patterns

```bash
# Count deprecated API usage
grep -r "import javax\." --include="*.java" <branch-dir> | wc -l   # pre
grep -r "import jakarta\." --include="*.java" <branch-dir> | wc -l  # post
```

#### B. Business Module Inventory

For each module, verify:
- Business Object count is preserved
- Service interface method signatures unchanged
- Document types preserved
- Batch jobs preserved

```bash
# Count BOs per module
find <module>/src/main/java -path "*/businessobject/*.java" -not -name "*Test*" | wc -l

# Count service interfaces
find <module>/src/main/java -path "*/service/*Service.java" -not -path "*/impl/*" | wc -l
```

#### C. Technical Architecture

Compare:
- ORM layer (OJB XML files vs JPA annotations)
- Web framework (Struts vs Spring MVC)
- DI configuration (XML vs @Configuration)
- Application entry point (WAR/Tomcat vs Spring Boot JAR)

#### D. Functional Capabilities

Verify all functional areas are preserved:
- Document workflow routing (KEW)
- Batch job scheduling (Quartz)
- Report generation (JasperReports)
- Cross-module integration (externalizable BOs)

#### E. Pattern Recognition & Gap Analysis

Identify:
- New patterns introduced (e.g., JPA converters, Spring Boot auto-config)
- Patterns removed (OJB descriptors, Struts configs)
- Any gaps where functionality may need additional work

### 3. Generate Report

Create a comprehensive HTML report covering all 5 dimensions. Include:
- Executive summary with pass/fail verdict
- Per-module comparison tables
- File count and LOC metrics
- Specific examples of migration patterns

### 4. Key Metrics to Verify

| Metric | Pre | Post | Verdict |
|--------|-----|------|---------|
| Business Objects | 514 | 514 | PASS (zero loss) |
| Document Types | 52 | 52 | PASS |
| Service Interfaces | 158 | 158 | PASS |
| Batch Jobs | 47 | 47 | PASS |
| Test Files | 624 | 975 | IMPROVED (+351) |

### 5. Output

Deliver:
1. HTML report (self-contained, downloadable)
2. Gap analysis markdown document
3. Optional: screen recording showing both branches
