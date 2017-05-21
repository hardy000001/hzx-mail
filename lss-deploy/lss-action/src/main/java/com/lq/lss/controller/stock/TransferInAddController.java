/**
 *
 */
package com.lq.lss.controller.stock;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.AdminDept;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import com.lq.util.TimeUtil;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ch
 */
@Controller
@RequestMapping(value = "/user/stock/transferInAdd.htm")

public class TransferInAddController extends ShiroBaseController<CStockTransfer, String, StockTransferService> {

    @Resource
    private StockTransferService stockTransferService;

    @Resource
    private MchBaseinfoService mchBaseinfoService;

    @Resource
    private AdminAuditLogService adminAuditLogService;
    @Resource
	private AdminDeptService deptService;

    @Value("/stock/transfer_in_Add")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
    	SessionUser user = LoginSessionUtils.getUserInSession(request);
		AdminDept adminDept = deptService.get(user.getCenterId());
		
		if(adminDept==null){
			adminDept=new AdminDept();
		}
		modelMap.put("adminDept", adminDept);
        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }


    @RequestMapping(params = "method=save")
    @RequiresPermissions(PermResourceConst.CENTER_TRANS_IN_ADD)
    @Override
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        SessionUser sessionUser = LoginSessionUtils.getUserInSession(request);

        ResultDto<String> resultDto = new ResultDto<String>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String itemData = request.getParameter("itemData");
        String tsfSerialno = request.getParameter("tsfSerialno");
        String fromMchId = request.getParameter("fromMchcode");
    

        String tsfSdate = request.getParameter("tsfSdate");
        String zxFee = request.getParameter("zxFee");
        String transportFee =request.getParameter("transportFee");
        String otherFee = request.getParameter("otherFee");
        String createoper = sessionUser.getUserId()+"";//request.getParameter("createoper");
        
        String others = request.getParameter("others");
        String traOutOper = request.getParameter("traOutOper");
        String traInOper = request.getParameter("traInOper");

        
        System.out.println("tsfSdate="+tsfSdate);
        CStockTransfer cStockTransfer = new CStockTransfer();

        List<CStockTransferDetail> cStockTransferDetails = new ArrayList<CStockTransferDetail>();

        JSONArray json = JSONArray.fromObject(itemData);

        try {
            // 调拨流水主表

            MchBaseinfo mchinfoFrom = mchBaseinfoService.queryMchBaseinfoById(fromMchId);

         
            cStockTransfer.setFromMchcode(mchinfoFrom.getMchcode()+"");
            cStockTransfer.setToMchcode("100000");
        
            Date cdate=null;
    	    if(tsfSdate!=null || !tsfSdate.equals("")){
    	     cdate=TimeUtil.get().parseTime(tsfSdate);
    	    }
    	    System.out.println("cdate="+cdate);
            cStockTransfer.setTsfSdate(cdate);
            cStockTransfer.setFromDeptid(mchinfoFrom.getDeptid());
            cStockTransfer.setToDeptid(mchinfoFrom.getDeptid());
        
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
            cStockTransfer.setCreatetime(new Date());
            cStockTransfer.setCreateoper(createoper);
            
            cStockTransfer.setOthers(others);
            cStockTransfer.setTraOutOper(traOutOper);
            cStockTransfer.setTraInOper(traInOper);
            cStockTransfer.setTraType(32);

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
            adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TRANSFER_IN.getType(),"新增中心内部调入单申请",cStockTransfer,0L);
        

        } catch (Exception e) {
            logger.error(e.getMessage());
            resultDto.setSuccess(false);
            resultDto.setErrorMsg(e.getMessage());
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
        }


        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
