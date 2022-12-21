package com.example.inclass07;

import org.json.JSONException;
import org.json.JSONObject;

public class Contacts<Public> {
    String Name, Email, Phone, PhoneType;
    int Cid;

    public Contacts(String name, String email, String phone, String phoneType, int cid) {
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.PhoneType = phoneType;
        this.Cid = cid;
    }

    public Contacts(JSONObject jsonObject) {
        try {
            this.Cid = jsonObject.getInt("Cid");
            this.Name = jsonObject.getString("Name");
            this.Email = jsonObject.getString("Email");
            this.Phone = jsonObject.getString("Phone");
            this.PhoneType = jsonObject.getString("PhoneType");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + Name + '\'' +
                ", email='" + Email + '\'' +
                ", phone='" + Phone + '\'' +
                ", phoneType='" + PhoneType + '\'' +
                ", cid=" + Cid +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType(String phoneType) {
        this.PhoneType = phoneType;
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        this.Cid = cid;
    }
}
