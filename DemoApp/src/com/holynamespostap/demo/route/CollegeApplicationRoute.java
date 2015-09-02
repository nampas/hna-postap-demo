package com.holynamespostap.demo.route;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel;
import com.holynamespostap.demo.storage.StorageFactory;
import com.holynamespostap.demo.storage.StorageInterface;
import com.holynamespostap.demo.util.AppSettings;
import com.holynamespostap.demo.util.HtmlUtil;
import com.holynamespostap.demo.util.HttpUtil;

/**
 * Servlet implementation class
 */
@WebServlet("/college-application/*")
public class CollegeApplicationRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HtmlUtil htmlUtil;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeApplicationRoute() {
        super();
        htmlUtil = new HtmlUtil();
        StorageFactory.initializeStorage(AppSettings.STORAGE_METHOD);
    }

	/**
	 * Executed when a client issues an Http GET to /college-application
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response) throws ServletException,
															IOException
	{
		// Nothing really to do here
	}

	/**
	 * Executed when a client issues an Http POST to /college-application
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response) throws ServletException,
															IOException
	{
    	// Pull the data out of the request
		String collegeName = request.getParameter("collegeName");
		String schoolType = request.getParameter("schoolType");
		String addmitted = request.getParameter("admitted");
		String dueDate = request.getParameter("dueDate");

		String username = HttpUtil.extractUsernameFromRequest(request);

		// Get the storage object and save the data
		StorageInterface storage = StorageFactory.getInstance();
		storage.addApplication(
			collegeName,
			CollegeApplicationCategoryModel.getCategory(schoolType),
			username
		);

		// Redirect back to the main page. The client will reload and render
		// this user's new content
		HttpUtil.redirectToIndex(response, username);
	}
}
