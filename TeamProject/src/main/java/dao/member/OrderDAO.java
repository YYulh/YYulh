package dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CouponVO;
import vo.MemberVO;
import vo.OrderVO;

public class OrderDAO {
	
	private SqlSession sqlSession;
	
	public OrderDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public MemberVO selectOne(int mb_no) {
		return sqlSession.selectOne("order.selectOne",mb_no);
	}
	
	
	public int getCouponCount(int mb_no) {
		return sqlSession.selectOne("order.getCouponCount",mb_no);
	}
	
	
	public int insertCart(OrderVO ord) {
		return sqlSession.insert("order.insertCart",ord);
	}
	
	public List<OrderVO> listCart(int mb_no){
		return sqlSession.selectList("order.listCart", mb_no);
		
	}
	
	public int updateCart(int mb_no, int prod_no, int cart_cnt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mb_no", mb_no);
		map.put("prod_no", prod_no);
		map.put("cart_cnt", cart_cnt);
	
		return sqlSession.update("order.updateCart",map);
	}
	
	public int deleteCart(String cart_no) {
		return sqlSession.delete("order.deleteCart",cart_no);
	}
	
	public int deleteCartAll(int mb_no) {
		return sqlSession.delete("order.deleteCartAll",mb_no);
	}
	
	public int sumCart(int mb_no) {
		return sqlSession.selectOne("order.sumCart",mb_no);
	}
	
	
	public int getCartCount(int mb_no, int prod_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mb_no", mb_no);
		map.put("prod_no", prod_no);
		return sqlSession.selectOne("order.getCartCount",map);
	
	}
	
	public int condUpdateCart(OrderVO vo) {
		return sqlSession.update("order.condUpdateCart",vo);
	}
	public OrderVO getPo_no(String optionName) {
		return sqlSession.selectOne("order.getPo_no",optionName);
	}
	public OrderVO checkCart(OrderVO ord) {
		return sqlSession.selectOne("order.checkCart",ord);
	}
	

	public int insertOrder(OrderVO vo) {
		return sqlSession.insert("order.insertOrder",vo);
	}
	
	public int updateMbPoint(int mb_point, int mb_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mb_point",mb_point);
		map.put("mb_no", mb_no);
		return sqlSession.update("order.updateMbPoint",map);
	}
	
	public List<CouponVO> getCouponList(int mb_no, int start){
		Map<String, Object> myparam = new HashMap<>();
		
		myparam.put("mb_no", mb_no);
		myparam.put("start", start);
		return sqlSession.selectList("order.getCouponList", myparam);
		
	}
	
	public int couponTotal(int mb_no) {
		return sqlSession.selectOne("order.couponTotal", mb_no);
	}
	
	public List<MemberVO> shippingAddr(int mb_no, int start){
		Map<String, Object> myparam = new HashMap<>();
	
		myparam.put("mb_no", mb_no);
		myparam.put("start", start);
		return sqlSession.selectList("order.shippingAddr", myparam);
	 }
	
	public int getTotal() {
		return sqlSession.selectOne("order.getTotal");
	}
	
	public int insertPay(OrderVO vo) {
		return sqlSession.insert("order.insertPay", vo);
	}
	
	public OrderVO orderResult(int ord_no) {
		return sqlSession.selectOne("order.orderResult", ord_no);
	}
	
	public int deleteCartAfterOrder(int mb_no, int prod_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("mb_no", mb_no);
		map.put("prod_no", prod_no);
		return sqlSession.delete("order.deleteCartAfterOrder", map);
	}
	
	
	public int updateCouponused(int mb_no, String cr_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("mb_no", mb_no);
		map.put("cr_no", cr_no);
		return sqlSession.delete("order.updateCouponused", map);
	}
	
	public int updateUsedPoint(int mb_no, int ord_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("mb_no", mb_no);
		map.put("ord_no", ord_no);
		return sqlSession.delete("order.updateUsedPoint", map);
	}
}
