package com.lq.lss.core.dao;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockTransferDetail;

import java.util.List;

/**
 * 
 * @author lanbo
 *
 */
@Repository
public class StockTransferDetailDao extends LssSimpleBaseDao<CStockTransferDetail, String> {

    public List<CStockTransferDetail> queryDetailList(String tsfSerialNo){

        return  (List<CStockTransferDetail>) findListByParams("queryDetailList", tsfSerialNo);
    }

}
