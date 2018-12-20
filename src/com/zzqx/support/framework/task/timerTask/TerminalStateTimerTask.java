package com.zzqx.support.framework.task.timerTask;

import java.util.List;

import com.zzqx.mvc.entity.Terminal;
import com.zzqx.support.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zzqx.mvc.service.TerminalService;
import com.zzqx.support.framework.mina.MinaManager;
import com.zzqx.support.framework.mina.MinaSession;
import com.zzqx.support.utils.file.PropertiesHelper;
import com.zzqx.support.utils.net.Pinger;

@Transactional
public class TerminalStateTimerTask {
	
	@Autowired
	private TerminalService terminalService;
	
	public void doTask() {
		PropertiesHelper props = new PropertiesHelper("config");
		String modal = props.readValueTrim("monitor.terminal.state.modal");
		List<Terminal> terminals = terminalService.getAll();
		if("ping".equalsIgnoreCase(modal)) {
			terminals.forEach(terminal->
				new Thread(()->{
					Pinger pinger = new Pinger(terminal.getIp(), 1, 3000);
					boolean status = pinger.isReachable();
					if(!status) {
						MinaSession minaSession = MinaManager.getByMac(terminal.getMac());
						status = minaSession != null;
					}
					if(!String.valueOf(status).equalsIgnoreCase(terminal.getStatus())) {
						terminal.setStatus(String.valueOf(status));
						terminalService.saveOrUpdate(terminal);
					}
				}).start());
		} else {
			List<MinaSession> minaSessions = MinaManager.getFlashClients();
			terminals.forEach(terminal->{
				terminal.setStatus("false");
				minaSessions.forEach(minaSession -> {
					if(StringHelper.isNotBlank(terminal.getMac()) && terminal.getMac().equalsIgnoreCase(minaSession.getMac())) {
						terminal.setStatus("true");
					}
				});
				terminalService.saveOrUpdate(terminal);
			});
		}
	}
}