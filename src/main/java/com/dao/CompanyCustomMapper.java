package com.dao;

import com.model.Company;

import java.util.List;

public interface CompanyCustomMapper extends BaseMapper {

    Company selectSecondCompany();
}
