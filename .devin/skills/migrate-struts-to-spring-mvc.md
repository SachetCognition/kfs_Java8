# Skill: Migrate Struts Action/Form to Spring MVC + Thymeleaf

Convert a module's Struts 1 web layer to Spring MVC controllers with Thymeleaf templates. Follow Phase 4 migration patterns.

## Prerequisites

- Branch based on `post-migration-final`
- Spring Boot 3.3.5 and Thymeleaf dependencies already in `pom.xml`
- `KfsApplication.java` Spring Boot entry point exists in `kfs-web`

## Steps

### 1. Identify Struts Actions to Migrate

```bash
find <module>/src/main/java -path "*/web/struts/*Action.java" | head -20
```

Each Struts `Action` class becomes a Spring MVC `@Controller`. Each `ActionForm` is replaced by a model object or `@ModelAttribute`.

### 2. Locate Corresponding JSPs

```bash
find kfs-web/src/main/webapp -name "*.jsp" | grep -i <module-name>
```

Each JSP becomes a Thymeleaf `.html` template.

### 3. Create the Spring MVC Controller

Place controllers in `kfs-web/src/main/java/org/kuali/kfs/web/spring/controller/`:

```java
package org.kuali.kfs.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/<module>")
public class ModuleController extends KfsBaseController {

    @GetMapping("/lookup")
    public String lookup(@RequestParam Map<String, String> params, Model model) {
        // Delegate to existing service layer — DO NOT rewrite business logic
        model.addAttribute("results", service.search(params));
        return "<module>/lookup";  // Thymeleaf template path
    }

    @PostMapping("/document")
    public String submitDocument(@ModelAttribute DocumentForm form, Model model) {
        // Validate and submit via existing document service
        return "redirect:/<module>/document?docId=" + form.getDocumentNumber();
    }
}
```

### 4. Key Mapping Rules (Struts -> Spring MVC)

| Struts 1 | Spring MVC |
|----------|-----------|
| `Action.execute(mapping, form, request, response)` | `@GetMapping`/`@PostMapping` method |
| `ActionForm` | `@ModelAttribute` POJO or `@RequestParam` |
| `ActionForward` | Return `String` (template name) or `"redirect:..."` |
| `ActionMapping.findForward("success")` | `return "templateName"` |
| `request.setAttribute("key", value)` | `model.addAttribute("key", value)` |
| `struts-config.xml` action mappings | `@RequestMapping` annotations |

### 5. Create Thymeleaf Templates

Place templates in `kfs-web/src/main/resources/templates/<module>/`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${pageTitle}">Module Page</title>
</head>
<body>
    <div th:replace="~{fragments/header :: header}"></div>
    <table>
        <tr th:each="item : ${results}">
            <td th:text="${item.code}">CODE</td>
            <td th:text="${item.name}">Name</td>
        </tr>
    </table>
</body>
</html>
```

### 6. JSP to Thymeleaf Conversion Rules

| JSP | Thymeleaf |
|-----|-----------|
| `<c:out value="${var}"/>` | `th:text="${var}"` |
| `<c:forEach items="${list}" var="item">` | `th:each="item : ${list}"` |
| `<c:if test="${condition}">` | `th:if="${condition}"` |
| `<form:input path="field"/>` | `<input th:field="*{field}"/>` |
| `<html:link action="/path">` | `<a th:href="@{/path}">` |
| `<bean:message key="label.key"/>` | `th:text="#{label.key}"` |

### 7. Preserve Business Logic

- Controllers must **delegate to existing service layer** — do not rewrite business logic
- The service layer, validation rules, and document workflow are unchanged
- Only the web presentation layer (Action -> Controller, JSP -> Thymeleaf) changes

### 8. Build and Verify

```bash
mvn compile -pl kfs-web -Denforcer.phase=none -Drice.version=2.1.10
mvn test -pl kfs-web -Denforcer.phase=none -Drice.version=2.1.10
```

### 9. Commit

```bash
git add kfs-web/src/main/java/org/kuali/kfs/web/spring/controller/
git add kfs-web/src/main/resources/templates/
git commit -m "S-4: Migrate <module> Struts actions to Spring MVC + Thymeleaf"
```
