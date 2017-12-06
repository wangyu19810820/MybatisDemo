package com.main;

import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo1 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Company> list = sqlSession.selectList("com.dao.CompanyMapper.selectByExample");
        sqlSession.close();

        list.forEach(System.out::println);
    }
}
