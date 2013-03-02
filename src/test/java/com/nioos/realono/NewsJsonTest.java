package com.nioos.realono;



import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;



/**
 * Test for the NewsJson class.
 * 
 * @author Hipolito Jimenez
 */
public class NewsJsonTest {
	
	
	/**
	 * Test for the GetNextRandomNewsInJsonFormat method.
	 * 
	 * @throws UnsupportedEncodingException never
	 */
	@Test
	public final void testGetNextRandomNewsInJsonFormat()
			throws UnsupportedEncodingException {
		//
		final String expected = "{\"descripcion\":\"descripción\",\"id\":1,\"realFake\":\"r\",\"titulo\":\"título\"}"; // NOPMD
		//
		final NewsJson newsJson = new NewsJson();
		final byte[] buffer = newsJson.getNextRandomNewsInJsonFormat();
		final String actual = new String(buffer, "UTF8");
		assertEquals("news failed", expected, actual);
	}
	
	
}
