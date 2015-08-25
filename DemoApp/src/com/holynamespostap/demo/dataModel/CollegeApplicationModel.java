package com.holynamespostap.demo.dataModel;

/**
 * Represents a college application
 * 
 * @author Nathan P
 *
 */
public class CollegeApplicationModel extends AbstractDataModel {

	@Override
	public String renderToHtml() {
		return "<h3>This is an application</h3>";
	}

}
