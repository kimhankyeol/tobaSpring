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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.BoardLikeDTO;
import com.poly.toba.model.NoticeDTO;
import com.poly.toba.model.PagingDTO;
import com.poly.toba.service.impl.ICommonService;
import com.poly.toba.util.CmmUtil;

@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/notices")
public class NoticeController {
	@Autowired
	private ICommonService noticeService;

	// 전체조회
	@GetMapping("/list/{pageno}")
	public ResponseEntity<HashMap<String, Object>> getNoticeAllList(@PathVariable String pageno) throws Exception {
		System.out.println("확인");
		HashMap<String, Object> resultMap = new HashMap<>();
		// 페이징
		PagingDTO paging = new PagingDTO();
		int pagenum = Integer.parseInt(pageno);
		int contentnum = 10;
		int totalcount = 0;
		totalcount = noticeService.noticeTotalCount();
		paging.setTotalcount(totalcount);// 전체 게시글 지정
		paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
		paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
		paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
		paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
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
		return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);

	}

	@GetMapping("/list/{pageno}/{searchWord}/{searchCategory}")
	public ResponseEntity<HashMap<String, Object>> getNoticeList(@PathVariable String pageno,
			@PathVariable String searchWord, @PathVariable String searchCategory) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<>();
		// 페이징
		PagingDTO paging = new PagingDTO();
		int pagenum = Integer.parseInt(pageno);
		int contentnum = 10;
		int totalcount = 0;
		if (CmmUtil.nvl(searchWord) != "") {
			if (CmmUtil.nvl(searchCategory).equals("title")) {
				HashMap<String, Object> hMap = new HashMap<>();
				hMap.put("searchWord", searchWord);
				hMap.put("searchCategory", searchCategory);
				totalcount = noticeService.noticeSearchTitleTotalCount(hMap);
				paging.setTotalcount(totalcount);// 전체 게시글 지정
				paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
				paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
				paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
				paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
				paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
				paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
				paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로
																					// 정함
				List<NoticeDTO> nList = new ArrayList();
				int i = paging.getPagenum() * 10;
				int j = paging.getContentnum();
				hMap.put("pagenum", i);
				hMap.put("contentnum", j);
				nList = noticeService.getNoticeSearchTitleList(hMap);
				resultMap.put("nList", nList);
				resultMap.put("paging", paging);
			} else if (CmmUtil.nvl(searchCategory).equals("content")) {
				HashMap<String, Object> hMap = new HashMap<>();
				hMap.put("searchWord", searchWord);
				hMap.put("searchCategory", searchCategory);
				totalcount = noticeService.noticeSearchContentTotalCount(hMap);
				paging.setTotalcount(totalcount);// 전체 게시글 지정
				paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
				paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
				paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
				paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
				paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
				paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
				paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로
																					// 정함
				List<NoticeDTO> nList = new ArrayList();
				int i = paging.getPagenum() * 10;
				int j = paging.getContentnum();
				hMap.put("pagenum", i);
				hMap.put("contentnum", j);
				nList = noticeService.getNoticeSearchContentList(hMap);
				resultMap.put("nList", nList);
				resultMap.put("paging", paging);
			} else if (CmmUtil.nvl(searchCategory).equals("writer")) {
				HashMap<String, Object> hMap = new HashMap<>();
				hMap.put("searchWord", searchWord);
				hMap.put("searchCategory", searchCategory);
				totalcount = noticeService.noticeSearchContentWriterCount(hMap);
				paging.setTotalcount(totalcount);// 전체 게시글 지정
				paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
				paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
				paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
				paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
				paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
				paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
				paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로
																					// 정함
				List<NoticeDTO> nList = new ArrayList();
				int i = paging.getPagenum() * 10;
				int j = paging.getContentnum();
				hMap.put("pagenum", i);
				hMap.put("contentnum", j);
				nList = noticeService.getNoticeSearchWriterList(hMap);
				resultMap.put("nList", nList);
				resultMap.put("paging", paging);
			}

		}
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
		// 좋아요 수 가져오기
		BoardLikeDTO blDTO = new BoardLikeDTO();
		blDTO.setNoticeNo(noticeNo);
		int likeCount = noticeService.noticeLikeTotalCount(blDTO);
		hMap.put("noticeLikeCount", likeCount);
		System.out.println(" 이 글의 좋아요 수는 : " + likeCount+"개 입니당");
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
		} else {
			prev = "이전 글은 없습니다.";
			next = "다음 글은 없습니다.";
			prevDTO.setNoticeNo("0");
			prevDTO.setNoticeTitle(prev);
			nextDTO.setNoticeNo("0");
			nextDTO.setNoticeTitle(next);
			hMap.put("prevDTO", prevDTO);
			hMap.put("nextDTO", nextDTO);
		}
		return new ResponseEntity<HashMap<String, Object>>(hMap, HttpStatus.OK);
	}

	@PostMapping("/submit")
	public ResponseEntity<Integer> regNotice(@RequestBody NoticeDTO nDTO) throws Exception {
		System.out.println("게시물 등록을 해보자");
		int result = noticeService.noticeReg(nDTO);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	// 공지사항 좋아요
	@PostMapping("/noticeLike")
	public ResponseEntity<Integer> noticeLike(@RequestBody BoardLikeDTO blDTO) throws Exception {
		int likeCount, result;
		// 좋아요 여부 확인
		result = noticeService.noticeLikeCheck(blDTO);
		// 좋아요 하지 않았었으면
		if(result == 0) {
			result = noticeService.noticeLike(blDTO);
			likeCount = noticeService.noticeLikeTotalCount(blDTO);
		} else {
			// 좋아요 했었으면
			result = noticeService.noticeLikeDelete(blDTO);
			likeCount = noticeService.noticeLikeTotalCount(blDTO);
		}
		return new ResponseEntity<Integer>(likeCount, HttpStatus.OK);
	}
}
