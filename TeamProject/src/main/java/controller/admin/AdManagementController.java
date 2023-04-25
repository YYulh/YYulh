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
	
	//��� ���� view
	@RequestMapping("/admin/management/bannerForm")
	public String bannerForm(HttpServletRequest request) {
		
		//����� ��ʰ� ������ list�� �޾ƿͼ� ��µ� �� �ֵ��� ����
		List<ManagementVO> list =managementService.selectBannerList();
				
		request.setAttribute("list", list);
		
		return ViewPath.ADMINMANAGEMENT + "banner.jsp";
	}
	
	//��� ���
	@RequestMapping("/admin/management/bannerInsert")		//'photo' parameter�� List<MultipartFile>Ÿ���� 'list'�� �޾ƿ���
	public String bannerInsert(HttpServletRequest request, @RequestParam("photo") List<MultipartFile> list) {
		ManagementVO vo = new ManagementVO();
		int result = 0;
		
		String msg = null;
		String url = null;
		
		//���� ���� ���� ���
		String savePath = request.getSession().getServletContext().getRealPath("/resources/adminPic/");
		
		String filename = null;		
		
		//���ε�� �������ϸ�
		
		for(int i = 0; i < list.size(); i++) {
				
			MultipartFile photo = list.get(i);
			
			filename = list.get(i).getOriginalFilename();			
				
		if(!photo.isEmpty()) { //������ ������ ������
				
		//File ��ü ����
		File saveFile = new File(savePath,filename); 
			
		if(!saveFile.exists()) { //��ο� ������ ������
			saveFile.mkdirs();
			}else { //������					
		long time = System.currentTimeMillis(); //���� �̸� �ٲ��ִ� ���, �ٸ��� �ٲ��൵ ��.
				
		filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
			
		saveFile = new File(savePath,filename);
		}			
			
			//���ε�� ������ MultipartResolver��� Ŭ������ ������ �ӽ�����ҿ� ����Ǿ� �ִ�...
			//������ �����ð��� ������ ������⶧���� ���� ������ ��η� �������ش�...
			try {
				photo.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();					
				saveFile.delete(); // Ÿ�ӹи������ �ظ��ϸ� �̸��� �Ȱ�ġ������ ȣ���ö� ���ļ� �������� �ش� ���� ����!					
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
			msg = "��ʰ� ���������� ��ϵǾ����ϴ�.";
		}else{
			msg = "��� ��Ͽ� �����Ͽ����ϴ�. �����ڿ��� ���� ���ֽʽÿ�.";
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
			msg = "�����Ͻ� �׸��� �����Ǿ����ϴ�.";
		}else {
			msg = "�����Ͻ� �׸� ������ �����Ͽ����ϴ�.";
		}
		 url = "/team/admin/adminHome";
		 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		
		
		return ViewPath.ADMINMANAGEMENT + "result.jsp";
		}
		//-----------------Membership ���� ���� ---------------------
	
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
			
			//���ε�� �������ϸ�
				
			
			for(int i = 0; i < list.size(); i++) {
					
				MultipartFile photo = list.get(i);
				
				filename = list.get(i).getOriginalFilename();			
					
			if(!photo.isEmpty()) { //������ ������ ������
					
			//File ��ü ����
			File saveFile = new File(savePath,filename); 
				
			if(!saveFile.exists()) { //��ο� ������ ������
				saveFile.mkdirs();
				}else { //������					
			long time = System.currentTimeMillis(); //���� �̸� �ٲ��ִ� ���, �ٸ��� �ٲ��൵ ��.
					
			filename = String.format("%s%d%s", filename.substring(0, filename.lastIndexOf(".")),time,filename.substring(filename.lastIndexOf(".")));
				
			saveFile = new File(savePath,filename);
			}			
				
				//���ε�� ������ MultipartResolver��� Ŭ������ ������ �ӽ�����ҿ� ����Ǿ� �ִ�...
				//������ �����ð��� ������ ������⶧���� ���� ������ ��η� �������ش�...
				try {
					photo.transferTo(saveFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();					
					saveFile.delete(); // Ÿ�ӹи������ �ظ��ϸ� �̸��� �Ȱ�ġ������ ȣ���ö� ���ļ� �������� �ش� ���� ����!					
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
				msg = "�̹����� ���������� ��ϵǾ����ϴ�.";
			}else{
				msg = "�̹��� ��Ͽ� �����Ͽ����ϴ�. �����ڿ��� ���� ���ֽʽÿ�.";
			}
				url = "/team/admin/management/membershipForm";
								
		
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
			
			return ViewPath.ADMINMANAGEMENT+"result.jsp";
		}
		//------------categorie------------------------------
		
		
}
