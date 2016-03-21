package com.home.dao;

import com.home.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProductsByGroupId(int groupId);
}
