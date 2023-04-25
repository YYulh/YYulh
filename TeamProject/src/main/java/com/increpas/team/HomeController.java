package com.increpas.team;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.ViewPath;
import service.member.HomeService;
import vo.ManagementVO;
import vo.ProductVO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private HomeService homeService;
	
	public HomeController(HomeService homeService) { 
		this.homeService = homeService; 	
	}; 	
	
	@RequestMapping(value = {"/","/team/"}, method = RequestMethod.GET) //연결할 경로 , GET,POST 방식 설정
	public String home(HttpServletRequest request) { 
		String name = "best"; //링크를 통해 받아온 name(카테고리 명) 값
	
		List<ProductVO> list = homeService.selectList(name);
		
		request.setAttribute("list", list);
		
		List<ProductVO> pbc = homeService.selectBigCate();
		List<ProductVO> psc1 = homeService.selectSmallCate1();
		List<ProductVO> psc2 = homeService.selectSmallCate2();
		
		request.getSession().setAttribute("pbc", pbc);
		request.getSession().setAttribute("psc1", psc1);
		request.getSession().setAttribute("psc2", psc2);		
		
		List<ManagementVO> bannerList = homeService.selectBannerList(); 
		
		request.getSession().setAttribute("bannerList", bannerList);
		
		
		return ViewPath.VIEW + "home.jsp";
	}
	

}
