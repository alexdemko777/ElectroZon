package com.example.alex.electrozon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 4/27/17.
 */

public class Customer implements Parcelable {
    private String FullName;
    private String Address;
    private String City;
    private String Zip;
    private String CardNumber;
    private String ExpDate;

    public Customer(){}

    public Customer(String FullName, String Address, String City, String Zip, String CardNumber, String ExpDate) {
        this.FullName = FullName;
        this.Address = Address;
        this.City = City;
        this.Zip = Zip;
        this.CardNumber = CardNumber;
        this.ExpDate = ExpDate;
    }

    protected Customer(Parcel in) {
        this.FullName = in.readString();
        this.Address = in.readString();
        this.City = in.readString();
        this.Zip = in.readString();
        this.CardNumber = in.readString();
        this.ExpDate = in.readString();

    }
    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };


    public String getFullName() {
        return FullName;
    }
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }
    public void setCity(String City) {
        this.City = City;
    }

    public String getZip() {
        return Zip;
    }
    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getCardNumber() {
        return CardNumber;
    }
    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getExpDate() {
        return ExpDate;
    }
    public void setExpDate(String ExpDate) {
        this.ExpDate = ExpDate;
    }


    public String toString(){
        return  "Name: " + getFullName() +"\n"+
                "Address: " + getAddress() +"\n"+
                "City: " + getCity() +"\n"+
                "Zip: " + getZip() +"\n"+
                "CardNumber: " + getCardNumber() +"\n"+
                "ExpDate: " + getExpDate();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FullName);
        dest.writeString(Address);
        dest.writeString(City);
        dest.writeString(Zip);
        dest.writeString(CardNumber);
        dest.writeString(ExpDate);

    }
}