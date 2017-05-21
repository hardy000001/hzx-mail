package com.lq.lss.core.service.impl;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.MaterialInfoDao;
import com.lq.lss.core.dao.MchBaseinfoDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.service.MchBaseinfoService;
import com.lq.lss.dto.MchBaseinfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.MchBaseinfo;
import com.lq.lss.utils.exception.jta.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 租赁商户信息接口实现类
 * @author  作者: hzx
 * @date 创建时间: 2016-9-6下午1:54:04
 */
@Service
public class MchBaseinfoServiceImpl extends EasyUIServiceBase<MchBaseinfo, Integer, MchBaseinfoDao> implements MchBaseinfoService{

	@Resource
    private MaterialInfoDao materialInfoDao;
	@Resource
    private StockInfoDao stockInfoDao;
	@Resource
    private MchBaseinfoDao mchBaseinfoDao;

	@Override
	public String queryMaxMchCodeByDeptId(String deptId) {
		return modelDao.queryMaxMchCodeByDeptId(deptId);
	}

	@Override
	public ResultDto<String> saveMchBaseinfoRdTx(MchBaseinfo mchBaseinfo) {
		
		ResultDto<String> result=null;
		try 
		{
				//默认信息
				mchBaseinfo.setStatus(1);
				mchBaseinfo.setCreatetime(new Date());
				result=new ResultDto<String>(); 
				modelDao.create(mchBaseinfo);
		        result.setSuccess(true);
		        result.setErrorMsg("提交数据成功，等到审核中。");
		        log.debug("提交数据成功，等到审核中。");
		} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
				result.setErrorMsg("提交数据异常。");
				log.error("提交数据异常。"+e.getMessage());
		}
		return result;
	}

	@Override
	public ResultDto<String> updateMchBaseinfoRdTx(MchBaseinfoDto mchBaseinfoDto) 
	            throws BusinessException{
				ResultDto<String> result=null;
				try {
					    String mchcode=mchBaseinfoDto.getMchcode()+"";
					    String deptid=mchBaseinfoDto.getDeptid();
					    MchBaseinfo mchBaseinfo2=modelDao.queryMchBaseinfoById(mchcode, deptid);
					    if(mchBaseinfo2!=null)
					    {
					    	    String status=mchBaseinfoDto.getStatus();
						    	//审核后不再允许审核
						        String status2=mchBaseinfo2.getStatus()+"";
							    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status2))
							    {
							    	return new ResultDto<String>(false,"已审核数据不可以再审核");
							    }
						    	MchBaseinfo mchBaseinfo=new MchBaseinfo();
								mchBaseinfo.setId(mchBaseinfoDto.getId());
								mchBaseinfo.setMchcode(mchBaseinfoDto.getMchcode());
								mchBaseinfo.setStatus(Integer.parseInt(status));
								mchBaseinfo.setModifytime(new Date());
								mchBaseinfo.setCreateoper(mchBaseinfoDto.getCreateoper());
								mchBaseinfo.setDeptid(Integer.parseInt(mchBaseinfoDto.getDeptid()));
								modelDao.update(mchBaseinfo);
								result=new ResultDto<String>(true,"商户审核数据成功");
								log.debug("=====================审核状态更改成功===================");
								if(String.valueOf(CheckStatus.OK.getCode()).equals(status)){
									 List<BMaterialInfo> bMaterialInfos=materialInfoDao.loadAll();
		
									 List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>(); 
									 
									 for (BMaterialInfo bMaterialInfo : bMaterialInfos) {
										 CStockInfo cStockInfo=new CStockInfo();
										 cStockInfo.setDeptid(mchBaseinfo2.getDeptid());
										 cStockInfo.setMchcode(mchBaseinfo2.getMchcode()+"");
										 cStockInfo.setMaterialcode(bMaterialInfo.getCode());
										 cStockInfo.setUnit(bMaterialInfo.getAccountFlag());
										 cStockInfo.setTotalS(new BigDecimal(0));
										 cStockInfo.setTotalM(new BigDecimal(0));
										 cStockInfo.setTotalT(new BigDecimal(0));
										 cStockInfo.setModifytime(new Date());
										 cStockInfos.add(cStockInfo);
									 }
									 stockInfoDao.batchCreate(cStockInfos);
									 log.debug("==================商户库存信息添加成功=================");
								}
								//库存基本信息
					    }else
					    {
					    	    result=new ResultDto<String>(false,"该数据不在本中心不允许审核");
					    }
						
				} catch (NumberFormatException e) {
						e.printStackTrace();
						log.error("商户审核数据转换异常。"+e.getMessage());
						throw new BusinessException(e.getMessage());
				} catch (Exception e) {
						e.printStackTrace();
						log.error("商户审核数据异常。"+e.getMessage());
						throw new BusinessException(e.getMessage());
			    }
				
			return result;
	}

	@Override
	public MchBaseinfo queryMchBaseinfoById(String id) {
		
		return modelDao.findById(Integer.parseInt(id));
	}
	@Override
	public MchBaseinfo findMchInfoByIdAndDeptId(String id, String deptId) {
		
		return modelDao.queryMchBaseinfoById(id, deptId);
	}

	@Override
	public ResultDto<String> deleteInfoById(String id, String deptId) {
		 ResultDto<String> result=null;
		    
				try {  
					          
					            MchBaseinfo mchBaseinfo2=modelDao.queryMchBaseinfoById(id, deptId);
								if( mchBaseinfo2 !=null)
								{
										//审核后不可删除
								        String status=mchBaseinfo2.getStatus()+"";
									    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
									    {
									    	return new ResultDto<String>(false,"已审核数据不可以删除");
									    }
									    modelDao.deleteByIdAndDeptid(id, deptId);
									    log.debug("---------商户删除成功--------");
									    
									    result=new ResultDto<String>(true,"删除数据成功");
								}else
								{ 
									    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
								}
					
					
				} catch (NumberFormatException e) {
								log.error("商户数据转化异常"+e.getMessage());
								e.printStackTrace();
								throw new BusinessException(e.getMessage());
				} catch (Exception e) {
								log.error("商户数据删除异常"+e.getMessage());
								e.printStackTrace();
								throw new BusinessException(e.getMessage());
	      }
			
		 return result;
	}

	@Override
	public List<MchBaseinfo> queryMchBaseinfoByDeptId(String deptId) {
		
		return mchBaseinfoDao.findMaInfoListByDeptidList(deptId);
	}

	@Override
	public MchBaseinfo queryMchBaseinfo(MchBaseinfo baseinfo) {
		return modelDao.selectMchBaseinfo(baseinfo);
	}

	@Override
	public List<MchBaseinfo> querMchInfAndCustomerinfoByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return mchBaseinfoDao.findMchInfAndCustomerinfoList(deptId);
	}
}