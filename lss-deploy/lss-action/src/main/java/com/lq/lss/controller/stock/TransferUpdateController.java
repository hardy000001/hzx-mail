/**
 *
 */
package com.lq.lss.controller.stock;

import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockTransferDetailService;
import com.lq.lss.core.service.StockTransferService;
import com.lq.lss.model.CStockTransfer;
import com.lq.lss.model.CStockTransferDetail;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/stock/transferUpdate.htm")

public class TransferUpdateController extends ShiroBaseController<CStockTransfer, String, StockTransferService> {

    @Resource
    private StockTransferService stockTransferService;
    @Resource
    private StockTransferDetailService stockTransferDetailService;
    @Resource
    private MchBaseinfoService mchBaseinfoService;

    @Value("/stock/transferUpdate")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();


        String tsfSerialno = ServletRequestUtils.getStringParameter(request, "tsfSerialno", "");

        CStockTransfer cStockTransfer = stockTransferService.get(tsfSerialno);
        List<CStockTransferDetail>  detailList = stockTransferDetailService.queryDetailList(tsfSerialno);

        MchBaseinfo  mchbaseinfo = new MchBaseinfo();
        mchbaseinfo.setDeptid(cStockTransfer.getFromDeptid());
        mchbaseinfo.setMchcode(Long.parseLong(cStockTransfer.getFromMchcode()));

        mchbaseinfo = mchBaseinfoService.queryMchBaseinfo(mchbaseinfo);
        if(mchbaseinfo!=null){
            modelMap.put("formMchId",mchbaseinfo.getId());
        }
        mchbaseinfo =  new MchBaseinfo();
        mchbaseinfo.setDeptid(cStockTransfer.getToDeptid());
        mchbaseinfo.setMchcode(Long.parseLong(cStockTransfer.getToMchcode()));
        mchbaseinfo = mchBaseinfoService.queryMchBaseinfo(mchbaseinfo);
        if(mchbaseinfo!=null){
            modelMap.put("toMchId",mchbaseinfo.getId());
        }
        modelMap.put("transfer",cStockTransfer );
        modelMap.put("detailList", detailList);

        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }



    @RequestMapping(params = "method=save")
    @RequiresPermissions(PermResourceConst.CENTER_TRANS_UPDATE)
    @Override
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {


        ResultDto<String> resultDto = new ResultDto<String>();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String itemData = request.getParameter("itemData");
        String tsfSerialno = request.getParameter("tsfSerialno");
        String fromMchcode = request.getParameter("fromMchcode");
        String createtime = request.getParameter("createtime");
        String toMchcode = request.getParameter("toMchcode");
        String tsfSdate = request.getParameter("tsfSdate");
        String tsfEdate = request.getParameter("tsfEdate");
        String zxFee = request.getParameter("zxFee");
        String otherFee = request.getParameter("otherFee");
        String createoper = request.getParameter("createoper");
        
        String others = request.getParameter("others");
        String traOutOper = request.getParameter("traOutOper");
        String traInOper = request.getParameter("traInOper");



        CStockTransfer cStockTransfer = new CStockTransfer();

        List<CStockTransferDetail> cStockTransferDetails = new ArrayList<CStockTransferDetail>();

        JSONArray json = JSONArray.fromObject(itemData);

        try {
            // 调拨流水主表

            MchBaseinfo mchinfoFrom = mchBaseinfoService.queryMchBaseinfoById(fromMchcode);

            MchBaseinfo mchinfoTo = mchBaseinfoService.queryMchBaseinfoById(toMchcode);

            cStockTransfer.setToMchcode(mchinfoTo.getMchcode()+"");
            cStockTransfer.setFromMchcode(mchinfoFrom.getMchcode()+"");
            cStockTransfer.setTsfEdate(df.parse(tsfEdate));
            cStockTransfer.setTsfSdate(df2.parse(tsfSdate));
            cStockTransfer.setFromDeptid(mchinfoFrom.getDeptid());
            cStockTransfer.setToDeptid(mchinfoTo.getDeptid());
            cStockTransfer.setStatus("1");
            if (StringUtils.hasText(zxFee)) {
                cStockTransfer.setZxFee(new BigDecimal(zxFee));
            }
            if (StringUtils.hasText(otherFee)) {
                cStockTransfer.setOtherFee(new BigDecimal(otherFee));
            }
            cStockTransfer.setTsfSerialno(tsfSerialno);
            cStockTransfer.setCreatetime(df2.parse(createtime));
            cStockTransfer.setCreateoper(createoper);
            
            cStockTransfer.setOthers(others);
            cStockTransfer.setTraOutOper(traOutOper);
            cStockTransfer.setTraInOper(traInOper);

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

        } catch (Exception e) {
            logger.error(e.getMessage());
            resultDto.setSuccess(false);
            resultDto.setErrorMsg(e.getMessage());
            return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
        }



        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
    }


}
