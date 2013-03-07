package com.nioos.realono;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import org.junit.Test;

import com.nioos.realono.data.DataFile;
import com.nioos.realono.data.NewsRecord;



/**
 * DataFile junit tests.
 * 
 * @author Hipolito Jimenez.
 */
public class DataFileTest {
	
	
	/**
	 * News link.
	 */
	private static final String LINK = "http://realono.nioos.com/";
	
	
	/**
	 * News Description.
	 */
	private static final String DESCRIPCION = "descripción";
	
	
	/**
	 * News Title.
	 */
	private static final String TITULO = "título";
	
	
	/**
	 * Data file name used for the tests.
	 */
	static final String NEWS_DATA_FILE_NAME = "news.data"; // NOPMD
	
	
	/**
	 * Test getNextRandomNews().
	 * 
	 * @throws IOException on error.
	 */
	@Test
	public final void testGetNextRandomNews() throws IOException {
		//
		final File tmpFile = prepareTestDataFile();
		//
		final NewsRecord expected =
			new NewsRecord(0, TITULO, DESCRIPCION, 'r', LINK, new Date(0L));
		//
		final DataFile dataFile = new DataFile("", false);
		final NewsRecord actual = dataFile.getNextRandomNews();
		//
		assertEquals("data file failed", expected, actual);
		//
		dataFile.close();
		final boolean deleted = tmpFile.delete();
		if (!deleted) {
			throw new IOException("Cannot delete file '" + NEWS_DATA_FILE_NAME
				+ "'");
		}
	}
	
	
	/**
	 * Prepare the data file used in the test.
	 * 
	 * @return the file prepared.
	 * @throws IOException on error
	 */
	static File prepareTestDataFile() throws IOException { // NOPMD
		final File tmpFile = new File(NEWS_DATA_FILE_NAME);
		//
		final RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
		raf.write(NewsRecord.formatTitle(TITULO));
		raf.write(NewsRecord.formatDescription(DESCRIPCION));
		raf.write(NewsRecord.formatLink(LINK));
		raf.writeChar('r');
		//
		raf.close();
		return tmpFile;
	}
	
	
}
