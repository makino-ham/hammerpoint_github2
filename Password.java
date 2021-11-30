package model;

public class Password {

		private int siskanid;
		private int kyouinid;
		private String name;
		private String pass;
		private String kansei;
		private String kanmei;


		public Password() {}
		public Password(int siskanid,String name,String pass) {
			this.siskanid=siskanid;
			this.name=name;
			this.pass=pass;
		}
		public int getKyouinid() {
			return kyouinid;
		}
		public void setKyouinid(int kyouinid) {
			this.kyouinid = kyouinid;
		}
		public String getKansei() {
			return kansei;
		}
		public void setKansei(String kansei) {
			this.kansei = kansei;
		}
		public String getKanmei() {
			return kanmei;
		}
		public void setKanmei(String kanmei) {
			this.kanmei = kanmei;
		}
		public Password(int kyouinid,String kansei,String kanmei,String pass) {
			this.kyouinid=kyouinid;
			this.kansei=kansei;
			this.kanmei=kanmei;
			this.pass=pass;
		}
		public Password(int siskanid, String pass) {
			this.siskanid=siskanid;
			this.pass=pass;
		}
		public int getSiskanid() {
			return siskanid;
		}
		public void setSiskanid(int siskanid) {
			this.siskanid = siskanid;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}


}
