package controller.admin;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import common.ViewPath;
import service.admin.AdProductService;
import vo.ProductVO;

	@Controller
	public class AdProductController {

		private AdProductService productService;
		
		public AdProductController(AdProductService productService) { 
			this.productService = productService; 	
		}
		
		@RequestMapping("/admin/product/productList")
		public String list(Model model,Integer start) {
						
			if(start == null) {
				start = 1;
			}
			
			List<ProductVO> list = productService.selectList(start);			

			//���� ������
			int nowPage = (start - 1) / 50 + 1;
			//�� ���� ����
			int total = productService.getTotal();
			int totalPage = total % 50 == 0 ? total / 50 : total / 50 + 1;
			
			model.addAttribute("start", start);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("list", list);
			
			return ViewPath.ADMINPRODUCT + "productList.jsp";
		}
		
		@RequestMapping("/admin/product/insertForm")
		public String insertForm(Model model) {
			
			//��ǰ �Һз� ī�װ� ����� üũ�ڽ��� ǥ�����ֱ� ���� List�� �޾ƿ�
			List<ProductVO> psc1 = productService.selectSmallCate1();
			List<ProductVO> psc2 = productService.selectSmallCate2();		
			
			//��õ ��ǰ ����� �ϴ� üũ �ڽ��� ǥ�� ���ֵ� ������ �ϳ��� ��������� css���� ������ ���� �ӽ÷� 10���� ǥ��(3�� ����Ʈ�� �� 30�� ���� ���Ϲް���)
			List<ProductVO> prod1 = productService.selectProduct1();
			List<ProductVO> prod2 = productService.selectProduct2();
			List<ProductVO> prod3 = productService.selectProduct3();
		
			//model ������ ����
			model.addAttribute("psc1", psc1);
			model.addAttribute("psc2", psc2);		
			model.addAttribute("prod1", prod1);	
			model.addAttribute("prod2", prod2);
			model.addAttribute("prod3", prod3);
			return ViewPath.ADMINPRODUCT + "insertProduct.jsp";
		}
		
	
		@RequestMapping("/admin/product/insert")		//@RequestParam�� ���� photo �Ķ���͵��� MultipartFileŸ�� List������ list��� �̸����� �ҷ���
		public String insert(HttpServletRequest request,ProductVO vo,@RequestParam("photo") List<MultipartFile> list) {
		
			String msg = null;
			String url = null;	
			
			//üũ�� psc(�Һз� ī�װ�) ���� box��� ������ �迭�� ���� ex) [all,best,memory]
			String[] box = request.getParameterValues("psc");
			
			//join�� ���� ,�� �������� cate ��� ���ڿ��� ���� ex) 'all,best,memory'
			String cate = String.join(",", box);
			
			
			vo.setCd_list(cate);
			//categorieDe table�� ���� (��ǰ�� fkŰ�� ���� ī�װ�(psc_no)�� not null�̹Ƿ� psc_no�� ���� ���� �ҷ��� �� ����)
			productService.insertCate(vo);

			//üũ�� more(��õ ��ǰ���) ���� box2 ��� ������ �迭�� ���� ex) [����Ʈ �ñ״�ó ��Ǿ, ����Ʈ �ڵ�ũ��, ����Ʈ �ٵ����]
			String[] box2 = request.getParameterValues("more");
			
			//join�� ���� ,�� �������� more ��� ���ڿ��� ���� ex) '����Ʈ �ñ״�ó ��Ǿ, ����Ʈ �ڵ�ũ��, ����Ʈ �ٵ����'
			String more = String.join(",", box2);
		
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			int disc = Integer.parseInt(request.getParameter("disc"));
			int sellprice = Integer.parseInt(request.getParameter("sellprice"));
			String content = request.getParameter("content");
			
			vo.setProd_name(name);
			vo.setProd_price(price);
			vo.setProd_disc(disc);
			vo.setProd_sellprice(sellprice);
			vo.setProd_content(content.replaceAll("\r\n", "<br>")); //script��Ҹ� html��ҷ� ��ȯ(�ٹٲ�)
			vo.setProd_more(more);
			
			//��ǰ ���
			int su = productService.insertProduct(vo);
			
			if(su ==0) {
				msg = "��ǰ ��Ͽ� �����Ͽ����ϴ�.";
			} else {
				msg = "��ǰ�� ��ϵǾ����ϴ�.";
			}
			
			//order by�� rownum�� ���� product ���̺��� ���������� insert��(��� insert��) ��ǰ�� seq��(prod_no) �� ������
			int seq = productService.getSeq(); 
			//��ǰ�� ��ϵʰ� ���ÿ� ������ ���̺��� ��ǰ������ ��ϵǾ�� �ϱ� ������ seq�� ���� insert
			productService.insertStock(seq); 
					
			// ������ ������ seq ������ �� ��ǰ���� ���� �����ͼ� vo�� ���� 
			vo = productService.getLastProduct(seq);
			
			//��ǰ �⺻�ɼ� ex)��ǰ , 49000�� �� ���� ������ �⺻������ ����Ǿ�� �ϹǷ� vo�� ���� �⺻�ɼ� insert
			productService.insertBasicOption(vo);
			
			//������ ������ ���	
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
			String filename = null;		
			
			//���� ���ε带 ���� MultipartFile�� �޾ƿ� ���� �� ��ŭ ���� �ݺ�
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);	
			//���ε�� �������ϸ�	
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //������ ������ ������
				
			//File ��ü ����
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //��ο� ������ ������
				saveFile.mkdirs();
				}else { //������					
			long time = System.currentTimeMillis(); //���� �̸� �ٲ��ִ� ���, �ٸ��� �ٲ��൵ ��.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//���ε�� ������ MultipartResolver��� Ŭ������ ������ �ӽ�����ҿ� ����Ǿ� �ִ�...
				//������ �����ð��� ������ ������⶧���� ���� ������ ��η� �������ش�...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // Ÿ�ӹи������ �ظ��ϸ� �̸��� �Ȱ�ġ������ ȣ���ö� ���ļ� �������� �ش� ���� ����!					
				} catch (IOException e) {
					e.printStackTrace();
				}	
				//��ϵ� ������ ������ null ��� �� ���ڿ��� ��� ����� �� �ֵ���
				}else {
					filename = " ";
				}
				
				vo.setProd_no(seq);
				vo.setPp_filename(filename);
				vo.setPp_path(savePath);
				
				//��ǰ �������� ���̺� ����
				productService.insertPic(vo);
			}
			
			url = "/team/admin/product/insertForm";
			request.setAttribute("vo", vo);
			request.setAttribute("msg",msg);
			request.setAttribute("url", url);
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		
		@RequestMapping("/admin/product/updateForm")
		public String updateForm(HttpServletRequest request) {
			
			int prod_no = Integer.parseInt(request.getParameter("prod_no"));
			
			ProductVO vo = new ProductVO();
			
			vo = productService.selectOne(prod_no);
			
			String cateList = productService.cdList(prod_no);
			String moreList = productService.moreList(prod_no);
			
			List<ProductVO> po = productService.selectPic(prod_no);
	
			List<ProductVO> psc1 = productService.selectSmallCate1();
			List<ProductVO> psc2 = productService.selectSmallCate2();
			List<ProductVO> prod1 = productService.selectProduct1();
			List<ProductVO> prod2 = productService.selectProduct2();
			List<ProductVO> prod3 = productService.selectProduct3();
			
			request.setAttribute("moreList", moreList);
			request.setAttribute("cateList", cateList);
			request.setAttribute("prod1", prod1);
			request.setAttribute("prod2", prod2);
			request.setAttribute("prod3", prod3);
			request.setAttribute("psc1", psc1);
			request.setAttribute("psc2", psc2);
			request.setAttribute("prod_no",prod_no);
			request.setAttribute("vo", vo);			
			request.setAttribute("po", po);
			
			return ViewPath.ADMINPRODUCT + "updateForm.jsp";
		}
		
		@RequestMapping("/admin/product/update")
		public String update(HttpServletRequest request,ProductVO vo,@RequestParam("photo") List<MultipartFile> list){

			String[] box = request.getParameterValues("psc_name");
			
			String cate = String.join(",", box);

			vo.setCd_list(cate);
			productService.updateCate(vo);
			//-------------------------------------------
			String[] box2 = request.getParameterValues("prod_more");
			
			String more = String.join(",", box2);

			String msg = null;
			String url = null;
			
			
			 int su = productService.updateProduct(vo);
			
			if(su ==0) {
				msg = "��ǰ ������ �����Ͽ����ϴ�.";
			} else {
				msg = "��ǰ�� ������ �����Ͽ����ϴ�.";
			}
			
			//-------------------------------------------------------
			//������ ������ ���
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
			String filename = null;		
			
			//���ε�� �������ϸ�
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);	
				
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //������ ������ ������
				
			//File ��ü ����
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //��ο� ������ ������
				saveFile.mkdirs();
				}else { //������					
			long time = System.currentTimeMillis(); //���� �̸� �ٲ��ִ� ���, �ٸ��� �ٲ��൵ ��.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//���ε�� ������ MultipartResolver��� Ŭ������ ������ �ӽ�����ҿ� ����Ǿ� �ִ�...
				//������ �����ð��� ������ ������⶧���� ���� ������ ��η� �������ش�...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // Ÿ�ӹи������ �ظ��ϸ� �̸��� �Ȱ�ġ������ ȣ���ö� ���ļ� �������� �ش� ���� ����!					
				} catch (IOException e) {
					e.printStackTrace();
				}				
				}else {
					filename = " ";
				}

				vo.setPp_filename(filename);
				vo.setPp_path(savePath);
				
				productService.insertPic(vo);
			}
			//-------------------------------------------
			url = "/team/admin/product/productList";
			
			
			request.setAttribute("msg",msg);
			request.setAttribute("url", url);
			
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		@RequestMapping("admin/product/delete")
		public String delete(HttpServletRequest request) {
			int su = 0;
			int result =0;
			
			//������ stock list(üũ��) prod_no �� ������ �迭�� ����
			String[] arr = request.getParameterValues("delList");
			
			//����� �迭��ŭ ���� �ݺ�
			for(int i =0; i <arr.length; i++) {
				
				//arr�迭�� i��° ���� prod_no�� ������ ��ü ����
				String no = arr[i];			
				su = productService.deleteProduct(no);
				result += su;
			}
			
			String msg = null;
			String url = null;
			
				
			if(result == arr.length) {
				msg = "�����Ͻ� �׸��� �����Ǿ����ϴ�.";
			}else {
				msg = "�����Ͻ� �׸� ������ �����Ͽ����ϴ�.";
			}
			
			url = "/team/admin/product/productList";
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		
		@RequestMapping("/admin/product/optionForm")
		public String optionForm(HttpServletRequest request) {
		
			//�ش� ��ǰ�� prod_no �� no�� ����
			int no = Integer.parseInt(request.getParameter("no"));
			
			ProductVO vo = new ProductVO();
			
			vo = productService.selectOne(no);
			if(vo==null) {
				return "redirect:/team/admin/product/productList";
			}
			request.setAttribute("vo", vo);
			
			//�ش� ��ǰ�� �ɼǵ��� List �� po�� ����
			List<ProductVO> po = productService.selectOptionList(no);
			
			if(po==null) {
				return "redirect:/team/admin/product/productList";
			}
			request.setAttribute("po", po);
			
			return ViewPath.ADMINPRODUCT + "optionForm.jsp";
		}
		
		@RequestMapping("/admin/product/insertOption")
		public String insertOption(HttpServletRequest request,ProductVO vo) {
			
			String msg = null;
			String url = null;
			
			//vo�� ����� ������� ���ο� ��ǰ�ɼ� insert
			int su = productService.insertOption(vo);
			if(su != 0) {
				msg = "��ǰ �ɼ� ��Ͽ� �����Ͽ����ϴ�";
			}else {
				msg = "��ǰ �ɼ� ��Ͽ� �����Ͽ����ϴ�. �����ڿ��� ���� �ٶ��ϴ�...";
			}
			url = "/team/admin/product/productList";
		
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return ViewPath.ADMINPRODUCT + "result.jsp";			
		}
		
		@RequestMapping("/admin/product/deleteOption")
		public String deleteOption(HttpServletRequest request) {
			
			String msg = null;
			String url = null;
			int su = 0;
			int result =0;
			
			String[] arr = request.getParameterValues("delList");
			
			for(int i =0; i <arr.length; i++) {
				
				String no = arr[i];			
				su = productService.deleteOption(no);
				result += su;
			}
			
			if(result == arr.length) {
				msg = "�����Ͻ� �׸��� �����Ǿ����ϴ�.";
			}else {
				msg = "�����Ͻ� �׸� ������ �����Ͽ����ϴ�.";
			}
			
			url = "/team/admin/product/productList";
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		
		
		
}
