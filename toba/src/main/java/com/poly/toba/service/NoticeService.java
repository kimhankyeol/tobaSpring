package com.poly.toba.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.NoticeMapper;
import com.poly.toba.model.NoticeDTO;
import com.poly.toba.service.impl.ICommonService;

@Service
public class NoticeService implements ICommonService<NoticeDTO>{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public NoticeDTO getDetail(NoticeDTO t) throws Exception {
		// TODO Auto-generated method stub
	
		return noticeMapper.getNoticeDetail(t);
	}
	//조회수
	@Override
	public int noticeUpdateCount(NoticeDTO nDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.noticeUpdateCount(nDTO);
		
	}
	
	//페이징//////////////////////////////////////////////////
	//게시물 총개수
	@Override
	public int noticeTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.noticeTotalCount();
	}
	//게시물 리스트
	@Override
	public List<NoticeDTO> getNoticeList(HashMap<String, Integer> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList(hMap);
	}
	
	

}
