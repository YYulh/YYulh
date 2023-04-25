package dao.member;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;
import vo.ProductVO;

public class ProductListDAO {
	private SqlSession sqlSession;
	
	public ProductListDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<ProductVO> selectList(String name) {
		return sqlSession.selectList("productlist.selectList",name);
	}
	public List<ProductVO> selectContentPic(int no) {
		return sqlSession.selectList("productlist.selectContentPic",no);
	}
	public List<ProductVO> selectDetailPic(int no) {
		return sqlSession.selectList("productlist.selectDetailPic",no);
	}
	public ProductVO selectProduct(int no) {
		return sqlSession.selectOne("productlist.selectProduct",no);
	}
	public List<ProductVO> selectFreebies() {
		return sqlSession.selectList("productlist.selectFreebies");
	}
	public String selectProductMore(int no) {
		return sqlSession.selectOne("productlist.selectProductMore",no);
	}
	public List<ProductVO> selectMoreList(Map<String,Object> map) {
		return sqlSession.selectList("productlist.selectMoreList",map);
	}
	public List<ProductVO> selectOptionList(int no) {
		return sqlSession.selectList("productlist.selectOptionList",no);
	}
	public List<BoardVO> selectReviewList(int no) {
		return sqlSession.selectList("productlist.selectReviewList",no);
	}
	public int getReviewCnt(int no) {
		return sqlSession.selectOne("productlist.getReviewCnt",no);
	}

	public List<BoardVO> selectRePic() {
		return sqlSession.selectList("productlist.selectRePic");
	}
}
