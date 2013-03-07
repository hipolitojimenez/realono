package com.nioos.realono.data;



import java.io.UnsupportedEncodingException;
import java.util.Date;



/**
 * Represents a news record.
 * 
 * @author Hipolito Jimenez
 */
public class NewsRecord {
	
	
	/**
	 * Title field length.
	 */
	public static final int TITLE_FIELD_LEN = 256;
	
	
	/**
	 * Title formating in the file.
	 */
	private static final String TITLE_FMT = "%1$-" + TITLE_FIELD_LEN + "s";
	
	
	/**
	 * Link field length.
	 */
	public static final int LINK_FIELD_LEN = 256;
	
	
	/**
	 * Link formating in the file.
	 */
	private static final String LINK_FMT = "%1$-" + LINK_FIELD_LEN + "s";
	
	
	/**
	 * Description field length.
	 */
	public static final int DESC_FIELD_LEN = 512;
	
	
	/**
	 * Description formating in the file.
	 */
	private static final String DESC_FMT = "%1$-" + DESC_FIELD_LEN + "s";
	
	
	/**
	 * char length in the file.
	 */
	private static final int CHAR_LEN = 2;
	
	
	/**
	 * Record length in the file.
	 */
	public static final int TOTAL_REC_LEN = TITLE_FIELD_LEN + DESC_FIELD_LEN
		+ CHAR_LEN + LINK_FIELD_LEN;
	
	
	/**
	 * Record identifier.
	 */
	private final transient int id; // NOPMD
	
	
	/**
	 * News title.
	 */
	private final transient String title;
	
	
	/**
	 * News little description.
	 */
	private final transient String description;
	
	
	/**
	 * Is the news real or fake?
	 */
	private final transient char realFake;
	
	
	/**
	 * News link.
	 */
	private final transient String link;
	
	
	/**
	 * News date.
	 */
	private final transient long date;
	
	
	/**
	 * Constructor.
	 * 
	 * @param theId the record id.
	 * @param theTitle the news title.
	 * @param theDesc the news description.
	 * @param real is the news real or fake?
	 * @param theLink the news link.
	 * @param theDate the news publish date.
	 */
	public NewsRecord(final int theId, final String theTitle,
			final String theDesc, final char real, final String theLink,
			final Date theDate) {
		id = theId;
		title = theTitle;
		description = theDesc;
		realFake = real;
		link = theLink;
		date = theDate.getTime();
	}
	
	
	/**
	 * Gets the record id.
	 * @return the record id.
	 */
	public final int getId() {
		return id;
	}
	
	
	/**
	 * Gets the news title.
	 * @return the news title.
	 */
	public final String getTitle() {
		return title;
	}
	
	
	/**
	 * Gets the news link.
	 * @return the news link.
	 */
	public final String getLink() {
		return link;
	}
	
	
	/**
	 * Gets the news date.
	 * @return the news date.
	 */
	public final long getDate() {
		return date;
	}
	
	
	/**
	 * Gets the news description.
	 * @return the news description.
	 */
	public final String getDescription() {
		return description + "...";
	}
	
	
	/**
	 * Is the news real or fake?
	 * @return 'r' (real) for real, 'f' (fake) for fake.
	 */
	public final char getRealFake() {
		return realFake;
	}
	
	
	/**
	 * Formats the title to be saved in the file.
	 * 
	 * @param theTitle the title.
	 * @return the formated title.
	 */
	public static byte[] formatTitle(final String theTitle) {
		return formatString(theTitle, TITLE_FIELD_LEN, TITLE_FMT);
	}
	
	
	/**
	 * Formats the link to be saved in the file.
	 * 
	 * @param theLink the link.
	 * @return the formated link.
	 */
	public static byte[] formatLink(final String theLink) {
		return formatString(theLink, LINK_FIELD_LEN, LINK_FMT);
	}
	
	
	/**
	 * Formats the description to be saved in the file.
	 * 
	 * @param theDescription the description.
	 * @return the formated description.
	 */
	public static byte[] formatDescription(final String theDescription) {
		return formatString(theDescription, DESC_FIELD_LEN, DESC_FMT);
	}
	
	
	/**
	 * Formats a string.
	 * 
	 * @param theString the String to be formated.
	 * @param len the length of the returned byte array.
	 * @param format the format used.
	 * @return the formated byte array.
	 */
	private static byte[] formatString(final String theString, final int len,
			final String format) {
		String result = theString; // NOPMD
		if (theString.length() > len) {
			result = theString.substring(0, len); // NOPMD
		}
		result = String.format(format, result);
		final byte[] buffer = new byte[len];
		try {
			final byte[] resultBytes = result.getBytes("UTF8");
			System.arraycopy(resultBytes, 0, buffer, 0, len);
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalArgumentException("UTF8 is not supported !!!",
				uee);
		}
		return buffer;
	}
	
	
	@Override
	@SuppressWarnings("PMD")
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		return result;
	}
	
	
	@Override
	@SuppressWarnings("PMD")
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NewsRecord other = (NewsRecord) obj;
		if (link == null) {
			if (other.link != null) {
				return false;
			}
		} else if (!link.equals(other.link)) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public final String toString() {
		return "NewsRecord [link=" + link + "]";
	}
	
	
}
