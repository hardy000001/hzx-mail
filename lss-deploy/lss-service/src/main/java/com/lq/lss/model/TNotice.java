package com.lq.lss.model;

import java.util.Date;
import com.lq.easyui.model.easyui.EasyUiModel;
                                                                
/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2017-03-17 09:11:48
 */
public class TNotice extends EasyUiModel<String>{

	/** 序列化ID */
	private static final long serialVersionUID = 1L;


    /** id **/
    private String id;
	/** 通知ID**/
    private String nid;
	/** 标题**/
    private String ntitle;
	/** 通知内容**/
    private String notice;
	/** 中心ID**/
    private Integer deptid;
	/** 发送人**/
    private Integer fromoper;
	/** 接收人**/
    private Integer tooper;
	/** 通知状态；0：未读；1：已读：2：已删除**/
    private String status;
	/** 管理者状态；0：正常；1：已删除**/
    private String adminstatus;
	/** 重要程度；0：普通；1：紧急；**/
    private String level;
	/** 备注**/
    private String remark;
	/** 创建时间**/
    private Date createtime;
	/** 创建人**/
    private Integer createoper;
	/** 修改时间**/
    private Date modifytime;
	/** 修改人**/
    private Integer modifyoper;
	/** 类型： 0：交易消息 1：系统消息**/
    private String type;
	/** 单据号：**/
    private String orderno;
    /** 接收商户**/
    private String tomerchant;
    
    private String fromName;
    private String toName;

   
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
    * 获取属性:nid
    * 通知ID
    * @return nid
    */
   public String getNid() {
       return nid;
   }
   /**
    * 设置属性:nid
    * 通知ID
    * @param nid
    */
   public void setNid(String nid) {
       this.nid = nid;
   }
	
   /**
    * 获取属性:ntitle
    * 标题
    * @return ntitle
    */
   public String getNtitle() {
       return ntitle;
   }
   /**
    * 设置属性:ntitle
    * 标题
    * @param ntitle
    */
   public void setNtitle(String ntitle) {
       this.ntitle = ntitle;
   }
	
   /**
    * 获取属性:notice
    * 通知内容
    * @return notice
    */
   public String getNotice() {
       return notice;
   }
   /**
    * 设置属性:notice
    * 通知内容
    * @param notice
    */
   public void setNotice(String notice) {
       this.notice = notice;
   }
	
   /**
    * 获取属性:deptid
    * 中心ID
    * @return deptid
    */
   public Integer getDeptid() {
       return deptid;
   }
   /**
    * 设置属性:deptid
    * 中心ID
    * @param deptid
    */
   public void setDeptid(Integer deptid) {
       this.deptid = deptid;
   }
	
   /**
    * 获取属性:fromoper
    * 发送人
    * @return fromoper
    */
   public Integer getFromoper() {
       return fromoper;
   }
   /**
    * 设置属性:fromoper
    * 发送人
    * @param fromoper
    */
   public void setFromoper(Integer fromoper) {
       this.fromoper = fromoper;
   }
	
   /**
    * 获取属性:tooper
    * 接收人
    * @return tooper
    */
   public Integer getTooper() {
       return tooper;
   }
   /**
    * 设置属性:tooper
    * 接收人
    * @param tooper
    */
   public void setTooper(Integer tooper) {
       this.tooper = tooper;
   }
	
   /**
    * 获取属性:status
    * 通知状态；0：未读；1：已读：2：已删除
    * @return status
    */
   public String getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * 通知状态；0：未读；1：已读：2：已删除
    * @param status
    */
   public void setStatus(String status) {
       this.status = status;
   }
	
   /**
    * 获取属性:adminstatus
    * 管理者状态；0：正常；1：已删除
    * @return adminstatus
    */
   public String getAdminstatus() {
       return adminstatus;
   }
   /**
    * 设置属性:adminstatus
    * 管理者状态；0：正常；1：已删除
    * @param adminstatus
    */
   public void setAdminstatus(String adminstatus) {
       this.adminstatus = adminstatus;
   }
	
   /**
    * 获取属性:level
    * 重要程度；0：普通；1：紧急；
    * @return level
    */
   public String getLevel() {
       return level;
   }
   /**
    * 设置属性:level
    * 重要程度；0：普通；1：紧急；
    * @param level
    */
   public void setLevel(String level) {
       this.level = level;
   }
	
   /**
    * 获取属性:remark
    * 备注
    * @return remark
    */
   public String getRemark() {
       return remark;
   }
   /**
    * 设置属性:remark
    * 备注
    * @param remark
    */
   public void setRemark(String remark) {
       this.remark = remark;
   }
	
   /**
    * 获取属性:createtime
    * 创建时间
    * @return createtime
    */
   public Date getCreatetime() {
       return createtime;
   }
   /**
    * 设置属性:createtime
    * 创建时间
    * @param createtime
    */
   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }
	
   /**
    * 获取属性:createoper
    * 创建人
    * @return createoper
    */
   public Integer getCreateoper() {
       return createoper;
   }
   /**
    * 设置属性:createoper
    * 创建人
    * @param createoper
    */
   public void setCreateoper(Integer createoper) {
       this.createoper = createoper;
   }
	
   /**
    * 获取属性:modifytime
    * 修改时间
    * @return modifytime
    */
   public Date getModifytime() {
       return modifytime;
   }
   /**
    * 设置属性:modifytime
    * 修改时间
    * @param modifytime
    */
   public void setModifytime(Date modifytime) {
       this.modifytime = modifytime;
   }
	
   /**
    * 获取属性:modifyoper
    * 修改人
    * @return modifyoper
    */
   public Integer getModifyoper() {
       return modifyoper;
   }
   /**
    * 设置属性:modifyoper
    * 修改人
    * @param modifyoper
    */
   public void setModifyoper(Integer modifyoper) {
       this.modifyoper = modifyoper;
   }
	
   /**
    * 获取属性:type
    * 类型： 0：交易消息 1：系统消息
    * @return type
    */
   public String getType() {
       return type;
   }
   /**
    * 设置属性:type
    * 类型： 0：交易消息 1：系统消息
    * @param type
    */
   public void setType(String type) {
       this.type = type;
   }
	
   /**
    * 获取属性:orderno
    * 单据号：
    * @return orderno
    */
   public String getOrderno() {
       return orderno;
   }
   /**
    * 设置属性:orderno
    * 单据号：
    * @param orderno
    */
   public void setOrderno(String orderno) {
       this.orderno = orderno;
   }
   /**
    * 获取属性:tomerchant
    * 接收商户
    * @return tomerchant
    */
   public String getTomerchant() {
       return tomerchant;
   }
   /**
    * 设置属性:tomerchant
    * 接收商户
    * @param tomerchant
    */
   public void setTomerchant(String tomerchant) {
       this.tomerchant = tomerchant;
   }
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	   
}