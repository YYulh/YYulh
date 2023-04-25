package controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.member.ProductListService;
import vo.BoardVO;
import vo.ProductVO;

	@Controller
	public class ProductListController {

		private ProductListService productlistService;
		
		public ProductListController(ProductListService productlistService) {
			this.productlistService = productlistService;
		}
		
		
		@RequestMapping("/member/product/productList")
		public String productList(HttpServletRequest request) {
			
			//카테고리 이름을 불러옴
			String name = request.getParameter("name"); 
			
			//where like를 이용해 해당카테고리 이름이 카테고리에 포함된 제품목록을 불러옴
			List<ProductVO> list = productlistService.selectList(name);
		
			request.setAttribute("list", list);

			return ViewPath.PRODUCT + "productList.jsp";
		}
		
		@RequestMapping("/member/product/contentForm")
		public String detailForm(HttpServletRequest request) {
			
			int no = Integer.parseInt(request.getParameter("no"));
				
			//제품 사진 정보 저장(content)
			List <ProductVO> contentPic = productlistService.selectContentPic(no);
			if(contentPic ==null) {
				return "/team/";
			}
			request.setAttribute("contentPic", contentPic);
			
			//리뷰 사진 정보 저장(rePic)
			List <BoardVO> rePic = productlistService.selectRePic();
			if(rePic ==null) {
				return "/team/";
			}
			request.setAttribute("rePic", rePic);
			
			//제품 사진 정보 저장(detail)
			List <ProductVO> detailPic = productlistService.selectDetailPic(no);
			if(detailPic ==null) {
				return "/team/";
			}
			request.setAttribute("detailPic", detailPic);
			
			//제품 정보 저장
			ProductVO vo = new ProductVO();
			vo = productlistService.selectProduct(no);
			if(vo == null) {
				return "/team/";
			}
			request.setAttribute("vo", vo);
		
			//사은품 목록 저장
			List<ProductVO> freebies = productlistService.selectFreebies();
			if(freebies == null) {
				return "/team/";
			}
			request.setAttribute("freebies", freebies);								
			
			//추천 상품
			String more = productlistService.selectProductMore(no); 		
			
			String [] moreArr = more.split(","); 
			Map<String,Object> map = new HashMap();
			for(int i = 0; i < moreArr.length; i++) {
				
				map.put("more"+i,moreArr[i]);
			}
			List<ProductVO> moreList = productlistService.selectMoreList(map); 
			if(moreList ==null) {
				return "/team/";
			}
			request.setAttribute("moreList", moreList);
			
			//리뷰 게시판 목록 저장
			List<BoardVO> review = productlistService.selectReviewList(no);		
			request.setAttribute("review", review);
			
			//리뷰 갯수 
			int viewCnt = productlistService.getReviewCnt(no);
			
			request.setAttribute("viewCnt", viewCnt);
			
			//옵션 목록
			List<ProductVO> option = productlistService.selectOptionList(no);
			if(option ==null) {
				return "/team/";
			}
			request.setAttribute("option",option);
			
			return ViewPath.PRODUCT + "contentForm.jsp";
		}
		
		
}
