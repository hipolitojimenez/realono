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
	private final int id; // NOPMD
	
	
	/**
	 * News title.
	 */
	private final String titulo;
	
	
	/**
	 * News little description.
	 */
	private final String descripcion;
	
	
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + id;
		result = prime * result + realFake;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id != other.id)
			return false;
		if (realFake != other.realFake)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
}
