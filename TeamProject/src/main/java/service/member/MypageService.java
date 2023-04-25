package service.member;

import java.util.List;
import java.util.Map;

import dao.member.MypageDAO;
import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;
import vo.OrderVO;

public class MypageService {
	
	private MypageDAO mypageDao;
	
	
	public MypageService(MypageDAO mypageDao) {
		this.mypageDao = mypageDao;
	}
	
	public MemberVO selectOne(int mb_no) {
		return mypageDao.selectOne(mb_no);
	}
	
	public GradeVO selectGrade(int mb_no) {
		return mypageDao.selectGrade(mb_no);
	}
	
	
	public int getCouponCount(int mb_no) {
		return mypageDao.getCouponCount(mb_no);
	}
	
	public List<CouponVO> getCouponList(int mb_no, int start){
		return mypageDao.getCouponList(mb_no, start);
	}
	
	public OrderVO selectOrders(int mb_no) {
		return mypageDao.selectOrders(mb_no);
	}
	
	public List<OrderVO> orderList(int mb_no, int start){
		return mypageDao.orderList(mb_no, start);
	}
	
	public List<OrderVO> getPointOrder(int mb_no, int start){
		return mypageDao.getPointFromOrder(mb_no, start);
	}
	
	public List<OrderVO> selectProcess(int mb_no){
		return mypageDao.selectProcess(mb_no);
	}
	
	public int getUsedPointSum(int mb_no) {
		return mypageDao.getUsedPointSum(mb_no);
	}
	
	public int update(MemberVO vo) {
		return mypageDao.update(vo);
	}
	
	
	public int leave(int mb_no) {
		return mypageDao.leave(mb_no);
	}
	
	public int addAddr(MemberVO vo) {
		return mypageDao.addAddr(vo);
	}
	
	public List<MemberVO> shippingAddr(int mb_no, int start){
		return mypageDao.shippingAddr(mb_no, start);
	}
	
	public int getTotal() {
		return mypageDao.getTotal();
	}

	
	public int couponTotal(int mb_no) {
		return mypageDao.couponTotal(mb_no);
	}
	
	public int getOrderTotal(int mb_no) {
		return mypageDao.getOrderTotal(mb_no);
	}
	
	public int deleteAddr(String shipaddr_no) {
		return mypageDao.deleteAddr(shipaddr_no);
	}
	
	public MemberVO updateAddrForm(int no) {
		return mypageDao.updateAddrForm(no);
	}

}
