/**
 * 
 */
package com.lq.lss.controller.sys;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.EasyUIConType;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JsonUtil2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/sys/conType.htm")
public class ConsumableTypeController extends ShiroBaseController<BConsumableType, Integer, ConsumableService> {

	@Resource
	private ConsumableService consumableService;

	@Value("/base/consumableType")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_CON_TYPE_ADD);
		modelMap.put("update", PermResourceConst.SYS_CON_TYPE_UPDATE);
		modelMap.put("del", PermResourceConst.SYS_CON_TYPE_DEL);
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
	@RequestMapping(params = "method=getConTypeList")
	public ModelAndView getConTypeList(HttpServletRequest request, HttpServletResponse response) {
		List<BConsumableType> list = consumableService.loadAll();

		List<EasyUIConType> easyUIConTypeList = new ArrayList<EasyUIConType>();

		if(list.size()>0){
			for (BConsumableType type : list) {
				if (type.getParentid() == 0) {
					EasyUIConType easyUIConType = new EasyUIConType();
					easyUIConType.setId(type.getTypeid());
					easyUIConType.setTypeid(type.getTypeid());
					easyUIConType.setName(type.getName());
					easyUIConType.setParentid(type.getParentid());
					easyUIConType.setPosition(type.getPosition());
					easyUIConType.setPrice(type.getPrice().doubleValue());
					easyUIConType.setUnit(type.getUnit());

					List<EasyUIConType> easyUIConTypeList2 = new ArrayList<EasyUIConType>();
					for (BConsumableType type2 : list) {
						EasyUIConType easyUIConType2 = new EasyUIConType();
						if (type2.getParentid() == type.getTypeid()) {
							easyUIConType2.setId(type2.getTypeid());
							easyUIConType2.setTypeid(type2.getTypeid());
							easyUIConType2.setName(type2.getName());
							easyUIConType2.setParentid(type2.getParentid());
							easyUIConType2.setPosition(type2.getPosition());
							easyUIConType2.setPrice(type2.getPrice().doubleValue());
							easyUIConType2.setUnit(type2.getUnit());
							easyUIConTypeList2.add(easyUIConType2);
						}
						List<EasyUIConType> easyUIConTypeList3 = new ArrayList<EasyUIConType>();
						for (BConsumableType type3 : list) {
							EasyUIConType easyUIConType3 = new EasyUIConType();
							if (type3.getParentid() == type2.getTypeid()) {
								easyUIConType3.setId(type3.getTypeid());
								easyUIConType3.setTypeid(type3.getTypeid());
								easyUIConType3.setName(type3.getName());
								easyUIConType3.setParentid(type3.getParentid());
								easyUIConType3.setPosition(type3.getPosition());
								easyUIConType3.setPrice(type3.getPrice().doubleValue());
								easyUIConType3.setUnit(type3.getUnit());
								easyUIConTypeList3.add(easyUIConType3);
							}
						}
						easyUIConType2.setChildren(easyUIConTypeList3);

					}
					easyUIConType.setChildren(easyUIConTypeList2);
					easyUIConTypeList.add(easyUIConType);
				}
			}
		}
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		if(type.equals("1")){
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIConTypeList));
		}else{
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIConTypeList).replace("name", "text"));
		}

	}



}
