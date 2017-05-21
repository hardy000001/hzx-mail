package com.lq.lss.enums;

/**
 * 通知状态 枚举类
 * 
 * @author
 */
public enum NoticeStatus {

    
	INIT(0, "初始化"),UNREAD(0, "未读"), READ(1, "已读"), DELETE(2, "删除");

    private int value;

    private String text;

    NoticeStatus(int val, String text) {
        this.value = val;
        this.text = text;
    }

    /**
     * 获取代码值
     * 
     * @return
     */
    public int value() {
        return value;
    }

    /**
     * 获取文字说明
     * @return
     */
    public String asText() {
        return this.text;
    }

    public static NoticeStatus of(int val) {
        for (NoticeStatus status : values()) {
            if (status.value() == val) {
                return status;
            }
        }
        return null;
    }

    public static String toText(Integer val){
        if(val != null){
            NoticeStatus sex = of(val);
            if(sex != null){
                return sex.asText();
            }
        }
        
        return null;
    }
}
