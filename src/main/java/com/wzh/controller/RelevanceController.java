package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.Recom;
import com.wzh.model.Relevance;
import com.wzh.model.Spot;
import com.wzh.service.RelevanceService;
import com.wzh.service.SpotService;
import com.wzh.until.JsonObject;
import com.wzh.until.PageRet;
import com.wzh.until.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/relevance")
public class RelevanceController {
    @Resource
    RelevanceService relevanceService;
    @Resource
    SpotService spotService;

    @Value("${server.port}")
    private String dkh;

    @RequestMapping("/tolist")
    public String tolist(Model model, @RequestParam("tid") String tid) {
        model.addAttribute("dkh", dkh);
        model.addAttribute("tid", tid);
        Spot spot = new Spot();
        List<Spot> list = spotService.findAll(spot);
        model.addAttribute("dlist",list);
        return "Relevance/list";

    }

    //    分页查询
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String nickname,
                        @RequestParam("tid") String tid){
        Page<Relevance> pageInfo = PageHelper.startPage(page, limit, "time desc");
        Relevance relevance = new Relevance();
        relevance.setName(nickname);
        relevance.setTid(tid);

        List<Relevance> list = relevanceService.findAll(relevance);
        PageInfo<Relevance> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toadd")
    public String toadd(Model model,@RequestParam("tid") String tid) {
        model.addAttribute("id", "");
        model.addAttribute("tid", tid);
        Spot spot = new Spot();
        List<Spot> list = spotService.findAll(spot);
        model.addAttribute("dlist",list);
        return "Relevance/add";
    }

    //新增数据
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(Relevance relevance) {
        try {
            relevance.setId(IdUtil.simpleUUID());
            relevance.setTime(Time.getTime());
            relevanceService.save(relevance);
            System.out.println("添加数据");
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @RequestMapping("/del")
    @ResponseBody
    public JsonObject del(@RequestParam("id") String ids[]){
        try {
            for (String id : ids) {
                relevanceService.del(id);
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
            Relevance u = relevanceService.get(id);
            return new JsonObject("200", "", u);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Relevance u) {
        try {
            relevanceService.update(u);
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }


    @RequestMapping("/toget")
    public String toget(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("dkh", dkh);
        Spot spot = new Spot();
        List<Spot> list = spotService.findAll(spot);
        model.addAttribute("dlist",list);
        System.out.println("修改界面");
        return "Relevance/add";
    }


    @RequestMapping("findAll")
    @ResponseBody
    public JsonObject findGh(Relevance relevance){
        try {
            List<Spot> spotList =new ArrayList<>();
            List<Relevance> list = relevanceService.findAll(relevance);
            for (int i = 0; i < list.size(); i++){
                Spot spot = spotService.get(list.get(i).getRid());
                if (spot!=null){
                    spotList.add(spot);
                }
            }

            return new JsonObject("200","", JSON.toJSONString(spotList));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }
    }

}
