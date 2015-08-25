package com.holynamespostap.demo.dataModel;

public class CollegeApplicationCategoryModel {

	private String category;
	private CollegeApplicationCategoryModel(String category){
		this.category = category;
	}
	
	public static CollegeApplicationCategoryModel GetCategory(String category)
	{
		if(category.equalsIgnoreCase("MATCH"))
			return new CollegeApplicationCategoryModel("MATCH");
		else if(category.equalsIgnoreCase("SAFETY"))
			return new CollegeApplicationCategoryModel("SAFETY");
		else if(category.equalsIgnoreCase("REACH"))
			return new CollegeApplicationCategoryModel("REACH");
			
		return null;
	}
}