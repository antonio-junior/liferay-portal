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

<%@ include file="/html/taglib/aui/progress_bar/init.jsp" %>

<c:if test="<%= useMarkup %>">
	<c:if test="<%= !hasBoundingBox %>">
		<div id="<%= uniqueId %>BoundingBox" class="<%= BOUNDING_BOX_CLASS %>" style="width: <%= width %>px; height: <%= height %>px">
	</c:if>

    <div id="<%= uniqueId %>SrcNode" class="<%= CONTENT_BOX_CLASS %>">
        <div class="<%= CSS_PROGRESS_BAR_STATUS %>"></div>

        <div class="<%= CSS_PROGRESS_BAR_TEXT %>">
        	<c:if test="<%= Validator.isNotNull(label) %>">
            	<%= label %>
            </c:if>
        </div>
    </div>

	<c:if test="<%= !hasBoundingBox %>">
		</div>
	</c:if>
</c:if>

<aui:component
	excludeAttributes="var,javaScriptAttributes,useMarkup,useJavaScript"
	tagPageContext="<%= pageContext %>"
	options="<%= _options %>"
	module="aui-progressbar"
	name="ProgressBar"
/>