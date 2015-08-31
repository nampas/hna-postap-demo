/**
 * 
 */
package com.holynamespostap.demo.storage;

import java.util.ArrayList;

import com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationModel;

/**
 * @author alcheng
 *
 */
public interface StorageInterface {

	public ArrayList<CollegeApplicationModel> getApplications();
	
	public ArrayList<CollegeApplicationModel> getApplications(String username);
	
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
