package com.example.artibe.objetos;

public class User {
    public String userName;
    public String email;
    public String urlimg;

    public User(){

    }
    public User(String userName, String email,String urlimg){
        this.userName = userName;
        this.email = email;
        this.urlimg = urlimg;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }
}
