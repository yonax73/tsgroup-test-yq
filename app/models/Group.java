/**
 * 
 */
package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * user : yonatan<br/>
 * update date : Jun 20, 2015<br/>
 * update by : Yonatan Alexis Quintero Rodriguez<br/>
 * 
 * @created : Jun 20, 2015<br/>
 * @version : 0.1 <br/>
 * @author Yonatan Alexis Quintero Rodriguez
 * 
 */
public class Group {

	private int id;
	private String name;
	private int creationYear;
	private LocalDateTime starDate;
	private LocalDateTime expirationDate;
	private List<Collection> collections;
	private Collection collection;

	public Group(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the creationYear
	 */
	public int getCreationYear() {
		return creationYear;
	}

	/**
	 * @param creationYear
	 *            the creationYear to set
	 */
	public void setCreationYear(int creationYear) {
		this.creationYear = creationYear;
	}

	/**
	 * @return the starDate
	 */
	public LocalDateTime getStarDate() {
		return starDate;
	}

	/**
	 * @param starDate
	 *            the starDate to set
	 */
	public void setStarDate(LocalDateTime starDate) {
		this.starDate = starDate;
	}

	/**
	 * @return the expirationDate
	 */
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the collections
	 */
	public List<Collection> getCollections() {
		return collections;
	}

	/**
	 * @param collections
	 *            the collections to set
	 */
	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public void createCollection(int totalRows) {
		collections = new ArrayList<Collection>();
		for (int i = 0; i < totalRows + 1; i++) {
			collections.add(new Collection(0, i));
		}
	}

}
