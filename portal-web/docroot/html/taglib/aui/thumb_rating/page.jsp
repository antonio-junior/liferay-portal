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

<%@ include file="/html/taglib/aui/thumb_rating/init.jsp" %>

<c:if test="<%= useMarkup %>">
	<c:if test="<%= !hasBoundingBox %>">
		<div id="<%= uniqueId %>BoundingBox" class="<%= BOUNDING_BOX_CLASS %>">
	</c:if>

   	<div id="<%= uniqueId %>SrcNode" class="<%= CONTENT_BOX_CLASS %>">
    	<div class="<%= CSS_RATING_LABEL_EL %>">
    		<%= label %>
   		</div>

		<a class="<%= RATING_EL_UP_CLASS %>" href="javascript:;"></a>

		<label for="<%= uniqueId %>Up" style="display:none;"><%= MessageUtil.substitute(elementTitle, new Object[] {"Good"}) %></label>
		<input checked="<%= 1 == (Integer)defaultSelected %>" id="<%= uniqueId %>Up" type="hidden" value="<%= 1 %>" />

		<a class="<%= RATING_EL_DOWN_CLASS %>" href="javascript:;"></a>

		<label for="<%= uniqueId %>Down" style="display:none;"><%= MessageUtil.substitute(elementTitle, new Object[] {"Bad"}) %></label>
		<input checked="<%= 2 == (Integer)defaultSelected %>" id="<%= uniqueId %>Down" type="hidden" value="<%= 2 %>" />
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
	name="ThumbRating"
/>