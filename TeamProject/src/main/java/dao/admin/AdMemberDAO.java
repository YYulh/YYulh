package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataIntegrityViolationException;

import vo.GradeVO;
import vo.MemberVO;



public class AdMemberDAO {
	
	private SqlSession sqlSession;
	
	public AdMemberDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	public List<MemberVO> selectList(int start){
		return sqlSession.selectList("member.selectList", start);
	}
	public List<MemberVO> selectLeaveList(int start){
		return sqlSession.selectList("member.selectLeaveList", start);
	}
	public int getTotal() {
		return sqlSession.selectOne("member.getTotal");
	}

	public int deleteMember(String no) {
		return sqlSession.delete("member.deleteMember",no);
	}
	public List<GradeVO> selectGrade() {
		return sqlSession.selectList("member.selectGrade");
	}

	
}
