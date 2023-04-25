package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ProductVO;



public class AdProductDAO {
	
	private SqlSession sqlSession;
	
	public AdProductDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	public List<ProductVO> selectList(int start){
		return sqlSession.selectList("product.selectList", start);
	}
	public int getTotal() {
		return sqlSession.selectOne("product.getTotal");
	}
	public ProductVO selectOne(int no) {
		return sqlSession.selectOne("product.selectOne",no);
	}	
	public List<ProductVO> selectSmallCate1(){
		return sqlSession.selectList("product.selectSmallCate1");
	}
	public List<ProductVO> selectSmallCate2(){
		return sqlSession.selectList("product.selectSmallCate2");
	}
	public List<ProductVO> selectProductAll(){
		return sqlSession.selectList("product.selectProductAll");
	}
	public List<ProductVO> selectProduct1(){
		return sqlSession.selectList("product.selectProduct1");
	}
	public List<ProductVO> selectProduct2(){
		return sqlSession.selectList("product.selectProduct2");
	}
	public List<ProductVO> selectProduct3(){
		return sqlSession.selectList("product.selectProduct3");
	}
	public int insertProduct(ProductVO vo) {
		return sqlSession.insert("product.insertProduct",vo);
	}
	public int insertFreebies(ProductVO vo) {
		return sqlSession.insert("product.insertFreebies",vo);
	}
	public int updateProduct(ProductVO vo) {
		return sqlSession.update("product.updateProduct",vo);
	}
	public int insertCate(ProductVO vo) {
		return sqlSession.insert("product.insertCate",vo);
	}
	public int updateCate(ProductVO vo) {
		return sqlSession.update("product.updateCate",vo);
	}
	public int deleteProduct(String no) {
		return sqlSession.delete("product.deleteProduct",no);
	}
	public String cdList(int prod_no) {
		return sqlSession.selectOne("product.cdList",prod_no);
	}
	public String moreList(int prod_no) {
		return sqlSession.selectOne("product.moreList",prod_no);
	}
	public int getSeq() {
		return sqlSession.selectOne("product.getSeq");
	}
	public int insertStock(int seq) {
		return sqlSession.insert("product.insertStock",seq);
	}
	public int insertPic(ProductVO vo) {
		return sqlSession.insert("product.insertPic",vo);
	}
	public List<ProductVO> selectPic(int prod_no) {
		return sqlSession.selectList("product.selectPic",prod_no);
	}
	public List<ProductVO> selectOptionList(int no) {
		return sqlSession.selectList("product.selectOptionList",no);
	}
	public int insertOption(ProductVO vo) {
		return sqlSession.insert("product.insertOption",vo);
	}
	public ProductVO getLastProduct(int seq) {
		return sqlSession.selectOne("product.getLastProduct",seq);
	}
	public int insertBasicOption(ProductVO vo) {
		return sqlSession.insert("product.insertBasicOption",vo);
	}
	public int deleteOption(String no) {
		return sqlSession.delete("product.deleteOption",no);
	}
}
