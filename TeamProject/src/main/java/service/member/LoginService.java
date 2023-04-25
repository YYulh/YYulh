package service.member;

import dao.member.LoginDAO;
import vo.JoinVO;
import vo.MemberVO;
import vo.OrderVO;

public class LoginService {

		private LoginDAO logindao;
		
		public LoginService(LoginDAO logindao) {// DAO »£√‚
			this.logindao = logindao;
		}
		public MemberVO checkLogin(MemberVO vo) {
			return logindao.checkLogin(vo);
		}
		public MemberVO checkKakaoLogin(MemberVO vo) {
			return logindao.checkKakaoLogin(vo);
		}
		public String findId(MemberVO vo) {
			return logindao.findId(vo);
		}
		
		public String findPw(MemberVO vo) {
			return logindao.findPw(vo);
		}
		public String findId2(MemberVO vo) {
			return logindao.findId2(vo);
		}
		
		public String findPw2(MemberVO vo) {
			return logindao.findPw2(vo);
		}

		
		
}
