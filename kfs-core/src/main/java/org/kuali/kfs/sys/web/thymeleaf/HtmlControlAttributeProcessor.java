/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.web.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Processes {@code <kfs:htmlControlAttribute>} elements, replacing the legacy
 * {@code <kul:htmlControlAttribute>} JSP tag.
 */
public class HtmlControlAttributeProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "htmlControlAttribute";
    private static final int PRECEDENCE = 1000;

    public HtmlControlAttributeProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        String property = tag.getAttributeValue("property");
        String attributeEntry = tag.getAttributeValue("attributeEntry");
        String readOnly = tag.getAttributeValue("readOnly");

        StringBuilder sb = new StringBuilder();
        sb.append("<input type=\"text\"");
        if (property != null) {
            sb.append(" name=\"").append(property).append("\"");
            sb.append(" th:field=\"*{").append(property).append("}\"");
        }
        if (attributeEntry != null) {
            sb.append(" data-attribute-entry=\"").append(attributeEntry).append("\"");
        }
        if ("true".equals(readOnly)) {
            sb.append(" readonly=\"readonly\"");
        }
        sb.append(" />");

        structureHandler.replaceWith(sb.toString(), false);
    }
}
