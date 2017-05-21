package com.lq.lss.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.dto.StockInventoryDetailDto;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.CStockIn;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CStockTransfer;
/**
 * 
 * @author Eric
 *
 */
@Repository
public class StockInfoDao extends LssSimpleBaseDao<CStockInfo, Integer> {
	
	/**
	 * 查询列表
	 * @param bConsumableType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CStockInfo> findStockSum(CStockInfo cStockInfo) {
		return (List<CStockInfo>) findListByParams("findStockSum", cStockInfo);
	}

	public List<CStockInfo> findStockTotals(CStockInfo cStockInfo) {
		return (List<CStockInfo>) findListByParams("findByMchcodeAndMaterialcode", cStockInfo);
	}
	@SuppressWarnings("unchecked")
	public List<StockInventoryDetailDto> findInventoryByMchcode(String deptId,
			String mchcode) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("deptid", deptId);
			map.put("mchcode", mchcode);
		
		return (List<StockInventoryDetailDto>) findListByParams("findInventoryByMchcode", map);
	}
	
	public void batchUpdateNum(List<CStockInfo> list){
		update("num_update_BATCH", list);
	}
}
