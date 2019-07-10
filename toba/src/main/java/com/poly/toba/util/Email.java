package com.poly.toba.util;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.poly.toba.model.EmailDTO;
import com.poly.toba.model.UserDTO;

@Repository
public class Email {
	private String subject;
	private String content;
	private String regdate;
	private String reciver;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
        this.subject = subject;
    }
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public String setContents(EmailDTO eDTO){
    	String contents = "";
    	contents += "<html>";
    	contents += "<title>";
    	contents += "</title>";
    	contents += "<body>";
		contents += "<div class='email'>";
		contents += "<div class='texts' style='color: rgb(97, 106, 116); line-height: 22px; padding-left: 10px; font-family: 돋움, Dotum; font-size: 12px;'><b>인증번호 : </b>"+eDTO.getEmailKey()+"</div>";
		contents += "</div>";
    	contents += "</body>";
    	contents += "</html>";
    	return contents;
    }
}
