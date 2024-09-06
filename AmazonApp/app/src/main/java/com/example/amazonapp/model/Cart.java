package com.example.amazonapp.model;

public class Cart {
    private String pid, name, price;

    public Cart(){}

    public Cart(String pid, String name, String price){
        this.pid=pid;
        this.name=name;
        this.price=price;
    }

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
