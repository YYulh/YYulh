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
	
	//���� ���
	@RequestMapping("/admin/coupon/couponList")
	public String couponList(Model model, Integer start) {
		
		if(start == null) {
			start = 1;
		}
		
		//�������� ��������� start�� �� ��ŭ �����Ͽ� ����¡ ó�� 
		List<CouponVO> list = couponService.selectCpnList(start);			

		//���� ������
		int nowPage = (start - 1) / 15 + 1;
		//�� ���� ����
		int total = couponService.getTotal();
		int totalPage = total % 15 == 0 ? total / 15 : total / 15 + 1;
		
		//model ������ ����
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		return ViewPath.ADMINCOUPON + "couponList.jsp";
	}
	
	//���� ����ϴ� view
	@RequestMapping("admin/coupon/insertCouponForm")
	public String insertCouponForm(HttpServletRequest request) {
		
		//���� ���ǿ� �ش��ϴ� '���' ��� �ҷ�����
		List<GradeVO> grade = couponService.selectGrade();
		
		//request ������ ����
		request.setAttribute("grade",grade);
		
		return ViewPath.ADMINCOUPON + "insertCoupon.jsp";
	}
	
	//���� ���
	@RequestMapping("/admin/coupon/insertCoupon")
	public String insertCoupon(HttpServletRequest request,CouponVO vo) { 
		// ->view ���� vo���� �޾��� �̸���� name�� ���� ���־��� ������ �Ű��������� vo�� �޾��ָ� ���� parameter�� �ް�, ������ �ʿ䰡 ����!
		
		String msg = null;
		String url = null;
		
		//���ο� ���� ���� insert
		int su =couponService.newCoupon(vo);
		
		if(su != 0) {
			msg = "���� ��Ͽ� �����Ͽ����ϴ�";
		}else {
			msg = "���� ��Ͽ� �����Ͽ����ϴ�";
		}
		url = "/team/admin/coupon/couponList";
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		
		return ViewPath.ADMINCOUPON + "result.jsp";
	}
	
	//���� ����
	@RequestMapping("/admin/coupon/deleteCpn")
	public String deleteBanner(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		String msg = null;
		String url = null;
		
		//'delList'�� üũ�� value ���� parameter�� �޾Ƽ� 'arr' �̶�� String type �迭�� ���� 
		String[] arr = request.getParameterValues("delList");
				
		//'arr'�� ���̸�ŭ delete�� �ݺ�, su�� ������ 1�� ��ȯ�ϱ� ������  ��� �����ߴٸ�  result == arr�� ���̰� ��
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = couponService.deleteCpn(no);
			result += su;		
		}
		
		if(result == arr.length) {
			msg = "�����Ͻ� �׸��� �����Ǿ����ϴ�.";
		}else {
			//�ϳ��� �����ϸ�
			msg = "�����Ͻ� �׸� ������ �����Ͽ����ϴ�.";
		}
		 url = "/team/admin/coupon/couponList";
		 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return ViewPath.ADMINCOUPON + "result.jsp";
		}
	
	//�������� ���� �����ϴ� view
	@RequestMapping("/admin/coupon/insertUserForm")
	public String insertUserForm(HttpServletRequest request) {
		
		int cpn_no = Integer.parseInt(request.getParameter("no"));
		
		int limit = couponService.getRimit(cpn_no);
		
		//���� ���� ���(�ݾ�)�� ���� �ش�Ǵ� ��� ����� ��ȯ
		List<MemberVO> list = couponService.selectMbList(limit);
		
		request.setAttribute("list", list);
		request.setAttribute("cpn_no", cpn_no);
		
		return ViewPath.ADMINCOUPON + "insertUserForm.jsp";
	}
	
	//�������� ���� ����
	@RequestMapping("/admin/coupon/insertUser")
	public String insertUser(HttpServletRequest request) {
		String msg = null;
		String url = null;
		int result = 0;
		
		int cpn_no = Integer.parseInt(request.getParameter("cpn_no"));
		
		//üũ�� 'sendList'(��� ��ȣ)�� �迭�� �޾ƿ�
		String [] arr = request.getParameterValues("sendList");
		
		//String  Ÿ������ �޾ƿ� �迭�� int�� �迭�� �ٲ��ֱ� ���� arr�� ���� ��ŭ�� int�� �迭 ����
		int[] intArr = new int[arr.length];
		
		CouponVO vo =new CouponVO();
		
		// arr�� ���̸�ŭ(üũ�� ����� ����ŭ) ���� �ø��� ��ȣ�� �����Ͽ� insert �ݺ�
		 for(int i =0; i< arr.length; i++) {
			 
			 intArr[i] = Integer.parseInt(arr[i]);
		//----------------------------------------------------------------------------
			 //�ø��� ��ȣ���� �������� ����� ���� �迭����
			 char[] charList =
				    {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
				     'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
				     'W','X','Y','Z'};
				
				int charCnt = charList.length;
				
				//�����޼ҵ�
				Random rnd = new Random();
				
				//j�� ����(������ �ø����ȣ�� �ڸ���) ��ŭ char[]���� ������ ���ڿ��� �̾ƿ� StringBuffer�� ���� ���ʴ�� 'serial'�� �߰�����
				StringBuffer buf = new StringBuffer();
				
				//0-11�� ������ ���߱⿡ 12�ڸ��� �ø��� ��ȣ�� �����ǰ� ��
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
			 msg = "���� ���ۿ� �����Ͽ����ϴ�.";
		 }else {
			 msg = "���� ���ۿ� �����Ͽ����ϴ�. �����ڿ��� ���� �ֽʽÿ�";
		 }
		 url = "/team/admin/coupon/couponList";

		 request.setAttribute("url", url);
		 request.setAttribute("msg", msg);
		 
		return ViewPath.ADMINCOUPON + "result.jsp";
	}
	
}
