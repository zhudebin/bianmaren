<#-- 美化时间模板 -->
<#macro friendly_time datetime=.now>
	<#assign ct = (.now?long-datetime?long)/1000>
	<#if ct gte 31104000><#--n年前--><span class="text-muted">${(ct/31104000)?int}年前</span>
	    <#t><#elseif ct gte 2592000><#--n月前--><span class="text-muted">${(ct/2592000)?int}个月前</span>
	    <#t><#elseif ct gte 86400*2><#--n天前--><span class="text-info">${(ct/86400)?int}天前</span>
	    <#t><#elseif ct gte 86400><#--1天前--><span class="text-warning">昨天</span>
	    <#t><#elseif ct gte 3600><#--n小时前--><span class="text-primary">${(ct/3600)?int}小时前</span>
	    <#t><#elseif ct gte 60><#--n分钟前--><span class="text-success">${(ct/60)?int}分钟前</span>
	    <#t><#elseif ct gt 0><#--n秒前--><span class="text-danger">${ct?int}秒前</span>
	    <#t><#else>刚刚
	</#if>
</#macro>

<#function max x y>
    <#if (x<y)><#return y><#else><#return x></#if>
</#function>
<#function min x y>
    <#if (x<y)><#return x><#else><#return y></#if>
</#function>

<#macro pages recordCount pageSize p toURL>
    	<style type="text/css">
		.pagination {
			line-height: 30px;
			height: 30px;
			text-align: center;
			font-size: 0;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}
		.pagination span{
			display: inline-block;
			font-size: 14px;
			color: #6b7485;
			background-color: #ecedf0;
			margin: 0 5px;
			padding: 0 12px;
			min-width: 14px;
			border-radius: 3px;
		}
		.pagination a, .pagination a:link, .pagination a:visited {
			display: inline-block;
			font-size: 14px;
			color: #6b7485;
			background-color: #ecedf0;
			margin: 0 5px;
			padding: 0 12px;
			min-width: 14px;
			border-radius: 3px;
		}
		.pagination a:hover, .pagination a:active {
			background: #DEE0E5;
			cursor: pointer;
		}
		.pagination span.current {
			color: #fff;
			background-color: #9097a4;
		}
		.pagination span.disabled { color: #ddd;}
	</style>
	<#if (recordCount>0) >
    <div class="pagination">
	    	<#assign size=((recordCount + pageSize - 1) / pageSize)?int>  
		    <#if (p<=5)> <#-- p among first 5 pages -->
		        <#assign interval = 1..(min(5,size))>
		    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
		        <#assign interval = (max(1,(size-4)))..size >
		    <#else>
		        <#assign interval = (p-2)..(p+2)>
		    </#if>
		    <#if (p == 1)>
				<span class="disabled">上一页</span>
		 		<#else>
				<a href="${toURL+(p - 1)}.html">上一页</a>
		 		</#if>
		    <#if !(interval?seq_contains(1))>
		     <a href="${toURL}1.html">1</a> <a>...</a> <#rt>
		    </#if>
		    <#list interval as page>
		        <#if page=p>
		          <span class="current">${page}</span><#t>
		        <#else>
		         <a href="${toURL}${page}.html">${page}</a><#t>
		        </#if>
		    </#list>
		    <#if !(interval?seq_contains(size))>
		     <a>...</a> <a href="${toURL}${size}.html">${size}</a><#lt>
		    </#if>
		    <#if (p == size)>
				<span class="disabled">下一页</span>
		 	<#else>
				<a href="${toURL+(p + 1)}.html">下一页</a>
		 	</#if>
    </div>
 	</#if>
</#macro>


<#macro pagesTemaplte page>

	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
    <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
    <input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}" />
    <input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}" />
    <input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}" />
    
    <#assign recordCount = page.total />
    <#assign pageSize = page.pageSize />
    <#assign p= page.pageNumber />

	<#if (recordCount>0) >
    <ul class="pagination">
	    	<#assign size=((recordCount + pageSize - 1) / pageSize)?int>  
		    <#if (p<=5)> <#-- p among first 5 pages -->
		        <#assign interval = 1..(min(5,size))>
		    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
		        <#assign interval = (max(1,(size-4)))..size >
		    <#else>
		        <#assign interval = (p-2)..(p+2)>
		    </#if>
		    <#if (p == 1)>
				<li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-left"></i></a></li>
			<#else>
				<li ><a href="javascript: $.pageSkip(${p - 1})"><i class="fa fa-angle-left"></i></a></li>
			</#if>
		    <#if !(interval?seq_contains(1))>
                <li><a href="javascript: $.pageSkip(${1})">1</a></li>
            	<li><a>...</a></li><#rt>
		    </#if>
		    <#list interval as page>
		        <#if page=p>
                    <li class="active"><a href="javascript:void(0)">${page}</a></li>
		        <#else>
                    <li><a href="javascript: $.pageSkip(${page})">${page}</a></li>
		        </#if>
		    </#list>
		    <#if !(interval?seq_contains(size))>
                <li><a>...</a></li>
            	<li><a href="javascript: $.pageSkip(${size})">${size}</a></li><#lt>
		    </#if>
		    <#if (p == size)>
                <li class="disabled">
                    <a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a>
                </li>
		 	<#else>
                <li>
                    <a href="javascript: $.pageSkip(${p+1})"><i class="fa fa-angle-right"></i></a>
                </li>
		 	</#if>
    </ul>
 	</#if>
</#macro>