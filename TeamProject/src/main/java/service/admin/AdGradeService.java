package service.admin;

import java.util.List;

import dao.admin.AdGradeDAO;
import vo.GradeVO;


public class AdGradeService {

	private AdGradeDAO gradedao;
	
	public AdGradeService(AdGradeDAO gradedao) {// DAO »£√‚
		this.gradedao = gradedao;
	}
	public List<GradeVO> selectGrade() {
		return gradedao.selectGrade();
	}
	public int insertGrade(GradeVO vo) {
		return gradedao.insertGrade(vo);
	}
	public int deleteGrade(String no) {
		return gradedao.deleteGrade(no);
	}
}
