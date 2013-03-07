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
 * PemtRssReader junit tests.
 * 
 * @author Hipolito Jimenez.
 */
public class PemtRssReaderTest {
	
	
	/**
	 * News Description.
	 */
	private static final String DESCRIPCION = "La alcaldesa conservadora de Fuengirola declara el 14 de abril Día del Perro La alcaldesa de la localidad malagueña de Fuengirola, Esperanza Oña,  vuelve a protagonizar otro episodio de controversia al asegurar que el próximo 14 de abril, fecha que conmemora la proclamación de la II República Española, se celebrará el “día del perro” en Fuengirola, según publican fuentes locales. La edil popular, conocida como la Esperanza Aguirre andaluza, ha sido duramente criticada por este hecho en las redes sociales. Tras la recriminación en la red, decidió arreglar el entuerto afirmando que  Zapatero eligió el 20N para celebrar las elecciones generales. No es la primera vez que la alcaldesa de Fuengirola se ve envuelta en una polémica de este tipo. Tras la muerte, del ex ministro franquista, José Antonio Girón de Velasco, en 1995, Oña declaró a la Cadena Ser que, “Girón consiguió socialmente grandes avances aunque habrá personas que no querrán reconocerlo porque le vayan a tildar de no sé qué”. ";
	
	
	/**
	 * News Title.
	 */
	private static final String TITLE = "title";
	
	
	/**
	 * News Original Link.
	 */
	private static final String ORIGINAL_LINK =
		"http://parecedelmundotoday.tumblr.com/post/44612549624";
	
	
	/**
	 * News Link.
	 */
	private static final String LINK = "http://www.publico.es/espana/451633/la-alcaldesa-conservadora-de-fuengirola-declara-el-14-de-abril-dia-del-perro";
	
	
	/**
	 * Test getAllRecords().
	 * 
	 * @throws MalformedURLException on error.
	 */
	@Test
	public final void testGetAllRecords() throws MalformedURLException {
		final PemtRssReader pemsRssReader = new PemtRssReader();
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
		entry01.setLink(ORIGINAL_LINK);
		entry01.setTitle(TITLE);
		entries.add(entry01);
		//
		feeds.setEntries(entries);
		syndFeedInfo.setSyndFeed(feeds);
		localCache.setFeedInfo(new URL(PemtRssReader.PEMT_RSS_URL),
			syndFeedInfo);
		final FeedFetcher feedFetcher = new InternalFeedFetcher(localCache);
		pemsRssReader.setFeedFetcher(feedFetcher);
		//
		final List<NewsRecord> records = pemsRssReader.getAllRecords();
		assertEquals("invalid number of records", 1, records.size());
		//
		final NewsRecord record = records.get(0);
		final String description = record.getDescription();
		assertEquals("invalid description", DESCRIPCION, description);
		final String link = record.getLink();
		assertEquals("invalid link", LINK, link);
		final String title = record.getTitle();
		assertEquals("invalid title", TITLE, title);
		final char realFake = record.getRealFake();
		assertEquals("invalid realFake", 'r', realFake);
	}
	
	
}
