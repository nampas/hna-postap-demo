package com.holynamespostap.demo.dataModel;

/**
 * Represents a task for a college application
 *
 * @author Nathan P
 *
 */
public class CollegeApplicationTaskModel extends AbstractDataModel {

	private String dueDate;
	private String completedDate;
	private String name;
	private String note;

	public CollegeApplicationTaskModel(String name){
		this.setName(name);
	}

	public CollegeApplicationTaskModel(String name, String dueDate){
		this.setName(name);
		this.setDueDate(dueDate);
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
	public String getCompletedDate() {
		return completedDate;
	}

	/**
	 * @param completedDate the completedDate to set
	 */
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String renderToHtml() {
		StringBuilder strBuilder = new StringBuilder()
			.append("<h4>" + name + "</h4>")
			.append("<p>Due Date: " + dueDate.toString() + "</p>");

		if(completedDate != null) {
			strBuilder.append("<p>Completed Date: " + completedDate.toString() + "</p>");
		}

		if(note != null && !note.isEmpty()) {
			strBuilder.append("<p>" + note + "</p>");
		}

		return strBuilder.toString();
	}

}
