/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sereba.model.util.Util;
import com.sereba.model.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
/**
/**
 *
 * @author John
 */
@Controller
public class IndexController extends HttpServlet {
    private String error = "";
    private Util util = new Util();
    private String BaseUrl = util.BaseUrl();
    
    
    
    @RequestMapping("/index/dashboard")
    public ModelAndView indexPage(HttpServletRequest request)throws Exception {
         SessionData temp = new SessionData(request);        
         BaseUrl = temp.BaseUrl(request);
         HttpSession session= request.getSession(false);  
        error = "";
        if(session == null || session.getAttribute("fullname")== null){
         String url = BaseUrl+"login/index.html?redirect=error/noaccess.html";
          return new ModelAndView("redirect:"+url);
        } 
        ModelAndView model = new ModelAndView("index/dashboard", "appname", "Sereba Accounting") ;
        try {
       
         
        }catch(Exception err){
            System.out.println("System error:"+err.getMessage());
        }
        model.addObject("baseUrl",BaseUrl);
                return model;
    }
}
