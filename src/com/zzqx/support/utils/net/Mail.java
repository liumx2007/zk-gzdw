package com.zzqx.support.utils.net;

import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.commons.lang.StringUtils;

import com.zzqx.support.utils.file.PropertiesHelper;

public class Mail {
	// 定义发件人、收件人、SMTP服务器、用户名、密码、主题、内容等
	private String displayName;
	private String to;
	private String cc;
	private String bcc;
	private String from;
	private String smtpServer;
	private String username;
	private String password;
	private String subject;
	private String content;
	private Map<String, String> paths = new HashMap<String, String>(); // 用于保存发送附件的文件名的集合
	private Map<String, File> files = new HashMap<String, File>(); // 用于保存发送附件的文件名的集合 

	/**
	 * 设置SMTP服务器地址
	 */
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	/**
	 * 设置发件人的地址
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 设置显示的名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 设置E-mail用户名
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * 设置E-mail密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置接收者
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * 设置抄送
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	/**
	 * 设置暗送
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * 设置主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 设置主体内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 该方法用于收集附件名
	 */
	public void addAttachfile(String fileName, String path) {
		paths.put(fileName, path);
	}
	
	public void addAttachfile(String fileName, File file) {
		files.put(fileName, file);
	}

	public Mail() {

	}

	/**
	 * 初始化SMTP服务器地址、发送者E-mail地址、用户名、密码、接收者、主题、内容
	 */
	public Mail(String smtpServer, String from, String displayName,
			String username, String password, String to, String cc, String bcc, String subject,
			String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.username = username;
		this.password = password;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 初始化SMTP服务器地址、发送者E-mail地址、接收者、主题、内容
	 */
	public Mail(String smtpServer, String from, String displayName, String to, String cc, String bcc,
			String subject, String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 发送邮件
	 */
	public HashMap<String, String> send() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", "success");
		String message = "邮件发送成功！";
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(username, password);  
            }
		});
		PropertiesHelper mailProps = new PropertiesHelper("mail");
		session.setDebug(mailProps.readBoolean("mail.debug"));
		Transport trans = null;
		try {
			Message msg = new MimeMessage(session);
			try {
				Address from_address = new InternetAddress(from, displayName);
				msg.setFrom(from_address);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String[] tos = StringUtils.isEmpty(to)?new String[0]:to.split(",");
			InternetAddress[] address1 = new InternetAddress[tos.length];
			for(int i = 0;i<tos.length;i++) {
				if(StringUtils.isEmpty(tos[i].trim())) continue;
				address1[i] = new InternetAddress(tos[i]);
			}
			String[] ccs = StringUtils.isEmpty(cc)?new String[0]:cc.split(",");
			InternetAddress[] address2 = new InternetAddress[ccs.length];
			for(int i = 0;i<ccs.length;i++) {
				if(StringUtils.isEmpty(ccs[i].trim())) continue;
				address2[i] = new InternetAddress(ccs[i]);
			}
			String[] bccs = StringUtils.isEmpty(bcc)?new String[0]:bcc.split(",");
			InternetAddress[] address3 = new InternetAddress[bccs.length];
			for(int i = 0;i<bccs.length;i++) {
				if(StringUtils.isEmpty(bccs[i].trim())) continue;
				address3[i] = new InternetAddress(bccs[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, address1);
			msg.setRecipients(Message.RecipientType.CC, address2);
			msg.setRecipients(Message.RecipientType.BCC, address3);
			msg.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content, "text/html;charset=utf-8");
			mp.addBodyPart(mbp);
			for(Map.Entry<String, String> entry : paths.entrySet()) {
				mbp = new MimeBodyPart();
				String filename = entry.getValue(); // 选择出每一个附件名
				FileDataSource fds = new FileDataSource(filename); // 得到数据源
				mbp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
				mbp.setFileName(MimeUtility.encodeWord(entry.getKey())); // 得到文件名同样至入BodyPart
				mp.addBodyPart(mbp);
			}
			paths.clear();
			for(Map.Entry<String, File> entry : files.entrySet()) {
				mbp = new MimeBodyPart();
				File file = entry.getValue(); // 选择出每一个附件名
				FileDataSource fds = new FileDataSource(file); // 得到数据源
				mbp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
				mbp.setFileName(MimeUtility.encodeWord(entry.getKey())); // 得到文件名同样至入BodyPart
				mp.addBodyPart(mbp);
			}
			files.clear();
			msg.setContent(mp); // Multipart加入到信件
			msg.setSentDate(new Date()); // 设置信件头的发送日期
			// 发送信件
			msg.saveChanges();
			trans = session.getTransport("smtp");
			trans.connect(smtpServer, username, password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();
		} catch (AuthenticationFailedException e) {
			map.put("state", "failed");
			message = "邮件发送失败！错误原因：" + "身份验证错误!";
			e.printStackTrace();
		} catch (MessagingException e) {
			message = "邮件发送失败！错误原因：" + e.getMessage();
			map.put("state", "failed");
			e.printStackTrace();
			Exception ex = null;
			if ((ex = e.getNextException()) != null) {
				ex.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(message);
		return map;
	}

}