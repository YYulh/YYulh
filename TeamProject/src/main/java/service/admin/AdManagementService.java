package service.admin;

import java.util.List;

import dao.admin.AdManagementDAO;
import vo.ManagementVO;
import vo.ProductVO;


public class AdManagementService {

	private AdManagementDAO managementdao;
	
	public AdManagementService(AdManagementDAO managementdao) {// DAO »£√‚
		this.managementdao = managementdao;
	}
	public List<ManagementVO> selectBannerList(){
		return managementdao.selectBannerList();
	}
	public int insertPic(ManagementVO vo){
		return managementdao.insertPic(vo);
	}
	public int deletePic(String no){
		return managementdao.deletePic(no);
	}
	public List<ManagementVO> selectMembershipList(){
		return managementdao.selectMembershipList();
	}

}
