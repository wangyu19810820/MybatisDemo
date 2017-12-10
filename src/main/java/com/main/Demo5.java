package com.main;

import com.dao.CompanyCustomMapper;
import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一个Dao对应两个Mapper的技术验证Demo
 */
public class Demo5 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CompanyCustomMapper mapper1 = sqlSession.getMapper(com.dao.CompanyCustomMapper.class);
        Company company = mapper1.selectByPrimaryKey(1);
        Company company1 = mapper1.selectSecondCompany();
        sqlSession.close();

        System.out.println(company);
        System.out.println(company1);

    }
}
