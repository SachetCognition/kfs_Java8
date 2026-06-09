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
 * Processes {@code <kfs:htmlAttributeLabel>} elements, replacing the legacy
 * {@code <kul:htmlAttributeLabel>} JSP tag.
 */
public class HtmlAttributeLabelProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "htmlAttributeLabel";
    private static final int PRECEDENCE = 1000;

    public HtmlAttributeLabelProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        String attributeEntryName = tag.getAttributeValue("attributeEntryName");
        String attributeEntry = tag.getAttributeValue("attributeEntry");

        StringBuilder sb = new StringBuilder();
        sb.append("<label class=\"kfs-label\"");
        if (attributeEntryName != null) {
            sb.append(" data-attribute-entry-name=\"").append(attributeEntryName).append("\"");
        }
        if (attributeEntry != null) {
            sb.append(" data-attribute-entry=\"").append(attributeEntry).append("\"");
        }
        sb.append("></label>");

        structureHandler.replaceWith(sb.toString(), false);
    }
}
