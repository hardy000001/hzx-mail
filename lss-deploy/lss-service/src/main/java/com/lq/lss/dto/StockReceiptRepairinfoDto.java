package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-17 12:41:53
 */
public class StockReceiptRepairinfoDto{

	
	/** 收料明细id**/
    private String receiptDetailId;
	/** 维修项id**/
    private String repairId;
	/** 维修费用**/
    private String repairFee;

	
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
   public String getRepairFee() {
       return repairFee;
   }
   /**
    * 设置属性:repairFee
    * 维修费用
    * @param repairFee
    */
   public void setRepairFee(String repairFee) {
       this.repairFee = repairFee;
   }

}