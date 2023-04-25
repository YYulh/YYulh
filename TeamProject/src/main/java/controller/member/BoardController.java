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

		//���� ������
		int nowPage = (start - 1) / 15 + 1;
		//�� ���� ����
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
			msg = "�� �ۼ��� �����Ͽ����ϴ�.";
		}else {
			msg = "�� �ۼ��� �����Ͽ����ϴ�.";
		}
		//-----------------------------------------------------------
		int seq = boardService.getSeq();  //��� insert ���� ���� seq ��������
	
		//-----------------------------------------------------------
		//������ ������ ���
		
				String savePath = request.getSession().getServletContext().getRealPath("/resources/board/");
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

		//faq_no �� ���� ���������� ����
		try {
			List<BoardVO> pic = boardService.faqPic(faq_no);	
			request.setAttribute("pic", pic);			
		} catch (MethodNotFoundException e) {
			e.printStackTrace();
		}
		

		//��ȸ�� +1
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

		//������ faq�� �������� �ҷ��ͼ� ����
		int faq_no = Integer.parseInt(request.getParameter("no"));
		BoardVO vo = new BoardVO();
		
		vo = boardService.faqContent(faq_no);
		if(vo == null) {
			return "redirect:/team/mamber/board/faqForm";
		}		
		request.setAttribute("vo", vo);
		
	//������ faq�� �������� �ҷ��ͼ� ����	
		
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
			msg = "�� ������ �����Ͽ����ϴ�.";
		}else {
			msg = "�� ������ �����Ͽ����ϴ�.";
		}
		//--------------------------------------------------------------------
		//������ ������ ���
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board/");
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
				msg = "�� ������ �����Ͽ����ϴ�.";
			}else {
				msg = "�� ������ �����Ͽ����ϴ�.";
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
			//���� ������
			int nowPage = (start - 1) / 15 + 1;
			//�� ���� ����
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
				msg = "�� �ۼ��� �����Ͽ����ϴ�.";
			}else {
				msg = "�� �ۼ��� �����Ͽ����ϴ�.";
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
			
			//�������Ͻ� ������ ��й�ȣ �Է� ���� Ȯ�ι�ư Ŭ�������� ������ Ȯ���� �� �ֵ���
			if(mo.getMb_no()==1) {
				return "������";
			}

			int no = Integer.parseInt(request.getParameter("no"));
			String pw = request.getParameter("pw");
			
			BoardVO vo = new BoardVO();
			
			vo.setQna_no(no);
			vo.setQna_pw(pw);
			
			vo = boardService.selectFaqPw(vo);
			
			if(vo == null) {
				return "��й�ȣ�� �ùٸ��� �ʽ��ϴ�";				
			}else {
				return "����";
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
		
			//��ȸ�� +1
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
			
			//������ qna�� qna_no�� ������ select�ؼ� vo �� ����
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
				msg = "�� ������ �����Ͽ����ϴ�.";
			}else {
				msg = "�� ������ �����Ͽ����ϴ�.";
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
				msg = "�� ������ �����Ͽ����ϴ�.";
			}else {
				msg = "�� ������ �����Ͽ����ϴ�.";
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
			
			//ȸ����ȣ�� ��ǰ��ȣ�� => �ֹ���ȣ & �ֹ� �ø����ȣ select
			List<OrderVO> ord1 = boardService.orderList(vo);	
			
			//�ش� ��ǰ�� �ֹ��� ������ �ִ°�?
			if(ord1.isEmpty()) { 
				return "�ۼ� ������ ���� �����ϴ�";
			}			
			List<BoardVO> chk = new ArrayList<BoardVO>();
			
			//�ش� �ֹ���ȣ�� ���並 ���� ���� �ִ°�?
			for(int i = 0; i< ord1.size(); i++) {
				
				int ord = ord1.get(i).getOrd_no();
				chk = boardService.checkOrd_no(ord);
				
				}
			if(!chk.isEmpty()) {
				return "�ۼ� ������ ���� �����ϴ�";
			}
			return "�ۼ� ����";				
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
			//���������� �Է� ������
			if(su !=0) { 
				
				boardService.updateProd_rate(prod_no);
				
				int re_no=boardService.getReSeq();
				
				//������ ������ ���
				System.out.println(re_no);
				String savePath = request.getSession().getServletContext().getRealPath("/resources/review/"+re_no+"/");
				String filename = null;		
				
				//���ε�� �������ϸ�
								
				for( int j = 0; j < list.size(); j++) {
						
					MultipartFile photo = list.get(j);	
					
					filename = list.get(j).getOriginalFilename();			
					
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
				
				msg = "���� ��Ͽ� �����Ͽ����ϴ�.";
				url = "/team/member/product/contentForm?no="+prod_no;
				
				request.setAttribute("url", url);
				request.setAttribute("msg", msg);
				
				return ViewPath.BOARD+"result.jsp";
			}
		}
		return "";
		}
}
























