package com.nioos.realono.feeds;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nioos.realono.data.NewsRecord;
import com.sun.syndication.feed.synd.SyndEntry;



/**
 * Parece Del Mundo Today Rss Reader.
 * 
 * @author Hipolito Jimenez
 */
public class PemtRssReader extends AbstractRssReader {
	
	
	/**
	 * The pages title.
	 */
	private static final String TITLE =
		"Parece del Mundo Today pero es VERDAD ";
	
	
	/**
	 * Parece Del Mundo Today Rss URL.
	 */
	public static final String PEMT_RSS_URL =
		"http://parecedelmundotoday.tumblr.com/rss";
	
	
	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(PemtRssReader.class);
	
	
	/**
	 * Constructor.
	 */
	public PemtRssReader() {
		super();
		try {
			setFeedUrl(new URL(PEMT_RSS_URL));
		} catch (MalformedURLException mue) {
			LOG.fatal("Invalid URL !!!", mue);
			throw new IllegalStateException("Invalid URL !!!", mue);
		}
	} 
	
	
	@Override
	protected final NewsRecord buildNewsRecordFromEntry(final SyndEntry entry) {
		NewsRecord result = null; // NOPMD
		final String title = entry.getTitle();
		final String originalLink = entry.getLink();
		String description = null; // NOPMD
		String link = null; // NOPMD
		try {
			final Document document = Jsoup.connect(originalLink).get();
			String bodyText = document.body().text(); // NOPMD
			bodyText = bodyText.replace(TITLE, "");
			bodyText = bodyText.replace(title, "");
			final int idxFuente = bodyText.indexOf("Fuente");
			if (idxFuente > 0) {
				description = bodyText.substring(0, idxFuente).trim();
			}
			//
			final Element lastLink = document.select("h3 > a[href]").last();
			link = lastLink.attr("href");
		} catch (IOException ioe) {
			LOG.error("Invalid link : " + originalLink, ioe);
		}
		if (description != null && link != null) {
			final Date date = entry.getPublishedDate();
			result = new NewsRecord(0, title, description, 'r', link, date);
		}
		return result;
	}
	
	
}
