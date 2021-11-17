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
	int gakkaId;

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
	public Kyouin(int gakkaId,String gakkaName,int gender,String kyouinName,int kyouinId) {
		this.gakkaId = gakkaId;
		this.gakkaName = gakkaName;
		this.gender = gender;
		this.kyouinName = kyouinName;
		this.kyouinId = kyouinId;
	}
	public Kyouin(int kyouinId, String kanSei, String kanMei, String huriSei, String huriMei, int gakkaId) {
		this.kyouinId = kyouinId;
		this.kanSei = kanSei;
		this.kanMei = kanMei;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.gakkaId = gakkaId;
	}
	public Kyouin(int kyouinId, String password, String kanSei, String kanMei, String huriSei,String huriMei, int gender, int gakkaId) {
		this.kyouinId = kyouinId;
		this.password = password;
		this.kanSei = kanSei;
		this.kanMei = kanMei;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.gender = gender;
		this.gakkaId = gakkaId;
	}
	public Kyouin(String kanSei,String kanMei,String huriSei,String huriMei,int gakkaId) {
		this.kanSei = kanSei;
		this.kanMei = kanMei;
		this.huriSei = huriSei;
		this.huriMei = huriMei;
		this.gakkaId = gakkaId;
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
	public int getGakkaId() {
		return gakkaId;
	}


}
