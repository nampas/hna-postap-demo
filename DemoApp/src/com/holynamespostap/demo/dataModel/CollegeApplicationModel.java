package com.holynamespostap.demo.dataModel;

import java.util.ArrayList;
import java.util.Date;

import com.holynamespostap.demo.storage.StorageFactory;

/**
 * Represents a college application
 * 
 * @author Nathan P
 *
 */
public class CollegeApplicationModel extends AbstractDataModel {

	private String id;
	private CollegeApplicationCategoryModel category;
	private String collegeName = "";
	private ArrayList<CollegeApplicationTaskModel> taskList = new ArrayList<CollegeApplicationTaskModel>();
	private Date applicationDueDate;
	private String username;
	private boolean admitted = false;
	
	public CollegeApplicationModel(String collegeName, CollegeApplicationCategoryModel category, String username, String id){
		this.id = id;
		this.collegeName = collegeName;
		this.category = category;
		this.username = username;
	}
		
	public CollegeApplicationCategoryModel getCategory() {
		return category;
	}

	public void setCategory(CollegeApplicationCategoryModel category) {
		this.category = category;
	}

	public Date getApplicationDueDate() {
		return applicationDueDate;
	}

	public void setApplicationDueDate(Date applicationDueDate) {
		this.applicationDueDate = applicationDueDate;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAdmitted() {
		return admitted;
	}

	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}

	public void addTask(CollegeApplicationTaskModel task){
		taskList.add(task);
	}
	
	public String getCollegeName() {
		return collegeName;
	}

	public String getID() {
		return this.id;
	}
	
	/**
	 * not sure whether we should just save on every setting on each property 
	 * or just have an explicit save method
	 */
	public void save(){
		StorageFactory.GetInstance().updateApplication(this);
	}
	
	@Override
	public String renderToHtml() {
		return "<h3>This is an application</h3>";
	}


}
