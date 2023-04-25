package vo;

import java.sql.Date;

public class MemberVO { 

	private int mb_no;
	private int grade_no;
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String mb_resi1;
	private String mb_resi2;
	private String mb_email1;
	private String mb_email2;
	private String mb_post;
	private String mb_detailaddr;
	private String mb_tel1;
	private String mb_tel2;
	private String mb_tel3;
	private Date mb_date;
	private Date mb_bir;
	private int mb_type;
	private int mb_point;
	private int mb_tp;
	private String mb_rcid;
	private int mb_14;
	private int mb_service;
	private int mb_info;
	private int mb_sms;
	private int mb_ckmail;
	private int mb_leave;
	
	private String grade_name;
	
	private int shipaddr_no;
	private String ship_name;
	private String ship_personname;
	private String ship_addr;
	private String ship_tel1;
	private String ship_tel2;
	private String ship_tel3;
	private int ship_default;
	
	
	private int prod_no;
	private int cart_cnt;
	
	
	public MemberVO() {
		
	}
	public MemberVO(int mb_no, int grade_no, String mb_id, String mb_pw, String mb_name, String mb_resi1,
			String mb_resi2, String mb_email1, String mb_email2, String mb_post, String mb_detailaddr, String mb_tel1,
			String mb_tel2, String mb_tel3, Date mb_date, Date mb_bir, int mb_type, int mb_point, int mb_tp,
			String mb_rcid, int mb_14, int mb_service, int mb_info, int mb_sms, int mb_ckmail, String grade_name, int mb_leave, int shipaddr_no, String ship_name, String ship_personname, String ship_addr, String ship_tel1, String ship_tel2, String ship_tel3, int ship_default, int prod_no, int cart_cnt) {
		super();
		this.mb_no = mb_no;
		this.grade_no = grade_no;
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_name = mb_name;
		this.mb_resi1 = mb_resi1;
		this.mb_resi2 = mb_resi2;
		this.mb_email1 = mb_email1;
		this.mb_email2 = mb_email2;
		this.mb_post = mb_post;
		this.mb_detailaddr = mb_detailaddr;
		this.mb_tel1 = mb_tel1;
		this.mb_tel2 = mb_tel2;
		this.mb_tel3 = mb_tel3;
		this.mb_date = mb_date;
		this.mb_bir = mb_bir;
		this.mb_type = mb_type;
		this.mb_point = mb_point;
		this.mb_tp = mb_tp;
		this.mb_rcid = mb_rcid;
		this.mb_14 = mb_14;
		this.mb_service = mb_service;
		this.mb_info = mb_info;
		this.mb_sms = mb_sms;
		this.mb_ckmail = mb_ckmail;
		this.grade_name = grade_name;
		this.mb_leave = mb_leave;
		
		this.shipaddr_no = shipaddr_no;
		this.ship_name = ship_name;
		this.ship_personname = ship_personname;
		this.ship_addr = ship_addr;
		this.ship_tel1 = ship_tel1;
		this.ship_tel2 = ship_tel2;
		this.ship_tel3 = ship_tel3;
		this.ship_default = ship_default;
		
		this.prod_no = prod_no;
		this.cart_cnt = cart_cnt;
	}
	
	
	
	public MemberVO(int shipaddr_no, String ship_name, String ship_personname, String ship_addr, String ship_tel1, String ship_tel2, String ship_tel3, int ship_default) {
		this.shipaddr_no = shipaddr_no;
		this.ship_name = ship_name;
		this.ship_personname = ship_personname;
		this.ship_addr = ship_addr;
		this.ship_tel1 = ship_tel1;
		this.ship_tel2 = ship_tel2;
		this.ship_tel3 = ship_tel3;
		this.ship_default = ship_default;
		
	}

	public MemberVO(String mb_id) {
		super();
		this.mb_id = mb_id;
	}
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public int getGrade_no() {
		return grade_no;
	}
	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_resi1() {
		return mb_resi1;
	}
	public void setMb_resi1(String mb_resi1) {
		this.mb_resi1 = mb_resi1;
	}
	public String getMb_resi2() {
		return mb_resi2;
	}
	public void setMb_resi2(String mb_resi2) {
		this.mb_resi2 = mb_resi2;
	}
	public String getMb_email1() {
		return mb_email1;
	}
	public void setMb_email1(String mb_email1) {
		this.mb_email1 = mb_email1;
	}
	public String getMb_email2() {
		return mb_email2;
	}
	public void setMb_email2(String mb_email2) {
		this.mb_email2 = mb_email2;
	}
	public String getMb_post() {
		return mb_post;
	}
	public void setMb_post(String mb_post) {
		this.mb_post = mb_post;
	}
	public String getMb_detailaddr() {
		return mb_detailaddr;
	}
	public void setMb_detailaddr(String mb_detailaddr) {
		this.mb_detailaddr = mb_detailaddr;
	}
	public String getMb_tel1() {
		return mb_tel1;
	}
	public void setMb_tel1(String mb_tel1) {
		this.mb_tel1 = mb_tel1;
	}
	public String getMb_tel2() {
		return mb_tel2;
	}
	public void setMb_tel2(String mb_tel2) {
		this.mb_tel2 = mb_tel2;
	}
	public String getMb_tel3() {
		return mb_tel3;
	}
	public void setMb_tel3(String mb_tel3) {
		this.mb_tel3 = mb_tel3;
	}
	public Date getMb_date() {
		return mb_date;
	}
	public void setMb_date(Date mb_date) {
		this.mb_date = mb_date;
	}
	public Date getMb_bir() {
		return mb_bir;
	}
	public void setMb_bir(Date mb_bir) {
		this.mb_bir = mb_bir;
	}
	public int getMb_type() {
		return mb_type;
	}
	public void setMb_type(int mb_type) {
		this.mb_type = mb_type;
	}
	public int getMb_point() {
		return mb_point;
	}
	public void setMb_point(int mb_point) {
		this.mb_point = mb_point;
	}
	public int getMb_tp() {
		return mb_tp;
	}
	public void setMb_tp(int mb_tp) {
		this.mb_tp = mb_tp;
	}
	public String getMb_rcid() {
		return mb_rcid;
	}
	public void setMb_rcid(String mb_rcid) {
		this.mb_rcid = mb_rcid;
	}
	public int getMb_14() {
		return mb_14;
	}
	public void setMb_14(int mb_14) {
		this.mb_14 = mb_14;
	}
	public int getMb_service() {
		return mb_service;
	}
	public void setMb_service(int mb_service) {
		this.mb_service = mb_service;
	}
	public int getMb_info() {
		return mb_info;
	}
	public void setMb_info(int mb_info) {
		this.mb_info = mb_info;
	}
	public int getMb_sms() {
		return mb_sms;
	}
	public void setMb_sms(int mb_sms) {
		this.mb_sms = mb_sms;
	}
	public int getMb_ckmail() {
		return mb_ckmail;
	}
	public int getMb_leave() {
		return mb_leave;
	}
	public void setMb_leave(int mb_leave) {
		this.mb_leave = mb_leave;
	}
	public void setMb_ckmail(int mb_ckmail) {
		this.mb_ckmail = mb_ckmail;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	
	public int getShipaddr_no() {
		return shipaddr_no;
	}


	public void setShipaddr_no(int shipaddr_no) {
		this.shipaddr_no = shipaddr_no;
	}


	public String getShip_name() {
		return ship_name;
	}


	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}


	public String getShip_personname() {
		return ship_personname;
	}


	public void setShip_personname(String ship_personname) {
		this.ship_personname = ship_personname;
	}


	public String getShip_addr() {
		return ship_addr;
	}


	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}


	public String getShip_tel1() {
		return ship_tel1;
	}


	public void setShip_tel1(String ship_tel1) {
		this.ship_tel1 = ship_tel1;
	}


	public String getShip_tel2() {
		return ship_tel2;
	}


	public void setShip_tel2(String ship_tel2) {
		this.ship_tel2 = ship_tel2;
	}


	public String getShip_tel3() {
		return ship_tel3;
	}


	public void setShip_tel3(String ship_tel3) {
		this.ship_tel3 = ship_tel3;
	}


	public int getShip_default() {
		return ship_default;
	}


	public void setShip_default(int setShip_default) {
		this.ship_default = setShip_default;
	}
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public int getCart_cnt() {
		return cart_cnt;
	}
	public void setCart_cnt(int cart_cnt) {
		this.cart_cnt = cart_cnt;
	}
	
	
	
	
	
}
