package com.wzh.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.Food;
import com.wzh.model.Orders;
import com.wzh.model.User;
import com.wzh.service.FoodService;
import com.wzh.service.OrdersService;
import com.wzh.service.SpotService;
import com.wzh.service.UserService;
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
import java.util.*;

@Controller
@RequestMapping("/food")
public class FoodController {
    @Resource
    FoodService foodService;

    @Value("${server.port}")
    private String dkh;

    @RequestMapping("/tolist")
    public String tolist(Model model) {
        model.addAttribute("dkh", dkh);
        return "Food/list";

    }
    //    分页查询
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String nickname){
        Page<Food> pageInfo = PageHelper.startPage(page, limit, "time desc");
        Food food = new Food();
        food.setName(nickname);
        List<Food> list = foodService.findAll(food);
        PageInfo<Food> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("id", "");
        return "Food/add";
    }

    //新增数据
    @RequestMapping("/add")
    @ResponseBody
    public JsonObject save(Food food) {
        try {
            food.setId(IdUtil.simpleUUID());
            food.setTime(Time.getTime());
            foodService.save(food);
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
                foodService.del(id);
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
            Food u = foodService.get(id);
            return new JsonObject("200", "", u);
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Food u) {
        try {
            foodService.update(u);
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
        return "Food/add";
    }

    @RequestMapping("findAll")
    @ResponseBody
    public JsonObject findAll(Food food){
        try {
            List<Food> list = foodService.findAll(food);
            return new JsonObject("200","", JSON.toJSONString(list));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }
    }
    @RequestMapping("/findId")
    @ResponseBody
    public JsonObject findId(@RequestParam("id") String id) {
        try {
            Food u = foodService.get(id);
            return new JsonObject("200", "",  JSON.toJSONString(u));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }

    }
    @Resource
    UserService userService;
    @Resource
    OrdersService ordersService;


    @RequestMapping("findtj")
    @ResponseBody
    public JsonObject findtj(@RequestParam("uid") String uid){
        try {
            System.err.println("测试");
            Map<String, Map<String, Double>> allUsers = new HashMap<String, Map<String, Double>>();
            User u1 = new User();
            List<User> users = userService.findAll(u1);
            Food food = new Food();
            List<Food> goodsInfos = foodService.findAll(food);
            for (User user : users) {
                System.err.println(user.getId());
                Orders orders = new Orders();
                orders.setLx("美食");
                orders.setUid(user.getId());
                List<Orders> goodsValues = ordersService.findAll(orders);
                System.out.println("goodsValues"+goodsValues.toString());
                if (goodsValues.size()>0){
                    Map<String, Double> u = new HashMap<String, Double>();
                    for (Food goodsInfo:goodsInfos) {
                        String GId = String.valueOf(goodsInfo.getId());
                        for (Orders value:goodsValues){
                            String browseGId = String.valueOf(value.getId());
                            if (browseGId.equals(GId)){
                                u.put(String.valueOf(GId), Double.valueOf(value.getId()));
                                break;
                            }else {
                                u.put(String.valueOf(GId),0.0);
                            }
                        }
                    }
                    allUsers.put(String.valueOf(user.getId()),u);
                    System.out.println("userID:"+user.getId()+",:"+u.toString());
                }
            }
            Map<String, Double> simUserSimMap = new HashMap<String, Double>();//存放相似度集合
            String Id = String.valueOf(uid);
            Map<String, Double> per=allUsers.get(Id);//获取当前用户
            //遍历每个用户
            for (Map.Entry<String, Map<String, Double>> userPerfEn : allUsers.entrySet()){
                String perId = userPerfEn.getKey();
                if(!Id.equals(perId)){//遍历到的用户非当前用户
                    double sim = getUserSimilar(per, userPerfEn.getValue());
                    System.out.println(Id+"与"+perId+"的相似度为："+String.valueOf(sim));
                    simUserSimMap.put(perId,sim);
                }
            }
            //获取相似度最高的用户，对simUserSimMap中的数据进行排序
            List<Map.Entry<String, Double>> wordMap = new ArrayList<>(simUserSimMap.entrySet());
            Collections.sort(wordMap, new Comparator<Map.Entry<String, Double>>() {// 根据value排序
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    double result = o2.getValue() - o1.getValue();
                    if (result > 0)
                        return 1;
                    else if (result == 0)
                        return 0;
                    else
                        return -1;
                }
            });
            for(Map.Entry<String, Double> set:wordMap){
                System.out.println("map美食:"+set.getKey() +"   "+set.getValue());
            }
            String maxID = wordMap.get(0).getKey();
            System.out.println("maxID"+maxID);
            Orders orders = new Orders();
            orders.setLx("美食");
            orders.setUid(maxID);
            List<Orders> browses = ordersService.findAll(orders);
            System.err.println("browses="+browses);
            List<Food> goodsInfoList = new ArrayList<>();
            for (int i = 0; i < browses.size(); i++){
                Food food1 = new Food();
                food1.setId(browses.get(i).getId());
                List<Food> goodList = foodService.findAll(food1);
                Food goods = goodList.get(0+i);
                System.out.println(String.valueOf(goods.getId()));
                goodsInfoList.add(goods);
            }

            return new JsonObject("200","", JSON.toJSONString(goodsInfoList));
        }
        catch (Exception e){
            return new JsonObject("500","失败","");
        }
    }
    private double getUserSimilar(Map<String, Double> pm1, Map<String, Double> pm2) {
        System.err.println("计算");
        int n = pm1.size();// 数量n
        Double sxy = 0.0;// Σxy=x1*y1+x2*y2+....xn*yn
        Double sx = 0.0;// Σx=x1+x2+....xn
        Double sy = 0.0;// Σy=y1+y2+...yn
        Double sx2 = 0.0;// Σx2=(x1)2+(x2)2+....(xn)2
        Double sy2 = 0.0;// Σy2=(y1)2+(y2)2+....(yn)2
        for (Map.Entry<String, Double> pme : pm1.entrySet()) {
            String key = pme.getKey();
            System.out.println("key:"+key);
            Double x = pme.getValue();
            Double y = pm2.get(key);
            System.out.println("x:"+String.valueOf(x)+",y:"+String.valueOf(y));

            if (x != null && y != null) {
                sxy += x * y;//x*y求和
                sx += x;//x求和
                sy += y;//y求和
                sx2 += Math.pow(x, 2.0);//x的平方求和
                sy2 += Math.pow(y, 2.0);//y的平方求和
                System.out.println(":sxy:"+sxy+",sx:"+sx+",sy"+",sx2:"+sx2+",sy2:"+sy2);

            }
        }
        double sd = sxy - (sx * sy) / n;
        double sm = Math.sqrt((sx2 - (Math.pow(sx, 2) / n)) * (sy2 - (Math.pow(sy, 2) / n)));
        System.out.println("sd:"+sd+",sm:"+sm);
        return Math.abs(sm == 0 ? 1 : sd / sm);
    }

}
