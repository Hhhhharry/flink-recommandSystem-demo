package com.demo.domain;


public class ContactEntity {

    private int id;
    private String pic_Url;
    private String item_Name;
    private String sub_Name;
    private double mart_Price;
    private String brand_Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic_Url() {
        return pic_Url;
    }

    public void setPic_Url(String pic_Url) {
        this.pic_Url = pic_Url;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getSub_Name() {
        return sub_Name;
    }

    public void setSub_Name(String sub_Name) {
        this.sub_Name = sub_Name;
    }

    public double getMart_Price() {
        return mart_Price;
    }

    public void setMart_Price(double mart_Price) {
        this.mart_Price = mart_Price;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", picUrl='" + pic_Url + '\'' +
                ", itemName='" + item_Name + '\'' +
                ", subName='" + sub_Name + '\'' +
                ", martPrice=" + mart_Price +
                ", brandName='" + brand_Name + '\'' +
                '}';
    }
}
