/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.Month;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author John
 */
public class Util {
     private Calendar calendar   = Calendar.getInstance();
    public String errorAlertReturn(String message){
      
            return "<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>";
       
    }
    
    public void errorAlertPrint(String message){
        System.out.println( "<div class='alert alert-danger alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-ban'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>");
    }
    
    
    public String successRedirectReturn(String message, String url, Integer delay, String formid){
        Integer time = delay * 1000;
       String data =  "<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>";
         if(formid!="" && formid!="form"){
                    data += "<script type='text/javascript'>document.getElementById('"+ formid + "').reset();"
                            + " window.setTimeout(function(){window.location.href = '"+url+"';}, "+time+");"
                            + "</script>";
         }
         
         return data;
    }
        
   public void successRedirectPrint(String message, String url, Integer delay, String formid){
        Integer time = delay * 1000;
       String data =  "<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>";
         if(formid!="" && formid!="form"){
                    data += "<script type='text/javascript'>document.getElementById('"+ formid + "').reset();"
                            + " window.setTimeout(function(){window.location.href = '"+url+"';}, "+time+");"
                            + "</script>";
         }
         
         System.out.println(data);
   }
   
   public String successAlertReturn(String message, String formid){
       
       String data =  "<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>";
         if(formid!="" && formid!="form"){
                    data += "<script type='text/javascript'>document.getElementById('"+ formid + "').reset();"
                         + "</script>";
         }
         
         return data;
   }
   
   public void successAlertPrint(String message, String formid){
         String data =  "<div class='alert alert-success alert-dismissable' style='width:100%; margin: 0 auto;'><i class='fa fa-check'></i>"
                    +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><b>Alert!</b> "+message +"</div>";
         if(formid!="" && formid!="form"){
                    data += "<script type='text/javascript'>document.getElementById('"+ formid + "').reset();"
                         + "</script>";
         }
         
        System.out.println(data);
   }

   public static Date getCurrentTimeStamp()  {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    try{
    String strDate = sdfDate.format(now);
    now = sdfDate.parse(strDate);
    }catch (ParseException err){
       //print an error of the date we are parsing
    }
    return now;
}
   
   public String check_length_add_one(String value){
           if(value.toString().length() ==1){
               value = "0"+value;
               
           }
           return value;
       }
   
    public Map getMonthlyDateRange(Integer start_month,Integer start_year,Integer end_month,Integer end_year){
       Integer year_diff = end_year-start_year;
       String month_start = "";
       String month_limit = "";
       List<Object> startYears = new ArrayList();
       List<Object> endYears = new ArrayList();
                        //$starts_years = array();
                        //$ends_years = array();
                        if(year_diff==0){
                             month_limit = end_month.toString();
                            for(Integer i=start_month;i<=Integer.parseInt(month_limit);i++){
                                //i = check_length_add_one(i.toString());
                                String tempi = check_length_add_one(i.toString());
                                String start_date = end_year+"-"+i+"-01";
                                String end_date = end_year+"-"+i+"-31";
                                startYears.add(start_date);
                                endYears.add(end_date);
                               // array_push($starts_years,$start_date);
                                //array_push($ends_years,$end_date);
                                 
                            }
                            
                             }else{
                            for(Integer r=start_year;r<=end_year;r++){
                                if(r==start_year){
                                     month_start = start_month.toString();
                                    
                                }else{
                                     month_start = "01";
                                }
                                
                                if(r==end_year){
                                     month_limit = end_month.toString();
                                }
                                else{
                                      month_limit = "12";
                                }
                                
                              for(Integer i=Integer.parseInt(month_start);i<=Integer.parseInt(month_limit);i++){
                                  //i = check_length_add_one(i);
                                  String tempi = check_length_add_one(i.toString());
                                  String start_date  = r+"-"+tempi+"-01";
                                  String end_date = r+"-"+tempi+"-31";
                                  
                                  startYears.add(start_date);
                                  endYears.add(end_date);
                                  // array_push($starts_years,$start_date);
                               // array_push($ends_years,$end_date);
                              }
                                
                            }
                            
                            
                        }
                        
                        
                        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
                        map.put("startYears", startYears);
                        map.put("endYears",endYears);
                       // return array($starts_years,$ends_years);
                       return map;
        
    }
    
    public Map getAnnualDateRange(Integer start_year,Integer end_year){
         List<Object> startYears = new ArrayList();
         List<Object> endYears = new ArrayList();
                        
                        for(int i=start_year; i<=end_year; i++){
                           String start_date = i+"-01-01";
                           String end_date = i+"-12-31";
                           startYears.add(start_date);
                           endYears.add(end_date);
                           //array_push($starts_years,$start_date);
                         //  array_push($ends_years,$end_date);
                           }
       Map<String,List<Object>> map = new HashMap<String,List<Object>>();
       map.put("startYears", startYears);
       map.put("endYears",endYears);
     return map;
    }
    
    public Map getAggregateDateRange(String startDate,String endDate){
    List<Object> startYears = new ArrayList();
    List<Object> endYears = new ArrayList();
    startYears.add(startDate);
    endYears.add(endDate);
    
     Map<String,List<Object>> map = new HashMap<String,List<Object>>();
       map.put("startYears", startYears);
       map.put("endYears",endYears);
     return map;
    
    }
    
    public Map get3MonthDateRange(String startDate,String endDate)throws Exception{
        String[] startDateArray = startDate.split("-");
       
       String[] endDateArray = endDate.split("-");
       
     
       String endYear = endDateArray[0];
       String endMonth = endDateArray[1];
       String endDay = endDateArray[2];
       
        LocalDate now = LocalDate.of(Integer.parseInt(endYear),Integer.parseInt(endMonth),Integer.parseInt(endDay));// 2015-11-24
        LocalDate earlier = now.minusMonths(3); // 2015-10-24

       
       
        List<Object> startYears = new ArrayList();
        List<Object> endYears = new ArrayList();
        
        String startMonth = String.valueOf(earlier.getMonth().getValue());
        String startYear = String.valueOf(earlier.getYear());
        String startDay = String.valueOf(earlier.getDayOfMonth());
        
        startDate = startYear+"-"+startMonth+"-"+startDay;
        
        startYears.add(startDate);
        endYears.add(endDate);
        
        Map<String,List<Object>> map = new HashMap<String,List<Object>>();
        map.put("startYears", startYears);
        map.put("endYears",endYears);
        return map;
    }
    public Map get6MonthDateRange(String startDate,String endDate)throws Exception{
       String[] startDateArray = startDate.split("-");
       
       String[] endDateArray = endDate.split("-");
       
     
       String endYear = endDateArray[0];
       String endMonth = endDateArray[1];
       String endDay = endDateArray[2];
       
        LocalDate now = LocalDate.of(Integer.parseInt(endYear),Integer.parseInt(endMonth),Integer.parseInt(endDay));// 2015-11-24
        LocalDate earlier = now.minusMonths(6); // 2015-10-24

       
       
        List<Object> startYears = new ArrayList();
        List<Object> endYears = new ArrayList();
        
        String startMonth = String.valueOf(earlier.getMonth().getValue());
        String startYear = String.valueOf(earlier.getYear());
        String startDay = String.valueOf(earlier.getDayOfMonth());
        
        startDate = startYear+"-"+startMonth+"-"+startDay;
        
        startYears.add(startDate);
        endYears.add(endDate);
        
        Map<String,List<Object>> map = new HashMap<String,List<Object>>();
        map.put("startYears", startYears);
        map.put("endYears",endYears);
        return map;
        
    }
  
    public Map parseDate(String startDate,String endDate, String period)throws Exception{
       List<String> dateList = new ArrayList<String>();
       String startYear = "";
       String startMonth = "";
       String startDay = "";
       String endYear = "";
       String endMonth = "";
       String endDay = "";
       String[] startDateArray = startDate.split("-");
       
       String[] endDateArray = endDate.split("-");
       
       startYear = startDateArray[0];
       startMonth = startDateArray[1];
       startDay = startDateArray[2];
       
       endYear = endDateArray[0];
       endMonth = endDateArray[1];
       endDay = endDateArray[2];
       
       Map result  = new HashMap();
       
       try{
           if(period.equalsIgnoreCase("aggregate")){
               return getAggregateDateRange(startDate,endDate);
           }else if(period.equalsIgnoreCase("annual")){
                return getAnnualDateRange(Integer.parseInt(startYear),Integer.parseInt(endYear));
           }else if(period.equalsIgnoreCase("3month")){
               return get3MonthDateRange(startDate,endDate);
           }else if(period.equalsIgnoreCase("6month")){
               return get6MonthDateRange(startDate,endDate);
           }
           else if(period.equalsIgnoreCase("monthly")){
               return  getMonthlyDateRange(Integer.parseInt(startMonth),Integer.parseInt(startYear),Integer.parseInt(endMonth),Integer.parseInt(endYear));
           }
       
       }catch(Exception err){
           System.out.println("System error :"+err.getMessage());
       }
       return result;
   }

   public static Date formatDate(String format,String strDate)  {
    SimpleDateFormat sdfDate = new SimpleDateFormat(format);//dd/MM/yyyy
   Date date = new Date();
    try{
    //
    date = sdfDate.parse(strDate);
    String formattedDate = sdfDate.format(date);
    date = sdfDate.parse(formattedDate);
    }catch (ParseException err){
       //print an error of the date we are parsing
    }
    return date;
  }
   public String BaseUrl(){
     return "http://localhost:8080/Sereba_Accounting/";
     }
   
   public String md5(String password)  {
         String md5Hex = "";
         try {
         md5Hex = DigestUtils.md5Hex(password);
         }catch (Exception err){
             System.out.println("An error in the md5 error page"+ err.getMessage());
         }
         
        return md5Hex;
    }
}
