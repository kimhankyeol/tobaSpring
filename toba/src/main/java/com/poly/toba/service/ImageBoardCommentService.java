package com.poly.toba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.ImageBoardCommentMapper;
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

}
