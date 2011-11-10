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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:calendar:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:calendar:scopedAttributes");
CustomAttributes customAttributes = (CustomAttributes)request.getAttribute("aui:calendar:customAttributes");

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
boolean allowNone = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:allowNone")), true);
java.lang.String blankDays = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:blankDays"));
java.lang.Number currentDay = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:calendar:currentDay")), 0);
java.lang.Number currentMonth = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:calendar:currentMonth")), 0);
java.lang.Number currentYear = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:calendar:currentYear")), 0);
java.util.HashMap customRenderer = _toHashMap(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:calendar:customRenderer"), "{}"));
java.lang.Object date = (java.lang.Object)request.getAttribute("aui:calendar:date");
java.lang.String dateFormat = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:dateFormat"), "%m/%d/%Y");
java.util.ArrayList dates = _toArrayList(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:calendar:dates")));
boolean destroyed = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:destroyed")), false);
boolean disabled = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:disabled")), false);
java.lang.String disabledDatesRule = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:disabledDatesRule"));
java.lang.String enabledDatesRule = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:enabledDatesRule"));
java.lang.Number firstDayOfWeek = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:calendar:firstDayOfWeek")), 0);
boolean focused = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:focused")), false);
java.lang.String headerContentNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:headerContentNode"));
java.lang.Object headerRenderer = (java.lang.Object)request.getAttribute("aui:calendar:headerRenderer");
java.lang.String headerTitleNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:headerTitleNode"));
java.lang.Object height = (java.lang.Object)request.getAttribute("aui:calendar:height");
java.lang.String iconNextNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:iconNextNode"));
java.lang.String iconPrevNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:iconPrevNode"));
java.lang.String calendarId = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:calendarId"));
boolean initialized = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:initialized")), false);
java.lang.Object maxDate = (java.lang.Object)request.getAttribute("aui:calendar:maxDate");
java.lang.Object maximumDate = (java.lang.Object)request.getAttribute("aui:calendar:maximumDate");
java.lang.Object minDate = (java.lang.Object)request.getAttribute("aui:calendar:minDate");
java.lang.Object minimumDate = (java.lang.Object)request.getAttribute("aui:calendar:minimumDate");
java.lang.String monthDays = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:monthDays"));
java.lang.String monthDaysNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:monthDaysNode"));
java.lang.Object noneLinkNode = (java.lang.Object)request.getAttribute("aui:calendar:noneLinkNode");
java.lang.Object paddingDaysEnd = (java.lang.Object)request.getAttribute("aui:calendar:paddingDaysEnd");
java.lang.Object paddingDaysStart = (java.lang.Object)request.getAttribute("aui:calendar:paddingDaysStart");
java.lang.Object render = (java.lang.Object)request.getAttribute("aui:calendar:render");
boolean rendered = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:rendered")), false);
boolean selectMultipleDates = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:selectMultipleDates")), false);
java.util.ArrayList selectedDates = _toArrayList(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:calendar:selectedDates")));
java.lang.String selectionMode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:selectionMode"), "single");
boolean setValue = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:setValue")), true);
boolean showNextMonth = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:showNextMonth")), false);
boolean showOtherMonth = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:showOtherMonth")), true);
boolean showPrevMonth = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:showPrevMonth")), false);
boolean showToday = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:showToday")), true);
java.util.HashMap strings = _toHashMap(GetterUtil.getObject((java.lang.Object)request.getAttribute("aui:calendar:strings")));
java.lang.Number tabIndex = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:calendar:tabIndex")), 0);
java.lang.Object todayLinkNode = (java.lang.Object)request.getAttribute("aui:calendar:todayLinkNode");
boolean visible = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:calendar:visible")), true);
java.lang.String weekDays = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:weekDays"));
java.lang.String weekDaysNode = GetterUtil.getString((java.lang.String)request.getAttribute("aui:calendar:weekDaysNode"));
java.lang.Object width = (java.lang.Object)request.getAttribute("aui:calendar:width");
java.lang.Object afterAllowNoneChange = (java.lang.Object)request.getAttribute("aui:calendar:afterAllowNoneChange");
java.lang.Object afterBlankDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:afterBlankDaysChange");
java.lang.Object afterBoundingBoxChange = (java.lang.Object)request.getAttribute("aui:calendar:afterBoundingBoxChange");
java.lang.Object afterContentBoxChange = (java.lang.Object)request.getAttribute("aui:calendar:afterContentBoxChange");
java.lang.Object afterCurrentDayChange = (java.lang.Object)request.getAttribute("aui:calendar:afterCurrentDayChange");
java.lang.Object afterCurrentMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:afterCurrentMonthChange");
java.lang.Object afterCurrentYearChange = (java.lang.Object)request.getAttribute("aui:calendar:afterCurrentYearChange");
java.lang.Object afterCustomRendererChange = (java.lang.Object)request.getAttribute("aui:calendar:afterCustomRendererChange");
java.lang.Object afterDateChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDateChange");
java.lang.Object afterDateFormatChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDateFormatChange");
java.lang.Object afterDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDatesChange");
java.lang.Object afterDestroy = (java.lang.Object)request.getAttribute("aui:calendar:afterDestroy");
java.lang.Object afterDestroyedChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDestroyedChange");
java.lang.Object afterDisabledChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDisabledChange");
java.lang.Object afterDisabledDatesRuleChange = (java.lang.Object)request.getAttribute("aui:calendar:afterDisabledDatesRuleChange");
java.lang.Object afterEnabledDatesRuleChange = (java.lang.Object)request.getAttribute("aui:calendar:afterEnabledDatesRuleChange");
java.lang.Object afterFirstDayOfWeekChange = (java.lang.Object)request.getAttribute("aui:calendar:afterFirstDayOfWeekChange");
java.lang.Object afterFocusedChange = (java.lang.Object)request.getAttribute("aui:calendar:afterFocusedChange");
java.lang.Object afterHeaderContentNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterHeaderContentNodeChange");
java.lang.Object afterHeaderRendererChange = (java.lang.Object)request.getAttribute("aui:calendar:afterHeaderRendererChange");
java.lang.Object afterHeaderTitleNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterHeaderTitleNodeChange");
java.lang.Object afterHeightChange = (java.lang.Object)request.getAttribute("aui:calendar:afterHeightChange");
java.lang.Object afterIconNextNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterIconNextNodeChange");
java.lang.Object afterIconPrevNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterIconPrevNodeChange");
java.lang.Object afterIdChange = (java.lang.Object)request.getAttribute("aui:calendar:afterIdChange");
java.lang.Object afterInit = (java.lang.Object)request.getAttribute("aui:calendar:afterInit");
java.lang.Object afterInitializedChange = (java.lang.Object)request.getAttribute("aui:calendar:afterInitializedChange");
java.lang.Object afterMaxDateChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMaxDateChange");
java.lang.Object afterMaximumDateChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMaximumDateChange");
java.lang.Object afterMinDateChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMinDateChange");
java.lang.Object afterMinimumDateChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMinimumDateChange");
java.lang.Object afterMonthDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMonthDaysChange");
java.lang.Object afterMonthDaysNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterMonthDaysNodeChange");
java.lang.Object afterNoneLinkNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterNoneLinkNodeChange");
java.lang.Object afterPaddingDaysEndChange = (java.lang.Object)request.getAttribute("aui:calendar:afterPaddingDaysEndChange");
java.lang.Object afterPaddingDaysStartChange = (java.lang.Object)request.getAttribute("aui:calendar:afterPaddingDaysStartChange");
java.lang.Object afterRenderChange = (java.lang.Object)request.getAttribute("aui:calendar:afterRenderChange");
java.lang.Object afterRenderedChange = (java.lang.Object)request.getAttribute("aui:calendar:afterRenderedChange");
java.lang.Object afterSelectMultipleDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:afterSelectMultipleDatesChange");
java.lang.Object afterSelectedDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:afterSelectedDatesChange");
java.lang.Object afterSelectionModeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterSelectionModeChange");
java.lang.Object afterSetValueChange = (java.lang.Object)request.getAttribute("aui:calendar:afterSetValueChange");
java.lang.Object afterShowNextMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:afterShowNextMonthChange");
java.lang.Object afterShowOtherMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:afterShowOtherMonthChange");
java.lang.Object afterShowPrevMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:afterShowPrevMonthChange");
java.lang.Object afterShowTodayChange = (java.lang.Object)request.getAttribute("aui:calendar:afterShowTodayChange");
java.lang.Object afterSrcNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterSrcNodeChange");
java.lang.Object afterStringsChange = (java.lang.Object)request.getAttribute("aui:calendar:afterStringsChange");
java.lang.Object afterTabIndexChange = (java.lang.Object)request.getAttribute("aui:calendar:afterTabIndexChange");
java.lang.Object afterTodayLinkNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterTodayLinkNodeChange");
java.lang.Object afterVisibleChange = (java.lang.Object)request.getAttribute("aui:calendar:afterVisibleChange");
java.lang.Object afterWeekDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:afterWeekDaysChange");
java.lang.Object afterWeekDaysNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:afterWeekDaysNodeChange");
java.lang.Object afterContentUpdate = (java.lang.Object)request.getAttribute("aui:calendar:afterContentUpdate");
java.lang.Object afterRender = (java.lang.Object)request.getAttribute("aui:calendar:afterRender");
java.lang.Object afterWidthChange = (java.lang.Object)request.getAttribute("aui:calendar:afterWidthChange");
java.lang.Object onAllowNoneChange = (java.lang.Object)request.getAttribute("aui:calendar:onAllowNoneChange");
java.lang.Object onBlankDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:onBlankDaysChange");
java.lang.Object onBoundingBoxChange = (java.lang.Object)request.getAttribute("aui:calendar:onBoundingBoxChange");
java.lang.Object onContentBoxChange = (java.lang.Object)request.getAttribute("aui:calendar:onContentBoxChange");
java.lang.Object onCurrentDayChange = (java.lang.Object)request.getAttribute("aui:calendar:onCurrentDayChange");
java.lang.Object onCurrentMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:onCurrentMonthChange");
java.lang.Object onCurrentYearChange = (java.lang.Object)request.getAttribute("aui:calendar:onCurrentYearChange");
java.lang.Object onCustomRendererChange = (java.lang.Object)request.getAttribute("aui:calendar:onCustomRendererChange");
java.lang.Object onDateChange = (java.lang.Object)request.getAttribute("aui:calendar:onDateChange");
java.lang.Object onDateFormatChange = (java.lang.Object)request.getAttribute("aui:calendar:onDateFormatChange");
java.lang.Object onDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:onDatesChange");
java.lang.Object onDestroy = (java.lang.Object)request.getAttribute("aui:calendar:onDestroy");
java.lang.Object onDestroyedChange = (java.lang.Object)request.getAttribute("aui:calendar:onDestroyedChange");
java.lang.Object onDisabledChange = (java.lang.Object)request.getAttribute("aui:calendar:onDisabledChange");
java.lang.Object onDisabledDatesRuleChange = (java.lang.Object)request.getAttribute("aui:calendar:onDisabledDatesRuleChange");
java.lang.Object onEnabledDatesRuleChange = (java.lang.Object)request.getAttribute("aui:calendar:onEnabledDatesRuleChange");
java.lang.Object onFirstDayOfWeekChange = (java.lang.Object)request.getAttribute("aui:calendar:onFirstDayOfWeekChange");
java.lang.Object onFocusedChange = (java.lang.Object)request.getAttribute("aui:calendar:onFocusedChange");
java.lang.Object onHeaderContentNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onHeaderContentNodeChange");
java.lang.Object onHeaderRendererChange = (java.lang.Object)request.getAttribute("aui:calendar:onHeaderRendererChange");
java.lang.Object onHeaderTitleNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onHeaderTitleNodeChange");
java.lang.Object onHeightChange = (java.lang.Object)request.getAttribute("aui:calendar:onHeightChange");
java.lang.Object onIconNextNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onIconNextNodeChange");
java.lang.Object onIconPrevNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onIconPrevNodeChange");
java.lang.Object onIdChange = (java.lang.Object)request.getAttribute("aui:calendar:onIdChange");
java.lang.Object onInit = (java.lang.Object)request.getAttribute("aui:calendar:onInit");
java.lang.Object onInitializedChange = (java.lang.Object)request.getAttribute("aui:calendar:onInitializedChange");
java.lang.Object onMaxDateChange = (java.lang.Object)request.getAttribute("aui:calendar:onMaxDateChange");
java.lang.Object onMaximumDateChange = (java.lang.Object)request.getAttribute("aui:calendar:onMaximumDateChange");
java.lang.Object onMinDateChange = (java.lang.Object)request.getAttribute("aui:calendar:onMinDateChange");
java.lang.Object onMinimumDateChange = (java.lang.Object)request.getAttribute("aui:calendar:onMinimumDateChange");
java.lang.Object onMonthDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:onMonthDaysChange");
java.lang.Object onMonthDaysNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onMonthDaysNodeChange");
java.lang.Object onNoneLinkNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onNoneLinkNodeChange");
java.lang.Object onPaddingDaysEndChange = (java.lang.Object)request.getAttribute("aui:calendar:onPaddingDaysEndChange");
java.lang.Object onPaddingDaysStartChange = (java.lang.Object)request.getAttribute("aui:calendar:onPaddingDaysStartChange");
java.lang.Object onRenderChange = (java.lang.Object)request.getAttribute("aui:calendar:onRenderChange");
java.lang.Object onRenderedChange = (java.lang.Object)request.getAttribute("aui:calendar:onRenderedChange");
java.lang.Object onSelectMultipleDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:onSelectMultipleDatesChange");
java.lang.Object onSelectedDatesChange = (java.lang.Object)request.getAttribute("aui:calendar:onSelectedDatesChange");
java.lang.Object onSelectionModeChange = (java.lang.Object)request.getAttribute("aui:calendar:onSelectionModeChange");
java.lang.Object onSetValueChange = (java.lang.Object)request.getAttribute("aui:calendar:onSetValueChange");
java.lang.Object onShowNextMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:onShowNextMonthChange");
java.lang.Object onShowOtherMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:onShowOtherMonthChange");
java.lang.Object onShowPrevMonthChange = (java.lang.Object)request.getAttribute("aui:calendar:onShowPrevMonthChange");
java.lang.Object onShowTodayChange = (java.lang.Object)request.getAttribute("aui:calendar:onShowTodayChange");
java.lang.Object onSrcNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onSrcNodeChange");
java.lang.Object onStringsChange = (java.lang.Object)request.getAttribute("aui:calendar:onStringsChange");
java.lang.Object onTabIndexChange = (java.lang.Object)request.getAttribute("aui:calendar:onTabIndexChange");
java.lang.Object onTodayLinkNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onTodayLinkNodeChange");
java.lang.Object onVisibleChange = (java.lang.Object)request.getAttribute("aui:calendar:onVisibleChange");
java.lang.Object onWeekDaysChange = (java.lang.Object)request.getAttribute("aui:calendar:onWeekDaysChange");
java.lang.Object onWeekDaysNodeChange = (java.lang.Object)request.getAttribute("aui:calendar:onWeekDaysNodeChange");
java.lang.Object onContentUpdate = (java.lang.Object)request.getAttribute("aui:calendar:onContentUpdate");
java.lang.Object onRender = (java.lang.Object)request.getAttribute("aui:calendar:onRender");
java.lang.Object onWidthChange = (java.lang.Object)request.getAttribute("aui:calendar:onWidthChange");

_updateOptions(_options, "allowNone", allowNone);
_updateOptions(_options, "blankDays", blankDays);
_updateOptions(_options, "boundingBox", boundingBox);
_updateOptions(_options, "contentBox", contentBox);
_updateOptions(_options, "currentDay", currentDay);
_updateOptions(_options, "currentMonth", currentMonth);
_updateOptions(_options, "currentYear", currentYear);
_updateOptions(_options, "customRenderer", customRenderer);
_updateOptions(_options, "date", date);
_updateOptions(_options, "dateFormat", dateFormat);
_updateOptions(_options, "dates", dates);
_updateOptions(_options, "destroyed", destroyed);
_updateOptions(_options, "disabled", disabled);
_updateOptions(_options, "disabledDatesRule", disabledDatesRule);
_updateOptions(_options, "enabledDatesRule", enabledDatesRule);
_updateOptions(_options, "firstDayOfWeek", firstDayOfWeek);
_updateOptions(_options, "focused", focused);
_updateOptions(_options, "headerContentNode", headerContentNode);
_updateOptions(_options, "headerRenderer", headerRenderer);
_updateOptions(_options, "headerTitleNode", headerTitleNode);
_updateOptions(_options, "height", height);
_updateOptions(_options, "iconNextNode", iconNextNode);
_updateOptions(_options, "iconPrevNode", iconPrevNode);
_updateOptions(_options, "calendarId", calendarId);
_updateOptions(_options, "initialized", initialized);
_updateOptions(_options, "maxDate", maxDate);
_updateOptions(_options, "maximumDate", maximumDate);
_updateOptions(_options, "minDate", minDate);
_updateOptions(_options, "minimumDate", minimumDate);
_updateOptions(_options, "monthDays", monthDays);
_updateOptions(_options, "monthDaysNode", monthDaysNode);
_updateOptions(_options, "noneLinkNode", noneLinkNode);
_updateOptions(_options, "paddingDaysEnd", paddingDaysEnd);
_updateOptions(_options, "paddingDaysStart", paddingDaysStart);
_updateOptions(_options, "render", render);
_updateOptions(_options, "rendered", rendered);
_updateOptions(_options, "selectMultipleDates", selectMultipleDates);
_updateOptions(_options, "selectedDates", selectedDates);
_updateOptions(_options, "selectionMode", selectionMode);
_updateOptions(_options, "setValue", setValue);
_updateOptions(_options, "showNextMonth", showNextMonth);
_updateOptions(_options, "showOtherMonth", showOtherMonth);
_updateOptions(_options, "showPrevMonth", showPrevMonth);
_updateOptions(_options, "showToday", showToday);
_updateOptions(_options, "srcNode", srcNode);
_updateOptions(_options, "strings", strings);
_updateOptions(_options, "tabIndex", tabIndex);
_updateOptions(_options, "todayLinkNode", todayLinkNode);
_updateOptions(_options, "visible", visible);
_updateOptions(_options, "weekDays", weekDays);
_updateOptions(_options, "weekDaysNode", weekDaysNode);
_updateOptions(_options, "width", width);
_updateOptions(_options, "afterAllowNoneChange", afterAllowNoneChange);
_updateOptions(_options, "afterBlankDaysChange", afterBlankDaysChange);
_updateOptions(_options, "afterBoundingBoxChange", afterBoundingBoxChange);
_updateOptions(_options, "afterContentBoxChange", afterContentBoxChange);
_updateOptions(_options, "afterCurrentDayChange", afterCurrentDayChange);
_updateOptions(_options, "afterCurrentMonthChange", afterCurrentMonthChange);
_updateOptions(_options, "afterCurrentYearChange", afterCurrentYearChange);
_updateOptions(_options, "afterCustomRendererChange", afterCustomRendererChange);
_updateOptions(_options, "afterDateChange", afterDateChange);
_updateOptions(_options, "afterDateFormatChange", afterDateFormatChange);
_updateOptions(_options, "afterDatesChange", afterDatesChange);
_updateOptions(_options, "afterDestroy", afterDestroy);
_updateOptions(_options, "afterDestroyedChange", afterDestroyedChange);
_updateOptions(_options, "afterDisabledChange", afterDisabledChange);
_updateOptions(_options, "afterDisabledDatesRuleChange", afterDisabledDatesRuleChange);
_updateOptions(_options, "afterEnabledDatesRuleChange", afterEnabledDatesRuleChange);
_updateOptions(_options, "afterFirstDayOfWeekChange", afterFirstDayOfWeekChange);
_updateOptions(_options, "afterFocusedChange", afterFocusedChange);
_updateOptions(_options, "afterHeaderContentNodeChange", afterHeaderContentNodeChange);
_updateOptions(_options, "afterHeaderRendererChange", afterHeaderRendererChange);
_updateOptions(_options, "afterHeaderTitleNodeChange", afterHeaderTitleNodeChange);
_updateOptions(_options, "afterHeightChange", afterHeightChange);
_updateOptions(_options, "afterIconNextNodeChange", afterIconNextNodeChange);
_updateOptions(_options, "afterIconPrevNodeChange", afterIconPrevNodeChange);
_updateOptions(_options, "afterIdChange", afterIdChange);
_updateOptions(_options, "afterInit", afterInit);
_updateOptions(_options, "afterInitializedChange", afterInitializedChange);
_updateOptions(_options, "afterMaxDateChange", afterMaxDateChange);
_updateOptions(_options, "afterMaximumDateChange", afterMaximumDateChange);
_updateOptions(_options, "afterMinDateChange", afterMinDateChange);
_updateOptions(_options, "afterMinimumDateChange", afterMinimumDateChange);
_updateOptions(_options, "afterMonthDaysChange", afterMonthDaysChange);
_updateOptions(_options, "afterMonthDaysNodeChange", afterMonthDaysNodeChange);
_updateOptions(_options, "afterNoneLinkNodeChange", afterNoneLinkNodeChange);
_updateOptions(_options, "afterPaddingDaysEndChange", afterPaddingDaysEndChange);
_updateOptions(_options, "afterPaddingDaysStartChange", afterPaddingDaysStartChange);
_updateOptions(_options, "afterRenderChange", afterRenderChange);
_updateOptions(_options, "afterRenderedChange", afterRenderedChange);
_updateOptions(_options, "afterSelectMultipleDatesChange", afterSelectMultipleDatesChange);
_updateOptions(_options, "afterSelectedDatesChange", afterSelectedDatesChange);
_updateOptions(_options, "afterSelectionModeChange", afterSelectionModeChange);
_updateOptions(_options, "afterSetValueChange", afterSetValueChange);
_updateOptions(_options, "afterShowNextMonthChange", afterShowNextMonthChange);
_updateOptions(_options, "afterShowOtherMonthChange", afterShowOtherMonthChange);
_updateOptions(_options, "afterShowPrevMonthChange", afterShowPrevMonthChange);
_updateOptions(_options, "afterShowTodayChange", afterShowTodayChange);
_updateOptions(_options, "afterSrcNodeChange", afterSrcNodeChange);
_updateOptions(_options, "afterStringsChange", afterStringsChange);
_updateOptions(_options, "afterTabIndexChange", afterTabIndexChange);
_updateOptions(_options, "afterTodayLinkNodeChange", afterTodayLinkNodeChange);
_updateOptions(_options, "afterVisibleChange", afterVisibleChange);
_updateOptions(_options, "afterWeekDaysChange", afterWeekDaysChange);
_updateOptions(_options, "afterWeekDaysNodeChange", afterWeekDaysNodeChange);
_updateOptions(_options, "afterContentUpdate", afterContentUpdate);
_updateOptions(_options, "afterRender", afterRender);
_updateOptions(_options, "afterWidthChange", afterWidthChange);
_updateOptions(_options, "onAllowNoneChange", onAllowNoneChange);
_updateOptions(_options, "onBlankDaysChange", onBlankDaysChange);
_updateOptions(_options, "onBoundingBoxChange", onBoundingBoxChange);
_updateOptions(_options, "onContentBoxChange", onContentBoxChange);
_updateOptions(_options, "onCurrentDayChange", onCurrentDayChange);
_updateOptions(_options, "onCurrentMonthChange", onCurrentMonthChange);
_updateOptions(_options, "onCurrentYearChange", onCurrentYearChange);
_updateOptions(_options, "onCustomRendererChange", onCustomRendererChange);
_updateOptions(_options, "onDateChange", onDateChange);
_updateOptions(_options, "onDateFormatChange", onDateFormatChange);
_updateOptions(_options, "onDatesChange", onDatesChange);
_updateOptions(_options, "onDestroy", onDestroy);
_updateOptions(_options, "onDestroyedChange", onDestroyedChange);
_updateOptions(_options, "onDisabledChange", onDisabledChange);
_updateOptions(_options, "onDisabledDatesRuleChange", onDisabledDatesRuleChange);
_updateOptions(_options, "onEnabledDatesRuleChange", onEnabledDatesRuleChange);
_updateOptions(_options, "onFirstDayOfWeekChange", onFirstDayOfWeekChange);
_updateOptions(_options, "onFocusedChange", onFocusedChange);
_updateOptions(_options, "onHeaderContentNodeChange", onHeaderContentNodeChange);
_updateOptions(_options, "onHeaderRendererChange", onHeaderRendererChange);
_updateOptions(_options, "onHeaderTitleNodeChange", onHeaderTitleNodeChange);
_updateOptions(_options, "onHeightChange", onHeightChange);
_updateOptions(_options, "onIconNextNodeChange", onIconNextNodeChange);
_updateOptions(_options, "onIconPrevNodeChange", onIconPrevNodeChange);
_updateOptions(_options, "onIdChange", onIdChange);
_updateOptions(_options, "onInit", onInit);
_updateOptions(_options, "onInitializedChange", onInitializedChange);
_updateOptions(_options, "onMaxDateChange", onMaxDateChange);
_updateOptions(_options, "onMaximumDateChange", onMaximumDateChange);
_updateOptions(_options, "onMinDateChange", onMinDateChange);
_updateOptions(_options, "onMinimumDateChange", onMinimumDateChange);
_updateOptions(_options, "onMonthDaysChange", onMonthDaysChange);
_updateOptions(_options, "onMonthDaysNodeChange", onMonthDaysNodeChange);
_updateOptions(_options, "onNoneLinkNodeChange", onNoneLinkNodeChange);
_updateOptions(_options, "onPaddingDaysEndChange", onPaddingDaysEndChange);
_updateOptions(_options, "onPaddingDaysStartChange", onPaddingDaysStartChange);
_updateOptions(_options, "onRenderChange", onRenderChange);
_updateOptions(_options, "onRenderedChange", onRenderedChange);
_updateOptions(_options, "onSelectMultipleDatesChange", onSelectMultipleDatesChange);
_updateOptions(_options, "onSelectedDatesChange", onSelectedDatesChange);
_updateOptions(_options, "onSelectionModeChange", onSelectionModeChange);
_updateOptions(_options, "onSetValueChange", onSetValueChange);
_updateOptions(_options, "onShowNextMonthChange", onShowNextMonthChange);
_updateOptions(_options, "onShowOtherMonthChange", onShowOtherMonthChange);
_updateOptions(_options, "onShowPrevMonthChange", onShowPrevMonthChange);
_updateOptions(_options, "onShowTodayChange", onShowTodayChange);
_updateOptions(_options, "onSrcNodeChange", onSrcNodeChange);
_updateOptions(_options, "onStringsChange", onStringsChange);
_updateOptions(_options, "onTabIndexChange", onTabIndexChange);
_updateOptions(_options, "onTodayLinkNodeChange", onTodayLinkNodeChange);
_updateOptions(_options, "onVisibleChange", onVisibleChange);
_updateOptions(_options, "onWeekDaysChange", onWeekDaysChange);
_updateOptions(_options, "onWeekDaysNodeChange", onWeekDaysNodeChange);
_updateOptions(_options, "onContentUpdate", onContentUpdate);
_updateOptions(_options, "onRender", onRender);
_updateOptions(_options, "onWidthChange", onWidthChange);
%>

<%@ include file="/html/taglib/aui/calendar/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:calendar:";
%>