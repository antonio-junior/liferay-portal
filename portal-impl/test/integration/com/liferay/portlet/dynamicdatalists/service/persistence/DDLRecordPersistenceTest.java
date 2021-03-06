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

package com.liferay.portlet.dynamicdatalists.service.persistence;

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

import com.liferay.portlet.dynamicdatalists.NoSuchRecordException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordModelImpl;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;

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
public class DDLRecordPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<DDLRecord> modelListener : _modelListeners) {
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

		for (ModelListener<DDLRecord> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDLRecord ddlRecord = _persistence.create(pk);

		Assert.assertNotNull(ddlRecord);

		Assert.assertEquals(ddlRecord.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDLRecord newDDLRecord = addDDLRecord();

		_persistence.remove(newDDLRecord);

		DDLRecord existingDDLRecord = _persistence.fetchByPrimaryKey(newDDLRecord.getPrimaryKey());

		Assert.assertNull(existingDDLRecord);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDLRecord();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDLRecord newDDLRecord = _persistence.create(pk);

		newDDLRecord.setUuid(RandomTestUtil.randomString());

		newDDLRecord.setGroupId(RandomTestUtil.nextLong());

		newDDLRecord.setCompanyId(RandomTestUtil.nextLong());

		newDDLRecord.setUserId(RandomTestUtil.nextLong());

		newDDLRecord.setUserName(RandomTestUtil.randomString());

		newDDLRecord.setVersionUserId(RandomTestUtil.nextLong());

		newDDLRecord.setVersionUserName(RandomTestUtil.randomString());

		newDDLRecord.setCreateDate(RandomTestUtil.nextDate());

		newDDLRecord.setModifiedDate(RandomTestUtil.nextDate());

		newDDLRecord.setDDMStorageId(RandomTestUtil.nextLong());

		newDDLRecord.setRecordSetId(RandomTestUtil.nextLong());

		newDDLRecord.setVersion(RandomTestUtil.randomString());

		newDDLRecord.setDisplayIndex(RandomTestUtil.nextInt());

		_persistence.update(newDDLRecord);

		DDLRecord existingDDLRecord = _persistence.findByPrimaryKey(newDDLRecord.getPrimaryKey());

		Assert.assertEquals(existingDDLRecord.getUuid(), newDDLRecord.getUuid());
		Assert.assertEquals(existingDDLRecord.getRecordId(),
			newDDLRecord.getRecordId());
		Assert.assertEquals(existingDDLRecord.getGroupId(),
			newDDLRecord.getGroupId());
		Assert.assertEquals(existingDDLRecord.getCompanyId(),
			newDDLRecord.getCompanyId());
		Assert.assertEquals(existingDDLRecord.getUserId(),
			newDDLRecord.getUserId());
		Assert.assertEquals(existingDDLRecord.getUserName(),
			newDDLRecord.getUserName());
		Assert.assertEquals(existingDDLRecord.getVersionUserId(),
			newDDLRecord.getVersionUserId());
		Assert.assertEquals(existingDDLRecord.getVersionUserName(),
			newDDLRecord.getVersionUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDLRecord.getCreateDate()),
			Time.getShortTimestamp(newDDLRecord.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingDDLRecord.getModifiedDate()),
			Time.getShortTimestamp(newDDLRecord.getModifiedDate()));
		Assert.assertEquals(existingDDLRecord.getDDMStorageId(),
			newDDLRecord.getDDMStorageId());
		Assert.assertEquals(existingDDLRecord.getRecordSetId(),
			newDDLRecord.getRecordSetId());
		Assert.assertEquals(existingDDLRecord.getVersion(),
			newDDLRecord.getVersion());
		Assert.assertEquals(existingDDLRecord.getDisplayIndex(),
			newDDLRecord.getDisplayIndex());
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
	public void testCountByUUID_G() {
		try {
			_persistence.countByUUID_G(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUUID_G(StringPool.NULL, 0L);

			_persistence.countByUUID_G((String)null, 0L);
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
	public void testCountByRecordSetId() {
		try {
			_persistence.countByRecordSetId(RandomTestUtil.nextLong());

			_persistence.countByRecordSetId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByR_U() {
		try {
			_persistence.countByR_U(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByR_U(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDLRecord newDDLRecord = addDDLRecord();

		DDLRecord existingDDLRecord = _persistence.findByPrimaryKey(newDDLRecord.getPrimaryKey());

		Assert.assertEquals(existingDDLRecord, newDDLRecord);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchRecordException");
		}
		catch (NoSuchRecordException nsee) {
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
		return OrderByComparatorFactoryUtil.create("DDLRecord", "uuid", true,
			"recordId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "versionUserId", true, "versionUserName",
			true, "createDate", true, "modifiedDate", true, "DDMStorageId",
			true, "recordSetId", true, "version", true, "displayIndex", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDLRecord newDDLRecord = addDDLRecord();

		DDLRecord existingDDLRecord = _persistence.fetchByPrimaryKey(newDDLRecord.getPrimaryKey());

		Assert.assertEquals(existingDDLRecord, newDDLRecord);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDLRecord missingDDLRecord = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDLRecord);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DDLRecordLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					DDLRecord ddlRecord = (DDLRecord)object;

					Assert.assertNotNull(ddlRecord);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DDLRecord newDDLRecord = addDDLRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDLRecord.class,
				DDLRecord.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recordId",
				newDDLRecord.getRecordId()));

		List<DDLRecord> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDLRecord existingDDLRecord = result.get(0);

		Assert.assertEquals(existingDDLRecord, newDDLRecord);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDLRecord.class,
				DDLRecord.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recordId",
				RandomTestUtil.nextLong()));

		List<DDLRecord> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DDLRecord newDDLRecord = addDDLRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDLRecord.class,
				DDLRecord.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("recordId"));

		Object newRecordId = newDDLRecord.getRecordId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("recordId",
				new Object[] { newRecordId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRecordId = result.get(0);

		Assert.assertEquals(existingRecordId, newRecordId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DDLRecord.class,
				DDLRecord.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("recordId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("recordId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		DDLRecord newDDLRecord = addDDLRecord();

		_persistence.clearCache();

		DDLRecordModelImpl existingDDLRecordModelImpl = (DDLRecordModelImpl)_persistence.findByPrimaryKey(newDDLRecord.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingDDLRecordModelImpl.getUuid(),
				existingDDLRecordModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingDDLRecordModelImpl.getGroupId(),
			existingDDLRecordModelImpl.getOriginalGroupId());
	}

	protected DDLRecord addDDLRecord() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDLRecord ddlRecord = _persistence.create(pk);

		ddlRecord.setUuid(RandomTestUtil.randomString());

		ddlRecord.setGroupId(RandomTestUtil.nextLong());

		ddlRecord.setCompanyId(RandomTestUtil.nextLong());

		ddlRecord.setUserId(RandomTestUtil.nextLong());

		ddlRecord.setUserName(RandomTestUtil.randomString());

		ddlRecord.setVersionUserId(RandomTestUtil.nextLong());

		ddlRecord.setVersionUserName(RandomTestUtil.randomString());

		ddlRecord.setCreateDate(RandomTestUtil.nextDate());

		ddlRecord.setModifiedDate(RandomTestUtil.nextDate());

		ddlRecord.setDDMStorageId(RandomTestUtil.nextLong());

		ddlRecord.setRecordSetId(RandomTestUtil.nextLong());

		ddlRecord.setVersion(RandomTestUtil.randomString());

		ddlRecord.setDisplayIndex(RandomTestUtil.nextInt());

		_persistence.update(ddlRecord);

		return ddlRecord;
	}

	private static Log _log = LogFactoryUtil.getLog(DDLRecordPersistenceTest.class);
	private ModelListener<DDLRecord>[] _modelListeners;
	private DDLRecordPersistence _persistence = (DDLRecordPersistence)PortalBeanLocatorUtil.locate(DDLRecordPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}