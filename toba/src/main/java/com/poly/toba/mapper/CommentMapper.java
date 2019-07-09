package com.poly.toba.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poly.toba.model.CommentDTO;
import com.poly.toba.model.NoticeDTO;

@Mapper
public interface CommentMapper {

	public List<CommentDTO> getCommentList(CommentDTO cDTO) throws Exception;

	public int insertComment(CommentDTO cDTO) throws Exception;
	
}
