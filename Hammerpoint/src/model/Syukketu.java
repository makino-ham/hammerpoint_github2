package model;

import java.util.Date;

public class Syukketu {
	//フィールド
	String gakusekiId;
	int kyoukaId;
	int jigen;
	Date hiduke;
	int syukketu;
	String riyuu;

	//コンストラクタ
	Syukketu(){}
	Syukketu(String gakusekiId, int kyoukaId, int jigen, Date hiduke, int syukketu) {
		this.gakusekiId = gakusekiId;
		this.kyoukaId = kyoukaId;
		this.jigen = jigen;
		this.hiduke = hiduke;
		this.syukketu = syukketu;
	}

	public String getGakusekiId() {
		return gakusekiId;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public int getJigen() {
		return jigen;
	}
	public Date getHiduke() {
		return hiduke;
	}
	public int getSyukketu() {
		return syukketu;
	}
	public String getRiyuu() {
		return riyuu;
	}

}
