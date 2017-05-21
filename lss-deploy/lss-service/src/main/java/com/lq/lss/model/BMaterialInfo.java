package com.lq.lss.model;

import java.math.BigDecimal;

import com.lq.easyui.model.easyui.EasyUiModel;

/**
 *
 * @author 
 * @since 2016-08-31
 */
public class BMaterialInfo  extends EasyUiModel<Integer>  {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Integer id;
	/** 材料名称 **/
    private String name;
	/** 材料类别 **/
    private Integer typeid;
	/** 材料类别名称 **/
    private String typeName;
	/** 材料编码 **/
    private String code;
	/** 材料单价 **/
    private BigDecimal price;
	/** 记账单位 **/
    private String accountFlag;
	/** 换算单位 **/
    private String convertFlag;
	/** 换算系数 **/
    private BigDecimal covertRatio;
	/** 赔偿单位 **/
    private String compensateFlag;
	/** 赔偿系数 **/
    private BigDecimal compensateRatio;
	/** 运费单位 **/
    private String expressFlag;
	/** 运费系数 **/
    private BigDecimal expressRatio;
	/** 运费单价 **/
    private BigDecimal expressPrice;
    
	/** 调拨单位 **/
    private String transferFlag;
	/** 调拨单价 **/
    private BigDecimal transferPrice;
    
	/** 装卸费单价 **/
    private BigDecimal zxPrice;
	/** 拼音简码 **/
    private String pyCode;
	/** 状态 0：正常 9：禁用 **/
    private String status;

	
   /**
    * 获取属性:id
    * id
    * @return id
    */
   public Integer getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Integer id) {
       this.id = id;
   }
	
   /**
    * 获取属性:name
    * 材料名称
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * 材料名称
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
   
   /**
    * 获取属性:typeName
    * 材料名称
    * @return typeName
    */
   public String getTypeName() {
       return typeName;
   }
   /**
    * 设置属性:typeName
    * 材料名称
    * @param typeName
    */
   public void setTypeName(String typeName) {
       this.typeName = typeName;
   }
   
   
   
	
   /**
    * 获取属性:typeid
    * 材料类别
    * @return typeid
    */
   public Integer getTypeid() {
       return typeid;
   }
   /**
    * 设置属性:typeid
    * 材料类别
    * @param typeid
    */
   public void setTypeid(Integer typeid) {
       this.typeid = typeid;
   }
	
   /**
    * 获取属性:code
    * 材料编码
    * @return code
    */
   public String getCode() {
       return code;
   }
   /**
    * 设置属性:code
    * 材料编码
    * @param code
    */
   public void setCode(String code) {
       this.code = code;
   }
	
   /**
    * 获取属性:price
    * 材料单价
    * @return price
    */
   public BigDecimal getPrice() {
       return price;
   }
   /**
    * 设置属性:price
    * 材料单价
    * @param price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
   }
	
   /**
    * 获取属性:accountFlag
    * 记账单位
    * @return accountFlag
    */
   public String getAccountFlag() {
       return accountFlag;
   }
   /**
    * 设置属性:accountFlag
    * 记账单位
    * @param accountFlag
    */
   public void setAccountFlag(String accountFlag) {
       this.accountFlag = accountFlag;
   }
	
   /**
    * 获取属性:convertFlag
    * 换算单位
    * @return convertFlag
    */
   public String getConvertFlag() {
       return convertFlag;
   }
   /**
    * 设置属性:convertFlag
    * 换算单位
    * @param convertFlag
    */
   public void setConvertFlag(String convertFlag) {
       this.convertFlag = convertFlag;
   }
	
   /**
    * 获取属性:covertRatio
    * 换算系数
    * @return covertRatio
    */
   public BigDecimal getCovertRatio() {
       return covertRatio;
   }
   /**
    * 设置属性:covertRatio
    * 换算系数
    * @param covertRatio
    */
   public void setCovertRatio(BigDecimal covertRatio) {
       this.covertRatio = covertRatio;
   }
	
   /**
    * 获取属性:compensateFlag
    * 赔偿单位
    * @return compensateFlag
    */
   public String getCompensateFlag() {
       return compensateFlag;
   }
   /**
    * 设置属性:compensateFlag
    * 赔偿单位
    * @param compensateFlag
    */
   public void setCompensateFlag(String compensateFlag) {
       this.compensateFlag = compensateFlag;
   }
	
   /**
    * 获取属性:compensateRatio
    * 赔偿系数
    * @return compensateRatio
    */
   public BigDecimal getCompensateRatio() {
       return compensateRatio;
   }
   /**
    * 设置属性:compensateRatio
    * 赔偿系数
    * @param compensateRatio
    */
   public void setCompensateRatio(BigDecimal compensateRatio) {
       this.compensateRatio = compensateRatio;
   }
	
   /**
    * 获取属性:expressFlag
    * 运费单位
    * @return expressFlag
    */
   public String getExpressFlag() {
       return expressFlag;
   }
   /**
    * 设置属性:expressFlag
    * 运费单位
    * @param expressFlag
    */
   public void setExpressFlag(String expressFlag) {
       this.expressFlag = expressFlag;
   }
	
   /**
    * 获取属性:expressRatio
    * 运费系数
    * @return expressRatio
    */
   public BigDecimal getExpressRatio() {
       return expressRatio;
   }
   /**
    * 设置属性:expressRatio
    * 运费系数
    * @param expressRatio
    */
   public void setExpressRatio(BigDecimal expressRatio) {
       this.expressRatio = expressRatio;
   }
	
   /**
    * 获取属性:expressPrice
    * 运费单价
    * @return expressPrice
    */
   public BigDecimal getExpressPrice() {
       return expressPrice;
   }
   /**
    * 设置属性:expressPrice
    * 运费单价
    * @param expressPrice
    */
   public void setExpressPrice(BigDecimal expressPrice) {
       this.expressPrice = expressPrice;
   }
   
   
   /**
    * 获取属性:transferFlag
    * 调拨单位
    * @return transferFlag
    */
   public String getTransferFlag() {
       return transferFlag;
   }
   /**
    * 设置属性:transferFlag
    * 调拨单位
    * @param transferFlag
    */
   public void setTransferFlag(String transferFlag) {
       this.transferFlag = transferFlag;
   }
   
   
   /**
    * 获取属性:TransferPrice
    * 调拨单价
    * @return TransferPrice
    */
   public BigDecimal getTransferPrice() {
       return transferPrice;
   }
   /**
    * 设置属性:TransferPrice
    * 调拨单价
    * @param transferPrice
    */
   public void setTransferPrice(BigDecimal transferPrice) {
       this.transferPrice = transferPrice;
   }
     
   
   
   
   
	
   /**
    * 获取属性:zxPrice
    * 装卸费单价
    * @return zxPrice
    */
   public BigDecimal getZxPrice() {
       return zxPrice;
   }
   /**
    * 设置属性:zxPrice
    * 装卸费单价
    * @param zxPrice
    */
   public void setZxPrice(BigDecimal zxPrice) {
       this.zxPrice = zxPrice;
   }
	
   /**
    * 获取属性:pyCode
    * 拼音简码
    * @return pyCode
    */
   public String getPyCode() {
       return pyCode;
   }
   /**
    * 设置属性:pyCode
    * 拼音简码
    * @param pyCode
    */
   public void setPyCode(String pyCode) {
       this.pyCode = pyCode;
   }
	
   /**
    * 获取属性:status
    * 状态 0：正常 9：禁用
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 状态 0：正常 9：禁用
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }

}