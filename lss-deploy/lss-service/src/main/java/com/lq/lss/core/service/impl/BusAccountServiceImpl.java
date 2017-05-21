package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lq.easyui.dto.ResultDto;
import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.model.CBusAccount;
import com.lq.lss.model.CBusHtStopflow;
import com.lq.lss.model.SessionUser;
import com.lq.lss.utils.exception.jta.BusinessException;
import com.lq.util.TimeUtil;
import com.lq.lss.core.dao.BusAccountDao;
import com.lq.lss.core.service.BusAccountService;
import com.lq.lss.dto.CBusAccountDto;
import com.lq.lss.enums.CheckStatus;

/**
 *
 * @author  作者: hzx
 * @date 创建时间: 2016-11-18 09:56:42
 */
@Service
public class BusAccountServiceImpl extends EasyUIServiceBase<CBusAccount, String, BusAccountDao> implements BusAccountService{

    @Resource
	private BusAccountDao BusAccountDao;

	@Override
	public ResultDto<String> saveBusAccountRdTx(CBusAccountDto cBusAccountDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
        
		try 
		{
			
						CBusAccount cBusAccount=new CBusAccount();
			            String htcode=cBusAccountDto.getHtcode();
			            cBusAccount.setAcSerialno(cBusAccountDto.getAcSerialno());
			            cBusAccount.setHtcode(htcode);
			            cBusAccount.setDeptid(user.getCenterId());
			            cBusAccount.setTradeAmt(new BigDecimal(cBusAccountDto.getTradeAmt()));
			            cBusAccount.setTradeType(new BigDecimal(cBusAccountDto.getTradeType()));
			            cBusAccount.setStatus(1+"");
			            cBusAccount.setCreatetime(new Date());
			            cBusAccount.setCreateoper(user.getUserId()+"");
						modelDao.create(cBusAccount);
						log.debug("---------收付款数据保存成功--------");
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
	public ResultDto<String> updateBusAccountRdTx(CBusAccountDto cBusAccountDto) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String id=cBusAccountDto.getAcSerialno();
			CBusAccount  cBusAccount= modelDao.findById(id);
			System.out.println("cBusAccountDto.getAcSerialno()1111111111111111"+cBusAccountDto.getAcSerialno());
			if(cBusAccount!=null){
				String status=cBusAccount.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"已审核数据不可以修改");
			    }
				cBusAccount.setTradeAmt(new BigDecimal(cBusAccountDto.getTradeAmt()));
				cBusAccount.setTradeType(new BigDecimal(cBusAccountDto.getTradeType()));
				modelDao.update(cBusAccount);
				log.debug("---------收付款修改成功--------");
				result=new ResultDto<String>(true,"数据修改成功");	
				
				
			}else{
				result=new ResultDto<String>(false,"该数据不在本中心不允许修改");
			}
		} catch (NumberFormatException e){
			log.error("收付款数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("收付款数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}

	@Override
	public ResultDto<String> deleteBusAccountById(String id, String deptId) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			          
			            CBusAccount cBusAccount2=modelDao.findById(id);
						
						if( cBusAccount2 !=null)
						{
							String status=cBusAccount2.getStatus();
							if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
						    {
						    	return new ResultDto<String>(false,"已审核数据不能修改");
						    }	
							    modelDao.deleteById(id);
							    log.debug("=========收付款数据删除成功========");
							   
							    result=new ResultDto<String>(true,"删除数据成功");
						}else
						{ 
							    result=new ResultDto<String>(false,"数据不存在");
						}
			
			
		} catch (NumberFormatException e) {
						log.error("收付款删除数据转化异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
		} catch (Exception e) {
						log.error("收付款删除异常"+e.getMessage());
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
     }
		
	 return result;
	}

	@Override
	public ResultDto<String> auditBusAccountRdTx(CBusAccountDto cBusAccountDto, SessionUser user) {
		// TODO Auto-generated method stub
		ResultDto<String> result=null;
		try {
			String id=cBusAccountDto.getAcSerialno();
			CBusAccount  cBusAccount= modelDao.findById(id);
			if(cBusAccount!=null){
				String status=cBusAccount.getStatus();
				if(!String.valueOf(CheckStatus.INIT.getCode()).equals(status))
			    {
			    	return new ResultDto<String>(false,"不能重复审核");
			    }
				cBusAccount.setStatus(cBusAccountDto.getStatus());
				cBusAccount.setAuditingoper(user.getUserId()+"");
				cBusAccount.setAuditingtime(new Date());
				modelDao.update(cBusAccount);
				log.debug("---------收付款审核成功--------");
				result=new ResultDto<String>(true,"审核成功");	
			}else{
				result=new ResultDto<String>(false,"该数据不在本中心不允许审核");
			}
		} catch (NumberFormatException e){
			log.error("收付款数据转化异常"+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
					log.error("收付款数据修改异常"+e.getMessage());
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
		}
		
		
		return result;
	}
}