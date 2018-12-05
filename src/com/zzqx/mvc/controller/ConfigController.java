package com.zzqx.mvc.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;

@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BaseController {

	@OpenAccess
	@ResponseBody
	@RequestMapping("get")
	public String get(String fileName) {
		String xmlPath = FileManager.getResource(FileManager.class, "//xml//"+fileName);
		if(StringHelper.isNotBlank(xmlPath)) {
			File xmlFile = new File(xmlPath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(xmlFile);
				return FileManager.doc2FormatString(document);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return defaultXml("error :"+fileName);
			} catch (SAXException e) {
				e.printStackTrace();
				return defaultXml("error :"+fileName);
			} catch (IOException e) {
				e.printStackTrace();
				return defaultXml("error :"+fileName);
			}
		} else {
			return defaultXml("File not found :"+fileName);
		}
	}
	
	private String defaultXml(String message) {
		return
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
        "<config>\n"+
        	"\t<message>"+message+"</message>\n"+
        "</config>";
	}
}
