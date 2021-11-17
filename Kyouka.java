package model;

public class Kyouka {
	int kyoukaId;
	String kyoukaName;
	int classId;
	int kyouinId;
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
	public Kyouka(int kyoukaId, String kyoukaName, int kyouinId, int classId) {
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
		this.kyouinId = kyouinId;
		this.classId = classId;
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

}



