package com.zzqx.support.framework.task;

public abstract class BaseTask implements Runnable {

	public abstract void beforeTask();
	
	public abstract void doTask();
	
	public abstract void afterTask();
	
	public abstract void stopTask();

	public final void run() {
		beforeTask();
		doTask();
		afterTask();
	}
}
