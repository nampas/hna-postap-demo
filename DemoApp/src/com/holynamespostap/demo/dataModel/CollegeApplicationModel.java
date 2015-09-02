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

	public CollegeApplicationModel(String collegeName, CollegeApplicationCategoryModel category, String username){
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

	public ArrayList<CollegeApplicationTaskModel> getTasks(){
		return taskList;
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
		StorageFactory.getInstance().updateApplication(this);
	}

	@Override
	public String renderToHtml() {
		return new StringBuilder()
			.append("<h4>" + collegeName + "</h4>")
			.append("<p>Category: " + category.getCategory() + "</p>")
			.append("<p>Admitted: " + admitted + "</p>")
			.toString();
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final CollegeApplicationModel other = (CollegeApplicationModel) obj;
	    if ((this.id == null) ? (other.id != null) : !this.id.equalsIgnoreCase(other.id)) {
	        return false;
	    }

	    return true;
	}

	@Override
	public int hashCode() {
	    return this.id.hashCode();
	}

}
