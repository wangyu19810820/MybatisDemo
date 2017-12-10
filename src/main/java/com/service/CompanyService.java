package com.service;

import com.dao.CompanyCustomMapper;
import com.dao.CompanyMapper;
import com.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyCustomMapper companyCustomMapper;

//    @Autowired
//    private CompanyMapper companyMapper;

    public Company getCompany(Integer i) {
        return companyCustomMapper.selectByPrimaryKey(i);
    }

    public Company getSecondCompany() {
//        return null;
        return companyCustomMapper.selectSecondCompany();
    }
}
