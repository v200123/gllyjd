package com.wzh.controller;

import com.wzh.model.Manager;
import com.wzh.service.ManagerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
public class IndexController {
    @Resource
    ManagerService managerService;

    @RequestMapping(value = "/login") // 访问路径
    public ModelAndView toIndex() {

        // 返回templates目录下index.html
        ModelAndView view = new ModelAndView("login");
        return view;
    }
    @RequestMapping("/main")
    public ModelAndView tomain(Model model) {


        ModelAndView view = new ModelAndView("welcome");
        return view;
    }
    @RequestMapping("/pass")
    public ModelAndView topass() {
        // pass.html
        ModelAndView view = new ModelAndView("user-password.html");
        return view;
    }
    @RequestMapping("/index")
    public ModelAndView index(Model model, @RequestParam("m") String id) {
        // 返回templates目录下main.html
        ModelAndView view = new ModelAndView("index");
        Manager manager = managerService.getByid(id);
        model.addAttribute("m", manager.getNickname());
        System.out.println("m="+id);
        return view;
    }
}
