/**
 * 
 */
package com.lq.lss.controller.stock;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.dto.ResultDto;
import com.lq.lss.constant.PermResourceConst;
import com.lq.lss.controller.shiro.ShiroBaseController;
import com.lq.lss.controller.util.AjaxModelAndViewUtils;
import com.lq.lss.controller.util.LoginSessionUtils;
import com.lq.lss.core.service.AdminAuditLogService;
import com.lq.lss.core.service.AdminDeptService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.core.service.StockTemporaryService;
import com.lq.lss.dto.CStockTemporaryDetailDto;
import com.lq.lss.dto.CStockTemporaryDto;
import com.lq.lss.enums.TradeType;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.CStockTemporary;
import com.lq.lss.model.EasyUITree;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.model.SessionUser;
import com.lq.springmvc.i18n.I18nModelAndView;
import com.lq.util.JSONUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "/user/stock/stockTemporaryAdd.htm")
public class StockTemporaryAddController extends ShiroBaseController<CStockTemporary, String, StockTemporaryService> {

	@Resource
	private MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private MchBaseinfoService mchInfoService;
	@Resource
	private AdminDeptService deptService;
	@Resource
	private StockTemporaryService sttservice;
	@Resource
	private AdminAuditLogService adminAuditLogService;
	
	@Value("/stock/stockTemporaryAdd")
	private String indexView;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUser user = LoginSessionUtils.getUserInSession(request);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("add", PermResourceConst.CENTER_TEMPORARY_ADD);
        modelMap.put("update", PermResourceConst.CENTER_TEMPORARY_UPDATE);
        modelMap.put("del", PermResourceConst.CENTER_TEMPORARY_DEL);
        modelMap.put("check", PermResourceConst.CENTER_TEMPORARY_CHECK);
		modelMap.put("user", user);
		if (useI18n) {
			return new I18nModelAndView(request, indexView, modelMap);
		}
		return new ModelAndView(indexView, modelMap);
	}

	
	@RequestMapping(params = "method=getMaInfoList")
	public ModelAndView getMaInfoList(HttpServletRequest request, HttpServletResponse response) {
	
		List<BMaterialInfo> tList = materialInfoService.loadAll();

		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(params = "method=getMaTypeList")
	public ModelAndView getConTypeList(HttpServletRequest request,HttpServletResponse response) {

		String typeId = ServletRequestUtils.getStringParameter(request, "id","");
		String typeName = ServletRequestUtils.getStringParameter(request,"typeName", "");

		BConsumableType conType = new BConsumableType();
		if (StringUtils.hasText(typeId)) {
			conType.setTypeid(Integer.parseInt(typeId));
		}
		if (StringUtils.hasText(typeName)) {
			conType.setName(URLDecoder.decode(typeName));
		}

		List<EasyUITree> treeList = new ArrayList<EasyUITree>();

		if(!StringUtils.hasText(typeId)){  // 无tree的id 值，默认加载物资类别tree
			List<BMaterialType> typeList = materialTypeService.loadAll();
			for (BMaterialType materialType : typeList) { 	// 只做二级处理
				if (materialType.getParentid() == 0) { // 一级节点
					EasyUITree tree = new EasyUITree();
					tree.setState("open");
					tree.setId(materialType.getTypeid() + "");
					tree.setText(materialType.getName());
					// 加载子节点
					List<EasyUITree> treeList1 = new ArrayList<EasyUITree>();
					for (BMaterialType materialType1 : typeList) {
						if ( materialType1.getParentid()==materialType.getTypeid()) {
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
									treeInfo.setFiled2(bMaterialInfo.getAccountFlag());
									treeInfo.setId(bMaterialInfo.getCode());
									treeInfo.setText(bMaterialInfo.getName());
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
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(treeList));
	}
	
		//加载公司
		@RequestMapping(params = "method=getMchInfoList")
		public ModelAndView getMchInfoList(HttpServletRequest request, HttpServletResponse response) {
		
			List<MchBaseinfo> tList = mchInfoService.loadAll();

			return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(tList));
		}

		
		@RequestMapping(params = "method=saveTemporary")
		@RequiresPermissions(PermResourceConst.CENTER_TEMPORARY_ADD)
		public ModelAndView saveTemporary(HttpServletRequest request, HttpServletResponse response,CStockTemporaryDto cStockTemporaryDto) {
			SessionUser user = LoginSessionUtils.getUserInSession(request); 
			ResultDto<String> resultDto = null;
			try {
				 cStockTemporaryDto.setDeptid(Integer.parseInt(user.getDeptId()));
				 String listStr=request.getParameter("rows");
				 String remSerialNo=request.getParameter("remSerialNo");
				 JSONArray json=JSONArray.fromObject(listStr);
				 List<CStockTemporaryDetailDto> dataList =(List<CStockTemporaryDetailDto>)JSONArray.toCollection(json, CStockTemporaryDetailDto.class);
				 cStockTemporaryDto.setcStockTemporaryDetailDtos(dataList);
				 if(cStockTemporaryDto!=null){
					 resultDto= sttservice.saveCStockTemporaryRdTx(cStockTemporaryDto, user);
					 adminAuditLogService.log(user.getUserId(), TradeType.TEMPORARY_IN.getType(),"新增暂存单",cStockTemporaryDto,0L);
					 
				 }
		
				} catch (Exception e) {
					 logger.error("提交暂存请数据出现异常", e);
					 resultDto=new ResultDto<String>(false,"数据提交失败");
			}
			
			return AjaxModelAndViewUtils.writeMsgReturnNull(response, JSONUtil.toJSonString(resultDto));
		}
		
	
}
