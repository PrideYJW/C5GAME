package com.c5.dao;

import com.c5.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */
public interface BaseDaoUtil {

    public List<Map<String,Object>> query4list(String sql);
    public Integer insert4Base(String sql);
    public Integer upload4Base(String sql);
}
