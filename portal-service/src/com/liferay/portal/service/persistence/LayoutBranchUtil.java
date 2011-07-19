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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the layout branch service. This utility wraps {@link LayoutBranchPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchPersistence
 * @see LayoutBranchPersistenceImpl
 * @generated
 */
public class LayoutBranchUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(LayoutBranch layoutBranch) {
		getPersistence().clearCache(layoutBranch);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LayoutBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LayoutBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LayoutBranch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static LayoutBranch remove(LayoutBranch layoutBranch)
		throws SystemException {
		return getPersistence().remove(layoutBranch);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static LayoutBranch update(LayoutBranch layoutBranch, boolean merge)
		throws SystemException {
		return getPersistence().update(layoutBranch, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static LayoutBranch update(LayoutBranch layoutBranch, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(layoutBranch, merge, serviceContext);
	}

	/**
	* Caches the layout branch in the entity cache if it is enabled.
	*
	* @param layoutBranch the layout branch
	*/
	public static void cacheResult(
		com.liferay.portal.model.LayoutBranch layoutBranch) {
		getPersistence().cacheResult(layoutBranch);
	}

	/**
	* Caches the layout branchs in the entity cache if it is enabled.
	*
	* @param layoutBranchs the layout branchs
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.LayoutBranch> layoutBranchs) {
		getPersistence().cacheResult(layoutBranchs);
	}

	/**
	* Creates a new layout branch with the primary key. Does not add the layout branch to the database.
	*
	* @param LayoutBranchId the primary key for the new layout branch
	* @return the new layout branch
	*/
	public static com.liferay.portal.model.LayoutBranch create(
		long LayoutBranchId) {
		return getPersistence().create(LayoutBranchId);
	}

	/**
	* Removes the layout branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param LayoutBranchId the primary key of the layout branch
	* @return the layout branch that was removed
	* @throws com.liferay.portal.NoSuchLayoutBranchException if a layout branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutBranch remove(
		long LayoutBranchId)
		throws com.liferay.portal.NoSuchLayoutBranchException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(LayoutBranchId);
	}

	public static com.liferay.portal.model.LayoutBranch updateImpl(
		com.liferay.portal.model.LayoutBranch layoutBranch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(layoutBranch, merge);
	}

	/**
	* Returns the layout branch with the primary key or throws a {@link com.liferay.portal.NoSuchLayoutBranchException} if it could not be found.
	*
	* @param LayoutBranchId the primary key of the layout branch
	* @return the layout branch
	* @throws com.liferay.portal.NoSuchLayoutBranchException if a layout branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutBranch findByPrimaryKey(
		long LayoutBranchId)
		throws com.liferay.portal.NoSuchLayoutBranchException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(LayoutBranchId);
	}

	/**
	* Returns the layout branch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param LayoutBranchId the primary key of the layout branch
	* @return the layout branch, or <code>null</code> if a layout branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutBranch fetchByPrimaryKey(
		long LayoutBranchId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(LayoutBranchId);
	}

	/**
	* Returns all the layout branchs.
	*
	* @return the layout branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutBranch> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the layout branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout branchs
	* @param end the upper bound of the range of layout branchs (not inclusive)
	* @return the range of layout branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutBranch> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the layout branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout branchs
	* @param end the upper bound of the range of layout branchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of layout branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutBranch> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the layout branchs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of layout branchs.
	*
	* @return the number of layout branchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LayoutBranchPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LayoutBranchPersistence)PortalBeanLocatorUtil.locate(LayoutBranchPersistence.class.getName());

			ReferenceRegistry.registerReference(LayoutBranchUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(LayoutBranchPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(LayoutBranchUtil.class,
			"_persistence");
	}

	private static LayoutBranchPersistence _persistence;
}