package com.itcast.ssm.dao;

import com.itcst.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductDao {

    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;

    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;
}
