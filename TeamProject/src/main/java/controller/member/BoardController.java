package controller.member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.ViewPath;
import service.member.BoardService;
import vo.BoardVO;
import vo.ManagementVO;
import vo.MemberVO;
import vo.OrderVO;

@Controller
public class BoardController {

	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/member/board/faqForm")
	public String faqForm(Model model,Integer start) {
		
		
		if(start == null) {
			start = 1;
		}
		
		List<BoardVO> list = boardService.selectFaqList(start);			

		//현재 페이지
		int nowPage = (start - 1) / 15 + 1;
		//총 글의 개수
		int total = boardService.getTotal();
		int totalPage = total % 15 == 0 ? total / 15 : total / 15 + 1;
		
		model.addAttribute("start", start);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", list);

		return ViewPath.BOARD + "faqForm.jsp";
	}
	
	@RequestMapping("/member/board/faqWrite")
	public String faqWrite(HttpServletRequest request) {	
		int no = Integer.parseInt(request.getParameter("board_no"));
		
		List<BoardVO> list = boardService.boardOption(no);
		
		request.setAttribute("list", list);
		return ViewPath.BOARD + "faqWriteForm.jsp";
	}
	
	
	@RequestMapping("/member/board/write")
	public String write(HttpServletRequest request,@RequestParam("photo") List<MultipartFile> list) {
		
		
		String msg = null;
		String url = null;
		//---------------------------------------------

		int bo_no = Integer.parseInt(request.getParameter("boardOption"));
	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		
		vo.setFaq_title(title);
		vo.setBo_no(bo_no);
		vo.setFaq_content(content.replaceAll("\r\n", "<br>"));
		
		int su = boardService.insertFaq(vo);
		if(su != 0) {
			msg = "글 작성에 성공하였습니다.";
		}else {
			msg = "글 작성이 실패하였습니다.";
		}
		//-----------------------------------------------------------
		int seq = boardService.getSeq();  //방금 insert 해준 글의 seq 가져오기
	
		//-----------------------------------------------------------
		//파일을 저장할 경로
		
				String savePath = request.getSession().getServletContext().getRealPath("/resources/board/");
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
					
					vo.setFaq_no(seq);
					vo.setFp_filename(filename);
					vo.setFp_path(savePath);
					
					boardService.insertPic(vo);
				}			

				
		url = "/team/member/board/faqForm";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return ViewPath.BOARD + "result.jsp";
	}		
	
	@RequestMapping("/member/board/faqContent")
	public String faqContent(HttpServletRequest request) {
		
		int faq_no = Integer.parseInt(request.getParameter("no"));
		
		BoardVO vo = new BoardVO();
		
		vo = boardService.faqContent(faq_no);
		if(vo == null) {
			return "redirect:/team/member/board/faqForm";
		}		
		request.setAttribute("vo", vo);

		//faq_no 로 엮인 사진정보들 저장
		try {
			List<BoardVO> pic = boardService.faqPic(faq_no);	
			request.setAttribute("pic", pic);			
		} catch (MethodNotFoundException e) {
			e.printStackTrace();
		}
		

		//조회수 +1
		int su = boardService.increaseView(faq_no);
		if(su ==0) {
			return "redirect:/team/member/board/faqForm";
		}
		return ViewPath.BOARD + "faqContent.jsp";
	}
	
	
	@RequestMapping("/member/board/faqUpdateForm")
	public String updateForm(HttpServletRequest request) {
			
		int no = Integer.parseInt(request.getParameter("board_no"));
		List<BoardVO> list = boardService.boardOption(no);
		
		request.setAttribute("list", list);

		//선택한 faq의 정보들을 불러와서 저장
		int faq_no = Integer.parseInt(request.getParameter("no"));
		BoardVO vo = new BoardVO();
		
		vo = boardService.faqContent(faq_no);
		if(vo == null) {
			return "redirect:/team/mamber/board/faqForm";
		}		
		request.setAttribute("vo", vo);
		
	//선택한 faq의 사진들을 불러와서 저장	
		
		List<BoardVO> pic = boardService.faqPic(faq_no);
		
		request.setAttribute("pic", pic);		
		
		return ViewPath.BOARD + "faqUpdateForm.jsp";
	}
	@RequestMapping("/member/board/faqUpdate")
	public String faqUpdate(HttpServletRequest request,@RequestParam("photo") List<MultipartFile> list) {
		String msg = null;
		String url = null;
		
		int faq_no = Integer.parseInt(request.getParameter("faq_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bo_no = Integer.parseInt(request.getParameter("boardOption"));
		
		BoardVO vo = new BoardVO();
		
		vo.setFaq_no(faq_no);
		vo.setFaq_title(title);
		vo.setBo_no(bo_no);
		vo.setFaq_content(content.replaceAll("\r\n", "<br>"));
				
		int su = boardService.faqUpdate(vo);
		
		if(su != 0) {
			msg = "글 수정에 성공하였습니다.";
		}else {
			msg = "글 수정에 실패하였습니다.";
		}
		//--------------------------------------------------------------------
		//파일을 저장할 경로
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board/");
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
			
			vo.setFaq_no(faq_no);
			vo.setFp_filename(filename);
			vo.setFp_path(savePath);
			
			boardService.insertPic(vo);
		}			
		//------------------------------------------------------------------------------
		url = "/team/member/board/faqContent?no=" +faq_no;
		
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		return ViewPath.BOARD + "result.jsp";
		}
	
		@RequestMapping("/member/board/faqDelete")
		public String faqDelete(HttpServletRequest request) {
			String url =null;
			String msg = null;
			
			int faq_no = Integer.parseInt(request.getParameter("faq_no"));
			
			int su = boardService.faqDelete(faq_no);
		
			if(su != 0) {
				msg = "글 삭제에 성공하였습니다.";
			}else {
				msg = "글 삭제에 실패하였습니다.";
			}
			
			url = "/team/member/board/faqForm";
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.BOARD + "result.jsp";
		}
		
		//--------------------------QNA---------------------------------
		@RequestMapping("/member/board/qnaForm")
		public String qnaForm(Model model,Integer start) {
			
			
			if(start == null) {
				start = 1;
			}
			
			List<BoardVO> list = boardService.selectQnaList(start);			
			//현재 페이지
			int nowPage = (start - 1) / 15 + 1;
			//총 글의 개수
			int total = boardService.getTotalQna();
			int totalPage = total % 15 == 0 ? total / 15 : total / 15 + 1;
			
			model.addAttribute("start", start);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("list", list);
	
			return ViewPath.BOARD + "qnaForm.jsp";
		}
		@RequestMapping("/member/board/qnaWriteForm")
		public String qnaWrite(HttpServletRequest request) {	
			int no = Integer.parseInt(request.getParameter("no"));
			
			List<BoardVO> list = boardService.boardOption(no);
			
			request.setAttribute("list", list);
			
			return ViewPath.BOARD + "qnaWriteForm.jsp";
		}
		
		@RequestMapping("/member/board/qnaWrite")
		public String write(HttpServletRequest request,BoardVO vo) {
			String msg = null;
			String url = null;
			
			MemberVO mo = new MemberVO();
			mo = (MemberVO) request.getSession().getAttribute("login");
	
			Integer mb_no =mo.getMb_no();
			vo.setMb_no(mb_no);
			//---------------------------------------------
		
			int seq = Integer.parseInt(request.getParameter("seq"));


			
			int dualSeq = boardService.dualSeq();
			
			if(seq != 0) {			
				BoardVO ref = boardService.selectOneRef(seq);
				
				vo.setQna_origin(ref.getQna_origin());
				vo.setQna_order(ref.getQna_order() + 1);
				vo.setQna_layer(ref.getQna_layer() + 1);
			}else {
				vo.setQna_origin(dualSeq);
			}
			
			int su = boardService.insertQna(vo);
				
			if(su != 0) {
				msg = "글 작성에 성공하였습니다.";
			}else {
				msg = "글 작성이 실패하였습니다.";
			}
			url = "/team/member/board/qnaForm";
				
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			
			return ViewPath.BOARD+"result.jsp";
		}
		
		@RequestMapping("/member/board/privateQnaForm")
		public String privateQnaForm(HttpServletRequest request) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);
			return ViewPath.BOARD + "privateQna.jsp";
		}
		
		@RequestMapping("/member/board/privateQna")
		@ResponseBody
		public String privateQna(HttpServletRequest request) {
			MemberVO mo = new MemberVO();
			mo = (MemberVO) request.getSession().getAttribute("login");
			
			//관리자일시 별도의 비밀번호 입력 없이 확인버튼 클릭만으로 내용을 확인할 수 있도록
			if(mo.getMb_no()==1) {
				return "관리자";
			}

			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("pw");
			
			BoardVO vo = new BoardVO();
			
			vo.setQna_no(no);
			vo.setQna_pw(pw);
			
			vo = boardService.selectFaqPw(vo);
			
			if(vo == null) {
				return "비밀번호가 올바르지 않습니다";				
			}else {
				return "정답";
			}		
		}
		
		@RequestMapping("/member/board/qnaContent")
		public String qnaContent(HttpServletRequest request) {
			
			int qna_no = Integer.parseInt(request.getParameter("no"));
			
			BoardVO vo = new BoardVO();
			
			vo = boardService.qnaContent(qna_no);
			if(vo == null) {
				return "redirect:/team/member/board/faqForm";
			}		
			request.setAttribute("vo", vo);
		
			//조회수 +1
			int su = boardService.increaseQnaView(qna_no);
			
			if(su ==0) {
				return "redirect:/team/member/board/faqForm";
			}
			
			return ViewPath.BOARD + "qnaContent.jsp";
		}
		
		@RequestMapping("/member/board/qnaAnswerForm")
		public String qnaAnswerForm(HttpServletRequest request) {
		
			int seq = Integer.parseInt(request.getParameter("seq"));
			int no = Integer.parseInt(request.getParameter("board_no"));
			
			List<BoardVO> list = boardService.boardOption(no);
			
			request.setAttribute("list", list);
			
			BoardVO vo = new BoardVO();
			
			vo = boardService.selectQna(seq);
			
			if(vo == null) {
				return "redirect:/team/member/board/qnaForm/";
			}
			request.setAttribute("seq", seq);
			request.setAttribute("vo", vo);
			
			return ViewPath.BOARD + "qnaAnswerForm.jsp";
		}
		
		@RequestMapping("/member/board/qnaUpdateForm")
		public String qnaUpdateForm(HttpServletRequest request) {
				
			int no = Integer.parseInt(request.getParameter("board_no"));
			List<BoardVO> list = boardService.boardOption(no);
			
			request.setAttribute("list", list);
			
			//선택한 qna의 qna_no로 정보를 select해서 vo 에 저장
			int qna_no = Integer.parseInt(request.getParameter("no"));
			BoardVO vo = new BoardVO();
			
			vo = boardService.qnaContent(qna_no);
			if(vo == null) {
				return "redirect:/team/member/board/qnaForm";
			}		
			request.setAttribute("vo", vo);
	
			return ViewPath.BOARD + "qnaUpdateForm.jsp";
		}
		
		@RequestMapping("/member/board/qnaUpdate")
		public String qnaUpdate(HttpServletRequest request,BoardVO vo) {
			String msg = null;
			String url = null;
			
			int su = boardService.qnaUpdate(vo);
			
			if(su != 0) {
				msg = "글 수정에 성공하였습니다.";
			}else {
				msg = "글 수정에 실패하였습니다.";
			}
			
			url = "/team/member/board/qnaContent?no=" +vo.getQna_no();
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.BOARD + "result.jsp";
			}
		
		@RequestMapping("/member/board/qnaDelete")
		public String qnaDelete(HttpServletRequest request) {
			String url =null;
			String msg = null;
			
			int qna_no = Integer.parseInt(request.getParameter("no"));
			
			int su = boardService.qnaDelete(qna_no);
		
			if(su != 0) {
				msg = "글 삭제에 성공하였습니다.";
			}else {
				msg = "글 삭제에 실패하였습니다.";
			}
			
			url = "/team/member/board/qnaForm";
			
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			return ViewPath.BOARD + "result.jsp";
		}
		//--------------------Membership-------------------
		@RequestMapping("/member/board/membershipForm")
		public String membershipForm(HttpServletRequest request) {
			
		List<ManagementVO> list = boardService.selectMembershipList();
			
		request.setAttribute("list", list);
			
		return ViewPath.BOARD + "membershipForm.jsp";
		}
		//-----------------------review---------------------
		
		@RequestMapping("/member/board/reWriteForm")
		@ResponseBody
		public String reWriteForm(HttpServletRequest request) {
			
			MemberVO mo = new MemberVO();
			mo = (MemberVO) request.getSession().getAttribute("login");
			
			int mb_no = mo.getMb_no();
			int prod_no = Integer.parseInt(request.getParameter("prod_no"));
			
			OrderVO vo = new OrderVO();
			vo.setMb_no(mb_no);
			vo.setProd_no(prod_no);
			
			//회원번호와 제품번호로 => 주문번호 & 주문 시리얼번호 select
			List<OrderVO> ord1 = boardService.orderList(vo);	
			
			//해당 제품을 주문한 내역이 있는가?
			if(ord1.isEmpty()) { 
				return "작성 가능한 글이 없습니다";
			}			
			List<BoardVO> chk = new ArrayList<BoardVO>();
			
			//해당 주문번호로 리뷰를 남긴 적이 있는가?
			for(int i = 0; i< ord1.size(); i++) {
				
				int ord = ord1.get(i).getOrd_no();
				chk = boardService.checkOrd_no(ord);
				
				}
			if(!chk.isEmpty()) {
				return "작성 가능한 글이 없습니다";
			}
			return "작성 가능";				
			}
		
		@RequestMapping("/member/board/reWrite")
		public String reWrite(HttpServletRequest request,@RequestParam("photo") List<MultipartFile> list) {
			String msg = null;
			String url = null;

			MemberVO mo = new MemberVO();
			mo = (MemberVO) request.getSession().getAttribute("login");
			 	
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int mb_no = mo.getMb_no();
			int prod_no = Integer.parseInt(request.getParameter("prod_no"));
			String re_title = request.getParameter("re_title");
			String re_content = request.getParameter("re_content");
			int re_rate = Integer.parseInt(request.getParameter("re_rate"));
		
		OrderVO vo = new OrderVO();
		vo.setMb_no(mb_no);
		vo.setProd_no(prod_no);
		
		BoardVO bo = new BoardVO();
		
		int dualSeq = boardService.dualSeqRe();
		
		if(seq != 0) {
			
			BoardVO ref = boardService.selectOneRefRe(seq);
			
			bo.setRe_origin(ref.getRe_origin());
			bo.setRe_order(ref.getRe_order() + 1);
			bo.setRe_layer(ref.getRe_layer() + 1);
		}else {
			bo.setRe_origin(dualSeq);
		}
		List<OrderVO> ord1 = boardService.orderList(vo); //prod 1 mb 1 ord 1
		
		for(int i =0; i < ord1.size(); i++) {
			
			bo.setMb_no(mb_no);
			bo.setProd_no(prod_no);
			bo.setOrd_no(ord1.get(i).getOrd_no());
			bo.setRe_title(re_title);
			bo.setRe_content(re_content);
			bo.setRe_rate(re_rate);		
			
			int su = boardService.insertRe(bo);
			//정상적으로 입력 됬을때
			if(su !=0) { 
				
				boardService.updateProd_rate(prod_no);
				
				int re_no=boardService.getReSeq();
				
				//파일을 저장할 경로
				System.out.println(re_no);
				String savePath = request.getSession().getServletContext().getRealPath("/resources/review/"+re_no+"/");
				String filename = null;		
				
				//업로드된 실제파일명
								
				for( int j = 0; j < list.size(); j++) {
						
					MultipartFile photo = list.get(j);	
					
					filename = list.get(j).getOriginalFilename();			
					
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
						saveFile.delete(); 
					} catch (IOException e) {
						e.printStackTrace();
					}				
					}else {
						filename = " ";
					}
					
					bo.setRe_no(re_no);
					bo.setRp_filename(filename);
					bo.setRp_path(savePath);
					
					boardService.insertRePic(bo);
				}			
				
				msg = "리뷰 등록에 성공하였습니다.";
				url = "/team/member/product/contentForm?no="+prod_no;
				
				request.setAttribute("url", url);
				request.setAttribute("msg", msg);
				
				return ViewPath.BOARD+"result.jsp";
			}
		}
		return "";
		}
}
























