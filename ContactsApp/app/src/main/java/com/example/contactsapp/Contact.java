package com.example.contactsapp;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "CONTACTS")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int idd;

    @ColumnInfo(name = "NAME")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "EMAIL")
    @SerializedName("email")
    @Expose
    private String email;

    @ColumnInfo(name = "ADDRESS")
    @SerializedName("address")
    @Expose
    private String address;

    @ColumnInfo(name = "GENDER")
    @SerializedName("gender")
    @Expose
    private String gender;

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @SerializedName("phone")
    @Expose
    private Phone phone;

    @Override
    public String toString() {
        return "Contact{" +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
