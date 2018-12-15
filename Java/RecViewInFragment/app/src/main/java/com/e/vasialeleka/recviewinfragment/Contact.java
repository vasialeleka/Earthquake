package com.e.vasialeleka.recviewinfragment;

public class Contact {
    private  String Name;
    private  String Phone;
    private  int Photo;

    public Contact() {
    }

    public Contact(String name, String phone, int photo) {
        Name = name;
        Phone = phone;
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public int getPhoto() {
        return Photo;
    }
}
