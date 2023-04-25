package service.admin;

import java.util.List;

import dao.admin.AdProductDAO;
import vo.ProductVO;

public class AdProductService {

	private AdProductDAO productdao;
	
	public AdProductService(AdProductDAO productdao) {// DAO »£√‚
		this.productdao = productdao;
	}
	public List<ProductVO> selectList(int start){
		return productdao.selectList(start);
	}	
	public int getTotal() {
		return productdao.getTotal();
	}
	public List<ProductVO> selectSmallCate1(){
		return productdao.selectSmallCate1();
	}
	public List<ProductVO> selectSmallCate2(){
		return productdao.selectSmallCate2();
	}
	public List<ProductVO> selectProductAll(){
		return productdao.selectProductAll();
	}
	public List<ProductVO> selectProduct1(){
		return productdao.selectProduct1();
	}
	public List<ProductVO> selectProduct2(){
		return productdao.selectProduct2();
	}
	public List<ProductVO> selectProduct3(){
		return productdao.selectProduct3();
	}
	public int insertProduct(ProductVO vo) {
		return productdao.insertProduct(vo);	
	}
	public int insertFreebies(ProductVO vo) {
		return productdao.insertFreebies(vo);	
	}
	public ProductVO selectOne(int no){
		return productdao.selectOne(no);
	}
	public int updateProduct(ProductVO vo){
		return productdao.updateProduct(vo);
	}
	public int insertCate(ProductVO vo) {
		return productdao.insertCate(vo);
	}
	public int updateCate(ProductVO vo){
		return productdao.updateCate(vo);
	}
	public int deleteProduct(String no){
		return productdao.deleteProduct(no);
	}
	public String cdList(int prod_no){
		return productdao.cdList(prod_no);
	}
	public String moreList(int prod_no){
		return productdao.moreList(prod_no);
	}
	public int getSeq(){
		return productdao.getSeq();
	}
	public int insertStock(int seq){
		return productdao.insertStock(seq);
	}
	public int insertPic(ProductVO vo){
		return productdao.insertPic(vo);
	}
	public List<ProductVO> selectPic(int prod_no){
		return productdao.selectPic(prod_no);
	}
	public List<ProductVO> selectOptionList(int no){
		return productdao.selectOptionList(no);
	}
	public int insertOption(ProductVO vo){
		return productdao.insertOption(vo);
	}
	public ProductVO getLastProduct(int seq){
		return productdao.getLastProduct(seq);
	}
	public int insertBasicOption(ProductVO vo){
		return productdao.insertBasicOption(vo);
	}
	public int deleteOption(String no){
		return productdao.deleteOption(no);
	}
}
