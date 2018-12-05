package com.zzqx.mvc.controller;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jetsum.core.orm.entity.Page;
import com.zzqx.Global;
import com.zzqx.mvc.annotation.OpenAccess;
import com.zzqx.mvc.javabean.Customer;
import com.zzqx.mvc.javabean.ReturnMessage;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.file.FileManager;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController {

	@ResponseBody
	@RequestMapping("addBatch")
	public String addBatch(HttpServletRequest request) {
		ReturnMessage message = new ReturnMessage(ReturnMessage.MESSAGE_SUCCESS, "添加成功！");
		if(Global.importStatus == 1) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("系统正在处理上次导入操作，请稍后再试！");
			return message.toString();
		}
		Global.importStatus = 1;
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		if(file == null || StringHelper.isBlank(file.getOriginalFilename())) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("上传失败！");
			return message.toString();
		}
		String suffix = FileManager.getSuffix(file.getOriginalFilename());
		if(!".csv".equalsIgnoreCase(suffix)) {
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("上传失败！");
			return message.toString();
		}
		long start = System.currentTimeMillis(), end = 0l;
		long s = start;
		System.out.println("正在解析文件...");
		List<Customer> customers = extract(file);
		end = System.currentTimeMillis();
		System.out.println("解析完成,耗时："+(end-start)+"毫秒。");
		start = System.currentTimeMillis();
		Connection conn = null;
		Statement st=null;
		PreparedStatement stmt = null;
		try {
	        Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
	        conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
	        st = conn.createStatement();
	        System.out.println("清空客户数据...");
	        st.executeUpdate("delete from tb_customer");
        	System.out.println("清空成功！");
	        st.executeUpdate("truncate tb_customer");
	        conn.setAutoCommit(false);
	        System.out.println("共"+customers.size()+"条记录，开始导入数据...");
	        stmt = (PreparedStatement) conn.prepareStatement("insert into tb_customer(code, js_code, name, js_name, type, address, bank_account, cf_phone, bz_phone) values (?,?,?,?,?,?,?,?,?)");
	        int index = 0;
			for(int i=0;i<customers.size();i++) {
				Customer customer = customers.get(i);
				stmt.setString(1, customer.getCode());
				stmt.setString(2, customer.getJsCode());
				stmt.setString(3, customer.getName());
				stmt.setString(4, customer.getJsName());
				stmt.setString(5, customer.getType());
				stmt.setString(6, customer.getAddress());
				stmt.setString(7, customer.getBankAccount());
				stmt.setString(8, customer.getCfPhone());
				stmt.setString(9, customer.getBzPhone());
				stmt.addBatch();
				if(i != 0 && i%1000 == 0) {
					stmt.executeBatch();
					conn.commit();
					stmt.clearBatch();
					end = System.currentTimeMillis();
					index = (int)Math.floor((i/1000));
					System.out.println("第"+index+"阶段"+((index-1)*1000+1)+"-"+(index*1000)+"，耗时："+(end-start)+"毫秒");
					start = System.currentTimeMillis();
				}
			}
			stmt.executeBatch();
			conn.commit();
			end = System.currentTimeMillis();
			System.out.println("第"+(index+1)+"阶段"+(index*1000+1)+"-"+customers.size()+"，耗时："+(end-start)+"毫秒");
			System.out.println("共耗时："+(end-s)+"毫秒。");
		} catch (Exception e) {
			e.printStackTrace();
			message.setType(ReturnMessage.MESSAGE_ERROR);
			message.setMessage("导入出错！");
		} finally {
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
			Global.importStatus = 0;
		}
		return message.toString();
	}
	
	private List<Customer> extract(MultipartFile file) {
		List<Customer> customers = new ArrayList<>();
		if(file != null) {
			try {
			 InputStreamReader read=new InputStreamReader(file.getInputStream(),"UTF-8");
			 BufferedReader bfr = new BufferedReader(read);
			 String[] properties = {"code","jsCode","name","jsName","type","address","bankAccount","cfPhone","bzPhone"};
			 int[] lens = {20,20,255,255,20,255,50,50,50};
			 String line=null;
			 int i = 0;
			 while((line=bfr.readLine())!=null){
				 if(i != 0) {
					 String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
					 Customer customer = new Customer();
					 for(int j=0;j<properties.length;j++) {
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
	
	@OpenAccess
	@ResponseBody
	@RequestMapping("find")
	public String find(String name, String address, String phone) {
		List<Customer> customers = new ArrayList<>();
		Page<Customer> page = new Page<>();
		if(StringHelper.isBlank(name) && StringHelper.isBlank(address) && StringHelper.isBlank(phone)) {
			return JSONObject.fromObject(page).toString();
		}
		name = name.trim();
		address = address.trim();
		phone = phone.trim();
		String[] properties = {"code","jsCode","name","jsName","type","address","bankAccount","cfPhone","bzPhone"};
		String[] labels = {"code","js_code","name","js_name","type","address","bank_account","cf_phone","bz_phone"};
		Connection conn = null;
		Statement st=null;
		ResultSet resultset=null;
		try {
			Class.forName(Global.DBDRIVER);// 动态加载mysql驱动
			conn = DriverManager.getConnection(Global.DBURL, Global.DBUSER, Global.DBPASS);
			st = conn.createStatement();
			String sql = "select * from tb_customer where 1=1";
			if(StringHelper.isNotBlank(address)) {
				sql += " and address like '%"+address+"%'";
			}
			if(StringHelper.isNotBlank(phone)) {
				sql += " and (cf_phone like '%"+phone+"%' or bz_phone like '%"+phone+"%')";
			}
			if(StringHelper.isNotBlank(name)) {
				sql += " and name like '%"+name+"%'";
			}
			sql += " order by name asc, code asc";
			System.out.println(sql);
			resultset=st.executeQuery(sql);
			while(resultset.next()) {
				Customer customer = new Customer();
				for(int i=0;i<properties.length;i++) {
					 PropertyDescriptor pd = new PropertyDescriptor(properties[i], Customer.class);
					 Method wM = pd.getWriteMethod();
					 wM.invoke(customer, resultset.getString(labels[i]));
				}
				customers.add(customer);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(st != null) {
				try {
					st.close();
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
		page.setTotalCount(customers.size());
		if(customers.size() <= 18) {
			page.setResult(customers);
		}
		return JSONObject.fromObject(page).toString();
	}
	
}
