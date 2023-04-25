package vo;

import java.sql.Date;

public class BoardVO {

	private int faq_no;
	private String faq_title;
	private String faq_content;
	private Date faq_date;
	private int faq_view;
	//-------------------------
	private int notice_no;
	private String notice_title;
	private String notice_content;
	private Date notice_date;
	private int notice_view;
	//-------------------------
	private int qna_no;
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private int qna_view;
	private int qna_isprivate;
	private String qna_pw;
	private int qna_origin;
	private int qna_order;
	private int qna_layer;
	private int qna_isanswer;
	//-------------------------
	private int re_no;
	private int prod_no;
	private int ord_no;
	private String re_title;
	private String re_content;
	private Date re_date;
	private int re_view;
	private int re_like;
	private int re_rate;
	private int re_origin;
	private int re_order;
	private int re_layer;
	private int re_isanswer;
	//-------------------------
	private int mb_no;
	private String mb_name;
	private int bc_no;
	private String bc_name;
	private int bo_no;
	private String bo_name;
	private String mb_id;
	private int fp_no;
	private String fp_filename;
	private String fp_path;
	private String grade_name;
	private int rp_no;
	private String rp_filename;
	private String rp_path;
	private String prod_name;
	
	private int rn;
	public BoardVO() {
		
	}





	public BoardVO(int faq_no, String faq_title, String faq_content, Date faq_date, int faq_view, int notice_no,
			String notice_title, String notice_content, Date notice_date, int notice_view, int qna_no, String qna_title,
			String qna_content, Date qna_date, int qna_view, int qna_isprivate, String qna_pw, int qna_origin,
			int qna_order, int qna_layer, int qna_isanswer, int re_no, int prod_no, int ord_no, String re_title,
			String re_content, Date re_date, int re_view, int re_like, int re_rate, int re_origin, int re_order,
			int re_layer, int re_isanswer, int mb_no, String mb_name, int bc_no, String bc_name, int bo_no,
			String bo_name, String mb_id, int fp_no, String fp_filename, String fp_path, String grade_name, int rp_no,
			String rp_filename, String rp_path, String prod_name, int rn) {
		super();
		this.faq_no = faq_no;
		this.faq_title = faq_title;
		this.faq_content = faq_content;
		this.faq_date = faq_date;
		this.faq_view = faq_view;
		this.notice_no = notice_no;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_date = notice_date;
		this.notice_view = notice_view;
		this.qna_no = qna_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_date = qna_date;
		this.qna_view = qna_view;
		this.qna_isprivate = qna_isprivate;
		this.qna_pw = qna_pw;
		this.qna_origin = qna_origin;
		this.qna_order = qna_order;
		this.qna_layer = qna_layer;
		this.qna_isanswer = qna_isanswer;
		this.re_no = re_no;
		this.prod_no = prod_no;
		this.ord_no = ord_no;
		this.re_title = re_title;
		this.re_content = re_content;
		this.re_date = re_date;
		this.re_view = re_view;
		this.re_like = re_like;
		this.re_rate = re_rate;
		this.re_origin = re_origin;
		this.re_order = re_order;
		this.re_layer = re_layer;
		this.re_isanswer = re_isanswer;
		this.mb_no = mb_no;
		this.mb_name = mb_name;
		this.bc_no = bc_no;
		this.bc_name = bc_name;
		this.bo_no = bo_no;
		this.bo_name = bo_name;
		this.mb_id = mb_id;
		this.fp_no = fp_no;
		this.fp_filename = fp_filename;
		this.fp_path = fp_path;
		this.grade_name = grade_name;
		this.rp_no = rp_no;
		this.rp_filename = rp_filename;
		this.rp_path = rp_path;
		this.prod_name = prod_name;
		this.rn = rn;
	}


















	public String getProd_name() {
		return prod_name;
	}





	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}





	public int getRp_no() {
		return rp_no;
	}


	public void setRp_no(int rp_no) {
		this.rp_no = rp_no;
	}


	public String getRp_filename() {
		return rp_filename;
	}


	public void setRp_filename(String rp_filename) {
		this.rp_filename = rp_filename;
	}


	public String getRp_path() {
		return rp_path;
	}


	public void setRp_path(String rp_path) {
		this.rp_path = rp_path;
	}


	public String getGrade_name() {
		return grade_name;
	}








	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}








	public int getRe_no() {
		return re_no;
	}

	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}

	public int getProd_no() {
		return prod_no;
	}

	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}

	public int getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(int ord_no) {
		this.ord_no = ord_no;
	}

	public String getRe_title() {
		return re_title;
	}

	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public Date getRe_date() {
		return re_date;
	}

	public void setRe_date(Date re_date) {
		this.re_date = re_date;
	}

	public int getRe_view() {
		return re_view;
	}

	public void setRe_view(int re_view) {
		this.re_view = re_view;
	}

	public int getRe_like() {
		return re_like;
	}

	public void setRe_like(int re_like) {
		this.re_like = re_like;
	}

	public int getRe_rate() {
		return re_rate;
	}

	public void setRe_rate(int re_rate) {
		this.re_rate = re_rate;
	}

	public int getRe_origin() {
		return re_origin;
	}

	public void setRe_origin(int re_origin) {
		this.re_origin = re_origin;
	}

	public int getRe_order() {
		return re_order;
	}

	public void setRe_order(int re_order) {
		this.re_order = re_order;
	}

	public int getRe_layer() {
		return re_layer;
	}

	public void setRe_layer(int re_layer) {
		this.re_layer = re_layer;
	}

	public int getRe_isanswer() {
		return re_isanswer;
	}

	public void setRe_isanswer(int re_isanswer) {
		this.re_isanswer = re_isanswer;
	}

	public int getRn() {
		return rn;
	}




	public void setRn(int rn) {
		this.rn = rn;
	}




	public String getMb_id() {
		return mb_id;
	}


	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}


	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getQna_date() {
		return qna_date;
	}

	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}

	public int getQna_view() {
		return qna_view;
	}

	public void setQna_view(int qna_view) {
		this.qna_view = qna_view;
	}

	public int getQna_isprivate() {
		return qna_isprivate;
	}

	public void setQna_isprivate(int qna_isprivate) {
		this.qna_isprivate = qna_isprivate;
	}

	public String getQna_pw() {
		return qna_pw;
	}

	public void setQna_pw(String qna_pw) {
		this.qna_pw = qna_pw;
	}

	public int getQna_origin() {
		return qna_origin;
	}

	public void setQna_origin(int qna_origin) {
		this.qna_origin = qna_origin;
	}

	public int getQna_order() {
		return qna_order;
	}

	public void setQna_order(int qna_order) {
		this.qna_order = qna_order;
	}

	public int getQna_layer() {
		return qna_layer;
	}

	public void setQna_layer(int qna_layer) {
		this.qna_layer = qna_layer;
	}

	public int getQna_isanswer() {
		return qna_isanswer;
	}

	public void setQna_isanswer(int qna_isanswer) {
		this.qna_isanswer = qna_isanswer;
	}

	public String getMb_name() {
		return mb_name;
	}





	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}





	public int getFp_no() {
		return fp_no;
	}


	public void setFp_no(int fp_no) {
		this.fp_no = fp_no;
	}


	public String getFp_filename() {
		return fp_filename;
	}


	public void setFp_filename(String fp_filename) {
		this.fp_filename = fp_filename;
	}


	public String getFp_path() {
		return fp_path;
	}


	public void setFp_path(String fp_path) {
		this.fp_path = fp_path;
	}


	public int getBc_no() {
		return bc_no;
	}



	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}



	public String getBc_name() {
		return bc_name;
	}



	public void setBc_name(String bc_name) {
		this.bc_name = bc_name;
	}



	public String getBo_name() {
		return bo_name;
	}



	public void setBo_name(String bo_name) {
		this.bo_name = bo_name;
	}



	public int getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

	public Date getFaq_date() {
		return faq_date;
	}

	public void setFaq_date(Date faq_date) {
		this.faq_date = faq_date;
	}

	public int getFaq_view() {
		return faq_view;
	}

	public void setFaq_view(int faq_view) {
		this.faq_view = faq_view;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_view() {
		return notice_view;
	}

	public void setNotice_view(int notice_view) {
		this.notice_view = notice_view;
	}

	public int getMb_no() {
		return mb_no;
	}

	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}


	public int getBo_no() {
		return bo_no;
	}

	public void setBo_no(int bo_no) {
		this.bo_no = bo_no;
	}

	
}
