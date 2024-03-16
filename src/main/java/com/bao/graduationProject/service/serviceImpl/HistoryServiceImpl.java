package com.bao.graduationProject.service.serviceImpl;

import com.bao.graduationProject.dao.HistoryDao;
import com.bao.graduationProject.domain.History;
import com.bao.graduationProject.service.HistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private HistoryDao historyDao;

    @Override
    public List<History> getHistory(String uid) {
//        System.out.println("selectHistory uid="+uid);
        QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
        queryWrapper.eq("uid", uid);
        List<History> historyList = historyDao.selectList(queryWrapper);
//        System.out.println("historyList:");
//        System.out.println(historyList);
        return historyList;
    }

    @Override
    public int insertHistory(String uid, String pid) {

        History history = new History();
        history.setUid(uid);
        history.setPid(pid);
        historyDao.insert(history);

        return 0;
    }


}
