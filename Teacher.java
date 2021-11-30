package model;

public class Teacher {
	int kyouinId;
	int kyoukaId;
	String kyoukaName;
	public Teacher() {}
	public Teacher(int kyouinId,int kyoukaId) {
		this.kyouinId=kyouinId;
		this.kyoukaId=kyoukaId;
	}
	public int getKyouinId() {
		return kyouinId;
	}
	public void setKyouinId(int kyouinId) {
		this.kyouinId = kyouinId;
	}
	public int getKyoukaId() {
		return kyoukaId;
	}
	public void setKyoukaId(int kyoukaId) {
		this.kyoukaId = kyoukaId;
	}
	public String getKyoukaName() {
		return kyoukaName;
	}
	public void setKyoukaName(String kyoukaName) {
		this.kyoukaName = kyoukaName;
	}


}
