package service.admin;

import java.util.List;
import java.util.Map;

import dao.admin.AdStockDAO;
import vo.ProductVO;
import vo.StockVO;


public class AdStockService {

	private AdStockDAO stockdao;
	
	public AdStockService(AdStockDAO stockdao) {
		this.stockdao = stockdao;
	}
	public List<StockVO> selectList(int start){
		return stockdao.selectList(start);
	}
	
	public int getTotal() {
		return stockdao.getTotal();
	}
	public int updateStock(StockVO vo) {
		return stockdao.updateStock(vo);
	}
//	public StockVO selectStock(int no) {
//		return stockdao.selectStock(no);
//	}
	public int insertStock(StockVO vo) {
		return stockdao.insertStock(vo);
	}
	public StockVO stockDetail(int no) {
		return stockdao.stockDetail(no);
	}
	public int deleteStock(String no) {
		return stockdao.deleteStock(no);
	}
}
