/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.asset.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.test.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;

import com.liferay.portlet.asset.NoSuchTagException;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.model.impl.AssetTagModelImpl;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class AssetTagPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<AssetTag> modelListener : _modelListeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();

		for (ModelListener<AssetTag> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetTag assetTag = _persistence.create(pk);

		Assert.assertNotNull(assetTag);

		Assert.assertEquals(assetTag.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetTag newAssetTag = addAssetTag();

		_persistence.remove(newAssetTag);

		AssetTag existingAssetTag = _persistence.fetchByPrimaryKey(newAssetTag.getPrimaryKey());

		Assert.assertNull(existingAssetTag);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetTag();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetTag newAssetTag = _persistence.create(pk);

		newAssetTag.setGroupId(RandomTestUtil.nextLong());

		newAssetTag.setCompanyId(RandomTestUtil.nextLong());

		newAssetTag.setUserId(RandomTestUtil.nextLong());

		newAssetTag.setUserName(RandomTestUtil.randomString());

		newAssetTag.setCreateDate(RandomTestUtil.nextDate());

		newAssetTag.setModifiedDate(RandomTestUtil.nextDate());

		newAssetTag.setName(RandomTestUtil.randomString());

		newAssetTag.setAssetCount(RandomTestUtil.nextInt());

		_persistence.update(newAssetTag);

		AssetTag existingAssetTag = _persistence.findByPrimaryKey(newAssetTag.getPrimaryKey());

		Assert.assertEquals(existingAssetTag.getTagId(), newAssetTag.getTagId());
		Assert.assertEquals(existingAssetTag.getGroupId(),
			newAssetTag.getGroupId());
		Assert.assertEquals(existingAssetTag.getCompanyId(),
			newAssetTag.getCompanyId());
		Assert.assertEquals(existingAssetTag.getUserId(),
			newAssetTag.getUserId());
		Assert.assertEquals(existingAssetTag.getUserName(),
			newAssetTag.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetTag.getCreateDate()),
			Time.getShortTimestamp(newAssetTag.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAssetTag.getModifiedDate()),
			Time.getShortTimestamp(newAssetTag.getModifiedDate()));
		Assert.assertEquals(existingAssetTag.getName(), newAssetTag.getName());
		Assert.assertEquals(existingAssetTag.getAssetCount(),
			newAssetTag.getAssetCount());
	}

	@Test
	public void testCountByGroupId() {
		try {
			_persistence.countByGroupId(RandomTestUtil.nextLong());

			_persistence.countByGroupId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_N() {
		try {
			_persistence.countByG_N(RandomTestUtil.nextLong(), StringPool.BLANK);

			_persistence.countByG_N(0L, StringPool.NULL);

			_persistence.countByG_N(0L, (String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetTag newAssetTag = addAssetTag();

		AssetTag existingAssetTag = _persistence.findByPrimaryKey(newAssetTag.getPrimaryKey());

		Assert.assertEquals(existingAssetTag, newAssetTag);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchTagException");
		}
		catch (NoSuchTagException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		try {
			_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetTag", "tagId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "name", true,
			"assetCount", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetTag newAssetTag = addAssetTag();

		AssetTag existingAssetTag = _persistence.fetchByPrimaryKey(newAssetTag.getPrimaryKey());

		Assert.assertEquals(existingAssetTag, newAssetTag);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetTag missingAssetTag = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetTag);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AssetTagLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					AssetTag assetTag = (AssetTag)object;

					Assert.assertNotNull(assetTag);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetTag newAssetTag = addAssetTag();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTag.class,
				AssetTag.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("tagId",
				newAssetTag.getTagId()));

		List<AssetTag> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetTag existingAssetTag = result.get(0);

		Assert.assertEquals(existingAssetTag, newAssetTag);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTag.class,
				AssetTag.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("tagId",
				RandomTestUtil.nextLong()));

		List<AssetTag> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetTag newAssetTag = addAssetTag();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTag.class,
				AssetTag.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tagId"));

		Object newTagId = newAssetTag.getTagId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("tagId",
				new Object[] { newTagId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTagId = result.get(0);

		Assert.assertEquals(existingTagId, newTagId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetTag.class,
				AssetTag.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tagId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("tagId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		AssetTag newAssetTag = addAssetTag();

		_persistence.clearCache();

		AssetTagModelImpl existingAssetTagModelImpl = (AssetTagModelImpl)_persistence.findByPrimaryKey(newAssetTag.getPrimaryKey());

		Assert.assertEquals(existingAssetTagModelImpl.getGroupId(),
			existingAssetTagModelImpl.getOriginalGroupId());
		Assert.assertTrue(Validator.equals(
				existingAssetTagModelImpl.getName(),
				existingAssetTagModelImpl.getOriginalName()));
	}

	protected AssetTag addAssetTag() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetTag assetTag = _persistence.create(pk);

		assetTag.setGroupId(RandomTestUtil.nextLong());

		assetTag.setCompanyId(RandomTestUtil.nextLong());

		assetTag.setUserId(RandomTestUtil.nextLong());

		assetTag.setUserName(RandomTestUtil.randomString());

		assetTag.setCreateDate(RandomTestUtil.nextDate());

		assetTag.setModifiedDate(RandomTestUtil.nextDate());

		assetTag.setName(RandomTestUtil.randomString());

		assetTag.setAssetCount(RandomTestUtil.nextInt());

		_persistence.update(assetTag);

		return assetTag;
	}

	private static Log _log = LogFactoryUtil.getLog(AssetTagPersistenceTest.class);
	private ModelListener<AssetTag>[] _modelListeners;
	private AssetTagPersistence _persistence = (AssetTagPersistence)PortalBeanLocatorUtil.locate(AssetTagPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}