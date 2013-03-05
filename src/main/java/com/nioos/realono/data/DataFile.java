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
	
	
	/**
	 * The file containing the records.
	 */
	private final transient RandomAccessFile raf;
	
	
	/**
	 * Number of records in the file.
	 */
	private transient int numberOfRecords;
	
	
	/**
	 * Random number generator.
	 */
	private final transient Random random = new Random();
	
	
	/**
	 * Constructor.
	 * 
	 * @param newsDataFilePath the data file path.
	 */
	public DataFile(final String newsDataFilePath) {
		try {
			raf = new RandomAccessFile(newsDataFilePath, "rw");
		} catch (FileNotFoundException fnfe) {
			LOG.fatal("Cannot access data file !!!", fnfe);
			throw new IllegalStateException("Cannot access data file !!!",
				fnfe);
		}
		try {
			final long len = raf.length();
			numberOfRecords = (int) (len / NewsRecord.TOTAL_REC_LEN);
		} catch (IOException ioe) {
			cannotReadDataFile(ioe);
		}
		loadRssData();
	}
	
	
	/**
	 * Utility method to log data file read errors.
	 * 
	 * @param ioe the error.
	 */
	private void cannotReadDataFile(final IOException ioe) {
		LOG.fatal("Cannot read data file !!!", ioe);
		throw new IllegalStateException("Cannot read data file !!!", ioe);
	}
	
	
	/**
	 * Close the file.
	 */
	public final void close() {
		try {
			synchronized (raf) {
				raf.close();
			}
		} catch (IOException ioe) {
			LOG.fatal("Cannot close data file !!!", ioe);
			throw new IllegalStateException("Cannot close data file !!!", ioe);
		}
	}
	
	
	/**
	 * Loads the data in the file.
	 * 
	 * The data is retrieved from rss.
	 */
	private void loadRssData() {
		loadElMundoTodayRssData();
		//TODO
		//loadNotElMundoTodayRssData();
	}
	
	
	/**
	 * Retrieve the data from the http://www.elmundotoday.es rss.
	 */
	private void loadElMundoTodayRssData() {
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * Get the next random news from the data file.
	 * 
	 * @return the NewsRecord with the next random news.
	 */
	public final NewsRecord getNextRandomNews() {
		int nextId = 0; // NOPMD
		if (numberOfRecords > 1) {
			nextId = random.nextInt(numberOfRecords - 1);
		}
		final int currentPosition = nextId * NewsRecord.TOTAL_REC_LEN;
		NewsRecord result = null; // NOPMD
		try {
			synchronized (raf) {
				raf.seek(currentPosition);
				//
				final byte[] titleBuffer = new byte[NewsRecord.TITLE_FIELD_LEN];
				raf.readFully(titleBuffer);
				final String fullTitle = new String(titleBuffer, "UTF8");
				final String title = fullTitle.trim();
				//
				final byte[] descBuffer = new byte[NewsRecord.DESC_FIELD_LEN];
				raf.readFully(descBuffer);
				final String fullDesc = new String(descBuffer, "UTF8");
				final String description = fullDesc.trim();
				//
				final char realOrFake = raf.readChar();
				//
				result = new NewsRecord(nextId, title, description, // NOPMD
					realOrFake);
			}
		} catch (IOException ioe) {
			cannotReadDataFile(ioe);
		}
		return result;
	}
	
	
}
