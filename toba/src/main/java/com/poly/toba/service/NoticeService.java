package com.poly.toba.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.NoticeMapper;
import com.poly.toba.model.BoardLikeDTO;
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
	@Override
	public int noticeSearchTitleTotalCount(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.noticeSearchTitleTotalCount(hMap);
	}
	@Override
	public int noticeSearchContentTotalCount(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.noticeSearchContentTotalCount(hMap);
	}
	@Override
	public int noticeSearchContentWriterCount(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.noticeSearchWriterTotalCount(hMap);
	}
	//게시물 리스트
	@Override
	public List<NoticeDTO> getNoticeList(HashMap<String, Integer> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList(hMap);
	}
	@Override
	public List<NoticeDTO> getNoticeSearchTitleList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeSearchTitleList(hMap);
	}
	@Override
	public List<NoticeDTO> getNoticeSearchContentList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeSearchContentList(hMap);
	}
	@Override
	public List<NoticeDTO> getNoticeSearchWriterList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeSearchWriterList(hMap);
	}
	@Override
	   public int noticeReg(NoticeDTO nDTO) throws Exception {
	      return noticeMapper.noticeReg(nDTO);
	   }

	// 좋아요
	@Override
	public int noticeLikeCheck(BoardLikeDTO blDTO) throws Exception {
		return noticeMapper.noticeLikeCheck(blDTO);
	}
	@Override
	public int noticeLike(BoardLikeDTO blDTO) throws Exception {
		return noticeMapper.noticeLike(blDTO);
	}
	@Override
	public int noticeLikeDelete(BoardLikeDTO blDTO) throws Exception {
		return noticeMapper.noticeLikeDelete(blDTO);
	}
	@Override
	public int noticeLikeTotalCount(BoardLikeDTO blDTO) throws Exception {
		return noticeMapper.noticeLikeTotalCount(blDTO);
	}
	// 메인 리스트 뿌리기
	@Override
	public List<NoticeDTO> getMainNoticeList() throws Exception {
		return noticeMapper.getMainNoticeList();
	}
	

}
