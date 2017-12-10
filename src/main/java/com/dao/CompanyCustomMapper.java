package com.dao;

import com.model.Company;

public interface CompanyCustomMapper extends CompanyMapper {

    Company selectSecondCompany();
}
