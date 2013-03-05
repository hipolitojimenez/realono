package com.nioos.realono.data;



/**
 * Represents a news record.
 * 
 * @author Hipolito Jimenez
 */
public class NewsRecord {
	
	
	public static final int TITLE_REC_LEN = 1024;
	
	
	private static final String TITLE_FMT = "%1$" + TITLE_REC_LEN + "s";
	
	
	public static final int DESC_REC_LEN = 4096;
	
	
	public static final int TOTAL_REC_LEN = TITLE_REC_LEN + DESC_REC_LEN + 4;
	
	
	/**
	 * Record identifier.
	 */
	private final int id; // NOPMD
	
	
	/**
	 * News title.
	 */
	private final String title;
	
	
	/**
	 * News little description.
	 */
	private final String description;
	
	
	/**
	 * Is the news real or fake?
	 */
	private final char realFake;
	
	
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
	public int hashCode() {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsRecord other = (NewsRecord) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (realFake != other.realFake)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	public static String formatTitle(String theTitle) {
		String result = theTitle;
		if (theTitle.length() > TITLE_REC_LEN) {
			result = theTitle.substring(0, TITLE_REC_LEN);
		}
		return String.format(TITLE_FMT, result);
	}
	
	
}
