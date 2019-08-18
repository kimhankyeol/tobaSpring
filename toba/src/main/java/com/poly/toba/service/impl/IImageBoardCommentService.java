package com.poly.toba.service.impl;

import java.util.HashMap;
import java.util.List;

import com.poly.toba.model.CommentDTO;

public interface IImageBoardCommentService {

	public int commentCount(int imageBoardNo) throws Exception;

	public List<CommentDTO> getCommentList(HashMap<String, Object> hMap) throws Exception;

	public List<CommentDTO> pagingLikeCnt(HashMap<String, Object> hMap) throws Exception;

	public int commentListTotalCount(CommentDTO cDTO) throws Exception; 

}
