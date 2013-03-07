package com.nioos.realono;



import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nioos.realono.data.DataFile;
import com.nioos.realono.data.NewsRecord;



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
	 * Data file for data access.
	 */
	private final transient DataFile dataFile;
	
	
	/**
	 * Constructor.
	 * 
	 * @param dataPath the real path for the data file.
	 */
	public NewsJson(final String dataPath) {
		dataFile = new DataFile(dataPath);
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param dataPath the real path for the data file.
	 * @param loadData load rss data on startup?
	 */
	public NewsJson(final String dataPath, final boolean loadData) {
		dataFile = new DataFile(dataPath, loadData);
	}
	
	
	/**
	 * Get the next random news from the data file in json format.
	 * 
	 * @return the byte array that contains the json string.
	 */
	public final byte[] getNextRandomNewsInJsonFormat() {
		final String news = getNextRandomNewsInStringFormat();
		byte[] buffer = null; // NOPMD
		try {
			buffer = news.getBytes("UTF8"); // NOPMD
		} catch (UnsupportedEncodingException uee) {
			LOG.fatal("UTF8 is not supported !!!", uee);
			throw new IllegalArgumentException("UTF8 is not supported !!!",
				uee);
		}
		return buffer;
	}
	
	
	/**
	 * Get the next random news from the data file in string format.
	 * 
	 * @return the string with the next random news.
	 */
	private String getNextRandomNewsInStringFormat() {
		final NewsRecord newsRecord = getNextRandomNewsInRecordFormat();
		return JSONObject.fromObject(newsRecord).toString();
	}
	
	
	/**
	 * Get the next random news from the data file in record format.
	 * 
	 * @return the NewsRecord with the next random news.
	 */
	private NewsRecord getNextRandomNewsInRecordFormat() {
		return dataFile.getNextRandomNews();
	}
	
	
	/**
	 * Close the underlying DataFile object.
	 */
	public final void stop() {
		dataFile.close();
	}
	
	
}
