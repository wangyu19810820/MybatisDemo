package com.main;

import com.dao.CompanyMapper;
import com.model.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 通过Mapper来调用Mybatis，用于debug，Mybatis的运作流程
 */
public class Demo3 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CompanyMapper mapper = sqlSession.getMapper(com.dao.CompanyMapper.class);
        Company company = mapper.selectByPrimaryKey(1);
        sqlSession.close();

        System.out.println(mapper);
        System.out.println(company);

    }

}
