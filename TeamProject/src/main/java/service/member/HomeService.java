package service.member;

import java.util.List;

import dao.member.HomeDAO;
import vo.ManagementVO;
import vo.ProductVO;

public class HomeService {

		private HomeDAO homedao;
		
		public HomeService(HomeDAO homedao) {// DAO »£√‚
			this.homedao = homedao;
		}
		
		public List<ProductVO> selectSmallCate1(){
			return homedao.selectSmallCate1();
		}
		public List<ProductVO> selectSmallCate2(){
			return homedao.selectSmallCate2();
		}
		public List<ManagementVO> selectBannerList(){
			return homedao.selectBannerList();
		}
		public List<ProductVO> selectList(String name){
			return homedao.selectList(name);
		}
		public List<ProductVO> selectBigCate(){
			return homedao.selectBigCate();
		}
}
