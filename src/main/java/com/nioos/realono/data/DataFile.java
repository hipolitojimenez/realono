package com.nioos.realono.data;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Represents the data file.
 * 
 * Access the data (read and writes) in NewsRecord format.
 * @author Hipolito Jimenez
 */
public class DataFile {
	
	
	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(DataFile.class);
	
	
	private static final long RECORD_LEN = 1; //TODO
	
	
	private RandomAccessFile raf = null;
	
	
	private int numberOfRecords;
	
	
	private Random random = new Random();
	
	
	public DataFile(String newsDataFilePath) {
		try {
			raf = new RandomAccessFile(newsDataFilePath, "rw");
		} catch (FileNotFoundException fnfe) {
			LOG.fatal("Cannot access data file !!!", fnfe);
			throw new IllegalArgumentException("Cannot access data file !!!",
				fnfe);
		}
		try {
			long len = raf.length();
			numberOfRecords = (int) (len / RECORD_LEN);
		} catch (IOException ioe) {
			LOG.fatal("Cannot read data file !!!", ioe);
			throw new IllegalStateException("Cannot read data file !!!", ioe);
		}
		loadRssData();
	}
	
	
	public void close() {
		try {
			raf.close();
		} catch (IOException ioe) {
			LOG.fatal("Cannot close data file !!!", ioe);
			throw new IllegalStateException("Cannot close data file !!!", ioe);
		}
	}
	
	
	private void loadRssData() {
		loadElMundoTodayRssData();
		//TODO
		//loadNotElMundoTodayRssData();
	}
	
	
	private void loadElMundoTodayRssData() {
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * Get the next random news from the data file.
	 * 
	 * @return the NewsRecord with the next random news.
	 */
	public final NewsRecord getNextRandomNews() {
		int nextId = 1;
		if (numberOfRecords > 1) {
			nextId = random.nextInt(numberOfRecords - 1) + 1;
		}
		//TODO
		final NewsRecord result =
				new NewsRecord(nextId, "título", "descripción", 'r');
		return result;
	}
	
	
}
