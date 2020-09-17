package com.dpb.controller;

import com.dpb.pojo.Admin;
import com.dpb.pojo.Result;
import com.dpb.pojo.Users;
import com.dpb.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @RequestMapping("/regist")
    public String regist(Admin admin){
        return "/regist";
    }
    @RequestMapping("/save")
    public String insertAdmin(@Valid Admin admin, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            System.out.println(admin.getUsername());
            return "/regist";
        }
        Admin exitUser=adminService.queryByName(admin.getUsername());
        if(exitUser!=null){
            System.out.println("用户已注册");
            model.addAttribute("msg","用户已注册");
        }else{
            System.out.println("注册成功");
            adminService.insertAdmin(admin);
            model.addAttribute("msg","注册成功");
        }
        return "regist";
    }
    @RequestMapping("/login")
    public String login(Admin admin){
        return "/login";
    }
    @RequestMapping("/login_handle")
    public String login_handle(Model model, String username){
        model.addAttribute("users",this.adminService.queryByName(username));
        return "/login";
    }
}
