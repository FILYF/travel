package com.itcast.ssm.service.Impl;

import com.itcast.ssm.service.IProductService;
import com.itcast.ssm.dao.IProductDao;
import com.itcst.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}
