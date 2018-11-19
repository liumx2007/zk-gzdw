package com.zzqx.support.framework.task;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.zzqx.mvc.SpringContext;
import com.zzqx.mvc.entity.Order;
import com.zzqx.mvc.entity.Picture;
import com.zzqx.mvc.service.OrderService;
import com.zzqx.mvc.service.PictureService;
import com.zzqx.support.utils.file.FileManager;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.SocketDataSender;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class PicturesTask extends BaseTask {
	
	private PictureService pictureService;
	private OrderService orderService;
	
	private PropertiesHelper props;
	private String relativeSavePath;
	private String absoluteSavePath;
	private String path;
	private long interval;
	private String[] ips;
	private SimpleDateFormat sdf;
	private File absoluteFile;
	private SmbFile smbDirectory;
	private SmbFile[] smbFiles;
	private Order order;
	private String fileName;
	private String subFileName;
	private String resizeFileName;
	private File sourcePictureFile;
	private File resizePictureFile;
	private File subPictureFile;
	private Picture picture;
	private SmbFile sf;

	public void beforeTask() {
		pictureService = (PictureService) SpringContext.getBean("pictureService");
		orderService = (OrderService) SpringContext.getBean("orderService");
		props = new PropertiesHelper("config");
		relativeSavePath = FileManager.getNormalPath("files/picture");
		absoluteSavePath = FileManager.getServerPath()+relativeSavePath;
		path = "smb://"+props.readValue("picture.share.username")+":"+props.readValue("picture.share.password")+
				"@"+props.readValueTrim("picture.share.ip")+"/"+props.readValue("picture.share.directory")+"/";
		Long intervalObject = props.readLong("picture.scan.interval");
		interval = intervalObject==null?2000:intervalObject;
		String ipsString = props.readValueTrim("picture.scan.notice.terminal");
		ips = ipsString==null?new String[0]:ipsString.split(",");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		absoluteFile = new File(absoluteSavePath);
		try {
			smbDirectory = new SmbFile(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void doTask() {
//		TimerTask timerTask = new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					if (absoluteFile == null || smbDirectory == null) return;
//					if (!absoluteFile.exists()) {
//						FileManager.makeDirectory(absoluteSavePath);
//					}
//					String dateString = sdf.format(new Date());
//					File file = new File(absoluteSavePath + dateString + "/");
//					try {
//						if (smbDirectory.exists() && smbDirectory.isDirectory()) {
//							smbFiles = smbDirectory.listFiles();
//							if (smbFiles.length > 0) {
//								order = orderService.getCurrent();
//								if (order != null) {
//									int pictureLength = 0;
//									for (SmbFile smbFile : smbFiles) {
//										sf = smbFile;
//										if (isPicture(FileManager.getSuffix(smbFile.getName()))) {
//											if (!file.exists()) {
//												FileManager.makeDirectory(absoluteSavePath + dateString + "/");
//											}
//											System.out.println("开始上传：" + smbFile.getName());
//											fileName = FileManager.getNewFileName(FileManager.getSuffix(smbFile.getName()));
//											subFileName = FileManager.getFileNameWithOutSuffix(fileName) + "_th.jpg";
//											resizeFileName = FileManager.getFileNameWithOutSuffix(fileName) + "_new.jpg";
////										String resizeFileName = fileName;
//											if (FileManager.smbGet(smbFile.getPath(), absoluteSavePath + dateString + "/" + fileName)) {
//												sourcePictureFile = new File(absoluteSavePath + dateString + "/" + fileName);
//												resizePictureFile = new File(absoluteSavePath + dateString + "/" + resizeFileName);
//												subPictureFile = new File(absoluteSavePath + dateString + "/" + subFileName);
//												boolean b = FileManager.resizeImage(
//															new BufferedInputStream(new FileInputStream(sourcePictureFile)),
//															new BufferedOutputStream(new FileOutputStream(resizePictureFile)), 1920, "jpg");
//												if (!b) continue;
//												FileManager.processImg(absoluteSavePath + dateString + "/" + fileName, absoluteSavePath + dateString + "/" + subFileName, "image/*");
//												pictureLength++;
//												picture = new Picture();
//												picture.setOrder(order);
//												picture.setAddTime(new Date());
//												picture.setFileName(fileName);
//												picture.setFilePath(relativeSavePath + dateString + "/" + resizeFileName);
//												picture.setSubFileName(subFileName);
//												picture.setSubFilePath(relativeSavePath + dateString + "/" + subFileName);
//												picture.setType(Picture.TYPE_CAMERA);
//
//												pictureService.saveOrUpdate(picture);
//												try {
////													sourcePictureFile.delete();
//													smbFile.delete();
//												} catch (Exception e) {
//													e.printStackTrace();
//													System.out.println("无法删除，文件正在使用！");
//												}
//												sf = null;
//												sourcePictureFile = null;
//												resizePictureFile = null;
//												subPictureFile = null;
//											}
//										}
//									}
//									if (pictureLength > 0) {
//										for (String ip : ips) {
//											SocketDataSender sender = new SocketDataSender();
//											sender.sendToTerminal(ip.trim(), props.readValueTrim("picture.scan.notice.message.scan"));
//										}
//									}
//								} else {
//									System.out.println("未设置当前参观！");
//								}
//							}
//						}
//					} catch (SmbException e) {
//						e.printStackTrace();
//					}
//					System.gc();
//					System.gc();
//				} catch (Exception e) {
//					System.out.println("出现异常！");
//					e.printStackTrace();
//					if(sf != null) {
//						try {
//							sf.delete();
//						} catch (SmbException e1) {
//							e1.printStackTrace();
//						}
//					}
//					System.out.println(sourcePictureFile == null);
//					if(sourcePictureFile != null) {
//						System.out.println(sourcePictureFile.delete());
//					}
//					if(resizePictureFile != null) {
//						resizePictureFile.delete();
//					}
//					if(subPictureFile != null) {
//						subPictureFile.delete();
//					}
//					sf = null;
//					sourcePictureFile = null;
//					resizePictureFile = null;
//					subPictureFile = null;
//				}
//			}
//		};
//		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
//		newScheduledThreadPool.scheduleWithFixedDelay(timerTask,0,interval,TimeUnit.MILLISECONDS);
	}

	public void afterTask() {}

	public void stopTask() {}
	
	private boolean isPicture(String suffix) {
		return ".png".equalsIgnoreCase(suffix) 
				|| ".jpg".equalsIgnoreCase(suffix)
				|| ".jpeg".equalsIgnoreCase(suffix)
				|| ".gif".equalsIgnoreCase(suffix);
	}
}
