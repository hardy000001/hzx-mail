package com.lq.lss.core.service.impl;

import com.lq.lss.core.dao.StockTransferDetailDao;
import com.lq.lss.core.service.StockTransferDetailService;
import com.lq.lss.model.CStockTransferDetail;
import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;

import java.util.List;

/**
 * 
 * @author lanbo
 *
 */
@Service
public class StockTransferDetailServiceImpl extends EasyUIServiceBase<CStockTransferDetail, String, StockTransferDetailDao> implements StockTransferDetailService {

    @Override
    public List<CStockTransferDetail> queryDetailList(String tsfSerialNo) {
        return modelDao.queryDetailList(tsfSerialNo);
    }
}
