package com.example.alex.electrozon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by alex on 4/10/17.
 */

public class Product implements Parcelable{
    private int Id;
    private String productName;
    private String productDesc;
    private long qty;
    private long price;

    public Product(){}

    public Product(int Id, String productName, String productDesc, long qty, long price) {
        this.Id = Id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.qty = qty;
        this.price = price;
    }

    protected Product(Parcel in) {
        this.Id = in.readInt();
        this.productName = in.readString();
        this.productDesc = in.readString();
        this.qty = in.readLong();
        this.price = in.readLong();
    }
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getProductId() {
        return Id;
    }
    public void setProductId(int Id) {
        this.Id = Id;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getproductDesc() {
        return productDesc;
    }
    public void setproductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public long getQty() {
        return qty;
    }
    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }

    public String toString(){
        return " $" + getPrice() + "        " +
                "   " + getProductName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(productName);
        dest.writeString(productDesc);
        dest.writeLong(qty);
        dest.writeLong(price);

    }
}
