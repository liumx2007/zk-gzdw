package com.zzqx.mvc.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

import com.zzqx.mvc.entity.Group;
import com.zzqx.mvc.entity.Terminal;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.entity.Content;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.mvc.service.ContentService;
import com.zzqx.mvc.service.GroupService;
import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.framework.mina.MinaManager;
import com.zzqx.support.framework.mina.MinaSession;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.machine.IPC;
import com.zzqx.support.utils.machine.TerminalManager;
import com.zzqx.support.utils.net.Com;
import com.zzqx.support.utils.net.SocketDataSender;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@OpenAccess
@Controller
@RequestMapping(value = "/cmd")
public class CommondController extends BaseController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private TerminalService terminalService;
	@Autowired
	private ContentService contentService;
	
	@ResponseBody
	@RequestMapping("open")
	public String open(HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "正在开馆...");
		List<Terminal> list = terminalService.getAll();
		AsyncContext context = request.startAsync();
		context.start(() -> {
			System.out.println("开启工控设备...");
			PropertiesHelper props = new PropertiesHelper("config");
			List<String> codes = props.readList("con.bat.oc", ",");
			codes.stream().distinct().forEach(code->{
				IPC mac = IPC.getInstance();
				mac.send(code, IPC.CMD_OPEN);
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("开启所有主机...");
			List<String> macs = list.stream().map(Terminal::getMac).collect(Collectors.toList());
			TerminalManager.poweronBatch(macs);
		});
		context.complete();
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("close")
	public String close(HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "正在闭馆...");
		List<Terminal> list = terminalService.getAll();
		AsyncContext context = request.startAsync();
		context.start(() -> {
			System.out.println("关闭所有主机...");
			PropertiesHelper props = new PropertiesHelper("config");
			List<String> ipOrCodeNames = props.readList("terminal.filter.close", ",");
			List<String> macs = list.stream().filter(terminal->ipOrCodeNames.indexOf(terminal.getIp())==-1&&ipOrCodeNames.indexOf(terminal.getCodeName())==-1).map(Terminal::getMac).collect(Collectors.toList());
			TerminalManager.poweroffBatch(macs);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("关闭工控设备...");
			List<String> codes = props.readList("con.bat.oc", ",");
			codes.stream().distinct().forEach(code->{
				IPC mac = IPC.getInstance();
				mac.send(code, IPC.CMD_CLOSE);
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
		context.complete();
		return message.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("send")
	public String send(String toTCPServer,String toUDPServer, String con, String flash, String client, String play, String com, String pc, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "发送成功！");
		if(toTCPServer != null && toTCPServer != "") {//发送给Socket服务器
			Stream.of(toTCPServer).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd->cmd.split(",").length >= 3).forEach(cmd -> {
				String ip = cmd.split(",")[0];
				String port = cmd.split(",")[1];
				String msg = "";
				for(int i = 2;i<cmd.split(",").length;i++) {
					if(i == 2) {
						msg = cmd.split(",")[i];
					} else {
						msg += ","+cmd.split(",")[i];
					}
				}
				SocketDataSender sender = new SocketDataSender();
				if(StringHelper.isNumeric(port)) {
					sender.sendToTCPServer(ip, Integer.valueOf(port), msg);
				}
			});
		}
		if(toUDPServer != null && toUDPServer != "") {//发送给Sokcet服务器
			Stream.of(toUDPServer).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
					.filter(cmd->cmd.split(",").length >= 3).forEach(cmd -> {
				String ip = cmd.split(",")[0];
				String port = cmd.split(",")[1];
				String msg = "";
				for(int i = 2;i<cmd.split(",").length;i++) {
					if(i == 2) {
						msg = cmd.split(",")[i];
					} else {
						msg += ","+cmd.split(",")[i];
					}
				}
				SocketDataSender sender = new SocketDataSender();
				if(StringHelper.isNumeric(port)) {
					sender.sendToUDPServer(ip, Integer.valueOf(port), msg);
				}
			});
		}
		if(con != null && con != "") {//工控指令
			Stream.of(con).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd->cmd.contains(",")).forEach(cmd -> {
				String deviceCode = cmd.substring(0, cmd.indexOf(","));
				String operation = cmd.substring(cmd.indexOf(",")+1, cmd.length());
				IPC.getInstance().send(deviceCode, operation);
			});
		}
		if(flash != null && flash != "") {//转发
			Stream.of(flash).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd -> cmd.contains(",")).forEach(cmd -> {
					String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
					String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
					SocketDataSender sender = new SocketDataSender();
					sender.sendToTerminal(ipOrCodeName, msg);
				});
		}
		if(client != null && client != "") {//客户端控制
//			String test = "自己,VOL_MAX;99,VOL_MAX";
			Stream.of(client).flatMap(cmds-> Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd -> cmd.contains(",")).forEach(cmd -> {
					String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
					String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
					SocketDataSender sender = new SocketDataSender();
					sender.sendToClient(ipOrCodeName, msg);
				});
//			String ipOrCodeName = client[0];
//			String msg = client[1];
//			SocketDataSender socketDataSender = new SocketDataSender();
//			socketDataSender.sendToClient(ipOrCodeName,msg);
		}
		if(play != null && play != "") {//播放指定内容
			Stream.of(play).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
			.filter(cmd -> cmd.contains(",")).forEach(cmd -> {
				String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
				String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length()).trim();
				String model = "";
				String action = "";
				//判断msg中是否出现"_"
				if(msg.contains("_")) {
					//取"_"前面所有的字符
					String newMsg = msg.substring(0, msg.indexOf("_"));
					//取"_"后面所有的字符
					String m = msg.substring(msg.indexOf("_")+1, msg.length()).trim();
					int li = m.indexOf("{");
					int ri = m.lastIndexOf("}");
					if(li < ri) {
						model = m.substring(0, li);
						action = m.substring(li+1, ri);
						action = action.replace("$", "&");
						action = action.replace("@", ";");
					} else {
						model = m;
					}
					msg = newMsg;
				}
				if(StringHelper.isNumeric(msg)) {
					List<Content> contents = contentService.find(Restrictions.eq("serialNumber", Integer.valueOf(msg)));
					if(contents.size() > 0) {
						Content content = contents.get(0);
						content.setTerminalContents(null);
						JsonConfig jsonConfig = new JsonConfig();
						jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
						String contentString = "content:"+JSONObject.fromObject(content, jsonConfig).toString();
						if(StringHelper.isNotBlank(model)) {
							contentString += "model:"+model;
						}
						if(StringHelper.isNotBlank(action)) {
							contentString += "action:"+action;
						}
						SocketDataSender sender = new SocketDataSender();
						sender.sendToClient(ipOrCodeName, contentString);
					}
				} else {
					SocketDataSender sender = new SocketDataSender();
					sender.sendToClient(ipOrCodeName, msg.toLowerCase());
				}
			});
		}
		if(com != null && com != "") {//向串口写数据
			Stream.of(com).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd -> cmd.split(",").length == 6).forEach(cmd -> {
					String[] config = cmd.split(",");
					String serialName = config[0];
					int rate = Integer.valueOf(config[1]);
					int databits = Integer.valueOf(config[2]);
					int stopbits = Integer.valueOf(config[3]);
					int parity = Integer.valueOf(config[4]);
					Com _com = new Com(serialName, rate, databits, stopbits, parity);
					_com.open();
					_com.send(StringHelper.hex2byte(config[5]));
					_com.close();
				});
		}
		if(pc != null && pc != "") {//PC控制
			List<String> openIpList = new ArrayList<>();
			List<String> closeIpList = new ArrayList<>();
			List<String> openCodeNameList = new ArrayList<>();
			List<String> closeCodeNameList = new ArrayList<>();
			Stream.of(pc).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
				.filter(cmd -> cmd.contains(",")).forEach(cmd -> {
					String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
					String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
					if("1".equals(msg.trim())) {
						if(StringHelper.isIp(ipOrCodeName)) {
							openIpList.add(ipOrCodeName);
						} else {
							openCodeNameList.add(ipOrCodeName);
						}
					}
					if("0".equals(msg.trim())) {
						if(StringHelper.isIp(ipOrCodeName)) {
							closeIpList.add(ipOrCodeName);
						} else {
							closeCodeNameList.add(ipOrCodeName);
						}
					}
				});
			String closestr = closeCodeNameList.stream().filter(StringHelper::isNotBlank).map(codeName->"'"+codeName+"'").collect(Collectors.joining(","));
			List<Terminal> terminals1 = new ArrayList<>();
			if(StringHelper.isNotBlank(closestr)) {
				terminals1 = terminalService.createQuery("from Terminal where codeName in ("+closestr+")").list();
			}
			String openstr1 = openIpList.stream().filter(StringHelper::isNotBlank).map(ip->"'"+ip+"'").collect(Collectors.joining(","));
			String openstr2 = openCodeNameList.stream().filter(StringHelper::isNotBlank).map(codeName->"'"+codeName+"'").collect(Collectors.joining(","));
			List<Terminal> terminals2 = new ArrayList<>();
			List<Terminal> terminals3 = new ArrayList<>();
			if(StringHelper.isNotBlank(openstr1)) {
				terminals2 = terminalService.createQuery("from Terminal where ip in ("+openstr1+")").list();
			}
			if(StringHelper.isNotBlank(openstr2)) {
				terminals3 = terminalService.createQuery("from Terminal where codeName in ("+openstr2+")").list();
			}
			terminals1.forEach(terminal->closeIpList.add(terminal.getMac()));
			Map<String, List<String>> groupTerminals = new HashMap<>();
			Stream.concat(terminals2.stream(), terminals3.stream()).forEach(terminal->{
				Group group = terminal.getGroup();
				if(group!=null) {
					List<String> macs = new ArrayList<>();
					String key = StringHelper.isNotBlank(group.getServerNodeMac())?group.getServerNodeMac():"default";
					if(groupTerminals.containsKey(key)) {
						macs = groupTerminals.get(key);
					} else {
						groupTerminals.put(key, macs);
					}
					macs.add(terminal.getMac());
				}
			});
			AsyncContext context = request.startAsync();
			context.start(()->{
				TerminalManager.poweroffBatch(closeIpList);
				for (Map.Entry<String, List<String>> entry : groupTerminals.entrySet()) {
					String key = entry.getKey();
					List<String> macs = entry.getValue();
					if("default".equals(key)) {
						TerminalManager.poweronBatch(macs);
					} else {
						MinaSession minaSession = MinaManager.getByMac(key);
						if(minaSession != null) {
							SocketDataSender sender = new SocketDataSender();
							sender.send(minaSession.getIoSession(), "poweron:"+macs.stream().collect(Collectors.joining(",")));
						} else {
							TerminalManager.poweronBatch(macs);
						}
					}
				}
			});
			context.complete();
		}
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("sendQueue")
	public String sendQueue(String[] message) {
		ReturnMessage m = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "处理成功！");
		if(message != null && message.length > 0) {
			for(String msg : message) {
				for(String cmd : msg.split(";")) {
					SocketDataSender sender = new SocketDataSender();
					long time = 500;
					String[] singleCmd = cmd.split(",");
					if(singleCmd.length == 4) {
						if(StringHelper.isNumeric(singleCmd[3])) {
							time = Long.valueOf(singleCmd[3]);
						}
					}
					if(singleCmd.length > 2) {
						if("toTCPServer".equalsIgnoreCase(singleCmd[0])) {
							if(singleCmd.length > 3) {
								if(StringHelper.isNumeric(singleCmd[2])) {
									sender.sendToTCPServer(singleCmd[1], Integer.valueOf(singleCmd[2]), singleCmd[3]);
								}
							}
						} else if("toUDPServer".equalsIgnoreCase(singleCmd[0])) {
							if(singleCmd.length > 3) {
								if(StringHelper.isNumeric(singleCmd[2])) {
									sender.sendToUDPServer(singleCmd[1], Integer.valueOf(singleCmd[2]), singleCmd[3]);
								}
							}
						} else if("con".equalsIgnoreCase(singleCmd[0])) {
							IPC.getInstance().send(singleCmd[1], singleCmd[2]);
						} else if("flash".equalsIgnoreCase(singleCmd[0])) {
							sender.sendToTerminal(singleCmd[1], singleCmd[2]);
						} else if("client".equalsIgnoreCase(singleCmd[0])) {
							sender.sendToClient(singleCmd[1], singleCmd[2]);
						} else if("com".equalsIgnoreCase(singleCmd[0])) {
							if(singleCmd.length > 7 && StringHelper.isNumeric(singleCmd[2]) && StringHelper.isNumeric(singleCmd[3])
									&& StringHelper.isNumeric(singleCmd[4]) && StringHelper.isNumeric(singleCmd[5])) {
								Com _com = new Com(singleCmd[1], Integer.valueOf(singleCmd[2]), Integer.valueOf(singleCmd[3]), Integer.valueOf(singleCmd[4]), Integer.valueOf(singleCmd[5]));
								_com.open();
								_com.send(StringHelper.hex2byte(singleCmd[6]));
								_com.close();
							}
						} else if("pc".equalsIgnoreCase(singleCmd[0])) {
							if(StringHelper.isIp(singleCmd[1])) {
								if("1".equals(singleCmd[2])) {
									List<Terminal> terminals = terminalService.find(Restrictions.eq("ip", singleCmd[1]));
									if(terminals.size() > 0) {
										try {
											TerminalManager.poweron(terminals.get(0).getMac());
										} catch (UnknownHostException e) {
											e.printStackTrace();
										}
									}
								} else if("0".equals(singleCmd[2])) {
									try {
										TerminalManager.poweroff(singleCmd[1]);
									} catch (UnknownHostException e) {
										e.printStackTrace();
									}
								}
							} else {
								List<Terminal> terminals = terminalService.find(Restrictions.eq("codeName", singleCmd[1]));
								if("1".equals(singleCmd[2])) {
									if(terminals.size() > 0) {
										try {
											TerminalManager.poweron(terminals.get(0).getMac());
										} catch (UnknownHostException e) {
											e.printStackTrace();
										}
									}
								} else if("0".equals(singleCmd[2])) {
									if(terminals.size() > 0) {
										try {
											
											TerminalManager.poweroff(terminals.get(0).getIp());
										} catch (UnknownHostException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
						try {
							TimeUnit.MILLISECONDS.sleep(time);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return m.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("poweron")
	public String poweron(String id, String codeName, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage();
		if(id == null && codeName == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("启动失败！");
			return message.toString();
		}
		id = StringHelper.isEmpty(id)?"":id;
		codeName = StringHelper.isEmpty(codeName)?"":codeName;
		List<Terminal> terminals1 = terminalService.get(id.split(","));
		String str = Stream.of(codeName.split(",")).filter(StringHelper::isNotBlank).map(name->"'"+name+"'").collect(Collectors.joining(","));
		List<Terminal> terminals2 = new ArrayList<>();
		if(StringHelper.isNotBlank(str)) {
			terminals2 = terminalService.createQuery("from Terminal where codeName in ("+str+")").list();
		}
		Map<String, List<String>> groupTerminals = new HashMap<>();
		Stream.concat(terminals1.stream(), terminals2.stream()).forEach(terminal->{
			Group group = terminal.getGroup();
			if(group!=null) {
				List<String> macs = new ArrayList<>();
				String key = StringHelper.isNotBlank(group.getServerNodeMac())?group.getServerNodeMac():"default";
				if(groupTerminals.containsKey(key)) {
					macs = groupTerminals.get(key);
				} else {
					groupTerminals.put(key, macs);
				}
				macs.add(terminal.getMac());
			}
		});
		AsyncContext context = request.startAsync();
		context.start(()-> {
			for (Map.Entry<String, List<String>> entry : groupTerminals.entrySet()) {
				String key = entry.getKey();
				List<String> macs = entry.getValue();
				if("default".equals(key)) {
					TerminalManager.poweronBatch(macs);
				} else {
					MinaSession minaSession = MinaManager.getByMac(key);
					if(minaSession != null) {
						SocketDataSender sender = new SocketDataSender();
						sender.send(minaSession.getIoSession(), "poweron:"+macs.stream().collect(Collectors.joining(",")));
					} else {
						TerminalManager.poweronBatch(macs);
					}
				}
			}
		});
		context.complete();
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("正在启动...");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("poweronAll")
	public String poweronAll(String groupId, HttpServletRequest request) {
		List<Terminal> list;
		if(StringHelper.isNotBlank(groupId)) {
			Group group = groupService.getById(groupId);
			list = terminalService.getByGroup(group);
		} else {
			list = terminalService.getAll();
		}
		Map<String, List<String>> groupTerminals = new HashMap<>();
		list.forEach(terminal->{
			Group group = terminal.getGroup();
			if(group!=null) {
				List<String> macs = new ArrayList<>();
				String key = StringHelper.isNotBlank(group.getServerNodeMac())?group.getServerNodeMac():"default";
				if(groupTerminals.containsKey(key)) {
					macs = groupTerminals.get(key);
				} else {
					groupTerminals.put(key, macs);
				}
				macs.add(terminal.getMac());
			}
		});
		AsyncContext context = request.startAsync();
		context.start(()-> {
			for (Map.Entry<String, List<String>> entry : groupTerminals.entrySet()) {
				String key = entry.getKey();
				List<String> macs = entry.getValue();
				if("default".equals(key)) {
					TerminalManager.poweronBatch(macs);
				} else {
					MinaSession minaSession = MinaManager.getByMac(key);
					if(minaSession != null) {
						SocketDataSender sender = new SocketDataSender();
						sender.send(minaSession.getIoSession(), "poweron:"+macs.stream().collect(Collectors.joining(",")));
					} else {
						TerminalManager.poweronBatch(macs);
					}
				}
			}
		});
		context.complete();
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "正在启动...").toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("reboot")
	public String reboot(String id, String codeName, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage();
		if(id == null && codeName == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("重启失败！");
			return message.toString();
		}
		id = StringHelper.isEmpty(id)?"":id;
		codeName = StringHelper.isEmpty(codeName)?"":codeName;
		List<Terminal> terminals1 = terminalService.get(id.split(","));
		String str = Stream.of(codeName.split(",")).filter(StringHelper::isNotBlank).map(name->"'"+name+"'").collect(Collectors.joining(","));
		List<Terminal> terminals2 = new ArrayList<>();
		if(StringHelper.isNotBlank(str)) {
			terminals2 = terminalService.createQuery("from Terminal where codeName in ("+str+")").list();
		}
		List<String> list = Stream.concat(terminals1.stream(), terminals2.stream()).map(Terminal::getMac).collect(Collectors.toList());
		AsyncContext context = request.startAsync();
		context.start(()->TerminalManager.rebootBatch(list));
		context.complete();
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("正在重启...");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("rebootAll")
	public String rebootAll(String groupId, HttpServletRequest request) {
		List<Terminal> list;
		if(StringHelper.isNotBlank(groupId)) {
			Group group = groupService.getById(groupId);
			list = terminalService.getByGroup(group);
		} else {
			list = terminalService.getAll();
		}
		List<String> codeNames = list.stream().map(Terminal::getMac).collect(Collectors.toList());
		AsyncContext context = request.startAsync();
		context.start(()->TerminalManager.rebootBatch(codeNames));
		context.complete();
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "正在重启...").toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("poweroff")
	public String poweroff(String id, String codeName, HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage();
		if(id == null && codeName == null) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("关闭失败！");
			return message.toString();
		}
		id = StringHelper.isEmpty(id)?"":id;
		codeName = StringHelper.isEmpty(codeName)?"":codeName;
		List<Terminal> terminals1 = terminalService.get(id.split(","));
		String str = Stream.of(codeName.split(",")).filter(StringHelper::isNotBlank).map(name->"'"+name+"'").collect(Collectors.joining(","));
		List<Terminal> terminals2 = new ArrayList<>();
		if(StringHelper.isNotBlank(str)) {
			terminals2 = terminalService.createQuery("from Terminal where codeName in ("+str+")").list();
		}
		List<String> list = Stream.concat(terminals1.stream(), terminals2.stream())
				.map(Terminal::getMac).collect(Collectors.toList());
		AsyncContext context = request.startAsync();
		context.start(()->TerminalManager.poweroffBatch(list));
		context.complete();
		message.setType(ReturnMessage.MESSAGE_SUCCESS);
		message.setMessage("正在关闭...");
		return message.toString();
	}
	
	@ResponseBody
	@RequestMapping("poweroffAll")
	public String poweroffAll(String groupId, HttpServletRequest request) {
		List<Terminal> list;
		if(StringHelper.isNotBlank(groupId)) {
			Group group = groupService.getById(groupId);
			list = terminalService.getByGroup(group);
		} else {
			list = terminalService.getAll();
		}
		List<String> ips = list.stream().map(Terminal::getMac).collect(Collectors.toList());
		AsyncContext context = request.startAsync();
		context.start(()->TerminalManager.poweroffBatch(ips));
		context.complete();
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "正在关闭...").toString();
	}
	
	@ResponseBody
	@RequestMapping("toggleVolume")
	public String toggleVolume(String model) {
		model = "0".equals(model)?"VOL_MIN":"1".equals(model)?"VOL_RE":"";
		List<Terminal> terminals = terminalService.getAll();
		for(Terminal terminal : terminals) {
			SocketDataSender sender = new SocketDataSender();
			sender.sendToClient(terminal.getCodeName(), model);
		}
		return new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "设置成功！").toString();
	}
}
