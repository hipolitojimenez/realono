package com.nioos.realono;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Returns random news from the data file.
 * 
 * @author Hipolito Jimenez
 */
public class NewsJson {
	
	
	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(NewsJson.class);
	
	
	/**
	 * JSON mime content type.
	 */
	public static final String CONTENT_TYPE = "application/json";
	
	
	/**
	 * Get the next random news from the data file in json format.
	 * 
	 * @return the byte array that contains the json string.
	 */
	public final byte[] getNextRandomNewsInJsonFormat() {
		final String str = Thread.currentThread().getName();
		byte[] buffer = null; // NOPMD
		try {
			buffer = str.getBytes("UTF8");
		} catch (UnsupportedEncodingException uee) {
			LOG.fatal("This should never happen", uee);
		}
		// TODO Auto-generated method stub
		return buffer;
	}
	
	
}
