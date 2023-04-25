package dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataIntegrityViolationException;

import vo.GradeVO;



public class AdGradeDAO {
	
	private SqlSession sqlSession;
	
	public AdGradeDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}

	public List<GradeVO> selectGrade() {
		return sqlSession.selectList("grade.selectGrade");
	}
	public int insertGrade(GradeVO vo) {
		return sqlSession.insert("grade.insertGrade",vo);
	}
	public int deleteGrade(String no) {
		int su = 1;
		try {
			su = sqlSession.delete("grade.deleteGrade",no);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
		return su;
	}
	
}
