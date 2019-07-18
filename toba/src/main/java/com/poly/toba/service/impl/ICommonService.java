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
	

}
