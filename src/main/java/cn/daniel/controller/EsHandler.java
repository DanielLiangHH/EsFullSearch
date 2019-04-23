package cn.daniel.controller;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.daniel.entity.User;
import cn.daniel.repository.UserDao;
import cn.daniel.service.LayerDataService;

@RestController
public class EsHandler {

    @Autowired
    private UserDao userDao;

    @Resource
    private LayerDataService layerDataService;

    @ResponseBody
    @RequestMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userDao.save(user);
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Optional<User> findById(String id) {
        return userDao.findById(id);
    }
}
