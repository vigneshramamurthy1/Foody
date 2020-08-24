package com.foody;

import com.foody.dao.FoodDaoImpl;
import com.foody.model.Food;
import com.foody.dao.CartDaoImpl;
import com.foody.dao.UserDao;
import com.foody.dao.UserDaoImpl;
import com.foody.model.Login;
import com.foody.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodyApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    UserDaoImpl userDaoImpl;

    @Autowired
    FoodDaoImpl foodDao;

    @Autowired
    CartDaoImpl cartDao;

    @Test
    public void contextLoads() {
        Login login = new Login();
        login.setUsername("vigi");
        login.setPassword("abcd1234");

        User user = userDaoImpl.validateUser(login);
        Assert.assertEquals("vigi",user.getFirstname());
        Assert.assertEquals("vigi",user.getLastname());
        Assert.assertEquals("vigin@gmail.com",user.getEmail());
        Assert.assertEquals(Long.valueOf(7654654),user.getPhone());
    }

    @Test
    public void checkFoodTable(){
        Food food = new Food();
        food.setId(Long.valueOf(01));
        Food food1 = foodDao.validateFoodInfo(food.getId());
        Assert.assertEquals("Coffee",food1.getItem());
        Assert.assertEquals(50,food1.getPrice());
    }


    /*Test*/


}
