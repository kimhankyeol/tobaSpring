package com.poly.toba.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poly.toba.model.NoticeDTO;

@Mapper
public interface NoticeMapper {
	//전체조회
	public List<NoticeDTO> getList() throws Exception;
	//공지사항 단일조회
	public NoticeDTO getNoticeDetail(NoticeDTO t) throws Exception;
}
