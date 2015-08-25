package com.holynamespostap.demo.dataModel;

/**
 * Represents a task for a college application
 * 
 * @author Nathan P
 *
 */
public class CollegeApplicationTaskModel extends AbstractDataModel {

	@Override
	public String renderToHtml() {
		return "<h3>This is a task</h3>";
	}

}
