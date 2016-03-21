package com.home.model;


import java.util.List;

public class Group {

    private int groupId;
    private String groupName;
    private List<Product> products;
    private int quantityOfProducts;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public void setQuantityOfProducts(int quantityOfProducts) {
        this.quantityOfProducts = quantityOfProducts;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", products=" + products +
                ", quantityOfProducts=" + quantityOfProducts +
                '}';
    }
}
