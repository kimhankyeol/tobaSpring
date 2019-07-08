package com.poly.toba.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.NoticeDTO;
import com.poly.toba.service.impl.ICommonService;

@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/notices")
public class NoticeController {
	@Autowired
	private ICommonService noticeService;
	
	//전체조회
	@GetMapping("/list")
	public ResponseEntity<List<NoticeDTO>> getNoticeList() throws Exception{
		List<NoticeDTO> nList = new ArrayList<>();
		nList = noticeService.getList();
		return new ResponseEntity<List<NoticeDTO>>(nList,HttpStatus.OK);
	}
	//단일조회
	@GetMapping("/detail/{noticeNo}")
	public ResponseEntity<NoticeDTO> getNoticeDetail(@PathVariable String noticeNo) throws Exception{
		NoticeDTO nDTO = new NoticeDTO();
		nDTO.setNoticeNo(noticeNo);
		nDTO = (NoticeDTO) noticeService.getDetail(nDTO);
		
		return new ResponseEntity<NoticeDTO>(nDTO,HttpStatus.OK);
	}
	
}
