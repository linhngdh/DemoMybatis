package com.mybatis.Controller;

import com.mybatis.Entity.Product;
import com.mybatis.Entity.ProductXML;
import com.mybatis.Entity.ProductXMLExample;
import com.mybatis.Mapper.ProductXMLMapper;
import com.mybatis.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> products = productService.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") int id) {
        try {
            Product product = productService.findById(id);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> create(@RequestBody Product product) {
        try {
            // Thực hiện validation nếu cần
            int createdProductId = productService.save(product);
            return new ResponseEntity<>(createdProductId, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable("id") Integer id, @RequestBody Product product) {
        try {
            // Thực hiện validation nếu cần
            int update = productService.update(id, product);
            if (update > 0) {
                return new ResponseEntity<>(update, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id) {
        try {
            int delete = productService.deleteById(id);
            if (delete > 0) {
                return new ResponseEntity<>(delete, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
