package com.foody.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foody.model.Cart;
import com.foody.model.Food;
import com.foody.model.NewCart;
import com.foody.model.NewFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CartDaoImpl{

    @Autowired
    CartDao cartDao;

    @Autowired
    FoodDao foodDao;

    @Value("${fileStorage}")
    private String storagePath;

    public void saveToCart(int id, int quantity, String userName, int cost){
        Cart cart= new Cart();
        cart.setUser_name(userName);
        cart.setQuantity_id(id);
        cart.setQuantity(quantity);
        cart.setCost(quantity * cost);
        cartDao.save(cart);
    }

    public void checkout(String userName){
        List<Cart> carts =cartDao.findAll();
        List<Food> foods = foodDao.findAll();
        for(int i=0; i < carts.size();i++){
            if(carts.get(i).getUser_name().equalsIgnoreCase(userName)){
                for(int j=0; j < foods.size();j++){
                    if(foods.get(j).getId() == carts.get(i).getQuantity_id()){
                        foods.get(j).setQuantity(foods.get(j).getQuantity()-carts.get(i).getQuantity());
                    }
                }
            }
        }
        foodDao.saveAll(foods);
    }

    public List<Cart> getAllCart(){
        return cartDao.findAll();
    }

    public void addItems(NewCart[] cart){
        List<Food> foods = foodDao.findAll();
        for(int i=0;i<foods.size();i++){
            foods.get(i).setQuantity(foods.get(i).getQuantity()+cart[i].getQuantity());
        }
        foodDao.saveAll(foods);
    }

    public boolean addNewItem(MultipartFile file, String newFoodData) throws IOException {
        NewFood newFood = new ObjectMapper().readValue(newFoodData,NewFood.class);
        if(!file.isEmpty())
        if(saveFileToAssets(file))
        {
            foodDao.save(new Food(newFood.getName(),newFood.getPrice(),newFood.getQuantityAvailable(),"/assets/"+file.getOriginalFilename(),"",""));
//            foodDao.save(new Food(newFood.getId(),newFood.getName(),newFood.getPrice(),newFood.getQuantityAvailable(),"\\"+file.getOriginalFilename(),"",""));
        }
        return true;
    }

    public boolean addNewItemWithUrl(String newFoodData) throws IOException {
        NewFood newFood = new ObjectMapper().readValue(newFoodData,NewFood.class);
        foodDao.save(new Food(newFood.getName(),newFood.getPrice(),newFood.getQuantityAvailable(),newFood.getFileDataF(),"",""));
        return true;
    }

    private boolean saveFileToAssets(MultipartFile file) throws IOException {
        Path filepath = Paths.get(storagePath, file.getOriginalFilename());
        file.transferTo(filepath);
        return true;
    }

    public int calculateTotal(String userName){
        int total=0;
        List<Cart> carts = cartDao.findAll();

        for(int i=0;i<carts.size();i++)
        {
            if(carts.get(i).getUser_name().equalsIgnoreCase(userName)) {
                total += carts.get(i).getCost();
            }
        }
        return total;
    }

    public boolean itemIdAvailable(Long itemId) {
        return foodDao.findById(itemId).isPresent();
    }

    public boolean updateQuantity(Long id, Long value){
        Food getFood = foodDao.getOne(id);
        getFood.setQuantity(Math.toIntExact(value));
        foodDao.save(getFood);
        return true;
    }
}
