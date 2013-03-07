package com.nioos.realono.servlets;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nioos.realono.NewsJson;



/**
 * Servlet that returns a random news from the data file.
 */
public class NewsJsonServlet extends HttpServlet {
    
    
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5034965722025202666L;
	
	
	/**
	 * This is the real working object.
	 */
	private transient NewsJson newsJson;
	
	
    /**
     * Servlet init.
     * 
     * Setup the real path for the data file.
     * @throws ServletException on error.
     */
    @Override
	public final void init() throws ServletException {
		super.init();
		final String dataPath = getServletContext().getRealPath("/WEB-INF/")
			+ "/";
    	newsJson = new NewsJson(dataPath);
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
	protected final void doGet(final HttpServletRequest request,
				final HttpServletResponse response)
			throws ServletException, IOException {
		final String contentType = NewsJson.CONTENT_TYPE;
		response.setContentType(contentType);
		final byte[] buffer = newsJson.getNextRandomNewsInJsonFormat();
		response.getOutputStream().write(buffer);
	}
	
	
	@Override
	public final void destroy() {
		super.destroy();
		newsJson.stop();
	}
	
	
}
