package com.wzh.controller;

import com.wzh.model.Manager;
import com.wzh.service.ManagerService;
import com.wzh.until.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    ManagerService managerService;
    @RequestMapping("/login")
    @ResponseBody
    public JsonObject login(@RequestParam("username") String username,
                            @RequestParam("pass") String pass,
                            Model model,
                            HttpSession session) {
        System.out.println("123");
        System.out.println("username"+username);
        System.out.println("pass"+pass);

        Manager m = managerService.getByUsername(username);
        if (m == null) {
            System.out.println("账号不存在");
            return new JsonObject("500", "账号不存在", "");
        } else if (m.getPass().equals(pass) && m.getUsername().equals(username)) {
            System.out.println("登录成功");
            session.setAttribute("loginSuccess", "123");
            model.addAttribute("nickname",m.getNickname());
            return new JsonObject("200", "",m);
        } else {
            System.out.println("密码错误");
            return new JsonObject("300", "密码错误", "");
        }
    }
    @RequestMapping("/exit")
    public ModelAndView toetit(HttpSession session,
                               HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/login");
        session.removeAttribute("loginSuccess");
        Object loginSuccess = request.getSession().getAttribute("loginSuccess");
        System.out.println("退出"+loginSuccess);
        return view;
    }
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(@RequestParam("old_password") String Oldpassword, @RequestParam("new_password") String Newpassword, @RequestParam("again_password") String Againpassword) {
        Manager m = managerService.getByid("1");
        try{
            if (m.getPass().equals(Oldpassword)){
                if (Newpassword.equals(Againpassword)){
                    Manager manager = new Manager();
                    manager.setId(m.getId());
                    manager.setPass(Newpassword);
                    managerService.update(manager);
                    return new JsonObject("200","修改成功", null);
                }
                else {
                    return new JsonObject("300","两次输入的密码不相同", null);
                }
            }
            else {
                return new JsonObject("400","原密码不正确", null);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            return new JsonObject("500","失败","");
        }
    }

}
