
package com.zzqx.support.framework.task.timerTask;

import cn.hutool.http.HttpUtil;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.entity.Environment;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.service.EnvironmentService;
import com.zzqx.mvc.service.InteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EnvironmentTimerTask {
	@Autowired
	private EnvironmentService environmentService;
	@Autowired
	private InteractionLogService interactionLogService;

	/**
	 * 采集环境数据
	 */
	public void getEnvironmentData() {
		Socket socket;
		PrintWriter out;
		InputStream inputStream;
		try {
			socket = new Socket("192.168.0.1", 9999);
			inputStream = socket.getInputStream();
			DataInputStream input = new DataInputStream(inputStream);
			byte[] b = new byte[1024];
			StringBuffer sb = new StringBuffer();
			do {
				int length = input.read(b);
				String Msg = new String(b, 0, length, "gb2312");
				sb.append(Msg);
			} while (input.read() == -1);
			System.out.print(sb.toString());
			socket.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 上传环境数据
	 */
	public void postEnvironmentData() {
		Environment environment = environmentService.getUpdateOne();
		if (environment != null) {
			try {
				CountInfo countInfo = new CountInfo();
				String url = countInfo.POST_ENVIRONMENT_DATA
						+ "&temperature=" + environment.getTemperature()
						+ "&humidity=" + environment.getHumidity()
						+ "&pm=" + environment.getPm()
						+ "&createTime=" + environment.getCreateTime().getTime()
						+ "&id=" + environment.getId()
						+ "&area=" + environment.getArea();
				String str = HttpUtil.get(url, 3000);
				if (str.contains("成功")) {
					environment.setStatus(1);
					environmentService.update(environment);
					System.out.println("环境数据：" + environment.getTemperature() + "上传完成");
				}
			} catch (Exception e) {
				System.out.println("环境数据：" + environment.getTemperature() + "上传失败");
			}
		} else {
			System.out.print("没有未上传的环境数据。。。。。");
		}
	}

	/**
	 * 上传交互数据
	 */
	public void postInteractionData() {
		InteractionLog interactionLog = interactionLogService.getUpdateOne();
		if (interactionLog != null) {
			try {
				CountInfo countInfo = new CountInfo();
				String url = countInfo.POST_INTERACTIONLOG_DATA
						+ "&id=" + interactionLog.getId()
						+ "&interactionId=" + interactionLog.getInteractionId()
						+ "&clickTime=" + interactionLog.getClickTime().getTime()
						+ "&sessionBusiness=" + interactionLog.getSessionBusiness()
						+ "&sessionInteract=" + interactionLog.getSessionInteract()
						+ "&folderType=" + interactionLog.getFolderType();
				String str = HttpUtil.get(url, 3000);
				if (str.contains("成功")) {
					interactionLog.setStatus(1);
					interactionLogService.update(interactionLog);
					System.out.println("交互数据：" + interactionLog.getId() + "上传完成");
				}
			} catch (Exception e) {
				System.out.println("交互数据：" + interactionLog.getId() + "上传失败");
			}
		} else {
			System.out.println("没有未上传的交互数据。。。。。");
		}
	}
}
