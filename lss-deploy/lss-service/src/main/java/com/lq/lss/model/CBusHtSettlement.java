package com.lq.lss.model;

import java.math.BigDecimal;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                        
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-04 13:27:03
 */
public class CBusHtSettlement extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


	/** id**/
    private String id;
	/** 合同编号**/
    private String htcode;
	/** 上车费**/
    private BigDecimal getonFee;
	/** 下车费**/
    private BigDecimal getoffFee;
	/** 运输费**/
    private BigDecimal transportFee;
	/** 押金**/
    private BigDecimal deposit;
	/** 滞纳金**/
    private BigDecimal lateFee;
	/** 袋费**/
    private BigDecimal bagFee;
	/** 滞纳金开起日期**/
    private Integer lateBegdate;
	/** 装卸费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出**/
    private String zxfeeBelong;
	/** 运费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出**/
    private String transportfeeBelong;
	/** 杂费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出**/
    private String otherfeeBelong;
	/** 结算方式1：算头不算尾2：算尾不算头3：算头算尾 4：头尾都不算**/
    private String settlementType;

   
	
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
    * 获取属性:htcode
    * 合同编号
    * @return htcode
    */
   public String getHtcode() {
       return htcode;
   }
   /**
    * 设置属性:htcode
    * 合同编号
    * @param htcode
    */
   public void setHtcode(String htcode) {
       this.htcode = htcode;
   }
	
   /**
    * 获取属性:getonFee
    * 上车费
    * @return getonFee
    */
   public BigDecimal getGetonFee() {
       return getonFee;
   }
   /**
    * 设置属性:getonFee
    * 上车费
    * @param getonFee
    */
   public void setGetonFee(BigDecimal getonFee) {
       this.getonFee = getonFee;
   }
	
   /**
    * 获取属性:getoffFee
    * 下车费
    * @return getoffFee
    */
   public BigDecimal getGetoffFee() {
       return getoffFee;
   }
   /**
    * 设置属性:getoffFee
    * 下车费
    * @param getoffFee
    */
   public void setGetoffFee(BigDecimal getoffFee) {
       this.getoffFee = getoffFee;
   }
	
   /**
    * 获取属性:transportFee
    * 运输费
    * @return transportFee
    */
   public BigDecimal getTransportFee() {
       return transportFee;
   }
   /**
    * 设置属性:transportFee
    * 运输费
    * @param transportFee
    */
   public void setTransportFee(BigDecimal transportFee) {
       this.transportFee = transportFee;
   }
	
   /**
    * 获取属性:deposit
    * 押金
    * @return deposit
    */
   public BigDecimal getDeposit() {
       return deposit;
   }
   /**
    * 设置属性:deposit
    * 押金
    * @param deposit
    */
   public void setDeposit(BigDecimal deposit) {
       this.deposit = deposit;
   }
	
   /**
    * 获取属性:lateFee
    * 滞纳金
    * @return lateFee
    */
   public BigDecimal getLateFee() {
       return lateFee;
   }
   /**
    * 设置属性:lateFee
    * 滞纳金
    * @param lateFee
    */
   public void setLateFee(BigDecimal lateFee) {
       this.lateFee = lateFee;
   }
	
   /**
    * 获取属性:bagFee
    * 袋费
    * @return bagFee
    */
   public BigDecimal getBagFee() {
       return bagFee;
   }
   /**
    * 设置属性:bagFee
    * 袋费
    * @param bagFee
    */
   public void setBagFee(BigDecimal bagFee) {
       this.bagFee = bagFee;
   }
	
   /**
    * 获取属性:lateBegdate
    * 滞纳金开起日期
    * @return lateBegdate
    */
   public Integer getLateBegdate() {
       return lateBegdate;
   }
   /**
    * 设置属性:lateBegdate
    * 滞纳金开起日期
    * @param lateBegdate
    */
   public void setLateBegdate(Integer lateBegdate) {
       this.lateBegdate = lateBegdate;
   }
	
   /**
    * 获取属性:zxfeeBelong
    * 装卸费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @return zxfeeBelong
    */
   public String getZxfeeBelong() {
       return zxfeeBelong;
   }
   /**
    * 设置属性:zxfeeBelong
    * 装卸费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @param zxfeeBelong
    */
   public void setZxfeeBelong(String zxfeeBelong) {
       this.zxfeeBelong = zxfeeBelong;
   }
	
   /**
    * 获取属性:transportfeeBelong
    * 运费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @return transportfeeBelong
    */
   public String getTransportfeeBelong() {
       return transportfeeBelong;
   }
   /**
    * 设置属性:transportfeeBelong
    * 运费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @param transportfeeBelong
    */
   public void setTransportfeeBelong(String transportfeeBelong) {
       this.transportfeeBelong = transportfeeBelong;
   }
	
   /**
    * 获取属性:otherfeeBelong
    * 杂费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @return otherfeeBelong
    */
   public String getOtherfeeBelong() {
       return otherfeeBelong;
   }
   /**
    * 设置属性:otherfeeBelong
    * 杂费归属1：乙方出，甲方不代收2：乙方出，甲方代收3：甲方出
    * @param otherfeeBelong
    */
   public void setOtherfeeBelong(String otherfeeBelong) {
       this.otherfeeBelong = otherfeeBelong;
   }
	
   /**
    * 获取属性:settlementType
    * 结算方式1：算头不算尾2：算尾不算头3：算头算尾 4：头尾都不算
    * @return settlementType
    */
   public String getSettlementType() {
       return settlementType;
   }
   /**
    * 设置属性:settlementType
    * 结算方式1：算头不算尾2：算尾不算头3：算头算尾 4：头尾都不算
    * @param settlementType
    */
   public void setSettlementType(String settlementType) {
       this.settlementType = settlementType;
   }

}