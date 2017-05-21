package com.lq.lss.core.service.impl;




import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.StockTemporaryDao;
import com.lq.lss.core.dao.StockTemporaryDetailDao;
import com.lq.lss.core.service.StockTemporaryDetailService;
import com.lq.lss.model.CStockTemporaryDetail;



@Service
public class StockTemporaryDetailServiceImpl extends EasyUIServiceBase<CStockTemporaryDetail, String, StockTemporaryDetailDao> implements StockTemporaryDetailService{

	@Resource
	StockTemporaryDetailDao stdedao;
	@Override
	public List<CStockTemporaryDetail> findCstdpBytemSerialno(String temSerialno) {
		// TODO Auto-generated method stub
		return stdedao.findByCStockTemporaryDetail(temSerialno);
	}

	
}
