package com.zzqx.mvc.service.impl;


import com.zzqx.mvc.dao.CmdListMapper;
import com.zzqx.mvc.dao.CmdMapper;
import com.zzqx.mvc.dto.CmdListDto;

import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.service.*;
import com.zzqx.mvc.vo.CmdListOneVo;
import com.zzqx.mvc.vo.CmdListVo;

import com.zzqx.support.framework.mina.MinaManager;
import com.zzqx.support.framework.mina.MinaSession;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.machine.IPC;
import com.zzqx.support.utils.machine.TerminalManager;
import com.zzqx.support.utils.net.Com;
import com.zzqx.support.utils.net.SocketDataSender;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.AsyncContext;
import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class CmdListServiceImpl implements CmdListService {
    @Autowired
    CmdListMapper cmdListMapper;
    @Autowired
    CmdMapper cmdMapper;
    @Autowired
    CmdService cmdService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private ContentService contentService;

    @Override
    public List<CmdList> allList(String directName) {
        CmdListExample cmdListExample = new CmdListExample();
        if (  !"".equals(directName)) {
            CmdListExample.Criteria criteria = cmdListExample.createCriteria();
            criteria.andDirectListNameEqualTo(directName);
        }
        return cmdListMapper.selectByExample(cmdListExample);
    }

    @Override
    public List<CmdList> getList(CmdListDto cmdListDto) {
        int pageNo = cmdListDto.getLimit0() == 0?1:cmdListDto.getLimit0();
        int pageSize =  cmdListDto.getLimit1() == 0?10:cmdListDto.getLimit1();
        int  limit0 = (pageNo-1)*pageSize;
        cmdListDto.setLimit0(limit0);
        cmdListDto.setLimit1(pageSize);
        return cmdListMapper.getList(cmdListDto);
    }

    @Override
    public List<CmdList> getListCount(CmdListDto cmdListDto) {
        return cmdListMapper.getListCount(cmdListDto);
    }

    @Override
    public CmdListVo getById(String id) {
        CmdList cmdList = cmdListMapper.selectByPrimaryKey(id);
        CmdListVo cmdListVo = cmdService.getAllDataByType();
        try {
            BeanUtils.copyProperties(cmdListVo,cmdList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //检测是否有toTCPServer字符
        String s = cmdList.getDirectList();
        boolean flag = StringUtils.contains(s,"toTCPServer=");
        if (flag){
            String data = s.substring(22);
            System.out.print(data);
            String ip = data.split(",")[0];
            String port = data.split(",")[1];
            cmdListVo.setIp(ip);
            cmdListVo.setPort(port);
        }
        return cmdListVo;
    }

    @Override
    public List<CmdListOneVo> getListExcludeDirectList() {
        return cmdListMapper.getListExcludeDirectList();
    }

//    @Override
//    public CmdListVo getCmdVoById(String id) {
//       CmdList cmdList =  cmdListMapper.selectByPrimaryKey(id);
//       String[] stings = cmdList.getDirectList().split(",");
//       for (String  item:stings){
//
//       }
//        return null;
//    }

    @Override
    public int insertSelect(CmdList cmdList) {
        return cmdListMapper.insertSelective(cmdList);
    }

    @Override
    public int updateSelect(CmdList cmdList) {
        return cmdListMapper.updateByPrimaryKeySelective(cmdList);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return cmdListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String ids) {

        return 0;
    }

    @Override
    public void send(String directListName) {
//        if(toTCPServer != null && toTCPServer != "") {//发送给Socket服务器
//            Stream.of(toTCPServer).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd->cmd.split(",").length >= 3).forEach(cmd -> {
//                String ip = cmd.split(",")[0];
//                String port = cmd.split(",")[1];
//                String msg = "";
//                for(int i = 2;i<cmd.split(",").length;i++) {
//                    if(i == 2) {
//                        msg = cmd.split(",")[i];
//                    } else {
//                        msg += ","+cmd.split(",")[i];
//                    }
//                }
//                SocketDataSender sender = new SocketDataSender();
//                if(StringHelper.isNumeric(port)) {
//                    sender.sendToTCPServer(ip, Integer.valueOf(port), msg);
//                }
//            });
//        }
//
//        if(toUDPServer != null && toUDPServer != "") {//发送给Sokcet服务器
//
//            Stream.of(toUDPServer).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd->cmd.split(",").length >= 3).forEach(cmd -> {
//                String ip = cmd.split(",")[0];
//                String port = cmd.split(",")[1];
//                String msg = "";
//                for(int i = 2;i<cmd.split(",").length;i++) {
//                    if(i == 2) {
//                        msg = cmd.split(",")[i];
//                    } else {
//                        msg += ","+cmd.split(",")[i];
//                    }
//                }
//                SocketDataSender sender = new SocketDataSender();
//                if(StringHelper.isNumeric(port)) {
//                    sender.sendToUDPServer(ip, Integer.valueOf(port), msg);
//                }
//            });
//        }
//
//        if(con != null && con != "") {//工控指令
//
//            Stream.of(con).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd->cmd.contains(",")).forEach(cmd -> {
//                String deviceCode = cmd.substring(0, cmd.indexOf(","));
//                String operation = cmd.substring(cmd.indexOf(",")+1, cmd.length());
//                IPC.getInstance().send(deviceCode, operation);
//            });
//        }
//
//        if(flash != null && flash != "") {//转发
//
//            Stream.of(flash).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd -> cmd.contains(",")).forEach(cmd -> {
//                String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
//                String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
//                SocketDataSender sender = new SocketDataSender();
//                sender.sendToTerminal(ipOrCodeName, msg);
//            });
//        }
//        if(client != null && client != "") {//客户端控制
////			String test = "自己,VOL_MAX;99,VOL_MAX";
//            Stream.of(client).flatMap(cmds-> Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd -> cmd.contains(",")).forEach(cmd -> {
//                String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
//                String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
//                SocketDataSender sender = new SocketDataSender();
//                sender.sendToClient(ipOrCodeName, msg);
//            });
////			String ipOrCodeName = client[0];
////			String msg = client[1];
////			SocketDataSender socketDataSender = new SocketDataSender();
////			socketDataSender.sendToClient(ipOrCodeName,msg);
//        }
//
//        if(play != null && play != "") {//播放指定内容
//
//            Stream.of(play).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd -> cmd.contains(",")).forEach(cmd -> {
//                String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
//                String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length()).trim();
//                String model = "";
//                String action = "";
//                //判断msg中是否出现"_"
//                if(msg.contains("_")) {
//                    //取"_"前面所有的字符
//                    String newMsg = msg.substring(0, msg.indexOf("_"));
//                    //取"_"后面所有的字符
//                    String m = msg.substring(msg.indexOf("_")+1, msg.length()).trim();
//                    int li = m.indexOf("{");
//                    int ri = m.lastIndexOf("}");
//                    if(li < ri) {
//                        model = m.substring(0, li);
//                        action = m.substring(li+1, ri);
//                        action = action.replace("$", "&");
//                        action = action.replace("@", ";");
//                    } else {
//                        model = m;
//                    }
//                    msg = newMsg;
//                }
//                if(StringHelper.isNumeric(msg)) {
//                    List<Content> contents = contentService.find(Restrictions.eq("serialNumber", Integer.valueOf(msg)));
//                    if(contents.size() > 0) {
//                        Content content = contents.get(0);
//                        content.setTerminalContents(null);
//                        JsonConfig jsonConfig = new JsonConfig();
//                        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//                        String contentString = "content:"+ JSONObject.fromObject(content, jsonConfig).toString();
//                        if(StringHelper.isNotBlank(model)) {
//                            contentString += "model:"+model;
//                        }
//                        if(StringHelper.isNotBlank(action)) {
//                            contentString += "action:"+action;
//                        }
//                        SocketDataSender sender = new SocketDataSender();
//                        sender.sendToClient(ipOrCodeName, contentString);
//                    }
//                } else {
//                    SocketDataSender sender = new SocketDataSender();
//                    sender.sendToClient(ipOrCodeName, msg.toLowerCase());
//                }
//            });
//        }
//
//        if(com != null && com != "") {//向串口写数据
//
//            Stream.of(com).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd -> cmd.split(",").length == 6).forEach(cmd -> {
//                String[] config = cmd.split(",");
//                String serialName = config[0];
//                int rate = Integer.valueOf(config[1]);
//                int databits = Integer.valueOf(config[2]);
//                int stopbits = Integer.valueOf(config[3]);
//                int parity = Integer.valueOf(config[4]);
//                Com _com = new Com(serialName, rate, databits, stopbits, parity);
//                _com.open();
//                _com.send(StringHelper.hex2byte(config[5]));
//                _com.close();
//            });
//        }
//
//        if(pc != null && pc != "") {//PC控制
//
//            List<String> openIpList = new ArrayList<>();
//            List<String> closeIpList = new ArrayList<>();
//            List<String> openCodeNameList = new ArrayList<>();
//            List<String> closeCodeNameList = new ArrayList<>();
//            Stream.of(pc).flatMap(cmds->Stream.of(cmds.split(";"))).filter(StringHelper::isNotBlank)
//                    .filter(cmd -> cmd.contains(",")).forEach(cmd -> {
//                String ipOrCodeName = cmd.substring(0, cmd.indexOf(","));
//                String msg = cmd.substring(cmd.indexOf(",")+1, cmd.length());
//                if("1".equals(msg.trim())) {
//                    if(StringHelper.isIp(ipOrCodeName)) {
//                        openIpList.add(ipOrCodeName);
//                    } else {
//                        openCodeNameList.add(ipOrCodeName);
//                    }
//                }
//                if("0".equals(msg.trim())) {
//                    if(StringHelper.isIp(ipOrCodeName)) {
//                        closeIpList.add(ipOrCodeName);
//                    } else {
//                        closeCodeNameList.add(ipOrCodeName);
//                    }
//                }
//            });
//            String closestr = closeCodeNameList.stream().filter(StringHelper::isNotBlank).map(codeName->"'"+codeName+"'").collect(Collectors.joining(","));
//            List<Terminal> terminals1 = new ArrayList<>();
//            if(StringHelper.isNotBlank(closestr)) {
//                terminals1 = terminalService.createQuery("from Terminal where codeName in ("+closestr+")").list();
//            }
//            String openstr1 = openIpList.stream().filter(StringHelper::isNotBlank).map(ip->"'"+ip+"'").collect(Collectors.joining(","));
//            String openstr2 = openCodeNameList.stream().filter(StringHelper::isNotBlank).map(codeName->"'"+codeName+"'").collect(Collectors.joining(","));
//            List<Terminal> terminals2 = new ArrayList<>();
//            List<Terminal> terminals3 = new ArrayList<>();
//            if(StringHelper.isNotBlank(openstr1)) {
//                terminals2 = terminalService.createQuery("from Terminal where ip in ("+openstr1+")").list();
//            }
//            if(StringHelper.isNotBlank(openstr2)) {
//                terminals3 = terminalService.createQuery("from Terminal where codeName in ("+openstr2+")").list();
//            }
//            terminals1.forEach(terminal->closeIpList.add(terminal.getMac()));
//            Map<String, List<String>> groupTerminals = new HashMap<>();
//            Stream.concat(terminals2.stream(), terminals3.stream()).forEach(terminal->{
//                Group group = terminal.getGroup();
//                if(group!=null) {
//                    List<String> macs = new ArrayList<>();
//                    String key = StringHelper.isNotBlank(group.getServerNodeMac())?group.getServerNodeMac():"default";
//                    if(groupTerminals.containsKey(key)) {
//                        macs = groupTerminals.get(key);
//                    } else {
//                        groupTerminals.put(key, macs);
//                    }
//                    macs.add(terminal.getMac());
//                }
//            });
//            AsyncContext context = request.startAsync();
//            context.start(()->{
//                TerminalManager.poweroffBatch(closeIpList);
//                for (Map.Entry<String, List<String>> entry : groupTerminals.entrySet()) {
//                    String key = entry.getKey();
//                    List<String> macs = entry.getValue();
//                    if("default".equals(key)) {
//                        TerminalManager.poweronBatch(macs);
//                    } else {
//                        MinaSession minaSession = MinaManager.getByMac(key);
//                        if(minaSession != null) {
//                            SocketDataSender sender = new SocketDataSender();
//                            sender.send(minaSession.getIoSession(), "poweron:"+macs.stream().collect(Collectors.joining(",")));
//                        } else {
//                            TerminalManager.poweronBatch(macs);
//                        }
//                    }
//                }
//            });
//            context.complete();
//        }
    }
}
