package model;

public class Senkou {
	//フィールド
	int senkouId;
	String senkouName;
	int gakkaId;
	int senkouFlag;

	//コンストラクタ
	public Senkou() {}
	public Senkou(int senkouId, String senkouName) {
		this.senkouId = senkouId;
		this.senkouName = senkouName;
	}
	public Senkou(int senkouId, String senkouName, int gakkaId, int senkouFlag) {
		this.senkouId = senkouId;
		this.senkouName = senkouName;
		this.gakkaId = gakkaId;
		this.senkouFlag = senkouFlag;
	}
	public Senkou(String senkouName, int gakkaId) {
		this.senkouName = senkouName;
		this.gakkaId = gakkaId;
	}
	public Senkou(int senkouId) {
		this.senkouId = senkouId;
	}
	public int getSenkouId() {
		return senkouId;
	}
	public String getSenkouName() {
		return senkouName;
	}
	public int getSenkouFlag() {
		return senkouFlag;
	}
	public int getGakkaId() {
		return gakkaId;
	}
	public void setSenkouId(int senkouId) {
		this.senkouId = senkouId;
	}
	public void setSenkouName(String senkouName) {
		this.senkouName = senkouName;
	}
	public void setSenkouFlag(int senkouFlag) {
		this.senkouFlag = senkouFlag;
	}
	public void setGakkaId(int gakkaId) {
		this.gakkaId = gakkaId;
	}

}
