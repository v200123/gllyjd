package com.wzh.until;

public class JsonObjectPrice {
  private  String code;
  private  String msg;

    public String getIsselect() {
        return isselect;
    }

    public void setIsselect(String isselect) {
        this.isselect = isselect;
    }

    private String isselect;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public JsonObjectPrice(String code, String msg, String isselect, String price, Object data) {
        this.code = code;
        this.msg = msg;
        this.isselect = isselect;
        this.price = price;
        this.data = data;
    }

    private  String price;

    private  Object data;

    public String getCode() {
        return code;
    }





    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
