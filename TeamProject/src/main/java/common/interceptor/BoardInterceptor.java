//package common.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//public class BoardInterceptor extends HandlerInterceptorAdapter{
//	
//	//������ ��ΰ� ���۵ɶ� ���� ����Ǵ� �޼ҵ�
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println("=========START=========");
//		System.out.println(request.getRequestURI());
//				
//		
//		if(request.getSession().getAttribute("login") == null) {
//			response.sendRedirect("/team/member/login/loginForm");
//		}
//		return super.preHandle(request, response, handler);
//	}
//	
	//������ ��ΰ� ������ ���� ����Ǵ� �޼ҵ�
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		super.postHandle(request, response, handler, modelAndView);
//	}
//}
