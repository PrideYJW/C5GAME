package com.c5.dao.impl;

import com.c5.dao.BaseDaoUtil;
import com.c5.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2.
 */
@Repository
public class BaseDaoUtilImpl implements BaseDaoUtil {

    NamedParameterJdbcTemplate jdbcTemplate;



    @Qualifier("datasourceSpring")
    @Autowired
    public void setJdbcTemplate(DataSource datasourceSpring) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(datasourceSpring);
    }


    public List<Map<String, Object>> query4list(String sql) {
        List<Map<String, Object>> list=jdbcTemplate.queryForList(sql,new BeanPropertySqlParameterSource(null));
        return list;
    }

    public Integer insert4Base(String sql) {
        Integer num=jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(null));
        return num;
    }

    public Integer upload4Base(String sql) {
        Integer num=jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(null));
        return num;
    }
}
