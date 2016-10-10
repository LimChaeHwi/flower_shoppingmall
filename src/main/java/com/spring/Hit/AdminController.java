package com.spring.Hit;

import java.awt.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.Hit.dao.AdminIDao;
import com.spring.Hit.dto.OrderDto;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private SqlSession sqlSession;

	@Inject
	private AdminIDao dao;

	// ������ ���� ������
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpSession session) {
		logger.info("���� ������");
		if (session.getAttribute("id") != null) {
			if (session.getAttribute("id").equals("admin123") == true) {
				return "/admin/main";
			} else {
				return "/member/error";
			}
		} else {
			return "/member/error";
		}
	}

	// ������ ���� ����
	@RequestMapping(value = "/admin_sales", method = RequestMethod.GET)
	public void salesGET(@ModelAttribute("odt") OrderDto odt, Model model) {
		logger.info("���� ������");
		model.addAttribute("list", dao.totalDao(odt));
	}

	// ������ ���� ����
	@RequestMapping(value = "/admin_sales_month", method = RequestMethod.GET)
	public void salesMonth(@ModelAttribute("odt") OrderDto odt, @RequestParam("month") String month, Model model) {
		logger.info("�� ������");
		odt.setMonth(month);
		model.addAttribute("list", dao.monthDao(odt));
	}

	// ������ ��� ����
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public void delivery(@ModelAttribute("odt") OrderDto odt, Model model) {
		logger.info(odt.toString());
		model.addAttribute("list", dao.vieworder(odt));
	}

	// ��� �Ϸ� ó��
	@RequestMapping("/delsuc")
	public String delsuc(HttpServletRequest request) {
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		dao.delsuc(order_no);
		return "redirect:/admin/delivery";
	}

	
	//��� �˻�
	@RequestMapping("/search")
	public String boardSearch(Model model, HttpServletRequest req) {
		model.addAttribute("req", req);
		model.addAttribute("list", dao.searchDao(model));//����,����,�ۼ��ڷ� �˻�
		return "/admin/delivery";
	}

	
	// ��� �� ������ ó��
	@RequestMapping(value = "/deliver_detail", method = RequestMethod.GET)
	public void deliver(@ModelAttribute("odt") OrderDto odt, Model model, HttpServletRequest request) {
		String a = request.getParameter("a");
		if(a.equals("a")){
			
			logger.info(odt.toString());
			model.addAttribute("list", dao.deliver1(odt));
			
		}else if(a.equals("b")){
			
			logger.info(odt.toString());
			model.addAttribute("list", dao.deliver2(odt));
			
		}
	}
}