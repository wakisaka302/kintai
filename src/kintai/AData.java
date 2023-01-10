package kintai;

import java.sql.Time;
import java.util.Date;

public class AData {
	
	private int employe_number;
	private int basic_salary;
	private String sex;
	private String name;
	
	private int employee_id;
	private Date date;
	private Time attendance_at_work;
	private Time leaving_work;
	
	//社員テーブルコンストラクタ
	public AData(int employe_number,int basic_salary,String sex,String name) {
		
		super();
		this.employe_number = employe_number;
		this.basic_salary = basic_salary;
		this.sex = sex;
		this.name = name;
	}
	
	//勤怠テーブルコンストラクタ
	public AData(int employee_id, Date date,Time attendance_at_work,Time leaving_work) {
		super();
		this.employee_id = employee_id;
		this.date = date;
		this.attendance_at_work = attendance_at_work;
		this.leaving_work = leaving_work;
		
	}
	
	
	//社員テーブル getter setter
	public int getEmploye_number() {
		return employe_number;
	}
	
	public void setEmploye_number(int employe_number) {
		this.employe_number = employe_number;
	}
	
	public int getBasic_salary() {
		return basic_salary;
	}
	
	public void setBasic_salary(int basic_salary) {
		this.basic_salary = basic_salary;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//勤怠テーブル getter setter
	public int getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getAttendance_at_work() {
		return attendance_at_work;
	}
	
	public void setAttendance_at_work(Time attendance_at_work) {
		this.attendance_at_work = attendance_at_work;
	}
	
	public Time getLeaving_work() {
		return leaving_work;
	}
	
	public void setLeaving_work(Time leaving_work) {
		this.leaving_work = leaving_work;
	}
	
	

}
