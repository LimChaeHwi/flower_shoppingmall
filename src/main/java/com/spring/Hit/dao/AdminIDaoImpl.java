package com.spring.Hit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.spring.Hit.dto.BasketDto;
import com.spring.Hit.dto.BoardDto;
import com.spring.Hit.dto.OrderDto;
import com.spring.Hit.dto.ProductDto;
import com.spring.Hit.dto.ReviewDto;

@Repository
public class AdminIDaoImpl implements AdminIDao{
	
	
	@Inject
	private SqlSession session;

	private static String namespace="com.spring.Hit.dao.AdminIDao";
	
	@Override
	public List<OrderDto> totalDao(OrderDto odt) {
		return session.selectList(namespace+".totalDao", odt);
	}
	
	@Override
	public List<OrderDto> monthDao(OrderDto odt) {
		return session.selectList(namespace+".monthDao", odt);
	}

	@Override
	public List<OrderDto> vieworder(OrderDto odt) {
		return session.selectList("vieworder", odt);
	}

	// ��� ó�� 
	@Override
	public void delsuc(int order_no) {
		session.update("delsuc", order_no);	
	}

	
	// ��� �˻�
	@Override
	public List<OrderDto> searchDao(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		OrderDto dto = new OrderDto();
		String type = req.getParameter("type");
		
		String content = req.getParameter("query");
		dto.setCategory(type);
		dto.setContent(content);
		return session.selectList("searchDao", dto);
	}
	
	
	// ��� ��
	@Override
	public List<OrderDto> deliver1(OrderDto odt) {
		return session.selectList("deliver1", odt);
	}
	@Override
	public List<OrderDto> deliver2(OrderDto odt) {
		return session.selectList("deliver2", odt);
	}
	
	
	
	
	
	
	
/*	@Override //��ǰ �� ��������(select one), ��ǰ ��� ����(one)
	public ProductDto viewDao(int item_no) {
		return session.selectOne("viewDao", item_no);
	}
	
	@Override
	public ProductDto searchItem(String item_name) {
		return session.selectOne("searchItem", item_name);
	}
	
	@Override //��ٱ��Ͽ� ��ǰ �߰�(insert one)
	public void writebasketDao(BasketDto bt) {
		BasketIDao bd = session.getMapper(BasketIDao.class);
		bd.writebasketDao(bt);
	}

	@Override //��ٱ��� ���� ����(select *)
	public List<ProductDto> viewbasketDao(BasketDto bdt) {
		return session.selectList("viewbasketDao",bdt);
	}
		
	@Override //��ٱ����� ��ǰ ����(delete one)
	public void deletebasketDao(int basket_no) {
		BasketIDao bd = session.getMapper(BasketIDao.class);
		bd.deletebasketDao(basket_no);
	}
	
	@Override //��ٱ����� ��ǰ ����(delete select)
	public void delchoiceBasket(int[] list) {
		BasketIDao bd = session.getMapper(BasketIDao.class);
		bd.delchoiceBasket(list);	
	}
	@Override
	public List<ProductDto> buychoiceBasket(int[] list) {
		return session.selectList("buychoiceBasket", list);
	}
	
	//���� ��ü��� ��������
	@Override
	public List<ReviewDto>getReview(int item_no) {
		return session.selectList("getReview", item_no);
	}
	
	//������
	@Override
	public void addReview(ReviewDto rd) {
		System.out.println(rd.getId());
		System.out.println(rd.getItem_no());
		System.out.println(rd.getReview_con());
		System.out.println(rd.getReview_tit());
		session.insert("addReview", rd);
	}

	//�������
	@Override
	public void updateReview(ReviewDto rd) {
		session.update("updateReview", rd);
	}
	
	//�������
	@Override
	public void deleteReview(int item_no) {
		session.delete("item_no", item_no);
	}*/
	
	
}
