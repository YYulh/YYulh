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

			//현재 페이지
			int nowPage = (start - 1) / 50 + 1;
			//총 글의 개수
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
			
			//제품 소분류 카테고리 목록을 체크박스로 표현해주기 위해 List로 받아옴
			List<ProductVO> psc1 = productService.selectSmallCate1();
			List<ProductVO> psc2 = productService.selectSmallCate2();		
			
			//추천 상품 목록을 하단 체크 박스에 표현 해주되 원래는 하나면 충분하지만 css적인 목적을 위해 임시로 10개씩 표현(3개 리스트로 총 30개 까지 리턴받게함)
			List<ProductVO> prod1 = productService.selectProduct1();
			List<ProductVO> prod2 = productService.selectProduct2();
			List<ProductVO> prod3 = productService.selectProduct3();
		
			//model 영역에 저장
			model.addAttribute("psc1", psc1);
			model.addAttribute("psc2", psc2);		
			model.addAttribute("prod1", prod1);	
			model.addAttribute("prod2", prod2);
			model.addAttribute("prod3", prod3);
			return ViewPath.ADMINPRODUCT + "insertProduct.jsp";
		}
		
	
		@RequestMapping("/admin/product/insert")		//@RequestParam으 통해 photo 파라미터들을 MultipartFile타입 List형태의 list라는 이름으로 불러옴
		public String insert(HttpServletRequest request,ProductVO vo,@RequestParam("photo") List<MultipartFile> list) {
		
			String msg = null;
			String url = null;	
			
			//체크된 psc(소분류 카테고리) 들을 box라는 문자형 배열에 저장 ex) [all,best,memory]
			String[] box = request.getParameterValues("psc");
			
			//join을 통해 ,로 구분지어 cate 라는 문자열에 저장 ex) 'all,best,memory'
			String cate = String.join(",", box);
			
			
			vo.setCd_list(cate);
			//categorieDe table에 저장 (제품의 fk키로 묶인 카테고리(psc_no)는 not null이므로 psc_no을 통해 엮어 불러올 수 있음)
			productService.insertCate(vo);

			//체크된 more(추천 상품목록) 들을 box2 라는 문자형 배열에 저장 ex) [포맨트 시그니처 퍼퓸, 포맨트 핸드크림, 포맨트 바디워시]
			String[] box2 = request.getParameterValues("more");
			
			//join을 통해 ,로 구분지어 more 라는 문자열에 저장 ex) '포맨트 시그니처 퍼퓸, 포맨트 핸드크림, 포맨트 바디워시'
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
			vo.setProd_content(content.replaceAll("\r\n", "<br>")); //script요소를 html요소로 변환(줄바꿈)
			vo.setProd_more(more);
			
			//제품 등록
			int su = productService.insertProduct(vo);
			
			if(su ==0) {
				msg = "제품 등록에 실패하였습니다.";
			} else {
				msg = "제품이 등록되었습니다.";
			}
			
			//order by와 rownum을 통해 product 테이블에서 마지막으로 insert된(방금 insert된) 제품의 seq값(prod_no) 을 가져옴
			int seq = productService.getSeq(); 
			//제품이 등록됨과 동시에 재고관리 테이블에도 제품정보가 등록되어야 하기 때문에 seq를 통해 insert
			productService.insertStock(seq); 
					
			// 위에서 가져온 seq 값으로 그 제품정보 들을 가져와서 vo에 저장 
			vo = productService.getLastProduct(seq);
			
			//제품 기본옵션 ex)단품 , 49000원 과 같은 정보도 기본적으로 저장되어야 하므로 vo를 통해 기본옵션 insert
			productService.insertBasicOption(vo);
			
			//파일을 저장할 경로	
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
			String filename = null;		
			
			//다중 업로드를 위해 MultipartFile로 받아온 파일 수 만큼 저장 반복
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);	
			//업로드된 실제파일명	
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //가져온 사진이 있으면
				
			//File 객체 생성
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //경로에 파일이 없으면
				saveFile.mkdirs();
				}else { //있으면					
			long time = System.currentTimeMillis(); //단지 이름 바꿔주는 방식, 다르게 바꿔줘도 됨.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//업로드된 파일은 MultipartResolver라는 클래스가 지정한 임시저장소에 저장되어 있다...
				//파일이 일정시간이 지나면 사라지기때문에 내가 지정한 경로로 복사해준다...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // 타임밀리언즈라 왠만하면 이름이 안겹치겠지만 호옥시라도 겹쳐서 오류나면 해당 파일 삭제!					
				} catch (IOException e) {
					e.printStackTrace();
				}	
				//등록된 파일이 없으면 null 대신 빈 문자열이 대신 저장될 수 있도록
				}else {
					filename = " ";
				}
				
				vo.setProd_no(seq);
				vo.setPp_filename(filename);
				vo.setPp_path(savePath);
				
				//제품 사진관리 테이블에 저장
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
				msg = "제품 수정에 실패하였습니다.";
			} else {
				msg = "제품이 수정에 성공하였습니다.";
			}
			
			//-------------------------------------------------------
			//파일을 저장할 경로
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
			String filename = null;		
			
			//업로드된 실제파일명
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);	
				
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //가져온 사진이 있으면
				
			//File 객체 생성
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //경로에 파일이 없으면
				saveFile.mkdirs();
				}else { //있으면					
			long time = System.currentTimeMillis(); //단지 이름 바꿔주는 방식, 다르게 바꿔줘도 됨.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//업로드된 파일은 MultipartResolver라는 클래스가 지정한 임시저장소에 저장되어 있다...
				//파일이 일정시간이 지나면 사라지기때문에 내가 지정한 경로로 복사해준다...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // 타임밀리언즈라 왠만하면 이름이 안겹치겠지만 호옥시라도 겹쳐서 오류나면 해당 파일 삭제!					
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
			
			//삭제할 stock list(체크된) prod_no 를 문자형 배열에 저장
			String[] arr = request.getParameterValues("delList");
			
			//저장된 배열만큼 삭제 반복
			for(int i =0; i <arr.length; i++) {
				
				//arr배열의 i번째 값을 prod_no로 가지는 객체 삭제
				String no = arr[i];			
				su = productService.deleteProduct(no);
				result += su;
			}
			
			String msg = null;
			String url = null;
			
				
			if(result == arr.length) {
				msg = "선택하신 항목이 삭제되었습니다.";
			}else {
				msg = "선택하신 항목 삭제에 실패하였습니다.";
			}
			
			url = "/team/admin/product/productList";
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		
		@RequestMapping("/admin/product/optionForm")
		public String optionForm(HttpServletRequest request) {
		
			//해당 제품의 prod_no 을 no에 저장
			int no = Integer.parseInt(request.getParameter("no"));
			
			ProductVO vo = new ProductVO();
			
			vo = productService.selectOne(no);
			if(vo==null) {
				return "redirect:/team/admin/product/productList";
			}
			request.setAttribute("vo", vo);
			
			//해당 제품의 옵션들을 List 로 po에 저장
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
			
			//vo에 저장된 정보들로 새로운 제품옵션 insert
			int su = productService.insertOption(vo);
			if(su != 0) {
				msg = "제품 옵션 등록에 성공하였습니다";
			}else {
				msg = "제품 옵션 등록에 실패하였습니다. 관리자에게 문의 바랍니다...";
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
				msg = "선택하신 항목이 삭제되었습니다.";
			}else {
				msg = "선택하신 항목 삭제에 실패하였습니다.";
			}
			
			url = "/team/admin/product/productList";
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.ADMINPRODUCT + "result.jsp";
		}
		
		
		
}
