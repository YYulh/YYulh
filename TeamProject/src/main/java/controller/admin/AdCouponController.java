package controller.admin;



import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.admin.AdCouponService;
import vo.CouponVO;
import vo.GradeVO;
import vo.MemberVO;

@Controller
public class AdCouponController {

	private AdCouponService couponService;
	
	public AdCouponController(AdCouponService couponService) { 
		this.couponService = couponService; 	
	}
	
	//쿠폰 목록
	@RequestMapping("/admin/coupon/couponList")
	public String couponList(Model model, Integer start) {
		
		if(start == null) {
			start = 1;
		}
		
		//보유중인 쿠폰목록을 start의 수 만큼 제한하여 페이징 처리 
		List<CouponVO> list = couponService.selectCpnList(start);			

		//현재 페이지
		int nowPage = (start - 1) / 15 + 1;
		//총 글의 개수
		int total = couponService.getTotal();
		int totalPage = total % 15 == 0 ? total / 15 : total / 15 + 1;
		
		//model 영역에 저장
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		return ViewPath.ADMINCOUPON + "couponList.jsp";
	}
	
	//쿠폰 등록하는 view
	@RequestMapping("admin/coupon/insertCouponForm")
	public String insertCouponForm(HttpServletRequest request) {
		
		//쿠폰 조건에 해당하는 '등급' 목록 불러오기
		List<GradeVO> grade = couponService.selectGrade();
		
		//request 영역에 저장
		request.setAttribute("grade",grade);
		
		return ViewPath.ADMINCOUPON + "insertCoupon.jsp";
	}
	
	//쿠폰 등록
	@RequestMapping("/admin/coupon/insertCoupon")
	public String insertCoupon(HttpServletRequest request,CouponVO vo) { 
		// ->view 에서 vo에서 받아줄 이름들로 name을 설정 해주었기 때문에 매개변수에서 vo로 받아주면 따로 parameter를 받고, 저장할 필요가 없음!
		
		String msg = null;
		String url = null;
		
		//새로운 쿠폰 정보 insert
		int su =couponService.newCoupon(vo);
		
		if(su != 0) {
			msg = "쿠폰 등록에 성공하였습니다";
		}else {
			msg = "쿠폰 등록에 실패하였습니다";
		}
		url = "/team/admin/coupon/couponList";
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		return ViewPath.ADMINCOUPON + "result.jsp";
	}
	
	//쿠폰 삭제
	@RequestMapping("/admin/coupon/deleteCpn")
	public String deleteBanner(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		String msg = null;
		String url = null;
		
		//'delList'의 체크된 value 들을 parameter로 받아서 'arr' 이라는 String type 배열에 담음 
		String[] arr = request.getParameterValues("delList");
				
		//'arr'의 길이만큼 delete를 반복, su는 성공시 1을 반환하기 때문에  모두 성공했다면  result == arr의 길이가 됨
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = couponService.deleteCpn(no);
			result += su;		
		}
		
		if(result == arr.length) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			//하나라도 실패하면
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		 url = "/team/admin/coupon/couponList";
		 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return ViewPath.ADMINCOUPON + "result.jsp";
		}
	
	//유저에게 쿠폰 전송하는 view
	@RequestMapping("/admin/coupon/insertUserForm")
	public String insertUserForm(HttpServletRequest request) {
		
		int cpn_no = Integer.parseInt(request.getParameter("no"));
		
		int limit = couponService.getRimit(cpn_no);
		
		//쿠폰 제한 등급(금액)에 따라 해당되는 멤버 목록을 반환
		List<MemberVO> list = couponService.selectMbList(limit);
		
		request.setAttribute("list", list);
		request.setAttribute("cpn_no", cpn_no);
		
		return ViewPath.ADMINCOUPON + "insertUserForm.jsp";
	}
	
	//유저에게 쿠폰 전송
	@RequestMapping("/admin/coupon/insertUser")
	public String insertUser(HttpServletRequest request) {
		String msg = null;
		String url = null;
		int result = 0;
		
		int cpn_no = Integer.parseInt(request.getParameter("cpn_no"));
		
		//체크된 'sendList'(멤버 번호)를 배열로 받아옴
		String [] arr = request.getParameterValues("sendList");
		
		//String  타입으로 받아온 배열을 int형 배열로 바꿔주기 위해 arr의 길이 만큼의 int형 배열 선언
		int[] intArr = new int[arr.length];
		
		CouponVO vo =new CouponVO();
		
		// arr의 길이만큼(체크된 멤버의 수만큼) 쿠폰 시리얼 번호를 생성하여 insert 반복
		 for(int i =0; i< arr.length; i++) {
			 
			 intArr[i] = Integer.parseInt(arr[i]);
		//----------------------------------------------------------------------------
			 //시리얼 번호에서 랜덤으로 사용할 문자 배열선언
			 char[] charList =
				    {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
				     'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
				     'W','X','Y','Z'};
				
				int charCnt = charList.length;
				
				//랜덤메소드
				Random rnd = new Random();
				
				//j의 범위(생성할 시리얼번호의 자릿수) 만큼 char[]에서 랜덤한 문자열을 뽑아와 StringBuffer를 통해 차례대로 'serial'에 추가해줌
				StringBuffer buf = new StringBuffer();
				
				//0-11로 범위를 정했기에 12자리의 시리얼 번호가 생성되게 됨
				for (int j= 0; j < 12; j++) {
				    buf.append(charList[rnd.nextInt(charCnt)]);
				   }
				String serial = buf.toString();
				
		//-------------------------------------------------------------------------
			 vo.setCpn_no(cpn_no);
			 vo.setMb_no(intArr[i]);
			 vo.setCr_serial(serial);
			 
			 int su = couponService.insertUser(vo);
			 result += su;
		 }
		 if(result == arr.length) {
			 msg = "쿠폰 전송에 성공하였습니다.";
		 }else {
			 msg = "쿠폰 전송에 실패하였습니다. 관리자에게 문의 주십시오";
		 }
		 url = "/team/admin/coupon/couponList";

		 request.setAttribute("url", url);
		 request.setAttribute("msg", msg);
		 
		return ViewPath.ADMINCOUPON + "result.jsp";
	}
	
}
