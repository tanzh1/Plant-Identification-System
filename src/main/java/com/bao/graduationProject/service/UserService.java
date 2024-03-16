package com.bao.graduationProject.service;

import com.bao.graduationProject.domain.User;

public interface UserService {

    User loginService(String id, String password);

    int registerService(String id, String name, String password);

}
