package com.lq.lss.controller.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import com.lq.util.JSONUtil;
import com.lq.util.ValidUtil;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.lq.lss.model.CStockInventory;
import com.lq.lss.model.SessionUser;
import com.lq.lss.core.service.StockInventoryDetailService;
import com.lq.lss.core.service.StockInventoryService;
import com.lq.lss.dto.StockInventoryDetailDto;

/**
 * Excel 导入数据
 * @author  作者: hzx
 * @date 创建时间: 2017-1-2下午8:28:02
 */
@Controller
@RequestMapping(value ="/user/excel/importInventory.htm")
public class ImportInventoryController extends ShiroBaseController<CStockInventory, String, StockInventoryService>{
	
	@Resource
	private StockInventoryService stockInventoryService;
	@Resource
	private StockInventoryDetailService stockInventoryDetailService;
	
	
	@Value("/stock/stock_inventory")
	private String indexView;
	
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		ResultDto<List<StockInventoryDetailDto>> resultDto=null;
		InputStream is=null;
		String deptId=String.valueOf(user.getCenterId());
		String mchcode = ServletRequestUtils.getStringParameter(request,
				"mchcode", "");
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile fileStream = (CommonsMultipartFile) multipartRequest.getFile("excelFile");
			
			    is=fileStream.getInputStream();
				resultDto=readExcelFile(is);
				if(resultDto.isSuccess())
				{
					List<StockInventoryDetailDto> detialList = stockInventoryDetailService
							.queryInventoryByMchcode(deptId, mchcode);
					
					List<StockInventoryDetailDto> detailDtos=resultDto.getParamObj();
					
					for (StockInventoryDetailDto stockInventoryDetailDto : detialList) 
					{
							String meterialcode=stockInventoryDetailDto.getMaterialcode();
							
							for (StockInventoryDetailDto detailDto : detailDtos) 
							{
									String code= detailDto.getMaterialcode();
									String realTotalS= detailDto.getRealTotalS();
									if(meterialcode.equals(code))
									{
											stockInventoryDetailDto.setRealTotalS(realTotalS);
											break;
									}
							}
						
					}
					resultDto.setParamObj(detialList);
				}
			
		} catch (Exception e) {
			logger.error("读取Excel文件异常");
			e.printStackTrace();
		}
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,
				JSONUtil.toJSonString(resultDto));
	}
	
	protected static ResultDto<List<StockInventoryDetailDto>> readExcelFile(InputStream is){
		
		ResultDto<List<StockInventoryDetailDto>> resultDto=new ResultDto<List<StockInventoryDetailDto>>();
		List<StockInventoryDetailDto> detailDtos=new ArrayList<StockInventoryDetailDto>();
		// 根据输入流创建Workbook对象
		Workbook wb;
		try {
				wb = WorkbookFactory.create(is);
				// get到Sheet对象 
				Sheet sheet = wb.getSheetAt(0);
				int bh=0;
				for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					bh=rowNum+1;
					StockInventoryDetailDto stockInventoryDetailDto=new StockInventoryDetailDto();
					
					Cell code = row.getCell(0);
					if (code == null) {
							resultDto.setErrorMsg("第"+bh+"行，物资编码不能为空");
							resultDto.setSuccess(false);
							return resultDto;
					}
					stockInventoryDetailDto.setMaterialcode(getValue(code));
					
//					Cell name = row.getCell(1);
//					if (name == null) {
//						   continue;
//					}
//					stockInventoryDetailDto.setName(getValue(name));
					
					Cell num = row.getCell(2);
					String _num=getValue(num);
					if(ValidUtil.get().isNumber(_num) || "".equals(_num))
					{
						stockInventoryDetailDto.setRealTotalS(_num);
					}else{
						resultDto.setErrorMsg("第"+bh+"行，实际库存只能为数字");
						resultDto.setSuccess(false);
						return resultDto;
					}
					
					detailDtos.add(stockInventoryDetailDto);
				}
				//全部验证通过就设置为true;
				resultDto.setSuccess(true);
				resultDto.setParamObj(detailDtos);
		} catch (Exception e) {
			    e.printStackTrace();
			    resultDto.setErrorMsg("读取Excel文件失败");
				resultDto.setSuccess(false);
		} finally{
			try 
			{
				if(is!=null)
				{
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultDto;
	}
	
	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	@SuppressWarnings("static-access")
	private static String getValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(cell.getBooleanCellValue());
		}else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
		    //先看是否是日期格式
	        if(DateUtil.isCellDateFormatted(cell)){
	        //读取日期格式
	    	return String.valueOf(cell.getDateCellValue());
	        }else{
	        //读取数字
	    	//返回数值类型的值 
	    	return String.valueOf(cell.getNumericCellValue());
	        }
		}else if(cell.getCellType() ==cell.CELL_TYPE_FORMULA ){
		    //读取公式
			return String.valueOf(cell.getCellFormula());
		} else {
			// 返回字符串类型的值
			return String.valueOf(cell.getStringCellValue());
		}
	}

	
	
	
}
