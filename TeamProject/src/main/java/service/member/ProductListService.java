package service.member;

import java.util.List;
import java.util.Map;

import dao.member.ProductListDAO;
import vo.BoardVO;
import vo.ProductVO;

public class ProductListService {

		private ProductListDAO productlistdao;
		
		public ProductListService(ProductListDAO productlistdao) {// DAO »£√‚
			this.productlistdao = productlistdao;
		}
		
		public List<ProductVO> selectList(String name){
			return productlistdao.selectList(name);
		}
		
		public List<ProductVO> selectContentPic(int no){
			return productlistdao.selectContentPic(no);
		}
		public List<ProductVO> selectDetailPic(int no){
			return productlistdao.selectDetailPic(no);
		}
		public ProductVO selectProduct(int no){
			return productlistdao.selectProduct(no);
		}
		public List<ProductVO> selectFreebies(){
			return productlistdao.selectFreebies();
		}
		public String selectProductMore(int no){
			return productlistdao.selectProductMore(no);
		}
		public List<ProductVO> selectMoreList(Map<String,Object> map){
			return productlistdao.selectMoreList(map);
		}
		public List<ProductVO> selectOptionList(int no){
			return productlistdao.selectOptionList(no);
		}
		public List<BoardVO> selectReviewList(int no){
			return productlistdao.selectReviewList(no);
		}
		public int getReviewCnt(int no){
			return productlistdao.getReviewCnt(no);
		}

		public List<BoardVO> selectRePic(){
			return productlistdao.selectRePic();
		}
}
