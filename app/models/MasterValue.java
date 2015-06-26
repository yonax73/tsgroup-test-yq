package models;

public class MasterValue {

	private int id;
	private int masterId;
	private String value1;
	private String value2;

	public MasterValue(int id) {
		this.masterId = id;
	}

	public MasterValue() {
		// TODO Auto-generated constructor stub
	}

	public MasterValue(int id, int masterId) {
		this.id = id;
		this.masterId = masterId;
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
	 * @return the masterId
	 */
	public int getMasterId() {
		return masterId;
	}

	/**
	 * @param masterId
	 *            the masterId to set
	 */
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	/**
	 * @return the value1
	 */
	public String getValue1() {
		return value1;
	}

	/**
	 * @param value1
	 *            the value1 to set
	 */
	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
}
