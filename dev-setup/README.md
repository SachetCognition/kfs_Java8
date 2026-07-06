# KFS Local Development Setup

Steps to build, deploy, and run KFS locally with a fully populated MySQL demo database.

## Prerequisites

- JDK 8 (required — WorkflowImporter and parts of Rice need JAXB, removed in Java 11+)
- Maven 3.6+
- MySQL 8.x with `lower_case_table_names=1` (must be set when the data directory is initialized)

## 1. Maven configuration

The original Kuali Nexus (`nexus.kuali.org`) is dead. Copy `maven-settings.xml` to
`~/.m2/settings.xml` so the `kuali.nexus` repository is mirrored to Maven Central.

Always build with:

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
```

- `-Drice.version=2.1.10` — Rice 2.1.9 was never published to Maven Central.
- `-Denforcer.phase=none` — skips enforcer rules pinned to the dead Nexus.

## 2. Database (MySQL)

```sql
CREATE DATABASE kuldev;
CREATE USER 'kuldev'@'%' IDENTIFIED BY 'kuldev';
GRANT ALL ON kuldev.* TO 'kuldev'@'%';
```

Load schema + demo data with the Impex tool from https://github.com/kuali/kfs-db:

```bash
git clone https://github.com/kuali/kfs-db /tmp/kfs-db
cd /tmp/kfs-db/kfs-db/db-impex/impex
# fix a broken Liquibase template variable in build.xml first:
sed -i 's/--logLevel=\${liquibase.logLevel}/--logLevel=info/' build.xml
ant create-schema empty-schema import -Dimpex.properties.file=<your impex properties>
```

Then run the WorkflowImporter (Java 8) to load workflow document types from
`kfs-core/src/main/config/workflow`.

### Extend fiscal-year data

The demo data only covers fiscal years through 2016/2017. Run `extend-fiscal-years.sh`
to clone the year-dependent reference tables (object codes, accounting periods,
university dates, system options, etc.) forward so documents can be created with the
current system date:

```bash
./extend-fiscal-years.sh kuldev 2028
```

## 3. Runtime configuration

Copy `kfs-config.example.properties` somewhere (e.g. `~/kfs-config/kfs-config.properties`)
and generate the Rice keystore:

```bash
mkdir -p ~/kuali/kfs-core
keytool -genkeypair -alias rice -keyalg RSA -keysize 2048 -validity 3650 \
  -keystore ~/kuali/kfs-core/rice.keystore -storepass r1c3pw -keypass r1c3pw \
  -dname "CN=rice"
```

## 4. Run

```bash
cd kfs-web
export MAVEN_OPTS="-Xmx4g -Duser.language=en -Duser.country=US \
  -Dadditional.kfs.config.locations=$HOME/kfs-config/kfs-config.properties"
mvn org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run-war \
  -Denforcer.phase=none -Drice.version=2.1.10
```

`-Duser.language=en -Duser.country=US` is required: Rice's `CurrencyFormatter` parses
values like `$0.00` with the JVM default locale and throws `ParseException` on
non-US locales (e.g. `C.UTF-8`), which breaks every accounting-line screen.

App: http://localhost:8080/kfs-web-dev/portal.do (auto-login as `khuntley` via the
dev `DummyLoginFilter`).
