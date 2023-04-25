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
			
			//ī�װ� �̸��� �ҷ���
			String name = request.getParameter("name"); 
			
			//where like�� �̿��� �ش�ī�װ� �̸��� ī�װ��� ���Ե� ��ǰ����� �ҷ���
			List<ProductVO> list = productlistService.selectList(name);
		
			request.setAttribute("list", list);

			return ViewPath.PRODUCT + "productList.jsp";
		}
		
		@RequestMapping("/member/product/contentForm")
		public String detailForm(HttpServletRequest request) {
			
			int no = Integer.parseInt(request.getParameter("no"));
				
			//��ǰ ���� ���� ����(content)
			List <ProductVO> contentPic = productlistService.selectContentPic(no);
			if(contentPic ==null) {
				return "/team/";
			}
			request.setAttribute("contentPic", contentPic);
			
			//���� ���� ���� ����(rePic)
			List <BoardVO> rePic = productlistService.selectRePic();
			if(rePic ==null) {
				return "/team/";
			}
			request.setAttribute("rePic", rePic);
			
			//��ǰ ���� ���� ����(detail)
			List <ProductVO> detailPic = productlistService.selectDetailPic(no);
			if(detailPic ==null) {
				return "/team/";
			}
			request.setAttribute("detailPic", detailPic);
			
			//��ǰ ���� ����
			ProductVO vo = new ProductVO();
			vo = productlistService.selectProduct(no);
			if(vo == null) {
				return "/team/";
			}
			request.setAttribute("vo", vo);
		
			//����ǰ ��� ����
			List<ProductVO> freebies = productlistService.selectFreebies();
			if(freebies == null) {
				return "/team/";
			}
			request.setAttribute("freebies", freebies);								
			
			//��õ ��ǰ
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
			
			//���� �Խ��� ��� ����
			List<BoardVO> review = productlistService.selectReviewList(no);		
			request.setAttribute("review", review);
			
			//���� ���� 
			int viewCnt = productlistService.getReviewCnt(no);
			
			request.setAttribute("viewCnt", viewCnt);
			
			//�ɼ� ���
			List<ProductVO> option = productlistService.selectOptionList(no);
			if(option ==null) {
				return "/team/";
			}
			request.setAttribute("option",option);
			
			return ViewPath.PRODUCT + "contentForm.jsp";
		}
		
		
}
