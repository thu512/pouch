package com.jinjoo.pouch.model;

public class Cosmetic {

    String img;
    String name;
    String brand;

    @Override
    public String toString() {
        return "Cosmetic{" +
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    public Cosmetic(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
