<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Show All Products</title>
  <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
  </style>
<%--  <script>
    function sortByName(){
      var products = [];
      var productList = group.product;

      for (i=0; i<group.products.length; i++){
        curProduct = group.products[i]
        products.push(curProduct);
      }
    }
  </script>--%>
</head>
<body>

<div align="center">


  <table style="width:50%">
    <thead bgcolor="yellow">
    <tr>
      <th rowspan="2" align="center"><h2>Group</h2></th>
      <th colspan="2" align="center"><h2>Product</h2></th>
    </tr>
    <tr>
        <td align="center"><a href="/ProductServlet?action=group&id=${currentProductId}&sortProductByName=${sortProductByName}"><b>Product Name</b></a></td>
        <td align="center"><a href="/ProductServlet?action=group&id=${currentProductId}&sortProductByPrice=${sortProductByPrice}"><b>Product Price</b></a></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${groups}" var="group">

      <tr>
        <td>
          <a href="/ProductServlet?action=groups&id=${group.groupId}"> <c:out value="${group.groupName} (${group.quantityOfProducts})"/> </a>
        </td>

        <td>
          <ul>
            <c:forEach items="${group.products}" end="10" var="curProduct">
              <li>${curProduct.productName}</li>
            </c:forEach>
          </ul>

        </td>
        <td>
          <ul>
            <c:forEach items="${group.products}" end="10" var="curProduct">
              <li>${curProduct.productPrice}</li>
            </c:forEach>
          </ul>

        </td>
      </tr>

    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>