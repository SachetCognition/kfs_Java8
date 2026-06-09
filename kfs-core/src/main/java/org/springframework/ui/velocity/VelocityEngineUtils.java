package org.springframework.ui.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Compatibility shim for Spring 6.x migration (S-4A).
 * Spring's VelocityEngineUtils was removed in Spring 5.x; this stub provides
 * the commonly used mergeTemplateIntoString method using the Velocity API directly.
 *
 * @deprecated Provided only for backward compatibility.
 *             New code should use VelocityEngine directly.
 */
@Deprecated
public abstract class VelocityEngineUtils {

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine,
            String templateLocation, Map<String, Object> model) {
        return mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", model);
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine,
            String templateLocation, String encoding, Map<String, Object> model) {
        try {
            StringWriter writer = new StringWriter();
            VelocityContext context = new VelocityContext(model);
            velocityEngine.mergeTemplate(templateLocation, encoding, context, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to merge Velocity template: " + templateLocation, e);
        }
    }
}
