package com.poly.toba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.ImageBoardDTO;
import com.poly.toba.model.NoticeDTO;
import com.poly.toba.service.impl.ICommentService;
import com.poly.toba.service.impl.ICommonService;
import com.poly.toba.service.impl.IImageBoardCommentService;
import com.poly.toba.service.impl.IImageBoardService;
import com.poly.toba.util.StringUtil;

@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/imageBoards")
public class ImageBoardController {
	 @Autowired
	 private IImageBoardService imageBoardService;
	 @Autowired
	 private IImageBoardCommentService imageBoardCommentService;
	 //이미지 게시판 5개 조회
	// 공지사항 최신 순 5개 리스트 가져오기
	@GetMapping("/mainImageBoardList")
	public ResponseEntity<HashMap<String, Object>> getMainImageBoardList() throws Exception {
		int imageBoardNo, imageBoardCommentCount;
		List<Integer> imageBoardCommentCountList = new ArrayList<>();
		HashMap<String, Object> hMap = new HashMap<>();
		List<ImageBoardDTO> ibList =imageBoardService.getMainImgBoardList();
		hMap.put("ibList", ibList);
		List<String> imgList = new ArrayList<>();
		List<String> thumbImgList = new ArrayList<>();
		for (int i = 0; i < ibList.size(); i++) {
			imageBoardNo = Integer.valueOf(ibList.get(i).getImageBoardNo());
			imageBoardCommentCount = imageBoardCommentService.commentCount(imageBoardNo);
			imageBoardCommentCountList.add(imageBoardCommentCount);
			imgList = StringUtil.getImgSrc(ibList.get(i).getImageBoardContent());
			thumbImgList.add(imgList.get(0));
		}
		hMap.put("imageBoardCommentCountList", imageBoardCommentCountList);
		hMap.put("thumbImgList", thumbImgList);
		
		return new ResponseEntity<HashMap<String, Object>>(hMap, HttpStatus.OK);
	}
	 
}
