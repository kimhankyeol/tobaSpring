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
import com.poly.toba.model.PagingDTO;
import com.poly.toba.service.impl.ICommonService;

@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/notices")
public class NoticeController {
	@Autowired
	private ICommonService noticeService;

	// 전체조회
	@GetMapping("/list/{pageno}")
	public ResponseEntity<HashMap<String, Object>> getNoticeList(@PathVariable String pageno) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<>();
		// 페이징
		PagingDTO paging = new PagingDTO();
		int pagenum = Integer.parseInt(pageno);
		System.out.println("페이지넘버" + pagenum);
		int contentnum = 10;
		int totalcount = 0;
		totalcount = noticeService.noticeTotalCount();
		paging.setTotalcount(totalcount);// 전체 게시글 지정
		paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
		paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
		paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
		paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
		// log.info("Last block:"+paging.getTotalcount());
		paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
		paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
		paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로 정함
		List<NoticeDTO> nList = new ArrayList();
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getPagenum() * 10;
		int j = paging.getContentnum();
		hMap.put("pagenum", i);
		hMap.put("contentnum", j);
		nList = noticeService.getNoticeList(hMap);
		resultMap.put("nList", nList);
		resultMap.put("paging", paging);

		System.out.println(paging.getCurrentblock());
		System.out.println(nList.get(0).getNoticeTitle());
		return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
	}

	// 단일조회
	@GetMapping("/detail/{noticeNo}")
	public ResponseEntity<HashMap<String, Object>> getNoticeDetail(@PathVariable String noticeNo) throws Exception {
		NoticeDTO nDTO = new NoticeDTO();
		NoticeDTO prevDTO = new NoticeDTO();
		NoticeDTO nextDTO = new NoticeDTO();
		HashMap<String, Object> hMap = new HashMap<>();
		nDTO.setNoticeNo(noticeNo);
		nDTO = (NoticeDTO) noticeService.getDetail(nDTO);
		// 조회수
		int result = noticeService.noticeUpdateCount(nDTO);
		hMap.put("nDTO", nDTO);
		hMap.put("noticeTotalCount", noticeService.noticeTotalCount());
		String prev, next;
		if (nDTO.getNoticePrev() == null && nDTO.getNoticeNext() != null) {
			prev = "이전 글은 없습니다.";
			prevDTO.setNoticeNo("0");
			prevDTO.setNoticeTitle(prev);
			nextDTO.setNoticeNo(nDTO.getNoticeNext());
			nextDTO = (NoticeDTO) noticeService.getDetail(nextDTO);
			hMap.put("prevDTO", prevDTO);
			hMap.put("nextDTO", nextDTO);
		} else if (nDTO.getNoticePrev() != null && nDTO.getNoticeNext() == null) {
			prevDTO.setNoticeNo(nDTO.getNoticePrev());
			prevDTO = (NoticeDTO) noticeService.getDetail(prevDTO);
			next = "다음 글은 없습니다.";
			nextDTO.setNoticeNo("0");
			nextDTO.setNoticeTitle(next);
			hMap.put("prevDTO", prevDTO);
			hMap.put("nextDTO", nextDTO);
		} else if (nDTO.getNoticeNext() != null && nDTO.getNoticePrev() != null) {
			prevDTO.setNoticeNo(nDTO.getNoticePrev());
			prevDTO = (NoticeDTO) noticeService.getDetail(prevDTO);
			nextDTO.setNoticeNo(nDTO.getNoticeNext());
			nextDTO = (NoticeDTO) noticeService.getDetail(nextDTO);
			hMap.put("prevDTO", prevDTO);
			hMap.put("nextDTO", nextDTO);
		}

		return new ResponseEntity<HashMap<String, Object>>(hMap, HttpStatus.OK);
	}

}