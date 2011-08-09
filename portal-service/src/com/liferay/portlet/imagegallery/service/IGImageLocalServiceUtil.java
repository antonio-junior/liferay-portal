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

package com.liferay.portlet.imagegallery.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the i g image local service. This utility wraps {@link com.liferay.portlet.imagegallery.service.impl.IGImageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IGImageLocalService
 * @see com.liferay.portlet.imagegallery.service.base.IGImageLocalServiceBaseImpl
 * @see com.liferay.portlet.imagegallery.service.impl.IGImageLocalServiceImpl
 * @generated
 */
public class IGImageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.imagegallery.service.impl.IGImageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the i g image to the database. Also notifies the appropriate model listeners.
	*
	* @param igImage the i g image
	* @return the i g image that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage addIGImage(
		com.liferay.portlet.imagegallery.model.IGImage igImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addIGImage(igImage);
	}

	/**
	* Creates a new i g image with the primary key. Does not add the i g image to the database.
	*
	* @param imageId the primary key for the new i g image
	* @return the new i g image
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage createIGImage(
		long imageId) {
		return getService().createIGImage(imageId);
	}

	/**
	* Deletes the i g image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imageId the primary key of the i g image
	* @throws PortalException if a i g image with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIGImage(long imageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteIGImage(imageId);
	}

	/**
	* Deletes the i g image from the database. Also notifies the appropriate model listeners.
	*
	* @param igImage the i g image
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIGImage(
		com.liferay.portlet.imagegallery.model.IGImage igImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteIGImage(igImage);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the i g image with the primary key.
	*
	* @param imageId the primary key of the i g image
	* @return the i g image
	* @throws PortalException if a i g image with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage getIGImage(
		long imageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getIGImage(imageId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the i g image with the UUID in the group.
	*
	* @param uuid the UUID of i g image
	* @param groupId the group id of the i g image
	* @return the i g image
	* @throws PortalException if a i g image with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage getIGImageByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getIGImageByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the i g images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of i g images
	* @param end the upper bound of the range of i g images (not inclusive)
	* @return the range of i g images
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.imagegallery.model.IGImage> getIGImages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getIGImages(start, end);
	}

	/**
	* Returns the number of i g images.
	*
	* @return the number of i g images
	* @throws SystemException if a system exception occurred
	*/
	public static int getIGImagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getIGImagesCount();
	}

	/**
	* Updates the i g image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param igImage the i g image
	* @return the i g image that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage updateIGImage(
		com.liferay.portlet.imagegallery.model.IGImage igImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateIGImage(igImage);
	}

	/**
	* Updates the i g image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param igImage the i g image
	* @param merge whether to merge the i g image with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the i g image that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.imagegallery.model.IGImage updateIGImage(
		com.liferay.portlet.imagegallery.model.IGImage igImage, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateIGImage(igImage, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.imagegallery.model.IGImage getImage(
		long imageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getImage(imageId);
	}

	public static IGImageLocalService getService() {
		if (_service == null) {
			_service = (IGImageLocalService)PortalBeanLocatorUtil.locate(IGImageLocalService.class.getName());

			ReferenceRegistry.registerReference(IGImageLocalServiceUtil.class,
				"_service");
			MethodCache.remove(IGImageLocalService.class);
		}

		return _service;
	}

	public void setService(IGImageLocalService service) {
		MethodCache.remove(IGImageLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(IGImageLocalServiceUtil.class,
			"_service");
		MethodCache.remove(IGImageLocalService.class);
	}

	private static IGImageLocalService _service;
}