package model;

public class Kyouka {
	int kyoukaId;
	String kyoukaName;
	int classId;

	public Kyouka() {}

	public Kyouka(int kyoukaId, String kyoukaName, int classId) {
		this.kyoukaId = kyoukaId;
		this.kyoukaName = kyoukaName;
		this.classId = classId;
	}
	public Kyouka(String kyoukaName, int kyoukaId) {
		this.kyoukaName = kyoukaName;
		this.kyoukaId = kyoukaId;
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
}



