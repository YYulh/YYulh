package controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.admin.AdCategorieService;
import vo.ProductVO;

@Controller
public class AdCategorieController {

	private AdCategorieService categorieService;
	
	public AdCategorieController(AdCategorieService categorieService) { 
		this.categorieService = categorieService; 	
	}
	@RequestMapping("/admin/management/categorieForm")
	public String categorieForm(HttpServletRequest request) {
		
		List<ProductVO> psc1 = categorieService.selectSmallCate1(); 
		List<ProductVO> psc2 = categorieService.selectSmallCate2(); 
		
		request.setAttribute("psc1", psc1);
		request.setAttribute("psc2", psc2);
		
		return ViewPath.ADMINMANAGEMENT + "categorie.jsp";
	}
	
	@RequestMapping("/admin/management/insertPsc1")
	public String insertPsc1(HttpServletRequest request) {
		
		String [] arrno = request.getParameterValues("no");
		String [] arrname = request.getParameterValues("psc_name1");
		
		int result = 0;
		String msg = null;
		String url = null;	
		
		for(int i = 0; i < arrname.length; i++) {
			ProductVO vo = new ProductVO();
			
			int no = Integer.parseInt(arrno[i]);
			
			
			vo.setPsc_no(no);
			vo.setPbc_no(1);;
			vo.setPsc_name(arrname[i]);
		
			int su = categorieService.insertSmallCate1(vo);
			result+=su;
		}
		if(result !=0) {
			msg = "Product 카테고리 수정에 성공하였습니다.";
		}else {
			msg = "Product 카테고리 수정에 실패하였습니다.";
		}
		
		url = "/team/admin/management/categorieForm";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
	}
	@RequestMapping("/admin/management/deleteCate1")
	public String deleteGrade(HttpServletRequest request) {
		String msg = null;
		String url = null;
		int su = 0;
		int result =0;
		
		String[] arr = request.getParameterValues("delList1");
		
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = categorieService.deleteCate1(no);
			result += su;
		}
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		url = "/team/admin/management/categorieForm";
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
	}
	
	@RequestMapping("/admin/management/insertPsc2")
	public String insertPsc2(HttpServletRequest request) {
		
		String [] arrno = request.getParameterValues("no");
		String [] arrname = request.getParameterValues("psc_name2");
		
		int result = 0;
		String msg = null;
		String url = null;	
		
		for(int i = 0; i < arrname.length; i++) {
			ProductVO vo = new ProductVO();
			
			int no = Integer.parseInt(arrno[i]);
			
			
			vo.setPsc_no(no);
			vo.setPbc_no(2);
			vo.setPsc_name(arrname[i]);
		
			int su = categorieService.insertSmallCate2(vo);
			result+=su;
		}
		if(result !=0) {
			msg = "Product 카테고리 수정에 성공하였습니다.";
		}else {
			msg = "Product 카테고리 수정에 실패하였습니다.";
		}
		
		url = "/team/admin/management/categorieForm";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
	}
	@RequestMapping("/admin/management/deleteCate2")
	public String deleteCate2(HttpServletRequest request) {
		String msg = null;
		String url = null;
		int su = 0;
		int result =0;
		
		String[] arr = request.getParameterValues("delList2");
		
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = categorieService.deleteCate2(no);
			result += su;
		}
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		url = "/team/admin/management/categorieForm";
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
	}
	
	
}
