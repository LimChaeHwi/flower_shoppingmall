package com.spring.Hit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Hit.dto.Criteria;
import com.spring.Hit.dto.PageMaker;
import com.spring.Hit.dto.ReplyDto;
import com.spring.Hit.dao.ReplyDao;
// Rest Controller ��� ���� ���� !! 
// ���� ���ؼ� �۾��� �� REST ����� Ajax�� ���� ���յ� �����̴�.
// @PathVariable - URL�� ��ο��� ���ϴ� �����͸� �����ϴ� �뵵�� ���
// @RequestBody - ���۵� JSON �����͸� ��ü�� ��ȯ�� �ִ� �������̼����� 
// @ModelArrtibute�� ������ ������ ������ JSON���� ���ȴٴ� ���� ����
// method = RequestMethod.?? -> ����� post, ����� get, ������ put or patch, ������ delete�� �̷����.

@RestController
@RequestMapping("/product/replies")
public class ReplyController {

	@Inject
	private ReplyDao dao;
	
	// ��� ��� 
	// ResponseEntity<> Ÿ������ �����ؾ� �Ѵ�.
	// = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	// HttpStatus.OK�� ���������� ó���� �Ǿ���. �� ��, "SUCCESS" ��Ʈ���� �����ش�.
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyDto rto) {
		/*
		 * ResponseEntity<String> �� ��ȯ�� ��� ��Ͽ� �����ϸ� try~catch�� ������ ���� �޽����� �����ϰ�
		 * ����ڿ��Դ� BAD_REQUEST(400)�� ����� �����Ѵ�.
		 */
		ResponseEntity<String> entity = null;
		try {
			dao.create(rto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
	// �����ߴٸ� BAD_REQUEST(400)�� �����Ѵ�.
			e.printStackTrace();
			
			entity = new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// ����� ��� ��� ��� 
	@RequestMapping(value = "/{reply_group}", method = RequestMethod.POST)
	public ResponseEntity<String> rep_register(@RequestBody ReplyDto rto, @PathVariable("reply_group") Integer reply_group) {

		ResponseEntity<String> entity = null;
		try {
			rto.setReply_group(reply_group);
			dao.repupdate(rto);
			dao.repcreate(rto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			entity = new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// ��ü ��� ����Ʈ ���
	@RequestMapping(value = "/all/{item_no}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDto>> list(@PathVariable("item_no") Integer item_no) {

		ResponseEntity<List<ReplyDto>> entity = null;
		try {
			// ���� ��϶��ʹ� �޸� ��ü list�� �����Ѵ�.
			entity = new ResponseEntity<>(dao.list(item_no), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
		
	// ��� ����	
	@RequestMapping(value = "/{reply_no}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("reply_no") Integer reply_no, @RequestBody ReplyDto rto) {

		ResponseEntity<String> entity = null;
		try {
			rto.setReply_no(reply_no);
			dao.update(rto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��� ����
	@RequestMapping(value = "/{reply_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("reply_no") Integer reply_no) {

		ResponseEntity<String> entity = null;
		try {
			dao.delete(reply_no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��� ���� + ����¡ó��
	@RequestMapping(value = "/{item_no}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("item_no") Integer item_no,
			@PathVariable("page") Integer page) {

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			cri.setPerPageNum(10);
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyDto> list = dao.listPage(item_no, cri);
			
			map.put("list", list);
			
			int replyCount = dao.count(item_no);
			pageMaker.setTotalCount(replyCount);

			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��� ���� + ����¡ó��
		@RequestMapping(value = "/{item_no}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> listPage(@PathVariable("item_no") Integer item_no) {

			ResponseEntity<Map<String, Object>> entity = null;

			try {
				Criteria cri = new Criteria();
				// perPageNum�� �� ȭ�鿡 �� ����
				cri.setPerPageNum(3);

				Map<String, Object> map = new HashMap<String, Object>();
				List<ReplyDto> list = dao.listPage(item_no, cri);

				map.put("list", list);

				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
	
}
