package com.lq.lss.model;

import java.util.List;

/**
 * 
 * @author lanbo
 *
 */
public class EasyUITree {

	private String id; // 绑定节点的标识值
	private String text; // 显示的节点文本
	private String state; // 节点状态，'open' 或 'closed'。
	private String iconCls; // 显示的节点图标CSS类ID。
	private String checked; // 该节点是否被选中。
	private String attributes; // 绑定该节点的自定义属性。
	private String filed1; // 绑定该节点的自定义字段。
	private String filed2; // 绑定该节点的自定义字段。
	private String filed3; // 绑定该节点的自定义字段。
	private String filed4; // 绑定该节点的自定义字段。
	private String target; // 目标DOM对象。
	private String pId;  // 父id
	private String pText; // 父节点文本
	private List<EasyUITree> children; // 子节点
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public String getFiled1() {
		return filed1;
	}
	public void setFiled1(String filed1) {
		this.filed1 = filed1;
	}
	public String getFiled2() {
		return filed2;
	}
	public void setFiled2(String filed2) {
		this.filed2 = filed2;
	}
	public String getFiled3() {
		return filed3;
	}
	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<EasyUITree> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpText() {
		return pText;
	}
	public void setpText(String pText) {
		this.pText = pText;
	}
	public String getFiled4() {
		return filed4;
	}
	public void setFiled4(String filed4) {
		this.filed4 = filed4;
	}
    
	
	
}