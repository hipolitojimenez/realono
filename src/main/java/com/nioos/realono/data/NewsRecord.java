package com.nioos.realono.data;



/**
 * Represents a news record.
 * 
 * @author Hipolito Jimenez
 */
public class NewsRecord {
	
	
	/**
	 * Record identifier.
	 */
	private final transient int id; // NOPMD
	
	
	/**
	 * News title.
	 */
	private final transient String titulo;
	
	
	/**
	 * News little description.
	 */
	private final transient String descripcion;
	
	
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
		titulo = theTitle;
		descripcion = theDesc;
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
	public final String getTitulo() {
		return titulo;
	}
	
	
	/**
	 * Gets the news description.
	 * @return the news description.
	 */
	public final String getDescripcion() {
		return descripcion;
	}
	
	
	/**
	 * Is the news real or fake?
	 * @return 'r' (real) for real, 'f' (fake) for fake.
	 */
	public final char getRealFake() {
		return realFake;
	}
	
	
}
