package com.zzqx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		String[] ids = list.stream().collect(Collectors.toList()).toArray(new String[0]);
		for(String s : ids) {
			System.out.println(s);
		}
	}

}
