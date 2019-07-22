package com.poly.toba.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poly.toba.model.NoticeDTO;

@Mapper
public interface NoticeMapper {

	//공지사항 단일조회
	public NoticeDTO getNoticeDetail(NoticeDTO t) throws Exception;
	//조회수
	public int noticeUpdateCount(NoticeDTO nDTO) throws Exception;
	
	//페이징 /////////////////////////////
	//게시물 총 개수
	public int noticeTotalCount() throws Exception;
	//게시물 리스트
	public List<NoticeDTO> getNoticeList(HashMap<String, Integer> hMap) throws Exception;
	public List<NoticeDTO> getNoticeSearchTitleList(HashMap<String, Object> hMap);
	public List<NoticeDTO> getNoticeSearchContentList(HashMap<String, Object> hMap);
	public List<NoticeDTO> getNoticeSearchWriterList(HashMap<String, Object> hMap);
	//검색 게시물 총개수
	public int noticeSearchTitleTotalCount(HashMap<String, Object> hMap);
	public int noticeSearchContentTotalCount(HashMap<String, Object> hMap);
	public int noticeSearchWriterTotalCount(HashMap<String, Object> hMap);
	// 등록
	public int noticeReg(NoticeDTO nDTO) throws Exception;
}
