package dao.admin;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;

public class AdCouponDAO {
	
	private SqlSession sqlSession;
	
	public AdCouponDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	public List<CouponVO> selectCpnList(int start){
		return sqlSession.selectList("coupon.selectCpnList",start);
	}
	public int getTotal() {
		return sqlSession.selectOne("coupon.getTotal");
	}
	public List<GradeVO> selectGrade() {
		return sqlSession.selectList("coupon.selectGrade");
	}
	public int newCoupon(CouponVO vo) {
		return sqlSession.insert("coupon.newCoupon",vo);
	}
	public int deleteCpn(String no){
		return sqlSession.delete("coupon.deleteCpn",no);
	}
	public int getRimit(int no){
		return sqlSession.selectOne("coupon.getRimit",no);
	}
	public List<MemberVO> selectMbList(int limit) {
		return sqlSession.selectList("coupon.selectMbList",limit);
	}
	public int insertUser(CouponVO vo){
		return sqlSession.insert("coupon.insertUser",vo);
	}
	public List<MemberVO> selectBir() {
		return sqlSession.selectList("coupon.selectBir");
	}
	public int getBirCpn(){
		return sqlSession.selectOne("coupon.getBirCpn");
	}
}
