package com.main;

import com.dao.CompanyCustomMapper;
import com.dao.CompanyMapper;
import com.model.Company;
import com.service.CompanyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring+Mybatis的技术验证Demo
 */
public class Demo6 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        CompanyService companyService = ctx.getBean(CompanyService.class);
        Company company1 = companyService.getCompany(1);
        System.out.println(company1);
        Company company2 = companyService.getSecondCompany();
        System.out.println(company2);
    }
}
