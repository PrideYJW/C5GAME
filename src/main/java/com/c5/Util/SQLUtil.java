package com.c5.Util;

import com.c5.Annotation.TableAnnotation;
import com.c5.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */
@Component
public class SQLUtil {
    private  StringBuffer sql=new StringBuffer();
    private  StringBuffer selectSql=new StringBuffer();
    private  StringBuffer tableSql=new StringBuffer();
    private  StringBuffer whereSql=new StringBuffer();

    private List<String> ListSQL=new ArrayList<String>();
        public  String query4ListSQLUtil(BaseEntity baseEntity)  {
            QueryEntityUtil4SQL(baseEntity);
            sql.append("select "+selectSql.toString().substring(0,selectSql.toString().length()-1)+" from "+tableSql.toString().substring(0,tableSql.toString().length()-1)+" where 1=1 "+whereSql.toString().substring(0,whereSql.toString().length()-3));
            return sql.toString();
        }
        public  List<String> insert4SQLUtil(List<BaseEntity> list){
            insertEntityUtil4SQL(list);
            return ListSQL;
        }
        public  List<String> upload4SQLUtil(List<BaseEntity> list){
            UploadEntityUtil4SQL(list);
            return ListSQL;
        }





        public  void QueryEntityUtil4SQL(BaseEntity baseEntity){
            Class<? extends BaseEntity> clazz=baseEntity.getClass();
            String tableName=clazz.getAnnotation(TableAnnotation.class).name();
            tableSql.append(tableName+" as "+tableName+" ,");
            Field[] field=clazz.getDeclaredFields();
            for (Field field1 : field) {
                field1.setAccessible(true);
                Object object= null;
                try {
                    object = field1.get(baseEntity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(object!=null){
                    if(object.getClass().isAnnotationPresent(TableAnnotation.class)){
                        whereSql.append(" "+tableName+"."+object.getClass().getAnnotation(TableAnnotation.class).name()+"Id ="+object.getClass().getAnnotation(TableAnnotation.class).name()+"."+object.getClass().getAnnotation(TableAnnotation.class).name()+"Id and");
                        BaseEntity baseEntity1= null;
                        try {
                            baseEntity1 = (BaseEntity) field1.get(baseEntity);
                            QueryEntityUtil4SQL(baseEntity1);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }else{
                        if(object!=null){
                            whereSql.append(" "+tableName+"."+field1.getName()+"="+object+" and");
                        }
                        selectSql.append(" "+tableName+"."+field1.getName()+" ,");
                    }
                }else{
                    selectSql.append(" "+tableName+"."+field1.getName()+" ,");
                }
            }
        }

        public  void insertEntityUtil4SQL(List<BaseEntity> list){
            for (BaseEntity baseEntity : list) {
                StringBuffer parameterSql=new StringBuffer();
                StringBuffer valuesSql=new StringBuffer();
                Class<? extends BaseEntity> clazz=baseEntity.getClass();
                String tableName=clazz.getAnnotation(TableAnnotation.class).name();
                Field[] field=clazz.getDeclaredFields();
                String sql="insert into "+tableName+" ";
                for (Field field1 : field) {
                    field1.setAccessible(true);
                    try {
                        Object object=field1.get(baseEntity);
                        if(object!=null){
                            parameterSql.append(" "+field1.getName()+" ,");
                            if(object.getClass().getSimpleName().equals("String")){
                                valuesSql.append(" '"+object+"' ,");
                            }else if(object.getClass().getSimpleName().equals("Integer")){
                                valuesSql.append(" "+object+" ,");
                            }else if(object.getClass().getSimpleName().equals("Double")){
                                valuesSql.append(" "+object+" ,");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                sql+="("+parameterSql.toString().substring(0,parameterSql.toString().length()-1)+")"+" values("+valuesSql.toString().substring(0,valuesSql.toString().length()-1)+")";
                ListSQL.add(sql);
            }
        }
        public  void UploadEntityUtil4SQL(List<BaseEntity> list){
            for (BaseEntity baseEntity : list) {
                StringBuffer setSql=new StringBuffer();
                StringBuffer whereSql=new StringBuffer();
                Class<? extends BaseEntity> clazz=baseEntity.getClass();
                String tableName=clazz.getAnnotation(TableAnnotation.class).name();
                Field[] field=clazz.getDeclaredFields();
                String sql="update "+tableName+" set ";
                for (Field field1 : field) {
                    field1.setAccessible(true);
                    try {
                        Object object=field1.get(baseEntity);
                        if(object!=null){
                            if(field1.getName().equals(tableName+"Id")){
                                whereSql.append("where 1=1 and "+tableName+"Id ='"+object+"'");
                            }else {
                                if (object.getClass().getSimpleName().equals("String")) {
                                    setSql.append(" " + tableName + "." + field1.getName() + "= '" + object + "' ,");
                                } else if (object.getClass().getSimpleName().equals("Integer")) {
                                    setSql.append(" " + tableName + "." + field1.getName() + "= " + object + " ,");
                                } else if (object.getClass().getSimpleName().equals("Double")) {
                                    setSql.append(" " + tableName + "." + field1.getName() + "= " + object + " ,");
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                sql+=setSql.toString().substring(0,setSql.toString().length()-1)+" "+whereSql;
                ListSQL.add(sql);
            }
        }
}
