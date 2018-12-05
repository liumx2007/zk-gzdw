package com.zzqx.mvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Mail;

@Controller
@RequestMapping(value = "/mail")
public class MailController extends BaseController {
	
	@ResponseBody
	@RequestMapping("/sendFile")
	public String sendFile(@RequestParam(required = false) MultipartFile file, HttpServletRequest request) {
		System.out.println("开始发送邮件...");
		Mail mail = new Mail();
		PropertiesHelper props = new PropertiesHelper("config");
		mail.setSmtpServer(props.readValue("mail.smtp"));
		mail.setUserName(props.readValue("mail.username"));
		mail.setPassword(props.readValue("mail.password"));
		mail.setFrom(props.readValue("mail.from"));
		mail.setDisplayName(props.readValue("mail.name"));
		mail.setTo("449303934@qq.com");
		mail.setSubject("vbcvbcvb��");
		mail.setContent("斯蒂芬是否 ");
		String tempPath = request.getSession().getServletContext().getRealPath("\\temp");
		FileManager.makeDirectory(tempPath);
		String realPath = tempPath+"\\"+FileManager.getNewFileName("png");
		if(file != null && !file.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			mail.addAttachfile(file.getOriginalFilename(), new File(realPath));
		}
		String state = mail.send().get("state");
		FileManager.deleteFile(realPath);
		return state;
	}
	
	@ResponseBody
	@RequestMapping("/sendByte")
	public String sendByte(String to, String cc, String bcc, 
			String subject, String content, HttpServletRequest request) {
		try {
			to = StringUtils.isNotBlank(to)?URLDecoder.decode(to, "utf-8"):"";
			cc = StringUtils.isNotBlank(cc)?URLDecoder.decode(cc, "utf-8"):"";
			bcc = StringUtils.isNotBlank(bcc)?URLDecoder.decode(bcc, "utf-8"):"";
			subject = StringUtils.isNotBlank(subject)?URLDecoder.decode(subject, "utf-8"):"";
			content = StringUtils.isNotBlank(content)?URLDecoder.decode(content, "utf-8"):"";
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println("正在给"+to+"发送邮件...");
		Mail mail = new Mail();
		PropertiesHelper props = new PropertiesHelper("config");
		mail.setSmtpServer(props.readValue("mail.smtp"));
		mail.setUserName(props.readValue("mail.username"));
		mail.setPassword(props.readValue("mail.password"));
		mail.setFrom(props.readValue("mail.from"));
		mail.setDisplayName(props.readValue("mail.name"));
		mail.setTo(to);
		mail.setCc(cc);
		mail.setBcc(bcc);
		mail.setSubject(subject);
		mail.setContent(content);
		String tempPath = request.getSession().getServletContext().getRealPath("\\temp");
		FileManager.makeDirectory(tempPath);
		String realPath = tempPath+"\\"+FileManager.getNewFileName("png");
		File file = new File(realPath);
		FileOutputStream outputStream;
		BufferedInputStream inputStream;
		try {
			byte [] bytes = new byte[1024];
			int v;
			inputStream = new BufferedInputStream(request.getInputStream());
			outputStream = new FileOutputStream(file);
			while((v=inputStream.read(bytes)) != -1){
				outputStream.write(bytes, 0, v);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mail.addAttachfile("照片.png", file);
		String state = mail.send().get("state");
		FileManager.deleteFile(realPath);
		return state;
	}
}
