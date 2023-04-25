package controller.admin;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import common.ViewPath;
import service.admin.AdManagementService;
import vo.GradeVO;
import vo.ManagementVO;
import vo.ProductVO;

@Controller
public class AdManagementController {

	private AdManagementService managementService;
	
	public AdManagementController(AdManagementService managementService) { 
		this.managementService = managementService; 	
	}
	
	//배너 수정 view
	@RequestMapping("/admin/management/bannerForm")
	public String bannerForm(HttpServletRequest request) {
		
		//저장된 배너가 있으면 list로 받아와서 출력될 수 있도록 저장
		List<ManagementVO> list =managementService.selectBannerList();
				
		request.setAttribute("list", list);
		
		return ViewPath.ADMINMANAGEMENT + "banner.jsp";
	}
	
	//배너 등록
	@RequestMapping("/admin/management/bannerInsert")		//'photo' parameter를 List<MultipartFile>타입의 'list'로 받아오기
	public String bannerInsert(HttpServletRequest request, @RequestParam("photo") List<MultipartFile> list) {
		ManagementVO vo = new ManagementVO();
		int result = 0;
		
		String msg = null;
		String url = null;
		
		//파일 실제 저장 경로
		String savePath = request.getSession().getServletContext().getRealPath("/resources/adminPic/");
		
		String filename = null;		
		
		//업로드된 실제파일명
		
		for(int i = 0; i < list.size(); i++) {
				
			MultipartFile photo = list.get(i);
			
			filename = list.get(i).getOriginalFilename();			
				
		if(!photo.isEmpty()) { //가져온 사진이 있으면
				
		//File 객체 생성
		File saveFile = new File(savePath,filename); 
			
		if(!saveFile.exists()) { //경로에 파일이 없으면
			saveFile.mkdirs();
			}else { //있으면					
		long time = System.currentTimeMillis(); //단지 이름 바꿔주는 방식, 다르게 바꿔줘도 됨.
				
		filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
			
		saveFile = new File(savePath,filename);
		}			
			
			//업로드된 파일은 MultipartResolver라는 클래스가 지정한 임시저장소에 저장되어 있다...
			//파일이 일정시간이 지나면 사라지기때문에 내가 지정한 경로로 복사해준다...
			try {
				photo.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();					
				saveFile.delete(); // 타임밀리언즈라 왠만하면 이름이 안겹치겠지만 호옥시라도 겹쳐서 오류나면 해당 파일 삭제!					
			} catch (IOException e) {
				e.printStackTrace();
			}				
			}else {
				filename = " ";
			}
			
			
			vo.setAp_filename(filename);
			vo.setAp_path(savePath);
			
			int su = managementService.insertPic(vo);
			result +=su;
		}
		if(result != 0) {
			msg = "배너가 성공적으로 등록되었습니다.";
		}else{
			msg = "배너 등록에 실패하였습니다. 관리자에게 문의 해주십시오.";
		}
			url = "/team/admin/management/bannerForm";
						
			
			
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);			
			
			
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
	}
	@RequestMapping("/admin/management/deletePic")
	public String deleteBanner(HttpServletRequest request) {
		
		int su = 0;
		int result =0;
		String msg = null;
		String url = null;
		
		String[] arr = request.getParameterValues("delList");
				
		for(int i =0; i <arr.length; i++) {
			
			String no = arr[i];			
			su = managementService.deletePic(no);
			result += su;
			
		}
		if(result != 0) {
			msg = "선택하신 항목이 삭제되었습니다.";
		}else {
			msg = "선택하신 항목 삭제에 실패하였습니다.";
		}
		 url = "/team/admin/adminHome";
		 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
		}
		//-----------------Membership 사진 관리 ---------------------
	
		@RequestMapping("/admin/management/membershipForm")
		public String membershipForm(HttpServletRequest request) {
				
		List<ManagementVO> list = managementService.selectMembershipList();
				
		request.setAttribute("list", list);
		
		return ViewPath.ADMINMANAGEMENT+"membership.jsp";
	}
		
		@RequestMapping("/admin/management/insertMembership")
		public String insertMembership(HttpServletRequest request,@RequestParam("photo") List<MultipartFile> list) {
		
			String url = null;
			String msg = null;
			int result = 0;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/adminPic/");
			
			String filename = null;		
			
			//업로드된 실제파일명
				
			
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);
				
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //가져온 사진이 있으면
					
			//File 객체 생성
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //경로에 파일이 없으면
				saveFile.mkdirs();
				}else { //있으면					
			long time = System.currentTimeMillis(); //단지 이름 바꿔주는 방식, 다르게 바꿔줘도 됨.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//업로드된 파일은 MultipartResolver라는 클래스가 지정한 임시저장소에 저장되어 있다...
				//파일이 일정시간이 지나면 사라지기때문에 내가 지정한 경로로 복사해준다...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // 타임밀리언즈라 왠만하면 이름이 안겹치겠지만 호옥시라도 겹쳐서 오류나면 해당 파일 삭제!					
				} catch (IOException e) {
					e.printStackTrace();
				}				
				}else {
					filename = " ";
				}
				
				ManagementVO vo = new ManagementVO();
				
				vo.setAp_filename(filename);
				vo.setAp_path(savePath);
				
				int su = managementService.insertPic(vo);
				result +=su;
			}
			
			if(result != 0) {
				msg = "이미지가 성공적으로 등록되었습니다.";
			}else{
				msg = "이미지 등록에 실패하였습니다. 관리자에게 문의 해주십시오.";
			}
				url = "/team/admin/management/membershipForm";
								
		
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			
			return ViewPath.ADMINMANAGEMENT+"result.jsp";
		}
		//------------categorie------------------------------
		
		
}
