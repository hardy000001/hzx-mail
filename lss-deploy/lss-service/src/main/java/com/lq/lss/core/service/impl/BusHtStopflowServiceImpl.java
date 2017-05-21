package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusHtStopflow;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.lss.core.dao.BusHtStopflowDao;
import com.lq.lss.core.service.BusHtStopflowService;
import com.lq.lss.dto.BusHtStopflowDto;
import com.lq.lss.enums.CheckStatus;
import com.lq.util.TimeUtil;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-10-26 11:02:13
 */
@Service
public class BusHtStopflowServiceImpl extends EasyUIServiceBase<CBusHtStopflow, String, BusHtStopflowDao> implements BusHtStopflowService{

   
	@Override
	public ResultDto<String> saveHtStopflowRdTx(
			BusHtStopflowDto busHtStopflowDto ,SessionUser user) {
		ResultDto<String> result=null;
		             
		try 
		{
			
			            CBusHtStopflow busHtStopflow=new CBusHtStopflow();
			            String htcode=busHtStopflowDto.getHtcode();
			            busHtStopflow.setHtcode(htcode);
			            
			           
			            
			            
			            	Date beginDate=TimeUtil.get().parseDate(busHtStopflowDto.getBeginDate());
							busHtStopflow.setBeginDate(beginDate);
							Date endDate=TimeUtil.get().parseDate(busHtStopflowDto.getEndDate());
							busHtStopflow.setEndDate(endDate);
							Integer totalDays=Integer.valueOf(busHtStopflowDto.getTotalDays());
							busHtStopflow.setTotalDays(totalDays);
							busHtStopflow.setStatus(1+"");
							busHtStopflow.setCreatetime(new Date());
							busHtStopflow.setCreateoper(user.getUserId()+"");
							modelDao.create(busHtStopflow);
							log.debug("---------合同报停数据保存成功--------");
						    result=new ResultDto<String>(true,"数据保存成功");
			            
			
		} catch (NumberFormatException e){
						log.error("数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("数据保存失败"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		}

	    return result;
	}

	@Override
	public ResultDto<String> updateHtStopflowRdTx(
			BusHtStopflowDto busHtStopflowDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String id=busHtStopflowDto.getId();
			CBusHtStopflow  busHtStopflow= modelDao.findById(id);
			if(busHtStopflow!=null){
				String status=busHtStopflow.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
				Date beginDate=TimeUtil.get().parseDate(busHtStopflowDto.getBeginDate());
				busHtStopflow.setBeginDate(beginDate);
				Date endDate=TimeUtil.get().parseDate(busHtStopflowDto.getEndDate());
				busHtStopflow.setEndDate(endDate);
				Integer totalDays=Integer.valueOf(busHtStopflowDto.getTotalDays());
				busHtStopflow.setTotalDays(totalDays);
				modelDao.update(busHtStopflow);
				log.debug("---------报停修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
				
				
			}else{
				result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
		} catch (NumberFormatException e){
			log.error("中心调入数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("中心调入数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}

	@Override
	public ResultDto<String> deleteHtStopflowById(String id, String deptId) {
		ResultDto<String> result=null;
		try {
			          
			            CBusHtStopflow busHtStopflow2=modelDao.findById(id);
						
						if( busHtStopflow2 !=null)
						{
							String status=busHtStopflow2.getStatus();
							if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
						    {
						    	return new ResultDto<String>(false,"已审核数据不能修改");
						    }	
							    modelDao.deleteById(id);
							    log.debug("=========合同报停数据删除成功========");
							   
							    result=new ResultDto<String>(true,"删除数据成功");
						}else
						{ 
							    result=new ResultDto<String>(false,"数据不存在");
						}
			
			
		} catch (NumberFormatException e) {
						log.error("合同报停删除数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("合同报停删除异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
     }
		
	 return result;
	}

	@Override
	public ResultDto<String> auditHtStopflowRdTx(BusHtStopflowDto busHtStopflowDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String id=busHtStopflowDto.getId();
			CBusHtStopflow  busHtStopflow= modelDao.findById(id);
			if(busHtStopflow!=null){
				String status=busHtStopflow.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"不能重复审核");
			    }
				busHtStopflow.setStatus(0+"");
				busHtStopflow.setAuditingoper(user.getUserId()+"");
				busHtStopflow.setAuditingtime(new Date());
				modelDao.update(busHtStopflow);
				log.debug("---------报停修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
			}else{
				result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
		} catch (NumberFormatException e){
			log.error("中心调入数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("中心调入数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
}