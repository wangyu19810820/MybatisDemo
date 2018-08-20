package com.dao;

import com.model.Company;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CompanyCustomMapper extends CompanyMapper {

    Company selectSecondCompany();
    List<Company> selectCompanyByRowBound(RowBounds rowBounds);

}
