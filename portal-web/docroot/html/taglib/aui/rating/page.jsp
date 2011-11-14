<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/taglib/aui/rating/init.jsp" %>

<c:if test="<%= useMarkup %>">
	<c:if test="<%= !hasBoundingBox %>">
		<div id="<%= uniqueId %>BoundingBox" class="<%= BOUNDING_BOX_CLASS %>">
	</c:if>

   	<div id="<%= uniqueId %>SrcNode" class="<%= CONTENT_BOX_CLASS %>">
    	<div class="<%= CSS_RATING_LABEL_EL %>">
    		<%= label %>
   		</div>
        <%
		for (int i = 1; i <= (Integer)size; i++) {
		%>
			<a class="<%= CSS_RATING_EL.concat((i <= (Integer)defaultSelected) ? StringPool.SPACE.concat(CSS_RATING_EL_ON) : StringPool.BLANK) %>" href="javascript:;"></a>

			<label for="<%= uniqueId %>Labeled<%= i %>" style="display:none;"><%= MessageUtil.substitute(elementTitle, new Object[] {i, size}) %></label>
			<input checked="<%= i == (Integer)defaultSelected %>" id="<%= uniqueId %>Labeled<%= i %>" type="hidden" value="<%= i %>" />
		<%
		}
		%>
    </div>

	<c:if test="<%= !hasBoundingBox %>">
		</div>
	</c:if>
</c:if>

<aui:component
	excludeAttributes="var,javaScriptAttributes,useMarkup,useJavaScript"
	tagPageContext="<%= pageContext %>"
	options="<%= _options %>"
	module="aui-rating"
	name="Rating"
/>