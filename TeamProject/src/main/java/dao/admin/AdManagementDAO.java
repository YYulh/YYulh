package dao.admin;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ManagementVO;
import vo.ProductVO;

public class AdManagementDAO {
	
	private SqlSession sqlSession;
	
	public AdManagementDAO(SqlSession sqlSession) { 
		this.sqlSession = sqlSession;				
	}
	
	public List<ManagementVO> selectBannerList(){
		return sqlSession.selectList("management.selectBannerList");
	}
	public int insertPic(ManagementVO vo){
		return sqlSession.insert("management.insertPic",vo);
	}
	public int deletePic(String no){
		return sqlSession.delete("management.deletePic",no);
	}
	public List<ManagementVO> selectMembershipList(){
		return sqlSession.selectList("management.selectMembershipList");
	}

}
