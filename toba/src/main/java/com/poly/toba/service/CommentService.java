package com.poly.toba.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.CommentMapper;
import com.poly.toba.model.CommentDTO;
import com.poly.toba.model.LikeDTO;
import com.poly.toba.model.NoticeDTO;
import com.poly.toba.model.RecommentDTO;
import com.poly.toba.service.impl.ICommentService;

@Service
public class CommentService implements ICommentService{

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDTO> getCommentList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getCommentList(hMap);
	}

	@Override
	public int insertComment(CommentDTO cDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.insertComment(cDTO);
	}


	@Override
	public int commentListTotalCount(CommentDTO cDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.commentListTotalCount(cDTO);
	}

	@Override
	public int recommentListTotalCount(RecommentDTO recDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.recommentListTotalCount(recDTO);
	}

	@Override
	public List<RecommentDTO> getRecommentList(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getRecommentList(hMap);
	}

	@Override
	public int insertRecomment(RecommentDTO recDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.insertRecomment(recDTO);
	}

	@Override
	public int deleteComment(String commentNo) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.deleteRecomment(commentNo);
		return commentMapper.deleteComment(commentNo);
	}

	@Override
	public int deleteRecommentSel(String recommentNo) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.deleteRecommentSel(recommentNo);
	}

	@Override
	public LikeDTO likeCheck(LikeDTO likeDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.likeCheck(likeDTO);
	}

	@Override
	public int likeUp(LikeDTO likeDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.likeUp(likeDTO);
	}

	@Override
	public int likeDown(LikeDTO likeDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.likeDown(likeDTO);
	}

	@Override
	public int likeCommentCount(LikeDTO likeDTO) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.likeCommentCount(likeDTO);
	}

	@Override
	public List<CommentDTO> pagingLikeCnt(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.pagingLikeCnt(hMap);
	}

	@Override
	public int commentCount(int noticeNo) throws Exception {
		return commentMapper.commentCount(noticeNo);
	}

	@Override
	public int commentUpd(CommentDTO cDTO) throws Exception {
		return commentMapper.commentUpd(cDTO);
	}

	@Override
	public int remmentUpd(RecommentDTO rDTO) throws Exception {
		return commentMapper.recommentUpd(rDTO);
	}

	@Override
	public String getProfileImg(String commentWriter) throws Exception {
		return commentMapper.getProfileImg(commentWriter);
	}

}
