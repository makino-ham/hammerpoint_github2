package model;

public class Seito {
	//フィールド
	String gakusekiId;
	String seitoName;
	String mail;
	String doubutuName;
	String className;
	int classId;
	int doubutuId;
	int gender;
	int seitoFlag;

	//コンストラクタ
	public Seito() {}
	public Seito(String gakusekiId, String seitoName, String mail, int classId, int doubutuId, int gender) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.mail = mail;
		this.classId = classId;
		this.doubutuId = doubutuId;
		this.gender = gender;
	}
	public Seito(String gakusekiId, String seitoName, int gender, String doubutuName) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.gender = gender;
		this.doubutuName = doubutuName;
	}
	public Seito(String gakusekiId, String seitoName, int gender, String mail, int doubutuId, String doubutuName, String className) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.gender = gender;
		this.mail = mail;
		this.doubutuId = doubutuId;
		this.doubutuName = doubutuName;
		this.className = className;
	}
	//生徒変更用
	public Seito(String gakusekiId, String seitoName, String mail, int doubutuId) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.mail = mail;
		this.doubutuId = doubutuId;
	}
	public Seito(String gakusekiId, String seitoName, int gender) {
		this.gakusekiId = gakusekiId;
		this.seitoName = seitoName;
		this.gender = gender;
	}
	//クラスID用
	public Seito(int classId) {
		this.classId = classId;
	}
	public String getGakusekiId() {
		return gakusekiId;
	}
	public String getSeitoName() {
		return seitoName;
	}
	public String getMail() {
		return mail;
	}
	public int getClassId() {
		return classId;
	}
	public int getDoubutuId() {
		return doubutuId;
	}
	public int getGender() {
		return gender;
	}
	public int getSeitoFlag() {
		return seitoFlag;
	}
	public String getDoubutuName() {
		return doubutuName;
	}
	public String getClassName() {
		return className;
	}
	public void setGakusekiId(String gakusekiId) {
		this.gakusekiId = gakusekiId;
	}
	public void setSeitoName(String seitoName) {
		this.seitoName = seitoName;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setDoubutuName(String doubutuName) {
		this.doubutuName = doubutuName;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public void setDoubutuId(int doubutuId) {
		this.doubutuId = doubutuId;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setSeitoFlag(int seitoFlag) {
		this.seitoFlag = seitoFlag;
	}

}
