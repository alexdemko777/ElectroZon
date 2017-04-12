package com.example.alex.electrozon;

/**
 * Created by alex on 4/10/17.
 */

public class Product {
    private int Id;
    private String productName;
    private long qty;
    private long price;

    public Product(){}

    public int getProductId() {
        return Id;
    }

    public Product(int Id, String productName, long qty, long price) {
        this.Id = Id;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
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
        return "Id: " + getProductId() + " " +
                "   " + getProductName() + " " +
                "    Qty: " + getQty() + " " +
                " $" + getPrice();
    }
}
