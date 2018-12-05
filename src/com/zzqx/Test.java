package com.zzqx;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zzqx.mvc.javabean.Customer;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.support.utils.StringHelper;

public class Test {

	public static void main(String[] args) {


		System.out.println(StringHelper.MD5("admin"));

		long start = System.currentTimeMillis(), end = 0l;
		long s = start;
		System.out.println("正在解析文件...");
		List<Customer> customers = extract(new File("C:\\Users\\still\\Desktop\\全海珠用户_utf8.csv"));
		end = System.currentTimeMillis();
		System.out.println("解析完成,耗时："+(end-start)+"毫秒。");
		System.out.println("共"+customers.size()+"条记录，开始导入数据...");
		start = System.currentTimeMillis();
		Connection conn = null;
		Statement st=null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        st = conn.createStatement();
	        int delete = st.executeUpdate("delete from tb_customer");
	        if(delete == 1) {
	        	System.out.println("清空成功！");
	        } else {
	        	System.out.println("清空失败！");
	        }
	        st.executeUpdate("truncate tb_customer");
//	        conn.setAutoCommit(false);
//	        stmt = (PreparedStatement) conn.prepareStatement("insert into tb_customer(code, js_code, name, js_name, type, address, bank_account, cf_phone, bz_phone) values (?,?,?,?,?,?,?,?,?)");
//	        int index = 0;
//			for(int i=0;i<customers.size();i++) {
//				Customer customer = customers.get(i);
//				stmt.setString(1, customer.getCode());
//				stmt.setString(2, customer.getJsCode());
//				stmt.setString(3, customer.getName());
//				stmt.setString(4, customer.getJsName());
//				stmt.setString(5, customer.getType());
//				stmt.setString(6, customer.getAddress());
//				stmt.setString(7, customer.getBankAccount());
//				stmt.setString(8, customer.getCfPhone());
//				stmt.setString(9, customer.getBzPhone());
//				stmt.addBatch();
//				if(i != 0 && i%1000 == 0) {
//					stmt.executeBatch();
//					conn.commit();
//					stmt.clearBatch();
//					end = System.currentTimeMillis();
//					index = (int)Math.floor((i/1000));
//					System.out.println("第"+index+"阶段"+((index-1)*1000+1)+"-"+(index*1000)+"，耗时："+(end-start)+"毫秒");
//					start = System.currentTimeMillis();
//				}
//			}
//			System.out.println("第"+(index+1)+"阶段"+(index*1000+1)+"-"+customers.size()+"，耗时："+(end-start)+"毫秒");
//			stmt.executeBatch();
//			conn.commit();
			end = System.currentTimeMillis();
			System.out.println("共耗时："+(end-s)+"毫秒。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static List<Customer> extract(File file) {
		List<Customer> customers = new ArrayList<>();
		if(file != null) {
			try {
			 InputStreamReader read=new InputStreamReader(new FileInputStream(file),"UTF-8");
			 BufferedReader bfr = new BufferedReader(read);
			 String[] properties = {"code","jsCode","name","jsName","type","address","bankAccount","cfPhone","bzPhone"};
			 int[] lens = {20,20,255,255,20,255,50,50,50};
			 String line=null;
			 int i = 0;
			 while((line=bfr.readLine())!=null){
				 if(i != 0) {
					 String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
					 Customer customer = new Customer();
					 for(int j=0;j<9;j++) {
						 PropertyDescriptor pd = new PropertyDescriptor(properties[j], Customer.class);
						 Method wM = pd.getWriteMethod();
						 if(item.length>j && StringHelper.isNotBlank(item[j])) {
							 item[j] = item[j].trim();
							 if(item[j].length() > lens[j]) {
								 wM.invoke(customer, item[j].substring(0, lens[j]));
							 } else {
								 wM.invoke(customer, item[j]);
							 }
						 } else {
							 wM.invoke(customer, "");
						 }
					 }
					 customers.add(customer);
				 }
				 i++;
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return customers;
	}

}
