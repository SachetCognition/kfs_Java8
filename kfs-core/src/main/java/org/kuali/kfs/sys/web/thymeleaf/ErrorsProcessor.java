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
 * Processes {@code <kfs:errors>} elements.
 */
public class ErrorsProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "errors";
    private static final int PRECEDENCE = 1000;

    public ErrorsProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        String keyMatch = tag.getAttributeValue("keyMatch");
        String errorTitle = tag.getAttributeValue("errorTitle");

        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"kfs-errors\"");
        if (keyMatch != null) {
            sb.append(" data-key-match=\"").append(keyMatch).append("\"");
        }
        sb.append(">");
        if (errorTitle != null) {
            sb.append("<h4>").append(errorTitle).append("</h4>");
        }
        sb.append("</div>");

        structureHandler.replaceWith(sb.toString(), false);
    }
}
