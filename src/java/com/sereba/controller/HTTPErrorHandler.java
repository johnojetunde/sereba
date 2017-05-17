/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.sereba.model.util.SessionData;
/**
 * 
 *
 * @author John
 */
@Controller
public class HTTPErrorHandler{
 private Util util = new Util();
 private String BaseUrl = util.BaseUrl();
 private String error = "";

    @RequestMapping(value="/error/error-404")
    public ModelAndView error404(){
        

        return new ModelAndView("error/error-404", "appname", "Error 404 - Sereba Application");
    }
    
    @RequestMapping(value="/error/error-500")
    public ModelAndView error500(){
        

        return new ModelAndView("error/error-500", "appname", "Error 500 - Sereba Application");
    }
    
    @RequestMapping(value="error/noaccess")
    public ModelAndView noAccess(HttpServletRequest request)throws Exception{
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
          HttpSession session= request.getSession(false);  
        error = "";
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=error/noaccess.html";
          return new ModelAndView("redirect:"+url);
        }
         ModelAndView model =  new ModelAndView("error/noaccess","appname","Sereba Application | Restricted Access");
        try{
    
     model.addObject("baseUrl",BaseUrl);
        }catch(Exception err){
            System.out.println("System Error:"+err.getMessage());
        }
     return model;
    }
    }
