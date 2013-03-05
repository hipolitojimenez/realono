package com.nioos.realono;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
			new NewsRecord(0, "título", "descripción", 'r');
		//
		final DataFile dataFile = new DataFile(NEWS_DATA_FILE_NAME);
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
		String titulo = "título"; // NOPMD
		raf.write(NewsRecord.formatTitle(titulo));
		String descripcion = "descripción"; // NOPMD
		raf.write(NewsRecord.formatDescription(descripcion));
		raf.writeChar('r');
		//
		raf.close();
		return tmpFile;
	}
	
	
}
