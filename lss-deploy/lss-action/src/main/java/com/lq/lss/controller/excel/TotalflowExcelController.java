package com.lq.lss.controller.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.SystemConst;
import com.lq.lss.controller.util.TemplateParseUtil;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.StockInfoService;
import com.lq.lss.core.service.StockTotalflowService;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CStockTotalflow;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/user/excel/totalflowExcel.htm")
public class TotalflowExcelController extends ShiroBaseController<CStockTotalflow, String, StockTotalflowService>{

	@Resource
	private StockInfoService stockInfoService;
	@Resource
	MaterialTypeService materialTypeService;
	@Resource
	private StockTotalflowService stockTotalflowService;
	
	protected HttpServletRequest request ;
	protected HttpServletResponse response ;
	
	private static final int EXCEL_QUERY_NUM=500; //每次查询500条，
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	@RequestMapping(params="method=InTotalFlow")
	public ModelAndView InTotalFlow(StockTotalflowDto stockTotalflowDto,HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		//内部相互调拨
		String type1=String.valueOf(TradeType.TRANSFER_MUTUAL.getType());
		String type2=String.valueOf(TradeType.STOCK_TRANSFER_OUT.getType());
		String type3=String.valueOf(TradeType.STOCK_TRANSFER_IN.getType());
		String type4=String.valueOf(TradeType.STOCK_IN.getType());
		String type5=String.valueOf(TradeType.STOCK_OUT.getType());
		String orderType=stockTotalflowDto.getOrderType();
		if(StringUtils.hasLength(orderType)){
			 orderType="("+orderType+")";
		}else{
			 orderType="("+type1+","+type2+","+type3+","+type4+","+type5+")";
		}
		stockTotalflowDto.setOrderType(orderType);
		stockTotalflowDto.setQueryType(4);
		int pageNumber = 1;
		PageParam pageParam = new PageParam(pageNumber, EXCEL_QUERY_NUM);
		Pager<Map<String, Object>> page = stockTotalflowService
				.queryFlowTotalPager(pageParam, stockTotalflowDto);
		List<Map<String, Object>> dataList=new ArrayList<Map<String,Object>>();
		for (int i =0; i < page.getPageCount(); i++) {
			 pageParam.setCurPage(pageNumber++);
			 page = stockTotalflowService
					.queryFlowTotalPager(pageParam, stockTotalflowDto);
			 dataList.addAll(page.getResultList());
			 dataList.remove(dataList.size()-1);
		}
		
		try {
			processExcel(dataList, "in_totalflow.ftl", "内部");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@RequestMapping(params="method=outTotalFlow")
	public ModelAndView outTotalFlow(StockTotalflowDto stockTotalflowDto,HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		//调拨
		String type1=String.valueOf(TradeType.STOCKCENTERTRANSFER_IN.getType());
		String type2=String.valueOf(TradeType.STOCKCENTERTRANSFER_OUT.getType());
		String type3=String.valueOf(TradeType.STOCK_TEMPORARY_IN.getType());
		String type4=String.valueOf(TradeType.STOCK_TEMPORARY_OUT.getType());
		String orderType=stockTotalflowDto.getOrderType();
		if(StringUtils.hasLength(orderType)){
			 orderType="("+orderType+")";
		}else{
			 orderType="("+type1+","+type2+","+type3+","+type4+")";
		}
		stockTotalflowDto.setOrderType(orderType);
		stockTotalflowDto.setQueryType(3);
		int pageNumber = 1;
		PageParam pageParam = new PageParam(pageNumber, EXCEL_QUERY_NUM);
		Pager<Map<String, Object>> page = stockTotalflowService
				.queryFlowTotalPager(pageParam, stockTotalflowDto);
		List<Map<String, Object>> dataList=new ArrayList<Map<String,Object>>();
		for (int i =0; i < page.getPageCount(); i++) {
			 pageParam.setCurPage(pageNumber++);
			 page = stockTotalflowService
						.queryFlowTotalPager(pageParam, stockTotalflowDto);
			 dataList.addAll(page.getResultList());
			 dataList.remove(dataList.size()-1);
		}
		try {
			processExcel(dataList, "out_totalflow.ftl", "外部");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@RequestMapping(params="method=leaseTotalFlow")
	public ModelAndView leaseTotalFlow(CStockTotalflow cStockTotalflow,HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		String type1=String.valueOf(TradeType.STOCK_SEND.getType());
		String type2=String.valueOf(TradeType.STOCK_RECEIPT.getType());
		
		String orderType=cStockTotalflow.getOrderType();
		if(StringUtils.hasLength(orderType)){
			orderType="("+orderType+")";
		}else{
			orderType="("+type1+","+type2+")";
		}
		cStockTotalflow.setOrderType(orderType);
		int pageNumber = 1;
		PageParam pageParam = new PageParam(pageNumber, EXCEL_QUERY_NUM);
		StockTotalflowDto stockTotalflowDto=new StockTotalflowDto();
		stockTotalflowDto.setDeptid(cStockTotalflow.getDeptid());
		stockTotalflowDto.setFromMchcode(cStockTotalflow.getFromMchcode());
		stockTotalflowDto.setToMchcode(cStockTotalflow.getToMchcode());
		stockTotalflowDto.setOrderType(orderType);
		stockTotalflowDto.setOrderNo(cStockTotalflow.getOrderNo());
		stockTotalflowDto.setHtcode(cStockTotalflow.getHtcode());
		stockTotalflowDto.setDealDate(cStockTotalflow.getDealDate());
		stockTotalflowDto.setCreateDate(cStockTotalflow.getCreateDate());
		Pager<Map<String, Object>> page = stockTotalflowService
				.queryFlowTotalPager(pageParam, stockTotalflowDto);
		List<Map<String, Object>> dataList=new ArrayList<Map<String,Object>>();
		for (int i =0; i < page.getPageCount(); i++) {
			 pageParam.setCurPage(pageNumber++);
			 page = stockTotalflowService
					.queryFlowTotalPager(pageParam, stockTotalflowDto);
			 dataList.addAll(page.getResultList());
			 dataList.remove(dataList.size()-1);
		}
		try {
			processExcel(dataList, "lease_totalflow.ftl", "租赁");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 导出excle统一处理
	 * @param resultList 需要导出exlce的数据
	 * @param templateName excle模版的名字
	 * @param excleName  excel的文件名
	 * @throws Exception
	 */
	protected void processExcel(List<?> resultList,String templateName,String excleName) throws Exception{
		Map<String,Object> data = new HashMap<String, Object>();  
		String officialDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		List<BMaterialType> typeList=materialTypeService.loadAll();
		data.put("typeList", typeList);  
	    data.put("resultList", resultList);  
    	
	    try {  
	    	ByteArrayOutputStream baos = null;
	    	String ftlPath = request.getSession().getServletContext().getRealPath("/")+"ftl/excel/";
	    	baos = TemplateParseUtil.parse(templateName, data,ftlPath);  
	        String fileName = excleName+officialDate+SystemConst.XLS;//导出excel的文件名
	        response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			out.write(baos.toByteArray());
			out.flush();
			out.close();
			
	    } catch (IOException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } catch (TemplateException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}

}
