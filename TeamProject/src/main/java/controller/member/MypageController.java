package controller.member;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.member.MypageService;
import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;
import vo.OrderVO;
import vo.ProductVO;

@Controller
public class MypageController {

	private MypageService mypageService;
	
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	
	@RequestMapping("/member/mypage/main")
	public String myPage(HttpServletRequest request) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		
		Integer mb_no =vo.getMb_no();
		
		if(mb_no < 1) {
			return "redirect:/member/login/loginForm";
		}
		
	
		vo = mypageService.selectOne(mb_no);
		GradeVO vo2 = mypageService.selectGrade(mb_no);
		int cr_cnt = mypageService.getCouponCount(mb_no);
		
		if(cr_cnt < 0) {
			return "redirect:/member/login/loginForm";
		}
		
	
		request.setAttribute("vo",vo);
		request.setAttribute("vo2",vo2);
		request.setAttribute("vo3", cr_cnt);
		System.out.println(vo);

		
		return ViewPath.MYPAGE + "main.jsp";
		
		
	}
	
	@RequestMapping("/member/mypage/infoUpdate")
	public String infoUpdate(HttpServletRequest request) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		if(mb_no <1) {
			return "redirect:member/login/loginForm";
		}
		
		vo = mypageService.selectOne(mb_no);
		GradeVO vo2 = mypageService.selectGrade(mb_no);
		
		request.setAttribute("vo",vo);
		request.setAttribute("vo2",vo2);
		
		return ViewPath.MYPAGE + "infoUpdate.jsp";
	}
	
	@RequestMapping("/member/mypage/couponList")
	public String list(Model model, HttpServletRequest request,  Integer start) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();


		if(start == null) {
			start = 1;
		}

		List<CouponVO> list = mypageService.getCouponList(mb_no, start);

	
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		//           
		int nowPage = (start - 1) / 5 + 1;
		//            
		int total = mypageService.couponTotal(mb_no);
		int totalPage = total % 5 == 0 ? total / 5 : total / 5 + 1;
		
		System.out.println(nowPage);
		System.out.println(total);
		System.out.println(totalPage);
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		

		
		return ViewPath.MYPAGE +"couponList.jsp";
	}

	@RequestMapping("/member/mypage/point")
	public String point(HttpServletRequest request, Integer start,Model model) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();

		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		
		if(start == null) {
			start = 1;
		}
		
		List<OrderVO> list = mypageService.getPointOrder(mb_no, start);
		
		System.out.println(start + "시작");
		
		
		vo = mypageService.selectOne(mb_no);
		int usedPointSum =mypageService.getUsedPointSum(mb_no);
		
		int nowPage = (start - 1) / 5 + 1;
		//            
		int total = mypageService.getOrderTotal(mb_no);
		int totalPage = total % 5 == 0 ? total / 5 : total / 5 + 1;
		
		System.out.println(nowPage);
		System.out.println(total);
		System.out.println(totalPage);
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		
		model.addAttribute("list", list);
		request.setAttribute("vo", vo);
		request.setAttribute("usedPointSum", usedPointSum);
		
		return ViewPath.MYPAGE + "point.jsp";
	}
	
	@RequestMapping("/member/mypage/update")
	public String update(HttpServletRequest request,MemberVO vo) {
//		Enumeration params = request.getParameterNames();
//		while(params.hasMoreElements()) {
//		  String name = (String) params.nextElement();
//		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
//		}
//		System.out.println();

		
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		

		
		if(mb_no < 1) {
			return "redirect:member/login/loginForm";
		}
		
		vo.setMb_no(mb_no);
		vo.setMb_pw(request.getParameter("mb_pw"));
		vo.setMb_email1(request.getParameter("mb_email1"));
		vo.setMb_email2(request.getParameter("mb_email2"));
		vo.setMb_tel1(request.getParameter("mb_tel1"));
		vo.setMb_tel2(request.getParameter("mb_tel2"));
		vo.setMb_tel3(request.getParameter("mb_tel3"));
		System.out.println(request.getParameter("mb_sms"));
		vo.setMb_sms(Integer.parseInt(request.getParameter("mb_sms").trim()));
		vo.setMb_ckmail(Integer.parseInt(request.getParameter("mb_sms").trim()));
	
		
		int check = mypageService.update(vo);

	
		
		String msg = null;
		String url = null;
		
		
		if(check != 0){
			msg = "수정 성공!!MyPage로...";
			url = "/team/member/mypage/main";
		}else{
			msg = "수정 실패!!";
			url = "/team/member/mypage/infoUpdate";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		return ViewPath.MYPAGE + "result.jsp";
	}

	@RequestMapping("/member/mypage/infoDelete")
	public String infoDelete(HttpServletRequest request) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		if(mb_no <1) {
			return "redirect:/login/loginForm";
		}
		
		vo = mypageService.selectOne(mb_no);
		
		request.setAttribute("vo", vo);
		
		return ViewPath.MYPAGE + "infoDelete.jsp";
	}
	
	@RequestMapping("/member/mypage/delete")
	public String leave(HttpServletRequest request,String mb_pw,HttpServletResponse response) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		if(mb_no <1) {
			return "redirect:/login/loginForm";
		}
		
		vo = mypageService.selectOne(mb_no);
		
		String msg = "";
		String url = "";
		
		if(!vo.getMb_pw().equals(mb_pw)){
			msg = "비밀번호가 틀렸습니다.!!이전페이지로....";
			url = "/myhome/member/deleteform";
		}else{

			int check = mypageService.leave(mb_no);
			if(check != 0){
				
				//  Ű         .
				Cookie[] cks = request.getCookies();
				if(cks != null){
					for(Cookie ck : cks){
						if(ck.getName().equals("ckid")){
							if(ck.getValue().equals(vo.getMb_id())){
								ck.setMaxAge(0);
								ck.setPath("/");
								response.addCookie(ck);
								break;
							}
						}
					}
				}
			
				request.getSession().invalidate();
				msg =  "탈퇴성공!!!";
				url = "/team/";
				
				
			}else{
				msg = "탈퇴실패!!잠시후에 다시 시도하십시오";
				url = "/team/member/mypage/infoUpdate";
			}
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return ViewPath.MYPAGE + "result.jsp";
	}
	
	@RequestMapping("/member/mypage/orderStatus")
	public String selectProcess(Model model, HttpServletRequest request, Integer start) {
		MemberVO vo = new MemberVO();
		
		vo = (MemberVO) request.getSession().getAttribute("login");
		Integer mb_no=0;
		try {	
			 mb_no =vo.getMb_no();
		} catch (NullPointerException e) {
			e.printStackTrace();
			String msg = "로그인이 필요한 서비스 입니다. 로그인 페이지로 이동합니다.";
			String url = "/team/member/login/loginForm";
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.LOGIN + "result.jsp";
		}
		
		
		
		if(start == null) {
			start = 1;
		}
		
		
		List<OrderVO> list = mypageService.orderList(mb_no, start);
		
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		int nowPage = (start - 1) / 5 + 1;
		int total = mypageService.getOrderTotal(mb_no);
		int totalPage = total % 5 == 0 ? total / 5 : total / 5 + 1;
		
		System.out.println(nowPage);
		System.out.println(total);
		System.out.println(totalPage);
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		
		
		request.setAttribute("list", list);
		
		
		
		
		return ViewPath.MYPAGE + "orderStatus.jsp";
	}
	
	@RequestMapping("/member/mypage/shippingAddr")
	public String shippingAddr(Model model, Integer start,HttpServletRequest request) {
		
		MemberVO vo = new MemberVO();
		
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		
		
		if(start == null) {
			start = 1;
		}
		
		List<MemberVO> list = mypageService.shippingAddr(mb_no, start);

		
		
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		//           
		int nowPage = (start - 1) / 50 + 1;
		//            
		int total = mypageService.getTotal();
		int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		
		return ViewPath.MYPAGE + "shippingAddr.jsp";
				
	}
	
	@RequestMapping("/member/mypage/addAddrForm")
	public String addAddrForm() {


		return ViewPath.MYPAGE + "addAddrForm.jsp";
				
	}
	
	
	@RequestMapping("/member/mypage/addAddr")
	public String addAddr(HttpServletRequest request, MemberVO vo) {

		
		vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}

		
		
		vo.setMb_no(mb_no);
		vo.setShip_name(request.getParameter("ship_name"));
		vo.setShip_personname(request.getParameter("ship_personname"));
		vo.setShip_addr(request.getParameter("ship_addr"));
		vo.setShip_tel1(request.getParameter("ship_tel1"));
		vo.setShip_tel2(request.getParameter("ship_tel2"));
		vo.setShip_tel3(request.getParameter("ship_tel3"));
		
		String ship_default = request.getParameter("ship_default");
		
		if(ship_default == null) {
			ship_default = "0";
			
			
		}
		
	
		vo.setShip_default(Integer.parseInt(ship_default));
	
		
		int check = mypageService.addAddr(vo);
			
		String msg = null;
		String url = null;
		
		if(check != 0){
			msg = "배송지가 등록 되었습니다. ";
			url = "/team/member/mypage/shippingAddr";	
			
		}else{
			msg = "배송지 등록에 실패하였습니다. ";
			url = "history.back()";		
		}
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		
		return ViewPath.MYPAGE + "result.jsp";
		
		
	}
	
	
	@RequestMapping("member/mypage/order/shippingaddr/delete")
	public String AddrDeleteForm(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		
		
		String[] arr = request.getParameterValues("delList");
		
		for(int i =0; i <arr.length; i++) {
			
			System.out.println(arr[i]);
			String no = arr[i];			
			su = mypageService.deleteAddr(no);
			result += su;
		}
		
		String msg = null;
		String url = null;
		
		
		
		if(result != 0) {
			msg = "선택하신 주소록이 삭제되었습니다.";
		}else {
			msg = "선택하신 주소록 삭제에 실패하였습니다.";
		}
		
		
		url = "/team/member/mypage/shippingAddr";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return ViewPath.MYPAGE + "result.jsp";
	}
	
	
	@RequestMapping("/member/mypage/updateAddrForm")
	public String AddrUpdateForm(HttpServletRequest request) {
		
		
		int no = Integer.parseInt(request.getParameter("no").trim());
		
		System.out.println(no);
	
		MemberVO vo = new MemberVO();
//		vo = (MemberVO) request.getSession().getAttribute("login");
//		
//		Integer mb_no = vo.getMb_no();
//		
//		if(mb_no <1) {
//			return "redirect:member/login/loginForm";
//		}
//	
//		vo.setShipaddr_no(no);
		
		vo = mypageService.updateAddrForm(no);
		
		
		request.setAttribute("vo", vo);
		
		
		return ViewPath.MYPAGE + "updateAddrForm.jsp";
	}
	
	@RequestMapping("/member/mypage/updateAddr")
	public String Addrupdate(HttpServletRequest request, Integer start) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		
		List<MemberVO> list = mypageService.shippingAddr(mb_no, start);
		
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		vo = mypageService.selectOne(mb_no);

		
		
		request.setAttribute("vo",vo);
		request.setAttribute("no",no);
		request.setAttribute("list", list);
		
	
		
		
		return ViewPath.MYPAGE + "result.jsp";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
