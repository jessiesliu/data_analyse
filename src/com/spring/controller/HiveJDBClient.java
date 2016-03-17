package com.spring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

public class HiveJDBClient {
	//private static 
	//jdbc:hive://10.1.1.40:10000/default", "", ""
	public static List queryAll(String tableName) { 
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		// List list = new ArrayList();
		try{
			//必须首先开启 Hive 的远程服务接口 hive --service hiveserver
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		    Connection conn=DriverManager.getConnection("jdbc:hive://10.1.1.40:10000/default", "", "");//url,账户名，密码
		    Statement st=conn.createStatement();
		   // String tableName="student_tmp";//表名  
		    ResultSet rs=st.executeQuery("select * from "+tableName+" ");//求平均数,会转成MapReduce作业运行
		    ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
		    int columnCount = md.getColumnCount();   //获得列数 
		    while (rs.next()) {
		     //System.out.println(rs.getString(1)+"   "+rs.getString(2));
		     // Map rowData = new HashMap(); 
		      Map<String,Object> rowData = new HashMap<String,Object>();
		      for (int i = 1; i <= columnCount; i++) {
		    	  String name = md.getColumnName(i);
		    	  String str = name.substring(name.lastIndexOf(".")+1);
		        rowData.put(str, rs.getObject(i));
		       // JSONObject json = JSONObject.fromObject(rowData);
		       // JSONArray json = JSONArray.fromObject(rowData);
		      }
		      list.add(rowData);
		    }
		   /* 
		    for (Map<String, Object> m : list)  
		    {  
		      for (String k : m.keySet())  
		      {  
		        System.out.println(k + " : " + m.get(k));  
		      }  
		  
		    }  
		    */
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return list;
		}
/*
  public static void main(String[] args) throws Exception{
	  //hiveconn();
	  List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
	  //List lists = new ArrayList<>();
	  lists=queryAll();
	  for (Map<String, Object> m : lists)  
	    {  
	      for (String k : m.keySet())  
	      {  
	        System.out.print(k + " : " + m.get(k)+"   ");  
	      }  
	        System.out.println("");
	    }  
	  //for(int i=0;i<lists.size();i++){
		//  System.out.println(lists.get(i));
	  //}
  }
  
*/
}
