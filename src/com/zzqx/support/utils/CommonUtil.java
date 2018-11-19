package com.zzqx.support.utils;

import java.util.Random;

public class CommonUtil {

	/**
     * 生成指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min,int max) {
    	if(max==0){
    		return 0;
    	}
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
       return s;
    }
}
