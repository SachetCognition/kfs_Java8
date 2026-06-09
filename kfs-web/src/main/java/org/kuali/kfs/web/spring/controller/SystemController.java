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
package org.kuali.kfs.web.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Replaces Struts action mappings for KFS system-level operations.
 * Original Actions:
 *   - KualiBalanceInquiryReportMenuAction (/balanceInquiryReportMenu.do)
 *   - KualiBatchJobModifyAction (/batchModify.do)
 *   - KualiBatchFileAdminAction (/batchFileAdmin.do)
 *   - ElectronicFundTransferAction (/electronicFundTransfer.do)
 *   - KualiBatchInputFileAction (/batchUpload.do)
 *   - KualiBatchInputFileSetAction (/batchUploadFileSet.do, /laborBatchUploadFileSet.do)
 */
@Controller
public class SystemController extends KfsBaseController {

    @RequestMapping("/balanceInquiryReportMenu.do")
    public ModelAndView balanceInquiryReportMenu(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/gl/KualiBalanceInquiryReportMenu.jsp");
    }

    @RequestMapping("/batchModify.do")
    public ModelAndView batchModify(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/KualiBatchJobModify.jsp");
    }

    @RequestMapping("/batchFileAdmin.do")
    public ModelAndView batchFileAdmin(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/KualiBatchFileAdmin.jsp");
    }

    @RequestMapping("/electronicFundTransfer.do")
    public ModelAndView electronicFundTransfer(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/ElectronicFundTransfer.jsp");
    }

    @RequestMapping("/batchUpload.do")
    public ModelAndView batchUpload(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/KualiBatchInputFile.jsp");
    }

    @RequestMapping("/batchUploadFileSet.do")
    public ModelAndView batchUploadFileSet(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/KualiBatchInputFileSet.jsp");
    }

    @RequestMapping("/laborBatchUploadFileSet.do")
    public ModelAndView laborBatchUploadFileSet(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/sys/KualiBatchInputFileSet.jsp");
    }
}
