package com.nioos.realono.feeds;



import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.SyndFeedInfo;

import com.nioos.realono.data.NewsRecord;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;



/**
 * EmtRssReader junit tests.
 * 
 * @author Hipolito Jimenez.
 */
public class EmtRssReaderTest {
	
	
	/**
	 * News Description.
	 */
	private static final String DESCRIPCION = "descripción";
	
	
	/**
	 * News Link.
	 */
	private static final String LINK = "link";
	
	
	/**
	 * News Title.
	 */
	private static final String TITLE = "title";
	
	
	/**
	 * Test getAllRecords().
	 * 
	 * @throws MalformedURLException on error.
	 */
	@Test
	public final void testGetAllRecords() throws MalformedURLException {
		final EmtRssReader emsRssReader = new EmtRssReader();
		final FeedFetcherCache localCache = new HashMapFeedInfoCache();
		final SyndFeedInfo syndFeedInfo = new SyndFeedInfo();
		final SyndFeed feeds = new SyndFeedImpl();
		final List<SyndEntryImpl> entries = new ArrayList<SyndEntryImpl>();
		//
		final SyndEntryImpl entry01 = new SyndEntryImpl();
		final List<SyndContentImpl> contents = new ArrayList<SyndContentImpl>();
		final SyndContentImpl content01 = new SyndContentImpl();
		content01.setValue(DESCRIPCION);
		contents.add(content01);
		entry01.setContents(contents);
		entry01.setPublishedDate(new Date(0));
		entry01.setLink(LINK);
		entry01.setTitle(TITLE);
		entries.add(entry01);
		//
		feeds.setEntries(entries);
		syndFeedInfo.setSyndFeed(feeds);
		localCache.setFeedInfo(new URL(EmtRssReader.EMT_RSS_URL), syndFeedInfo);
		final FeedFetcher feedFetcher = new InternalFeedFetcher(localCache);
		emsRssReader.setFeedFetcher(feedFetcher);
		//
		final List<NewsRecord> records = emsRssReader.getAllRecords();
		assertEquals("invalid number of records", 1, records.size());
		//
		final NewsRecord record = records.get(0);
		final String description = record.getDescription();
		assertEquals("invalid description", DESCRIPCION + "...", description);
		final String link = record.getLink();
		assertEquals("invalid link", LINK, link);
		final String title = record.getTitle();
		assertEquals("invalid title", TITLE, title);
		final char realFake = record.getRealFake();
		assertEquals("invalid realFake", 'f', realFake);
	}
	
	
}
