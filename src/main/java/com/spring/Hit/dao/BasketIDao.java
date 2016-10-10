package com.spring.Hit.dao;

import java.util.List;

import com.spring.Hit.dto.BasketDto;
import com.spring.Hit.dto.MemberDto;
import com.spring.Hit.dto.OrderDto;
import com.spring.Hit.dto.ProductDto;

public interface BasketIDao {

	// ��ٱ��Ͽ� ��ǰ �߰�(insert one)
	public void writebasketDao(BasketDto bt);

	// ��ٱ��� ��� ����(select *)
	public List<ProductDto> viewbasketDao(BasketDto bdt);

	// ��ٱ����� ��ǰ ����(delete one)
	public void deletebasketDao(int basket_no);

	// ��ٱ����� ��ǰ ���� ����(delete list)
	public void delchoiceBasket(int[] list);

	public List<ProductDto> buychoiceBasket(int[] list);

	// ����������
	public List<MemberDto> bmemDao(String id);

	// ���� �Է�
	public void orderbasketins(OrderDto order);

	// ��ٱ����� ��ǰ ���� ���������� �̵�(select list,one)
	// public List<ProductDto> buychoiceBasket(int[] list);

	// ���� ����
	// ���� ����
	// ���� ����
	// ���� ����
	// ��� ����
	// ��� ����
	// ��� ����
	// ��� ����

}
