package com.bao.graduationProject.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Picture {

    @TableId
    private String pid;
    private String name;
    private String url;




}
