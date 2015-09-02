package com.holynamespostap.demo.util;

public class HtmlUtil {

	private static final String BOOSTRAP_CSS_URL
		= "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css";

	private static final String CUSTOM_CSS_URL = "/static/index.css";

	/**
	 * Renders out the HTML head. Several things to note:
	 * TODO explain what the <title> tag does
	 * @return
	 */
	public String buildHtmlHead() {
		return new StringBuilder()
			.append("<head>")
				.append("<title>HNA College Application Tool</title>")
				.append("<link rel=\"stylesheet\" href=\"" + BOOSTRAP_CSS_URL + "\">")
				.append("<link rel=\"stylesheet\" href=\"" + CUSTOM_CSS_URL + "\">")
			.append("</head")
			.toString();
	}

	/**
	 * Renders out the header of the body. This is
	 * @param userName
	 * @return
	 */
	public String buildBodyHeader(String userName) {
		return new StringBuilder()
			.append("<h1>HNA College Application Tool</h1>")
			.append("<p align=\"right\">")
				.append("You are logged in as <strong>" + userName + "</strong>")
			.append("</p>")
			.toString();
	}

	/**
	 * Renders out the college application form, which will look like this in
	 * flat HTML:
	 *
	 *	<h3>Please enter a new college application</h3>
	 * 	<form>
	 *		College Name: <input type="text" name="CollegeName">
	 *		<br>
	 *		School_Type: <select name="School_Type">
	 *			<option value="Safety">Safety</option>
	 *			<option value="Match">Match</option>
	 *			<option value="Reach">Reach</option>
	 *		</select>
	 *		<br>
	 *		Admitted: <input type="checkbox" name="Admitted">
	 *		<br>
	 *		Due Date: <input type="text" name="DueDate">
	 *		<br>
	 *		<input type="submit">
	 *	</form>
	 *
	 * @return exactly that ^
	 */
	public String buildApplicationForm(String username) {
		return new StringBuilder()
			.append("<h3>Please enter a new college application</h3>")
			.append("<form action=\"/college-application/" + username + "\" method=\"post\">")
				.append("College Name: <input type=\"text\" name=\"collegeName\">")
				.append("<br>")
				.append("School Type: <select name=\"schoolType\">")
					.append("<option value=\"Safety\">Safety</option>")
					.append("<option value=\"Match\">Match</option>")
					.append("<option value=\"Reach\">Reach</option>")
				.append("</select>")
				.append("<br>")
				.append("Admitted: <input type=\"checkbox\" name=\"admitted\">")
				.append("<br>")
				.append("Due Date: <input type=\"text\" name=\"dueDate\">")
				.append("<br>")
				.append("<input type=\"submit\">")
			.append("</form>")
			.toString();
	}

	/**
	 * Renders out the task form, which looks like:
	 *
	 * 	<h3>Please Input Task details...</h3>
	 * 	<form action="/college-application-task/{username}" method="post">
	 * 		Task: <input type="text" name="TaskInfo">
	 * 		<br>
	 * 		Due Date: <input type="text" name="DueDate">
	 * 		<br>
	 * 		<input type="submit">
	 * 	</form>
	 *
	 * @return precisely that ^
	 */
	public String buildTaskForm(String username, String schoolName) {
		String postUrl = "/college-application-task/" + username
							+ "?schoolName=" + schoolName;

		return new StringBuilder()
			.append("<h3>Please Input Task details...</h3>")
			.append("<form action=\"" + postUrl + "\" method=\"post\">")
				.append("Task: <input type=\"text\" name=\"taskInfo\">")
				.append("<br>")
				.append("Due Date: <input type=\"text\" name=\"dueDate\">")
				.append("<br>")
				.append("<input type=\"submit\">")
			.append("</form>")
			.toString();
	}
}