package com.itcast.ssm.dao;

import com.itcst.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductDao {

    @Select("select * from product")
    public List<Product> findAll() throws Exception;
}