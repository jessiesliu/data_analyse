package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.controller.HiveJDBClient;
/**
 * 请求地址的映射requestmapping
 * @author ezijing
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private HiveJDBClient ssClient;
	
	public HiveJDBClient getSsClient() {
		return ssClient;
	}

	public void setSsClient(HiveJDBClient ssClient) {
		this.ssClient = ssClient;
	}

	public String abc(){
		return "ajflgjalgjg";
	}
	
	@RequestMapping(value="/jsonstyle.do")
	@ResponseBody
	public void list(HttpServletResponse response) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		String tablename="student_tmp";
		lists=ssClient.queryAll(tablename);
		
		//把List<Map<string,object>>转化成json格式
		StringBuilder sb = new StringBuilder();
		sb.append("[");
   for(Map<String,Object> map:lists){
	   sb.append("{");
        for (String key : map.keySet()) {
            sb.append("\"").append(key).append("\":\"").append(map.get(key))
                    .append("\"").append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}").append(",");
   	}
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        response.getWriter().write(sb.toString());
        //return sb.toString();
	}
	
	
	
	
	/*
	@RequestMapping(value="/list.do")
	public String list(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println("---查询用户信息----");
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		//String tablename = request.getParameter("tableName");
		String tablename="student_tmp";
		lists=ssClient.queryAll(tablename);
		
		//把List<Map<string,object>>转化成json格式
		StringBuilder sb = new StringBuilder();
		sb.append("[");
   for(Map<String,Object> map:lists){
	   sb.append("{");
        for (String key : map.keySet()) {
            sb.append("\"").append(key).append("\":\"").append(map.get(key))
                    .append("\"").append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}").append(",");
   	}
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        
        
        //System.out.println(sb.toString());
		//request.setAttribute("sb", sb);
		
		//request.setAttribute("lists", lists);
		//String aaa=abc();
		//request.setAttribute("aaa", aaa);
		//String abcd="advance";
		//request.setAttribute("abcd", abcd);
		//return "user_list";
        return sb.toString();
	}*/
	/*
	@RequestMapping(value="/add.do",method=RequestMethod.GET)
	public String add(){
		
		System.out.println("---添加用户信息----");
		return "user_add";
	}
	@RequestMapping(value="/update.do")
	public String update(){
		System.out.println("---更新用户信息----");
		return "user_update";
	}*/

}
