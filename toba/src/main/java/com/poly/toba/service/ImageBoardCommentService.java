package com.poly.toba.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.ImageBoardCommentMapper;
import com.poly.toba.model.CommentDTO;
import com.poly.toba.service.impl.IImageBoardCommentService;
@Service
public class ImageBoardCommentService implements IImageBoardCommentService {
	
	@Autowired
	ImageBoardCommentMapper imageBoardCommentMapper;
	@Override
	public int commentCount(int imageBoardNo) throws Exception {
		// TODO Auto-generated method stub
		return imageBoardCommentMapper.commentCount(imageBoardNo);
	}
	@Override
	public List<CommentDTO> getCommentList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return imageBoardCommentMapper.getCommentList(hMap);
	}
	@Override
	public List<CommentDTO> pagingLikeCnt(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return imageBoardCommentMapper.pagingLikeCnt(hMap);
	}
	@Override
	public int commentListTotalCount(CommentDTO cDTO) throws Exception {
		// TODO Auto-generated method stub
		return imageBoardCommentMapper.commentListTotalCount(cDTO);
	}

}
