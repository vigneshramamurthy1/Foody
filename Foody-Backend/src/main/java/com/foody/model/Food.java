package com.foody.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private int price;
    private int quantity;
    private String url;
    private String formID;
    private String cartID;

    public Food(){}

    public Food( String item, int price,int quantity, String url, String formID, String cartID) {
        this.item = item;
        this.price = price;
        this.quantity=quantity;
        this.url=url;
        this.formID=formID;
        this.cartID=cartID;
    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", url='" + url + '\'' +
                ", formID='" + formID + '\'' +
                ", cartID='" + cartID + '\'' +
                '}';
    }
}
