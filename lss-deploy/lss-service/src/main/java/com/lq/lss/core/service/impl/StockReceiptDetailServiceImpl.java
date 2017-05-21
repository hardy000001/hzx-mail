package com.lq.lss.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.BRepairInfo;
import com.lq.lss.model.CStockReceiptDetail;
import com.lq.lss.model.CStockReceiptRepairinfo;
import com.lq.lss.utils.SystemConst;
import com.lq.lss.core.dao.BRepairInfoDao;
import com.lq.lss.core.dao.StockReceiptDetailDao;
import com.lq.lss.core.dao.StockReceiptRepairinfoDao;
import com.lq.lss.core.service.StockReceiptDetailService;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-09 12:31:33
 */
@Service
public class StockReceiptDetailServiceImpl extends EasyUIServiceBase<CStockReceiptDetail, String, StockReceiptDetailDao> implements StockReceiptDetailService{

    @Resource
	private StockReceiptDetailDao cStockReceiptDetailDao;
    @Resource
    private BRepairInfoDao bRepairInfoDao;
    @Resource
    private StockReceiptRepairinfoDao stockReceiptRepairinfoDao;

	@Override
	public List<CStockReceiptDetail> queryReceiptDetailListById(
			String receiptSerialno) {
		
		return modelDao.findReceiptDetailListById(receiptSerialno);
	}

	@Override
	public List<Map<String, Object>> getReceiptDetailListById(
			String receiptSerialno) {
        
		List<Map<String, Object>> lists=new ArrayList<Map<String,Object>>();
		
		List<CStockReceiptDetail> stockReceiptDetails = modelDao
				.findReceiptDetailListById(receiptSerialno);
        List<BRepairInfo> bRepairInfos=bRepairInfoDao.loadAll();
		List<CStockReceiptRepairinfo> stockReceiptRepairinfos = stockReceiptRepairinfoDao
				.queryRepairiBySerialno(receiptSerialno);

		int itemid=1;
		for (CStockReceiptDetail stockReceiptDetail : stockReceiptDetails) {
			
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("itemid", itemid++);
				map.put("materialcode", stockReceiptDetail.getMaterialcode());
				map.put("name", stockReceiptDetail.getName());
				map.put("accountFlag", stockReceiptDetail.getAccountflag());
				map.put("covertRatio", stockReceiptDetail.getCovertratio());
				map.put("convertFlag", stockReceiptDetail.getConvertFlag());
				map.put("totalS", stockReceiptDetail.getTotalS());
				map.put("totalM", stockReceiptDetail.getTotalM());
				map.put("totalT", stockReceiptDetail.getTotalT());
				map.put("typeId", stockReceiptDetail.getTypeId());
				map.put("fname", stockReceiptDetail.getFname());
				map.put("unit", stockReceiptDetail.getUnit());
				
				String receiptNo=stockReceiptDetail.getId();
				
				for (CStockReceiptRepairinfo stockReceiptRepairinfo : stockReceiptRepairinfos) 
				{
						String detailId=stockReceiptRepairinfo.getReceiptDetailId();
						if(receiptNo.equals(detailId))
						{
							     for (BRepairInfo bRepairInfo : bRepairInfos) 
							     {
										 String id=String.valueOf(bRepairInfo.getId());
									     String repairId=stockReceiptRepairinfo.getRepairId();
										
									     if(id.equals(repairId))
									     {
									    	 map.put(SystemConst.PROPERTY_PREFIX+id, stockReceiptRepairinfo.getRepairFee());
										 }
									    
							     }
					    }
				}
				
				lists.add(map);
		}
		return lists;
	}
}