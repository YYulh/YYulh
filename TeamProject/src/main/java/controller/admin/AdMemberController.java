package controller.admin;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.admin.AdMemberService;
import vo.GradeVO;
import vo.MemberVO;

@Controller
public class AdMemberController {

	private AdMemberService memberService;
	
	public AdMemberController(AdMemberService memberService) { 
		this.memberService = memberService; 	
	}
	
	@RequestMapping("/admin/member/list")
	public String list(Model model,Integer start) {
		
		
		if(start == null) {
			start = 1;
		}
		
		List<MemberVO> list = memberService.selectList(start);
		
		
		List<GradeVO> grade = memberService.selectGrade();
		model.addAttribute("grade",grade);
		
		//현재 페이지
		int nowPage = (start - 1) / 50 + 1;
		//총 글의 개수
		int total = memberService.getTotal();
		int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		return ViewPath.ADMINMEMBER + "list.jsp";
	}
	@RequestMapping("/admin/member/leaveList")
	public String leaveList(Model model,Integer start) {
		
		
		if(start == null) {
			start = 1;
		}
		
		List<MemberVO> list = memberService.selectLeaveList(start);
		
		
		List<GradeVO> grade = memberService.selectGrade();
		model.addAttribute("grade",grade);
		
		//현재 페이지
		int nowPage = (start - 1) / 50 + 1;
		//총 글의 개수
		int total = memberService.getTotal();
		int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		return ViewPath.ADMINMEMBER + "leaveList.jsp";
	}
	@RequestMapping("/admin/member/deleteMember")
	public String deleteMember(HttpServletRequest request) {
		String[] arr = request.getParameterValues("check");
		String msg = null;
		String url = null;
		int su = 0;
		int result = 0;
		
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = memberService.deleteMember(no);
			result += su;
			
		}
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		 url = "/team/admin/member/memberList";
		 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return ViewPath.ADMINMEMBER +"leaveList.jsp";
	}
	
}
