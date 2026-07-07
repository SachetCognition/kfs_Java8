# Dev Setup

## Building and running KFS

KFS builds and runs on **Java 8** (the original path, unchanged) and on **JDK 21**.

### Java 8 (original path — unchanged)

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
mvn tomcat7:run-war -pl kfs-web -Drice.version=2.1.10 -Denforcer.phase=none
```

### JDK 21

#### Build

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
mvn clean install -DskipTests -Denforcer.phase=none -Drice.version=2.1.10
```

No extra flags are needed for the build. A `jdk9plus-build` Maven profile in the
root `pom.xml` activates automatically on JDK 9+ and:

- sets `-source/-target` to `1.8` (JDK 21 javac no longer accepts `1.7`;
  bytecode stays at 8 so the old ASM/CGLIB stack in Spring 3.1 / OJB keeps
  working),
- upgrades `maven-compiler-plugin` to 3.13.0 and `maven-war-plugin` to 3.4.0
  (the versions inherited from `kuali-pom` fail to load on modern JDKs),
- skips the `guice-maven-plugin` `metadata` execution, which requires the
  `java.ext.dirs` system property removed in JDK 9.

The `javax.*` EE APIs removed from the JDK since Java 11 (JAXB, JAX-WS, SAAJ,
`javax.annotation`, `javax.activation`, and the CORBA `javax.rmi` classes
needed by JOTM) are added as explicit dependencies in the root `pom.xml`,
staying on the `javax` namespace.

#### Runtime

Reflection-heavy frameworks (OJB, CGLIB, JOTM/CAROL, XAPool, Quartz) need the
JVM flags listed in [`java21-runtime-flags.txt`](java21-runtime-flags.txt).

`tomcat7-maven-plugin` (2.2) still works under JDK 21 — it runs Tomcat inside
the Maven JVM, so pass the flags via `MAVEN_OPTS`:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
export MAVEN_OPTS="$(grep '^--' dev-setup/java21-runtime-flags.txt | tr '\n' ' ')"
# plus any -D properties you need, e.g.:
# export MAVEN_OPTS="$MAVEN_OPTS -Dadditional.kfs.config.locations=$HOME/kfs-config/kfs-config.properties"
mvn tomcat7:run-war -pl kfs-web -Drice.version=2.1.10 -Denforcer.phase=none -DskipTests
```

For a standalone Tomcat deployment, put the same flags in
`$CATALINA_BASE/bin/setenv.sh` via `CATALINA_OPTS`.

#### Known limitations on JDK 21

- Tomcat 7's annotation scanner logs harmless
  `ClassFormatException: Invalid byte tag in constant pool: 19` warnings for
  `module-info.class` entries inside the new javax API jars; they can be
  ignored.
- The `COMPAT` locale provider was removed in JDK 21, so
  `-Djava.locale.providers=COMPAT` cannot be used; locale-sensitive formatting
  follows CLDR data.
- Full webapp startup requires a database with the complete Kuali Rice + KFS
  schema. On JDK 21 the application initializes Spring, OJB, the JOTM/XAPool
  JTA stack and Quartz, and proceeds to the first database queries (e.g.
  `KRSB_SVC_DEF_T`), which is as far as startup can go without the schema.
