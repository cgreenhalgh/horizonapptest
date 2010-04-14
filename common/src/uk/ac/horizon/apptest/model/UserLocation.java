/**
 * 
 */
package uk.ac.horizon.apptest.model;

import javax.jdo.annotations.PersistenceCapable;

/**
 * @author cmg
 *
 */
@PersistenceCapable
public class UserLocation {
	/** user id */
	protected String user_id;
	/** region id */
	protected String region_id;
	/** default cons. */
	public UserLocation() {
		super();
	}
	/**
	 * @param userId
	 * @param regionId
	 */
	public UserLocation(String userId, String regionId) {
		super();
		user_id = userId;
		region_id = regionId;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param userId the user_id to set
	 */
	public void setUser_id(String userId) {
		user_id = userId;
	}
	/**
	 * @return the region_id
	 */
	public String getRegion_id() {
		return region_id;
	}
	/**
	 * @param regionId the region_id to set
	 */
	public void setRegion_id(String regionId) {
		region_id = regionId;
	}
	
}
