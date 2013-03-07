package com.nioos.realono.feeds;



import java.io.IOException;
import java.net.URL;

import org.rometools.fetcher.FetcherException;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;
import org.rometools.fetcher.impl.SyndFeedInfo;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;



/**
 * Fake feed checker used by the junit tests.
 * 
 * @author Hipolito Jimenez.
 */
class InternalFeedFetcher extends HttpURLFeedFetcher {
	
	
	/**
	 * Constructor.
	 * 
	 * @param localCache the local feed cache.
	 */
	public InternalFeedFetcher(final FeedFetcherCache localCache) {
		super(localCache);
	}
	
	
	/**
	 * Retrieve the feeds from the cache.
	 * 
	 * @param userAgent the user agent.
	 * @param feedUrl the url of the rss feed.
	 * @return the feeds.
	 * @throws IOException on error.
	 * @throws FeedException on error.
	 * @throws FetcherException on error.
	 */
	@Override
	public SyndFeed retrieveFeed(final String userAgent, final URL feedUrl)
			throws IOException, FeedException, FetcherException {
		final FeedFetcherCache cache = getFeedInfoCache();
		final SyndFeedInfo feedInfo = cache.getFeedInfo(feedUrl);
		return feedInfo.getSyndFeed();
	}
	
	
}
