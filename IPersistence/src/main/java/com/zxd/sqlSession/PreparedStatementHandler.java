package com.zxd.sqlSession;


import com.zxd.config.BoundSql;
import com.zxd.pojo.Configuration;
import com.zxd.pojo.MappedStatement;
import com.zxd.utils.GenericTokenParser;
import com.zxd.utils.ParameterMapping;
import com.zxd.utils.ParameterMappingTokenHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: IPersistence
 * @description:
 * @author: Created by zxd
 * @create: 2020-04-30 21:18
 **/
public class PreparedStatementHandler {


    private Configuration configuration;

    private MappedStatement mappedStatement;

    private Object[] params;

    public PreparedStatementHandler(Configuration configuration, MappedStatement mappedStatement, Object[] params) {
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
        this.params = params;
    }

    public  PreparedStatement getPreparedStatement() throws Exception {
        //1.注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();
//        System.out.println("Connection autocommit is: " + connection.getAutoCommit());
        //2.获取sql语句  ->  转换sql语句
        String sql = mappedStatement.getSql();
        BoundSql bondSql = getBoundSql(sql);

        //3.获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(bondSql.getSqlText());

        //4.设置参数
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramterClass = paramterType != null ? Class.forName(paramterType) : null;
        List<ParameterMapping> parameterMappingList = bondSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = paramterClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }
        return preparedStatement;
    }


    /**
     * 完成对#{}的解析工作
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql){
        ParameterMappingTokenHandler paramterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}",paramterMappingTokenHandler);
        String parseSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = paramterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
