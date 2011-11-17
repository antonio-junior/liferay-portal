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

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:paginator:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:paginator:scopedAttributes");
CustomAttributes customAttributes = (CustomAttributes)request.getAttribute("aui:paginator:customAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

%>

<%@ include file="/html/taglib/aui/init-alloy.jsp" %>

<%
boolean alwaysVisible = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:paginator:alwaysVisible")), true);
java.lang.String containers = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:containers"));
boolean destroyed = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:paginator:destroyed")), false);
java.lang.String firstPageLink = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:firstPageLink"));
java.lang.String firstPageLinkLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:firstPageLinkLabel"), "first");
boolean initialized = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:paginator:initialized")), false);
java.lang.String lastPageLink = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:lastPageLink"));
java.lang.String lastPageLinkLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:lastPageLinkLabel"), "last");
java.lang.Number maxPageLinks = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:paginator:maxPageLinks")), 10);
java.lang.String nextPageLink = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:nextPageLink"));
java.lang.String nextPageLinkLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:nextPageLinkLabel"), "next &gt;");
java.lang.Number paginatorPage = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:paginator:paginatorPage")), 1);
java.lang.String pageContainerTemplate = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:pageContainerTemplate"));
java.lang.Object pageLinkContent = (java.lang.Object)request.getAttribute("aui:paginator:pageLinkContent");
java.lang.String pageLinkTemplate = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:pageLinkTemplate"));
java.lang.String pageReportEl = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:pageReportEl"));
java.lang.String pageReportLabelTemplate = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:pageReportLabelTemplate"), "({page} of {totalPages})");
java.lang.String prevPageLink = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:prevPageLink"));
java.lang.String prevPageLinkLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:prevPageLinkLabel"), "&lt; prev");
java.lang.Number rowsPerPage = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:paginator:rowsPerPage")), 1);
java.lang.String rowsPerPageEl = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:rowsPerPageEl"));
java.util.ArrayList rowsPerPageOptions = _toArrayList(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:paginator:rowsPerPageOptions"), "[]"));
java.util.HashMap state = _toHashMap(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:paginator:state"), "{}"));
java.lang.String template = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:template"), "{FirstPageLink} {PrevPageLink} {PageLinks} {NextPageLink} {LastPageLink} {CurrentPageReport} {Total} {RowsPerPageSelect}");
java.lang.Number total = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:paginator:total")), 0);
java.lang.String totalEl = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:totalEl"));
java.lang.String totalLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:paginator:totalLabel"), "(Total {total})");
java.lang.Number totalPages = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:paginator:totalPages")), 0);
java.lang.Object afterAlwaysVisibleChange = (java.lang.Object)request.getAttribute("aui:paginator:afterAlwaysVisibleChange");
java.lang.Object afterContainersChange = (java.lang.Object)request.getAttribute("aui:paginator:afterContainersChange");
java.lang.Object afterDestroy = (java.lang.Object)request.getAttribute("aui:paginator:afterDestroy");
java.lang.Object afterDestroyedChange = (java.lang.Object)request.getAttribute("aui:paginator:afterDestroyedChange");
java.lang.Object afterFirstPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:afterFirstPageLinkChange");
java.lang.Object afterFirstPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:afterFirstPageLinkLabelChange");
java.lang.Object afterInit = (java.lang.Object)request.getAttribute("aui:paginator:afterInit");
java.lang.Object afterInitializedChange = (java.lang.Object)request.getAttribute("aui:paginator:afterInitializedChange");
java.lang.Object afterLastPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:afterLastPageLinkChange");
java.lang.Object afterLastPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:afterLastPageLinkLabelChange");
java.lang.Object afterMaxPageLinksChange = (java.lang.Object)request.getAttribute("aui:paginator:afterMaxPageLinksChange");
java.lang.Object afterNextPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:afterNextPageLinkChange");
java.lang.Object afterNextPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:afterNextPageLinkLabelChange");
java.lang.Object afterPageChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageChange");
java.lang.Object afterPageContainerTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageContainerTemplateChange");
java.lang.Object afterPageLinkContentChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageLinkContentChange");
java.lang.Object afterPageLinkTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageLinkTemplateChange");
java.lang.Object afterPageReportElChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageReportElChange");
java.lang.Object afterPageReportLabelTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPageReportLabelTemplateChange");
java.lang.Object afterPrevPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPrevPageLinkChange");
java.lang.Object afterPrevPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:afterPrevPageLinkLabelChange");
java.lang.Object afterRowsPerPageChange = (java.lang.Object)request.getAttribute("aui:paginator:afterRowsPerPageChange");
java.lang.Object afterRowsPerPageElChange = (java.lang.Object)request.getAttribute("aui:paginator:afterRowsPerPageElChange");
java.lang.Object afterRowsPerPageOptionsChange = (java.lang.Object)request.getAttribute("aui:paginator:afterRowsPerPageOptionsChange");
java.lang.Object afterStateChange = (java.lang.Object)request.getAttribute("aui:paginator:afterStateChange");
java.lang.Object afterTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:afterTemplateChange");
java.lang.Object afterTotalChange = (java.lang.Object)request.getAttribute("aui:paginator:afterTotalChange");
java.lang.Object afterTotalElChange = (java.lang.Object)request.getAttribute("aui:paginator:afterTotalElChange");
java.lang.Object afterTotalLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:afterTotalLabelChange");
java.lang.Object afterTotalPagesChange = (java.lang.Object)request.getAttribute("aui:paginator:afterTotalPagesChange");
java.lang.Object onAlwaysVisibleChange = (java.lang.Object)request.getAttribute("aui:paginator:onAlwaysVisibleChange");
java.lang.Object onContainersChange = (java.lang.Object)request.getAttribute("aui:paginator:onContainersChange");
java.lang.Object onDestroy = (java.lang.Object)request.getAttribute("aui:paginator:onDestroy");
java.lang.Object onDestroyedChange = (java.lang.Object)request.getAttribute("aui:paginator:onDestroyedChange");
java.lang.Object onFirstPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:onFirstPageLinkChange");
java.lang.Object onFirstPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:onFirstPageLinkLabelChange");
java.lang.Object onInit = (java.lang.Object)request.getAttribute("aui:paginator:onInit");
java.lang.Object onInitializedChange = (java.lang.Object)request.getAttribute("aui:paginator:onInitializedChange");
java.lang.Object onLastPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:onLastPageLinkChange");
java.lang.Object onLastPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:onLastPageLinkLabelChange");
java.lang.Object onMaxPageLinksChange = (java.lang.Object)request.getAttribute("aui:paginator:onMaxPageLinksChange");
java.lang.Object onNextPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:onNextPageLinkChange");
java.lang.Object onNextPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:onNextPageLinkLabelChange");
java.lang.Object onPageChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageChange");
java.lang.Object onPageContainerTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageContainerTemplateChange");
java.lang.Object onPageLinkContentChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageLinkContentChange");
java.lang.Object onPageLinkTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageLinkTemplateChange");
java.lang.Object onPageReportElChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageReportElChange");
java.lang.Object onPageReportLabelTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:onPageReportLabelTemplateChange");
java.lang.Object onPrevPageLinkChange = (java.lang.Object)request.getAttribute("aui:paginator:onPrevPageLinkChange");
java.lang.Object onPrevPageLinkLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:onPrevPageLinkLabelChange");
java.lang.Object onRowsPerPageChange = (java.lang.Object)request.getAttribute("aui:paginator:onRowsPerPageChange");
java.lang.Object onRowsPerPageElChange = (java.lang.Object)request.getAttribute("aui:paginator:onRowsPerPageElChange");
java.lang.Object onRowsPerPageOptionsChange = (java.lang.Object)request.getAttribute("aui:paginator:onRowsPerPageOptionsChange");
java.lang.Object onStateChange = (java.lang.Object)request.getAttribute("aui:paginator:onStateChange");
java.lang.Object onTemplateChange = (java.lang.Object)request.getAttribute("aui:paginator:onTemplateChange");
java.lang.Object onTotalChange = (java.lang.Object)request.getAttribute("aui:paginator:onTotalChange");
java.lang.Object onTotalElChange = (java.lang.Object)request.getAttribute("aui:paginator:onTotalElChange");
java.lang.Object onTotalLabelChange = (java.lang.Object)request.getAttribute("aui:paginator:onTotalLabelChange");
java.lang.Object onTotalPagesChange = (java.lang.Object)request.getAttribute("aui:paginator:onTotalPagesChange");

_updateOptions(_options, "alwaysVisible", alwaysVisible);
_updateOptions(_options, "containers", containers);
_updateOptions(_options, "destroyed", destroyed);
_updateOptions(_options, "firstPageLink", firstPageLink);
_updateOptions(_options, "firstPageLinkLabel", firstPageLinkLabel);
_updateOptions(_options, "initialized", initialized);
_updateOptions(_options, "lastPageLink", lastPageLink);
_updateOptions(_options, "lastPageLinkLabel", lastPageLinkLabel);
_updateOptions(_options, "maxPageLinks", maxPageLinks);
_updateOptions(_options, "nextPageLink", nextPageLink);
_updateOptions(_options, "nextPageLinkLabel", nextPageLinkLabel);
_updateOptions(_options, "paginatorPage", paginatorPage);
_updateOptions(_options, "pageContainerTemplate", pageContainerTemplate);
_updateOptions(_options, "pageLinkContent", pageLinkContent);
_updateOptions(_options, "pageLinkTemplate", pageLinkTemplate);
_updateOptions(_options, "pageReportEl", pageReportEl);
_updateOptions(_options, "pageReportLabelTemplate", pageReportLabelTemplate);
_updateOptions(_options, "prevPageLink", prevPageLink);
_updateOptions(_options, "prevPageLinkLabel", prevPageLinkLabel);
_updateOptions(_options, "rowsPerPage", rowsPerPage);
_updateOptions(_options, "rowsPerPageEl", rowsPerPageEl);
_updateOptions(_options, "rowsPerPageOptions", rowsPerPageOptions);
_updateOptions(_options, "state", state);
_updateOptions(_options, "template", template);
_updateOptions(_options, "total", total);
_updateOptions(_options, "totalEl", totalEl);
_updateOptions(_options, "totalLabel", totalLabel);
_updateOptions(_options, "totalPages", totalPages);
_updateOptions(_options, "afterAlwaysVisibleChange", afterAlwaysVisibleChange);
_updateOptions(_options, "afterContainersChange", afterContainersChange);
_updateOptions(_options, "afterDestroy", afterDestroy);
_updateOptions(_options, "afterDestroyedChange", afterDestroyedChange);
_updateOptions(_options, "afterFirstPageLinkChange", afterFirstPageLinkChange);
_updateOptions(_options, "afterFirstPageLinkLabelChange", afterFirstPageLinkLabelChange);
_updateOptions(_options, "afterInit", afterInit);
_updateOptions(_options, "afterInitializedChange", afterInitializedChange);
_updateOptions(_options, "afterLastPageLinkChange", afterLastPageLinkChange);
_updateOptions(_options, "afterLastPageLinkLabelChange", afterLastPageLinkLabelChange);
_updateOptions(_options, "afterMaxPageLinksChange", afterMaxPageLinksChange);
_updateOptions(_options, "afterNextPageLinkChange", afterNextPageLinkChange);
_updateOptions(_options, "afterNextPageLinkLabelChange", afterNextPageLinkLabelChange);
_updateOptions(_options, "afterPageChange", afterPageChange);
_updateOptions(_options, "afterPageContainerTemplateChange", afterPageContainerTemplateChange);
_updateOptions(_options, "afterPageLinkContentChange", afterPageLinkContentChange);
_updateOptions(_options, "afterPageLinkTemplateChange", afterPageLinkTemplateChange);
_updateOptions(_options, "afterPageReportElChange", afterPageReportElChange);
_updateOptions(_options, "afterPageReportLabelTemplateChange", afterPageReportLabelTemplateChange);
_updateOptions(_options, "afterPrevPageLinkChange", afterPrevPageLinkChange);
_updateOptions(_options, "afterPrevPageLinkLabelChange", afterPrevPageLinkLabelChange);
_updateOptions(_options, "afterRowsPerPageChange", afterRowsPerPageChange);
_updateOptions(_options, "afterRowsPerPageElChange", afterRowsPerPageElChange);
_updateOptions(_options, "afterRowsPerPageOptionsChange", afterRowsPerPageOptionsChange);
_updateOptions(_options, "afterStateChange", afterStateChange);
_updateOptions(_options, "afterTemplateChange", afterTemplateChange);
_updateOptions(_options, "afterTotalChange", afterTotalChange);
_updateOptions(_options, "afterTotalElChange", afterTotalElChange);
_updateOptions(_options, "afterTotalLabelChange", afterTotalLabelChange);
_updateOptions(_options, "afterTotalPagesChange", afterTotalPagesChange);
_updateOptions(_options, "onAlwaysVisibleChange", onAlwaysVisibleChange);
_updateOptions(_options, "onContainersChange", onContainersChange);
_updateOptions(_options, "onDestroy", onDestroy);
_updateOptions(_options, "onDestroyedChange", onDestroyedChange);
_updateOptions(_options, "onFirstPageLinkChange", onFirstPageLinkChange);
_updateOptions(_options, "onFirstPageLinkLabelChange", onFirstPageLinkLabelChange);
_updateOptions(_options, "onInit", onInit);
_updateOptions(_options, "onInitializedChange", onInitializedChange);
_updateOptions(_options, "onLastPageLinkChange", onLastPageLinkChange);
_updateOptions(_options, "onLastPageLinkLabelChange", onLastPageLinkLabelChange);
_updateOptions(_options, "onMaxPageLinksChange", onMaxPageLinksChange);
_updateOptions(_options, "onNextPageLinkChange", onNextPageLinkChange);
_updateOptions(_options, "onNextPageLinkLabelChange", onNextPageLinkLabelChange);
_updateOptions(_options, "onPageChange", onPageChange);
_updateOptions(_options, "onPageContainerTemplateChange", onPageContainerTemplateChange);
_updateOptions(_options, "onPageLinkContentChange", onPageLinkContentChange);
_updateOptions(_options, "onPageLinkTemplateChange", onPageLinkTemplateChange);
_updateOptions(_options, "onPageReportElChange", onPageReportElChange);
_updateOptions(_options, "onPageReportLabelTemplateChange", onPageReportLabelTemplateChange);
_updateOptions(_options, "onPrevPageLinkChange", onPrevPageLinkChange);
_updateOptions(_options, "onPrevPageLinkLabelChange", onPrevPageLinkLabelChange);
_updateOptions(_options, "onRowsPerPageChange", onRowsPerPageChange);
_updateOptions(_options, "onRowsPerPageElChange", onRowsPerPageElChange);
_updateOptions(_options, "onRowsPerPageOptionsChange", onRowsPerPageOptionsChange);
_updateOptions(_options, "onStateChange", onStateChange);
_updateOptions(_options, "onTemplateChange", onTemplateChange);
_updateOptions(_options, "onTotalChange", onTotalChange);
_updateOptions(_options, "onTotalElChange", onTotalElChange);
_updateOptions(_options, "onTotalLabelChange", onTotalLabelChange);
_updateOptions(_options, "onTotalPagesChange", onTotalPagesChange);
%>

<%@ include file="/html/taglib/aui/paginator/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:paginator:";
%>