/**
 *
 */
package com.holynamespostap.demo.storage;

import java.util.List;

import com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationModel;

/**
 * @author alcheng
 *
 */
public interface StorageInterface {

	public List<CollegeApplicationModel> getApplications();

	public List<CollegeApplicationModel> getApplications(String username);

	public boolean updateApplication(CollegeApplicationModel application);

	/**
	 * add the application to storage and return the application for rendering
	 *
	 * @param university the name of the university
	 * @param category call CollegeApplicationCategoryModel.getCategory to create
	 * @param username
	 * @return application
	 */
	public CollegeApplicationModel addApplication(String university, CollegeApplicationCategoryModel category, String username);

	public boolean deleteApplication(CollegeApplicationModel application);
}
