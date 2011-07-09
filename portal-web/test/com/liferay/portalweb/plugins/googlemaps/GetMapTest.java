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

package com.liferay.portalweb.plugins.googlemaps;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class GetMapTest extends BaseTestCase {
	public void testGetMap() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Google Maps Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();
		selenium.clickAt("link=Google Maps Test Page",
			RuntimeVariables.replace("Google Maps Test Page"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		selenium.type("//form/input[1]",
			RuntimeVariables.replace("17730 Antonio Ave, Cerritos, CA, 90703"));
		selenium.saveScreenShotAndSource();
		selenium.type("//form/input[3]", RuntimeVariables.replace(""));
		selenium.saveScreenShotAndSource();
		selenium.clickAt("//input[@value='Get Map']",
			RuntimeVariables.replace("Get Map"));
		Thread.sleep(5000);
		assertEquals(RuntimeVariables.replace(
				"17730 Antonio Ave, Cerritos, CA, 90703"),
			selenium.getText("//div[10]/div/div[1]/div/div"));
	}
}