package com.dao;

import com.model.Company;

public interface BaseMapper {

    Company selectByPrimaryKey(Integer id);
}
