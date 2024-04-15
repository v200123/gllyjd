package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.wzh.model.Orders;
import com.wzh.model.Spot;
import com.wzh.model.User;
import com.wzh.service.OrdersService;
import com.wzh.until.JsonObject;
import com.wzh.until.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    OrdersService ordersService;
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(Orders orders) {
        try {
            orders.setId(IdUtil.simpleUUID());
            orders.setTime(Time.getTime());
            ordersService.save(orders);
            System.out.println("添加数据");
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @RequestMapping("findAll")
    @ResponseBody
    public JsonObject findAll(Orders orders){
        try {
            List<Orders> list = ordersService.findAll(orders);
            return new JsonObject("200","", JSON.toJSONString(list));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Orders u) {
        try {
            ordersService.update(u);
            return new JsonObject("200", "", null);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
}
