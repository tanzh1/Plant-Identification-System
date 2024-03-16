package com.bao.graduationProject.service;

import com.bao.graduationProject.domain.Picture;

import java.util.List;

public interface PictureService {

    List<Picture> getHistoryPicture(String pid);

    int insertPicture(String pid, String name, String url);

    List<Picture> getAllPicture();

    List<Picture> getPictureByName(String name);

}
