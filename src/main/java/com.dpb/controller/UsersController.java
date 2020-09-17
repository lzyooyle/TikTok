package com.dpb.controller;

import com.dpb.pojo.Users;
import com.dpb.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @program: springboot-ssm
 * @description: 用户的控制层
 * @author: 波波烤鸭
 * @create: 2019-05-15 19:57
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 页面跳转
     */
    @RequestMapping("/usersAdd")
    public String showPage(Users users){
        System.out.println("---测试--123");
        return "/usersAdd";
    }

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("/query")
    public String queryUser(Model model){
        model.addAttribute("list",usersService.query());
        return "/users";
    }

    /**
     * 添加用户信息
     * @Valid 开启对数据的校验
     * @return
     */
    @RequestMapping("/save")
    public String insertUser(@Valid Users users, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(users.getName());
            // 数据不合法
            return "/usersAdd";
        }
        usersService.insertUser(users);
        return "redirect:/users/query";
    }
    @RequestMapping("/updateInfo")
    public String updateInfo(Model model,Integer id){
        model.addAttribute("users",this.usersService.queryUsersById(id));
        return "/usersUpdate";
    }
    @RequestMapping("/update")
    public String updateUser(Users users){
        this.usersService.updateUser(users);
        return "redirect:/users/query";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        this.usersService.delete(id);
        return "redirect:/users/query";
    }
}
