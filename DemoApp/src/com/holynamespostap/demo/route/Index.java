package com.holynamespostap.demo.route;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holynamespostap.demo.dataModel.CollegeApplicationModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationTaskModel;
import com.holynamespostap.demo.storage.StorageFactory;
import com.holynamespostap.demo.util.AppSettings;
import com.holynamespostap.demo.util.HtmlUtil;
import com.holynamespostap.demo.util.HttpUtil;

/**
 * Servlet implementation class
 */
@WebServlet("/user/*")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HtmlUtil htmlUtil;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        htmlUtil = new HtmlUtil();
        StorageFactory.initializeStorage(AppSettings.STORAGE_METHOD);
    }

	/**
	 * Executed when a client issues an Http GET to /index
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response) throws ServletException,
															IOException
	{
    	String username = HttpUtil.extractUsernameFromRequest(request);

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
		strBuilder.append(htmlUtil.buildBodyHeader(username));

		// Include the form for adding applications
		strBuilder.append(htmlUtil.buildApplicationForm(username));

		// not sure if this belongs here or somewhere else
		strBuilder.append(buildApplicationList(username));

		// Don't forget to close all the tags that were opened!
		strBuilder.append("</body>");
		strBuilder.append("</html>");

		// Finally, let's write out the entire html string we've built to the
		// body of the http response
		response.getWriter().println(strBuilder.toString());
	}

	/**
	 *
	 * @return application list HTML
	 */
	private String buildApplicationList(String username) {
		StringBuilder builder = new StringBuilder();

		List<CollegeApplicationModel> applications
						= StorageFactory.getInstance().getApplications(username);

		for(CollegeApplicationModel application : applications){
			builder.append("<div class=\"application content-padding\">")
					.append(application.renderToHtml())
					.append("<div class=\"tasks\">")
						.append("<h4>Tasks</h4>");
			for(CollegeApplicationTaskModel task : application.getTasks()) {
				builder.append(task.renderToHtml());
			}

			// And the form for adding application tasks
			builder.append(htmlUtil.buildTaskForm(username, application.getCollegeName()))
					.append("</div>") // close the tasks div
					.append("</div>"); // close the application div
		}

		return builder.toString();
	}


}
