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
	 * Test getNextRandomNews().
	 * 
	 * @throws IOException on error.
	 */
	@Test
	public final void testGetNextRandomNews() throws IOException {
		//
		String newsDataFileName = "news.data";
		File tmpFile = new File(newsDataFileName);
		boolean deleted = tmpFile.delete();
		if (!deleted) {
			System.err.println("Cannot delete file 'news.data'");
		}
		//
		RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
		String titulo = "título";
		titulo = NewsRecord.formatTitle(titulo);
		raf.write(titulo.getBytes("UTF8"));
		String descripcion = "descripción";
		descripcion = NewsRecord.formatDescription(descripcion);
		raf.write(descripcion.getBytes("UTF8"));
		raf.writeChar('r');
		//
		raf.close();
		//
		NewsRecord expected =
			new NewsRecord(1, "título", "descripción", 'r');
		//
		DataFile dataFile = new DataFile(newsDataFileName);
		NewsRecord actual = dataFile.getNextRandomNews();
		//
		assertEquals("data file failed", expected, actual);
		//
		dataFile.close();
		deleted = tmpFile.delete();
		if (!deleted) {
			System.err.println("Cannot delete file 'news.data'");
		}
	}
	
	
}
