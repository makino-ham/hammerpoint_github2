package model;

public class Jikanwari {
	//フィールド
	int youbiId;
	int jigen;
	int kyoukaId;
	String kyoukaName;
	int gakunen;
	int senkouId;
	String senkouName;
	int jugyouSuu;

	public Jikanwari() {}
	public Jikanwari(int kyoukaId, int youbiId, int jugyouSuu, int jigen) {
		this.kyoukaId = kyoukaId;
		this.youbiId = youbiId;
		this.jugyouSuu = jugyouSuu;
		this.jigen = jigen;
	}



	public int getYoubiId() {
		return youbiId;
	}
	public int getJugyouSuu() {
		return jugyouSuu;
	}
	public int getJigen() {
		return jigen;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public String getKyoukaName() {
		return kyoukaName;
	}
	public int getGakunen() {
		return gakunen;
	}
	public int getSenkouId() {
		return senkouId;
	}
	public String getSenkouName() {
		return senkouName;
	}



}
