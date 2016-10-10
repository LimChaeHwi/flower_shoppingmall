package com.spring.Hit.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.spring.Hit.dto.BoardDto;

public interface BoardIDao {
	
	//�Խ��� �� ����
	public int boardCountDao();
	//�Խ��� ���� Ȯ���ϱ�
	public String boardTName(String tName);
	//�Խ��� ��Ϻ���
	public List<BoardDto> boardDao(Model model);
	//�Խ��� �˻�
	public List<BoardDto> boardSearchDao(Model model);
	//�Խ��� �ۺ���
	public BoardDto boardContentDao(Model model);
	//�Խ��� �۵��
	public int boardWriteDao(Model model);
	//�Խ��� �� ���� ��
	public BoardDto boardUpdateFormDao(Model model);
	//�Խ��� �ۼ���
	public int boardUpdateDao(Model model);
	//�Խ��� �ۻ���
	public int boardDeleteDao(Model model);
	//�Խ��� ��ȸ�� ����
	public int boardHitsUpDao(Model model);
	//�Խ��� ��� ����Ʈ ����
	public List<BoardDto> boardReplyListDao(Model model);
	//�Խ��� ��� ����
	public int boardReplyWriteDao(Model model);
}
