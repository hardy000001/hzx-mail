package com.lq.lss.dto;

import java.util.List;


import com.lq.lss.model.CStockSendDetail;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.CStockTemporaryDetail;
                                                    
/**
 *
 * @author  作者: CH
 * @date 创建时间: 2016-10-08 09:26:28
 */
public class CStockTemporaryDyDto{

	private CStockTemporary cStockTemporary;
   
	private List<CStockTemporaryDetail>   cStockTemporaryDetails;

	public CStockTemporary getcStockTemporary() {
		return cStockTemporary;
	}

	public void setcStockTemporary(CStockTemporary cStockTemporary) {
		this.cStockTemporary = cStockTemporary;
	}

	

	public List<CStockTemporaryDetail> getcStockTemporaryDetails() {
		return cStockTemporaryDetails;
	}

	public void setcStockTemporaryDetails(List<CStockTemporaryDetail> cStockTemporaryDetails) {
		this.cStockTemporaryDetails = cStockTemporaryDetails;
	}

	
	
	
	
}