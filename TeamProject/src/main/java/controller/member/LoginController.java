package controller.member;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ViewPath;
import service.member.LoginService;
import vo.JoinVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping("/member/login/loginForm")
	public String loginForm(HttpServletRequest request) {
		
		String id = request.getParameter("id");

		boolean check = false;
		//쿠키
		if(id == null){
			
			Cookie[] cks = request.getCookies();
			
			if(cks != null){
				for(Cookie ck : cks){
					if(ck.getName().equals("ckid")){
						id = ck.getValue();
						check = true;
						break;
					}
				}
			}
			
			if(id == null){
				id = "";
			}
		}
		
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		
		return ViewPath.LOGIN + "form.jsp";
	}
	
	@RequestMapping("/member/login/check")
	public String loginCheck(HttpServletRequest request,HttpServletResponse response) {
		MemberVO vo = new MemberVO();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		vo.setMb_id(id);
		vo.setMb_pw(pw);
		
		vo = loginService.checkLogin(vo);
		
		String msg = null;
		boolean check = false;
		
		if(vo != null){
			msg = vo.getMb_id() + "님이 로그인하셨습니다.";
			check = true;
				
			//session에 로그인 값 저장...
			request.getSession().setAttribute("login", vo); // 로그인 처리...
			
			//아이디 기억하기 체크 유무
			String ckid = request.getParameter("ckid");
			
			Cookie ck = null;
			
			//쿠키파일 읽어 오기...
			Cookie[] cks = request.getCookies();
			
			//기존 쿠키파일 검색
			if(cks != null){
				for(Cookie c : cks){
					if(c.getName().equals("ckid")){
						ck = c;
						break;
					}
				}
			}
			
			if(ckid != null){ //체크 되어 있을때
				if(ck == null){ // 쿠키파일 없을때
					ck = new Cookie("ckid",vo.getMb_id());
					
					//root로 경로 설정
					ck.setPath("/");
				
					//유효시간 설정
					ck.setMaxAge(60*60*24);
				
					//클라이언트에게 쿠키파일 생성
					response.addCookie(ck);
				}else{ //있을때
					if(!ck.getValue().equals(vo.getMb_id())){
						ck.setValue(vo.getMb_id());
						response.addCookie(ck);
					}
				}
			}else{ // 체크 안되어 있을때
				if(ck != null){
					if(ck.getValue().equals(vo.getMb_id())){
						ck.setMaxAge(0);
						ck.setPath("/");
						response.addCookie(ck);
					}
				}
			}
		}else{
			msg = "아이디 또는 비밀번호가 일치하지 않습니다.";		 
		}
		
		String url = "/team/";
		
		request.setAttribute("msg", msg);
		request.setAttribute("check", check);
		request.setAttribute("url", url);
		
		return ViewPath.LOGIN + "result.jsp";
	}
	
	@RequestMapping("/member/login/logout")
	public String logout(Model model, HttpSession session) {
		
		session.invalidate();
		
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/team/");
		
		return ViewPath.LOGIN + "result.jsp";
	}
	
	@RequestMapping("/admin/adminHome")
	public String adminHome() {

		return ViewPath.ADMIN + "adminHome.jsp";
	}
	
	@RequestMapping("/member/login/findForm")
	public String findForm() {
		return ViewPath.LOGIN + "findForm.jsp";
	}
	
	@RequestMapping("/member/login/find")
	public String find(HttpServletRequest request) {
		String mode = request.getParameter("mode");

		MemberVO vo = new MemberVO();
		String id = null;
		if(mode.equals("pw")){
			id = request.getParameter("id");
			vo.setMb_id(id);
		}
		
		String find = "";
		
		String[] radioArray = request.getParameterValues("radio");
		String radio = String.join("",radioArray);
		
		if(radio.equals("tel")) {
			String name = request.getParameter("name");
			String tel1 = request.getParameter("tel1");
			String tel2 = request.getParameter("tel2");
		    String tel3 = request.getParameter("tel3");
			
			vo.setMb_name(name);
			vo.setMb_tel1(tel1);
			vo.setMb_tel2(tel2);
			vo.setMb_tel3(tel3);
				
			
			if(id == null){		
				find = (String)loginService.findId(vo);
			}
			else{
				find = loginService.findPw(vo);		
			}
		}
		
		if(radio.equals("email")) {
			String name = request.getParameter("name");
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
		    
			vo.setMb_name(name);
			vo.setMb_email1(email1);
			vo.setMb_email2(email2);
			
			if(id == null){		
				find = (String)loginService.findId2(vo);
			}
			else{
				find = loginService.findPw2(vo);		
			}
		}

		boolean check = false;
		if(find != null){
			check = true;
			
			String re = "";
			
			for(int i = 0; i < find.length(); i++){
				if(i % 2 == 0){
				re += find.charAt(i);
			}else{
				re += "*";
				}
			}
			
			if(mode.equals("id")){
				request.setAttribute("id", re);
		}else{	
			request.setAttribute("pw", re);	
			}
		}
		
		request.setAttribute("check", check);
		

		return ViewPath.LOGIN + "findResult.jsp" ;
		}
	@RequestMapping("/member/login/kakaoLogin")
	public String kakaoLogin(HttpServletRequest request) {
		String url =  null;
		
		MemberVO vo = new MemberVO();
		
		String mb_id = request.getParameter("mb_id");
		String mb_name = request.getParameter("mb_name");
		String mode = "kakao";
		
		vo.setMb_id(mb_id);
		vo.setMb_name(mb_name);

		
		vo = loginService.checkKakaoLogin(vo);
		
		String msg = null;
		boolean check = false;
		
		if(vo != null){
			msg = vo.getMb_name() + "님이 로그인하셨습니다.";
			check = true;
				
			//session에 로그인 값 저장...
			request.getSession().setAttribute("login", vo); // 로그인 처리...
			url ="/team/";
		} else {
			request.setAttribute("mb_id", mb_id);
			request.setAttribute("mb_name", mb_name);
			request.setAttribute("mode", mode);
			
			return ViewPath.JOIN +"joinForm.jsp";
		}			

		request.setAttribute("msg", msg);
		request.setAttribute("check", check);
		request.setAttribute("url", url);
		
		return ViewPath.LOGIN + "result.jsp";
	}
	
	
	
}
























