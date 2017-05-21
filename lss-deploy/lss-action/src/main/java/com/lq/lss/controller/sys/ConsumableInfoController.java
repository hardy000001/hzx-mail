/**
 *
 */
package com.lq.lss.controller.sys;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.model.EasyUITreeNew;
import com.lq.lss.utils.EasyuiUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.core.service.ConsumableInfoService;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.model.BConsumableInfo;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.EasyUITree;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/sys/consumInfo.htm")
public class ConsumableInfoController extends ShiroBaseController<BConsumableInfo, Integer, ConsumableInfoService> {

    @Resource
    private ConsumableInfoService consumableInfoService;
    @Resource
    private ConsumableService consumableService;

    @Value("/base/consumableInfo")
    private String indexView;

    @RequestMapping
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("add", PermResourceConst.SYS_CON_INFO_ADD);
        modelMap.put("update", PermResourceConst.SYS_CON_INFO_UPDATE);
        modelMap.put("del", PermResourceConst.SYS_CON_INFO_DEL);
        if (useI18n) {
            return new I18nModelAndView(request, indexView, modelMap);
        }
        return new ModelAndView(indexView, modelMap);
    }

    /**
     * 查询类别名称tree
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(params = "method=getConTypeList")
    public ModelAndView getConTypeList(HttpServletRequest request, HttpServletResponse response) {

        String typeId = ServletRequestUtils.getStringParameter(request, "id", "");
        String typeName = ServletRequestUtils.getStringParameter(request, "typeName", "");

        BConsumableType conType = new BConsumableType();
        if (StringUtils.hasText(typeId)) {
            conType.setTypeid(Integer.parseInt(typeId));
        }
        if (StringUtils.hasText(typeName)) {
            conType.setName(URLDecoder.decode(typeName));
        }


        List<BConsumableType> typeList = consumableService.findTypeList(conType);

        List<EasyUITreeNew> treeDataList = new ArrayList<EasyUITreeNew>();
        for (BConsumableType bConsumableType : typeList) {
            EasyUITreeNew easyUITreeNew = new EasyUITreeNew();
            easyUITreeNew.setId(bConsumableType.getTypeid() + "");
            easyUITreeNew.setText(bConsumableType.getName());
            easyUITreeNew.setState("open");
            easyUITreeNew.setPid(bConsumableType.getParentid() + "");
            treeDataList.add(easyUITreeNew);
        }

        List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", treeDataList);

        return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(treeList));
    }

}
