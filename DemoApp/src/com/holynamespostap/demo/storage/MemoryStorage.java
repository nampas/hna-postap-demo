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
public class MemoryStorage implements StorageInterface {

	private ArrayList<CollegeApplicationModel> applications = new ArrayList<CollegeApplicationModel>();

	@Override
	public ArrayList<CollegeApplicationModel> getApplications() {
		return this.applications;
	}

	@Override
	public ArrayList<CollegeApplicationModel> getApplications(String username) {

		ArrayList<CollegeApplicationModel> filteredApp = new ArrayList<CollegeApplicationModel>();
		for(int i = 0; i < this.applications.size(); i++)
		{
			if(applications.get(i).getUsername().equalsIgnoreCase(username)){
				filteredApp.add(applications.get(i));
			}
		}

		return filteredApp;
	}

	@Override
	public boolean updateApplication(CollegeApplicationModel application) {
		for(int i = 0; i < this.applications.size(); i++)
		{
			if(application.getID().equalsIgnoreCase(this.applications.get(i).getID())){
				this.applications.set(i, application);
				return true;
			}
		}
		return false;
	}

	@Override
	public CollegeApplicationModel addApplication(String collegeName, CollegeApplicationCategoryModel category,
			String username) {

		String id = Integer.toString(this.applications.size());
		CollegeApplicationModel application = new CollegeApplicationModel(collegeName, category, username, id);
		this.applications.add(application);
		return application;
	}

	@Override
	public boolean deleteApplication(CollegeApplicationModel application) {
		for(int i = 0; i < this.applications.size(); i++)
		{
			if(application.getID().equalsIgnoreCase(this.applications.get(i).getID())){
				this.applications.remove(i);
				return true;
			}
		}
		return false;
	}


}
