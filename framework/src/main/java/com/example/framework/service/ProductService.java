package com.example.framework.service;

import com.example.framework.entity.CategoriesRecord;
import com.example.framework.entity.ProductRecord;
import com.example.framework.entity.UsersRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductRecord> findAll();

    List<ProductRecord> findByKeyword(String name);

    int insert(String product_id, String name, int price, int category_id);

    List<CategoriesRecord> categories();

    ProductRecord findByPid(String product_id);
}
