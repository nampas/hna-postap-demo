package com.holynamespostap.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holynamespostap.demo.util.HtmlUtil;

/**
 * Servlet implementation class
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HtmlUtil htmlUtil;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        htmlUtil = new HtmlUtil();
    }

	/**
	 * Executed when a client issues an Http GET to /index
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response) throws ServletException,
															IOException
	{
		StringBuilder strBuilder = new StringBuilder();

		// Open up the HTML document with correct identifier
		strBuilder.append("<!DOCTYPE html><html>");

		// Add in the header. This contains metadata about the page, and tells
		// the client about any other resources it should grab (javascript, html
		// css, etc) to successfully render this page
		strBuilder.append(htmlUtil.buildHtmlHead());

		// Start the body of the html document. This is where all of the visible
		// content on the page is defined
		strBuilder.append("<body>");

		// Include the header. This is the content we'll put at the top of the
		// page
		strBuilder.append(htmlUtil.buildBodyHeader("tealks12"));

		// Include the form for adding applications
		strBuilder.append(htmlUtil.buildApplicationForm());

		// And the form for adding application tasks
		strBuilder.append(htmlUtil.buildTaskForm());

		// Don't forget to close all the tags that were opened!
		strBuilder.append("</body>");
		strBuilder.append("</html>");

		// Finally, let's write out the entire html string we've built to the
		// body of the http response
		response.getWriter().println(strBuilder.toString());
	}
}
