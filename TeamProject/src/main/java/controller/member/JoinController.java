package controller.member;

import java.sql.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ViewPath;
import service.member.JoinService;
import vo.JoinVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class JoinController {

	private JoinService joinService;
	
	public JoinController(JoinService joinService) {
		this.joinService = joinService;
	}
	
	@RequestMapping("/member/join/joinForm")	
	public String joinForm(HttpServletRequest request) {
		String mode = request.getParameter("mode");
		
		request.setAttribute("mode", mode);
		return ViewPath.JOIN + "joinForm.jsp";	
	}
	
	@RequestMapping("/member/join/join")
	public String join(HttpServletRequest request, JoinVO vo) {
		int su=0;
		String mode = request.getParameter("mode");
		
		if(mode.equals("nomal")) {			
		 su = joinService.insert(vo);
		
		} else if(mode.equals("kakao")) {
		 su = joinService.kakaoInsert(vo);
		 
		}

		String url = null;
		String msg = null;

		if (su != 0) {
			int no = joinService.selectLastMb();
			int cpn_no = joinService.selectCpnNo();
			//--------------------------------------
			char[] charList =
			    {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
			     'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
			     'W','X','Y','Z'};
			
			int charCnt = charList.length;
			
			Random rnd = new Random();
			
			StringBuffer buf = new StringBuffer();
			
			for (int j= 0; j < 12; j++) {
			    buf.append(charList[rnd.nextInt(charCnt)]);
			   }
			String serial = buf.toString();
			//---------------------------------------
			
			OrderVO ord = new OrderVO();
			
			ord.setMb_no(no);
			ord.setCpn_no(cpn_no);
			ord.setCr_serial(serial);
			
			joinService.joinCpn(ord);
			
			msg = "회원가입 성공!..로그인페이지로..";
			url = "/team/";

		} else {
			msg = "회원가입 실패! 관리자에게 문의해주십시오";
			url = "/team/";
		}

		request.setAttribute("url", url);
		request.setAttribute("msg", msg);

		return ViewPath.JOIN + "result.jsp";
	}

	@RequestMapping(value = "/member/checkId")
	@ResponseBody
	public String checkId(String id) {

		if (joinService.checkId(id) != null) {
			return "이미 사용중인 아이디입니다";
		} else {
			return "사용가능한 아이디입니다";
		}
	}
	
	@RequestMapping("/member/join/kakaoJoinForm")
	public String kakaoJoinForm(HttpServletRequest request) {
		String mb_id = request.getParameter("mb_id");
		String mb_name = request.getParameter("mb_name");
		String mode = request.getParameter("mode");
		
		MemberVO vo = new MemberVO();
		
		vo.setMb_id(mb_id);
		vo.setMb_name(mb_name);


		request.setAttribute("vo", vo);
		request.setAttribute("mode", mode);
		
		return ViewPath.JOIN + "joinForm.jsp";
	}
	
	
}
























