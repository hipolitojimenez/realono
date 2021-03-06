package com.nioos.realono;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;



/**
 * Test for the NewsJson class.
 * 
 * @author Hipolito Jimenez
 */
public class NewsJsonTest {
	
	
	/**
	 * Test for the GetNextRandomNewsInJsonFormat method.
	 * @throws IOException on error.
	 */
	@Test
	public final void testGetNextRandomNewsInJsonFormat()
			throws IOException {
		//
		final File tmpFile = DataFileTest.prepareTestDataFile();
		//
		final String expected = "{\"date\":0,\"description\":\"descripción...\",\"id\":0,\"link\":\"http://realono.nioos.com/\",\"realFake\":\"r\",\"title\":\"título\"}"; // NOPMD
		//
		final NewsJson newsJson = new NewsJson("", false);
		final byte[] buffer = newsJson.getNextRandomNewsInJsonFormat();
		final String actual = new String(buffer, "UTF8");
		assertEquals("news failed", expected, actual);
		//
		newsJson.stop();
		final boolean deleted = tmpFile.delete();
		if (!deleted) {
			throw new IOException("Cannot delete file '"
				+ DataFileTest.NEWS_DATA_FILE_NAME + "'");
		}
	}
	
	
	/**
	 * Test for the getNewsbyIdInJsonFormat method.
	 * @throws IOException on error.
	 */
	@Test
	public final void testGetNewsbyIdInJsonFormat()
			throws IOException {
		//
		final File tmpFile = DataFileTest.prepareTestDataFile();
		//
		final String expected = "{\"date\":0,\"description\":\"descripción...\",\"id\":0,\"link\":\"http://realono.nioos.com/\",\"realFake\":\"r\",\"title\":\"título\"}"; // NOPMD
		//
		final NewsJson newsJson = new NewsJson("", false);
		final byte[] buffer = newsJson.getNewsbyIdInJsonFormat("0");
		final String actual = new String(buffer, "UTF8");
		assertEquals("news failed", expected, actual);
		//
		newsJson.stop();
		final boolean deleted = tmpFile.delete();
		if (!deleted) {
			throw new IOException("Cannot delete file '"
				+ DataFileTest.NEWS_DATA_FILE_NAME + "'");
		}
	}
	
	
}
