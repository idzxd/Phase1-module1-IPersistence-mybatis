package com.zxd.sqlSession;

import com.zxd.pojo.Configuration;
import com.zxd.pojo.MappedStatement;

import java.util.List;
/**
 * @program: IPersistence
 * @description:
 * @author: Created by zxd
 * @create: 2020-04-30 21:18
 **/
public interface Executor {

     <E> List<E> query(Configuration configuration,MappedStatement mappedStatement,Object... params) throws Exception;

     int insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

     int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

     int delete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
