<nav class="text-center">
    <ul class="pagination">

	[#if totalPages > 1]
		[#if isFirst]
            <li><span class="firstPage">&nbsp;</span><li>
		[#else]
            <li><a class="firstPage" href="[@pattern?replace("{pageNumber}", "${firstPageNumber}")?interpret /]">&nbsp;</a><li>
		[/#if]
		[#if hasPrevious]
            <li><a class="previousPage" href="[@pattern?replace("{pageNumber}", "${previousPageNumber}")?interpret /]">&nbsp;</a><li>
		[#else]
            <li><span class="previousPage">&nbsp;</span><li>
		[/#if]
		[#list segment as segmentPageNumber]
			[#if segmentPageNumber_index == 0 && segmentPageNumber > firstPageNumber + 1]
                <li><span class="pageBreak">...</span><li>
			[/#if]
			[#if segmentPageNumber != pageNumber]
                <li><a href="[@pattern?replace("{pageNumber}", "${segmentPageNumber}")?interpret /]">${segmentPageNumber}</a><li>
			[#else]
                <li class="active"><span >${segmentPageNumber}</span><li>
			[/#if]
			[#if !segmentPageNumber_has_next && segmentPageNumber < lastPageNumber - 1]
                <li><span class="pageBreak">...</span><li>
			[/#if]
		[/#list]
		[#if hasNext]
            <li><a class="nextPage" href="[@pattern?replace("{pageNumber}", "${nextPageNumber}")?interpret /]">&nbsp;</a><li>
		[#else]
            <li><span class="nextPage">&nbsp;</span><li>
		[/#if]
		[#if isLast]
            <li><span class="lastPage">&nbsp;</span><li>
		[#else]
            <li><a class="lastPage" href="[@pattern?replace("{pageNumber}", "${lastPageNumber}")?interpret /]">&nbsp;</a><li>
		[/#if]
	[/#if]
    </ul>
</nav>