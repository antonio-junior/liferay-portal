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

package com.liferay.portlet.announcements.service.persistence;

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
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.test.TransactionalPersistenceAdvice;
import com.liferay.portal.util.test.RandomTestUtil;

import com.liferay.portlet.announcements.NoSuchEntryException;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;

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
public class AnnouncementsEntryPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<AnnouncementsEntry> modelListener : _modelListeners) {
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

		for (ModelListener<AnnouncementsEntry> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsEntry announcementsEntry = _persistence.create(pk);

		Assert.assertNotNull(announcementsEntry);

		Assert.assertEquals(announcementsEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AnnouncementsEntry newAnnouncementsEntry = addAnnouncementsEntry();

		_persistence.remove(newAnnouncementsEntry);

		AnnouncementsEntry existingAnnouncementsEntry = _persistence.fetchByPrimaryKey(newAnnouncementsEntry.getPrimaryKey());

		Assert.assertNull(existingAnnouncementsEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAnnouncementsEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsEntry newAnnouncementsEntry = _persistence.create(pk);

		newAnnouncementsEntry.setUuid(RandomTestUtil.randomString());

		newAnnouncementsEntry.setCompanyId(RandomTestUtil.nextLong());

		newAnnouncementsEntry.setUserId(RandomTestUtil.nextLong());

		newAnnouncementsEntry.setUserName(RandomTestUtil.randomString());

		newAnnouncementsEntry.setCreateDate(RandomTestUtil.nextDate());

		newAnnouncementsEntry.setModifiedDate(RandomTestUtil.nextDate());

		newAnnouncementsEntry.setClassNameId(RandomTestUtil.nextLong());

		newAnnouncementsEntry.setClassPK(RandomTestUtil.nextLong());

		newAnnouncementsEntry.setTitle(RandomTestUtil.randomString());

		newAnnouncementsEntry.setContent(RandomTestUtil.randomString());

		newAnnouncementsEntry.setUrl(RandomTestUtil.randomString());

		newAnnouncementsEntry.setType(RandomTestUtil.randomString());

		newAnnouncementsEntry.setDisplayDate(RandomTestUtil.nextDate());

		newAnnouncementsEntry.setExpirationDate(RandomTestUtil.nextDate());

		newAnnouncementsEntry.setPriority(RandomTestUtil.nextInt());

		newAnnouncementsEntry.setAlert(RandomTestUtil.randomBoolean());

		_persistence.update(newAnnouncementsEntry);

		AnnouncementsEntry existingAnnouncementsEntry = _persistence.findByPrimaryKey(newAnnouncementsEntry.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsEntry.getUuid(),
			newAnnouncementsEntry.getUuid());
		Assert.assertEquals(existingAnnouncementsEntry.getEntryId(),
			newAnnouncementsEntry.getEntryId());
		Assert.assertEquals(existingAnnouncementsEntry.getCompanyId(),
			newAnnouncementsEntry.getCompanyId());
		Assert.assertEquals(existingAnnouncementsEntry.getUserId(),
			newAnnouncementsEntry.getUserId());
		Assert.assertEquals(existingAnnouncementsEntry.getUserName(),
			newAnnouncementsEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAnnouncementsEntry.getCreateDate()),
			Time.getShortTimestamp(newAnnouncementsEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAnnouncementsEntry.getModifiedDate()),
			Time.getShortTimestamp(newAnnouncementsEntry.getModifiedDate()));
		Assert.assertEquals(existingAnnouncementsEntry.getClassNameId(),
			newAnnouncementsEntry.getClassNameId());
		Assert.assertEquals(existingAnnouncementsEntry.getClassPK(),
			newAnnouncementsEntry.getClassPK());
		Assert.assertEquals(existingAnnouncementsEntry.getTitle(),
			newAnnouncementsEntry.getTitle());
		Assert.assertEquals(existingAnnouncementsEntry.getContent(),
			newAnnouncementsEntry.getContent());
		Assert.assertEquals(existingAnnouncementsEntry.getUrl(),
			newAnnouncementsEntry.getUrl());
		Assert.assertEquals(existingAnnouncementsEntry.getType(),
			newAnnouncementsEntry.getType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAnnouncementsEntry.getDisplayDate()),
			Time.getShortTimestamp(newAnnouncementsEntry.getDisplayDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAnnouncementsEntry.getExpirationDate()),
			Time.getShortTimestamp(newAnnouncementsEntry.getExpirationDate()));
		Assert.assertEquals(existingAnnouncementsEntry.getPriority(),
			newAnnouncementsEntry.getPriority());
		Assert.assertEquals(existingAnnouncementsEntry.getAlert(),
			newAnnouncementsEntry.getAlert());
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
	public void testCountByUserId() {
		try {
			_persistence.countByUserId(RandomTestUtil.nextLong());

			_persistence.countByUserId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C() {
		try {
			_persistence.countByC_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByC_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C_A() {
		try {
			_persistence.countByC_C_A(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

			_persistence.countByC_C_A(0L, 0L, RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AnnouncementsEntry newAnnouncementsEntry = addAnnouncementsEntry();

		AnnouncementsEntry existingAnnouncementsEntry = _persistence.findByPrimaryKey(newAnnouncementsEntry.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsEntry, newAnnouncementsEntry);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchEntryException");
		}
		catch (NoSuchEntryException nsee) {
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
		return OrderByComparatorFactoryUtil.create("AnnouncementsEntry",
			"uuid", true, "entryId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "title", true, "content",
			true, "url", true, "type", true, "displayDate", true,
			"expirationDate", true, "priority", true, "alert", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AnnouncementsEntry newAnnouncementsEntry = addAnnouncementsEntry();

		AnnouncementsEntry existingAnnouncementsEntry = _persistence.fetchByPrimaryKey(newAnnouncementsEntry.getPrimaryKey());

		Assert.assertEquals(existingAnnouncementsEntry, newAnnouncementsEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsEntry missingAnnouncementsEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAnnouncementsEntry);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AnnouncementsEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					AnnouncementsEntry announcementsEntry = (AnnouncementsEntry)object;

					Assert.assertNotNull(announcementsEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AnnouncementsEntry newAnnouncementsEntry = addAnnouncementsEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsEntry.class,
				AnnouncementsEntry.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				newAnnouncementsEntry.getEntryId()));

		List<AnnouncementsEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AnnouncementsEntry existingAnnouncementsEntry = result.get(0);

		Assert.assertEquals(existingAnnouncementsEntry, newAnnouncementsEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsEntry.class,
				AnnouncementsEntry.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				RandomTestUtil.nextLong()));

		List<AnnouncementsEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AnnouncementsEntry newAnnouncementsEntry = addAnnouncementsEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsEntry.class,
				AnnouncementsEntry.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		Object newEntryId = newAnnouncementsEntry.getEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { newEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntryId = result.get(0);

		Assert.assertEquals(existingEntryId, newEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AnnouncementsEntry.class,
				AnnouncementsEntry.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AnnouncementsEntry addAnnouncementsEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AnnouncementsEntry announcementsEntry = _persistence.create(pk);

		announcementsEntry.setUuid(RandomTestUtil.randomString());

		announcementsEntry.setCompanyId(RandomTestUtil.nextLong());

		announcementsEntry.setUserId(RandomTestUtil.nextLong());

		announcementsEntry.setUserName(RandomTestUtil.randomString());

		announcementsEntry.setCreateDate(RandomTestUtil.nextDate());

		announcementsEntry.setModifiedDate(RandomTestUtil.nextDate());

		announcementsEntry.setClassNameId(RandomTestUtil.nextLong());

		announcementsEntry.setClassPK(RandomTestUtil.nextLong());

		announcementsEntry.setTitle(RandomTestUtil.randomString());

		announcementsEntry.setContent(RandomTestUtil.randomString());

		announcementsEntry.setUrl(RandomTestUtil.randomString());

		announcementsEntry.setType(RandomTestUtil.randomString());

		announcementsEntry.setDisplayDate(RandomTestUtil.nextDate());

		announcementsEntry.setExpirationDate(RandomTestUtil.nextDate());

		announcementsEntry.setPriority(RandomTestUtil.nextInt());

		announcementsEntry.setAlert(RandomTestUtil.randomBoolean());

		_persistence.update(announcementsEntry);

		return announcementsEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(AnnouncementsEntryPersistenceTest.class);
	private ModelListener<AnnouncementsEntry>[] _modelListeners;
	private AnnouncementsEntryPersistence _persistence = (AnnouncementsEntryPersistence)PortalBeanLocatorUtil.locate(AnnouncementsEntryPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}