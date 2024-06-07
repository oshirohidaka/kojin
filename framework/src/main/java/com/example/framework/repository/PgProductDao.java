package com.example.framework.repository;

import com.example.framework.entity.CategoriesRecord;
import com.example.framework.entity.ProductRecord;
import com.example.framework.entity.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class PgProductDao implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ProductRecord> findAll() {
        return jdbcTemplate.query("SELECT products.id,product_id,products.name,price,categories.name AS categories_name  FROM products INNER JOIN categories ON products.category_id = categories.id ORDER BY product_id;",

        new DataClassRowMapper<>(ProductRecord.class));
    }

    @Override
    public List<ProductRecord> findByKeyword(String name) {
        var param = new MapSqlParameterSource();
        param.addValue("name", name);
        return jdbcTemplate.query("SELECT products.id,product_id,products.name,price,categories.name AS categories_name FROM products INNER JOIN categories ON products.category_id = categories.id WHERE products.name LIKE '%"+name+"%' ORDER BY product_id;", param, new DataClassRowMapper<>(ProductRecord.class));
    }

    @Override
    public int insert(int id,String product_id,String name,int price,int category_id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        param.addValue("product_id",product_id);
        param.addValue("name",name);
        param.addValue("price",price);
        param.addValue("category_id",category_id);

        return jdbcTemplate.update("INSERT INTO products(id,product_id,name,price,category_id) VALUES(:id,:product_id,:name,:price,:category_id)", param);
    }

    @Override
    public List<CategoriesRecord> categories() {
        return jdbcTemplate.query("SELECT * FROM categories",
                new DataClassRowMapper<>(CategoriesRecord.class));

    }

    @Override
    public ProductRecord findByPid(String product_id) {
        var param = new MapSqlParameterSource();
        param.addValue("product_id", product_id);
        var list = jdbcTemplate.query("SELECT *, '' AS categories_name FROM products WHERE product_id = :product_id ", param, new DataClassRowMapper<>(ProductRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public ProductRecord findByid(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT products.id,product_id,products.name,price,categories.name AS categories_name FROM products INNER JOIN categories ON products.category_id = categories.id WHERE products.id = :id;", param, new DataClassRowMapper<>(ProductRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

//    @Override
    public int delete(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.update("DELETE FROM products WHERE products.id = :id", param);
    }
}
