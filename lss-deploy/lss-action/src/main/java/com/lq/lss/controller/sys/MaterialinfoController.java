/**
 *
 */
package com.lq.lss.controller.sys;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.core.service.ConsumableInfoService;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.model.*;
import com.lq.lss.utils.EasyuiUtil;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eric
 */
@Controller
@RequestMapping(value = "/user/sys/materialinfo.htm")

public class MaterialinfoController extends ShiroBaseController<BMaterialInfo, Integer, MaterialInfoService> {


    @Resource
    MaterialTypeService materialTypeService;
    @Resource
    private MaterialInfoService materialInfoService;
    @Resource
    private ConsumableService consumableService;
    @Resource
    private ConsumableInfoService consumableInfoService;


    @Value("/base/materialInfo")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("add", PermResourceConst.SYS_MT_INFO_ADD);
        modelMap.put("update", PermResourceConst.SYS_MT_INFO_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_MT_INFO_DEL);
        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }

    /**
     * 查询类别名称tree  --租赁物资+易耗品
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(params = "method=getMaTypeList")
    public ModelAndView getMaTypeList(HttpServletRequest request, HttpServletResponse response) {

        String typeId = ServletRequestUtils.getStringParameter(request, "id", "");
        String typeName = ServletRequestUtils.getStringParameter(request, "typeName", "");

        BConsumableType conType = new BConsumableType();
        if (StringUtils.hasText(typeId)) {
            conType.setTypeid(Integer.parseInt(typeId));
        }
        if (StringUtils.hasText(typeName)) {
            conType.setName(URLDecoder.decode(typeName));
        }

        List<EasyUITree> treeList = new ArrayList<EasyUITree>();

        if (!StringUtils.hasText(typeId)) {  // 无tree的id 值，默认加载物资类别tree
            List<BMaterialType> typeList = materialTypeService.loadAll();
            for (BMaterialType materialType : typeList) {    // 只做二级处理
                if (materialType.getParentid() == 0) { // 一级节点
                    EasyUITree tree = new EasyUITree();
                    tree.setState("open");
                    tree.setId(materialType.getTypeid() + "");
                    tree.setText(materialType.getName());
                    // 加载子节点
                    List<EasyUITree> treeList1 = new ArrayList<EasyUITree>();
                    for (BMaterialType materialType1 : typeList) {
                        if (materialType1.getParentid() == materialType.getTypeid()) {
                            EasyUITree tree1 = new EasyUITree();
                            tree1.setState("closed");
                            tree1.setId(materialType1.getTypeid() + "");
                            tree1.setText(materialType1.getName());
                            treeList1.add(tree1);
                            //加载材料物资信息
                            List<EasyUITree> treeList2 = new ArrayList<EasyUITree>();
                            List<BMaterialInfo> maInfoList = materialInfoService.queryMaInfoList(materialType1.getTypeid());
                            for (BMaterialInfo bMaterialInfo : maInfoList) {
                                EasyUITree treeInfo = new EasyUITree();
                                treeInfo.setState("open");
                                treeInfo.setAttributes(bMaterialInfo.getAccountFlag());
                                treeInfo.setFiled1("1"); // 表示材料项目
                                treeInfo.setId(bMaterialInfo.getCode());
                                treeInfo.setText(bMaterialInfo.getName());
                                treeInfo.setFiled2(bMaterialInfo.getPrice() + "");
                                treeInfo.setpId(materialType1.getTypeid() + "");
                                treeInfo.setpText(materialType1.getName());
                                treeInfo.setFiled2(bMaterialInfo.getPrice()+""); 
								treeInfo.setFiled3(bMaterialInfo.getCovertRatio()+""); 
								treeInfo.setFiled4(materialType1.getAccountFlag());
                                
                                
                                treeList2.add(treeInfo);
                            }
                            tree1.setChildren(treeList2);
                        }
                    }
                    tree.setChildren(treeList1);
                    treeList.add(tree);
                }
            }

            List<BConsumableType> typeList2 = consumableService.findTypeList(conType);
            // 只做二级处理
            for (BConsumableType bConsumableType : typeList2) {
                if (bConsumableType.getParentid() == 0) { // 一级节点
                    EasyUITree tree = new EasyUITree();
                    tree.setState("open");
                    tree.setId(bConsumableType.getTypeid() + "");
                    tree.setText(bConsumableType.getName());
                    // 加载子节点
                    List<EasyUITree> treeList1 = new ArrayList<EasyUITree>();
                    for (BConsumableType bConsumableType1 : typeList2) {
                        if (bConsumableType1.getParentid() == bConsumableType.getTypeid()) {
                            EasyUITree tree1 = new EasyUITree();
                            tree1.setState("closed");
                            tree1.setId(bConsumableType1.getTypeid() + "");
                            tree1.setText(bConsumableType1.getName());
                            treeList1.add(tree1);
                            //加载消耗品信息
                            List<EasyUITree> treeList2 = new ArrayList<EasyUITree>();
                            Integer typeid = bConsumableType1.getTypeid();
                            List<BConsumableInfo> cInfos = consumableInfoService.queryConsumableInfoList(typeid);
                            for (BConsumableInfo bConsumableInfo : cInfos) {
                                EasyUITree treeInfo = new EasyUITree();
                                treeInfo.setState("open");
                                treeInfo.setAttributes(bConsumableInfo.getUnit());
                                treeInfo.setFiled1("2"); // 表示消耗品项目
                                treeInfo.setId(bConsumableInfo.getId().toString());
                                treeInfo.setText(bConsumableInfo.getName());
                                treeInfo.setFiled2(bConsumableInfo.getPrice() + "");
                                treeInfo.setpId(bConsumableType1.getTypeid() + "");
                                treeInfo.setpText(bConsumableType1.getName());
                                treeList2.add(treeInfo);
                            }
                            tree1.setChildren(treeList2);
                        }
                    }
                    tree.setChildren(treeList1);
                    treeList.add(tree);
                }
            }
        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));
    }


    /**
     * 查询类别名称tree --租赁物资
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(params = "method=getMaterialList")
    public ModelAndView getMaterialList(HttpServletRequest request, HttpServletResponse response) {

        String typeId = ServletRequestUtils.getStringParameter(request, "id", "");
        String typeName = ServletRequestUtils.getStringParameter(request, "typeName", "");

        BConsumableType conType = new BConsumableType();
        if (StringUtils.hasText(typeId)) {
            conType.setTypeid(Integer.parseInt(typeId));
        }
        if (StringUtils.hasText(typeName)) {
            conType.setName(URLDecoder.decode(typeName));
        }

        List<EasyUITree> treeList = new ArrayList<EasyUITree>();

        if (!StringUtils.hasText(typeId)) {  // 无tree的id 值，默认加载物资类别tree
            List<BMaterialType> typeList = materialTypeService.loadAll();
            for (BMaterialType materialType : typeList) {    // 只做二级处理
                if (materialType.getParentid() == 0) { // 一级节点
                    EasyUITree tree = new EasyUITree();
                    tree.setState("open");
                    tree.setId(materialType.getTypeid() + "");
                    tree.setText(materialType.getName());
                    // 加载子节点
                    List<EasyUITree> treeList1 = new ArrayList<EasyUITree>();
                    for (BMaterialType materialType1 : typeList) {
                        if (materialType1.getParentid() == materialType.getTypeid()) {
                            EasyUITree tree1 = new EasyUITree();
                            tree1.setState("closed");
                            tree1.setId(materialType1.getTypeid() + "");
                            tree1.setText(materialType1.getName());
                            treeList1.add(tree1);
                            //加载材料物资信息
                            List<EasyUITree> treeList2 = new ArrayList<EasyUITree>();
                            List<BMaterialInfo> maInfoList = materialInfoService.queryMaInfoList(materialType1.getTypeid());
                            for (BMaterialInfo bMaterialInfo : maInfoList) {
                                EasyUITree treeInfo = new EasyUITree();
                                treeInfo.setState("open");
                                treeInfo.setAttributes(bMaterialInfo.getAccountFlag());
                                treeInfo.setFiled1("1"); // 表示材料项目
                                treeInfo.setId(bMaterialInfo.getCode());
                                treeInfo.setText(bMaterialInfo.getName());
                                treeInfo.setFiled2(bMaterialInfo.getPrice() + "");
                                treeInfo.setpId(materialType1.getTypeid() + "");
                                treeInfo.setpText(materialType1.getName());
                                treeList2.add(treeInfo);
                            }
                            tree1.setChildren(treeList2);
                        }
                    }
                    tree.setChildren(treeList1);
                    treeList.add(tree);
                }
            }

        }

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));
    }


    @RequestMapping(params = "method=bachcreat")
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {

        ResultDto<String> result = null;
        materialInfoService.bachcreatMerTest();

        return AjaxModelAndViewUtils.writeMsgReturnNull(response,
                JSONUtil.toJSonString(result));
    }

    /**
     * 查询租赁物资 tree  --无限极 新方法  自定义attributes
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(params = "method=getMaterialListNew")
    public ModelAndView getMaterialListNew(HttpServletRequest request, HttpServletResponse response) {

        // 一次加载全部
        List<EasyUITreeNew> treeDataList = new ArrayList<EasyUITreeNew>();
        // 加载类目
        List<BMaterialType> materialTypes = materialTypeService.loadAll();

        for (BMaterialType materialType : materialTypes) {
            EasyUITreeNew tree = new EasyUITreeNew();

            if(materialType.getParentid()==0){
                tree.setState("open");
            }else{
                tree.setState("closed");
            }
            tree.setId(materialType.getTypeid() + "");
            tree.setText(materialType.getName());
            tree.setPid(materialType.getParentid() + "");


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nodeType", "0");  // 类目节点
            tree.setAttributes(map);

            treeDataList.add(tree);
        }
        // 加载项目
        List<BMaterialInfo> maInfoList = materialInfoService.loadAll();
        for (BMaterialInfo bMaterialInfo : maInfoList) {
            EasyUITreeNew tree = new EasyUITreeNew();

            tree.setId(bMaterialInfo.getCode());
            tree.setPid(bMaterialInfo.getId() + "");
            tree.setText(bMaterialInfo.getName());
            tree.setChecked(false);
            tree.setPtext(bMaterialInfo.getTypeName());
            tree.setPid(bMaterialInfo.getTypeid() + "");
            tree.setState("open");
            tree.setIconCls("");

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nodeType", "1"); // 项目节点
            map.put("code", bMaterialInfo.getCode());
            map.put("price", bMaterialInfo.getPrice());
            map.put("accountFlag", bMaterialInfo.getAccountFlag());
            map.put("unit", bMaterialInfo.getAccountFlag());
            map.put("convertFlag", bMaterialInfo.getConvertFlag());
            map.put("covertRatio", bMaterialInfo.getCovertRatio());
            map.put("compensateFlag", bMaterialInfo.getCompensateFlag());
            map.put("compensateRatio", bMaterialInfo.getCompensateRatio());
            map.put("expressFlag", bMaterialInfo.getExpressFlag());
            map.put("expressRatio", bMaterialInfo.getExpressRatio());
            map.put("expressPrice", bMaterialInfo.getExpressPrice());
            map.put("transferFlag", bMaterialInfo.getTransferFlag());
            map.put("transferPrice", bMaterialInfo.getTransferPrice());
            map.put("zxPrice", bMaterialInfo.getZxPrice());

            tree.setAttributes(map);

            treeDataList.add(tree);
        }

        // 转换后就可得所需的json格式
        List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", treeDataList);

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));
    }



    /**
     * 查询  租赁物资+易耗品 tree  --无限极 新方法  自定义attributes
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(params = "method=getMaTypeListNew")
    public ModelAndView getMaTypeListNew(HttpServletRequest request, HttpServletResponse response) {

        // 一次加载全部
        List<EasyUITreeNew> treeDataList = new ArrayList<EasyUITreeNew>();
        // 加载租赁类目
        List<BMaterialType> materialTypes = materialTypeService.loadAll();

        for (BMaterialType materialType : materialTypes) {
            EasyUITreeNew tree = new EasyUITreeNew();

            if(materialType.getParentid()==0){
                tree.setState("open");
            }else{
                tree.setState("closed");
            }
            tree.setId(materialType.getTypeid() + "");
            tree.setText(materialType.getName());
            tree.setPid(materialType.getParentid() + "");


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nodeType", "0");  // 类目节点
            tree.setAttributes(map);

            treeDataList.add(tree);
        }
        // 加载租赁物资项目
        List<BMaterialInfo> maInfoList = materialInfoService.loadAll();
        for (BMaterialInfo bMaterialInfo : maInfoList) {
            EasyUITreeNew tree = new EasyUITreeNew();

            tree.setId(bMaterialInfo.getCode());
            tree.setText(bMaterialInfo.getName());
            tree.setChecked(false);
            tree.setPtext(bMaterialInfo.getTypeName());
            tree.setPid(bMaterialInfo.getTypeid() + "");
            tree.setState("open");
            tree.setIconCls("");

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nodeType", "1"); // 项目节点
            map.put("code", bMaterialInfo.getCode());
            map.put("price", bMaterialInfo.getPrice());
            map.put("accountFlag", bMaterialInfo.getAccountFlag());
            map.put("unit", bMaterialInfo.getAccountFlag());
            map.put("convertFlag", bMaterialInfo.getConvertFlag());
            map.put("covertRatio", bMaterialInfo.getCompensateRatio());
            map.put("compensateFlag", bMaterialInfo.getCompensateFlag());
            map.put("compensateRatio", bMaterialInfo.getCompensateRatio());
            map.put("expressFlag", bMaterialInfo.getExpressFlag());
            map.put("expressRatio", bMaterialInfo.getExpressRatio());
            map.put("expressPrice", bMaterialInfo.getExpressPrice());
            map.put("transferFlag", bMaterialInfo.getTransferFlag());
            map.put("transferPrice", bMaterialInfo.getTransferPrice());
            map.put("zxPrice", bMaterialInfo.getZxPrice());

            tree.setAttributes(map);

            treeDataList.add(tree);
        }



        BConsumableType consumableType = new BConsumableType();
        List<BConsumableType> bConsumableTypes = consumableService.findTypeList(consumableType);
        for (BConsumableType bConsumableType : bConsumableTypes) {

            EasyUITreeNew tree = new EasyUITreeNew();

            if(bConsumableType.getParentid()==0){
                tree.setState("open");
            }else{
                tree.setState("closed");
            }
            tree.setId(bConsumableType.getTypeid() + "");
            tree.setText(bConsumableType.getName());
            tree.setPid(bConsumableType.getParentid() + "");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nodeType", "0");  // 类目节点
            tree.setAttributes(map);

            treeDataList.add(tree);

        }
        List<BConsumableInfo> bConsumableInfos = consumableInfoService.loadAll();
        for (BConsumableInfo bConsumableInfo : bConsumableInfos) {
            EasyUITreeNew tree = new EasyUITreeNew();

            tree.setId(bConsumableInfo.getCode());
            tree.setPid(bConsumableInfo.getTypeid()+"");
            tree.setText(bConsumableInfo.getName());
            tree.setChecked(false);
            tree.setPtext(bConsumableInfo.getTypeName());
            tree.setState("open");
            tree.setIconCls("");

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nodeType", "2"); // 项目节点
            map.put("price", bConsumableInfo.getPrice()); // 项目节点
            map.put("unit", bConsumableInfo.getUnit());
            map.put("quantity", bConsumableInfo.getPrice());
            map.put("status", bConsumableInfo.getStatus());

            tree.setAttributes(map);

            treeDataList.add(tree);
        }



        // 转换后就可得所需的json格式
        List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", treeDataList);

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));
    }


}
