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

package com.liferay.portlet.imagegallery.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.ImageLocalService;
import com.liferay.portal.service.ImageService;
import com.liferay.portal.service.LayoutLocalService;
import com.liferay.portal.service.LayoutService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.LayoutFinder;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.expando.service.ExpandoValueLocalService;
import com.liferay.portlet.expando.service.ExpandoValueService;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;
import com.liferay.portlet.imagegallery.model.IGFolder;
import com.liferay.portlet.imagegallery.service.IGFolderLocalService;
import com.liferay.portlet.imagegallery.service.IGFolderService;
import com.liferay.portlet.imagegallery.service.IGImageLocalService;
import com.liferay.portlet.imagegallery.service.IGImageService;
import com.liferay.portlet.imagegallery.service.persistence.IGFolderPersistence;
import com.liferay.portlet.imagegallery.service.persistence.IGImageFinder;
import com.liferay.portlet.imagegallery.service.persistence.IGImagePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the i g folder local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.imagegallery.service.impl.IGFolderLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.imagegallery.service.impl.IGFolderLocalServiceImpl
 * @see com.liferay.portlet.imagegallery.service.IGFolderLocalServiceUtil
 * @generated
 */
public abstract class IGFolderLocalServiceBaseImpl
	implements IGFolderLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.imagegallery.service.IGFolderLocalServiceUtil} to access the i g folder local service.
	 */

	/**
	 * Adds the i g folder to the database. Also notifies the appropriate model listeners.
	 *
	 * @param igFolder the i g folder
	 * @return the i g folder that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IGFolder addIGFolder(IGFolder igFolder) throws SystemException {
		igFolder.setNew(true);

		igFolder = igFolderPersistence.update(igFolder, false);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(igFolder);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return igFolder;
	}

	/**
	 * Creates a new i g folder with the primary key. Does not add the i g folder to the database.
	 *
	 * @param folderId the primary key for the new i g folder
	 * @return the new i g folder
	 */
	public IGFolder createIGFolder(long folderId) {
		return igFolderPersistence.create(folderId);
	}

	/**
	 * Deletes the i g folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the i g folder
	 * @throws PortalException if a i g folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIGFolder(long folderId)
		throws PortalException, SystemException {
		IGFolder igFolder = igFolderPersistence.remove(folderId);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(igFolder);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Deletes the i g folder from the database. Also notifies the appropriate model listeners.
	 *
	 * @param igFolder the i g folder
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIGFolder(IGFolder igFolder) throws SystemException {
		igFolderPersistence.remove(igFolder);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(igFolder);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return igFolderPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return igFolderPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return igFolderPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return igFolderPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the i g folder with the primary key.
	 *
	 * @param folderId the primary key of the i g folder
	 * @return the i g folder
	 * @throws PortalException if a i g folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IGFolder getIGFolder(long folderId)
		throws PortalException, SystemException {
		return igFolderPersistence.findByPrimaryKey(folderId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return igFolderPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the i g folder with the UUID in the group.
	 *
	 * @param uuid the UUID of i g folder
	 * @param groupId the group id of the i g folder
	 * @return the i g folder
	 * @throws PortalException if a i g folder with the UUID in the group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IGFolder getIGFolderByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return igFolderPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the i g folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of i g folders
	 * @param end the upper bound of the range of i g folders (not inclusive)
	 * @return the range of i g folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IGFolder> getIGFolders(int start, int end)
		throws SystemException {
		return igFolderPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of i g folders.
	 *
	 * @return the number of i g folders
	 * @throws SystemException if a system exception occurred
	 */
	public int getIGFoldersCount() throws SystemException {
		return igFolderPersistence.countAll();
	}

	/**
	 * Updates the i g folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param igFolder the i g folder
	 * @return the i g folder that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IGFolder updateIGFolder(IGFolder igFolder) throws SystemException {
		return updateIGFolder(igFolder, true);
	}

	/**
	 * Updates the i g folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param igFolder the i g folder
	 * @param merge whether to merge the i g folder with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the i g folder that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IGFolder updateIGFolder(IGFolder igFolder, boolean merge)
		throws SystemException {
		igFolder.setNew(false);

		igFolder = igFolderPersistence.update(igFolder, merge);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(igFolder);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return igFolder;
	}

	/**
	 * Returns the i g folder local service.
	 *
	 * @return the i g folder local service
	 */
	public IGFolderLocalService getIGFolderLocalService() {
		return igFolderLocalService;
	}

	/**
	 * Sets the i g folder local service.
	 *
	 * @param igFolderLocalService the i g folder local service
	 */
	public void setIGFolderLocalService(
		IGFolderLocalService igFolderLocalService) {
		this.igFolderLocalService = igFolderLocalService;
	}

	/**
	 * Returns the i g folder remote service.
	 *
	 * @return the i g folder remote service
	 */
	public IGFolderService getIGFolderService() {
		return igFolderService;
	}

	/**
	 * Sets the i g folder remote service.
	 *
	 * @param igFolderService the i g folder remote service
	 */
	public void setIGFolderService(IGFolderService igFolderService) {
		this.igFolderService = igFolderService;
	}

	/**
	 * Returns the i g folder persistence.
	 *
	 * @return the i g folder persistence
	 */
	public IGFolderPersistence getIGFolderPersistence() {
		return igFolderPersistence;
	}

	/**
	 * Sets the i g folder persistence.
	 *
	 * @param igFolderPersistence the i g folder persistence
	 */
	public void setIGFolderPersistence(IGFolderPersistence igFolderPersistence) {
		this.igFolderPersistence = igFolderPersistence;
	}

	/**
	 * Returns the i g image local service.
	 *
	 * @return the i g image local service
	 */
	public IGImageLocalService getIGImageLocalService() {
		return igImageLocalService;
	}

	/**
	 * Sets the i g image local service.
	 *
	 * @param igImageLocalService the i g image local service
	 */
	public void setIGImageLocalService(IGImageLocalService igImageLocalService) {
		this.igImageLocalService = igImageLocalService;
	}

	/**
	 * Returns the i g image remote service.
	 *
	 * @return the i g image remote service
	 */
	public IGImageService getIGImageService() {
		return igImageService;
	}

	/**
	 * Sets the i g image remote service.
	 *
	 * @param igImageService the i g image remote service
	 */
	public void setIGImageService(IGImageService igImageService) {
		this.igImageService = igImageService;
	}

	/**
	 * Returns the i g image persistence.
	 *
	 * @return the i g image persistence
	 */
	public IGImagePersistence getIGImagePersistence() {
		return igImagePersistence;
	}

	/**
	 * Sets the i g image persistence.
	 *
	 * @param igImagePersistence the i g image persistence
	 */
	public void setIGImagePersistence(IGImagePersistence igImagePersistence) {
		this.igImagePersistence = igImagePersistence;
	}

	/**
	 * Returns the i g image finder.
	 *
	 * @return the i g image finder
	 */
	public IGImageFinder getIGImageFinder() {
		return igImageFinder;
	}

	/**
	 * Sets the i g image finder.
	 *
	 * @param igImageFinder the i g image finder
	 */
	public void setIGImageFinder(IGImageFinder igImageFinder) {
		this.igImageFinder = igImageFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image remote service.
	 *
	 * @return the image remote service
	 */
	public ImageService getImageService() {
		return imageService;
	}

	/**
	 * Sets the image remote service.
	 *
	 * @param imageService the image remote service
	 */
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout remote service.
	 *
	 * @return the layout remote service
	 */
	public LayoutService getLayoutService() {
		return layoutService;
	}

	/**
	 * Sets the layout remote service.
	 *
	 * @param layoutService the layout remote service
	 */
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the layout finder.
	 *
	 * @return the layout finder
	 */
	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	/**
	 * Sets the layout finder.
	 *
	 * @param layoutFinder the layout finder
	 */
	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the resource finder.
	 *
	 * @return the resource finder
	 */
	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	/**
	 * Sets the resource finder.
	 *
	 * @param resourceFinder the resource finder
	 */
	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the expando value local service.
	 *
	 * @return the expando value local service
	 */
	public ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	/**
	 * Sets the expando value local service.
	 *
	 * @param expandoValueLocalService the expando value local service
	 */
	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	/**
	 * Returns the expando value remote service.
	 *
	 * @return the expando value remote service
	 */
	public ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	/**
	 * Sets the expando value remote service.
	 *
	 * @param expandoValueService the expando value remote service
	 */
	public void setExpandoValueService(ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	/**
	 * Returns the expando value persistence.
	 *
	 * @return the expando value persistence
	 */
	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	/**
	 * Sets the expando value persistence.
	 *
	 * @param expandoValuePersistence the expando value persistence
	 */
	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.imagegallery.model.IGFolder",
			igFolderLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.imagegallery.model.IGFolder");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return IGFolder.class;
	}

	protected String getModelClassName() {
		return IGFolder.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = igFolderPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = IGFolderLocalService.class)
	protected IGFolderLocalService igFolderLocalService;
	@BeanReference(type = IGFolderService.class)
	protected IGFolderService igFolderService;
	@BeanReference(type = IGFolderPersistence.class)
	protected IGFolderPersistence igFolderPersistence;
	@BeanReference(type = IGImageLocalService.class)
	protected IGImageLocalService igImageLocalService;
	@BeanReference(type = IGImageService.class)
	protected IGImageService igImageService;
	@BeanReference(type = IGImagePersistence.class)
	protected IGImagePersistence igImagePersistence;
	@BeanReference(type = IGImageFinder.class)
	protected IGImageFinder igImageFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;
	@BeanReference(type = GroupService.class)
	protected GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = ImageLocalService.class)
	protected ImageLocalService imageLocalService;
	@BeanReference(type = ImageService.class)
	protected ImageService imageService;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = LayoutLocalService.class)
	protected LayoutLocalService layoutLocalService;
	@BeanReference(type = LayoutService.class)
	protected LayoutService layoutService;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutFinder.class)
	protected LayoutFinder layoutFinder;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = ExpandoValueLocalService.class)
	protected ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(type = ExpandoValueService.class)
	protected ExpandoValueService expandoValueService;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private static Log _log = LogFactoryUtil.getLog(IGFolderLocalServiceBaseImpl.class);
	private String _beanIdentifier;
}