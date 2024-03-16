package com.bao.graduationProject;

import com.bao.graduationProject.dao.HistoryDao;
import com.bao.graduationProject.dao.PictureDao;
import com.bao.graduationProject.dao.UserDao;
import com.bao.graduationProject.domain.History;
import com.bao.graduationProject.domain.Picture;
import com.bao.graduationProject.domain.User;
import com.bao.graduationProject.service.PictureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class GraduationProjectApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private HistoryDao historyDao;
    @Autowired
    private PictureDao pictureDao;

    @Resource
    private PictureService pictureService;

    @Test
    void contextLoads() {
//        List<User> userList = userDao.selectList(null);
//        System.out.println(userList);
        QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
        String uid = "123456";
        queryWrapper.eq("uid",uid);
        List<History> historyList = historyDao.selectList(queryWrapper);
        System.out.println(historyList);
    }

    @Test
    void pictureTest() {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<Picture>();
        String pid = "01";
        queryWrapper.eq("pid",pid);
        List<Picture> pictureList = pictureDao.selectList(queryWrapper);
        System.out.println(pictureList);
    }

    @Test
    void insertHistoryTest() {
        pictureService.insertPicture("04", "hhh", "hhh.jpg");
    }

}
