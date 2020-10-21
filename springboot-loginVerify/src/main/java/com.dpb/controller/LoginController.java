package com.dpb.controller;
import com.dpb.WebSecurityConfig;
import com.dpb.pojo.User;
import com.dpb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        Iterable<User> users = loginService.getAll();
        model.addAttribute("list", users);
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        System.out.println("login");
        return "login";
    }
    @PostMapping("/loginVerify")
    public String loginVerify(String username, String password, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean verify = loginService.verifyLogin(user);
        if(verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "redirect:/pager?pageIndex=1";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
    @GetMapping("/add")
    public String add() {
        return "add";
    }
    @PostMapping("/save")
    public String save(User user) {
        loginService.save(user);
        return "redirect:/pager?pageIndex=1";
    }
    @GetMapping("/updateInfo")
    public String updateInfo(Model model, long id) {
        model.addAttribute("users", loginService.getById(id));
        return "usersUpdate";
    }
    @PostMapping("/update")
    public String update(User user) {
        loginService.update(user);
        return "redirect:/pager?pageIndex=1";
    }
    @GetMapping("/delete")
    public String delete(long id) {
        loginService.delete(id);
        return "redirect:/pager?pageIndex=1";
    }
    @GetMapping("/sort")
    public String sortUser(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "username");
        Iterable<User> userData = loginService.findAllSort(sort);
        model.addAttribute("list", userData);
        return "sort";
    }
    @GetMapping("/pager")
    public String sortPagerArticle(int pageIndex, Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable page = PageRequest.of(pageIndex - 1, 2, sort);
        Page<User> articleData = loginService.findAll(page);
        int currPageNo = articleData.getNumber() + 1;
        model.addAttribute("currPageNo", currPageNo);
        int numOfPages = articleData.getTotalPages();
        model.addAttribute("numOfPages", numOfPages);
        int firstPageNo = Math.max(2, currPageNo - 2);
        model.addAttribute("firstPageNo", firstPageNo);
        int lastPageNo = Math.max(numOfPages - 1, currPageNo + 2);
        model.addAttribute("lastPageNo", lastPageNo);
        List<User> articles = articleData.getContent();
        model.addAttribute("list", articles);
        return "index";
    }
}
