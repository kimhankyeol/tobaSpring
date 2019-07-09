package com.poly.toba.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
	public ResponseEntity<HashMap<String,Object>> getNoticeDetail(@PathVariable String noticeNo) throws Exception{
		NoticeDTO nDTO = new NoticeDTO();
		NoticeDTO prevDTO = new NoticeDTO();
		NoticeDTO nextDTO = new NoticeDTO();
		HashMap<String,Object> hMap = new HashMap<>();
		nDTO.setNoticeNo(noticeNo);
		nDTO = (NoticeDTO) noticeService.getDetail(nDTO);
		
		hMap.put("nDTO",nDTO);
		hMap.put("noticeTotalCount", noticeService.getList().size());
		String prev,next;
		if(nDTO.getNoticePrev()==null&&nDTO.getNoticeNext()!=null) {
			prev = "이전 글은 없습니다.";
			prevDTO.setNoticeNo("0");
			prevDTO.setNoticeTitle(prev);
			nextDTO.setNoticeNo(nDTO.getNoticeNext());
			nextDTO = (NoticeDTO) noticeService.getDetail(nextDTO);
			hMap.put("prevDTO",prevDTO);
			hMap.put("nextDTO",nextDTO);
		}else if(nDTO.getNoticePrev()!=null&&nDTO.getNoticeNext()==null) {
			prevDTO.setNoticeNo(nDTO.getNoticePrev());
			prevDTO = (NoticeDTO) noticeService.getDetail(prevDTO);
			next="다음 글은 없습니다.";
			nextDTO.setNoticeNo("0");
			nextDTO.setNoticeTitle(next);
			hMap.put("prevDTO",prevDTO);
			hMap.put("nextDTO",nextDTO);
		}else if(nDTO.getNoticeNext()!=null&&nDTO.getNoticePrev()!=null) {
			prevDTO.setNoticeNo(nDTO.getNoticePrev());
			prevDTO = (NoticeDTO) noticeService.getDetail(prevDTO);
			nextDTO.setNoticeNo(nDTO.getNoticeNext());
			nextDTO = (NoticeDTO) noticeService.getDetail(nextDTO);
			hMap.put("prevDTO",prevDTO);
			hMap.put("nextDTO",nextDTO);
		}
		
		
		return new ResponseEntity<HashMap<String,Object>>(hMap,HttpStatus.OK);
	}
	
}
