package model;

public class ClassRoom {
	//フィールド
	int classId;
	int gakkaId;
	int senkouId;
	String className;
	int gakunen;
	int kyouinId;

	//コンストラクタ
	public ClassRoom() {}
	public ClassRoom(int gakkaId, int senkouId, String className, int gakunen, int kyouinId) {
		this.gakkaId = gakkaId;
		this.senkouId = senkouId;
		this.className = className;
		this.gakunen = gakunen;
		this.kyouinId = kyouinId;
	}
	public ClassRoom(int classId, String className) {
		this.classId = classId;
		this.className = className;
	}
	public int getClassId() {
		return classId;
	}
	public int getGakkaId() {
		return gakkaId;
	}
	public int getSenkouId() {
		return senkouId;
	}
	public String getClassName() {
		return className;
	}
	public int getGakunen() {
		return gakunen;
	}
	public int getKyouinId() {
		return kyouinId;
	}


}
