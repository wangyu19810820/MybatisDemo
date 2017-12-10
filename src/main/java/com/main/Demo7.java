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
 * Java公版通用字段，利用插件赋值的验证Demo
 */
public class Demo7 {

    public static void main(String[] args) throws IOException {
        String configPath = "mybatis-configuration-plug.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CompanyMapper mapper = sqlSession.getMapper(com.dao.CompanyMapper.class);
        Company company = new Company();
        company.setId(7);
        company.setName("11");
//        mapper.insert(company);
        mapper.updateByPrimaryKey(company);

        sqlSession.commit();
        sqlSession.close();

        System.out.println(company);

    }
}
