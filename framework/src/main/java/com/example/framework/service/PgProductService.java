package com.example.framework.service;

import com.example.framework.entity.CategoriesRecord;
import com.example.framework.entity.ProductRecord;
import com.example.framework.entity.UsersRecord;
import com.example.framework.repository.PgProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PgProductService implements ProductService {
    public PgProductDao pgProductDao;

    public PgProductService(PgProductDao pgProductDao) {
        this.pgProductDao = pgProductDao;
    }
    @Override
    public List<ProductRecord> findAll() {
        return pgProductDao.findAll();
    }
    @Override
    public List<ProductRecord> findByKeyword(String name){
        return pgProductDao.findByKeyword(name);
    }
    @Override
    public int insert(int id,String product_id,String name,int price,int category_id) {
        return pgProductDao.insert(id,product_id,name,price,category_id);
    }
    @Override
    public List<CategoriesRecord> categories(){
        return pgProductDao.categories();
    }
    @Override
    public ProductRecord findByPid(String product_id){
        return pgProductDao.findByPid(product_id);
    }
    @Override
    public ProductRecord findByid(int id){
        return pgProductDao.findByid(id);
    }
    public int delete(int product_id) {
        return pgProductDao.delete(product_id);
    }



}
