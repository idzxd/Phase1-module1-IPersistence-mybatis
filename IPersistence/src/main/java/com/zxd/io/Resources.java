package com.zxd.io;

import java.io.InputStream;
/**
 * @program: IPersistence
 * @description:
 * @author: Created by zxd
 * @create: 2020-04-30 21:18
 **/
public class Resources {

    // 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
    public static InputStream getResourceAsSteam(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return  resourceAsStream;

    }



}
