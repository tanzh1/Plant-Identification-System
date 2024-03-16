package com.bao.graduationProject.controller;

import com.bao.graduationProject.service.HistoryService;
import com.bao.graduationProject.service.PictureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@RequestMapping("/search")
public class SearchController {

    private String generalPid;

    @Resource
    private HistoryService historyService;

    @Resource
    private PictureService pictureService;

    @Resource
    HttpServletRequest request;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
//        System.out.println("upload");
//        System.out.println(file);
        if(file.isEmpty()){ //若文件选择为空,就上传失败
            return "上传失败,请选择文件!";
        }
        String fileName=file.getOriginalFilename();//获取文件上传的文件名

        String filePath = "D:/fileSource"; //指定到上传的文件路径
        String filePath2 = "D:\\IntelliJ IDEA 2021.1.3\\WORKS\\study\\graduation-project\\src\\main\\resources\\static\\picture";
        File dir = new File(filePath);
        File dir2 = new File(filePath2);
        if (!dir.exists()) {  //若路径不存在,则创建一个这样的文件夹
            dir.mkdir();
        }
        if (!dir2.exists()) {
            dir2.mkdir();
        }
        try {
            File file1 = new File(filePath, "sample.jpg"); //创建了一个File对象，名字是file1 ，路径是filePath，名字是fileName。
//然后就可以调用这个对象的相关方法完成文件创建，删除，读取，写入等操作
            file.transferTo(file1);    //将上传的文件写入创建好的文件中

            generalPid = String.valueOf(System.currentTimeMillis());
            System.out.println("时间戳："+System.currentTimeMillis()+"="+generalPid);

            //保存到静态资源
            String pUrl = generalPid+".jpg";
            Path path1 = Paths.get(filePath, "/sample.jpg");
            Path path2 = Paths.get("D:\\IntelliJ IDEA 2021.1.3\\WORKS\\study\\graduation-project\\dd\\",pUrl);
            Files.copy(path1, path2);

            //保存pid到session
            HttpSession session = request.getSession();

            System.out.println("pid:" + generalPid);
            String userid = (String) session.getAttribute("userid");
            if (userid == null){
                userid = "00000000";
            }

            //保存history到数据库
            historyService.insertHistory(userid, generalPid);

            return ("上传成功!文件路径为:"+filePath+"/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }


    @RequestMapping("/search")
    public String search(){

        String result="";
        System.out.println("searchController");
        Process proc;

        try {
//            proc = Runtime.getRuntime().exec("python D:\\VS_Code\\deepLearning\\study\\0424-prediction.py");// 执行py文件
            proc = Runtime.getRuntime().exec("python D:\\VS_Code\\deepLearning\\study\\prediction-0504-part.py");
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result = line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap map = new HashMap();
        map.put("orchids", "兰花");
        map.put("poppies", "罂粟花");
        map.put("roses", "玫瑰");
        map.put("sunflowers", "向日葵");
        map.put("tulips", "郁金香");


        String pid = generalPid;
        System.out.println("pid2:"+pid);

        //保存picture到数据库
        String pUrl = pid+".jpg";
        pictureService.insertPicture(pid, (String) map.get(result), pUrl);

        return (String) map.get(result);
    }


}
