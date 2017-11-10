package com.c5.web;

import com.c5.entity.BaseEntity;
import com.c5.service.BaseServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
@Controller
public class UserController {

    @Autowired
    BaseServiceUtil baseServiceUtil;

    @RequestMapping("/userquery4list.xhtml")
    @ResponseBody
    public List<Map<String,Object>> query4List(HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        String entity=request.getParameter("entity");
        try {
            BaseEntity baseEntity= (BaseEntity) Class.forName("com.c5.entity."+entity).newInstance();
            System.out.println(baseEntity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/userquery4object")
    @ResponseBody
    public List<Map<String,Object>> query4object(HttpServletRequest request){
        String entity=request.getParameter("userid");

        return null;
    }



  @RequestMapping("index.xhtml")
    public  String aa(){
        System.out.println("mo");

        return "";
    }



}
