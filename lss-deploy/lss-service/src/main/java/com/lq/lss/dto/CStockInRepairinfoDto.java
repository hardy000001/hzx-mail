package com.lq.lss.dto;


/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-02-23 15:58:20
 */
public class CStockInRepairinfoDto{

	
	
	/** 入库明细id**/
    private String receiptDetailId;
	/** 维修项id**/
    private String repairId;
	/** 维修费用**/
    private String repairFee;
	/** 入库流水号**/
    private String inSerialno;

 
   /**
    * 获取属性:receiptDetailId
    * 入库明细id
    * @return receiptDetailId
    */
   public String getReceiptDetailId() {
       return receiptDetailId;
   }
   /**
    * 设置属性:receiptDetailId
    * 入库明细id
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
	
   /**
    * 获取属性:inSerialno
    * 入库流水号
    * @return inSerialno
    */
   public String getInSerialno() {
       return inSerialno;
   }
   /**
    * 设置属性:inSerialno
    * 入库流水号
    * @param inSerialno
    */
   public void setInSerialno(String inSerialno) {
       this.inSerialno = inSerialno;
   }

}