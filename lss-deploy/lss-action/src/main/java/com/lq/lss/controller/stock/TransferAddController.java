/**
 *
 */
package com.lq.lss.controller.stock;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/stock/transferAdd.htm")

public class TransferAddController extends ShiroBaseController<CStockTransfer, String, StockTransferService> {

    @Resource
    private StockTransferService stockTransferService;

    @Resource
    private MchBaseinfoService mchBaseinfoService;

    @Resource
    private AdminAuditLogService adminAuditLogService;

    @Value("/stock/transferAdd")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }


    @RequestMapping(params = "method=save")
    @RequiresPermissions(PermResourceConst.CENTER_TRANS_ADD)
    @Override
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);

        ResultDto<String> resultDto = new ResultDto<String>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String itemData = request.getParameter("itemData");
        String tsfSerialno = request.getParameter("tsfSerialno");
        String fromMchId = request.getParameter("fromMchcode");
        String createtime = request.getParameter("createtime");
        String toMchId = request.getParameter("toMchcode");
        String tsfSdate = request.getParameter("tsfSdate");
        String tsfEdate = request.getParameter("tsfEdate");
        String zxFee = request.getParameter("zxFee");
        String transportFee =request.getParameter("transportFee");
        String otherFee = request.getParameter("otherFee");
        String createoper = sessionUser.getUserId()+"";//request.getParameter("createoper");
        
        String others = request.getParameter("others");
        String traOutOper = request.getParameter("traOutOper");
        String traInOper = request.getParameter("traInOper");

        

        CStockTransfer cStockTransfer = new CStockTransfer();

        List<CStockTransferDetail> cStockTransferDetails = new ArrayList<CStockTransferDetail>();

        JSONArray json = JSONArray.fromObject(itemData);

        try {
            // 调拨流水主表

            MchBaseinfo mchinfoFrom = mchBaseinfoService.queryMchBaseinfoById(fromMchId);

            MchBaseinfo mchinfoTo = mchBaseinfoService.queryMchBaseinfoById(toMchId);

            cStockTransfer.setToMchcode(mchinfoTo.getMchcode()+"");
            cStockTransfer.setFromMchcode(mchinfoFrom.getMchcode()+"");

            cStockTransfer.setTsfEdate(df.parse(tsfEdate));
            cStockTransfer.setTsfSdate(df.parse(tsfSdate));
            cStockTransfer.setFromDeptid(mchinfoFrom.getDeptid());
            cStockTransfer.setToDeptid(mchinfoTo.getDeptid());
            cStockTransfer.setStatus("1");
            if (StringUtils.hasText(zxFee)) {
                cStockTransfer.setZxFee(new BigDecimal(zxFee));
            }
            if (StringUtils.hasText(otherFee)) {
                cStockTransfer.setOtherFee(new BigDecimal(otherFee));
            }
            if (StringUtils.hasText(transportFee)) {
                cStockTransfer.setTransportFee(new BigDecimal(transportFee));
            }
            cStockTransfer.setTsfSerialno(tsfSerialno);
            cStockTransfer.setCreatetime(df2.parse(createtime));
            cStockTransfer.setCreateoper(createoper);
            
            cStockTransfer.setOthers(others);
            cStockTransfer.setTraOutOper(traOutOper);
            cStockTransfer.setTraInOper(traInOper);
            cStockTransfer.setTraType(10);
            // 调拨流水明细表
            List<CStockTransferDetail> dataList = (List<CStockTransferDetail>) JSONArray.toCollection(json, CStockTransferDetail.class);
            for (CStockTransferDetail detail : dataList) {
                CStockTransferDetail cStockTransferDetail = new CStockTransferDetail();
                cStockTransferDetail.setTsfSerialno(tsfSerialno);
                cStockTransferDetail.setMaterialCode(detail.getMaterialCode());
                cStockTransferDetail.setTotalS(detail.getTotalS());
                cStockTransferDetail.setTotalT(detail.getTotalT());
                cStockTransferDetail.setTotalM(detail.getTotalM());
                cStockTransferDetail.setStatus("0");
                cStockTransferDetails.add(cStockTransferDetail);
            }



            resultDto = stockTransferService.saveStockTransferRdTx(cStockTransfer, cStockTransferDetails);

            //操作日志
            adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TRS.getType(),"调拨单",cStockTransfer,0L);
            //操作日志
            adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TRS.getType(),"调拨单",cStockTransferDetails,0L);

        } catch (Exception e) {
            logger.error(e.getMessage());
            resultDto.setSuccess(false);
            resultDto.setErrorMsg(e.getMessage());
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
        }


        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
