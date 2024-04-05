package com.example.myapplication;

public class MainModel {

    String cost, email, p_name, p_qty,itemId;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_qty() { // Corrected getter method name
        return p_qty;
    }

    public void setP_qty(String p_qty) { // Corrected setter method name
        this.p_qty = p_qty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) { // Corrected setter method name
        this.itemId = itemId;
    }
}