package com.bao.graduationProject.service;

import com.bao.graduationProject.domain.History;

import java.util.List;

public interface HistoryService {

    List<History> getHistory(String uid);

    int insertHistory(String uid, String pid);

}
