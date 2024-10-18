package com.example.madproject.activity.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String pic;
    private String Decsription;
    private Double Fee;
    private int NumberInCart;
    public FoodDomain(String title, String pic, String decsription, Double fee) {
        this.title = title;
        this.pic = pic;
        Decsription = decsription;
        Fee = fee;
    }
    public String getTitle() {
        return title;
    }

    public String getPic() {
        return pic;
    }

    public String getDecsription() {
        return Decsription;
    }

    public Double getFee() {
        return Fee;
    }

    public int getNumberInCart() {
        return NumberInCart;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDecsription(String decsription) {
        Decsription = decsription;
    }

    public void setFee(Double fee) {
        Fee = fee;
    }

    public void setNumberInCart(int numberInCart) {
        NumberInCart = numberInCart;
    }
}
