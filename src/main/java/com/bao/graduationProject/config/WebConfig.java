package com.bao.graduationProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 项目所在的位置
        String basePath = System.getProperty("user.dir");

        // 图片，可访问的资源
        //其中/files/**表示前端请求的url的前缀。("file:"+basePath+"/files/")是文件真实的存储路径前缀。也就是前端发起获取图片的请求，不需要带上存储路径前缀
        // 项目中的资源是使用classpath:/开头，而本地文件夹需要使用file:/ 开头，使用文件协议加载文件
        // 结尾一定要有/或者\\，推荐使用/，如果不加没效果
        registry.addResourceHandler("/dd/**")
                .addResourceLocations(
                        "file:"+basePath+"/dd/"
                );
    }
}
