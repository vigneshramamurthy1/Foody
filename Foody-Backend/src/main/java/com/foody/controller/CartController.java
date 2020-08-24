package com.foody.controller;

import com.foody.dao.CartDaoImpl;
import com.foody.model.NewCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    CartDaoImpl cartDao;


    @GetMapping("/cart/{id}/{quantity}/{userName}/{cost}")
    public int getTotal(@PathVariable("id") int id, @PathVariable("quantity") int quantity, @PathVariable("userName") String userName,  @PathVariable("cost") int cost){
        cartDao.saveToCart(id, quantity, userName, cost);
        return cartDao.calculateTotal(userName);
    }

    @RequestMapping("/checkout/{userName}")
    public boolean checkout(@PathVariable("userName") String userName){
        cartDao.checkout(userName);
        return true;
    }

    @PostMapping("/addToCart")
    public NewCart[] increaseQuantity(@RequestBody NewCart[] cart, Model model){
        cartDao.addItems(cart);
        return cart;
    }

    @PostMapping("/addNewItem")
    public boolean addNewItem(@RequestParam("file") MultipartFile file, @RequestParam("newFoodItem") String newFoodData) throws IOException {
        return cartDao.addNewItem(file,newFoodData);
    }


    @PostMapping("/addNewItemUrl")
    public boolean addNewItemByUrl(@RequestParam("newFoodItem") String newFoodData) throws IOException {
        return cartDao.addNewItemWithUrl(newFoodData);
    }

    @PostMapping("/checkItemId")
    public boolean checkItemId(@RequestBody Long itemId, Model model){
        return !cartDao.itemIdAvailable(itemId);
    }

    @GetMapping("/updateQuantity/{id}/{quantity}")
    public boolean updateItemById(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) throws IOException {
        return cartDao.updateQuantity(id, quantity);
    }
}
