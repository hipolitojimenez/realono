package com.nioos.realono;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

import com.nioos.realono.data.DataFile;
import com.nioos.realono.data.NewsRecord;



public class DataFileTest {
	
	
	@Test
	public void testGetNextRandomNews() throws IOException {
		//
		String newsDataFileName = "news.data";
		File tmpFile = new File(newsDataFileName);
		tmpFile.delete();
		//
		RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw");
		String titulo = "título";
		titulo = String.format("%1$1024s", titulo); //TODO move to NewsRecord
		raf.write(titulo.getBytes("UTF8"));
		String descripcion = "descripción";
		descripcion = String.format("%1$4096s", descripcion);
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
		tmpFile.delete();
	}
	
	
}
