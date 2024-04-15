package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.User;
import com.wzh.service.UserService;
import com.wzh.until.JsonObject;
import com.wzh.until.PageRet;
import com.wzh.until.Pass;
import com.wzh.until.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @Value("${server.port}")
    private String dkh;

    @RequestMapping("/tolist")
    public String tolist(Model model) {
        model.addAttribute("dkh", dkh);
        return "User/list";

    }
    //    分页查询
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String nickname){
        Page<User> pageInfo = PageHelper.startPage(page, limit, "time desc");
        User user = new User();
        user.setNickname(nickname);
        List<User> list = userService.findAll(user);
        PageInfo<User> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("id", "");
        return "User/add";
    }

    //新增数据
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(@RequestParam("username") String username, User user) {
        String str = Pass.opinions(user.getPass());
        if (str.equals("200")){
            User u = userService.getByUsername(username);
            if (u ==null){
                String id = IdUtil.simpleUUID();

                user.setId(id);
                user.setTime(Time.getTime());
                userService.save(user);
                System.out.println("添加数据");
                return new JsonObject("200", "", null);
            }else {
                return new JsonObject("500", "账号已经存在", null);
            }
        }
        else {
            return new JsonObject("300", "密码格式不对,请输入6到12位英文加数字", null);

        }

    }
    @RequestMapping("/del")
    @ResponseBody
    public JsonObject del(@RequestParam("id") String ids[]){
        try {
            for (String id : ids) {
                userService.del(id);
            }
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @RequestMapping("/get")
    @ResponseBody
    public JsonObject get(@RequestParam("id") String id) {
        try {
            User u = userService.get(id);
            return new JsonObject("200", "", u);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(User u) {
        try {
            userService.update(u);
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @RequestMapping("/updatepass")
    @ResponseBody
    public JsonObject updatepass(User u) {
        try {
            String str = Pass.opinions(u.getPass());
            if (str.equals("200")){
                userService.update(u);
                return new JsonObject("200", "", null);
            }
            else {
                return new JsonObject("300", "密码格式不对,请输入6到12位英文加数字", null);
            }
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }

    @RequestMapping("/toget")
    public String toget(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("dkh", dkh);
        System.out.println("修改界面");
        return "User/update";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonObject login(@RequestParam("username") String username,
                            @RequestParam("pass") String pass
    ) {
        User u = userService.getByUsername(username);
        System.out.println(u != null);
        if (u == null) {
            System.out.println("账号不存在");
            return new JsonObject("500", "账号不存在", "");
        } else if (u.getPass().equals(pass) && u.getUsername().equals(username)) {


            return new JsonObject("200", "", JSON.toJSONString(u));
        } else {
            System.out.println("密码错误");
            return new JsonObject("300", "密码错误", "");
        }
    }


}
