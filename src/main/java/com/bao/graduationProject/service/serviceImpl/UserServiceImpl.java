package com.bao.graduationProject.service.serviceImpl;

import com.bao.graduationProject.dao.UserDao;
import com.bao.graduationProject.domain.User;
import com.bao.graduationProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User loginService(String id, String password) {

        User user = userDao.selectById(id);
//        List<User> userList = userDao.selectList(null);
//        System.out.println("pwd:"+user.getPassword());
        return user;
    }

    @Override
    public int registerService(String id, String name, String password) {

        User userRegister = new User();
        userRegister.setId(id);
        userRegister.setName(name);
        userRegister.setPassword(password);
        int insert = 0;
        try {
            userDao.insert(userRegister);
            System.out.println("register pass");
            return 1;
        }catch (Exception e) {
            System.out.println("register error");
            return 0;
        }

    }
}
