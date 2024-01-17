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
@RequestMapping("/products/xml")
public class ProductXMLController {


    @Autowired
    private ProductXMLMapper productXMLMapper;


    @GetMapping("/")
    public List<ProductXML> findAllXML(){
    ProductXMLExample example = new ProductXMLExample();
    return productXMLMapper.selectByExample(example);
    }

    @GetMapping("/{id}")
    public ProductXML findXMLById(@PathVariable("id") int id) {
        try {
            ProductXML product = productXMLMapper.selectByPrimaryKey(id);
            if (product != null) {
                // Log thông tin sản phẩm nếu tìm thấy
                System.out.println("Found product: " + product.toString());
            } else {
                System.out.println("Product not found for ID: " + id);
            }
            return product;
        } catch (Exception e) {
            // Log ngoại lệ nếu có
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ProductXML product) {
        try {
            // Thực hiện thêm sản phẩm vào cơ sở dữ liệu sử dụng productXMLMapper
            int create = productXMLMapper.insert(product);

            if (create > 0) {
                // Trả về thông báo thành công nếu thêm sản phẩm thành công
                return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
            } else {
                // Trả về thông báo lỗi nếu có vấn đề khi thêm sản phẩm
                return new ResponseEntity<>("Failed to add product", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Log ngoại lệ nếu có
            e.printStackTrace();
            // Trả về thông báo lỗi nếu có vấn đề khi thêm sản phẩm
            return new ResponseEntity<>("Failed to add product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductXML updatedProduct) {
        try {
            // Kiểm tra xem sản phẩm có tồn tại không
            ProductXML existingProduct = productXMLMapper.selectByPrimaryKey(id);

            if (existingProduct != null) {
                // Cập nhật thông tin sản phẩm
                updatedProduct.setId(id);
                int rowsAffected = productXMLMapper.updateByPrimaryKey(updatedProduct);

                if (rowsAffected > 0) {
                    // Trả về thông báo thành công nếu cập nhật sản phẩm thành công
                    return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
                } else {
                    // Trả về thông báo lỗi nếu có vấn đề khi cập nhật sản phẩm
                    return new ResponseEntity<>("Failed to update product", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                // Trả về thông báo lỗi nếu sản phẩm không tồn tại
                return new ResponseEntity<>("Product not found for ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log ngoại lệ nếu có
            e.printStackTrace();
            // Trả về thông báo lỗi nếu có vấn đề khi cập nhật sản phẩm
            return new ResponseEntity<>("Failed to update product", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            // Kiểm tra xem sản phẩm có tồn tại không
            ProductXML existingProduct = productXMLMapper.selectByPrimaryKey(id);

            if (existingProduct != null) {
                // Thực hiện xóa sản phẩm
                int rowsAffected = productXMLMapper.deleteByPrimaryKey(id);

                if (rowsAffected > 0) {
                    // Trả về thông báo thành công nếu xóa sản phẩm thành công
                    return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
                } else {
                    // Trả về thông báo lỗi nếu có vấn đề khi xóa sản phẩm
                    return new ResponseEntity<>("Failed to delete product", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                // Trả về thông báo lỗi nếu sản phẩm không tồn tại
                return new ResponseEntity<>("Product not found for ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log ngoại lệ nếu có
            e.printStackTrace();
            // Trả về thông báo lỗi nếu có vấn đề khi xóa sản phẩm
            return new ResponseEntity<>("Failed to delete product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
