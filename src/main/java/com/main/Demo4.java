package com.main;

import com.dao.CompanyMapper;
import com.github.pagehelper.PageHelper;
import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo4 {

    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        a();
        b();
    }

    public static void a() {
        Thread t = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                SqlSession sqlSession = sqlSessionFactory.openSession();
                CompanyMapper mapper = sqlSession.getMapper(com.dao.CompanyMapper.class);
                PageHelper.startPage(1, 2, false);
                List<Company> list = mapper.selectByExample(null);
                sqlSession.close();
                System.out.println("a:" + (list.size() == 2));
            }
        });
        t.start();
    }

    public static void b() {
        Thread t = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                SqlSession sqlSession = sqlSessionFactory.openSession();
                CompanyMapper mapper = sqlSession.getMapper(com.dao.CompanyMapper.class);
                List<Company> list = mapper.selectByExample(null);
                sqlSession.close();
                System.out.println("b:" + (list.size() == 4));
            }
        });
        t.start();
    }
}
