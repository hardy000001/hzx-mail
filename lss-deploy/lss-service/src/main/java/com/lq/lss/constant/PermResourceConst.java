package com.lq.lss.constant;

/**
 * Created by lanbo on 16/11/12.
 */
public interface PermResourceConst {

    /* 系统信息维护 */
    String SYS_MRG = "101";
    /* 中心核心业务管理 */
    String CENTER_BUS_MRG = "102";
    /*  财务报表管理*/
    String ENTER_EX_MGR = "103";
    
    
    /*  系统管理*/
    String SYS_BASE_MGR = "10101";
    /* 租赁公司管理 */
    String SYS_MCH_MGR = "10102";
    
    /* 新增租赁公司*/
    String SYS_MCH_ADD = "101020201";
    /* 修改租赁公司 */
    String SYS_MCH_UPDATE = "101020202";
    /* 删除租赁公司 */
    String SYS_MCH_DEL = "101020203";
    /* 审核租赁公司  */
    String SYS_MCH_CHECK = "101020204";

    /* 新增租赁客户 */
    String SYS_CUSTOMER_ADD = "101020301";
    /* 修改租赁客户 */
    String SYS_CUSTOMER_UPDATE = "101020302";
    /* 删除租赁客户 */
    String SYS_CUSTOMER_DEL = "101020303";
    /* 审核租赁客户  */
    String SYS_CUSTOMER_CHECK = "101020304";
    
    /* 新增调拨客户 */
    String SYS_CUSTOMER_RENTINFO_ADD = "101020401";
    /* 修改调拨客户 */
    String SYS_CUSTOMER_RENTINFO_UPDATE = "101020402";
    /* 删除调拨客户 */
    String SYS_CUSTOMER_RENTINFO_DEL = "101020403";
    /* 审核调拨客户  */
    String SYS_CUSTOMER_RENTINFO_CHEKE = "101020404";

    
    /* 用户管理 */
    String SYS_USER_MRG = "1010101";
    /* 新增用户 */
    String SYS_USER_MGR_ADD = "101010101";
    /* 修改用户 */
    String SYS_USER_MGR_UPDATE = "101010102";
    /* 删除用户  */
    String SYS_USER_MGR_DEL = "101010103";
    /* 锁定用户  */
    String SYS_USER_MGR_LOCK = "101010104";
    /* 解锁用户  */
    String SYS_USER_MGR_UNLOCK = "101010105";
    
    

    /*  角色管理*/
    String SYS_ROLE_MGR = "1010102";
    String SYS_ROLE_ADD = "101010201";
    String SYS_ROLE_UPDATE = "101010202";
    String SYS_ROLE_DEL = "101010203";
    String SYS_ROLE_RESOURCE = "101010204";

    /* 部门管理  */
    String SYS_DEPT_MRG = "1010103";
    String SYS_DEPT_MGR_ADD = "101010301";
    String SYS_DEPT_MGR_UPDATE = "101010302";
    String SYS_DEPT_MGR_DEL = "101010302";

    /* 租赁物资类别*/
    String SYS_MT_TYPE_MGR = "1010301";
    String SYS_MT_TYPE_ADD = "101030101";
    String SYS_MT_TYPE_UPDATE = "101030102";
    String SYS_MT_TYPE_DEL = "101030103";
    /* 租赁物资*/
    String SYS_MT_INFO_MGR = "1010302";
    String SYS_MT_INFO_ADD = "101030201";
    String SYS_MT_INFO_UPDATE = "101030202";
    String SYS_MT_INFO_DEL = "101030203";
    /* 易耗品类别*/
    String SYS_CON_TYPE_MGR = "1010303";
    String SYS_CON_TYPE_ADD = "101030301";
    String SYS_CON_TYPE_UPDATE = "101030302";
    String SYS_CON_TYPE_DEL = "101030303";
    /* 易耗品信息*/
    String SYS_CON_INFO_MGR = "1010304";
    String SYS_CON_INFO_ADD = "101030401";
    String SYS_CON_INFO_UPDATE = "101030402";
    String SYS_CON_INFO_DEL = "101030403";
    /* 维修标准*/
    String SYS_REP_MRG = "1010305";
    String SYS_REP_ADD = "101030501";
    String SYS_REP_UPDATE = "101030502";
    String SYS_REP_DEL = "101030503";
    
    /* 添加通知 */
    String SYS_NOTICE_ADD = "101010501";
    /* 修改通知 */
    String SYS_NOTICE_UPDATE = "101010502";
    /* 删除通知 */
    String SYS_NOTICE_DEL = "101010503";

    /* 租赁公司相互调拨 */
    String CENTER_WZIN_TRANS = "1020104";
    /* 新增调拨单 */
    String CENTER_TRANS_ADD = "102010401";
    /* 修改挑拨单 */
    String CENTER_TRANS_UPDATE = "102010402";
    /* 删除调拨单 */
    String CENTER_TRANS_DEL = "102010403";
    /* 审核调拨单  */
    String CENTER_TRANS_CHECK = "102010404";

    /* 新增采购单 */
    String CENTER_PUR_ADD = "102040101";
    /* 修改采购单 */
    String CENTER_PUR_UPDATE = "102040102";
    /* 删除采购单 */
    String CENTER_PUR_DEL = "102040103";
    /* 审核采购单  */
    String CENTER_PUR_CHECK = "102040104";

    /* 新增出库单 */
    String CENTER_OUT_ADD = "102010301";
    /* 修改出库单 */
    String CENTER_OUT_UPDATE = "102010302";
    /* 删除出库单 */
    String CENTER_OUT_DEL = "102010303";
    /* 审核出库单  */
    String CENTER_OUT_CHECK = "102010304";
    
    
    /* 新增入库申请单 */
    String CENTER_IN_ADD = "102010201";
    /* 修改入库申请单 */
    String CENTER_IN_UPDATE = "102010202";
    /* 删除入库申请单 */
    String CENTER_IN_DEL = "102010203";
    /* 审核入库申请单  */
    String CENTER_IN_CHECK = "102010204";
   

    /* 新增销售单 */
    String CENTER_SALE_ADD = "102040201";
    /* 修改销售单 */
    String CENTER_SALE_UPDATE = "102040202";
    /* 删除销售单 */
    String CENTER_SALE_DEL = "102040203";
    /* 审核销售单  */
    String CENTER_SALE_CHECK = "102040204";

    /* 新增租赁合同 */
    String CENTER_HT_ADD = "102050101";
    /* 修改租赁合同 */
    String CENTER_HT_UPDATE = "102050102";
    /* 删除租赁合同 */
    String CENTER_HT_DEL = "102050103";
    /* 审核租赁合同 */
    String CENTER_HT_CHECK = "102050104";

    /* 新增收料单 */
    String CENTER_RECEIPT_ADD = "102050401";
    /* 修改收料单 */
    String CENTER_RECEIPT_UPDATE = "102050402";
    /* 删除收料单 */
    String CENTER_RECEIPT_DEL = "102050403";
    /* 审核收料单  */
    String CENTER_RECEIPT_CHECK = "102050404";
    /* 新增加工改制单*/
    String CENTER_REMODELING_ADD = "102030101";
    /* 修改加工改制单*/
    String CENTER_REMODELING_UPDATE = "102030102";
    /* 删除加工改制单*/
    String CENTER_REMODELING_DEL = "102030103";
    /* 审核加工改制单  */
    String CENTER_REMODELING_CHECK = "102030104";
    
    
    
    /* 新增暂存单*/
    String CENTER_TEMPORARY_ADD = "102030201";
    /* 修改暂存单*/
    String CENTER_TEMPORARY_UPDATE = "102030202";
    /* 删除暂存单*/
    String CENTER_TEMPORARY_DEL = "102030203";
    /* 审核暂存单  */
    String CENTER_TEMPORARY_CHECK = "102030204";
    
    /* 新增中心调出单*/
    String CENTER_CENTERTRANSFER_ADD = "102020201";
    /* 修改中心调出单*/
    String CENTER_CENTERTRANSFER_UPDATE = "102020202";
    /* 删除中心调出单*/
    String CENTER_CENTERTRANSFER_DEL = "102020203";
    /* 审核中心调出单  */
    String CENTER_CENTERTRANSFER_CHECK = "102020204";
    
    
    /* 新增中心调入单*/
    String CENTER_CENTERTRANSFERIN_ADD = "102020301";
    /* 修改中心调入单*/
    String CENTER_CENTERTRANSFERIN_UPDATE = "102020302";
    /* 删除中心调入单*/
    String CENTER_CENTERTRANSFERIN_DEL = "102020303";
    /* 审核中心调入单  */
    String CENTER_CENTERTRANSFERIN_CHECK = "102020304";
    
    
    
    /* 新增合同报停*/
    String BUS_HTSTOPFLOW_ADD = "102050201";
    /* 修改合同报停*/
    String BUS_HTSTOPFLOW_UPDATE = "102050202";
    /* 删除合同报停*/
    String BUS_HTSTOPFLOW_DEL = "102050203";
    /* 审核合同报停  */
    String BUS_HTSTOPFLOW_CHECK = "102050204";
    
    
    /* 新增发料单*/
    String CENTER_SEND_ADD = "102050301";
    /* 修改发料单*/
    String CENTER_SEND_UPDATE = "102050302";
    /* 删除发料单*/
    String CENTER_SEND_DEL = "102050303";
    /* 审核发料单  */
    String CENTER_SEND_CHECK = "102050304";
    
    

    /* 新增收付款单*/
    String BUS_ACCOUNT_ADD = "102050501";
    /* 修改收付款单*/
    String BUS_ACCOUNT_UPDATE = "102050502";
    /* 删除收付款单*/
    String BUS_ACCOUNT_DEL = "102050503";
    /* 审核收付款单  */
    String BUS_ACCOUNT_CHECK = "102050504";
    
    
    /* 盘点新增*/
    String STOCK_INVENTORY_ADD = "102010501";
    /* 盘点修改*/
    String STOCK_INVENTORY_UPDATE = "102010502";
    /* 盘点删除*/
    String STOCK_INVENTORY_DEL = "102010503";
    /* 盘点审核 */
    String STOCK_INVENTORY_CHECK = "102010504";
    
    
    /* 新增赔偿单*/
    String STOCK_CMPENSATE_ADD = "102050601";
    /* 修改赔偿单*/
    String STOCK_CMPENSATE_UPDATE = "102050602";
    /* 删除赔偿单*/
    String STOCK_CMPENSATE_DEL = "102050603";
    /* 审核赔偿单  */
    String STOCK_CMPENSATE_CHECK = "102050604";

    
    
    
    /* 新增中心暂存单 */
    String STOCK_TEMPORARY_IN_ADD = "102020401";
    /* 修改中心暂存单 */
    String STOCK_TEMPORARY_IN_UPDATE = "102020402";
    /* 删除中心暂存单 */
    String STOCK_TEMPORARY_IN_DEL = "102020403";
    /* 审核中心暂存单  */
    String STOCK_TEMPORARY_IN_CHECK = "102020404";
    
    
    
    /* 新增中心提暂存单 */
    String STOCK_TEMPORARY_OUT_ADD = "102020501";
    /* 修改中心提暂存单 */
    String STOCK_TEMPORARY_OUT_UPDATE = "102020502";
    /* 删除中心提暂存单 */
    String STOCK_TEMPORARY_OUT_DEL = "102020503";
    /* 审核中心提暂存单  */
    String STOCK_TEMPORARY_OUT_CHECK = "102020504";
    
    
    
    /* 新增中心内部调入单 */
    String CENTER_TRANS_IN_ADD = "102010601";
    /* 修改中心内部调入单 */
    String CENTER_TRANS_IN_UPDATE = "102010602";
    /* 删除中心内部调入单 */
    String CENTER_TRANS_IN_DEL = "102010603";
    /* 审核中心内部调入单  */
    String CENTER_TRANS_IN_CHECK = "102010604";
    
    
    /* 新增中心内部调出单 */
    String CENTER_TRANS_OUT_ADD = "102010701";
    /* 修改中心内部调出单 */
    String CENTER_TRANS_OUT_UPDATE = "102010702";
    /* 删除中心内部调出单*/
    String CENTER_TRANS_OUT_DEL = "102010703";
    /* 审核中心内部调出单  */
    String CENTER_TRANS_OUT_CHECK = "102010704";
}
