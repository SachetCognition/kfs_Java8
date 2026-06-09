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

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

/**
 * Custom Thymeleaf dialect for KFS/Rice KNS tag equivalents.
 *
 * Provides element and attribute processors that mirror the behavior of the
 * legacy JSP custom tags ({@code kul:*}, {@code sys:*}, {@code fp:*}, etc.)
 * in a Thymeleaf-native way.
 */
public class KfsDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "KFS Dialect";
    private static final String DIALECT_PREFIX = "kfs";
    private static final int DIALECT_PRECEDENCE = 1000;

    public KfsDialect() {
        super(DIALECT_NAME, DIALECT_PREFIX, DIALECT_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();
        processors.add(new DocumentPageProcessor(dialectPrefix));
        processors.add(new TabProcessor(dialectPrefix));
        processors.add(new HtmlControlAttributeProcessor(dialectPrefix));
        processors.add(new HtmlAttributeLabelProcessor(dialectPrefix));
        processors.add(new PanelFooterProcessor(dialectPrefix));
        processors.add(new ErrorsProcessor(dialectPrefix));
        processors.add(new PageProcessor(dialectPrefix));
        processors.add(new DocumentOverviewProcessor(dialectPrefix));
        processors.add(new NotesProcessor(dialectPrefix));
        processors.add(new AdHocRecipientsProcessor(dialectPrefix));
        processors.add(new RouteLogProcessor(dialectPrefix));
        processors.add(new SuperUserActionsProcessor(dialectPrefix));
        processors.add(new LookupProcessor(dialectPrefix));
        processors.add(new InquiryProcessor(dialectPrefix));
        return processors;
    }
}
