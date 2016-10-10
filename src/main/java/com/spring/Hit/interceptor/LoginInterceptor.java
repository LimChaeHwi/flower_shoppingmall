package com.spring.Hit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// �α����� �ȵǾ� ������
		if(request.getSession().getAttribute("logininfo")==null){
			// �α����������� �̵�
			response.sendRedirect("/Hit/member/loginForm");
			System.out.println("���ͼ��� ����");
			log.debug("���ͼ��� �׽�Ʈ");
			return false;
		}
		
		// �α����� �Ǿ� ������ ���������� ��Ʈ�ѷ� ȣ��
		return true;
	}
}
