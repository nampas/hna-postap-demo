package com.holynamespostap.demo.route;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.holynamespostap.demo.storage.StorageFactory;
import com.holynamespostap.demo.util.AppSettings;
import com.holynamespostap.demo.util.HtmlUtil;

/**
 * Servlet implementation class
 */
@WebServlet("/college-application-task")
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

	}
}
