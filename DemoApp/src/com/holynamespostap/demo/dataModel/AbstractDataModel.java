package com.holynamespostap.demo.dataModel;

/**
 * A data object. Each instance of this class should encapsulate one distinct
 * piece of data. This class is also responsible for rendering itself to HTML.
 * Much like an object's toString() returns a String representation,
 * renderToHtml() should return an HTML representation
 *
 * @author Nathan P
 *
 */
public abstract class AbstractDataModel {

	/**
	 *
	 *
	 * @return An HTML string representing this data object
	 */
	public abstract String renderToHtml();
}
