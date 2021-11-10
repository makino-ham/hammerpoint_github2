package model;

public class Gakka {
	//フィールド
	int gakkaId;
	String gakkaName;

	//コンストラクタ
	public Gakka() {}
	public Gakka(int gakkaId, String gakkaName) {
		this.gakkaId = gakkaId;
		this.gakkaName = gakkaName;
	}
	public Gakka(String gakkaName) {
		this.gakkaName = gakkaName;
	}
	public int getGakkaId() {
		return gakkaId;
	}
	public String getGakkaName() {
		return gakkaName;
	}


}
