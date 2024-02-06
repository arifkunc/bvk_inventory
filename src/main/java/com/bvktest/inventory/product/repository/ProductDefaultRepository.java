package com.bvktest.inventory.product.repository;

import com.bvktest.inventory.product.object.AddProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDefaultRepository implements ProductRepository{

    private final String SQL_GET_SEQ_PRODUCT_ID_NEXTVAL = "select lpad(nextval('seq_product_id'), 6, '0') from dual";

    private final String SQL_INSERT_PRODUCT = "insert into product values (?, ?, ?, ?)";

    private final String SQL_UPDATE_QUANTITY = "update product set quantity=quantity+? where id=?";

    private final String SQL_GET_QUANTITY = "select quantity from product where id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDefaultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insertProduct(AddProductDto addProductDto) {
        String productId = jdbcTemplate.queryForObject(SQL_GET_SEQ_PRODUCT_ID_NEXTVAL, String.class);

        jdbcTemplate.update(SQL_INSERT_PRODUCT, productId, addProductDto.getName(), addProductDto.getPrice(), addProductDto.getQuantity());

        return productId;
    }

    @Override
    public int updateQuantity(String productId, int quantityDelta) {
        int rowsUpdate = jdbcTemplate.update(SQL_UPDATE_QUANTITY, quantityDelta, productId);

        return rowsUpdate;
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
