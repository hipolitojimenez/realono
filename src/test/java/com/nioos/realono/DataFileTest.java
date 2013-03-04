package com.nioos.realono;



import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.nioos.realono.data.DataFile;
import com.nioos.realono.data.NewsRecord;



public class DataFileTest {
	
	
	@Test
	public void testGetNextRandomNews() {
		//
		String newsDataFileName = "news.data";
		File tmpFile = new File(newsDataFileName);
		tmpFile.delete();
		//
		NewsRecord expected =
			new NewsRecord(1, "título", "descripción", 'r');
		//
		DataFile dataFile = new DataFile(newsDataFileName);
		NewsRecord actual = dataFile.getNextRandomNews();
		//
		assertEquals("data file failed", expected, actual);
		//
		tmpFile.delete();
	}
	
	
}
