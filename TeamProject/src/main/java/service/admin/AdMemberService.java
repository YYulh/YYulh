package service.admin;

import java.util.List;

import dao.admin.AdMemberDAO;
import vo.GradeVO;
import vo.MemberVO;


public class AdMemberService {

	private AdMemberDAO memberdao;
	
	public AdMemberService(AdMemberDAO memberdao) {// DAO »£√‚
		this.memberdao = memberdao;
	}
		
	public List<MemberVO> selectList(int start){
		return memberdao.selectList(start);
	}
	public List<MemberVO> selectLeaveList(int start){
		return memberdao.selectLeaveList(start);
	}
	public int getTotal() {
		return memberdao.getTotal();
	}
	public int deleteMember(String no) {
		return memberdao.deleteMember(no);
	}
	public List<GradeVO> selectGrade() {
		return memberdao.selectGrade();
	}

}
