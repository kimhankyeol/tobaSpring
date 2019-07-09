package com.poly.toba.service;

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
	public List<NoticeDTO> getList() throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getList();
	}
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
	
	

}
