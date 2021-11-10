package model;

public class Senkou {
	//フィールド
	int senkouId;
	String senkouName;

	//コンストラクタ
	public Senkou() {}
	public Senkou(int senkouId, String senkouName) {
		this.senkouId = senkouId;
		this.senkouName = senkouName;
	}
	public Senkou(String senkouName) {
		this.senkouName = senkouName;
	}
	public int getSenkouId() {
		return senkouId;
	}
	public String getSenkouName() {
		return senkouName;
	}


}
