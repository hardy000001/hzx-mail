package com.lq.lss.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lq.lss.core.dao.base.LssSimpleBaseDao;
import com.lq.lss.model.CStockSend;

/**
 * 
 * @author ch
 *
 */
@Repository
public class StockSendDao extends LssSimpleBaseDao<CStockSend, String> {
	
	public CStockSend findCStockSend(String sendSerialno) {
		return (CStockSend) findObjectByParams("findCStockSend", sendSerialno);
	}
}
