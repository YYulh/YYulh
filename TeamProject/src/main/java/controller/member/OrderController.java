package controller.member;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ViewPath;
import service.member.OrderService;
import vo.CouponVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	@RequestMapping("/member/order/cart")
	public String cart(HttpServletRequest request) {
		MemberVO vo = new MemberVO();
		
		Integer mb_no = 0;
		try {
			vo = (MemberVO) request.getSession().getAttribute("login");	
			 mb_no =vo.getMb_no();
		} catch (NullPointerException e) {
			e.printStackTrace();
			String msg = "로그인이 필요한 서비스 입니다. 로그인 페이지로 이동합니다.";
			String url = "/team/member/login/loginForm";
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.LOGIN + "result.jsp";
		}
	
		/* 비회원 장바구니 쿠키로 구현..? 여기서? 아님 새로 만들기..?
		if(vo == null) {
			return ViewPath.ORDER + "cart.jsp";
		}
		
		System.out.println(vo + "이거?");
		*/
		
		
//		int prod_no = Integer.parseInt(request.getParameter("prod_no"));

		if(mb_no < 1) {
			return "redirect:/member/login/loginForm";
		}
		
	
		vo = orderService.selectOne(mb_no);
		int cr_cnt = orderService.getCouponCount(mb_no);
		
		if(cr_cnt < 0) {
			return "redirect:/member/login/loginForm";
		}	
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderVO> list = orderService.listCart(mb_no);	
		
		int sumCart = orderService.sumCart(mb_no);
		
		int fee = sumCart >= 50000 ? 0 : 2500;	
		
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumCart", sumCart);
		map.put("fee", fee);
		map.put("total", sumCart + fee);
	

		request.setAttribute("vo",vo);
		request.setAttribute("cr_cnt", cr_cnt);
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		request.setAttribute("fee", fee);
		request.setAttribute("sumCart", sumCart);
		request.setAttribute("total", sumCart + fee);
		request.setAttribute("map", map);
		
//		System.out.println(list);
//		System.out.println(sumCart);
//		System.out.println(fee);
//		System.out.println(map);
//		
		   
		return ViewPath.ORDER + "cart.jsp";
	}
	
	@RequestMapping("/member/order/cart/insert")
	@ResponseBody
	public String insert(HttpServletRequest request) {
		
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		int mb_no = vo.getMb_no();
		
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		String optionName = request.getParameter("optionName");
		int cnt = Integer.parseInt(request.getParameter("cnt"));
	
		OrderVO ord1 = new OrderVO();
		ord1 = orderService.getPo_no(optionName);
		
		int po_no = ord1.getPo_no();
		ord1.setMb_no(mb_no);
		ord1.setProd_no(prod_no);
		ord1.setPo_no(po_no);
		//-----------------------------------------------
		
		if(orderService.checkCart(ord1) != null) {			
			return "이미 동일한 상품(옵션)이 장바구니에 담겨있습니다";
		}
		//-------------------------------------------	
				OrderVO ord = new OrderVO();
				
				ord.setMb_no(mb_no);
				ord.setProd_no(prod_no);
				ord.setPo_no(po_no);
				ord.setCart_cnt(cnt);

				int su = orderService.insertCart(ord);
				
				if(su !=0) {
					return "성공";
				}else {
					return "실패";
				}
			}
	
	
	@RequestMapping("/member/order/cart/delete")
	public String cartDelete(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		
		
		String[] arr = request.getParameterValues("delList");
		
		System.out.println(arr);
		
		for(int i =0; i <arr.length; i++) {
			
			System.out.println(arr[i]);
			String no = arr[i];			
			su = orderService.deleteCart(no);
			result += su;
		}
		
		
		String msg = null;
		String url = null;
		
		
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		
		url = "/team/member/order/cart";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
	
		
		return ViewPath.ORDER + "result.jsp";
	}
	
	@RequestMapping("/member/order/cart/deleteAll")
	public String cartDeleteAll(HttpServletRequest request) {
		
		MemberVO vo = new MemberVO();
		
		
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no =vo.getMb_no();
		
		int su = orderService.deleteCartAll(mb_no);
		
		
		String msg = null;
		String url = null;
		
		
		
		if(su != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		
		url = "/team/member/order/cart";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
	
		
		return ViewPath.ORDER + "result.jsp";
	}
	
	@RequestMapping("member/order/cart/update")
	public String cartUpdate(HttpServletRequest request) {
	

	
		String[] arr =request.getParameterValues("cart_cnt");
	
		for(int i = 0; i <arr.length; i++) {
			System.out.println(arr[i]);
			
		}
		
		int mb_no = Integer.parseInt(request.getParameter("mb_no").trim());
		int prod_no = Integer.parseInt(request.getParameter("prod_no").trim());
		int cart_cnt = Integer.parseInt(request.getParameter("cart_cnt").trim());
		
		System.out.println(mb_no);
		System.out.println(prod_no);
		System.out.println(cart_cnt);
		
		int su = orderService.updateCart(mb_no,prod_no,cart_cnt);
		
		String msg = null;
		String url = null;
		
		if(su == 0) {
			msg = "제품 수정에 실패하였습니다.";
		} else {
			msg = "제품이 수정에 성공하였습니다.";
		}
		url = "/team/member/order/cart";
		
		
		request.setAttribute("msg",msg);
		request.setAttribute("url", url);
		
	

		
		return ViewPath.ORDER + "result.jsp";
	}
	
	@RequestMapping("/member/order/orderAll")
	public String orderAll(HttpServletRequest request) {
		
		MemberVO vo = new MemberVO();
		Integer mb_no=0;
		try {
			vo = (MemberVO) request.getSession().getAttribute("login");	
			 mb_no =vo.getMb_no();
		} catch (NullPointerException e) {
			e.printStackTrace();
			String msg = "로그인이 필요한 서비스 입니다. 로그인 페이지로 이동합니다.";
			String url = "/team/member/login/loginForm";
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.LOGIN + "result.jsp";
		}
		
		
		
		if(mb_no < 1) {
			return "redirect:/member/login/loginForm";
		}
		
		vo = orderService.selectOne(mb_no);
		int cr_cnt = orderService.getCouponCount(mb_no);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderVO> list = orderService.listCart(mb_no);	
		
		int sumCart = orderService.sumCart(mb_no);
		
		int fee = sumCart >= 50000 ? 0 : 2500;	
		
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumCart", sumCart);
		map.put("fee", fee);
		map.put("total", sumCart + fee);
		

		request.setAttribute("vo",vo);
		request.setAttribute("cr_cnt", cr_cnt);
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		request.setAttribute("fee", fee);
		request.setAttribute("sumCart", sumCart);
		request.setAttribute("total", sumCart + fee);
		request.setAttribute("map", map);
		
		
		return ViewPath.ORDER + "orderForm.jsp";
	}
	
	
	@RequestMapping("/member/order/delete")
	public String orderDelete(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		
		
		String[] arr = request.getParameterValues("delList");
		
		System.out.println(arr);
		
		for(int i =0; i <arr.length; i++) {
			
			System.out.println(arr[i]);
			String no = arr[i];			
			su = orderService.deleteCart(no);
			result += su;
		}
		
		
		String msg = null;
		String url = null;
		
		
		
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		
		
		url = "/team/member/order/orderAll";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
	
		
		return ViewPath.ORDER + "result.jsp";
	}
	
	
	@RequestMapping("/member/order/insertOrder")
	public String insertOrder(HttpServletRequest request) {


			 String msg = null;
			 String url = null;
			 
			 MemberVO vo = new MemberVO();
			 vo = (MemberVO) request.getSession().getAttribute("login");
				
			 int mb_no = vo.getMb_no();

			 
			 int prod_no = Integer.parseInt(request.getParameter("prod_no").trim());
			 int grade_no = Integer.parseInt(request.getParameter("grade_no").trim());
//			 int py_no = 61; // 처리상태 //현재 test중인 db에 상품준비중 시퀀스가 61로 들어가있음..)
			 String ord_receiver = request.getParameter("ord_receiver");
			 String ord_tel1 = request.getParameter("ord_tel1");
			 String ord_tel2 = request.getParameter("ord_tel2");
			 String ord_tel3 = request.getParameter("ord_tel3");
			 String ord_addr = request.getParameter("ord_addr");
			 String ord_post= request.getParameter("ord_post");
			 int ord_usepoint = Integer.parseInt(request.getParameter("ord_usepoint").trim());
			 int ord_price = Integer.parseInt(request.getParameter("ord_price").trim());
			 int ord_cnt = Integer.parseInt(request.getParameter("ord_cnt").trim());
			 String ord_msg = request.getParameter("ord_msg");
			 int ord_fee = Integer.parseInt(request.getParameter("ord_fee").trim());	 
			 String cpn_no = request.getParameter("cpn_no");
			 String shipaddr_no = request.getParameter("shipaddr_no");
			 
			 String pay_acc = request.getParameter("pay_acc");
			 String pay_bnk = request.getParameter("pay_bnk");
			 String pay_method = request.getParameter("pay_method");
			 
			 String cr_no = request.getParameter("cr_no");
			
			 
			 OrderVO ord = new OrderVO();
			 
			 ord.setMb_no(mb_no);
			 ord.setProd_no(prod_no);
			 ord.setGrade_no(grade_no);
//			 ord.setPy_no(py_no);
			 ord.setCpn_no_str(cpn_no);
			 ord.setShipaddr_no_str(shipaddr_no);
			 ord.setOrd_receiver(ord_receiver);
			 ord.setOrd_tel1(ord_tel1);
			 ord.setOrd_tel2(ord_tel2);
			 ord.setOrd_tel3(ord_tel3);
			 ord.setOrd_addr(ord_addr);
			 ord.setOrd_post(ord_post);
			 ord.setOrd_usepoint(ord_usepoint);
			 ord.setOrd_price(ord_price);
//			 ord.setOrd_price(ord_price);
			 ord.setOrd_cnt(ord_cnt);
			 ord.setOrd_msg(ord_msg);
			 ord.setOrd_fee(ord_fee);
			 
			 ord.setCr_no_str(cr_no);

			 
			int su = orderService.insertOrder(ord);

			ord.setPay_acc(pay_acc);
			ord.setPay_bnk(pay_bnk);
			ord.setPay_method(pay_method);
			
			orderService.insertPay(ord);
			
			int ord_no = ord.getOrd_no();
			
			//장바구니 비우기
			int check = orderService.deleteCartAfterOrder(mb_no, prod_no);
			
			int couponUsed = orderService.updateCouponused(mb_no, cr_no);
			
//			int pointUsed = orderService.updatePoint(mb_no, ord_usepoint);
			
			
			int usedPoint = orderService.updateUsedPoint(mb_no, ord_no);
		
			
			if(su !=0) {
				msg = "주문이 성공적으로 완료되었습니다.";
				
				int mb_point = (int) (ord_price * 0.01);
				orderService.updateMbPoint(mb_point, mb_no);
			}else {
				msg = "주문에 실패하였습니다.";
			}
			
			url = "/team/member/order/orderResult"; 
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			

		
		return ViewPath.ORDER + "result.jsp";
	}
	
	@RequestMapping("/member/order/orderResult")
	public String OrderResult(HttpServletRequest request, Model model) {
		
		MemberVO vo = new MemberVO();
		
		
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();

		
		OrderVO ord = new OrderVO();
		
	
		vo = orderService.selectOne(mb_no);
		Integer ord_no = ord.getOrd_no();
		

		
		Integer od_no = ord.getOrd_no();
		
		
		ord = orderService.orderResult(ord_no);
		int cr_cnt = orderService.getCouponCount(mb_no);
		
		request.setAttribute("vo", vo);
		model.addAttribute("ord", ord);
		request.setAttribute("cr_cnt", cr_cnt);
		
		return ViewPath.ORDER + "orderResult.jsp";
	}
	
	@RequestMapping("/member/order/orderCouponList")
	public String orderCouponList(Model model,HttpServletRequest request, Integer start) {
		MemberVO vo = new MemberVO();
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		


		if(start == null) {
			start = 1;
		}

		List<CouponVO> list = orderService.getCouponList(mb_no, start);

	
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		

		int nowPage = (start - 1) / 5 + 1;
		int total = orderService.couponTotal(mb_no);
		int totalPage = total % 5 == 0 ? total / 5 : total / 5 + 1;

		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);

		
		return ViewPath.ORDER + "orderCouponList.jsp";
	}
	
	@RequestMapping("/member/order/orderShipAddr")
	public String orderShipAddr(Model model, HttpServletRequest request, Integer start) {
		
		
		MemberVO vo = new MemberVO();
		
		vo = (MemberVO) request.getSession().getAttribute("login");
		
		Integer mb_no = vo.getMb_no();
		
		
	
		
		if(start == null) {
			start = 1;
		}
		
		List<MemberVO> list = orderService.shippingAddr(mb_no, start);

		
		
		if(mb_no <1) {
			return "redirect:/member/login/loginForm";
		}
		
		int nowPage = (start - 1) / 50 + 1;
	
		int total = orderService.getTotal();
		int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);
		
		
		return ViewPath.ORDER + "orderShipAddr.jsp";
	}
	
}
