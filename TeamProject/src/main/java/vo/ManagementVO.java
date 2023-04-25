package vo;

public class ManagementVO {

	private int ap_no;
	private String ap_filename;
	private String ap_path;
	
	public ManagementVO() {
		
	}

	public ManagementVO(int ap_no, String ap_filename, String ap_path) {
		super();
		this.ap_no = ap_no;
		this.ap_filename = ap_filename;
		this.ap_path = ap_path;
	}

	public int getAp_no() {
		return ap_no;
	}

	public void setAp_no(int ap_no) {
		this.ap_no = ap_no;
	}

	public String getAp_filename() {
		return ap_filename;
	}

	public void setAp_filename(String ap_filename) {
		this.ap_filename = ap_filename;
	}

	public String getAp_path() {
		return ap_path;
	}

	public void setAp_path(String ap_path) {
		this.ap_path = ap_path;
	}
	
}
