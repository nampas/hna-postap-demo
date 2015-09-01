package com.holynamespostap.demo.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationTaskModel;
import com.holynamespostap.demo.storage.StorageFactory;
import com.holynamespostap.demo.storage.StorageInterface;

public class CloudStorageUnitTest {

	private static StorageInterface storage;
	private static ArrayList<CollegeApplicationModel> appsAdded;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageFactory.initializeStorage(StorageFactory.CLOUDSTORAGE);
        storage = StorageFactory.getInstance();
        appsAdded = new ArrayList<CollegeApplicationModel>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(int i = 0; i < appsAdded.size(); i++){
			storage.deleteApplication(appsAdded.get(i));
		}
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.CloudStorage#getApplications()}.
	 */
	@Test
	public void testGetApplications() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		appsAdded.add(app);
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertTrue(apps.contains(app));
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.CloudStorage#updateApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
	 */
	@Test
	public void testUpdateApplication() {
		CollegeApplicationModel app = storage.addApplication("updateUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		appsAdded.add(app);
		CollegeApplicationTaskModel task = new CollegeApplicationTaskModel("myTask");
		Calendar calendar = new GregorianCalendar(2015,9,16);
		task.setDueDate(calendar.getTime());
		app.addTask(task);
		boolean updated = storage.updateApplication(app);
		assertTrue(updated);
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertTrue(apps.contains(app));
		CollegeApplicationModel newApp = apps.get(apps.indexOf(app));
		assertNotNull(newApp);
		assertNotNull(newApp.getTasks());
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.CloudStorage#updateApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
	 */
	@Test
	public void testUpdateApplicationAfterDelete() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		assertTrue(storage.deleteApplication(app));
		CollegeApplicationTaskModel task = new CollegeApplicationTaskModel("myTask");
		Calendar calendar = new GregorianCalendar(2015,9,16);
		task.setDueDate(calendar.getTime());
		app.addTask(task);
		boolean updated = storage.updateApplication(app);
		assertFalse(updated);
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.CloudStorage#addApplication(java.lang.String, com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel, java.lang.String)}.
	 */
	@Test
	public void testAddApplication() {
		CollegeApplicationModel app1 = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		CollegeApplicationModel app2 = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		appsAdded.add(app1);
		appsAdded.add(app2);

		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertTrue(apps.contains(app1));
		assertTrue(apps.contains(app2));
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.CloudStorage#deleteApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
	 */
	@Test
	public void testDeleteApplication() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		boolean deleted = storage.deleteApplication(app);
		assertTrue(deleted);
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertFalse(apps.contains(app));
	}

}
