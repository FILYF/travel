package com.itcast.ssm.service;

import com.itcst.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws Exception;

    public void save(Product product);
}
