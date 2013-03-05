package com.nioos.realono.data;



import java.io.UnsupportedEncodingException;



/**
 * Represents a news record.
 * 
 * @author Hipolito Jimenez
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class NewsRecord {
	
	
	/**
	 * Title field length.
	 */
	public static final int TITLE_FIELD_LEN = 1024;
	
	
	/**
	 * Title formating in the file.
	 */
	private static final String TITLE_FMT = "%1$-" + TITLE_FIELD_LEN + "s";
	
	
	/**
	 * Description field length.
	 */
	public static final int DESC_FIELD_LEN = 4096;
	
	
	/**
	 * Description formating in the file.
	 */
	private static final String DESC_FMT = "%1$-" + DESC_FIELD_LEN + "s";
	
	
	/**
	 * char length in the file.
	 */
	private static final int CHAR_LEN = 4;
	
	
	/**
	 * Record length in the file.
	 */
	public static final int TOTAL_REC_LEN = TITLE_FIELD_LEN + DESC_FIELD_LEN
		+ CHAR_LEN;
	
	
	/**
	 * Record identifier.
	 */
	private final int id; // NOPMD
	
	
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
	 * Constructor.
	 * 
	 * @param theId the record id.
	 * @param theTitle the news title.
	 * @param theDesc the news description.
	 * @param real is the news real or fake?
	 */
	public NewsRecord(final int theId, final String theTitle,
			final String theDesc, final char real) {
		id = theId;
		title = theTitle;
		description = theDesc;
		realFake = real;
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
	 * Gets the news description.
	 * @return the news description.
	 */
	public final String getDescription() {
		return description;
	}
	
	
	/**
	 * Is the news real or fake?
	 * @return 'r' (real) for real, 'f' (fake) for fake.
	 */
	public final char getRealFake() {
		return realFake;
	}
	
	
	@Override
	@SuppressWarnings("PMD")
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + realFake;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (realFake != other.realFake) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
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
	
	
}
