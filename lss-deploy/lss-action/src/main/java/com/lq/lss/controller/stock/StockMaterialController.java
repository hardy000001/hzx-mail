package com.lq.lss.controller.stock;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lq.easyui.controller.easyui.EasyUIController;
import com.lq.easyui.view.AjaxModelAndViewUtils;
import com.lq.lss.core.service.ConsumableInfoService;
import com.lq.lss.core.service.ConsumableService;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.core.service.MaterialTypeService;
import com.lq.lss.model.BConsumableInfo;
import com.lq.lss.model.BConsumableType;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.BMaterialType;
import com.lq.lss.model.EasyUITree;
import com.lq.util.JSONUtil;


@Controller
@RequestMapping("/user/stock/StockMaterial.htm")
public class StockMaterialController extends EasyUIController<BMaterialType, Integer, MaterialTypeService>{

	@Resource
	MaterialTypeService materialTypeService;
	@Resource
	private MaterialInfoService materialInfoService;
	@Resource
	private ConsumableService consumableService;
	@Resource
	private ConsumableInfoService consumableInfoService;
	
	/**
	 * 查询类别名称tree
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
									treeInfo.setId(bMaterialInfo.getCode());
									treeInfo.setText(bMaterialInfo.getName());
									treeInfo.setFiled2(bMaterialInfo.getAccountFlag()); 
									treeInfo.setpId(materialType1.getTypeid()+"");
									treeInfo.setpText(materialType1.getName());
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
		}	
			
		
		return AjaxModelAndViewUtils.writeMsgReturnNull(response,JSONUtil.toJSonString(treeList));
	}

}
