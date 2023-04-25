package dao.member;

import org.apache.ibatis.session.SqlSession;

import vo.JoinVO;
import vo.MemberVO;
import vo.OrderVO;

public class LoginDAO {
	private SqlSession sqlSession;
	
	public LoginDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
		public MemberVO checkLogin(MemberVO vo) {
			return sqlSession.selectOne("login.checkLogin",vo);		
		}
		public MemberVO checkKakaoLogin(MemberVO vo) {
			return sqlSession.selectOne("login.checkKakaoLogin",vo);		
			}
		public String findId(MemberVO vo) {
			return sqlSession.selectOne("login.findId",vo);
		}
		
		public String findPw(MemberVO vo) {
			return sqlSession.selectOne("login.findPw",vo);
		}
		public String findId2(MemberVO vo) {
			return sqlSession.selectOne("login.findId2",vo);
		}
		
		public String findPw2(MemberVO vo) {
			return sqlSession.selectOne("login.findPw2",vo);
		}	
}
