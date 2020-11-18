package com.sam.service;

import com.sam.dao.WriteDao;
import com.sam.pojo.WriteEntity;
import org.springframework.stereotype.Service;

@Service("WriteService")
public class WriteService {

    public static WriteEntity add(String userId){
        return WriteDao.addWirte(userId);
    }
}
