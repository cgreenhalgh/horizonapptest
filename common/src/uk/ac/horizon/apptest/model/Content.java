/**
 * 
 */
package uk.ac.horizon.apptest.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author cmg
 *
 */
@PersistenceCapable
public class Content {
	/** content id */
	@PrimaryKey
	protected String id;
	/** description */
	protected String description;
	/** default cons */
	public Content() {
		super();
	}
	
	/**
	 * @param id
	 * @param description
	 */
	public Content(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Content [description=" + description + ", id=" + id + "]";
	}
	
	
}
