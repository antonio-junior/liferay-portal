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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.convert.ConvertImageGallery;
import com.liferay.portal.convert.ConvertProcess;
import com.liferay.portal.image.DLHook;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.imagegallery.model.IGFolder;
import com.liferay.portlet.imagegallery.model.IGImage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Sergio González
 */
public class UpgradeImageGallery extends UpgradeProcess {

	protected void addDLFileEntry(
			String uuid, long fileEntryId, long groupId, long companyId,
			long userId, String userName, long versionUserId,
			String versionUserName, Date createDate, Date modifiedDate,
			long repositoryId, long folderId, String name, String extension,
			String mimeType, String title, String description,
			String extraSettings, String version, long size, int readCount,
			long smallImageId, long largeImageId, long custom1ImageId,
			long custom2ImageId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(9);

			sb.append("insert into DLFileEntry (uuid_, fileEntryId, groupId, ");
			sb.append("companyId, userId, userName, versionUserId, ");
			sb.append("versionUserName, createDate, modifiedDate, ");
			sb.append("repositoryId, folderId, name, extension, mimeType, ");
			sb.append("title, description, extraSettings, version, size_, ");
			sb.append("readCount, smallImageId, largeImageId, ");
			sb.append("custom1ImageId, custom2ImageId) values (");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setString(1, uuid);
			ps.setLong(2, fileEntryId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setLong(7, versionUserId);
			ps.setString(8, versionUserName);
			ps.setDate(9, createDate);
			ps.setDate(10, modifiedDate);
			ps.setLong(11, repositoryId);
			ps.setLong(12, folderId);
			ps.setString(13, name);
			ps.setString(14, extension);
			ps.setString(15, mimeType);
			ps.setString(16, title);
			ps.setString(17, description);
			ps.setString(18, extraSettings);
			ps.setString(19, version);
			ps.setLong(20, size);
			ps.setInt(21, readCount);
			ps.setLong(22, smallImageId);
			ps.setLong(23, largeImageId);
			ps.setLong(24, custom1ImageId);
			ps.setLong(25, custom2ImageId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void addDLFileVersion(
			long fileVersionId, long groupId, long companyId, long userId,
			String userName, Date createDate, long repositoryId,
			long fileEntryId, String extension, String mimeType, String title,
			String description, String changeLog, String extraSettings,
			long fileEntryTypeId, String version, long size, long smallImageId,
			long largeImageId, long custom1ImageId, long custom2ImageId,
			int status, long statusByUserId, String statusByUserName,
			Date statusDate)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(9);

			sb.append("insert into DLFileVersion (fileVersionId, groupId, ");
			sb.append("companyId, userId, userName, createDate, ");
			sb.append("repositoryId, fileEntryId, extension, mimeType, ");
			sb.append("title, description, changeLog, extraSettings, ");
			sb.append("fileEntryTypeId, version, size_, smallImageId, ");
			sb.append("largeImageId, custom1ImageId, custom2ImageId, status, ");
			sb.append("statusByUserId, statusByUserName, statusDate) values (");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, fileVersionId);
			ps.setLong(2, groupId);
			ps.setLong(3, companyId);
			ps.setLong(4, userId);
			ps.setString(5, userName);
			ps.setDate(6, createDate);
			ps.setLong(7, repositoryId);
			ps.setLong(8, fileEntryId);
			ps.setString(9, extension);
			ps.setString(10, mimeType);
			ps.setString(11, title);
			ps.setString(12, description);
			ps.setString(13, changeLog);
			ps.setString(14, extraSettings);
			ps.setLong(15, fileEntryTypeId);
			ps.setString(16, version);
			ps.setLong(17, size);
			ps.setLong(18, smallImageId);
			ps.setLong(19, largeImageId);
			ps.setLong(20, custom1ImageId);
			ps.setLong(21, custom2ImageId);
			ps.setInt(22, status);
			ps.setLong(23, statusByUserId);
			ps.setString(24, statusByUserName);
			ps.setDate(25, statusDate);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void addDLFolderEntry(
			String uuid, long folderId, long groupId, long companyId,
			long userId, String userName, Date createDate, Date modifiedDate,
			long repositoryId, long parentFolderId, String name,
			String description, Date lastPostDate)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("insert into DLFolder (uuid_, folderId, groupId, ");
			sb.append("companyId, userId, userName, createDate, ");
			sb.append("modifiedDate, repositoryId, mountPoint, ");
			sb.append("parentFolderId, name, description, lastPostDate) ");
			sb.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setString(1, uuid);
			ps.setLong(2, folderId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setDate(7, createDate);
			ps.setDate(8, modifiedDate);
			ps.setLong(9, repositoryId);
			ps.setBoolean(10, false);
			ps.setLong(11, parentFolderId);
			ps.setString(12, name);
			ps.setString(13, description);
			ps.setDate(14, lastPostDate);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateIGFolderEntries();
		updateIGImageEntries();
		updateIGFolderPermissions();
		updateIGImagePermissions();

		if (!PropsValues.IMAGE_HOOK_IMPL.equals(DLHook.class.getName())) {
			ConvertProcess convertProcess = new ConvertImageGallery();

			String[] parameters = {DLHook.class.getName()};

			convertProcess.setParameterValues(parameters);

			convertProcess.convert();
		}
	}

	protected Object[] getImage(long imageId) throws Exception {
		Object[] image = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select type_, size_  from Image where imageId = " + imageId);

			rs = ps.executeQuery();

			while (rs.next()) {
				String type = rs.getString("type_");
				long size = rs.getLong("size_");

				image = new Object[] {type, size};
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return image;
	}

	protected void updateIGFolderEntries() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from IGFolder");

			rs = ps.executeQuery();

			while (rs.next()) {
				String uuid = rs.getString("uuid_");
				long folderId = rs.getLong("folderId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Date createDate = rs.getDate("createDate");
				Date modifiedDate = rs.getDate("modifiedDate");
				long parentFolderId = rs.getLong("parentFolderId");
				String name = rs.getString("name");
				String description = rs.getString("description");

				addDLFolderEntry(
					uuid, folderId, groupId, companyId, userId, userName,
					createDate, modifiedDate, groupId, parentFolderId, name,
					description, modifiedDate);
			}

			runSQL("drop table IGFolder");
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateIGFolderPermissions() throws Exception {
		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM != 6) {
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("update ResourcePermission set name = '");
			sb.append(DLFolder.class.getName());
			sb.append("' where name = '");
			sb.append(IGFolder.class.getName());
			sb.append("'");

			ps = con.prepareStatement(sb.toString());

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateIGImageEntries() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from IGImage");

			rs = ps.executeQuery();

			while (rs.next()) {
				String uuid = rs.getString("uuid_");
				long imageId = rs.getLong("imageId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Date createDate = rs.getDate("createDate");
				Date modifiedDate = rs.getDate("modifiedDate");
				long folderId = rs.getLong("folderId");
				String title = rs.getString("name");
				String description = rs.getString("description");
				long smallImageId = rs.getLong("smallImageId");
				long largeImageId = rs.getLong("largeImageId");
				long custom1ImageId = rs.getLong("custom1ImageId");
				long custom2ImageId = rs.getLong("custom2ImageId");

				Object[] image = getImage(largeImageId);

				if (image == null) {
					continue;
				}

				String extension = (String)image[0];

				String mimeType = MimeTypesUtil.getContentType(
					"A." + extension);

				String name = String.valueOf(
					increment(DLFileEntry.class.getName()));

				long size = (Long)image[1];

				addDLFileEntry(
					uuid, imageId, groupId, companyId, userId,
					userName, userId, userName, createDate, modifiedDate,
					groupId, folderId, name, extension, StringPool.BLANK, title,
					description, StringPool.BLANK, "1.0", size, 0, smallImageId,
					largeImageId, custom1ImageId, custom2ImageId);

				addDLFileVersion(
					increment(), groupId, companyId, userId, userName,
					createDate, groupId, imageId, extension, mimeType,
					title, description, StringPool.BLANK, StringPool.BLANK, 0,
					"1.0", size, smallImageId, largeImageId, custom1ImageId,
					custom2ImageId, 0, userId, userName, modifiedDate);
			}

			runSQL("drop table IGImage");
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateIGImagePermissions() throws Exception {
		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM != 6) {
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("update ResourcePermission set name = '");
			sb.append(DLFileEntry.class.getName());
			sb.append("' where name = '");
			sb.append(IGImage.class.getName());
			sb.append("'");

			ps = con.prepareStatement(sb.toString());

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}