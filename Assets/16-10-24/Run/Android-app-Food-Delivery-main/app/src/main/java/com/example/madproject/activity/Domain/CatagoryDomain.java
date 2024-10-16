package com.example.madproject.activity.Domain;

public class CatagoryDomain {
    private  String Title;
    private String pic;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public  CatagoryDomain(String title, String pic){
        this.Title=title;
        this.pic=pic;
    }

}
