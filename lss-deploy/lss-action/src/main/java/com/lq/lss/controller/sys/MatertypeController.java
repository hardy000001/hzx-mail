package com.lq.lss.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.EasyUIBMaterialType;
import com.lq.lss.model.EasyUITreeNew;
import com.lq.lss.utils.EasyuiUtil;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JsonUtil2;


@Controller
@RequestMapping("/user/sys/matertype.htm")
public class MatertypeController extends ShiroBaseController<BMaterialType, Integer, MaterialTypeService> {

	@Resource
	MaterialTypeService materialTypeService;
	@Value("/base/materialType")
	private String indexView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_MT_TYPE_ADD);
		modelMap.put("update", PermResourceConst.SYS_MT_TYPE_UPDATE);
		modelMap.put("del", PermResourceConst.SYS_MT_TYPE_DEL);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}
	/**
	 * 查询所有
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=getBmaterialtypeList")
	public ModelAndView getBmaterialtypeList(HttpServletRequest request, HttpServletResponse response) {
		List<BMaterialType> list = materialTypeService.loadAll();
		List<EasyUIBMaterialType> easyUIBMaterialTypes = new ArrayList<EasyUIBMaterialType>();
		
		if(list.size()>0){
			for (BMaterialType bmMaterialType : list) {
				if (bmMaterialType.getParentid() == 0) {
					EasyUIBMaterialType easyUIBMaterialType = new EasyUIBMaterialType();
					easyUIBMaterialType.setId(bmMaterialType.getTypeid());
					easyUIBMaterialType.setTypeid(bmMaterialType.getTypeid());
					easyUIBMaterialType.setName(bmMaterialType.getName());
					easyUIBMaterialType.setParentid(bmMaterialType.getParentid());
					easyUIBMaterialType.setPosition(bmMaterialType.getPosition());
					easyUIBMaterialType.setAccountFlag(bmMaterialType.getAccountFlag());
					easyUIBMaterialType.setConvertFlag(bmMaterialType.getConvertFlag());
					easyUIBMaterialType.setStatus(bmMaterialType.getStatus());
					easyUIBMaterialType.setCreatetime(bmMaterialType.getCreatetime());
					
				List<EasyUIBMaterialType> easyUIBMaterialTypes2 = new ArrayList<EasyUIBMaterialType>();
					for (BMaterialType bmMaterialType2 : list) {
						EasyUIBMaterialType easyUIBMaterialType2 = new EasyUIBMaterialType();
						if (bmMaterialType2.getParentid() == bmMaterialType.getTypeid()) {
							easyUIBMaterialType2.setId(bmMaterialType2.getTypeid());
							easyUIBMaterialType2.setTypeid(bmMaterialType2.getTypeid());
							easyUIBMaterialType2.setName(bmMaterialType2.getName());
							easyUIBMaterialType2.setParentid(bmMaterialType2.getParentid());
							easyUIBMaterialType2.setPosition(bmMaterialType2.getPosition());
							easyUIBMaterialType2.setAccountFlag(bmMaterialType2.getAccountFlag());
							easyUIBMaterialType2.setConvertFlag(bmMaterialType2.getConvertFlag());
							easyUIBMaterialType2.setStatus(bmMaterialType2.getStatus());
							easyUIBMaterialType2.setCreatetime(bmMaterialType2.getCreatetime());
							easyUIBMaterialTypes2.add(easyUIBMaterialType2);
						}
				List<EasyUIBMaterialType> easyUIBMaterialTypes3 = new ArrayList<EasyUIBMaterialType>();
						for (BMaterialType bmMaterialType3 : list) {
							EasyUIBMaterialType easyUIBMaterialType3 = new EasyUIBMaterialType();
							if (bmMaterialType3.getParentid() == bmMaterialType2.getTypeid()) {
								easyUIBMaterialType3.setId(bmMaterialType3.getTypeid());
								easyUIBMaterialType3.setTypeid(bmMaterialType3.getTypeid());
								easyUIBMaterialType3.setName(bmMaterialType3.getName());
								easyUIBMaterialType3.setParentid(bmMaterialType3.getParentid());
								easyUIBMaterialType3.setPosition(bmMaterialType3.getPosition());
								easyUIBMaterialType3.setAccountFlag(bmMaterialType3.getAccountFlag());
								easyUIBMaterialType3.setConvertFlag(bmMaterialType3.getConvertFlag());
								easyUIBMaterialType3.setStatus(bmMaterialType3.getStatus());
								easyUIBMaterialType3.setCreatetime(bmMaterialType3.getCreatetime());
								easyUIBMaterialTypes3.add(easyUIBMaterialType3);
							}
						}
						easyUIBMaterialType2.setChildren(easyUIBMaterialTypes3);
						
					}
					easyUIBMaterialType.setChildren(easyUIBMaterialTypes2);
					easyUIBMaterialTypes.add(easyUIBMaterialType);
				}
			}
		}
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		if(type.equals("1")){
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIBMaterialTypes));
		}else{
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIBMaterialTypes).replace("name", "text"));
		}
		
	}

	
	
	@RequestMapping(params = "method=getBmaterialtypeListNew")
	public ModelAndView getBmaterialtypeListNew(HttpServletRequest request, HttpServletResponse response) {
		
		 // 一次加载全部
        List<EasyUITreeNew> treeDataList = new ArrayList<EasyUITreeNew>();
		List<BMaterialType> list = materialTypeService.loadAll();
		 for (BMaterialType materialType : list) {
	            EasyUITreeNew tree = new EasyUITreeNew();

	            if(materialType.getParentid()==0){
	                tree.setState("open");
	            }else{
	                tree.setState("closed");
	            }
	            tree.setId(materialType.getTypeid() + "");
	            tree.setName(materialType.getName());
	            tree.setText(materialType.getName());
	            tree.setPosition(materialType.getPosition());
	            tree.setAccountFlag(materialType.getAccountFlag());
	            tree.setConvertFlag(materialType.getConvertFlag());
	            tree.setStatus(materialType.getStatus());
	            tree.setCreatetime(materialType.getCreatetime());
	            tree.setParentid(materialType.getParentid());
	            tree.setTypeid(materialType.getTypeid());
	            tree.setPid(materialType.getParentid() + "");
	           

	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("nodeType", "0");  // 类目节点
	            tree.setAttributes(map);

	            treeDataList.add(tree);
	        }
		
		 List<EasyUITreeNew> treeList = EasyuiUtil.getfatherNode("0", treeDataList);
		
		
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		if(type.equals("1")){
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(treeList));
		}else{
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(treeList).replace("name", "text"));
		}
		
	}
}
