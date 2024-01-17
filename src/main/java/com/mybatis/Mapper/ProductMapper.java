package com.mybatis.Mapper;

import com.mybatis.Entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM Product ")
    List<Product> findAll();

    @Select("SELECT * FROM Product WHERE id = #{id}")
    Product findById(@Param("id") Integer id);

    @Delete("DELETE FROM Product WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Insert("INSERT INTO Product (id, name, status) VALUES (#{id}, #{name}, #{status})")
    int save(Product item);

    @Update("UPDATE Product SET name = #{name}, status = #{status} WHERE id = #{id}")
    int update(Product item);
}
