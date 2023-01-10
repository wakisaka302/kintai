package kintai;

public class EmployeeData {
	private  int bangou;
	private int kihon;
	private String seibetu;
	private String name;
	String Dammy3;
	
	public EmployeeData(int bangou, int kihon, String seibetu, String name) {
		super();
		this.bangou = bangou;
		this.kihon = kihon;
		this.seibetu = seibetu;
		this.name = name;
	}
	
	public  int getBangou() {
		return bangou;
	}
	public void setBangou(int bangou) {
		this.bangou = bangou;
	}
	public int getKihon() {
		return kihon;
	}
	public void setKihon(int kihon) {
		this.kihon = kihon;
	}
	public String getSeibetu() {
		return seibetu;
	}
	public void setSeibetu(String seibetu) {
		this.seibetu = seibetu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
