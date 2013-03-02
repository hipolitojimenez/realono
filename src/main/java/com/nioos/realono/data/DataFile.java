package com.nioos.realono.data;



/**
 * Represents the data file.
 * 
 * Access the data (read and writes) in NewsRecord format.
 * @author Hipolito Jimenez
 */
public class DataFile {
	
	
	/**
	 * Get the next random news from the data file.
	 * 
	 * @return the NewsRecord with the next random news.
	 */
	public final NewsRecord getNextRandomNews() {
		// TODO Auto-generated method stub
		final NewsRecord result =
				new NewsRecord(1, "título", "descripción", 'r');
		return result;
	}
	
	
}
