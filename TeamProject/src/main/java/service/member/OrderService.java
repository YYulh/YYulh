package service.member;

import java.util.List;

import dao.member.OrderDAO;
import vo.CouponVO;
import vo.MemberVO;
import vo.OrderVO;

public class OrderService {
	 private OrderDAO orderDao;
	 
	 public OrderService(OrderDAO orderDao) {
		 this.orderDao = orderDao;
	 }
	 
	 public MemberVO selectOne(int mb_no) {
		 return orderDao.selectOne(mb_no);
	 }
	 
	 public int getCouponCount(int mb_no) {
		 return orderDao.getCouponCount(mb_no);
	 }
	 
	 
	 public int getCartCount(int mb_no, int prod_no) {
		 return orderDao.getCartCount(mb_no, prod_no);
	 }
	 
	 public int insertCart(OrderVO ord) {
		 return orderDao.insertCart(ord);
	 }
	 
	 public List<OrderVO> listCart(int mb_no){
		 return orderDao.listCart(mb_no);
	 }
	 
	 public int updateCart(int mb_no, int prod_no, int cart_cnt){
		 return orderDao.updateCart( mb_no, prod_no, cart_cnt);
	 }
	 
	 public int deleteCart(String cart_no) {
		return orderDao.deleteCart(cart_no);
	 }
	 
	 public int deleteCartAll(int mb_no) {
		return orderDao.deleteCartAll(mb_no);
	 }
	 
	 public int sumCart(int mb_no) {
		 return orderDao.sumCart(mb_no);
	 }
	 
	 public int condUpdateCart(OrderVO vo) {
		 return orderDao.condUpdateCart(vo);
	 }
	 public OrderVO getPo_no(String optionName) {
		 return orderDao.getPo_no(optionName);
	 }
	 
	 public int insertOrder(OrderVO vo) {
		 return orderDao.insertOrder(vo);
	 }
	 
	 public int updateMbPoint(int mb_point, int mb_no) {
		 return orderDao.updateMbPoint(mb_point, mb_no);
	 }
	 public OrderVO checkCart(OrderVO ord) {
		 return orderDao.checkCart(ord);
	 }
	 
	 public List<CouponVO> getCouponList(int mb_no, int start){
		return orderDao.getCouponList(mb_no, start);
	}
		
	 
	public int couponTotal(int mb_no) {
		return orderDao.couponTotal(mb_no);
	}
	
	public List<MemberVO> shippingAddr(int mb_no, int start){
		return orderDao.shippingAddr(mb_no, start);
	}
	
	public int getTotal() {
		return orderDao.getTotal();
	}
	
	public int insertPay(OrderVO vo) {
		return orderDao.insertPay(vo);
	}
	
	public OrderVO orderResult(int ord_no) {
		return orderDao.orderResult(ord_no);
	}
	
	public int deleteCartAfterOrder(int mb_no, int prod_no) {
		return orderDao.deleteCartAfterOrder(mb_no, prod_no);
	}
	
	public int updateCouponused(int mb_no, String cr_no) {
		return orderDao.updateCouponused(mb_no, cr_no);
	}
	
	public int updateUsedPoint(int mb_no, int ord_no) {
		return orderDao.updateUsedPoint(mb_no, ord_no);
	}
		
}
