package com.lq.lss.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lq.util.RandomUtil;

/**
 * 
 * @author  作者: hzx
 * @date 创建时间: 2016-8-31 下午12:23:30
 */
public class CodeGenerater {
	
	private static final String INIT_NUMBER = "100100";           // 初始化商户号
	private static final String INIT_NUMBER_CUS = "10100";        // 初始化客户号
	private static volatile CodeGenerater codeGenerater = null;
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date = new Date();

	private CodeGenerater() {}

	/**
	 * 取得CodeGenerater的单例实现
	 * 
	 * @return
	 */
	public static CodeGenerater getInstance() {
		
		if (codeGenerater == null) 
		{
				synchronized (CodeGenerater.class) 
				{
						if (codeGenerater == null) 
						{
							codeGenerater = new CodeGenerater();
						}
				}
		}
		return codeGenerater;
	}

	/**
	 * 生成下一个租赁商户号
	 */
	public synchronized String generaterNextMchCode(String mchCode) {
		
		String  id = null;
		if (mchCode == null || "".equals(mchCode))
		{
			    id = INIT_NUMBER;
		} 
		else 
		{
				
				id =  1 + Long.parseLong(mchCode)+"";
		}
		return id;
	}
	/**
	 * 生成下一个客户号
	 */
	public synchronized String generaterNextCusCode(String cusCode) {
		
		String  id = null;
		if (cusCode == null || "".equals(cusCode))
		{
			    id = INIT_NUMBER_CUS;
		} 
		else 
		{
				
				id =  1 + Long.parseLong(cusCode)+"";
		}
		return id;
	}
	/**
	 * 生成下一个采购流水号18位
	 */
	public synchronized String generaterNextPurCode(){
		
		String  id = formatDate.format(date)+RandomUtil.get().randomDegital(4);
		
		return id;
	}
	/**
	 * 生成下一个销售流水号19位  
	 */
	public synchronized String generaterNextSaleCode(){
		
		String  id = formatDate.format(date)+RandomUtil.get().randomDegital(5);
		
		return id;
	}

}
