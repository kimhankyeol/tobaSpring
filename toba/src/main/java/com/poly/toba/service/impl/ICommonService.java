package com.poly.toba.service.impl;

import java.util.HashMap;
import java.util.List;

import com.poly.toba.model.NoticeDTO;

public interface ICommonService<T> {
	
	
	//공지사항 단일 조회
	public T getDetail(T t) throws Exception;
	
	//조회수
	public int noticeUpdateCount(NoticeDTO nDTO) throws Exception;

	
	//페이징//////////////////////////////////
	//게시물 총 개수 
	public int noticeTotalCount() throws Exception;
	//게시물 조회
	public List<NoticeDTO> getNoticeList(HashMap<String, Integer> hMap) throws Exception ;
	
	//검색 후 all 게시물 개수
	public int noticeSearchTitleTotalCount(HashMap<String, Object> hMap) throws Exception;
	public int noticeSearchContentTotalCount(HashMap<String, Object> hMap) throws Exception;
	public int noticeSearchContentWriterCount(HashMap<String, Object> hMap) throws Exception;
	//검색 후 all  게시물 리스트
	public List<NoticeDTO> getNoticeSearchTitleList(HashMap<String, Object> hMap) throws Exception;
	public List<NoticeDTO> getNoticeSearchContentList(HashMap<String, Object> hMap) throws Exception;
	public List<NoticeDTO> getNoticeSearchWriterList(HashMap<String, Object> hMap) throws Exception;
	// 등록
	 public int noticeReg(NoticeDTO nDTO) throws Exception;

	
	

}
