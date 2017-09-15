<%--
  Created by IntelliJ IDEA.
  User: santosh
  Date: 9/13/17
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/templates/header.jsp" %>


<div class="container-wrapper">

    <div class="container">

        <div class="page-header">
            <h1>Administrator Page</h1>

            <p class="lead">This is the administrator page</p>


        </div>

        <h3>
            <a href="<c:url value="/admin/productInventory"/>">Product Inventory</a>
        </h3>

        <p>Here you can check modify and add inventory</p>

        <%@include file="/WEB-INF/views/templates/footer.jsp" %>





