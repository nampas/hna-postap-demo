package com.holynamespostap.demo.route;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class
 */
@WebServlet("/static/*")
public class StaticRoute extends HttpServlet {

	private static final long serialVersionUID = 1L;


	// This method is called by the servlet container to process a GET request.
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    	String requestUrl = req.getRequestURL().toString();

    	int startPath = requestUrl.indexOf("/static/");
    	requestUrl = requestUrl.substring(startPath + 8, requestUrl.length());

	    // Get the absolute path of the image
	    ServletContext sc = getServletContext();

	    String filename = sc.getRealPath("../../../../../../../hna-postap-demo/DemoApp/res/" + requestUrl);

	    // Get the MIME type of the image
	    String mimeType = sc.getMimeType(filename);
	    if (mimeType == null) {
	        sc.log("Could not get MIME type of "+filename);
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return;
	    }

	    // Set content type
	    resp.setContentType(mimeType);

	    // Set content size
	    File file = new File(filename);
	    resp.setContentLength((int)file.length());

	    // Open the file and output streams
	    FileInputStream in = new FileInputStream(file);
	    OutputStream out = resp.getOutputStream();

	    // Copy the contents of the file to the output stream
	    byte[] buf = new byte[1024];
	    int count = 0;
	    while ((count = in.read(buf)) >= 0) {
	        out.write(buf, 0, count);
	    }
	    in.close();
	    out.close();
	}









//	/**
//	 * Serves up static assets
//	 *
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//    @Override
//	protected void doGet(HttpServletRequest request,
//						HttpServletResponse response) throws ServletException,
//															IOException
//    {
//    	String workingDir = System.getProperty("user.dir");
//
//    	String requestUrl = request.getRequestURL().toString();
//
//    	int startPath = requestUrl.indexOf("/static/");
//    	requestUrl = requestUrl.substring(startPath + 8, requestUrl.length());
//
//    	String asset = getTextAsset(Paths.get("./" + requestUrl));
//
//    	response.getWriter().println(asset);
//	}
//
//    private String getTextAsset(Path path) {
//    	StringBuilder bufferedFile = new StringBuilder();
//    	try (BufferedReader reader = Files.newBufferedReader(path)) {
//    	    String line = null;
//    	    while ((line = reader.readLine()) != null) {
//    	        bufferedFile.append(line);
//    	    }
//    	} catch (IOException x) {
//    	    System.err.format("IOException: %s%n", x);
//    	}
//
//    	return bufferedFile.toString();
//    }

}
