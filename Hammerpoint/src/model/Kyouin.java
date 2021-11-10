package model;

public class Kyouin {
	//フィールド
	int kyouinId;
	String password;
	int kyoukaId;
	int gender;
	String huriSei;
	String huriMei;
	String kanSei;
	String kanMei;

	//コンストラクタ
	public Kyouin() {}
	public Kyouin(int kyouinId, String password, int kyoukaId, int gender, String huriSei, String huriMei, String kanSei, String kanMei) {
		this.kyouinId = kyouinId;
		this.password = password;
		this.kyoukaId = kyoukaId;
		this.gender = gender;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.kanSei = kanSei;
		this.kanMei = kanMei;
	}
	public int getKyouinId() {
		return kyouinId;
	}
	public String getPassword() {
		return password;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public int getGender() {
		return gender;
	}
	public String getHuriSei() {
		return huriSei;
	}
	public String getHuriMei() {
		return huriMei;
	}
	public String getKanSei() {
		return kanSei;
	}
	public String getKanMei() {
		return kanMei;
	}


}
