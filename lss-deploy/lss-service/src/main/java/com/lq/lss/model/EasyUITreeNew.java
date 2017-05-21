package com.lq.lss.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
public class EasyUITreeNew {

    private String id; // 绑定节点的标识值
    private String text; // 显示的节点文本
    private String state; // 节点状态，'open' 或 'closed'。
    private String iconCls; // 显示的节点图标CSS类ID。
    private Boolean checked; // 该节点是否被选中。
    private Map<String, Object> attributes; // 绑定该节点的自定义属性。
    private String pid;  // 父id
    private String ptext; // 父节点文本
    private String filed1; // 预留属性1
    private String filed2; // 预留属性2
    private String filed3; // 预留属性3
    private List<EasyUITreeNew> children; // 子节点
    /** 名称 **/
   	private String name;
   	/** 同级排序 **/
   	private String position;
   	/** 记账单位 **/
   	private String accountFlag;
   	/** 换算单位 **/
   	private String convertFlag;
   	/** 状态 0：正常 9：禁用 **/
   	private String status;
   	/** 创建时间 **/
   	private Date createtime;
   	/** 父ID **/
	private Integer parentid;
	
	private Integer typeid;

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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtext() {
        return ptext;
    }

    public void setPtext(String ptext) {
        this.ptext = ptext;
    }

    public List<EasyUITreeNew> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITreeNew> children) {
        this.children = children;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	public String getConvertFlag() {
		return convertFlag;
	}

	public void setConvertFlag(String convertFlag) {
		this.convertFlag = convertFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
    
    
}