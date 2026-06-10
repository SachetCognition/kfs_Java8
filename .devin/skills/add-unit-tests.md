# Skill: Add JUnit 5 Unit Tests to a KFS Module

Add unit test coverage to a KFS module following the patterns established during the Phase 1 migration.

## Prerequisites

- Branch based on `post-migration-final` (JUnit 5 + Mockito 5 + AssertJ are available)
- The module must already be buildable (`mvn compile -pl <module>` succeeds)

## Steps

### 1. Identify the Target Module

Determine which module to add tests for. Valid modules:

```
kfs-core   kfs-ar    kfs-purap  kfs-tem  kfs-cam
kfs-bc     kfs-cg    kfs-kc     kfs-ld   kfs-ec
```

### 2. Survey Existing Tests

```bash
find <module>/src/test -name "*Test.java" -o -name "*UnitTest.java" | wc -l
```

Check existing coverage to avoid duplicating tests.

### 3. Identify Testable Classes

Focus on classes with pure business logic (no Rice/Spring context dependencies):

- `businessobject/` — getters, setters, calculated fields, `equals`/`hashCode`
- `document/validation/` — business rules (pure Java, no framework deps)
- `service/impl/` — service implementations (mock dependencies with Mockito)
- `batch/` — batch step logic (mock services)
- `report/` — report data holders

**Avoid** classes that require the full Rice context (`SpringContext.getBean(...)`, KIM services, KEW workflow).

### 4. Create Test Classes

Place tests in the mirror `src/test/java/` directory matching the source package.

**Naming conventions:**
- `<ClassName>Test.java` for unit tests
- `<ClassName>UnitTest.java` if a legacy `<ClassName>Test.java` integration test already exists

**Test structure:**

```java
package org.kuali.kfs.module.<module>.<subpackage>;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SomeServiceImplTest {

    @Mock
    private SomeDependency dependency;

    private SomeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SomeServiceImpl();
        service.setDependency(dependency);
    }

    @Test
    @DisplayName("should compute value correctly when input is valid")
    void shouldComputeValueCorrectly() {
        when(dependency.getValue()).thenReturn("expected");
        assertThat(service.compute()).isEqualTo("expected");
    }
}
```

### 5. Business Object Tests

For business objects, test:

- Default constructor initializes fields correctly
- Getters/setters work as expected
- Calculated/derived fields return correct values
- `toString()` does not throw (if overridden)
- Collection fields are initialized (not null)

### 6. Validation Rule Tests

For `document/validation/` classes, test:

- Each validation rule returns `true` for valid input
- Each validation rule returns `false` for invalid input
- Error messages are added to the correct property
- Edge cases (null, empty, boundary values)

### 7. Build and Verify

```bash
# Compile tests
mvn test-compile -pl <module> -Denforcer.phase=none -Drice.version=2.1.10

# Run the new tests
mvn test -pl <module> -Denforcer.phase=none -Drice.version=2.1.10

# Check coverage (if JaCoCo is configured)
mvn test jacoco:report -pl <module> -Denforcer.phase=none -Drice.version=2.1.10
```

### 8. Coverage Target

Target >= 85% branch coverage per module. Check JaCoCo reports at:
```
<module>/target/site/jacoco/index.html
```

### 9. Commit

```bash
git add <module>/src/test/
git commit -m "Add JUnit 5 unit tests for <module> module"
```
