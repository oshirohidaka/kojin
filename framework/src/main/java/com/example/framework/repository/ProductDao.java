package com.example.framework.repository;

import com.example.framework.entity.CategoriesRecord;
import com.example.framework.entity.ProductRecord;
import com.example.framework.entity.UsersRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao {
    List<ProductRecord> findAll();

    List<ProductRecord> findByKeyword(String name);

    int insert(String product_id,String name,int price,int category_id);

    List<CategoriesRecord> categories();

    ProductRecord findByPid(String product_id);

}
