package com.mycompany.mywebapp.server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.microsoft.test.datacontroll;
import org.microsoft.test.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gwt.core.client.JsonUtils;
//import com.mycompany.mywebapp.client.MyWebApp;

import sun.misc.BASE64Encoder;

public class JsonStockDataImpl extends HttpServlet {

  private static final double MAX_PRICE = 100.0; // $100.00
  private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
 datacontroll text=new test();
 text.createFile("ok.properties");
 text.writeProperty("ok.properties", "password", "1111");
    Random rnd = new Random();
  //  resp.getHeader("username");
   // resp.getHeader("pazzword");
   // req.se
   // resp.
    //req.getHeader(arg0)
  //  if(req.getHeader("Auth").equals("111")){
   // InputStream ExcelFileToRead = new FileInputStream("C:/text.json");
   /* File file = new File("E:/temp.txt");  
    PrintStream ps = new PrintStream(new FileOutputStream(file));
    String str2=rnd.nextInt(90000)+ 100000000+rnd.nextInt(80000)+rnd.nextInt(50000)+rnd.nextInt(90000)+"";
    ps.println(str2);// 往文件里写入字符串  
    byte[] bytes = new byte[0];*/
    //bytes = new byte[ExcelFileToRead.available()];
   // ExcelFileToRead.read(bytes);
    String str="";
   if(req.getParameter("userid").equals("admin")&&req.getParameter("password").equals("123456")){
    str = "{\"Name\": \"Apple\",\"Expiry\":\"2007/10/11 13:54\",\"Price\": 3.99,\"Sizes\": [\"Small\",\"Medium\",\"Large\"]}";
    resp.sendRedirect("/MyWebApp2.html");
   }
  /*  List<HashMap<String,String>> listvalue=new ArrayList<HashMap<String,String>>();
    HashMap<String,String>mapvalue=new HashMap<String,String>();
    mapvalue.put("test", str.split(":")[1].substring(0, str.split(":")[1].length()-1));
    listvalue.add(mapvalue);*/
  //  BASE64Encoder encoder=new BASE64Encoder();
   // String encode = encoder.encode(str.getBytes());
     Gson strjson=new Gson();
//    JSONObject dataJson = new JSONObject(str);
 //   JsonParser parser = new JsonParser();
   // String test=strjson.toJson(str);
    //PrintWriter out = resp.getWriter();
 //   if(req.getHeader("username").equals("admin")&&req.getHeader("pazzword").equals("text")){
  //  resp.addHeader("auths", str2);
   // out.println(str);
    
    //out.flush();
    /*}else{
    	out.println("error");
    	out.flush();
    }
    /*}else{
    	 PrintWriter out = resp.getWriter();
    	    out.println("error");
    	    
    	    out.flush();
    }*/
  }

}
