package com.mybatis.Service;

import com.mybatis.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    int deleteById(Integer id);
    int save(Product item);
    int update(Integer id,Product item);

}
