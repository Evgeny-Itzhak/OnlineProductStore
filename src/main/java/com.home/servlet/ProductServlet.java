package com.home.servlet;

import com.home.dao.GroupDAO;
import com.home.dao.ProductDAO;
import com.home.dao.daoimpl.GroupDAOImpl;
import com.home.dao.daoimpl.ProductDAOImpl;
import com.home.model.Group;
import com.home.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;
    private GroupDAO groupDAO;

    private static final String LIST_GROUP_PRODUCT = "/view/product/groupProductList.jsp";

    public ProductServlet() {
        groupDAO = new GroupDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        List<Group> groups = groupDAO.getGroups();

        String idParam = request.getParameter("id");
        String productSortByNameParam = request.getParameter("sortProductByName");
        String productSortByPriceParam = request.getParameter("sortProductByPrice");

        int id = (idParam != null && !idParam.isEmpty()) ? Integer.valueOf(idParam) : -1;

        if (productSortByPriceParam == null && productSortByNameParam != null) {
            productSortByNameParam = productSortByNameParam.isEmpty() ? "asc" : productSortByNameParam;
        }
        if (productSortByNameParam == null && productSortByPriceParam != null) {
            productSortByPriceParam = productSortByPriceParam.isEmpty() ? "asc" : productSortByPriceParam;
        }

        request.setAttribute("groups", groups);

        if (action.equalsIgnoreCase("groups")) {
            for (Group group : groups) {
                if (group.getGroupId() == id) {
                    List<Product> productList = productDAO.getProductsByGroupId(group.getGroupId());
                    request.setAttribute("currentProductId", id);
                    final String finalProductSortByNameParam = productSortByNameParam;
                    final String finalProductSortByPriceParam = productSortByPriceParam;

                    Collections.sort(productList, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getProductName().compareTo(o2.getProductName());
                        }
                    });

                    if (finalProductSortByNameParam != null) {
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                if ("asc".equalsIgnoreCase(finalProductSortByNameParam)) {
                                    request.setAttribute("sortProductByName", "desc");
                                    return -1 * o1.getProductName().compareTo(o2.getProductName());
                                } else {
                                    request.setAttribute("sortProductByName", "asc");
                                    return o1.getProductName().compareTo(o2.getProductName());
                                }
                            }
                        });
                    }

                    if (finalProductSortByPriceParam != null) {
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                if ("asc".equalsIgnoreCase(finalProductSortByPriceParam)) {
                                    request.setAttribute("sortProductByPrice", "desc");
                                    if (o1.getProductPrice() > o2.getProductPrice()) {
                                        return 1;
                                    } else if (o1.getProductPrice() < o2.getProductPrice()) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                } else {
                                    request.setAttribute("sortProductByPrice", "asc");
                                    if (o1.getProductPrice() > o2.getProductPrice()) {
                                        return -1;
                                    } else if (o1.getProductPrice() < o2.getProductPrice()) {
                                        return 1;
                                    } else {
                                        return 0;
                                    }
                                }
                            }
                        });
                    }
                    group.setProducts(productList);
                } else {
                    group.setProducts(null);
                }
            }
            forward = LIST_GROUP_PRODUCT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}