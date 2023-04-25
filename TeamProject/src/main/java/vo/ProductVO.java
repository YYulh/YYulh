package vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {

	private int prod_no;
	private int psc_no;
	private int stock_no;
	private String prod_name;
	private int prod_price;
	private int prod_disc;
	private int prod_sellprice;
	private String prod_content;
	private int prod_view;
	private int prod_rate;
	private String prod_more;
	private String psc_name;
	private String cd_list;
	private int cd_no;
	private MultipartFile photo;
	private int pp_no;
	private int pbc_no;
	private String pbc_name;
	
	private String pp_filename;
	private String pp_path;
	
	private int po_no;
	private String po_name;
	private int po_price;
	
	public ProductVO() {	
	}

	
	


	public ProductVO(int prod_no, int psc_no, int stock_no, String prod_name, int prod_price, int prod_disc,
			int prod_sellprice, String prod_content, int prod_view, int prod_rate, String prod_more, String psc_name,
			String cd_list, int cd_no, MultipartFile photo, int pp_no, int pbc_no, String pbc_name, String pp_filename,
			String pp_path, int po_no, String po_name, int po_price) {
		super();
		this.prod_no = prod_no;
		this.psc_no = psc_no;
		this.stock_no = stock_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_disc = prod_disc;
		this.prod_sellprice = prod_sellprice;
		this.prod_content = prod_content;
		this.prod_view = prod_view;
		this.prod_rate = prod_rate;
		this.prod_more = prod_more;
		this.psc_name = psc_name;
		this.cd_list = cd_list;
		this.cd_no = cd_no;
		this.photo = photo;
		this.pp_no = pp_no;
		this.pbc_no = pbc_no;
		this.pbc_name = pbc_name;
		this.pp_filename = pp_filename;
		this.pp_path = pp_path;
		this.po_no = po_no;
		this.po_name = po_name;
		this.po_price = po_price;
	}





	public int getPbc_no() {
		return pbc_no;
	}





	public void setPbc_no(int pbc_no) {
		this.pbc_no = pbc_no;
	}





	public String getPbc_name() {
		return pbc_name;
	}





	public void setPbc_name(String pbc_name) {
		this.pbc_name = pbc_name;
	}





	public int getPo_no() {
		return po_no;
	}


	public void setPo_no(int po_no) {
		this.po_no = po_no;
	}


	public String getPo_name() {
		return po_name;
	}

	public void setPo_name(String po_name) {
		this.po_name = po_name;
	}

	public int getPo_price() {
		return po_price;
	}

	public void setPo_price(int po_price) {
		this.po_price = po_price;
	}

	public int getProd_sellprice() {
		return prod_sellprice;
	}

	public void setProd_sellprice(int prod_sellprice) {
		this.prod_sellprice = prod_sellprice;
	}

	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public int getPsc_no() {
		return psc_no;
	}
	public void setPsc_no(int psc_no) {
		this.psc_no = psc_no;
	}
	public int getStock_no() {
		return stock_no;
	}
	public void setStock_no(int stock_no) {
		this.stock_no = stock_no;
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
	public int getProd_disc() {
		return prod_disc;
	}
	public void setProd_disc(int prod_disc) {
		this.prod_disc = prod_disc;
	}
	public String getProd_content() {
		return prod_content;
	}
	public void setProd_content(String prod_content) {
		this.prod_content = prod_content;
	}
	public int getProd_view() {
		return prod_view;
	}
	public void setProd_view(int prod_view) {
		this.prod_view = prod_view;
	}
	public int getProd_rate() {
		return prod_rate;
	}
	public void setProd_rate(int prod_rate) {
		this.prod_rate = prod_rate;
	}
	public String getProd_more() {
		return prod_more;
	}
	public void setProd_more(String prod_more) {
		this.prod_more = prod_more;
	}
	public String getPsc_name() {
		return psc_name;
	}
	public void setPsc_name(String psc_name) {
		this.psc_name = psc_name;
	}
	public String getCd_list() {
		return cd_list;
	}
	public void setCd_list(String cd_list) {
		this.cd_list = cd_list;
	}
	public int getCd_no() {
		return cd_no;
	}
	public void setCd_no(int cd_no) {
		this.cd_no = cd_no;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public int getPp_no() {
		return pp_no;
	}
	public void setPp_no(int pp_no) {
		this.pp_no = pp_no;
	}
	
	public String getPp_filename() {
		return pp_filename;
	}
	public void setPp_filename(String pp_filename) {
		this.pp_filename = pp_filename;
	}
	public String getPp_path() {
		return pp_path;
	}
	public void setPp_path(String pp_path) {
		this.pp_path = pp_path;
	}

	

	

	
	
}
