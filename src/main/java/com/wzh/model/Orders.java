package com.wzh.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private String id;

    private String uid;

    private String tid;

    private String name;

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", path1='" + path1 + '\'' +
                ", path2='" + path2 + '\'' +
                ", path3='" + path3 + '\'' +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", traffic='" + traffic + '\'' +
                ", lx='" + lx + '\'' +
                ", room='" + room + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    private String pic;

    private String type;

    private BigDecimal price;

    private String content;

    private String path1;

    private String path2;

    private String path3;

    private String time;

    private String address;

    private String traffic;

    private String lx;

    private String room;

    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
