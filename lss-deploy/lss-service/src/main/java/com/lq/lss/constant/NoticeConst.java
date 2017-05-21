package com.lq.lss.constant;

/**
 * 通知
 * @author  作者: hzx
 * @date 创建时间: 2017-3-15上午9:28:17
 */
public interface NoticeConst {
	
	/**
	 * 通知模板
	 */
	String NOTICE_CONTENT_TEMPLATE ="您的单据号：XXXX已被审核拒绝，请您修改后重新提交审核。";

    /**
     * 出库
     */
    final static String NOTICE_CK_TITLE ="出库单消息";
    /**
     * 入库
     */
    final static String NOTICE_RK_TITLE="入库单消息";
    /**
     * 采购
     */
    final static String NOTICE_CG_TITLE ="采购单消息";
    /**
     * 销售
     */
    final static String NOTICE_XS_TITLE="销售单消息";
    /**
     * 发料
     */
    final static String NOTICE_FL_TITLE ="发料单消息";
    /**
     * 收料
     */
    final static String NOTICE_SL_TITLE="收料单消息";
    /**
     * 调出
     */
    final static String NOTICE_DC_TITLE ="调出单消息";
    /**
     * 调入
     */
    final static String NOTICE_DR_TITLE="调入单消息";
    /**
     * 暂存
     */
    final static String NOTICE_ZC_TITLE ="暂存单消息";
    /**
     * 提暂存
     */
    final static String NOTICE_TZC_TITLE="提暂存单消息";
    /**
     * 中心内部调出
     */
    final static String NOTICE_NBDC_TITLE="中心内部调出单消息";
    /**
     * 中心内部调入
     */
    final static String NOTICE_NBDR_TITLE="中心内部调入单消息";
}
