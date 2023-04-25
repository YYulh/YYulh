package dao.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DuplicateKeyException;

import vo.BoardVO;
import vo.ManagementVO;
import vo.OrderVO;

public class BoardDAO {
	private SqlSession sqlSession;
	
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public List<BoardVO> selectFaqList(int start){
		return sqlSession.selectList("board.selectFaqList", start);
	}
	public int getTotal() {
		return sqlSession.selectOne("board.getTotal");
	}
	public List<BoardVO> boardOption(int no){
		return sqlSession.selectList("board.boardOption", no);
	}
	public int insertFaq(BoardVO vo) {
		return sqlSession.insert("board.insertFaq",vo);
	}
	public int getSeq() {
		return sqlSession.selectOne("board.getSeq");
	}
	public int insertPic(BoardVO vo) {
		return sqlSession.insert("board.insertPic",vo);
	}
	public BoardVO faqContent(int faq_no) {
		return sqlSession.selectOne("board.faqContent",faq_no);
	}
	public List<BoardVO> faqPic(int faq_no){
		return sqlSession.selectList("board.faqPic", faq_no);
	}
	public int increaseView(int faq_no) {
		return sqlSession.update("board.increaseView",faq_no);
	}
	public int faqUpdate(BoardVO vo) {
		return sqlSession.update("board.faqUpdate",vo);
	}
	public int faqDelete(int faq_no) {
		return sqlSession.delete("board.faqDelete",faq_no);
	}
	public List<BoardVO> selectQnaList(int start){
		return sqlSession.selectList("board.selectQnaList", start);
	}
	public int getTotalQna() {
		return sqlSession.selectOne("board.getTotalQna");
	}
	public BoardVO selectOneRef(int seq) {
		return sqlSession.selectOne("board.selectOneRef",seq);
	}
	public BoardVO selectOneRefRe(int seq) {
		return sqlSession.selectOne("board.selectOneRefRe",seq);
	}
	public int insertQna(BoardVO vo) {
		return sqlSession.insert("board.insertQna",vo);
	}
	public int dualSeq() {
		return sqlSession.selectOne("board.dualSeq");
	}
	public int dualSeqRe() {
		return sqlSession.selectOne("board.dualSeqRe");
	}
	public BoardVO selectFaqPw(BoardVO vo) {
		return sqlSession.selectOne("board.selectFaqPw",vo);
	}
	public BoardVO qnaContent(int qna_no) {
		return sqlSession.selectOne("board.qnaContent",qna_no);
	}
	public int increaseQnaView(int qna_no) {
		return sqlSession.update("board.increaseQnaView",qna_no);
	}
	public BoardVO selectQna(int seq) {
		return sqlSession.selectOne("board.selectQna",seq);
	}
	public int qnaUpdate(BoardVO vo) {
		return sqlSession.update("board.qnaUpdate",vo);
	}
	public int qnaDelete(int qna_no) {
		return sqlSession.delete("board.qnaDelete",qna_no);
	}
	public List<ManagementVO> selectMembershipList(){
		return sqlSession.selectList("board.selectMembershipList");
	}
	public List<OrderVO> orderList(OrderVO vo){
		return sqlSession.selectList("board.orderList",vo);
	}
	public List<OrderVO> checkOrd(List<OrderVO> ord){
		return sqlSession.selectList("board.checkOrd",ord);
	}
	public List<BoardVO> checkOrd_no(int ord){
		return sqlSession.selectList("board.checkOrd_no",ord);
	}
	public int insertRe(BoardVO bo) {
		int su = 0;
		try {
			su = sqlSession.insert("board.insertRe",bo);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
		
		return su;
	}
	public int getReSeq() {
		return sqlSession.selectOne("board.getReSeq");
	}
	public int insertRePic(BoardVO bo) {
		return sqlSession.insert("board.insertRePic",bo);
	}
	public int updateProd_rate(int prod_no) {
		return sqlSession.update("board.updateProd_rate",prod_no);
	}
	
}
