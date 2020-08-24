package com.foody.controller;

import com.foody.model.User;
import com.foody.dao.UserDaoImpl;
import com.foody.model.Login;
import com.foody.utility.StrongAES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserDaoImpl userDao;


    @RequestMapping("/login")
    public Login showLogin() {
        return new Login();
    }

    @PostMapping("/login")
    public User loginProcess(@RequestBody Login login, Model model) {
        User user = null;
        user = userDao.validateUser(login);
        if(user!=null)
        user.setPassword(null);
        return user;
    }

    @RequestMapping("/xx")
    private String xx(){
        return new StrongAES().encrypt("");
    }

}
