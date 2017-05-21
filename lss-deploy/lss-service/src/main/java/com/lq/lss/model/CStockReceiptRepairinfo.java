package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 11:01:05
 */
public class CStockReceiptRepairinfo extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 收料明细id**/
    private String receiptDetailId;
	/** 维修项id**/
    private String repairId;
	/** 维修费用**/
    private BigDecimal repairFee;
	/** 收料流水号**/
    private String receiptSerialno;

   
	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public String getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(String id) {
       this.id = id;
   }
	
   /**
    * 获取属性:receiptDetailId
    * 收料明细id
    * @return receiptDetailId
    */
   public String getReceiptDetailId() {
       return receiptDetailId;
   }
   /**
    * 设置属性:receiptDetailId
    * 收料明细id
    * @param receiptDetailId
    */
   public void setReceiptDetailId(String receiptDetailId) {
       this.receiptDetailId = receiptDetailId;
   }
	
   /**
    * 获取属性:repairId
    * 维修项id
    * @return repairId
    */
   public String getRepairId() {
       return repairId;
   }
   /**
    * 设置属性:repairId
    * 维修项id
    * @param repairId
    */
   public void setRepairId(String repairId) {
       this.repairId = repairId;
   }
	
   /**
    * 获取属性:repairFee
    * 维修费用
    * @return repairFee
    */
   public BigDecimal getRepairFee() {
       return repairFee;
   }
   /**
    * 设置属性:repairFee
    * 维修费用
    * @param repairFee
    */
   public void setRepairFee(BigDecimal repairFee) {
       this.repairFee = repairFee;
   }
	
   /**
    * 获取属性:receiptSerialno
    * 收料流水号
    * @return receiptSerialno
    */
   public String getReceiptSerialno() {
       return receiptSerialno;
   }
   /**
    * 设置属性:receiptSerialno
    * 收料流水号
    * @param receiptSerialno
    */
   public void setReceiptSerialno(String receiptSerialno) {
       this.receiptSerialno = receiptSerialno;
   }

}