package com.dpb;
import com.dpb.pojo.User;
import com.dpb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Iterator;

@Controller
public class UserController{
    @Resource
    UserService userService;
    @RequestMapping("/")
    public String index(){
        return "redirect:/list";
    }
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="pageNum",defaultValue="0") int pageNum,@RequestParam(value="pageSize",defaultValue="2") int pageSize){
        System.out.println("=========");
        Page<User> users = userService.getUserList(pageNum,pageSize);
        System.out.println("totalPages"+users.getTotalPages());
        System.out.println("nowPage"+pageNum);
        System.out.println("pageData");
        Iterator<User> u = users.iterator();
        while(u.hasNext()){
            System.out.println(u.next().toString());
        }
        model.addAttribute("users",users);
        return "user/list";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }
    @RequestMapping("/add")
    public String add(User user){
        boolean verify = userService.verifyLogin(user);
        if(verify){
            System.out.println("infoError");
            return "redirect:/toAdd";
        }else{
            userService.save(user);
            return "redirect:/list";
        }
    }
    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }
    @RequestMapping("/edit")
    public String edit(User user, RedirectAttributes attr){
        boolean verify = userService.verifyLogin(user);
        if(verify){
            attr.addAttribute("id",user.getId());
            System.out.println("username not unique");
            return "redirect:/toEdit";
        }else{
            userService.edit(user);
            return "redirect:/list";
        }
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        userService.delete(id);
        return "redirect:/list";
    }
}
