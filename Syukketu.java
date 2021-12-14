package model;

public class Syukketu {
	//フィールド
	String gakusekiId;
	String seitoName;
	int kyoukaId;
	String kyoukaName;
	int jigen;
	String hiduke;
	int syukketu;
	String riyuu;
	int count;

	//コンストラクタ
	public Syukketu(){}
	public Syukketu(String gakusekiId, int kyoukaId, int jigen, String hiduke, int syukketu) {
		this.gakusekiId = gakusekiId;
		this.kyoukaId = kyoukaId;
		this.jigen = jigen;
		this.hiduke = hiduke;
		this.syukketu = syukketu;
	}
	public Syukketu(String gakusekiId, String seitoName, int kyoukaId, String kyoukaName, int jigen, String hiduke, int syukketu) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
		this.jigen = jigen;
		this.hiduke = hiduke;
		this.syukketu = syukketu;
	}
	public Syukketu(String gakusekiId, int kyoukaId, int jigen, String hiduke) {
		this.gakusekiId = gakusekiId;
		this.kyoukaId = kyoukaId;
		this.jigen = jigen;
		this.hiduke = hiduke;
	}
	public Syukketu(String gakusekiId, int kyoukaId, int jigen, String hiduke, int syukketu, String riyuu) {
		this.gakusekiId = gakusekiId;
		this.kyoukaId = kyoukaId;
		this.jigen = jigen;
		this.hiduke = hiduke;
		this.syukketu = syukketu;
		this.riyuu = riyuu;
	}
	public Syukketu(String gakusekiId, int syukketu) {
		this.gakusekiId = gakusekiId;
		this.syukketu = syukketu;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getGakusekiId() {
		return gakusekiId;
	}
	public void setGakusekiId(String gakusekiId) {
		this.gakusekiId = gakusekiId;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public void setKyoukaId(int kyoukaId) {
		this.kyoukaId = kyoukaId;
	}
	public int getJigen() {
		return jigen;
	}
	public void setJigen(int jigen) {
		this.jigen = jigen;
	}
	public String getHiduke() {
		return hiduke;
	}
	public void setHiduke(String hiduke) {
		this.hiduke = hiduke;
	}
	public int getSyukketu() {
		return syukketu;
	}
	public void setSyukketu(int syukketu) {
		this.syukketu = syukketu;
	}
	public String getRiyuu() {
		return riyuu;
	}
	public void setRiyuu(String riyuu) {
		this.riyuu = riyuu;
	}
	public String getSeitoName() {
		return seitoName;
	}
	public void setSeitoName(String seitoName) {
		this.seitoName = seitoName;
	}
	public String getKyoukaName() {
		return kyoukaName;
	}
	public void setKyoukaName(String kyoukaName) {
		this.kyoukaName = kyoukaName;
	}


}
