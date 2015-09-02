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
import com.holynamespostap.demo.storage.StorageInterface;
import com.holynamespostap.demo.util.AppSettings;
import com.holynamespostap.demo.util.HtmlUtil;
import com.holynamespostap.demo.util.HttpUtil;

/**
 * Servlet implementation class
 */
@WebServlet("/college-application-task/*")
public class CollegeApplicationTaskRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HtmlUtil htmlUtil;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeApplicationTaskRoute() {
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
	 * Executed when a client issues an Http GET to /college-application
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request,
						HttpServletResponse response) throws ServletException,
															IOException
	{
    	String username = HttpUtil.extractUsernameFromRequest(request);
    	List<CollegeApplicationModel> applications
    					= StorageFactory.getInstance().getApplications(username);

    	String schoolName = request.getParameter("schoolName");
    	for(CollegeApplicationModel app : applications) {
    		if(app.getCollegeName().equalsIgnoreCase(schoolName)) {
    			String name = request.getParameter("taskInfo");
    			String dueDate = request.getParameter("dueDate");

    			app.addTask(new CollegeApplicationTaskModel(name, dueDate));

    			StorageInterface storage = StorageFactory.getInstance();
    			storage.updateApplication(app);

    			break;
    		}
    	}

    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	HttpUtil.redirectToIndex(response, username);
	}
}
