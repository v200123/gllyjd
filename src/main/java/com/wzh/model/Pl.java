package com.wzh.model;

import java.util.Date;
import java.util.List;

public class Pl {
    private String id;

    private String pics;
    private String head;

    public List<String> getPiclist() {
        return piclist;
    }

    public void setPiclist(List<String> piclist) {
        this.piclist = piclist;
    }

    private List<String> piclist;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

    private String uid;

    private String tid;

    private String msg;

    private String rate;

    private String time;

    private String fwrate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFwrate() {
        return fwrate;
    }

    public void setFwrate(String fwrate) {
        this.fwrate = fwrate;
    }
}
