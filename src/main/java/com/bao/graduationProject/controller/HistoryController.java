package com.bao.graduationProject.controller;

import com.bao.graduationProject.domain.History;
import com.bao.graduationProject.domain.Picture;
import com.bao.graduationProject.service.HistoryService;
import com.bao.graduationProject.service.PictureService;
import com.bao.graduationProject.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @GetMapping("/hello")
    public String hello(){
        return "helloHistory";
    }

    @Resource
    private PictureService pictureService;

    @Resource
    private HistoryService historyService;

    @GetMapping("/getHistory")
    public Result<List<Picture>> getUserHistoryController(@RequestParam String uid){
//        System.out.println("getHistory,uid="+uid);
        List<History> historyList = historyService.getHistory(uid);
        List<Picture> ResultPictureList = new ArrayList<Picture>();;
        for (History history : historyList) {
            String pid = history.getPid();
//            System.out.println("history:"+history.getPid());
            List<Picture> pictureList = pictureService.getHistoryPicture(pid);
//            System.out.println("pictureList:"+pictureList);
            ResultPictureList.addAll(pictureList);
        }
        return Result.success(ResultPictureList);
    }

}
