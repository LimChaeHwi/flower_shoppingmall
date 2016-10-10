package com.spring.Hit.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.spring.Hit.dto.BoardDto;

@Repository
public class BoardIDaoImpl implements BoardIDao{
	@Inject
	private SqlSession session;
	//board���̺� �� ���� ����
	@Override
	public int boardCountDao() {
		// TODO Auto-generated method stub
		return session.selectOne("boardCountDao");
	}
	//board���̺� ����¡ ó���ؼ� ����Ʈ ���
	@Override
	public List<BoardDto> boardDao(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int strNum = 1;
		if(req.getParameter("strNum")!=null){
			strNum = Integer.parseInt(req.getParameter("strNum"));
		}
		return session.selectList("boardDao", strNum);
	}
	//board���̺� �˻�
	@Override
	public List<BoardDto> boardSearchDao(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		BoardDto dto = new BoardDto();
		dto.setCategory(req.getParameter("type"));
		dto.setContent(req.getParameter("query"));
		return session.selectList("boardSearchDao", dto);
	}
	//board���̺� �� ���� ����
	@Override
	public BoardDto boardContentDao(Model model){
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		return session.selectOne("boardContentDao", board_no);
	}
	//board���̺� �� ����
	@Override
	public int boardWriteDao(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String content = req.getParameter("content");	
		BoardDto dto = new BoardDto(0, id, name, title, content, category, 0, 0, 0, 0, new Timestamp(System.currentTimeMillis()/1000));
		
		return session.insert("boardWriteDao", dto);
		
	}
	//board���̺� �� ����
	@Override
	public int boardDeleteDao(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		return session.delete("boardDeleteDao", board_no);
	}
	//board���̺� �� ���� �� ����
	@Override
	public BoardDto boardUpdateFormDao(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		return session.selectOne("boardContentDao", board_no);
	}
	//board���̺� �� ����
	@Override
	public int boardUpdateDao(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		Timestamp post_date = Timestamp.valueOf(req.getParameter("post_date"));
		
		BoardDto dto = new BoardDto(board_no, id, name, title, content, category, 0, 0, 0, 0, post_date);
		
		return session.update("boardUpdateDao", dto);
	}
	//board���̺� ��ȸ�� ����
	@Override
	public int boardHitsUpDao(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		return session.update("boardHitsUpDao", board_no);
	}
	//board���̺� ��� ����Ʈ ����
	@Override
	public List<BoardDto> boardReplyListDao(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		return session.selectList("boardReplyListDao", board_no);
	}
	//board���̺� ��� ����
	@Override
	public int boardReplyWriteDao(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		int board_group = Integer.parseInt(req.getParameter("board_group"));
		int board_step = Integer.parseInt(req.getParameter("board_step"));
		int board_indent = Integer.parseInt(req.getParameter("board_indent"));
		
		BoardDto dto = new BoardDto(0, id, name, null, content, null, 0, board_group, board_step, board_indent, new Timestamp(System.currentTimeMillis()/1000));
		session.update("boardReplyShapeDao", dto);
		return session.insert("boardReplyWriteDao",dto);
	}




}
