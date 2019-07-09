package com.poly.toba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.CommentMapper;
import com.poly.toba.model.CommentDTO;
import com.poly.toba.service.impl.ICommentService;

@Service
public class CommentService implements ICommentService{

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDTO> getCommentList(CommentDTO cDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getCommentList(cDTO);
	}

	@Override
	public int insertComment(CommentDTO cDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.insertComment(cDTO);
	}
	

}
