package controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ViewPath;
import service.admin.AdStockService;
import vo.ProductVO;
import vo.StockVO;

	
	@Controller
	public class AdStockController {

		private AdStockService stockService;
		
		public AdStockController(AdStockService stockService) { 
			this.stockService = stockService; 	
		}
		
		@RequestMapping("/admin/stock/stockList")
		public String list(Model model,Integer start) {
						
			if(start == null) {
				start = 1;
			}
			
			List<StockVO> list = stockService.selectList(start);
			
			//���� ������
			int nowPage = (start - 1) / 50 + 1;
			//�� ���� ����
			int total = stockService.getTotal();
			int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
			
			model.addAttribute("start", start);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("list", list);
			
			return ViewPath.ADMINSTOCK + "stockList.jsp";
		}
		
		
		
		@RequestMapping("/admin/stock/insertForm")
		public String insertForm(HttpServletRequest request) {
			int no = Integer.parseInt(request.getParameter("no"));

			StockVO vo = new StockVO();
			
			//stock_no �� �ҷ��� stock������ select
			vo = stockService.stockDetail(no);	
			
			if(vo == null) {
				return "redirect:/admin/stock/stockList";
			}
			
			request.setAttribute("vo", vo);
			
			return ViewPath.ADMINSTOCK + "insertStock.jsp";
		}
		
		@RequestMapping("/admin/stock/insert")
		public String insert(HttpServletRequest request,StockVO vo) {

			
			String msg =null;
			String url =null;
			
			//vo�� ��������� ���ο� stock insert
			int su = stockService.insertStock(vo);
			if(su ==0) {
				msg = "����Է¿� �����Ͽ����ϴ�..";
			}else {
				msg = "����Է¿� �����Ͽ����ϴ�.";
			}
			url = "/team/admin/stock/stockList";
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.ADMINSTOCK + "result.jsp";
		}
		
		@RequestMapping("/admin/stock/deleteStock")
		public String deleteStock(HttpServletRequest request) {
			
			int su = 0;
			int result =0;
			String msg = null;
			String url = null;
			
			//������ stock list(üũ��) stock_no �� ������ �迭�� ����
			String[] arr = request.getParameterValues("delList");
			
			//����� �迭��ŭ ���� �ݺ�
			for(int i =0; i <arr.length; i++) {
			
				//arr�迭�� i��° ���� stock_no�� ������ ��ü ����
				String no = arr[i];			
				su = stockService.deleteStock(no);
				result += su;
				
			}
			if(result == arr.length) {
				msg = "�����Ͻ� �׸��� �����Ǿ����ϴ�.";
			}else {
				msg = "�����Ͻ� �׸� ������ �����Ͽ����ϴ�.";
			}
			 url = "/team/admin/stock/stockList";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			return ViewPath.ADMINSTOCK + "result.jsp";		
		}
}
