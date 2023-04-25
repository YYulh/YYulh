package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ProductVO;
import vo.StockVO;


public class AdStockDAO {
	
	private SqlSession sqlSession;
	
	public AdStockDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	public List<StockVO> selectList(int start){
		return sqlSession.selectList("stock.selectList", start);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("stock.getTotal");
	}
	public int updateStock(StockVO vo) {
		return sqlSession.update("stock.updateStock",vo);
	}
//	public StockVO selectStock(int no) {
//		return sqlSession.selectOne("stock.selectStock",no);
//	}
	public int insertStock(StockVO vo) {
		return sqlSession.insert("stock.insertStock",vo);
	}
	public StockVO stockDetail(int no) {
		return sqlSession.selectOne("stock.stockDetail",no);
	}
	public int deleteStock(String no) {
		return sqlSession.delete("stock.deleteStock",no);
	}
}
