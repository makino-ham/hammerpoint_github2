package model;

public class Doubutu {
	//フィールド
	int doubutuId;
	String doubutuName;

	//コンストラクタ
	public Doubutu() {}
	public Doubutu(int doubutuId, String doubutuName) {
		this.doubutuId = doubutuId;
		this.doubutuName = doubutuName;
	}

	public int getDoubutuId() {
		return doubutuId;
	}
	public String getDoubutuName() {
		return doubutuName;
	}

}
