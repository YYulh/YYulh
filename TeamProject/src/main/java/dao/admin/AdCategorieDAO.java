package dao.admin;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;
import vo.ProductVO;

public class AdCategorieDAO {
	
	private SqlSession sqlSession;
	
	public AdCategorieDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	public List<ProductVO> selectSmallCate1(){
		return sqlSession.selectList("categorie.selectSmallCate1");
	}
	public List<ProductVO> selectSmallCate2(){
		return sqlSession.selectList("categorie.selectSmallCate2");
	}
	public int insertSmallCate1(ProductVO vo){
		return sqlSession.insert("categorie.insertSmallCate1",vo);
	}
	public int deleteCate1(String no){
		return sqlSession.delete("categorie.deleteCate1",no);
	}
	public int insertSmallCate2(ProductVO vo){
		return sqlSession.insert("categorie.insertSmallCate1",vo);
	}
	public int deleteCate2(String no){
		return sqlSession.delete("categorie.deleteCate2",no);
	}
}
