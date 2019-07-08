package com.poly.toba.service.impl;

import java.util.List;

import com.poly.toba.model.NoticeDTO;

public interface ICommonService<T> {
	//전체 조회
	public List<T> getList() throws Exception;
	
	//공지사항 단일 조회
	public T getDetail(T t) throws Exception;
	
//	//등록
//	public int boardAdd(T t) throws Exception;
//	
	//삭제
	
	//수정
}
