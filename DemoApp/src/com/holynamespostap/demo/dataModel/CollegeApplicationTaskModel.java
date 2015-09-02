package com.holynamespostap.demo.dataModel;

import java.util.Date;

/**
 * Represents a task for a college application
 *
 * @author Nathan P
 *
 */
public class CollegeApplicationTaskModel extends AbstractDataModel {

	private Date dueDate;
	private Date completedDate;
	private String name;
	private String note;

	public CollegeApplicationTaskModel(String name){
		this.setName(name);
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the completedDate
	 */
	public Date getCompletedDate() {
		return completedDate;
	}

	/**
	 * @param completedDate the completedDate to set
	 */
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String renderToHtml() {
		StringBuilder strBuilder = new StringBuilder()
			.append("<h4>" + name + "</h4>")
			.append("<p>Due Date: " + dueDate.toString() + "</p>")
			.append("<p>Completed Date: " + completedDate.toString() + "</p>");

		if(note != null && !note.isEmpty()) {
			strBuilder.append("<p>" + note + "</p>");
		}

		return strBuilder.toString();
	}

}
