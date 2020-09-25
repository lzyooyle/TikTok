package com.dpb.controller;

import com.dpb.pojo.Admin;
import com.dpb.pojo.Result;
import com.dpb.pojo.User;
import com.dpb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;
    @PostMapping(value="/regist")
    public Result regist(User user){
        return userService.regist(user);
    }
    @PostMapping(value="/login")
    public Result login(User user){
        return userService.login(user);
    }
    @PostMapping(value="/save")
    public ModelAndView save(@Valid Admin admin, BindingResult bindingResult){
        ModelAndView mvc = new ModelAndView("add");
        if(bindingResult.hasErrors()){
            System.out.println("数据录入错误");
        }else{
            Result result = userService.save(admin);
            mvc.addObject("msg",result.getDetail());
            System.out.println(result.getDetail());
            System.out.println("数据录入成功");
        }
        return mvc;
    }
    @RequestMapping("/query")
    public ModelAndView query(){
        ModelAndView mb=new ModelAndView("user");
        mb.addObject("list",userService.query());
        return mb;
    }
    @RequestMapping("/del")
    public ModelAndView delete(Integer id){
        this.userService.delete(id);
        ModelAndView mb=new ModelAndView(new RedirectView("/user/query"));
        return mb;
    }
    @RequestMapping("/updateInfo")
    public ModelAndView updateInfo(Integer id){
        ModelAndView mb = new ModelAndView("user_update");
        mb.addObject("user",this.userService.updateInfo(id));
        return mb;
    }
    @RequestMapping("/updateUser")
    public ModelAndView update(Admin admin){
        this.userService.updateUser(admin);
        ModelAndView mb = new ModelAndView(new RedirectView("/user/query"));
        return mb;
    }
}
