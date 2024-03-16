package com.bao.graduationProject.controller;

import com.bao.graduationProject.domain.User;
import com.bao.graduationProject.service.UserService;
import com.bao.graduationProject.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String loginController(@RequestParam String uid, @RequestParam String password, HttpServletRequest request){
        User user = userService.loginService(uid, password);
//        System.out.println(password+user.getPassword());
        if(user!=null && user.getPassword().equals(password)){
//            return Result.success(user,"登录成功！");
            HttpSession session = request.getSession();
            session.setAttribute("userid", uid);
            System.out.println("session userid:" + session.getAttribute("userid"));
            System.out.println("登录成功");
            return user.getName();
        }else{
//            return Result.error("123","账号或密码错误！");
            System.out.println("登录失败");
            return "0";
        }
    }

    @PostMapping("/register")
    public int registerController(@RequestParam String uid, @RequestParam String name, @RequestParam String password){
        return userService.registerService(uid, name, password);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("logout!");
    }

}
