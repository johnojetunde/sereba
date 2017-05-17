/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class Login {
    @RequestMapping("/index")
	public ModelAndView hello() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from HelloWork.java **********</div><br><br>";
		return new ModelAndView("index/welcome", "message", message);
	}
        
   @RequestMapping("/index/login")
   public ModelAndView page() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from Page HelloWork.java **********</div><br><br>";
		return new ModelAndView("index/login", "message", message);
	}
   
   @RequestMapping("/login/index")
   public ModelAndView login() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from Page HelloWork.java **********</div><br><br>";
		return new ModelAndView("login/index", "message", message);
	}
   
   
    @RequestMapping("/index/dashboard")
   public ModelAndView dashboard() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from Page HelloWork.java **********</div><br><br>";
                ModelAndView model = new ModelAndView("index/dashboard", "message", message) ;
                model.addObject("appname", "Sereba Accounting");
                return model;
		
	}
}
