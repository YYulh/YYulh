package dao.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ManagementVO;
import vo.MemberVO;
import vo.ProductVO;

public class HomeDAO {
	private SqlSession sqlSession;
	
	public HomeDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<ProductVO> selectSmallCate1(){
		return sqlSession.selectList("home.selectSmallCate1");
	}
	public List<ProductVO> selectSmallCate2(){
		return sqlSession.selectList("home.selectSmallCate2");
	}
	public List<ManagementVO> selectBannerList(){
		return sqlSession.selectList("home.selectBannerList");
	}
	public List<ProductVO> selectList(String name) {
		return sqlSession.selectList("home.selectList",name);
	}
	public List<ProductVO> selectBigCate(){
		return sqlSession.selectList("home.selectBigCate");
	}
}
