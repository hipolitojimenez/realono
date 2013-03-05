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
		final File tmpFile = new File(NEWS_DATA_FILE_NAME);
		//
		final RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
		String titulo = "título"; // NOPMD
		titulo = NewsRecord.formatTitle(titulo);
		raf.write(titulo.getBytes("UTF8"));
		String descripcion = "descripción"; // NOPMD
		descripcion = NewsRecord.formatDescription(descripcion);
		raf.write(descripcion.getBytes("UTF8"));
		raf.writeChar('r');
		//
		raf.close();
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
	
	
}
