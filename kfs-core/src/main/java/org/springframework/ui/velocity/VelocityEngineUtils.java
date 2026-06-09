package org.springframework.ui.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Compatibility shim for the removed Spring VelocityEngineUtils class.
 * Provides template merging functionality using the Velocity engine directly.
 */
public final class VelocityEngineUtils {

    private VelocityEngineUtils() {
    }

    @SuppressWarnings("unchecked")
    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, Map model) {
        return mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", model);
    }

    @SuppressWarnings("unchecked")
    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation,
            String encoding, Map model) {
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
