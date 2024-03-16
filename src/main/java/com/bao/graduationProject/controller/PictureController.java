package com.bao.graduationProject.controller;

import com.bao.graduationProject.domain.Picture;
import com.bao.graduationProject.service.PictureService;
import com.bao.graduationProject.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PictureController {

    @Resource
    private PictureService pictureService;

    @GetMapping("/explore")
    public Result<List<Picture>> explore(){
        List<Picture> pictureList = pictureService.getAllPicture();
        return Result.success(pictureList);
    }

    @GetMapping("/search")
    public Result<List<Picture>> search(String name){
        List<Picture> pictureList = pictureService.getPictureByName(name);
        return Result.success(pictureList);
    }
}
