package com.main;

import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 学习一级缓存
 * 一级缓存在一个SqlSession内有效
 * 当执行增删改或者手动清空缓存，一级缓存会失效
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Company list1 = sqlSession.selectOne("com.dao.CompanyMapper.selectByPrimaryKey", 1);
        // 执行增，删，改，会清空缓存
//        Company list2 = sqlSession.selectOne("com.dao.CompanyMapper.selectByPrimaryKey", 2);
//        sqlSession.update("com.dao.CompanyMapper.updateByPrimaryKey", list2);

        // 手动清空缓存
        sqlSession.clearCache();

        Company list3 = sqlSession.selectOne("com.dao.CompanyMapper.selectByPrimaryKey", 1);
        sqlSession.close();

    }
}
