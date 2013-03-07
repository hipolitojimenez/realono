package com.nioos.realono.feeds;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.FetcherException;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

import com.nioos.realono.data.NewsRecord;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;



/**
 * Base class for rss readers.
 * 
 * @author Hipolito Jimenez.
 */
public abstract class AbstractRssReader {
	
	
	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(AbstractRssReader.class);
	
	
	/**
	 * Feed fetcher.
	 */
	private transient FeedFetcher feedFetcher = new HttpURLFeedFetcher();
	
	
	/**
	 * Feed URL.
	 */
	private transient URL feedUrl;
	
	
	/**
	 * Get all the feeds as a NewsRecord list.
	 * 
	 * @return the feeds as a NewsRecord list.
	 */
	public final List<NewsRecord> getAllRecords() {
		final List<NewsRecord> result = new ArrayList<NewsRecord>();
		SyndFeed feeds;
		try {
			feeds = feedFetcher.retrieveFeed(feedUrl);
			final List<SyndEntry> entries = getFeedEntries(feeds);
			addEntriesToResult(entries, result);
		} catch (IllegalArgumentException iae) {
			LOG.error("Illegal Argument", iae);
		} catch (IOException ioe) {
			LOG.error("Input/Output Error", ioe);
		} catch (FeedException feedEx) {
			LOG.error("Feed Error", feedEx);
		} catch (FetcherException fetchEx) {
			LOG.error("Fetcher Error", fetchEx);
		}
		Collections.reverse(result);
		return result;
	}
	
	
	/**
	 * Add the valid rss entries to the NewsRecord list.
	 * 
	 * @param entries the rss entries.
	 * @param result the NewsRecord list.
	 */
	private void addEntriesToResult(final List<SyndEntry> entries,
			final List<NewsRecord> result) {
		for (SyndEntry entry : entries) {
			final NewsRecord record = buildNewsRecordFromEntry(entry);
			if (record != null) {
				result.add(record);
			}
		}
	}
	
	
	/**
	 * Abstract method to build a NewsRecord from a rss entry.
	 * 
	 * @param entry the rss entry.
	 * @return the NewsRecord or null if the entry is not valid.
	 */
	protected abstract NewsRecord buildNewsRecordFromEntry(SyndEntry entry);
	
	
	/**
	 * Gets the feed entries.
	 * 
	 * @param feeds the feeds object.
	 * @return the entries list.
	 */
	@SuppressWarnings("unchecked")
	private List<SyndEntry> getFeedEntries(final SyndFeed feeds) {
		return feeds.getEntries();
	}
	
	
	/**
	 * Sets the feed URL.
	 * @param theFeedUrl the feed URL.
	 */
	public final void setFeedUrl(final URL theFeedUrl) {
		feedUrl = theFeedUrl;
	}
	
	
	/**
	 * Sets the feed fetcher.
	 * 
	 * @param theFeedFetcher the feed fetcher.
	 */
	public final void setFeedFetcher(final FeedFetcher theFeedFetcher) {
		feedFetcher = theFeedFetcher;
	}
	
	
}
