package com.lq.lss.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CStockTotalflow;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.dao.MaterialTypeDao;
import com.lq.lss.core.dao.StockTotalflowDao;
import com.lq.lss.core.service.StockTotalflowService;
import com.lq.lss.dto.StockTotalDataDto;
import com.lq.lss.dto.StockTotalflowDto;
import com.lq.lss.enums.TradeTypeNew;
import com.lq.pager.PageParam;
import com.lq.pager.Pager;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-23 15:27:44
 */
@Service
public class StockTotalflowServiceImpl extends EasyUIServiceBase<CStockTotalflow, String, StockTotalflowDao> implements StockTotalflowService{

    @Resource
	private StockTotalflowDao stockTotalflowDao;
    @Resource
	private MaterialTypeDao materialTypeDao;

	@Override
	public Pager<CStockTotalflow> queryTranseferPage(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {
		
		return modelDao.findTranseferPage(pageParam, stockTotalflowDto);
	}

	@Override
	public Pager<CStockTotalflow> queryCenterTotalPage(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {
		
		return modelDao.findCenterTotalPage(pageParam, stockTotalflowDto);
	}

	@Override
	public CStockTotalflow queryTotalData(StockTotalflowDto stockTotalflowDto) {
		
		return stockTotalflowDao.findTotalData(stockTotalflowDto);
	}

	@Override
	public Pager<Map<String, Object>> queryFlowTotalPager(PageParam pageParam,
			StockTotalflowDto stockTotalflowDto) {
		
		int type=stockTotalflowDto.getQueryType();
		List<StockTotalDataDto> totalData=null;
		
		if(String.valueOf(type).equals("4"))
		{          
			       //相互调拨只查询枝星的流水
			       stockTotalflowDto.setFromMchcode(SystemConst.ZX_MCHCODE);
			       totalData=modelDao.findTotalDataInfoIn(stockTotalflowDto);
		}else{
			       totalData=modelDao.findTotalDataInfo(stockTotalflowDto);
		}
		
		
		
		List<CStockTotalflow> pList=null;
		Pager<CStockTotalflow> pager=null;
		Integer totalCount=0;
		
		switch (type) 
		{
				case 1:
					    pager=modelDao.findPage(pageParam, stockTotalflowDto);
						pList=pager.getResultList();
						totalCount=pager.getTotalCount();
						break;
		        case 2:
		        	    pager=modelDao.findCenterTotalPage(pageParam, stockTotalflowDto);
		        	    pList=pager.getResultList();
		        	    totalCount=pager.getTotalCount();
					    break;
		        case 3:
		        	    pager=modelDao.findTranseferPage(pageParam, stockTotalflowDto);
		        	    pList=pager.getResultList();
		        	    totalCount=pager.getTotalCount();
					    break;
		        case 4:
		        	    pager=modelDao.findTranseferInPage(pageParam, stockTotalflowDto);
		        	    pList=pager.getResultList();
		        	    totalCount=pager.getTotalCount();
				        break;
				default:
						pager=modelDao.findPage(pageParam, stockTotalflowDto);
						pList=pager.getResultList();
						totalCount=pager.getTotalCount();
						break;
		}
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for (CStockTotalflow cStockTotalflow : pList) {
			
				String orderNo=cStockTotalflow.getOrderNo();
				Map<String, Object> map=new HashMap<String, Object>();
				
				map.put("tradeDate", cStockTotalflow.getTradetime());
				map.put("deptname", cStockTotalflow.getDeptname());
				map.put("orderType", cStockTotalflow.getOrderType());
				map.put("mchname", cStockTotalflow.getMchname());
				map.put("toMchname", cStockTotalflow.getToMchname());
				map.put("orderNo", cStockTotalflow.getOrderNo());
				map.put("htname", cStockTotalflow.getHtname());
			    map.put("orderName", TradeTypeNew.getTradeName(Integer
					.parseInt(cStockTotalflow.getOrderType())));
				
				for (StockTotalDataDto stockTotalDataDto : totalData)
				{
					   String totalNo=stockTotalDataDto.getOrderNo();
					   String typeid=stockTotalDataDto.getTypeid();
					   if(orderNo.equals(totalNo))
					   {
						   map.put(SystemConst.PROPERTY_PREFIX+typeid, stockTotalDataDto.getTotalM());
					   }
				}
				resultList.add(map);
		}
		//汇总
		if(resultList.size()>0)
		{
				List<StockTotalDataDto> totalList=null;
				if(String.valueOf(type).equals("4"))
				{
					totalList=modelDao.findTotalDataListIn(stockTotalflowDto);
				}else{
					totalList=modelDao.findTotalDataList(stockTotalflowDto);
				}
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("orderNo", "汇总");
				for (StockTotalDataDto stockTotalDataDto : totalList) {
					 String typeid=stockTotalDataDto.getTypeid();
					 map.put(SystemConst.PROPERTY_PREFIX+typeid, stockTotalDataDto.getTotalM());
				}
				resultList.add(map);
		}
		
		Pager<Map<String, Object>> newPager = new Pager<Map<String, Object>>(
				pageParam.getCurPage(), pageParam.getPageSize(),
				totalCount, resultList);
		

		return newPager;
	}
}