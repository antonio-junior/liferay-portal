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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchLayoutPrototypeException;
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
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.test.TransactionalPersistenceAdvice;
import com.liferay.portal.util.test.RandomTestUtil;

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
public class LayoutPrototypePersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<LayoutPrototype> modelListener : _modelListeners) {
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

		for (ModelListener<LayoutPrototype> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPrototype layoutPrototype = _persistence.create(pk);

		Assert.assertNotNull(layoutPrototype);

		Assert.assertEquals(layoutPrototype.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutPrototype newLayoutPrototype = addLayoutPrototype();

		_persistence.remove(newLayoutPrototype);

		LayoutPrototype existingLayoutPrototype = _persistence.fetchByPrimaryKey(newLayoutPrototype.getPrimaryKey());

		Assert.assertNull(existingLayoutPrototype);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutPrototype();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPrototype newLayoutPrototype = _persistence.create(pk);

		newLayoutPrototype.setMvccVersion(RandomTestUtil.nextLong());

		newLayoutPrototype.setUuid(RandomTestUtil.randomString());

		newLayoutPrototype.setCompanyId(RandomTestUtil.nextLong());

		newLayoutPrototype.setUserId(RandomTestUtil.nextLong());

		newLayoutPrototype.setUserName(RandomTestUtil.randomString());

		newLayoutPrototype.setCreateDate(RandomTestUtil.nextDate());

		newLayoutPrototype.setModifiedDate(RandomTestUtil.nextDate());

		newLayoutPrototype.setName(RandomTestUtil.randomString());

		newLayoutPrototype.setDescription(RandomTestUtil.randomString());

		newLayoutPrototype.setSettings(RandomTestUtil.randomString());

		newLayoutPrototype.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(newLayoutPrototype);

		LayoutPrototype existingLayoutPrototype = _persistence.findByPrimaryKey(newLayoutPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutPrototype.getMvccVersion(),
			newLayoutPrototype.getMvccVersion());
		Assert.assertEquals(existingLayoutPrototype.getUuid(),
			newLayoutPrototype.getUuid());
		Assert.assertEquals(existingLayoutPrototype.getLayoutPrototypeId(),
			newLayoutPrototype.getLayoutPrototypeId());
		Assert.assertEquals(existingLayoutPrototype.getCompanyId(),
			newLayoutPrototype.getCompanyId());
		Assert.assertEquals(existingLayoutPrototype.getUserId(),
			newLayoutPrototype.getUserId());
		Assert.assertEquals(existingLayoutPrototype.getUserName(),
			newLayoutPrototype.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutPrototype.getCreateDate()),
			Time.getShortTimestamp(newLayoutPrototype.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutPrototype.getModifiedDate()),
			Time.getShortTimestamp(newLayoutPrototype.getModifiedDate()));
		Assert.assertEquals(existingLayoutPrototype.getName(),
			newLayoutPrototype.getName());
		Assert.assertEquals(existingLayoutPrototype.getDescription(),
			newLayoutPrototype.getDescription());
		Assert.assertEquals(existingLayoutPrototype.getSettings(),
			newLayoutPrototype.getSettings());
		Assert.assertEquals(existingLayoutPrototype.getActive(),
			newLayoutPrototype.getActive());
	}

	@Test
	public void testCountByUuid() {
		try {
			_persistence.countByUuid(StringPool.BLANK);

			_persistence.countByUuid(StringPool.NULL);

			_persistence.countByUuid((String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUuid_C() {
		try {
			_persistence.countByUuid_C(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUuid_C(StringPool.NULL, 0L);

			_persistence.countByUuid_C((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByCompanyId() {
		try {
			_persistence.countByCompanyId(RandomTestUtil.nextLong());

			_persistence.countByCompanyId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_A() {
		try {
			_persistence.countByC_A(RandomTestUtil.nextLong(),
				RandomTestUtil.randomBoolean());

			_persistence.countByC_A(0L, RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutPrototype newLayoutPrototype = addLayoutPrototype();

		LayoutPrototype existingLayoutPrototype = _persistence.findByPrimaryKey(newLayoutPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutPrototype, newLayoutPrototype);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchLayoutPrototypeException");
		}
		catch (NoSuchLayoutPrototypeException nsee) {
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

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LayoutPrototype",
			"mvccVersion", true, "uuid", true, "layoutPrototypeId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "name", true, "description", true,
			"settings", true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutPrototype newLayoutPrototype = addLayoutPrototype();

		LayoutPrototype existingLayoutPrototype = _persistence.fetchByPrimaryKey(newLayoutPrototype.getPrimaryKey());

		Assert.assertEquals(existingLayoutPrototype, newLayoutPrototype);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPrototype missingLayoutPrototype = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutPrototype);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LayoutPrototypeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					LayoutPrototype layoutPrototype = (LayoutPrototype)object;

					Assert.assertNotNull(layoutPrototype);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutPrototype newLayoutPrototype = addLayoutPrototype();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPrototype.class,
				LayoutPrototype.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutPrototypeId",
				newLayoutPrototype.getLayoutPrototypeId()));

		List<LayoutPrototype> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutPrototype existingLayoutPrototype = result.get(0);

		Assert.assertEquals(existingLayoutPrototype, newLayoutPrototype);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPrototype.class,
				LayoutPrototype.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutPrototypeId",
				RandomTestUtil.nextLong()));

		List<LayoutPrototype> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutPrototype newLayoutPrototype = addLayoutPrototype();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPrototype.class,
				LayoutPrototype.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutPrototypeId"));

		Object newLayoutPrototypeId = newLayoutPrototype.getLayoutPrototypeId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutPrototypeId",
				new Object[] { newLayoutPrototypeId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutPrototypeId = result.get(0);

		Assert.assertEquals(existingLayoutPrototypeId, newLayoutPrototypeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPrototype.class,
				LayoutPrototype.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutPrototypeId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutPrototypeId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LayoutPrototype addLayoutPrototype() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPrototype layoutPrototype = _persistence.create(pk);

		layoutPrototype.setMvccVersion(RandomTestUtil.nextLong());

		layoutPrototype.setUuid(RandomTestUtil.randomString());

		layoutPrototype.setCompanyId(RandomTestUtil.nextLong());

		layoutPrototype.setUserId(RandomTestUtil.nextLong());

		layoutPrototype.setUserName(RandomTestUtil.randomString());

		layoutPrototype.setCreateDate(RandomTestUtil.nextDate());

		layoutPrototype.setModifiedDate(RandomTestUtil.nextDate());

		layoutPrototype.setName(RandomTestUtil.randomString());

		layoutPrototype.setDescription(RandomTestUtil.randomString());

		layoutPrototype.setSettings(RandomTestUtil.randomString());

		layoutPrototype.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(layoutPrototype);

		return layoutPrototype;
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutPrototypePersistenceTest.class);
	private ModelListener<LayoutPrototype>[] _modelListeners;
	private LayoutPrototypePersistence _persistence = (LayoutPrototypePersistence)PortalBeanLocatorUtil.locate(LayoutPrototypePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}