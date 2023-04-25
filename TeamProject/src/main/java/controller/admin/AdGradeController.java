package controller.admin;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.admin.AdGradeService;
import vo.GradeVO;

@Controller
public class AdGradeController {

	private AdGradeService gradeService;
	
	public AdGradeController(AdGradeService gradeService) { 
		this.gradeService = gradeService; 	
	}
	
	
	@RequestMapping("/admin/member/grade")
	public String grade(HttpServletRequest request) {
		
		List<GradeVO> list = gradeService.selectGrade();
		
		request.setAttribute("list", list);
		
		return ViewPath.ADMINMEMBER + "grade.jsp";
	}
	
	@RequestMapping("/admin/member/insertGrade")
	public String insertGrade(HttpServletRequest request) {
		
		String [] arrno = request.getParameterValues("no");
		String [] arrname = request.getParameterValues("name");
		String [] arrprice = request.getParameterValues("price");
		String [] arrper = request.getParameterValues("per");
		int result = 0;
		String msg = null;
		String url = null;	
		
		try {
			for(int i = 0; i < arrname.length; i++) {
				GradeVO vo = new GradeVO();
				
				int no = Integer.parseInt(arrno[i]);
				int price = Integer.parseInt(arrprice[i]);
				int per = Integer.parseInt(arrper[i]);

				vo.setGrade_no(no);
				vo.setGrade_name(arrname[i]);
				vo.setGrade_price(price);
				vo.setGrade_per(per);
				
				int su = gradeService.insertGrade(vo);
				result+=su;
			}
			if(result !=0) {
				msg = "등급정보 수정에 성공하였습니다.";
			}else {
				msg = "등급정보 수정에 실패하였습니다.";
			}
		} catch (NumberFormatException e) {
			msg = "조건금액 및 적립율에는 숫자만 입력 가능합니다.";
			e.printStackTrace();
		}
		
		
		url ="/team/admin/member/grade";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return ViewPath.ADMINMEMBER+"result.jsp";
	}
	@RequestMapping("/admin/member/deleteGrade")
	public String deleteGrade(HttpServletRequest request) {
		String msg = null;
		String url = null;
		int su = 0;
		int result =0;
		
		String[] arr = request.getParameterValues("delList");
		
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = gradeService.deleteGrade(no);
			result += su;
		}
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.(해당 등급에 속해 있는 회원이 있을 경우 결과가 반영되지 않습니다.)";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		url = "/team/admin/member/grade";
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		return ViewPath.ADMINMEMBER + "result.jsp";
	}
	
	
}
