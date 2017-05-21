/**
 * 
 */
package com.lq.lss.controller.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.model.AdminDept;
import com.lq.lss.controller.shiro.ShiroBaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JsonUtil2;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.model.EasyUIDept;

/**
 * @author lanbo
 */
@Controller
@RequestMapping(value = "/user/adminDept.htm")
public class AdminDeptController extends ShiroBaseController<AdminDept, Integer, AdminDeptService> {

	@Resource
	private AdminDeptService deptService;

	@Value("/system/admin_dept")
	private String indexView;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {


		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.SYS_DEPT_MGR_ADD);
		modelMap.put("update", PermResourceConst.SYS_DEPT_MGR_UPDATE);
		modelMap.put("del", PermResourceConst.SYS_DEPT_MGR_DEL);

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
	@RequestMapping(params = "method=getDeptList")
	public ModelAndView getDeptList(HttpServletRequest request, HttpServletResponse response) {
		List<AdminDept> list = deptService.loadAll();
		List<EasyUIDept> easyUIDepList = new ArrayList<EasyUIDept>();
		
		if(list.size()>0){
			for (AdminDept adminDept : list) {
				if (adminDept.getParentId() == 0) {
					EasyUIDept easyUIDept = new EasyUIDept();
					easyUIDept.setId(adminDept.getDeptId());
					easyUIDept.setDeptId(adminDept.getDeptId());
					easyUIDept.setName(adminDept.getName());
					easyUIDept.setParentId(adminDept.getParentId());
					easyUIDept.setPosition(adminDept.getPosition());
					easyUIDept.setCreateTime(adminDept.getCreateTime());
					
					List<EasyUIDept> easyUIDepList2 = new ArrayList<EasyUIDept>();
					for (AdminDept adminDept2 : list) {
						EasyUIDept easyUIDept2 = new EasyUIDept();
						if (adminDept2.getParentId() == adminDept.getDeptId()) {
							easyUIDept2.setCreateTime(adminDept2.getCreateTime());
							easyUIDept2.setId(adminDept2.getDeptId());
							easyUIDept2.setDeptId(adminDept2.getDeptId());
							easyUIDept2.setName(adminDept2.getName());
							easyUIDept2.setParentId(adminDept2.getParentId());
							easyUIDept2.setPosition(adminDept2.getPosition());
							easyUIDepList2.add(easyUIDept2);
						}
						List<EasyUIDept> easyUIDepList3 = new ArrayList<EasyUIDept>();
						for (AdminDept adminDept3 : list) {
							EasyUIDept easyUIDept3 = new EasyUIDept();
							if (adminDept3.getParentId() == adminDept2.getDeptId()) {
								easyUIDept3.setCreateTime(adminDept3.getCreateTime());
								easyUIDept3.setId(adminDept3.getDeptId());
								easyUIDept3.setDeptId(adminDept3.getDeptId());
								easyUIDept3.setName(adminDept3.getName());
								easyUIDept3.setParentId(adminDept3.getParentId());
								easyUIDept3.setPosition(adminDept3.getPosition());
								easyUIDepList3.add(easyUIDept3);
							}
						}
						easyUIDept2.setChildren(easyUIDepList3);
						
					}
					easyUIDept.setChildren(easyUIDepList2);
					easyUIDepList.add(easyUIDept);
				}
			}
		}
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		if(type.equals("1")){
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIDepList));
		}else{
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JsonUtil2.toJson(easyUIDepList).replace("name", "text"));
		}
		
	}

}
