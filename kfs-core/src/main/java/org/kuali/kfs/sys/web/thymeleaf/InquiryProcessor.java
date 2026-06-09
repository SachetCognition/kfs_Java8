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
 * Processes {@code <kfs:inquiry>} elements.
 */
public class InquiryProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "inquiry";
    private static final int PRECEDENCE = 1000;

    public InquiryProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        String boClassName = tag.getAttributeValue("boClassName");
        String keyValues = tag.getAttributeValue("keyValues");

        StringBuilder sb = new StringBuilder();
        sb.append("<span class=\"kfs-inquiry\"");
        if (boClassName != null) {
            sb.append(" data-bo-class=\"").append(boClassName).append("\"");
        }
        if (keyValues != null) {
            sb.append(" data-key-values=\"").append(keyValues).append("\"");
        }
        sb.append("></span>");

        structureHandler.replaceWith(sb.toString(), false);
    }
}
