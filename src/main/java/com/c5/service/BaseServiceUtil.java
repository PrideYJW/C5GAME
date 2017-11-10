package com.c5.service;

import com.c5.entity.BaseEntity;
import com.sun.xml.internal.rngom.parse.host.Base;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */
public interface BaseServiceUtil {

    public List<Map<String,Object>> query4list(BaseEntity baseEntity);
    public Integer insert4Base(List<BaseEntity> list);
    public Integer upload4Base(List<BaseEntity> list);
}
