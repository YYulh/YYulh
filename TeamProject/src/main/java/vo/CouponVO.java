package vo;

import java.sql.Date;

public class CouponVO {
	
	private int cpn_no;
	private int grade_no;
	private String cpn_name;
	private int cpn_per;
	private int cpn_price;
	
	private int cr_no;
	private int mb_no;
	private Date cr_date1;
	private Date cr_date2;
	private String cr_serial;
	private int cr_isused;
	private String grade_name;
	private int grade_price;
	public CouponVO() {
		
	}

	

	



	public CouponVO(int cpn_no, int grade_no, String cpn_name, int cpn_per, int cpn_price, int cr_no, int mb_no,
			Date cr_date1, Date cr_date2, String cr_serial, int cr_isused, String grade_name, int grade_price) {
		super();
		this.cpn_no = cpn_no;
		this.grade_no = grade_no;
		this.cpn_name = cpn_name;
		this.cpn_per = cpn_per;
		this.cpn_price = cpn_price;
		this.cr_no = cr_no;
		this.mb_no = mb_no;
		this.cr_date1 = cr_date1;
		this.cr_date2 = cr_date2;
		this.cr_serial = cr_serial;
		this.cr_isused = cr_isused;
		this.grade_name = grade_name;
		this.grade_price = grade_price;
	}





	public int getGrade_price() {
		return grade_price;
	}



	public void setGrade_price(int grade_price) {
		this.grade_price = grade_price;
	}







	public String getGrade_name() {
		return grade_name;
	}






	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}






	public int getCpn_no() {
		return cpn_no;
	}

	public void setCpn_no(int cpn_no) {
		this.cpn_no = cpn_no;
	}

	public int getGrade_no() {
		return grade_no;
	}

	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}

	public String getCpn_name() {
		return cpn_name;
	}

	public void setCpn_name(String cpn_name) {
		this.cpn_name = cpn_name;
	}

	public int getCpn_per() {
		return cpn_per;
	}

	public void setCpn_per(int cpn_per) {
		this.cpn_per = cpn_per;
	}

	public int getCpn_price() {
		return cpn_price;
	}

	public void setCpn_price(int cpn_price) {
		this.cpn_price = cpn_price;
	}
	
	
	public int getCr_no() {
		return cr_no;
	}

	public void setCr_no(int cr_no) {
		this.cr_no = cr_no;
	}


	public int getMb_no() {
		return mb_no;
	}

	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}

	public Date getCr_date1() {
		return cr_date1;
	}

	public void setCr_date1(Date cr_date1) {
		this.cr_date1 = cr_date1;
	}

	public Date getCr_date2() {
		return cr_date2;
	}

	public void setCr_date2(Date cr_date2) {
		this.cr_date2 = cr_date2;
	}

	public String getCr_serial() {
		return cr_serial;
	}

	public void setCr_serial(String cr_serial) {
		this.cr_serial = cr_serial;
	}

	public int getCr_isused() {
		return cr_isused;
	}

	public void setCr_isused(int cr_isused) {
		this.cr_isused = cr_isused;
	}

	
	
	

}
