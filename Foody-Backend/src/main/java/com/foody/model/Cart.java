package com.foody.model;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity_id;
    private int quantity;
    private int cost;
    private String user_name;

    public Cart(){}

    public Cart(int quantity_id, int quantity, String user_name, int cost) {
        this.quantity_id = quantity_id;
        this.quantity = quantity;
        this.user_name = user_name;
        this.cost= cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity_id() {
        return quantity_id;
    }

    public void setQuantity_id(int quantity_id) {
        this.quantity_id = quantity_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", quantity_id=" + quantity_id +
                ", quantity=" + quantity +
                ", userName=" + user_name +
                ", cost=" + cost +
                '}';
    }
}
