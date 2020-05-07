package com.jinjoo.pouch.model.naver;

public class Item {
    String image;
    String category3;


    @Override
    public String toString() {
        return "Item{" +
                "image='" + image + '\'' +
                ", category3='" + category3 + '\'' +
                '}';
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
