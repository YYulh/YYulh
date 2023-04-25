package dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;
import vo.OrderVO;

public class MypageDAO {
	
	private SqlSession sqlSession;
	
	public MypageDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	
	
	public MemberVO selectOne(int mb_no) {
		return sqlSession.selectOne("mypage.selectOne",mb_no);
	}
	
	public GradeVO selectGrade(int mb_no) {
		return sqlSession.selectOne("mypage.selectGrade",mb_no);
	}
	
	
	public int getCouponCount(int mb_no) {
		return sqlSession.selectOne("mypage.getCouponCount",mb_no);
	}
	
	public List<CouponVO> getCouponList(int mb_no, int start){
		Map<String, Object> myparam = new HashMap<>();
		
		myparam.put("mb_no", mb_no);
		myparam.put("start", start);
		return sqlSession.selectList("mypage.getCouponList", myparam);
		
	}
	
	public OrderVO selectOrders(int mb_no) {
		return sqlSession.selectOne("mypage.selectOrders",mb_no);
	}
	
	public List<OrderVO> orderList(int mb_no, int start){
		Map<String, Object> map = new HashMap<>();
		
		map.put("mb_no", mb_no);
		map.put("start", start);
		return sqlSession.selectList("mypage.orderList",map);
	}
	
	public List<OrderVO> getPointFromOrder(int mb_no, int start){
		Map<String, Object> map = new HashMap<>();
		map.put("mb_no", mb_no);
		map.put("start", start);
		
		return sqlSession.selectList("mypage.getPointFromOrder",map);
	}
	
	public List<OrderVO> selectProcess(int mb_no){
		return sqlSession.selectList("mypage.selectProcess", mb_no);
	}
	
	
	public int getUsedPointSum(int mb_no) {
		return sqlSession.selectOne("mypage.getUsedPointSum", mb_no);
	}
	
	
	
	public int update(MemberVO vo) {
		return sqlSession.update("mypage.update",vo);
	}
	
	
	public int leave(int mb_no) {
		return sqlSession.update("mypage.leave",mb_no);
	}
	
	public int addAddr(MemberVO vo) {
		return sqlSession.insert("mypage.addAddr", vo);
	}
	
//	public List<MemberVO> shippingAddr(Map<String, Object> map){
//		return sqlSession.selectList("mypage.shippingAddr", map);
//	}
	
	public List<MemberVO> shippingAddr(int mb_no, int start){
		Map<String, Object> myparam = new HashMap<>();
	
		myparam.put("mb_no", mb_no);
		myparam.put("start", start);
		return sqlSession.selectList("mypage.shippingAddr", myparam);
	 }
	
	public int getTotal() {
		return sqlSession.selectOne("mypage.getTotal");
	}

	public int couponTotal(int mb_no) {
		return sqlSession.selectOne("mypage.couponTotal", mb_no);
	}
	
	public int getOrderTotal(int mb_no) {
		return sqlSession.selectOne("mypage.getOrderTotal", mb_no);
	}
	
	public int deleteAddr(String shipaddr_no) {
		return sqlSession.delete("mypage.deleteAddr",shipaddr_no);
	}
	
	public MemberVO updateAddrForm(int no) {
		return sqlSession.selectOne("mypage.shippingAddr1",no);
	}
	

}
