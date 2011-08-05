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

package com.liferay.portlet.mobiledevicerules.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.mobiledevicerules.service.http.MDRActionServiceSoap}.
 *
 * @author    Edward C. Han
 * @see       com.liferay.portlet.mobiledevicerules.service.http.MDRActionServiceSoap
 * @generated
 */
public class MDRActionSoap implements Serializable {
	public static MDRActionSoap toSoapModel(MDRAction model) {
		MDRActionSoap soapModel = new MDRActionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActionId(model.getActionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRuleGroupId(model.getRuleGroupId());
		soapModel.setRuleId(model.getRuleId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());
		soapModel.setTypeSettings(model.getTypeSettings());

		return soapModel;
	}

	public static MDRActionSoap[] toSoapModels(MDRAction[] models) {
		MDRActionSoap[] soapModels = new MDRActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MDRActionSoap[][] toSoapModels(MDRAction[][] models) {
		MDRActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MDRActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MDRActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MDRActionSoap[] toSoapModels(List<MDRAction> models) {
		List<MDRActionSoap> soapModels = new ArrayList<MDRActionSoap>(models.size());

		for (MDRAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MDRActionSoap[soapModels.size()]);
	}

	public MDRActionSoap() {
	}

	public long getPrimaryKey() {
		return _actionId;
	}

	public void setPrimaryKey(long pk) {
		setActionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActionId() {
		return _actionId;
	}

	public void setActionId(long actionId) {
		_actionId = actionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getRuleGroupId() {
		return _ruleGroupId;
	}

	public void setRuleGroupId(long ruleGroupId) {
		_ruleGroupId = ruleGroupId;
	}

	public long getRuleId() {
		return _ruleId;
	}

	public void setRuleId(long ruleId) {
		_ruleId = ruleId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	private String _uuid;
	private long _actionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ruleGroupId;
	private long _ruleId;
	private String _name;
	private String _description;
	private String _type;
	private String _typeSettings;
}