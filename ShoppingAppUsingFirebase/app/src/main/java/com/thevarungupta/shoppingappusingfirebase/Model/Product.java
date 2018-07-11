package com.thevarungupta.shoppingappusingfirebase.Model;

public class Product {

    private String pid;
    private String pName;
    private int mrp;
    private int price;
    private String description;
    private String catId;

    public Product(){

    }

    public Product(String pid, String pName, int mrp, int price, String description, String catId) {
        this.pid = pid;
        this.pName = pName;
        this.mrp = mrp;
        this.price = price;
        this.description = description;
        this.catId = catId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
