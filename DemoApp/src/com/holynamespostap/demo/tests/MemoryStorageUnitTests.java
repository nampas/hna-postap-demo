/**
 * 
 */
package com.holynamespostap.demo.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.*;

import com.holynamespostap.demo.dataModel.*;
import com.holynamespostap.demo.storage.*;

/**
 * @author alcheng
 *
 */
public class MemoryStorageUnitTests {
	
	private StorageInterface storage;
	
    @Before
    public void setUp() {
        StorageFactory.initializeStorage(StorageFactory.MEMORYSTORAGE);
        storage = StorageFactory.GetInstance();
    }

	
	/**
	 * Test method for {@link com.holynamespostap.demo.storage.MemoryStorage#getApplications()}.
	 */
	@Test
	public void testGetApplications() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertTrue(apps.contains(app));
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.MemoryStorage#updateApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
	 */
	@Test
	public void testUpdateApplication() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
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
	 * Test method for {@link com.holynamespostap.demo.storage.MemoryStorage#updateApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
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
	 * Test method for {@link com.holynamespostap.demo.storage.MemoryStorage#addApplication(java.lang.String, com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel, java.lang.String)}.
	 */
	@Test
	public void testAddApplication() {
		storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertEquals(2, apps.size());
	}

	/**
	 * Test method for {@link com.holynamespostap.demo.storage.MemoryStorage#deleteApplication(com.holynamespostap.demo.dataModel.CollegeApplicationModel)}.
	 */
	@Test
	public void testDeleteApplication() {
		CollegeApplicationModel app = storage.addApplication("testUniversity", CollegeApplicationCategoryModel.GetCategory("match"), "alex");
		boolean deleted = storage.deleteApplication(app);
		assertTrue(deleted);
		ArrayList<CollegeApplicationModel> apps = storage.getApplications();
		assertEquals(0, apps.size());
	}

}
