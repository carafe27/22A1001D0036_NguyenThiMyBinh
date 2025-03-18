package com.example.deso1.nguyenthimybinh.dlu_22A1001D0036;

public class FoodItem {
    private String id;
    private String name;
    private int price;
    private int image;

    public FoodItem(String id, String name, int price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImage() { return image; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
