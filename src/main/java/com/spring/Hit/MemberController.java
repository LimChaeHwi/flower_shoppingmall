package com.spring.Hit;

import java.sql.Date;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Hit.dao.MemberIDao;
import com.spring.Hit.dto.MemberDto;


@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberIDao dao;
	
	//���� ������ & ������ ��ǰ ����Ʈ
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest req) {
		
		model.addAttribute("list", dao.mainDao());
		
		return "/member/main";
	}
/*
 * 	�ۼ��� : ������
 * 	������ : 2016.10.3
 */
	
	// ȸ������ �Է�
	@RequestMapping("/member_join")
	public String member_join(Model model, HttpServletRequest req) {
		model.addAttribute("req", req);
		dao.memberJoinDao(model);

		return "/member/main";
	}

//	// ȸ������ ������
//	@RequestMapping("/member_join_view")
//	public String member_join_view(Model model, HttpServletRequest req) {
//		
//		return "/member/member_join";
//	}

	// ID �ߺ� Ȯ��
	@RequestMapping("/member_Id")
	public String member_Id(Model model, HttpServletRequest req) {
		String id = req.getParameter("id");
		
		String a = dao.memberId(id);
		if(a==null){
			System.out.println(id);
			model.addAttribute("id", id);
		}else{
			
		}
		
		return "/member/member_join";
	}

/*
 * 	�ۼ��� : ������
 * 	������ : 2016.10.3
 */	
   //�α��� ������
//   @RequestMapping("/login")
//   public String login(Model model) {
//      
//      return "/member/login";
//   }
   
   //�α��� ó��
   @RequestMapping("/loginProc")
   public String loginProc(Model model, MemberDto dto, HttpServletRequest request) throws Exception {
      
      if(dao.checkDao(dto) == 0){            
         return "/member/main";
      }
      
      if(request.getSession()!=null){
         request.getSession().setAttribute("id",dto.getId());
         request.getSession().setAttribute("password",dto.getPassword());
      
         return "/member/main";
      }
      
      return "/member/login";
   }
   
   //�α׾ƿ�
   @RequestMapping("/logout")
   public String logout(Model model, HttpSession session) {
      
      if(session.getAttribute("id") != null)
         session.removeAttribute("id");
      
      return "/member/main";
   }
   
   //����������
   @RequestMapping("/myPage")
   public String myPage(Model model, HttpSession session) {
      
      String id = (String)session.getAttribute("id");
      
      model.addAttribute("dto", dao.viewMemberDao(id));
      
      return "/member/myPage";
   }
   
   //��������������
   @RequestMapping("/modifyInfo")
   public String modifyInfo(Model model, HttpSession session){
      
      String id = (String)session.getAttribute("id");
      model.addAttribute("dto", dao.viewMemberDao(id));
      
      return "/member/modifyInfo";
   }
   
   //�������� ó��
   @RequestMapping("/modifyInfoProc")
   public String modifyInfoProc(Model model, MemberDto dto, HttpSession session){
	   System.out.println("1111");
      
      dao.updateDao(dto);
      session.setAttribute("password", dto.getPassword());
      session.setAttribute("email", dto.getEmail());
      session.setAttribute("phone", dto.getPhone());
      
      return "/member/modifyInfo";
   }
   
   //ȸ�� Ż��
   @RequestMapping("/deleteDao")
   public String deleteDao(Model model, MemberDto dto, HttpSession session){
   
      String id = (String)session.getAttribute("id");
      
      dao.deleteDao(id);
      session.removeAttribute("id");
      
      return "/member/main";
   }
/*
* 	�ۼ��� : ������
* 	������ : 2016.10.3
*/	
   @RequestMapping("/joinForm")
   public String joinForm(Model model) {
	   return "/member/joinForm";
   }

   @RequestMapping("/loginForm")
   public String loginForm(Model model) {
	   return "/member/loginForm";
   }
	
   @RequestMapping("/login")
   public String login(MemberDto dto, HttpSession session) {
	   int cnt = dao.loginDao(dto);
	   System.out.println(dto.getId());
		
		// dao�κ��� id,pwd�� �Ѱܼ� �����ͺ��̽����� �α��ε� ��������� �ƴ����� �Ǻ�
	   if (cnt > 0) {
			// �α��� �� ����� �̱� ������ ������ �����Ѵ�.
		   session.setAttribute("id", dto.getId());
		   session.setAttribute("password", dto.getPassword());
		   session.setAttribute("logininfo", true);
		   System.out.println("Login ����!");
	   } else {
			// �߸��� �����̶�� �並 �����ֵ��� ���� ������ ����.
		   System.out.println("�߸��� ����");
		   return "member/loginForm";
	   }
	   return "member/main";
   }
//   @RequestMapping("/logout")
//   public String logout(HttpSession session){	
//	   session.invalidate();
//	   return "member/main";
//   }
	
   @RequestMapping("/cart")
   public String cart(HttpSession session){	
	   return "member/cart";
   }
	
   @RequestMapping("/order")
   public String order(HttpSession session){
	   return "member/order";
   }
		
   @RequestMapping("/wishList")
   public String wishList(HttpSession session){
	   	return "member/wishList";
}
	
   @RequestMapping("/findlogin")
   public void findlogin(HttpSession session){
	   session.setAttribute("logininfo", true);
   }
	
   @RequestMapping("/error")
   public String error(HttpSession session) {
      return "member/error";
   }
   
//   @RequestMapping("/myPage")
//   public String myPage(Model model, HttpSession session) {
//	   String id = (String)session.getAttribute("id");
//	   System.out.println(id);
//	   model.addAttribute("dto", dao.viewMemberDao(id)); 
//	   return "/member/myPage";
//   }

}
