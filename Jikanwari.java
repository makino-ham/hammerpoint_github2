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
	String youbiName;

	public Jikanwari() {}
	public Jikanwari(int kyoukaId, int youbiId, int jugyouSuu, int jigen) {
		this.youbiId = youbiId;
		this.kyoukaId = kyoukaId;
		this.jugyouSuu = jugyouSuu;
		this.jigen = jigen;
	}


	public Jikanwari(int youbiId,String youbiName) {
		this.youbiId=youbiId;
		this.youbiName=youbiName;
	}

	public String getYoubiName() {
		return youbiName;
	}
	public void setYoubiName(String youbiName) {
		this.youbiName = youbiName;
	}
	public void setYoubiId(int youbiId) {
		this.youbiId = youbiId;
	}
	public void setJigen(int jigen) {
		this.jigen = jigen;
	}
	public void setKyoukaId(int kyoukaId) {
		this.kyoukaId = kyoukaId;
	}
	public void setKyoukaName(String kyoukaName) {
		this.kyoukaName = kyoukaName;
	}
	public void setGakunen(int gakunen) {
		this.gakunen = gakunen;
	}
	public void setSenkouId(int senkouId) {
		this.senkouId = senkouId;
	}
	public void setSenkouName(String senkouName) {
		this.senkouName = senkouName;
	}
	public void setJugyouSuu(int jugyouSuu) {
		this.jugyouSuu = jugyouSuu;
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
