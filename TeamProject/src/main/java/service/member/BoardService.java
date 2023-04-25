package service.member;

import java.util.List;

import dao.member.BoardDAO;
import vo.BoardVO;
import vo.ManagementVO;
import vo.OrderVO;

public class BoardService {

		private BoardDAO boarddao;
		
		public BoardService(BoardDAO boarddao) {// DAO »£√‚
			this.boarddao = boarddao;
		}
		public List<BoardVO> selectFaqList(int start){
			return boarddao.selectFaqList(start);
		}	
		public int getTotal() {
			return boarddao.getTotal();
		}
		public List<BoardVO> boardOption(int no){
			return boarddao.boardOption(no);
		}
		public int insertFaq(BoardVO vo) {
			return boarddao.insertFaq(vo);
		}
		public int getSeq(){
			return boarddao.getSeq();
		}
		public int insertPic(BoardVO vo){
			return boarddao.insertPic(vo);
		}
		public BoardVO faqContent(int faq_no){
			return boarddao.faqContent(faq_no);
		}
		public List<BoardVO> faqPic(int faq_no){
			return boarddao.faqPic(faq_no);
		}
		public int increaseView(int faq_no){
			return boarddao.increaseView(faq_no);
		}
		public int faqUpdate(BoardVO vo){
			return boarddao.faqUpdate(vo);
		}
		public int faqDelete(int faq_no){
			return boarddao.faqDelete(faq_no);
		}
		public List<BoardVO> selectQnaList(int start){
			return boarddao.selectQnaList(start);
		}	
		public int getTotalQna() {
			return boarddao.getTotalQna();
		}
		public BoardVO selectOneRef(int seq){
			return boarddao.selectOneRef(seq);
		}
		public BoardVO selectOneRefRe(int seq){
			return boarddao.selectOneRefRe(seq);
		}
		public int insertQna(BoardVO vo) {
			return boarddao.insertQna(vo);
		}
		public int dualSeq() {
			return boarddao.dualSeq();
		}
		public int dualSeqRe() {
			return boarddao.dualSeqRe();
		}
		public BoardVO selectFaqPw(BoardVO vo){
			return boarddao.selectFaqPw(vo);
		}
		public BoardVO qnaContent(int qna_no){
			return boarddao.qnaContent(qna_no);
		}
		public int increaseQnaView(int qna_no){
			return boarddao.increaseQnaView(qna_no);
		}
		public BoardVO selectQna(int seq){
			return boarddao.selectQna(seq);
		}
		public int qnaUpdate(BoardVO vo){
			return boarddao.qnaUpdate(vo);
		}
		public int qnaDelete(int qna_no){
			return boarddao.qnaDelete(qna_no);
		}
		public List<ManagementVO> selectMembershipList(){
			return boarddao.selectMembershipList();
		}
		public List<OrderVO> orderList(OrderVO vo){
			return boarddao.orderList(vo);
		}
		public List<OrderVO> checkOrd(List<OrderVO> ord){
			return boarddao.checkOrd(ord);
		}
		public List<BoardVO> checkOrd_no(int ord){
			return boarddao.checkOrd_no(ord);
		}
		public int insertRe(BoardVO bo){
			return boarddao.insertRe(bo);
		}
		public int getReSeq(){
			return boarddao.getReSeq();
		}
		public int insertRePic(BoardVO bo){
			return boarddao.insertRePic(bo);
		}
		public int updateProd_rate(int prod_no){
			return boarddao.updateProd_rate(prod_no);
		}
		
}
