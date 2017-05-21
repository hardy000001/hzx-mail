package com.lq.lss.core.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lq.easyui.service.impl.EasyUIServiceBase;
import com.lq.lss.core.dao.MaterialInfoDao;
import com.lq.lss.core.service.MaterialInfoService;
import com.lq.lss.model.BMaterialInfo;

/**
 * 
 * @author lanbo
 *
 */
@Service
public class MaterialInfoServiceImpl extends EasyUIServiceBase<BMaterialInfo, Integer, MaterialInfoDao> implements MaterialInfoService {

	@Override
	public List<BMaterialInfo> queryMaInfoList(Integer typeid) {
		return modelDao.findMaInfoList(typeid);
	}

	@Override
	public void bachcreatMerTest() {
		// TODO Auto-generated method stub
		
		//钢管信息添加
//		for (int i=1;i<71;i++){
//			
//			BMaterialInfo b=new BMaterialInfo();
//			
//			float code= i*0.1f;
//			String coden="1000"+ String.format("%02d", i); ;
//			String Name=
//					String.format("%.1f米钢管", code);;
//			b.setCode(coden);
//			b.setName(Name);
//			b.setTypeid(1000);
//			b.setPrice(new BigDecimal(20.0));
//			b.setAccountFlag("米");
//			b.setConvertFlag("米");
//			b.setCovertRatio(new BigDecimal(code));
//			b.setCompensateFlag("米");
//			b.setCompensateRatio(new BigDecimal(20.0));
//
//			b.setExpressFlag("吨");
//			b.setExpressPrice(new BigDecimal(20.0));
//			b.setExpressRatio(new BigDecimal(20.0));
//			b.setPyCode("GG");
//			b.setZxPrice(new BigDecimal(20.0));
//			b.setStatus("0");
//			modelDao.create(b);
//		}
		
//		//扣件信息添加
//		for (int i=0;i<3;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		String[] a=new String[]{"十字扣件","对接扣件","转向扣件"};
//		
//		float code= i*0.1f;
//		String coden="2100"+ String.format("%02d", i+1); ;
//		String Name=a[i];
//		b.setCode(coden);
//		b.setName(Name);
//		b.setTypeid(2100);
//		b.setPrice(new BigDecimal(20.0));
//		b.setAccountFlag("只");
//		b.setConvertFlag("只");
//		b.setCovertRatio(new BigDecimal(1));
//		b.setCompensateFlag("只");
//		b.setCompensateRatio(new BigDecimal(20.0));
//
//		b.setExpressFlag("吨");
//		b.setExpressPrice(new BigDecimal(20.0));
//		b.setExpressRatio(new BigDecimal(20.0));
//		b.setPyCode("BKJ");
//		b.setZxPrice(new BigDecimal(20.0));
//		b.setStatus("0");
//		modelDao.create(b);
//	}
//		
//		
//		//扣件信息添加
//		for (int i=0;i<3;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		String[] a=new String[]{"十字扣件","对接扣件","转向扣件"};
//		
//		float code= i*0.1f;
//		String coden="2200"+ String.format("%02d", i+1); ;
//		String Name=a[i];
//		b.setCode(coden);
//		b.setName(Name);
//		b.setTypeid(2200);
//		b.setPrice(new BigDecimal(20.0));
//		b.setAccountFlag("只");
//		b.setConvertFlag("只");
//		b.setCovertRatio(new BigDecimal(1));
//		b.setCompensateFlag("只");
//		b.setCompensateRatio(new BigDecimal(20.0));
//
//		b.setExpressFlag("吨");
//		b.setExpressPrice(new BigDecimal(20.0));
//		b.setExpressRatio(new BigDecimal(20.0));
//		b.setPyCode("BKJ");
//		b.setZxPrice(new BigDecimal(20.0));
//		b.setStatus("0");
//		modelDao.create(b);
//	}
		
//		//套管信息添加
//		for (int i=1;i<6;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		String[] a=new String[]{"40公分套管","30公分套管","20公分套管","15公分套管","10公分套管"};
//		
//		float code= i*0.1f;
//		String coden="3000"+ String.format("%02d", i); ;
//		String Name=a[i-1];
//		b.setCode(coden);
//		b.setName(Name);
//		b.setTypeid(3000);
//		b.setPrice(new BigDecimal(20.0));
//		b.setAccountFlag("只");
//		b.setConvertFlag("只");
//		b.setCovertRatio(new BigDecimal(1));
//		b.setCompensateFlag("只");
//		b.setCompensateRatio(new BigDecimal(20.0));
//
//		b.setExpressFlag("吨");
//		b.setExpressPrice(new BigDecimal(20.0));
//		b.setExpressRatio(new BigDecimal(20.0));
//		b.setPyCode("KJ");
//		b.setZxPrice(new BigDecimal(20.0));
//		b.setStatus("0");
//		modelDao.create(b);
//	}
		
		
//		//短管信息添加
//		for (int i=1;i<6;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		String[] a=new String[]{"10公分短管","20公分短管","30公分短管","40公分短管","50公分短管"};
//		float code= i*0.1f;
//		String coden="4000"+ String.format("%02d", i); ;
//		String Name=a[i-1];
//		b.setCode(coden);
//		b.setName(Name);
//		b.setTypeid(4000);
//		b.setPrice(new BigDecimal(20.0));
//		b.setAccountFlag("只");
//		b.setConvertFlag("只");
//		b.setCovertRatio(new BigDecimal(1));
//		b.setCompensateFlag("只");
//		b.setCompensateRatio(new BigDecimal(20.0));
//
//		b.setExpressFlag("吨");
//		b.setExpressPrice(new BigDecimal(20.0));
//		b.setExpressRatio(new BigDecimal(20.0));
//		b.setPyCode("KJ");
//		b.setZxPrice(new BigDecimal(20.0));
//		b.setStatus("0");
//		modelDao.create(b);
//	}
//		
//		//槽钢信息添加
//		for (int i=1;i<10;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		//String[] a=new String[]{"10公分短管","20公分短管","30公分短管","40公分短管","50公分短管"};
//		
//		float code= i*0.1f;
//		String coden="6120"+ String.format("%02d", i); ;
//		String Name=String.valueOf(i) +".0米12#槽钢";
//		b.setCode(coden);
//		b.setName(Name);
		
		for (int i=10;i<91;i++){
		
			if(i<61 || (i==70)|| (i==80)|| (i==90)){
				
				BMaterialInfo b=new BMaterialInfo();
				float code= i*0.1f;
				String coden="6180"+ String.format("%02d", i); ;
				String Name=
						String.format("%.1f米18#槽钢", code);;
				b.setCode(coden);
				b.setName(Name);				
				b.setTypeid(6180);
				b.setPrice(new BigDecimal(20.0));
				b.setAccountFlag("根");
				b.setConvertFlag("米");
				b.setCovertRatio(new BigDecimal(1));
				b.setCompensateFlag("根");
				b.setCompensateRatio(new BigDecimal(20.0));

				b.setExpressFlag("吨");
				b.setExpressPrice(new BigDecimal(20.0));
				b.setExpressRatio(new BigDecimal(20.0));
				b.setPyCode("CG");
				b.setZxPrice(new BigDecimal(20.0));
				b.setStatus("0");
				modelDao.create(b);	
				
			}
	
	}
		
		
		for (int i=10;i<91;i++){
		
			if(i<61 || (i==70)|| (i==80)|| (i==90)){
				
				BMaterialInfo b=new BMaterialInfo();
				float code= i*0.1f;
				String coden="6140"+ String.format("%02d", i); ;
				String Name=
						String.format("%.1f米14#槽钢", code);;
				b.setCode(coden);
				b.setName(Name);				
				b.setTypeid(6140);
				b.setPrice(new BigDecimal(20.0));
				b.setAccountFlag("根");
				b.setConvertFlag("米");
				b.setCovertRatio(new BigDecimal(1));
				b.setCompensateFlag("根");
				b.setCompensateRatio(new BigDecimal(20.0));

				b.setExpressFlag("吨");
				b.setExpressPrice(new BigDecimal(20.0));
				b.setExpressRatio(new BigDecimal(20.0));
				b.setPyCode("CG");
				b.setZxPrice(new BigDecimal(20.0));
				b.setStatus("0");
				modelDao.create(b);	
				
			}
	
	}
		
		
		for (int i=10;i<91;i++){
			
			if(i<61 || (i==70)|| (i==80)|| (i==90)){
				
				BMaterialInfo b=new BMaterialInfo();
				float code= i*0.1f;
				String coden="6120"+ String.format("%02d", i); ;
				String Name=
						String.format("%.1f米12#槽钢", code);;
				b.setCode(coden);
				b.setName(Name);				
				b.setTypeid(6120);
				b.setPrice(new BigDecimal(20.0));
				b.setAccountFlag("根");
				b.setConvertFlag("米");
				b.setCovertRatio(new BigDecimal(1));
				b.setCompensateFlag("根");
				b.setCompensateRatio(new BigDecimal(20.0));

				b.setExpressFlag("吨");
				b.setExpressPrice(new BigDecimal(20.0));
				b.setExpressRatio(new BigDecimal(20.0));
				b.setPyCode("CG");
				b.setZxPrice(new BigDecimal(20.0));
				b.setStatus("0");
				modelDao.create(b);	
				
			}
	
	}
		
		
		for (int i=10;i<91;i++){
			
			if(i<61 || (i==70)|| (i==80)|| (i==90)){
				
				BMaterialInfo b=new BMaterialInfo();
				float code= i*0.1f;
				String coden="6000"+ String.format("%02d", i); ;
				String Name=
						String.format("%.1f米10#槽钢", code);;
				b.setCode(coden);
				b.setName(Name);				
				b.setTypeid(6000);
				b.setPrice(new BigDecimal(20.0));
				b.setAccountFlag("根");
				b.setConvertFlag("米");
				b.setCovertRatio(new BigDecimal(1));
				b.setCompensateFlag("根");
				b.setCompensateRatio(new BigDecimal(20.0));

				b.setExpressFlag("吨");
				b.setExpressPrice(new BigDecimal(20.0));
				b.setExpressRatio(new BigDecimal(20.0));
				b.setPyCode("CG");
				b.setZxPrice(new BigDecimal(20.0));
				b.setStatus("0");
				modelDao.create(b);	
				
			}
	
	}
		
//		
//		//槽钢信息添加
//		for (int i=1;i<10;i++){
//		
//		BMaterialInfo b=new BMaterialInfo();
//		//String[] a=new String[]{"10公分短管","20公分短管","30公分短管","40公分短管","50公分短管"};
//		
//		float code= i*0.1f;
//		String coden="6140"+ String.format("%02d", i); ;
//		String Name=String.valueOf(i) +".0米14#槽钢";
//		b.setCode(coden);
//		b.setName(Name);
//		b.setTypeid(6140);
//		b.setPrice(new BigDecimal(20.0));
//		b.setAccountFlag("根");
//		b.setConvertFlag("米");
//		b.setCovertRatio(new BigDecimal(1));
//		b.setCompensateFlag("根");
//		b.setCompensateRatio(new BigDecimal(20.0));
//
//		b.setExpressFlag("吨");
//		b.setExpressPrice(new BigDecimal(20.0));
//		b.setExpressRatio(new BigDecimal(20.0));
//		b.setPyCode("CG");
//		b.setZxPrice(new BigDecimal(20.0));
//		b.setStatus("0");
//		modelDao.create(b);
//	}
		
		
//		//12#工字钢信息添加
//		for (int i=1;i<61;i++){
//			
//			BMaterialInfo b=new BMaterialInfo();
//			
//			float code= i*0.1f;
//			String coden="8180"+ String.format("%02d", i); ;
//			String Name=
//					String.format("%.1f米18#工字钢", code);;
//			b.setCode(coden);
//			b.setName(Name);
//			b.setTypeid(8180);
//			b.setPrice(new BigDecimal(20.0));
//			b.setAccountFlag("根");
//			b.setConvertFlag("米");
//			b.setCovertRatio(new BigDecimal(code));
//			b.setCompensateFlag("米");
//			b.setCompensateRatio(new BigDecimal(20.0));
//
//			b.setExpressFlag("吨");
//			b.setExpressPrice(new BigDecimal(20.0));
//			b.setExpressRatio(new BigDecimal(20.0));
//			b.setPyCode("GZG");
//			b.setZxPrice(new BigDecimal(20.0));
//			b.setStatus("0");
//			modelDao.create(b);
//		}		
//		
//		//12#工字钢信息添加
//		for (int i=1;i<61;i++){
//			
//			BMaterialInfo b=new BMaterialInfo();
//			
//			float code= i*0.1f;
//			String coden="8160"+ String.format("%02d", i); ;
//			String Name=
//					String.format("%.1f米16#工字钢", code);;
//			b.setCode(coden);
//			b.setName(Name);
//			b.setTypeid(8160);
//			b.setPrice(new BigDecimal(20.0));
//			b.setAccountFlag("根");
//			b.setConvertFlag("米");
//			b.setCovertRatio(new BigDecimal(code));
//			b.setCompensateFlag("米");
//			b.setCompensateRatio(new BigDecimal(20.0));
//
//			b.setExpressFlag("吨");
//			b.setExpressPrice(new BigDecimal(20.0));
//			b.setExpressRatio(new BigDecimal(20.0));
//			b.setPyCode("GZG");
//			b.setZxPrice(new BigDecimal(20.0));
//			b.setStatus("0");
//			modelDao.create(b);
//		}	
		
		
		
		
		
		
		
		
	}

	


}
