package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.wzh.model.Orders;
import com.wzh.model.Pl;
import com.wzh.service.OrdersService;
import com.wzh.service.PlService;
import com.wzh.until.JsonObject;
import com.wzh.until.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pl")
public class PlController {
    @Resource
    PlService plService;
    @Resource
    OrdersService ordersService;
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(Pl pl) {
        try {

            pl.setId(IdUtil.simpleUUID());
            pl.setTime(Time.getTime());
            plService.save(pl);
            System.out.println("添加数据");
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @RequestMapping("findAll")
    @ResponseBody
    public JsonObject findAll(Pl pl){
        try {
            List<Pl> list = plService.findAll(pl);
            List<Pl> all_list = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Pl pl1 = new Pl();
                pl1 = list.get(i);
                List<String>stringList = new ArrayList<>();
                if (list.get(i).getPics()!=null && !list.get(i).getPics().equals("")){
                    if (list.get(i).getPics().contains("#")){
                        String pp[] = list.get(i).getPics().split("#");
                        for (int j = 0; j < pp.length; j++) {
                            stringList.add(pp[j]);
                        }
                    }
                    else {
                        stringList.add(list.get(i).getPics());
                    }
                    pl1.setPiclist(stringList);
                }
                all_list.add(pl1);
            }
            return new JsonObject("200",list.size()+"", JSON.toJSONString(all_list));
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
                plService.del(id);
            }
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
}
