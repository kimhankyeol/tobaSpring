package com.poly.toba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.CommentDTO;
import com.poly.toba.service.impl.ICommentService;

@SpringBootApplication
@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private ICommentService commentService;

	@GetMapping("/list/{noticeNo}")
	public ResponseEntity<HashMap<String, Object>> getCommentList(@PathVariable String noticeNo) throws Exception {
		CommentDTO cDTO = new CommentDTO();
		List<CommentDTO> cList = new ArrayList<>();
		HashMap<String, Object> hMap = new HashMap<>();
		cDTO.setNoticeNo(noticeNo);
		cList = commentService.getCommentList(cDTO);
		if (cList == null) {
			hMap.put("commentTotalCount", 0);
		} else {
			hMap.put("cList", cList);
			hMap.put("commentTotalCount", cList.size());
		}

		return new ResponseEntity<HashMap<String, Object>>(hMap, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> insertComment(@RequestBody CommentDTO cDTO) throws Exception {
		int result = commentService.insertComment(cDTO);

		if (result == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
		}

	}
}
