package com.poly.toba.service.impl;

import java.util.List;

import com.poly.toba.model.CommentDTO;

public interface ICommentService {

	public List<CommentDTO> getCommentList(CommentDTO cDTO) throws Exception;

	public int insertComment(CommentDTO cDTO) throws Exception;

}
