package vo;

public class GradeVO {
	
	private int grade_no;
	private String grade_name;
	private int grade_price;
	private int grade_per;
	

	
	public GradeVO() {
		
	}
	

	public GradeVO(int grade_no, String grade_name, int grade_price, int grade_per) {
		super();
		this.grade_no = grade_no;
		this.grade_name = grade_name;
		this.grade_price = grade_price;
		this.grade_per = grade_per;
	}

	public int getGrade_no() {
		return grade_no;
	}

	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public int getGrade_price() {
		return grade_price;
	}

	public void setGrade_price(int grade_price) {
		this.grade_price = grade_price;
	}

	public int getGrade_per() {
		return grade_per;
	}

	public void setGrade_per(int grade_per) {
		this.grade_per = grade_per;
	}

	
	
	
	
	

}
