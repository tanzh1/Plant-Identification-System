package com.bao.graduationProject.service.serviceImpl;

import com.bao.graduationProject.dao.PictureDao;
import com.bao.graduationProject.domain.Picture;
import com.bao.graduationProject.service.PictureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Resource
    private PictureDao pictureDao;

    @Override
    public List<Picture> getHistoryPicture(String pid) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<Picture>();
        queryWrapper.eq("pid", pid);
        List<Picture> pictureList = pictureDao.selectList(queryWrapper);
        return pictureList;
    }

    @Override
    public int insertPicture(String pid, String name, String url){
        Picture picture = new Picture();
        picture.setName(name);
        picture.setPid(pid);
        picture.setUrl(url);
        pictureDao.insert(picture);
        return 1;
    }

    @Override
    public List<Picture> getAllPicture() {
        List<Picture> pictureList = pictureDao.selectList(null);
        return pictureList;
    }

    @Override
    public List<Picture> getPictureByName(String name) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<Picture>();
        queryWrapper.eq("name", name);
        List<Picture> pictureList = pictureDao.selectList(queryWrapper);
        return pictureList;
    }

}
