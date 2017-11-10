package com.c5.service.impl;

import com.c5.Util.SQLUtil;
import com.c5.dao.BaseDaoUtil;
import com.c5.entity.BaseEntity;
import com.c5.exceptions.NullEx;
import com.c5.service.BaseServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */
@Service
public class BaseServiceUtilImpl implements BaseServiceUtil {

    @Autowired
    BaseDaoUtil baseDaoUtil;
    @Autowired
    SQLUtil sqlUtil;
    @Transactional
    public List<Map<String, Object>> query4list(BaseEntity baseEntity) {
        String SQL=sqlUtil.query4ListSQLUtil(baseEntity);
        if(SQL==null){
            throw new NullEx("SQL为空");
        }
        List<Map<String, Object>> list=baseDaoUtil.query4list(SQL);
        return list;
    }
    @Transactional
    public Integer insert4Base(List<BaseEntity> list) {
        List<String> ListSQL=sqlUtil.insert4SQLUtil(list);
        if(ListSQL==null){
            throw new NullEx("SQL为空");
        }
        for (String sql : ListSQL) {
            Integer num=baseDaoUtil.insert4Base(sql);
            if(num==0){
                return 0;
            }
        }
        return 1;
    }
    @Transactional
    public Integer upload4Base(List<BaseEntity> list) {
        List<String> ListSQL=sqlUtil.upload4SQLUtil(list);
        if(ListSQL==null){
            throw new NullEx("SQL为空");
        }
//        for (String sql : ListSQL) {
//            Integer num=baseDaoUtil.upload4Base(sql);
//            if(num==0){
//                return 0;
//            }
//        }
        return 1;
    }
}
