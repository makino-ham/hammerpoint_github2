package model;

public class Gakka {
	//フィールド
	int gakkaId;
	String gakkaName;
	int gakkaFlag;

	//コンストラクタ
	public Gakka() {}
	public Gakka(int gakkaId, String gakkaName) {
		this.gakkaId = gakkaId;
		this.gakkaName = gakkaName;
	}
	public Gakka(int gakkaId, String gakkaName, int gakkaFlag) {
		this.gakkaId = gakkaId;
		this.gakkaName = gakkaName;
		this.gakkaFlag = gakkaFlag;
	}
	public Gakka(int gakkaId) {
		this.gakkaId = gakkaId;
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
	public void setGakkaId(int gakkaId) {
		this.gakkaId = gakkaId;
	}
	public void setGakkaName(String gakkaName) {
		this.gakkaName = gakkaName;
	}
	public int getGakkaFlag() {
		return gakkaFlag;
	}
	public void setGakkaFlag(int gakkaFlag) {
		this.gakkaFlag = gakkaFlag;
	}


}
