package com.bvktest.inventory.product.repository;

import com.bvktest.inventory.product.object.AddProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDefaultRepository implements ProductRepository{
    private final String SQL_GET_QUANTITY = "select quantity from product where id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDefaultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProduct(AddProductDto addProductDto) {
        return 0;
    }

    @Override
    public int updateQuantity(String productId, int quantityDelta) {
        return 0;
    }

    @Override
    public Integer getQuantity(String productId) {
        List<Integer> list = jdbcTemplate.query(SQL_GET_QUANTITY,
                (rs, rowNum) -> rs.getObject("quantity") != null ? rs.getInt("quantity") : null,
                productId
        );

        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }
}
