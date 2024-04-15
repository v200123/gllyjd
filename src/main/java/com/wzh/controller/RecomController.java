package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.Food;
import com.wzh.model.Recom;
import com.wzh.model.Relevance;
import com.wzh.model.Spot;
import com.wzh.service.FoodService;
import com.wzh.service.RecomService;
import com.wzh.service.RelevanceService;
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
import java.util.List;

@Controller
@RequestMapping("/recom")
public class RecomController {
    @Resource
    RecomService recomService;

    @Value("${server.port}")
    private String dkh;

    @RequestMapping("/tolist")
    public String tolist(Model model) {
        model.addAttribute("dkh", dkh);
        return "Recom/list";

    }
    @Resource
    RelevanceService relevanceService;
    //    分页查询
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String nickname){
        Page<Recom> pageInfo = PageHelper.startPage(page, limit, "time desc");
        Recom recom = new Recom();
        recom.setName(nickname);

        List<Recom> list = recomService.findAll(recom);
        for (int i = 0; i < list.size(); i++){
            Relevance relevance1 = new Relevance();
            relevance1.setTid(list.get(i).getId());
            List<Relevance>relevanceList = relevanceService.findAll(relevance1);
            if (relevanceList.size()>0){
                if (relevanceList.size()>1){
                    String op = "";
                    for (int q = 0; q < relevanceList.size(); q++){
                        op = op + relevanceList.get(q).getName() + " - ";
                    }
                    System.err.println("op="+op);
                    System.err.println("op2="+op.substring(0, op.length()-2));

                    Recom recom1 = new Recom();
                    recom1.setId(list.get(i).getId());
                    recom1.setName(op.substring(0, op.length()-2));
                    recomService.update(recom1);
                }
                else {
                    Recom recom1 = new Recom();
                    recom1.setId(list.get(i).getId());
                    recom1.setName(relevanceList.get(0).getName());
                    recomService.update(recom1);

                }
            }
        }
        PageInfo<Recom> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("id", "");
        return "Recom/add";
    }

    //新增数据
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(Recom recom) {
        try {
            recom.setId(IdUtil.simpleUUID());
            recom.setTime(Time.getTime());
            recomService.save(recom);
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
                recomService.del(id);
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
            Recom u = recomService.get(id);
            return new JsonObject("200", "", u);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Recom u) {
        try {
            recomService.update(u);
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
        System.out.println("修改界面");
        return "Recom/add";
    }

    @RequestMapping("findAll")
    @ResponseBody
    public JsonObject findAll(Recom recom){
        try{
            List<Recom> list = recomService.findAll(recom);
            for (int i = 0; i < list.size(); i++){
                Relevance relevance1 = new Relevance();
                relevance1.setTid(list.get(i).getId());
                List<Relevance>relevanceList = relevanceService.findAll(relevance1);
                if (relevanceList.size()>0){
                    if (relevanceList.size()>1){
                        String op = "";
                        for (int q = 0; q < relevanceList.size(); q++){
                            op = op + relevanceList.get(q).getName() + " - ";
                        }
                        Recom recom1 = new Recom();
                        recom1.setId(list.get(i).getId());
                        recom1.setName(op.substring(0, op.length()-2));
                        recomService.update(recom1);
                    }
                    else {
                        Recom recom1 = new Recom();
                        recom1.setId(list.get(i).getId());
                        recom1.setName(relevanceList.get(0).getName());
                        recomService.update(recom1);

                    }
                }
            }
            return new JsonObject("200","", JSON.toJSONString(list));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }
    }

}
