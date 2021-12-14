package model;

public class Kyouka {
	int kyoukaId;
	String kyoukaName;
	int classId;
	int kyouinId;
	int senkouId;
	String senkouName;
	int gakunen;
	int jigen;
	public Kyouka() {}

	public Kyouka(int kyoukaId, String kyoukaName, int classId) {
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
		this.classId = classId;
	}
	public Kyouka(int kyouinId, int classId, String kyoukaName) {
		this.kyouinId = kyouinId;
		this.classId = classId;
		this.kyoukaName = kyoukaName;
	}
	public Kyouka(int kyoukaId, String kyoukaName) {
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
	}
	public  Kyouka(String kyoukaName, int kyoukaId) {
		this.kyoukaName=kyoukaName;
		this.kyoukaId=kyoukaId;
	}
	public Kyouka(int kyoukaId, String kyoukaName, int kyouinId, int classId) {
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
		this.kyouinId = kyouinId;
		this.classId = classId;
	}
	public Kyouka(String senkouName, int classId,int gakunen,String kyoukaName,int kyoukaId,int jigen) {
		this.senkouName=senkouName;
		this.classId = classId;
		this.gakunen=gakunen;
		this.kyoukaName=kyoukaName;
		this.kyoukaId=kyoukaId;
		this.jigen=jigen;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public String getKyoukaName() {
		return kyoukaName;
	}

	public int getClassId() {
		return classId;
	}

	public int getKyouinId() {
		return kyouinId;
	}

	public int getSenkouId() {
		return senkouId;
	}

	public String getSenkouName() {
		return senkouName;
	}

	public int getGakunen() {
		return gakunen;
	}

	public int getJigen() {
		return jigen;
	}

	public void setKyoukaId(int kyoukaId) {
		this.kyoukaId = kyoukaId;
	}

	public void setKyoukaName(String kyoukaName) {
		this.kyoukaName = kyoukaName;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public void setKyouinId(int kyouinId) {
		this.kyouinId = kyouinId;
	}

	public void setSenkouId(int senkouId) {
		this.senkouId = senkouId;
	}

	public void setSenkouName(String senkouName) {
		this.senkouName = senkouName;
	}

	public void setGakunen(int gakunen) {
		this.gakunen = gakunen;
	}

	public void setJigen(int jigen) {
		this.jigen = jigen;
	}

}



