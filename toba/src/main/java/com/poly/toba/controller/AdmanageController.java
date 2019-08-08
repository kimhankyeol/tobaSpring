package com.poly.toba.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.IDN;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.toba.model.AdmanageDTO;
import com.poly.toba.model.PagingDTO;
import com.poly.toba.service.impl.IAdmanageService;
@SpringBootApplication
@MapperScan(basePackages = "com.poly.toba")
@RestController
@RequestMapping("/admanage")
public class AdmanageController {
	@Autowired
	private IAdmanageService admanageService;
	
	
	// 전체조회
	@GetMapping("/totalAdmanage/{pageno}")
	public ResponseEntity<HashMap<String, Object>> getAdList(@PathVariable String pageno) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		// 페이징
		PagingDTO paging = new PagingDTO();
		int pagenum = Integer.parseInt(pageno);
		int contentnum = 9;
		int totalcount = 0;
		totalcount = admanageService.adTotalCount();
		paging.setTotalcount(totalcount);// 전체 게시글 지정
		paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
		paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
		paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
		paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
		paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
		paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
		paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로 정함
		List<AdmanageDTO> adList = new ArrayList();
		HashMap<String, Integer> hMap = new HashMap<>();
		int i = paging.getPagenum() * 9;
		int j = paging.getContentnum();
		hMap.put("pagenum", i);
		hMap.put("contentnum", j);
		
		adList = admanageService.getAdList(hMap);
		resultMap.put("adList", adList);
		resultMap.put("paging", paging);

		return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
	}
	// 노출광고 관리
		@GetMapping("/EnableAdManage/{pageno}")
		public ResponseEntity<HashMap<String, Object>> getAdEnableList(@PathVariable String pageno) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<>();
			// 페이징
			PagingDTO paging = new PagingDTO();
			int pagenum = Integer.parseInt(pageno);
			System.out.println("페이지넘버" + pagenum);
			int contentnum = 9;
			int totalcount = 0;
			totalcount = admanageService.adTotalCount();
			paging.setTotalcount(totalcount);// 전체 게시글 지정
			paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
			paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
			paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
			paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
			// log.info("Last block:"+paging.getTotalcount());
			paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
			paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
			paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로 정함
			List<AdmanageDTO> adEnableList = new ArrayList();
			HashMap<String, Integer> hMap = new HashMap<>();
			int i = paging.getPagenum() * 9;
			int j = paging.getContentnum();
			hMap.put("pagenum", i);
			hMap.put("contentnum", j);
			
			adEnableList = admanageService.getAdEnableList(hMap);
			
			resultMap.put("adList", adEnableList);
			resultMap.put("paging", paging);

			return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
		}
		// 비활성 광고 관리
		@GetMapping("/DisableAdManage/{pageno}")
		public ResponseEntity<HashMap<String, Object>> getAdDisableList(@PathVariable String pageno) throws Exception {
			HashMap<String, Object> resultMap = new HashMap<>();
			// 페이징
			PagingDTO paging = new PagingDTO();
			int pagenum = Integer.parseInt(pageno);
			System.out.println("페이지넘버" + pagenum);
			int contentnum = 9;
			int totalcount = 0;
			totalcount = admanageService.adTotalCount();
			paging.setTotalcount(totalcount);// 전체 게시글 지정
			paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
			paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
			paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
			paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
			// log.info("Last block:"+paging.getTotalcount());
			paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
			paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
			paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로 정함
			List<AdmanageDTO> adDisableList = new ArrayList();
			HashMap<String, Integer> hMap = new HashMap<>();
			int i = paging.getPagenum() * 9;
			int j = paging.getContentnum();
			hMap.put("pagenum", i);
			hMap.put("contentnum", j);
			
			adDisableList = admanageService.getAdDisableList(hMap);
			resultMap.put("adList", adDisableList);
			resultMap.put("paging", paging);
			
			return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
		}
		// 광고 등록
		@PostMapping("/adRegister")
		public ResponseEntity<String> adRegister(@RequestBody AdmanageDTO adDTO) throws Exception{
			System.out.println("광고등록");
			String datatype = adDTO.getAdImg().split(",")[0];
			System.out.println(datatype);
			switch (datatype) {//이미지 타입 체크
            case "data:image/jpeg;base64":
            	datatype = "jpeg";
                break;
            case "data:image/png;base64":
            	datatype = "png";
                break;
            case "data:image/gif;base64":
            	datatype = "gif";
                break;
            default://이미지 타입
            	datatype = "jpg";
                break;
			}
			String data = adDTO.getAdImg().split(",")[1];
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
			String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
			UUID uid = UUID.randomUUID();
			String fileName = uid + now;
			String realPath = "http://15.164.160.236:8080/imageUpload/ad/";
			String path = "/usr/local/tomcat/webapps/ROOT/imageUpload/ad/";
			String newFileName = fileName+"."+datatype;
			try {
				File filePath = new File(path);
				if (!filePath.isDirectory()) {
					filePath.mkdirs();
				}
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
				ImageIO.write(bufImg, datatype, new File(path+newFileName));
				adDTO.setAdImg(realPath+fileName+"."+datatype);
				adDTO.setAdChgdate(now);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int result = admanageService.insertAd(adDTO);
			
			if (result == 1) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
			}
		}
		// 상세 조회
		@GetMapping("/adDetail/{adNo}")
		public ResponseEntity<HashMap<String, Object>> getAdDetail(@PathVariable String adNo) throws Exception {
			AdmanageDTO adDTO = new AdmanageDTO();
			HashMap<String, Object> hMap = new HashMap<>();
			adDTO.setAdNo(adNo);
			adDTO = (AdmanageDTO) admanageService.getAdDetail(adDTO);
			hMap.put("adDTO", adDTO);
			return new ResponseEntity<HashMap<String, Object>>(hMap, HttpStatus.OK);
		}
		// 광고 수정
		// 광고 수정
		@CrossOrigin("*")
		@PutMapping("/adUpdate")
		public ResponseEntity<String> adUpdate(@RequestBody AdmanageDTO adDTO) throws Exception {
			String datatype = adDTO.getAdImg().split(",")[0];
			switch (datatype) {//이미지 타입 체크
            case "data:image/jpeg;base64":
            	datatype = "jpeg";
                break;
            case "data:image/png;base64":
            	datatype = "png";
                break;
            case "data:image/gif;base64":
            	datatype = "gif";
                break;
            default://이미지 타입
            	datatype = "jpg";
                break;
			}
			String data = adDTO.getAdImg().split(",")[1];
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
			String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
			UUID uid = UUID.randomUUID();
			String fileName = uid + now;
			String realPath = "http://15.164.160.236:8080/imageUpload/ad/";
			String path = "/usr/local/tomcat/webapps/ROOT/imageUpload/ad/";
			String newFileName = fileName+"."+datatype;
			
			try {
				File filePath = new File(path);
				if (!filePath.isDirectory()) {
					filePath.mkdirs();
				}
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
				ImageIO.write(bufImg, datatype, new File(path+newFileName));
				adDTO.setAdImg(realPath+fileName+"."+datatype);
				adDTO.setAdChgdate(now);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int result = admanageService.updateAd(adDTO);
			if (result == 1) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
			}
		}
		// 광고 활성화/비활성화
		@CrossOrigin("*")
		@PutMapping("/adEnDisable")
		public ResponseEntity<HashMap<String,Object>> adEnDisable(@RequestBody List<String> adNoList) throws Exception {
			int adNoListSize = adNoList.size()-1;
			if (adNoList.get(adNoListSize).toString().equals("disable")) {
				for (int i=0; i < adNoListSize; i++) {
					System.out.println(adNoList.get(i).toString());
					int result = admanageService.adEnDisable("0",adNoList.get(i).toString());
				}
			} else if (adNoList.get(adNoListSize).toString().equals("enable")) {
				for (int i=0; i < adNoListSize; i++) {
					System.out.println(adNoList.get(i).toString());
					int result = admanageService.adEnDisable("1",adNoList.get(i).toString());
				}
			}
			
			//int result = admanageService.updateAd(adDTO);
			
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.OK);
		}
		// 광고 검색
		@GetMapping("/adSearch/{adSearch}/{pageno}")
		public ResponseEntity<HashMap<String, Object>> getAdSearch(@PathVariable String adSearch, @PathVariable String pageno) throws Exception {
			System.out.println("검색 : " + adSearch);
			// 페이징
			int pagenum = Integer.parseInt(pageno);
			HashMap<String, Object> resultMap = new HashMap<>();
			PagingDTO paging = new PagingDTO();
			int contentnum = 9;
			int totalcount = 0;
			totalcount = admanageService.adTotalCount();
			paging.setTotalcount(totalcount);// 전체 게시글 지정
			paging.setPagenum(pagenum - 1);// 현재페이지를 페이지 객체에 지정한다 -1 해야 쿼리에서 사용가능
			paging.setContentnum(contentnum);// 한 페이지에 몇개 씩 게시글을 보여줄지 지정
			paging.setCurrentblock(pagenum);// 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정함
			paging.setLastblock(paging.getTotalcount());// 마지막 블록 번호를 전체 게시글 수를 통해 정함
			paging.prevnext(pagenum); // 현재 페이지 번호로 화살표를 나타낼지 정함
			paging.setStartPage(paging.getCurrentblock());// 시작페이지를 페이지 블록번호로 정함
			paging.setEndPage(paging.getLastblock(), paging.getCurrentblock());// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록번호로 정함
			HashMap<String, Object> hMap = new HashMap<>();
			Object i= paging.getPagenum() * 9;
			Object j = paging.getContentnum();
			hMap.put("adSearch", adSearch);
			hMap.put("pagenum", i);
			hMap.put("contentnum", j);
			
			List<AdmanageDTO> adSearchList = new ArrayList();
			adSearchList = admanageService.getAdSearchList(hMap);
			System.out.println(adSearchList.toString());
			resultMap.put("adSearchList", adSearchList);
			resultMap.put("paging", paging);
			
			return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
		}
		
		//메인 광고 랜덤 한개만 노출 , 전체 노출
		@GetMapping("/exposeAd")
		public ResponseEntity<HashMap<String,Object>> getExposeAd() throws Exception{
			List<AdmanageDTO> adExposeList = new ArrayList();
			AdmanageDTO aDTO = new AdmanageDTO();
			HashMap<String,Object> hMap = new HashMap<>();
			String adNo;
			String adActive = "1";
			adExposeList = admanageService.getAdExposeList(adActive);
			int adRandomExposeNo = (int) (Math.random()*adExposeList.size()); //0<= x <5  1<= x <2 
			aDTO = adExposeList.get(adRandomExposeNo);
			System.out.println("번호:"+adRandomExposeNo);
		    System.out.println(aDTO.getAdNo());
		    adNo = aDTO.getAdNo();
		    
			//메인 광고 노출 카운트 1 증가 하는 서비스 
			int result  = admanageService.updateMainAdExposeCountUp(adNo);
			//광고 노출 횟수가 0 이면 완료 컬럼
			//광고 노출 
			hMap.put("adExposeList",adExposeList);
			hMap.put("aDTO", aDTO);
			return new ResponseEntity<HashMap<String,Object>>(hMap,HttpStatus.OK);
		}
		
}