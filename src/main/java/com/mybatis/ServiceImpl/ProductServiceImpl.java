package com.mybatis.ServiceImpl;

import com.mybatis.Entity.Product;
import com.mybatis.Mapper.ProductMapper;
import com.mybatis.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;

    public ProductServiceImpl(ProductMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public List<Product> findAll() {
        return mapper.findAll();
    }
    @Override
    public Product findById(Integer id) {
        return mapper.findById(id);
    }
    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }
    @Override
    public int save(Product item) {
        return mapper.save(item);
    }
    @Override
    public int update(Integer id, Product item) {
        item.setId(id);
        return mapper.update(item);
    }
}
