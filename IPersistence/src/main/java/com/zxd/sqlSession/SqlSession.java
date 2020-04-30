package com.zxd.sqlSession;

import java.util.List;
/**
 * @program: IPersistence
 * @description:
 * @author: Created by zxd
 * @create: 2020-04-30 21:18
 **/
public interface SqlSession {

    //查询所有
    <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //根据条件查询单个
    <T> T selectOne(String statementid,Object... params) throws Exception;

    int insertOne(String statementId, Object... params) throws Exception;

    int updateOne(String statementId, Object... params) throws Exception;

    int deleteOne(String statementId, Object... params) throws Exception;


    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);


}
