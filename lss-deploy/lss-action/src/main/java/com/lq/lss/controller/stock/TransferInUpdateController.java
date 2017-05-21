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
import com.lq.lss.core.service.StockTransferDetailService;
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
import org.springframework.web.bind.ServletRequestUtils;
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
@RequestMapping(value = "/user/stock/transferInUpdate.htm")

public class TransferInUpdateController extends ShiroBaseController<CStockTransfer, String, StockTransferService> {

    @Resource
    private StockTransferService stockTransferService;
    @Resource
    private StockTransferDetailService stockTransferDetailService;
    @Resource
    private MchBaseinfoService mchBaseinfoService;
    @Resource
	private AdminDeptService deptService;
    @Resource
    private AdminAuditLogService adminAuditLogService;

    @Value("/stock/transfer_in_update")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        SessionUser user = LoginSessionUtils.getUserInSession(request);
        String tsfSerialno = ServletRequestUtils.getStringParameter(request, "tsfSerialno", "");

        CStockTransfer cStockTransfer = stockTransferService.get(tsfSerialno);
        List<CStockTransferDetail>  detailList = stockTransferDetailService.queryDetailList(tsfSerialno);
        AdminDept adminDept = deptService.get(user.getCenterId());
        
        MchBaseinfo  mchbaseinfo = new MchBaseinfo();
        mchbaseinfo.setDeptid(cStockTransfer.getFromDeptid());
        mchbaseinfo.setMchcode(Long.parseLong(cStockTransfer.getFromMchcode()));
        mchbaseinfo = mchBaseinfoService.queryMchBaseinfo(mchbaseinfo);
        if(mchbaseinfo!=null){
            modelMap.put("formMchId",mchbaseinfo.getId());
        }
        
       
        modelMap.put("transfer",cStockTransfer );
        modelMap.put("detailList", detailList);
        modelMap.put("adminDept", adminDept);

        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }



    @RequestMapping(params = "method=save")
    @RequiresPermissions(PermResourceConst.CENTER_TRANS_IN_UPDATE)
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
        String tsfEdate = request.getParameter("tsfEdate");
        String zxFee = request.getParameter("zxFee");
        String otherFee = request.getParameter("otherFee");

        
        String others = request.getParameter("others");
        String traOutOper = request.getParameter("traOutOper");
        String traInOper = request.getParameter("traInOper");


        System.out.println("fromMchcode"+fromMchId);
        CStockTransfer cStockTransfer = new CStockTransfer();

        List<CStockTransferDetail> cStockTransferDetails = new ArrayList<CStockTransferDetail>();

        JSONArray json = JSONArray.fromObject(itemData);

        try {
            // 调拨流水主表
       
            MchBaseinfo mchinfoFrom = mchBaseinfoService.queryMchBaseinfoById(fromMchId);


           
            cStockTransfer.setFromMchcode(mchinfoFrom.getMchcode()+"");
            Date cdate=null;
            cStockTransfer.setToMchcode(mchinfoFrom.getMchcode()+"");
            if(tsfSdate!=null || !tsfSdate.equals("")){
       	     cdate=TimeUtil.get().parseTime(tsfSdate);
       	    }
           
            cStockTransfer.setTsfSdate(cdate);
            cStockTransfer.setFromDeptid(mchinfoFrom.getDeptid());
          
            cStockTransfer.setStatus("1");
            if (StringUtils.hasText(zxFee)) {
                cStockTransfer.setZxFee(new BigDecimal(zxFee));
            }
            if (StringUtils.hasText(otherFee)) {
                cStockTransfer.setOtherFee(new BigDecimal(otherFee));
            }
            cStockTransfer.setTsfSerialno(tsfSerialno);
 
            
            cStockTransfer.setOthers(others);
            cStockTransfer.setTraOutOper(traOutOper);
            cStockTransfer.setTraInOper(traInOper);
            cStockTransfer.setStatus("1");
            cStockTransfer.setCreateoper(sessionUser.getUserId()+"");
            cStockTransfer.setAuditingoper(null);
            cStockTransfer.setAuditingtime(null);
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

            resultDto = stockTransferService.updateStockTransferRdTx(cStockTransfer, cStockTransferDetails);
            
            
            //操作日志
            adminAuditLogService.log(sessionUser.getUserId(), TradeType.STOCK_TRANSFER_IN.getType(),"修改中心内部调入单申请",cStockTransfer,0L);
        
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
            resultDto.setSuccess(false);
            resultDto.setErrorMsg(e.getMessage());
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
        }



        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
