# Skill: Add JPA Annotations to a KFS Module

Annotate business objects with JPA annotations for the OJB-to-JPA migration. Follow the Phase 2 migration patterns.

## Prerequisites

- Branch based on `post-migration-final`
- JPA infrastructure already in place: `persistence.xml`, `JpaConfig.java`, Hibernate 6.4 dependency
- The module should already have OJB mapping files (`ojb-*.xml`) to use as a reference

## Steps

### 1. Locate OJB Mapping Files

```bash
find <module>/src/main/resources -name "ojb-*.xml" | head -20
```

These XML files define the current ORM mappings. Each `<class-descriptor>` maps to a business object that needs JPA annotations.

### 2. Identify Business Objects

```bash
find <module>/src/main/java -path "*/businessobject/*.java" -not -name "*Test*" | wc -l
```

### 3. Annotate Each Business Object

For each class in `businessobject/`, add JPA annotations:

```java
import jakarta.persistence.*;

@Entity
@Table(name = "TABLE_NAME")  // from ojb-*.xml class-descriptor table attribute
public class SomeBusinessObject {

    @Id
    @Column(name = "COLUMN_NAME")  // from ojb field-descriptor with primarykey="true"
    private String id;

    @Column(name = "OTHER_COLUMN")
    private String otherField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COLUMN", insertable = false, updatable = false)
    private RelatedObject relatedObject;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildObject> children;
}
```

### 4. Mapping Rules (OJB XML -> JPA)

| OJB XML | JPA Annotation |
|---------|---------------|
| `<class-descriptor class="..." table="...">` | `@Entity @Table(name = "...")` |
| `<field-descriptor name="..." column="..." primarykey="true">` | `@Id @Column(name = "...")` |
| `<field-descriptor name="..." column="...">` | `@Column(name = "...")` |
| `<reference-descriptor ... class-ref="..." auto-retrieve="true">` | `@ManyToOne(fetch = LAZY) @JoinColumn(...)` |
| `<collection-descriptor ... element-class-ref="...">` | `@OneToMany(mappedBy = "...")` |
| `<field-descriptor ... conversion="OjbCharBooleanConversion">` | Use `@Convert(converter = BooleanYNConverter.class)` |

### 5. Special Converters

KFS uses `Y`/`N` character fields for booleans. Use the existing converter:

```java
@Column(name = "ACTV_IND")
@Convert(converter = BooleanYNConverter.class)
private boolean active;
```

The `BooleanYNConverter` class is in `org.kuali.kfs.sys.businessobject.converter`.

### 6. Composite Keys

For tables with composite primary keys, use `@IdClass` or `@EmbeddedId`:

```java
@Entity
@Table(name = "TABLE_NAME")
@IdClass(SomeBusinessObjectId.class)
public class SomeBusinessObject {
    @Id @Column(name = "COL1") private String field1;
    @Id @Column(name = "COL2") private String field2;
}
```

### 7. Register in persistence.xml

Add each annotated entity to `kfs-core/src/main/resources/META-INF/persistence.xml`:

```xml
<class>org.kuali.kfs.module.<module>.businessobject.SomeBusinessObject</class>
```

### 8. Preserve Existing Behavior

- Do NOT change any method signatures
- Do NOT remove OJB-specific methods (they may still be called)
- The `@Entity` annotation is additive — OJB and JPA can coexist during migration
- Keep `transient` fields as `@Transient` in JPA

### 9. Build and Verify

```bash
mvn compile -pl <module> -Denforcer.phase=none -Drice.version=2.1.10
mvn test -pl <module> -Denforcer.phase=none -Drice.version=2.1.10
```

### 10. Commit

```bash
git add <module>/src/main/java/ kfs-core/src/main/resources/META-INF/persistence.xml
git commit -m "S-2-<MODULE>: Add JPA annotations to all <module> business objects"
```
