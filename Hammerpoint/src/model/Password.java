package model;

public class Password {

		private int siskanid;
		private String name;
		private String pass;


		public Password() {}
		public Password(int siskanid,String name,String pass) {
			this.siskanid=siskanid;
			this.name=name;
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
