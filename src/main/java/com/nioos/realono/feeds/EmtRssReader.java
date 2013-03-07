package com.nioos.realono.feeds;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;

import com.nioos.realono.data.NewsRecord;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;



/**
 * El Mundo Today Rss Reader.
 * 
 * @author Hipolito Jimenez.
 */
public class EmtRssReader extends AbstractRssReader {
	
	
	/**
	 * El Mundo Today Rss URL.
	 */
	public static final String EMT_RSS_URL =
		"http://www.elmundotoday.com/feed/";
	
	
	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(EmtRssReader.class);
	
	
	/**
	 * Constructor.
	 */
	public EmtRssReader() {
		super();
		try {
			setFeedUrl(new URL(EMT_RSS_URL));
		} catch (MalformedURLException mue) {
			LOG.fatal("Invalid URL !!!", mue);
			throw new IllegalStateException("Invalid URL !!!", mue);
		}
	} 
	
	
	@Override
	protected final NewsRecord buildNewsRecordFromEntry(final SyndEntry entry) {
		NewsRecord result = null; // NOPMD
		final SyndContent content = (SyndContent) entry.getContents().get(0);
		final String value = content.getValue();
		final String description = Jsoup.parse(value).text();
		if (description.length() > 1) {
			final String link = entry.getLink();
			final String title = entry.getTitle();
			final Date date = entry.getPublishedDate();
			result = new NewsRecord(0, title, description, 'f', link, date);
		}
		return result;
	}
	
	
}
