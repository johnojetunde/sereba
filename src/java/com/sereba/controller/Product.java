/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author John
 */

@Controller
public class Product {
    
    
    @RequestMapping("/product")
    public ModelAndView helloWorld() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    
    @RequestMapping("/product/addproduct")
    public ModelAndView addProduct() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    @RequestMapping("/product/manageproduct")
    public ModelAndView manageProduct() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
    
    @RequestMapping("/product/view/id/*")
    public ModelAndView viewProduct() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";

        return new ModelAndView("index/welcome", "message", message);
    }
}
