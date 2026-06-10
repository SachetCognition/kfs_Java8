# Skill: Build and Test KFS

Build and run tests for the Kuali Financial System. Works on both `master` (pre-migration) and `post-migration-final` branches.

## Steps

### 1. Detect Branch Context

```bash
BRANCH=$(git rev-parse --abbrev-ref HEAD)
echo "Current branch: $BRANCH"
```

Determine if you're on `master` (Java 8) or a post-migration branch (Java 21) to set the correct `JAVA_HOME`.

### 2. Set Java Environment

**For `master` or branches based on it:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

**For `post-migration-final` or branches based on it:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

If unsure, check `pom.xml` for `<maven.compiler.source>`. Value `8` = Java 8, value `21` = Java 21.

### 3. Verify Stub JARs

Before building, confirm the stub JARs exist in `~/.m2/repository/`:

```bash
ls ~/.m2/repository/org/jacorb/jacorb/2.2.3-jonas-patch-20071018/jacorb-2.2.3-jonas-patch-20071018.jar
ls ~/.m2/repository/org/kuali/kfs/kfs-help/5.4.0/kfs-help-5.4.0.jar
ls ~/.m2/repository/p6spy/p6spy/1.3-patched/p6spy-1.3-patched.jar
ls ~/.m2/repository/xapool/xapool/1.5.0-patch4/xapool-1.5.0-patch4.jar
```

If any are missing, the environment blueprint's `initialize` section needs to be re-run. See `AGENTS.md` for the full list of stub JARs.

### 4. Build (Skip Tests)

```bash
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
```

**Required flags (every invocation):**
- `-Denforcer.phase=none` — parent POM enforcer references unavailable artifacts
- `-Drice.version=2.1.10` — Rice 2.1.9 was never published to Maven Central

### 5. Run Unit Tests

```bash
# All modules
mvn test -Denforcer.phase=none -Drice.version=2.1.10

# Single module
mvn test -pl kfs-core -Denforcer.phase=none -Drice.version=2.1.10

# Specific test class
mvn test -pl kfs-core -Dtest=DynamicCollectionComparatorTest -Denforcer.phase=none -Drice.version=2.1.10
```

### 6. Integration Tests (Advanced)

Integration tests require the full Kuali Rice + KFS database schema (hundreds of tables). The schema DDL was distributed via the defunct Kuali Nexus impex artifacts and is **not in the repo**. Most tests will fail with database connection or missing table errors — this is expected.

### 7. Interpret Results

- Build failures mentioning "Could not resolve artifact" = missing stub JARs (step 3)
- Test failures in `*IT.java` or `*IntegrationTest.java` = expected without Rice DB
- Surefire `--add-opens` warnings on Java 21 = expected and harmless
- `maven-enforcer-plugin` failures = forgot `-Denforcer.phase=none`
