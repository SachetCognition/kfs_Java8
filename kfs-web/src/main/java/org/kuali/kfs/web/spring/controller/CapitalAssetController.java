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
 * Replaces Struts action mappings for Capital Asset Management (CAMS) and
 * Capital Asset Builder (CAB) modules.
 * Original wildcards:
 *   /cams* → org.kuali.kfs.module.cam.document.web.struts.{1}Action
 *   /cab*  → org.kuali.kfs.module.cab.document.web.struts.{1}Action
 * Plus explicit: /uploadBarcodeInventoryFile
 */
@Controller
public class CapitalAssetController extends KfsBaseController {

    // CAMS document actions
    @RequestMapping("/camsAssetPayment.do")
    public ModelAndView assetPayment(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/AssetPayment.jsp");
    }

    @RequestMapping("/camsAssetTransfer.do")
    public ModelAndView assetTransfer(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/AssetTransfer.jsp");
    }

    @RequestMapping("/camsEquipmentLoanOrReturn.do")
    public ModelAndView equipmentLoanOrReturn(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/EquipmentLoanOrReturn.jsp");
    }

    @RequestMapping("/camsBarcodeInventoryError.do")
    public ModelAndView barcodeInventoryError(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/BarcodeInventoryError.jsp");
    }

    @RequestMapping("/camsAssetDepreciationDocument.do")
    public ModelAndView assetDepreciation(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/AssetDepreciationDocument.jsp");
    }

    @RequestMapping("/uploadBarcodeInventoryFile.do")
    public ModelAndView uploadBarcodeInventoryFile(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cams/AssetBarCodeInventoryInputFile.jsp");
    }

    // CAB document actions
    @RequestMapping("/cabPurApLine.do")
    public ModelAndView purApLine(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cab/PurApLine.jsp");
    }

    @RequestMapping("/cabGlLine.do")
    public ModelAndView glLine(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cab/GlLine.jsp");
    }

    @RequestMapping("/cabCapitalAssetInformation.do")
    public ModelAndView capitalAssetInformation(HttpServletRequest request, HttpServletResponse response) {
        return forward("/jsp/module/cab/CapitalAssetInformation.jsp");
    }
}
