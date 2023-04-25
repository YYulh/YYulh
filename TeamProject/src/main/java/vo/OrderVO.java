package vo;

import java.sql.Date;

public class OrderVO {
	
	private int ord_no;
	private int mb_no;
	private int prod_no;
	private int grade_no;
	private int nm_no;
	private int py_no;
	private int cpn_no;
	private Date ord_date;
	private String ord_receiver;
	private String ord_tel1;
	private String ord_tel2;
	private String ord_tel3;
	private String ord_addr;
	private String ord_post;
	private int ord_usepoint;
	private int ord_price;
	private int ord_inv;
	private String ord_msg;
	private int ord_fee;
	private int ord_cnt;
	private String ord_serial;
	
	private int pt_name;
	
	private int cart_no;
	private int cart_cnt;
	private int prod_price;
	private String prod_name;
	private String pp_filename;
	private int po_no;
	private int prod_sellprice;
	
	private int shipaddr_no;
	private String cpn_no_str;
	private String shipaddr_no_str;
	private String cr_no_str;
	
	
	private int pay_no;
	private String pay_acc;
	private String pay_bnk;
	private String pay_method;
	

	private String cpn_name;
	private int cpn_per;
	private int cpn_price;
	
	private int cr_no;

	private Date cr_date1;
	private Date cr_date2;
	private String cr_serial;
	private int cr_isused;
	

	
	public OrderVO() {
		
	}
	
	
	



	public OrderVO(int ord_no, int mb_no, int prod_no, int grade_no, int nm_no, int py_no, int cpn_no, Date ord_date,
			String ord_receiver, String ord_tel1, String ord_tel2, String ord_tel3, String ord_addr, String ord_post,
			int ord_usepoint, int ord_price, int ord_inv, String ord_msg, int ord_fee, int ord_cnt, int pt_name,
			int cart_no, int cart_cnt, int prod_price, String prod_name, String pp_filename, int po_no, String ord_serial, 
			int shipaddr_no, String cpn_no_str, String shipaddr_no_str, int pay_no, String pay_acc, String pay_bnk, String pay_method, int prod_sellprice,
			String cpn_name, int cpn_per, int cpn_price, int cr_no, Date cr_date1, Date cr_date2, String cr_serial, int cr_isused, String cr_no_str) {
		super();
		this.ord_no = ord_no;
		this.mb_no = mb_no;
		this.prod_no = prod_no;
		this.grade_no = grade_no;
		this.nm_no = nm_no;
		this.py_no = py_no;
		this.cpn_no = cpn_no;
		this.ord_date = ord_date;
		this.ord_receiver = ord_receiver;
		this.ord_tel1 = ord_tel1;
		this.ord_tel2 = ord_tel2;
		this.ord_tel3 = ord_tel3;
		this.ord_addr = ord_addr;
		this.ord_post = ord_post;
		this.ord_usepoint = ord_usepoint;
		this.ord_price = ord_price;
		this.ord_inv = ord_inv;
		this.ord_msg = ord_msg;
		this.ord_fee = ord_fee;
		this.ord_cnt = ord_cnt;
		this.pt_name = pt_name;
		this.cart_no = cart_no;
		this.cart_cnt = cart_cnt;
		this.prod_price = prod_price;
		this.prod_name = prod_name;
		this.pp_filename = pp_filename;
		this.po_no = po_no;
		this.ord_serial = ord_serial;
		this.shipaddr_no = shipaddr_no;
		this.cpn_no_str = cpn_no_str;
		this.shipaddr_no_str = shipaddr_no_str;
		this.pay_no = pay_no;
		this.pay_acc = pay_acc;
		this.pay_bnk = pay_bnk;
		this.pay_method = pay_method;
		this.prod_sellprice = prod_sellprice;
		

		this.cpn_name = cpn_name;
		this.cpn_per = cpn_per;
		this.cpn_price = cpn_price;
		
		this.cr_no = cr_no;
		this.cr_date1 = cr_date1;
		this.cr_date2 = cr_date2;
		this.cr_serial = cr_serial;
		this.cr_isused = cr_isused;
		
		this.cr_no_str = cr_no_str;
	
	}








	public String getCr_no_str() {
		return cr_no_str;
	}






	public void setCr_no_str(String cr_no_str) {
		this.cr_no_str = cr_no_str;
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






	public int getShipaddr_no() {
		return shipaddr_no;
	}






	public int getProd_sellprice() {
		return prod_sellprice;
	}






	public void setProd_sellprice(int prod_sellprice) {
		this.prod_sellprice = prod_sellprice;
	}






	public void setShipaddr_no(int shipaddr_no) {
		this.shipaddr_no = shipaddr_no;
	}



	public void setShipaddr_no_str(String shipaddr_no_str) {
		this.shipaddr_no_str = shipaddr_no_str;
	}

	public String getShipaddr_no_str() {
		return shipaddr_no_str;
	}

	public String getPp_filename() {
		return pp_filename;
	}






	public String getOrd_serial() {
		return ord_serial;
	}






	public void setOrd_serial(String ord_serial) {
		this.ord_serial = ord_serial;
	}






	public void setPp_filename(String pp_filename) {
		this.pp_filename = pp_filename;
	}













	public int getPo_no() {
		return po_no;
	}






	public void setPo_no(int po_no) {
		this.po_no = po_no;
	}






	public String getProd_name() {
		return prod_name;
	}


	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}


	public int getProd_price() {
		return prod_price;
	}


	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}


	public int getCart_no() {
		return cart_no;
	}


	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}


	public int getCart_cnt() {
		return cart_cnt;
	}


	public void setCart_cnt(int cart_cnt) {
		this.cart_cnt = cart_cnt;
	}


	public int getOrd_cnt() {
		return ord_cnt;
	}


	public void setOrd_cnt(int ord_cnt) {
		this.ord_cnt = ord_cnt;
	}


	public int getPt_name() {
		return pt_name;
	}


	


	public void setPt_name(int pt_name) {
		this.pt_name = pt_name;
	}


	public int getOrd_no() {
		return ord_no;
	}


	public void setOrd_no(int ord_no) {
		this.ord_no = ord_no;
	}


	public int getMb_no() {
		return mb_no;
	}


	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}


	public int getProd_no() {
		return prod_no;
	}


	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}


	public int getGrade_no() {
		return grade_no;
	}


	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}


	public int getNm_no() {
		return nm_no;
	}


	public void setNm_no(int nm_no) {
		this.nm_no = nm_no;
	}


	public int getPy_no() {
		return py_no;
	}


	public void setPy_no(int py_no) {
		this.py_no = py_no;
	}


	public int getCpn_no() {
		return cpn_no;
	}


	public void setCpn_no(int cpn_no) {
		this.cpn_no = cpn_no;
	}
	
	public void setCpn_no_str(String cpn_no_str) {
		this.cpn_no_str = cpn_no_str;
	}
	
	public String getCpn_no_str() {
		return cpn_no_str;
	}


	public Date getOrd_date() {
		return ord_date;
	}


	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}


	public String getOrd_receiver() {
		return ord_receiver;
	}


	public void setOrd_receiver(String ord_receiver) {
		this.ord_receiver = ord_receiver;
	}


	public String getOrd_tel1() {
		return ord_tel1;
	}


	public void setOrd_tel1(String ord_tel1) {
		this.ord_tel1 = ord_tel1;
	}


	public String getOrd_tel2() {
		return ord_tel2;
	}


	public void setOrd_tel2(String ord_tel2) {
		this.ord_tel2 = ord_tel2;
	}


	public String getOrd_tel3() {
		return ord_tel3;
	}


	public void setOrd_tel3(String ord_tel3) {
		this.ord_tel3 = ord_tel3;
	}


	public String getOrd_addr() {
		return ord_addr;
	}


	public void setOrd_addr(String ord_addr) {
		this.ord_addr = ord_addr;
	}


	public String getOrd_post() {
		return ord_post;
	}


	public void setOrd_post(String ord_post) {
		this.ord_post = ord_post;
	}


	public int getOrd_usepoint() {
		return ord_usepoint;
	}


	public void setOrd_usepoint(int ord_usepoint) {
		this.ord_usepoint = ord_usepoint;
	}


	public int getOrd_price() {
		return ord_price;
	}


	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	



	public int getOrd_inv() {
		return ord_inv;
	}


	public void setOrd_inv(int ord_inv) {
		this.ord_inv = ord_inv;
	}


	public String getOrd_msg() {
		return ord_msg;
	}


	public void setOrd_msg(String ord_msg) {
		this.ord_msg = ord_msg;
	}


	public int getOrd_fee() {
		return ord_fee;
	}


	public void setOrd_fee(int ord_fee) {
		this.ord_fee = ord_fee;
	}






	public int getPay_no() {
		return pay_no;
	}






	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}






	public String getPay_acc() {
		return pay_acc;
	}






	public void setPay_acc(String pay_acc) {
		this.pay_acc = pay_acc;
	}






	public String getPay_bnk() {
		return pay_bnk;
	}






	public void setPay_bnk(String pay_bnk) {
		this.pay_bnk = pay_bnk;
	}






	public String getPay_method() {
		return pay_method;
	}






	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}





	
	
	
}
