package com.zzqx.support.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zzqx.mvc.SpringContext;
import com.zzqx.mvc.entity.Item;
import com.zzqx.mvc.service.ItemService;
import com.zzqx.Global;
import com.zzqx.support.framework.task.BaseTask;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.security.DESUtils;

import SuperDog.Dog;
import SuperDog.DogStatus;

public class Application implements ApplicationListener<ContextRefreshedEvent>{
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		folderService = (FolderService) SpringContext.getBean("folderService");
		PropertiesHelper props = new PropertiesHelper("config");
		if(!Global.inited) {
			boolean authorized = checkAuthorized() || trial();
			if(authorized) {
				flashSocketSecurity();
				startTasks();
			} else {
				System.err.println("***************************软件未授权，限制使用！***************************");
			}
			Global.cros = props.readValueTrim("global.cros");
			Global.inited = true;
		}
	}
	
	private void flashSocketSecurity(){
		new Thread(()->{
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(843);
				while(true){
					try {
						String xml = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
						Socket socket = serverSocket.accept();
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
						PrintWriter pw = new PrintWriter(socket.getOutputStream());
						char[] by = new char[22];
						br.read(by, 0, 22);
						String s = new String(by);
						if (s.equals("<policy-file-request/>")) {
							pw.print(xml);
							pw.flush();
							br.close();
							pw.close();
							socket.close();
							System.out.println("已经向" + socket.getRemoteSocketAddress() + "发送策略文件");
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean checkAuthorized() {
//		System.out.println("授权校验中...");
		try {
			Dog curDog = new Dog(Dog.DOG_DEFAULT_FID);
			String vendorCode = "OcjtorlNlEwPmJUH+ugtna35hWJCfELqAIWFRAhNJeWnx8oFq3J9IfaGQgBzvXUU/LSSNK6Ux4Rfutj1FLqL12gJFRzD"
				      + "GgESxxeFOuBjzDRfCRN64O//KEUTtH8WLu5xMD62misdMQ5FTu5LcUVyQuGhsnsXaFDKnbGgyi3bJcREe+7KN/Wj3uH"
				      + "P/WfYPdUkx+QqDUgfaNME5v8WXxFjBq2aR1V5CqnrxFJzFTEF5Pt870pQwXpPOWXmJ7dciDMuDgY4RcU0lLjro3rAcx"
				      + "XJuvo3YfFEj0Wrk285bcZP3wyh7QKmZNnQOJO6DgKC4P6vc7RrQAeWQCj05UMUBDW67w+i46vtqvyfECXE5yw+doyPE"
				      + "V1EBjf0wrkrU0gPweTEpPUvogFLWWSvSbv44BSSCGCsxiK5t93Gw/oCqd70cdb4f38ti0DBOwCPVzL/IPIUAMEYrlz3"
				      + "8VV5h4gk8FoWV9tjRqRyVuO9GvhMrAXpAhlENXDV/HA677KFHmgi9VgMkoFxi4w3ePn+rYiMbe+IceeOgqI7oNs5JKH"
				      + "JXcyWJTavZ1AWGNGsJ4QChULr3DksTLml6+dEQRXH29lzYinB0CrLjlpqQ7Ygy0uQOFj7mELxCYO8V+QMviArNd01+K"
				      + "1Z4wPK48UaXEwytWG3iDkiKDP0t2NxOsF916OQUj1boLi0Aq47nWRYjSkxXvRdOGmM5SnOMA8R3V8MWYnTAgZAbk2Lw"
				      + "YlcXjdiVl4SaYOL1RDAC2MPp+lbwBqpbW5oOZDGW/z8a6gKRlNmptqHxHfpo5T1c7OHvTZLEwwWKCY68B4XsOETiXOB1"
				      + "7DhpIiS7Gxh5pgMExHpLFB37XBxCd2FbCdRDXK0y5XD6gE3F/G+fsBoVAZGh3BMgOnekPlUhb3ErSYPZg4CR6+c23D3G"
				      + "dGl0EZp0JExk/OMbCSzDgfpqZfE3THAofVXaWb5PXcFh5/SmZJY49SOtDb/yF9eakMgKQ==";
			curDog.login(vendorCode);
			int status = curDog.getLastError();
			boolean b = status == DogStatus.DOG_STATUS_OK;
			if(b) {
				curDog.login(vendorCode);
				String info = curDog.getSessionInfo(Dog.DOG_KEYINFO);
				String dogId = info.substring(info.indexOf("<dogid>")+7, info.indexOf("</dogid>"));
				b = Global.dogId.equals(dogId);
			}
			Global.authorizeStatus = b?1:0;
			if(!b) {
//				System.out.println("软件未授权！");
			} else {
//				System.out.println("(*^__^*)");
//				System.out.println("软件已授权，欢迎使用！");
			}
			return b;
		} catch(Error e) {
			Global.authorizeStatus = 0;
//			System.out.println("软件环境配置错误，无法检测授权信息！");
			return false;
		}
	}
	
	private boolean trial() {
//		System.out.println("验证试用许可信息...");
		ItemService itemService = (ItemService) SpringContext.getBean("itemService");
		List<Item> items = itemService.find(Restrictions.eq("item", "n"));
		if(items.size() > 0) {
			String eKey = items.get(0).getValue();
			try {
				String dKey = DESUtils.decode(eKey);
				if(StringHelper.isNumeric(dKey)) {
					int key = Integer.valueOf(dKey);
					Global.residue = key;
					if(key <= 0) {
//						System.out.println("试用许可到期！");
						return false;
					}
					Timer timer = new Timer();  
			        timer.schedule(new TimerTask() {
						@Override
						public void run() {
							ItemService itemService = (ItemService) SpringContext.getBean("itemService");
							List<Item> items = itemService.find(Restrictions.eq("item", "n"));
							if(items.size()>0) {
								Item item = items.get(0);
								String eKey = item.getValue();
								try {
									String dKey = DESUtils.decode(eKey);
									if(StringHelper.isNumeric(dKey)) {
										int key = Integer.valueOf(dKey);
										Global.residue = key;
										if(key <= 0) {
											Global.authorizeStatus = 0;
										} else {
											item.setValue(DESUtils.encode((key-1)+""));
											itemService.saveOrUpdate(item);
											Global.authorizeStatus = 2;
										}
									} else {
										Global.authorizeStatus = 0;
										timer.cancel();
									}
								} catch (Exception e) {
									Global.authorizeStatus = 0;
									timer.cancel();
								}
							} else {
								Global.authorizeStatus = 0;
								timer.cancel();
							}
						}
					}, 0, 60000);
				} else {
					Global.authorizeStatus = 0;
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Global.authorizeStatus = 0;
				return false;
			}
		}
		Global.authorizeStatus = 2;
//		System.out.println("正在试用...");
		return true;
	}
	
	private void startTasks() {
		PropertiesHelper props = new PropertiesHelper("task");
		Map<String, String> map = props.getProperties();
		for(Map.Entry<String, String> entry : map.entrySet()) {
			try {
				BaseTask baseTask = (BaseTask) Class.forName(entry.getKey()).newInstance();
				baseTask.run();
				Global.tasks.put(Class.forName(entry.getKey()), baseTask);
				System.out.println("线程已启动："+Class.forName(entry.getKey()).getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
