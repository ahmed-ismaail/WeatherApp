package com.example.contactsapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity(foreignKeys = @ForeignKey(entity = Contact.class,parentColumns = "idd",
//        childColumns = "contactIdd",onDelete = CASCADE))
public class Phone {

//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    private int contactIdd;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("home")
    @Expose
    private String home;

    @SerializedName("office")
    @Expose
    private String office;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mobile='" + mobile + '\'' +
                ", home='" + home + '\'' +
                ", office='" + office + '\'' +
                '}';
    }

//    public int getContactIdd() {
//        return contactIdd;
//    }
//
//    public void setContactIdd(int contactIdd) {
//        this.contactIdd = contactIdd;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
