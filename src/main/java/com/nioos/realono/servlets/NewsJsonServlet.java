package com.nioos.realono.servlets;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet that returns a random news from the data file.
 */
public class NewsJsonServlet extends HttpServlet {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5034965722025202666L;
	
	
	private transient final Object obj;
	
	
	/**
     * Default constructor. 
     */
    public NewsJsonServlet() {
    	super();
    	obj = new Object();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * Called by the server (via the service method) to allow a servlet to
	 * handle a GET request.
	 * 
	 * @param request an HttpServletRequest object that contains the request
	 * the client has made of the servlet.
	 * @param response an HttpServletResponse object that contains the
	 * response the servlet sends to the client.
	 * @throws IOException if an input or output error is detected when the
	 * servlet handles the GET request.
	 * @throws ServletException if the request for the GET could not be
	 * handled
	 */
	protected void doGet(final HttpServletRequest request,
				final HttpServletResponse response)
			throws ServletException, IOException {
		obj.toString();
		// TODO Auto-generated method stub
	}
	
	
}
