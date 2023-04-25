package vo;

import java.sql.Date;

public class StockVO {

	private int stock_no;
	private int prod_no;
	private String prod_name;
	private int stock_sellcnt;
	private int stock_sell;
	private Date stock_date;
	private int stock_bad;
	private int stock_in;
	private int stock_out;
	
	public StockVO() {
		
	}
	
	public StockVO(int stock_no, int prod_no, String prod_name, int stock_sellcnt, int stock_sell, Date stock_date,
			int stock_bad, int stock_in, int stock_out) {
		super();
		this.stock_no = stock_no;
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.stock_sellcnt = stock_sellcnt;
		this.stock_sell = stock_sell;
		this.stock_date = stock_date;
		this.stock_bad = stock_bad;
		this.stock_in = stock_in;
		this.stock_out = stock_out;
	}
	public int getStock_no() {
		return stock_no;
	}
	public void setStock_no(int stock_no) {
		this.stock_no = stock_no;
	}
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getStock_sellcnt() {
		return stock_sellcnt;
	}
	public void setStock_sellcnt(int stock_sellcnt) {
		this.stock_sellcnt = stock_sellcnt;
	}
	public int getStock_sell() {
		return stock_sell;
	}
	public void setStock_sell(int stock_sell) {
		this.stock_sell = stock_sell;
	}
	public Date getStock_date() {
		return stock_date;
	}
	public void setStock_date(Date stock_date) {
		this.stock_date = stock_date;
	}
	public int getStock_bad() {
		return stock_bad;
	}
	public void setStock_bad(int stock_bad) {
		this.stock_bad = stock_bad;
	}
	public int getStock_in() {
		return stock_in;
	}
	public void setStock_in(int stock_in) {
		this.stock_in = stock_in;
	}
	public int getStock_out() {
		return stock_out;
	}
	public void setStock_out(int stock_out) {
		this.stock_out = stock_out;
	}
	
	
	
}
