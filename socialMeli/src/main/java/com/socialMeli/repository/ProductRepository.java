package com.socialMeli.repository;

import com.socialMeli.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{
    List<Product> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }


    @Override
    public void add(Product product) {
        this.productList.add(product);
    }
}
