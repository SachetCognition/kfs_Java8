/*
 * Compatibility shim – VelocityEngineUtils was removed in Spring 5.0.
 * This stub provides the mergeTemplateIntoString method used by KFS
 * email services.
 */
package org.springframework.ui.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public abstract class VelocityEngineUtils {

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine,
            String templateLocation, Map<String, Object> model) {
        Object enc = velocityEngine.getProperty(RuntimeConstants.INPUT_ENCODING);
        String encoding = (enc != null) ? enc.toString() : "UTF-8";
        return mergeTemplateIntoString(velocityEngine, templateLocation, encoding, model);
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine,
            String templateLocation, String encoding, Map<String, Object> model) {
        try {
            StringWriter result = new StringWriter();
            VelocityContext velocityContext = new VelocityContext(model);
            velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, result);
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to merge Velocity template: " + templateLocation, e);
        }
    }
}
