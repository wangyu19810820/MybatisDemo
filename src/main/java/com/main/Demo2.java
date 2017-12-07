package com.main;

import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 学习二级缓存
 * 因为Mapper中有cache标签，开启了二级缓存。第二次查询没有经过数据库就返回结果了。
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Company list1 = sqlSession.selectOne("com.dao.CompanyMapper.selectByPrimaryKey", 1);

        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();

        Company list3 = sqlSession.selectOne("com.dao.CompanyMapper.selectByPrimaryKey", 1);
        sqlSession.close();

    }
}
