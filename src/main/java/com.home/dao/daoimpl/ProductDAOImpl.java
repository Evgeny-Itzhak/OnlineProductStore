package com.home.dao.daoimpl;


import com.home.dao.ProductDAO;
import com.home.model.Product;
import com.home.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl() {
        connection = DbUtil.getConnection();
    }

    public List<Product> getProductsByGroupId(int groupId) {

        List<Product> products = new ArrayList<Product>();

        String query = "SELECT PRODUCT_NAME, PRODUCT_PRICE FROM t_product\n" +
                "RIGHT JOIN t_group ON t_group.GROUP_ID=t_product.GROUP_ID\n" +
                "WHERE t_product.GROUP_ID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductName(resultSet.getString("PRODUCT_NAME"));
                product.setProductPrice(Integer.parseInt(resultSet.getString("PRODUCT_PRICE")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
