package com.lq.lss.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.StockTotalDataDto;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.dto.StockTransTotalflowDto;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
@Repository
public class StockTotalflowDao extends LssSimpleBaseDao<CStockTotalflow, String>{
	
	
	
	@SuppressWarnings("unchecked")
	public List<CStockTotalflow> findTotalFlow(String  sDate,String eDate) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
		return (List<CStockTotalflow>) findListByParams("findTotalFlow", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CStockTotalflow> findTotalRentData(String  sDate,String eDate) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		return (List<CStockTotalflow>) findListByParams("findTotalRentData", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<CStockTotalflow> findTotalTransferFlow(String  sDate,String eDate) {
		//调拨客户信息汇总
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
		return (List<CStockTotalflow>) findListByParams("findTotalTransferFlow", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<CStockTotalflow> findTotalFlowData(String  sDate,String eDate) {
		//调拨客户信息汇总
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
		return (List<CStockTotalflow>) findListByParams("findTotalFlowData", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CStockTotalflow> findTotalMchTransferFlow(String  sDate,String eDate) {
		//入驻客户信息汇总
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
		return (List<CStockTotalflow>) findListByParams("findTotalMchTransferFlow", map);
	}
	
	
	
	
	
	
	
	  /**
	   * 维修费用计算
	   * @param StockTransTotalflowDto
	   * @return
	   */
	@SuppressWarnings("unchecked")
	public List<StockTransTotalflowDto> findTotalRepairFee(String  sDate,String eDate) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
		return (List<StockTransTotalflowDto>) findListByParams("findTotalRepairFee", map);
	}
	
	
  /**
   * 查找汇总数据
   * @param stockTotalflowDto
   * @return
   */
  public CStockTotalflow findTotalData (StockTotalflowDto stockTotalflowDto){
	  return  (CStockTotalflow) findObjectByParams("findTotalData", stockTotalflowDto);
  }
  /**
   * 查找汇总数据
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTotalDataDto> findTotalDataList (StockTotalflowDto stockTotalflowDto){
	  return  (List<StockTotalDataDto>) findListByParams("findTotalDataList", stockTotalflowDto);
  }
  /**
   * 查找汇总数据
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTotalDataDto> findTotalDataListIn (StockTotalflowDto stockTotalflowDto){
	  return  (List<StockTotalDataDto>) findListByParams("findTotalDataListIn", stockTotalflowDto);
  }
  

  /**
   * 获取调拨费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalTransferFee(String  sDate,String eDate){
	  
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalTransferFee", map);
  }
  
  
  /**
   * 获取入驻商户调拨费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalMchTransferFee(String  sDate,String eDate){
	  
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalMchTransferFee", map);
  }
  
  
  /**
   * 获取项目租赁费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalRentFee(String  sDate,String eDate){
	  
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalRentFee", map);
  }
  
  
  /**
   * 获取调拨其他费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalTsfOthrerFee(String  sDate,String eDate){
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalTsfOthrerFee", map);
  }
  
  
  /**
   * 获取租赁其他费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalRentOthrerFee(String  sDate,String eDate){
	  
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalRentOthrerFee", map);
  }
  
  /**
   * 获取入驻客户其他费用
   * @param stockTotalflowDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTransTotalflowDto> findTotalMchOthrerFee(String  sDate,String eDate){
	  
		Map<String, String> map = new HashMap<String, String>();
		map.put("sDate", sDate);
		map.put("eDate", eDate);
	  
	  return  (List<StockTransTotalflowDto>) findListByParams("findTotalMchOthrerFee", map);
  }
  
  /**
   * 获取汇总数据
   * @param StockTotalDataDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTotalDataDto> findTotalDataInfo(StockTotalflowDto  stockTotalflowDto){
	  return  (List<StockTotalDataDto>) findListByParams("findTotalDataInfo", stockTotalflowDto);
  }
  /**
   * 获取汇总数据
   * @param StockTotalDataDto
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<StockTotalDataDto> findTotalDataInfoIn(StockTotalflowDto  stockTotalflowDto){
	  return  (List<StockTotalDataDto>) findListByParams("findTotalDataInfoIn", stockTotalflowDto);
  }
 
 
  /**
   * 中心汇总流水
   * @param pageParam
   * @param stockTotalflowDto
   * @return
   */
  public Pager<CStockTotalflow> findCenterTotalPage(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {

		return findByPage("queryCenterTotalPage", "queryCenterCount",
				pageParam, stockTotalflowDto);
  }
  public Pager<CStockTotalflow> findTranseferPage(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {
		
		return findByPage("queryTranseferPage", "queryTranseferCount",
				pageParam, stockTotalflowDto);
  }
  public Pager<CStockTotalflow> findTranseferInPage(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {
		
		return findByPage("queryTranseferInPage", "queryTranseferInCount",
				pageParam, stockTotalflowDto);
  }
 
}