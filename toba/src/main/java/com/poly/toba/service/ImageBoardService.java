package com.poly.toba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.toba.mapper.ImageBoardMapper;
import com.poly.toba.model.ImageBoardDTO;
import com.poly.toba.service.impl.IImageBoardService;
@Service
public class ImageBoardService implements IImageBoardService{

	@Autowired
	private ImageBoardMapper imageBoardMapper;

	@Override
	public List<ImageBoardDTO> getMainImgBoardList() throws Exception {
		return imageBoardMapper.getMainImgBoardList();
	}


}
