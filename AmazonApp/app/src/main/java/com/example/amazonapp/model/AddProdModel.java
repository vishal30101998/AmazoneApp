package com.example.amazonapp.model;

public class AddProdModel {
    String pid;
    String name;
    String price;
    String description;
    String category;
    String img;
    String date;
    String time;

    public AddProdModel(){}

    public AddProdModel(String pid, String name, String price, String description, String category,
                        String img, String date, String time){
        this.pid=pid;
        this.name=name;
        this.price=price;
        this.description=description;
        this.category=category;
        this.img=img;
        this.date=date;
        this.time=time;
    }

    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
