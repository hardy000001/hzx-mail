package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.CustomerInfoDao;
import com.lq.lss.core.dao.CustomerRentinfoDao;
import com.lq.lss.core.dao.MaterialInfoDao;
import com.lq.lss.core.dao.StockInfoDao;
import com.lq.lss.core.service.CustomerInfoService;
import com.lq.lss.dto.AuditDto;
import com.lq.lss.dto.CustomerInfoDto;
import com.lq.lss.dto.CustomerRentinfoDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.lss.model.BMaterialInfo;
import com.lq.lss.model.CStockInfo;
import com.lq.lss.model.CustomerInfo;
import com.lq.lss.model.CustomerRentinfo;
import com.lq.lss.utils.exception.jta.BusinessException;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Service
public class CustomerInfoServiceImpl extends EasyUIServiceBase<CustomerInfo, String, CustomerInfoDao> implements CustomerInfoService{

	@Resource
    private MaterialInfoDao materialInfoDao;
	@Resource
    private StockInfoDao stockInfoDao;
    @Resource
	private CustomerInfoDao customerInfoDao;
    @Resource
	private CustomerRentinfoDao customerRentinfoDao;

	@Override
	public String queryMaxCusCodeByDeptId(String deptId) {
		
	     return modelDao.queryMaxCusCodeByDeptId(deptId);
	}

	@Override
	public ResultDto<String> saveCustomerInfoRdTx(
			CustomerInfoDto customerInfoDto) {
			ResultDto<String> result=null;
			try 
			{
				    CustomerInfo customerInfo=new CustomerInfo();
				    customerInfo.setCuscode(customerInfoDto.getCuscode());
				    customerInfo.setCusrule(customerInfoDto.getCusrule());
				    customerInfo.setCusname(customerInfoDto.getCusname());
				    customerInfo.setCustype(customerInfoDto.getCustype());
				    customerInfo.setLinkman(customerInfoDto.getLinkman());
				    customerInfo.setCustel(customerInfoDto.getCustel());
				    customerInfo.setCusaddress(customerInfoDto.getCusaddress());
				    customerInfo.setCuslicence(customerInfoDto.getCuslicence());
				    customerInfo.setContracturl(customerInfoDto.getContracturl());
				    customerInfo.setBankinfo(customerInfoDto.getBankinfo());
				    customerInfo.setBankaccount(customerInfoDto.getBankaccount());
				    customerInfo.setAccountno(customerInfoDto.getAccountno());
				    customerInfo.setDeptid(customerInfoDto.getDeptid());
					customerInfo.setCreateoper(customerInfoDto.getCreateoper());
					//默认信息
				    customerInfo.setStatus(1); //申请
					customerInfo.setCreatetime(new Date());
					result=new ResultDto<String>(); 
					modelDao.create(customerInfo);
			        result.setSuccess(true);
			        result.setErrorMsg("提交数据成功，等到审核中。");
			        log.debug("提交数据成功，等到审核中。");

			        List<CustomerRentinfoDto> customerRentinfoDtos = customerInfoDto
					                                .getCustomerRentinfoDtos();
			        
			        if(customerRentinfoDtos!=null && customerRentinfoDtos.size()>0)
			        {
			        	List<CustomerRentinfo> customerRentinfos=new ArrayList<CustomerRentinfo>();
			        	
			        	for (CustomerRentinfoDto customerRentinfoDto : customerRentinfoDtos) 
			        	{
			        		CustomerRentinfo customerRentinfo=new CustomerRentinfo();
			        		customerRentinfo.setCuscode(customerInfoDto.getCuscode());
			        		customerRentinfo.setMaterialcode(customerRentinfoDto.getMaterialcode());
			        		Double quantiy=Double.valueOf(customerRentinfoDto.getQuantity());
			        		customerRentinfo.setQuantity(new BigDecimal(quantiy));
			        		customerRentinfo.setTonQantity(customerRentinfoDto.getTonQantity());
							Double rentalDay=Double.valueOf(customerRentinfoDto.getRentalDay());
							customerRentinfo.setRentalDay(new BigDecimal(rentalDay));
							customerRentinfo.setUnit(customerRentinfoDto.getUnit());
							customerRentinfos.add(customerRentinfo);
						}
			        	customerRentinfoDao.batchCreate(customerRentinfos);
			        	log.debug("=========调拨客户租费数据保存成功=======");
			        }
			        

			} catch (Exception e) {
					e.printStackTrace();
					log.error("提交数据异常。"+e.getMessage());
					throw new BusinessException(e.getMessage());
			}
			return result;
	}

	@Override
	public ResultDto<String> deleteInfoById(String id, String deptId) {

		       ResultDto<String> result=null;
		    
				try {  
					          
					            CustomerInfo customerInfo=modelDao.queryCustomerInfoById(id, deptId);
								if( customerInfo !=null)
								{
										//审核后不可删除
								        String status=customerInfo.getStatus()+"";
									    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
									    {
									    	return new ResultDto<String>(false,"已审核数据不可以删除");
									    }
									    customerRentinfoDao.deleteById(id);
									    log.debug("---------租价信息删除成功--------");
									    
									    modelDao.deleteByIdAndDeptid(id, deptId);
									    
									    log.debug("---------客户删除成功--------");
									    
									    result=new ResultDto<String>(true,"删除数据成功");
								}else
								{ 
									    result=new ResultDto<String>(false,"该数据不在本中心不允许删除");
								}
					
					
				} catch (NumberFormatException e) {
								log.error("客户数据转化异常"+e.getMessage());
								e.printStackTrace();
								throw new BusinessException(e.getMessage());
				} catch (Exception e) {
								log.error("客户数据删除异常"+e.getMessage());
								e.printStackTrace();
								throw new BusinessException(e.getMessage());
	      }
			
		 return result;
		
	}

	@Override
	public ResultDto<String> updateCustomerInfoRdTx(CustomerInfoDto customerInfoDto) 
	       throws BusinessException{
		ResultDto<String> result=null;
		try {  
			     
			        String cuscode=customerInfoDto.getCuscode();
			        String deptId=customerInfoDto.getDeptid();
					CustomerInfo customerInfo2=modelDao.queryCustomerInfoById(cuscode, deptId);
					if(customerInfo2 !=null)
					{
				            //审核后不再允许审核
					        String status2=customerInfo2.getStatus()+"";
						    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status2))
						    {
						    	return new ResultDto<String>(false,"已审核数据不可以再修改");
						    }
						    CustomerInfo customerInfo=new CustomerInfo();
						    customerInfo.setCuscode(cuscode);
						    customerInfo.setCusrule(customerInfoDto.getCusrule());
						    customerInfo.setCusname(customerInfoDto.getCusname());
						    customerInfo.setCustype(customerInfoDto.getCustype());
						    customerInfo.setLinkman(customerInfoDto.getLinkman());
						    customerInfo.setCustel(customerInfoDto.getCustel());
						    customerInfo.setCusaddress(customerInfoDto.getCusaddress());
						    customerInfo.setCuslicence(customerInfoDto.getCuslicence());
						    customerInfo.setContracturl(customerInfoDto.getContracturl());
						    customerInfo.setBankinfo(customerInfoDto.getBankinfo());
						    customerInfo.setBankaccount(customerInfoDto.getBankaccount());
						    customerInfo.setAccountno(customerInfoDto.getAccountno());
						    customerInfo.setDeptid(customerInfoDto.getDeptid());
							customerInfo.setModifytime(new Date());
							customerInfo.setCreateoper(customerInfoDto.getCreateoper());
							modelDao.update(customerInfo);
							result=new ResultDto<String>(true,"修改数据成功");
							log.debug("=====================客户信息更改成功===================");
							
							
							customerRentinfoDao.deleteById(cuscode);
							log.debug("=====================删除租费信息===================");
							
							List<CustomerRentinfoDto> customerRentinfoDtos = customerInfoDto
	                                .getCustomerRentinfoDtos();
							if(customerRentinfoDtos!=null && customerRentinfoDtos.size()>0)
					        {
					        	List<CustomerRentinfo> customerRentinfos=new ArrayList<CustomerRentinfo>();
					        	
					        	for (CustomerRentinfoDto customerRentinfoDto : customerRentinfoDtos) 
					        	{
					        		CustomerRentinfo customerRentinfo=new CustomerRentinfo();
					        		customerRentinfo.setCuscode(customerInfoDto.getCuscode());
					        		customerRentinfo.setMaterialcode(customerRentinfoDto.getMaterialcode());
					        		Double quantiy=Double.valueOf(customerRentinfoDto.getQuantity());
					        		customerRentinfo.setQuantity(new BigDecimal(quantiy));
					        		customerRentinfo.setTonQantity(customerRentinfoDto.getTonQantity());
									Double rentalDay=Double.valueOf(customerRentinfoDto.getRentalDay());
									customerRentinfo.setRentalDay(new BigDecimal(rentalDay));
									customerRentinfo.setUnit(customerRentinfoDto.getUnit());
									customerRentinfos.add(customerRentinfo);
								}
					        	customerRentinfoDao.batchCreate(customerRentinfos);
					        	log.debug("=========调拨客户租费数据保存成功=======");
					        }
					}else
					{ 
						    result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
					}
		} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error("客户数据转换异常。"+e.getMessage());
				throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				e.printStackTrace();
				log.error("客户数据异常。"+e.getMessage());
				throw new BusinessException(e.getMessage());
	    }
		
	    return result;
	}
	
	@Override
	public ResultDto<String> auditInfoRdTx(AuditDto auditDto) {
		ResultDto<String> result=null;
		try {
			    String cuscode=auditDto.getId();
			    String deptid=auditDto.getDeptId();
			    CustomerInfo customerInfo2=modelDao.queryCustomerInfoById(cuscode, deptid);
			    if(customerInfo2!=null)
			    {
			    	    String status=auditDto.getStatus();
				    	//审核后不再允许审核
				        String status2=customerInfo2.getStatus()+"";
					    if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status2))
					    {
					    	return new ResultDto<String>(false,"已审核数据不可以再审核");
					    }
					    CustomerInfo customerInfo=new CustomerInfo();
					    customerInfo.setCuscode(cuscode);
					    customerInfo.setStatus(Integer.parseInt(status));
					    customerInfo.setModifytime(new Date());
					    customerInfo.setDeptid(deptid);
						modelDao.update(customerInfo);
						result=new ResultDto<String>(true,"客户审核数据成功");
						log.debug("=====================审核状态更改成功===================");
						
						String code=String.valueOf(CheckStatus.OK.getCode());
						String obj=(String) auditDto.getObj();
						//调拨客户初始化库存星星
						if(code.equals(status) && "1".equals(obj)){
							 List<BMaterialInfo> bMaterialInfos=materialInfoDao.loadAll();

							 List<CStockInfo> cStockInfos=new ArrayList<CStockInfo>(); 
							 
							 for (BMaterialInfo bMaterialInfo : bMaterialInfos) {
								 CStockInfo cStockInfo=new CStockInfo();
								 cStockInfo.setDeptid(Integer.parseInt(deptid));
								 cStockInfo.setMchcode(cuscode);
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
						
			    }else
			    {
			    	    result=new ResultDto<String>(false,"该数据不在本中心不允许审核");
			    }
				
		} catch (NumberFormatException e) {
				e.printStackTrace();
				log.error("客户审核数据转换异常。"+e.getMessage());
				throw new BusinessException(e.getMessage());
		} catch (Exception e) {
				e.printStackTrace();
				log.error("客户审核数据异常。"+e.getMessage());
				throw new BusinessException(e.getMessage());
	    }
	    return result;
	}

	@Override
	public CustomerInfo queryCustomerInfoById(String id) {
		
		return modelDao.findById(id);
	}

	@Override
	public CustomerInfo findCustomerInfoByIdAndDeptId(String id, String deptId) {
		
		return modelDao.queryCustomerInfoById(id, deptId);
	}

	@Override
	public List<CustomerInfo> queryCustomerInfoByDeptId(String deptId) {
		
		return modelDao.findCustomerInfoList(deptId);
	}

	@Override
	public List<CustomerInfo> queryCustomerInfoByDeptIdAndCustype(String deptId) {
		// TODO Auto-generated method stub
		return customerInfoDao.findCustomerInfoByDeptIdAndCustype(deptId);
	}

	
}