package com.lq.lss.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.BRepairInfoDao;
import com.lq.lss.core.dao.CStockInRepairinfoDao;
import com.lq.lss.core.dao.StockInDetailDao;
import com.lq.lss.core.service.StockInDetailService;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockInDetail;
import com.lq.lss.model.CStockInRepairinfo;
import com.lq.lss.model.CStockReceiptRepairinfo;
import com.lq.lss.utils.SystemConst;

/**
 * 
 * @author Eric
 *
 */
@Service
public class StockInDetailServiceImpl extends EasyUIServiceBase<CStockInDetail, String, StockInDetailDao> implements StockInDetailService {

	@Resource
    private BRepairInfoDao bRepairInfoDao;
	@Resource
    private CStockInRepairinfoDao stockInRepairinfoDao;

	@Override
	public List<CStockInDetail> queryStockInDetailListById(String inSerialno) {
		// TODO Auto-generated method stub
		
		return modelDao.queryStockInDetailListById(inSerialno);
	}

	@Override
	public List<Map<String, Object>> getInDetailListById(String inSerialno) {

		List<Map<String, Object>> lists=new ArrayList<Map<String,Object>>();
		
		List<CStockInDetail> stockInDetails = modelDao.queryStockInDetailListById(inSerialno);
        List<BRepairInfo> bRepairInfos=bRepairInfoDao.loadAll();
		List<CStockInRepairinfo> sCStockInRepairinfos = stockInRepairinfoDao
				.queryRepairiBySerialno(inSerialno);

		int itemid=1;
		for (CStockInDetail stockInDetail : stockInDetails) {
			
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("itemid", itemid++);
				map.put("materialCode", stockInDetail.getMaterialCode());
				map.put("name", stockInDetail.getName());
				map.put("accountFlag", stockInDetail.getAccountFlag());
				map.put("covertRatio", stockInDetail.getCovertRatio());
				map.put("convertFlag", stockInDetail.getConvertFlag());
				map.put("totalS", stockInDetail.getTotalS());
				map.put("totalM", stockInDetail.getTotalM());
				map.put("totalT", stockInDetail.getTotalT());
				map.put("typeId", stockInDetail.getTypeId());
				map.put("fname", stockInDetail.getFname());
				map.put("unit", stockInDetail.getUnit());
				
				String receiptNo=stockInDetail.getId();
				
				for (CStockInRepairinfo stockInRepairinfo : sCStockInRepairinfos) 
				{
						String detailId=stockInRepairinfo.getReceiptDetailId();
						if(receiptNo.equals(detailId))
						{
							     for (BRepairInfo bRepairInfo : bRepairInfos) 
							     {
										 String id=String.valueOf(bRepairInfo.getId());
									     String repairId=stockInRepairinfo.getRepairId();
										
									     if(id.equals(repairId))
									     {
									    	 map.put(SystemConst.PROPERTY_PREFIX+id, stockInRepairinfo.getRepairFee());
										 }
									    
							     }
					    }
				}
				
				lists.add(map);
		}
		return lists;
	}

	
	

}
