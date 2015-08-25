package com.holynamespostap.demo.util;

public class HtmlUtil {

	/**
	 * Renders out the HTML head. Several things to note:
	 * TODO explain what the <title> tag does
	 * @return
	 */
	public String buildHtmlHead() {
		return new StringBuilder()
			.append("<head>")
				.append("<title>HNA College Application Tool</title>")
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

	public String buildApplicationForm() {
		return null;
	}

	public String buildTaskForm() {
		return null;
	}
}