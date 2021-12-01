package model;

public class Doubutu {
	//フィールド
	int doubutuId;
	String doubutuName;
	String doubutuCount;
	int count;

	//コンストラクタ
	public Doubutu() {}
	public Doubutu(int doubutuId, String doubutuName) {
		this.doubutuId = doubutuId;
		this.doubutuName = doubutuName;
	}
	public Doubutu(String doubutuName, String doubutuCount) {
		this.doubutuName = doubutuName;
		this.doubutuCount = doubutuCount;
	}
	public Doubutu(int count) {
		this.count = count;
	}
	public Doubutu(String doubutuName) {
		this.doubutuName = doubutuName;
	}
	public int getDoubutuId() {
		return doubutuId;
	}
	public void setDoubutuId(int doubutuId) {
		this.doubutuId = doubutuId;
	}
	public String getDoubutuName() {
		return doubutuName;
	}
	public void setDoubutuName(String doubutuName) {
		this.doubutuName = doubutuName;
	}
	public String getDoubutuCount() {
		return doubutuCount;
	}
	public void setDoubutuCount(String doubutuCount) {
		this.doubutuCount = doubutuCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


}
