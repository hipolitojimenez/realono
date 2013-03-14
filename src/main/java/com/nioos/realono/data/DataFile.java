package com.nioos.realono.data;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nioos.realono.feeds.EmtRssReader;
import com.nioos.realono.feeds.PemtRssReader;
import com.nioos.realono.feeds.AbstractRssReader;



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
	 * El Mundo Today Rss Reader.
	 */
	private final transient AbstractRssReader emtRss = new EmtRssReader();
	
	
	/**
	 * Parece Del Mundo Today Rss Reader.
	 */
	private final transient AbstractRssReader pemtRss = new PemtRssReader();
	
	
	/**
	 * Last emt date.
	 */
	private transient long lastEmtDate = 0;
	
	
	/**
	 * Last pemt date.
	 */
	private transient long lastPemtDate = 0;
	
	
	/**
	 * emt date file.
	 */
	private transient String emtDateFile;
	
	
	/**
	 * pemt date file.
	 */
	private transient String pemtDateFile;
	
	
	/**
	 * Scheduled Executor Service.
	 */
	private final transient ScheduledExecutorService executor =
		Executors.newSingleThreadScheduledExecutor();
	
	
	/**
	 * Delay in seconds (one day).
	 */
	private static final long DELAY = 60 * 60 * 24;
	
	
	/**
	 * Constructor.
	 * 
	 * @param dataPath the data path.
	 */
	public DataFile(final String dataPath) {
		this(dataPath, true);
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param dataPath the data path.
	 * @param loadData load rss data on startup?
	 */
	public DataFile(final String dataPath, final boolean loadData) {
		emtDateFile = dataPath + "emt.date";
		pemtDateFile = dataPath + "pemt.date";
		//
		try {
			raf = new RandomAccessFile(dataPath + "news.data", "rw");
		} catch (FileNotFoundException fnfe) {
			LOG.fatal("Cannot access data file !!!", fnfe);
			throw new IllegalStateException("Cannot access data file !!!",
				fnfe);
		}
		try {
			final long len = raf.length();
			numberOfRecords = (int) (len / NewsRecord.TOTAL_REC_LEN);
			if (LOG.isInfoEnabled()) {
				LOG.info("Number Of Records : " + numberOfRecords);
			}
		} catch (IOException ioe) {
			cannotReadDataFile(ioe);
		}
		if (loadData) {
			loadDates();
			loadRssData();
		}
		//
		final Runnable command = new Runnable() { // NOPMD
			public void run() {
				loadRssData();
			}
		};
		executor.scheduleWithFixedDelay(command, DELAY, DELAY,
			TimeUnit.SECONDS);
	}
	
	
	/**
	 * Load last dates from the date files.
	 */
	private void loadDates() {
		lastEmtDate = loadDate(emtDateFile);
		if (LOG.isInfoEnabled()) {
			LOG.info("Last Emt Date : " + new Date(lastEmtDate));
		}
		lastPemtDate = loadDate(pemtDateFile);
		if (LOG.isInfoEnabled()) {
			LOG.info("Last pEmt Date : " + new Date(lastPemtDate));
		}
	}
	
	
	/**
	 * Load last rss date from file.
	 * 
	 * @param dateFileName the date file name.
	 * @return the last date.
	 */
	private long loadDate(final String dateFileName) {
		long result = 0; // NOPMD
		try {
			final RandomAccessFile dateFile =
				new RandomAccessFile(dateFileName, "rw");
			result = dateFile.readLong();
			dateFile.close();
		} catch (FileNotFoundException fnfe) {
			LOG.error("Cannot find date file !!!", fnfe);
		} catch (IOException ioe) {
			LOG.error("Cannot read date file !!!", ioe);
		}
		return result;
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
		executor.shutdownNow();
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
		loadPareceDelMundoTodayRssData();
	}
	
	
	/**
	 * Retrieve the data from the http://www.elmundotoday.es rss.
	 */
	private void loadElMundoTodayRssData() {
		final List<NewsRecord> records = emtRss.getAllRecords();
		for (NewsRecord record : records) {
			final long recordDate = record.getDate();
			if (recordDate > lastEmtDate) {
				lastEmtDate = recordDate;
				saveRecord(record, 'f', emtDateFile);
			}
		}
	}
	
	
	/**
	 * Save the record to file.
	 * 
	 * @param record the record to be saved.
	 * @param realFake is real or fake?
	 * @param file the date file.
	 */
	private void saveRecord(final NewsRecord record, final char realFake,
			final String file) {
		try {
			synchronized (raf) {
				raf.seek(raf.length());
				raf.write(NewsRecord.formatTitle(record.getTitle()));
			  raf.write(NewsRecord.formatDescription(record.getDescription()));
				raf.write(NewsRecord.formatLink(record.getLink()));
				raf.writeChar(realFake);
				raf.getFD().sync();
				//
				numberOfRecords++;
			}
			//
			final RandomAccessFile dateFile = new RandomAccessFile(file, "rw");
			dateFile.writeLong(record.getDate());
			dateFile.close();
		} catch (IOException ioe) {
			LOG.fatal("Cannot write file !!!", ioe);
			throw new IllegalStateException("Cannot write file !!!", ioe);
		}
	}
	
	
	/**
	 * Retrieve the data from the http://parecedelmundotoday.tumblr.com/ rss.
	 */
	private void loadPareceDelMundoTodayRssData() {
		final List<NewsRecord> records = pemtRss.getAllRecords();
		for (NewsRecord record : records) {
			final long recordDate = record.getDate();
			if (recordDate > lastPemtDate) {
				lastPemtDate = recordDate;
				saveRecord(record, 'r', pemtDateFile);
			}
		}
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
		return getNewsById(nextId);
	}
	
	
	/**
	 * Get the news from the data file.
	 * 
	 * @param nid the news identifier.
	 * @return the NewsRecord with the next random news.
	 */
	private NewsRecord getNewsById(final int nid) {
		final int currentPosition = nid * NewsRecord.TOTAL_REC_LEN;
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
				final byte[] linkBuffer = new byte[NewsRecord.LINK_FIELD_LEN];
				raf.readFully(linkBuffer);
				final String fullLink = new String(linkBuffer, "UTF8");
				final String link = fullLink.trim();
				//
				final char realOrFake = raf.readChar();
				//
				result = new NewsRecord(nid, title, description, // NOPMD
					realOrFake, link, new Date(0L));
			}
		} catch (IOException ioe) {
			cannotReadDataFile(ioe);
		}
		return result;
	}
	
	
	/**
	 * Get the news from the data file.
	 * 
	 * @param nid the news identifier.
	 * @return the NewsRecord with the next random news.
	 */
	public final NewsRecord getNewsById(final String nid) {
		return getNewsById(Integer.parseInt(nid));
	}
	
	
}
