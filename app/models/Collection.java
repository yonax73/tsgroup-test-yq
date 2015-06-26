/**
 * 
 */
package models;

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
public class Collection {

	public static final int TYPE = 1;
	public static final int COLOR = 2;
	private int id;
	private MasterValue type;
	private MasterValue color;
	private int row;
	private String name;
	private String brand;

	public Collection(int id) {
		this.id = id;
	}

	public Collection() {
		// TODO Auto-generated constructor stub
	}

	public Collection(int id, int row) {
		this.id = id;
		this.row = row;
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
	 * @return the type
	 */
	public MasterValue getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MasterValue type) {
		this.type = type;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
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
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public MasterValue getColor() {
		return color;
	}

	public void setColor(MasterValue color) {
		this.color = color;
	}

}
