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
	String kyouinName;
	String gakkaName;

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
	public Kyouin(String gakkaName,int gender,String kyouinName,int kyouinId) {
		this.gakkaName = gakkaName;
		this.gender = gender;
		this.kyouinName = kyouinName;
		this.kyouinId = kyouinId;
	}
	public Kyouin(int kyouinId, String kanSei, String kanMei, String huriSei, String huriMei, String gakkaName) {
		this.kyouinId = kyouinId;
		this.kanSei = kanSei;
		this.kanMei = kanMei;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.gakkaName = gakkaName;
	}
	public Kyouin(int kyouinId, String password, String kanSei, String kanMei, String huriSei,String huriMei, int gender, String gakkaName) {
		this.kyouinId = kyouinId;
		this.password = password;
		this.kanSei = kanSei;
		this.kanMei = kanMei;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.gender = gender;
		this.gakkaName = gakkaName;
	}
	public String getKyouinName() {
		return kyouinName;
	}
	public String getGakkaName() {
		return gakkaName;
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
