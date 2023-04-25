package service.admin;

import java.util.List;

import dao.admin.AdCategorieDAO;
import vo.ProductVO;


public class AdCategorieService {

	private AdCategorieDAO categoriedao;
	
	public AdCategorieService(AdCategorieDAO categoriedao) {// DAO »£√‚
		this.categoriedao = categoriedao;
	}
	public List<ProductVO> selectSmallCate1(){
		return categoriedao.selectSmallCate1();
	}
	public List<ProductVO> selectSmallCate2(){
		return categoriedao.selectSmallCate2();
	}
	public int insertSmallCate1(ProductVO vo){
		return categoriedao.insertSmallCate1(vo);
	}
	public int deleteCate1(String no){
		return categoriedao.deleteCate1(no);
	}
	public int insertSmallCate2(ProductVO vo){
		return categoriedao.insertSmallCate2(vo);
	}
	public int deleteCate2(String no){
		return categoriedao.deleteCate2(no);
	}
	
}
