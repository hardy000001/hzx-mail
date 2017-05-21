  <#macro pagination pu>
       
     <#--  <#if pu.totalCount?has_content && pu.totalCount gt 0> -->
        <#assign pageRand=0> 
     <#if pu.totalCount?exists && pu.totalCount gt 0>
     	<#assign pageRand=pu.hashCode()>  
        <div class="TableFooter">
          <div class="FR">
       <#assign pageIndex=pu.curPage>
       <#assign pageCount=pu.getPageCount()>
       <#if (pageIndex>pageCount)>  
          <#assign pageIndex=pageCount>  
        </#if>
        <#if (pageIndex lt 0)>  
          <#assign pageIndex=1>  
        </#if>    
        
      <#if (pageIndex>1)>  
         <span><a href="javascript:pageGo_${pageRand}(${1})"  title="首页" >首页</a></span>
         <span><a href="javascript:pageGo_${pageRand}(${(pageIndex-1)})">上一页</a></span>
      </#if>  
     
       <span>${pageIndex}/${pageCount}页</span>
      
      <#-- 显示"下一页" --> 
	<#if (pageIndex lt pageCount)>  
	      <span><a href="javascript:pageGo_${pageRand}(${(pageIndex+1)})" title="下一页">下一页</a></span>  
	      <span><a href="javascript:pageGo_${pageRand}(${pageCount})" title="尾页">尾页</a></span>
      </#if>  
   </div>
</div>
   </#if>	
  <script type="text/javascript">
<!--
	function pageGo_${pageRand}(pageNo){
		<#if pu.pagerFunction?has_content >
				${pu.pagerFunction}(pageNo);
			<#else>
				var locaHref =  window.location.href;
				var newUrl = locaHref.replace(/(curPage\=\d{1,})/,"curPage="+pageNo);
				window.location.href = newUrl;	
		</#if>
	}
//-->
</script>
  </#macro>
