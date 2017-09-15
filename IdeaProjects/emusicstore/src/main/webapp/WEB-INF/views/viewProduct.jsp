<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/templates/header.jsp" %>


<div class="container-wrapper">

    <div class="container">

        <div class="page-header">
            <h1>Product Details</h1>

            <p class="lead">here is the product detail information !</p>

            <div class="container">

                <div class="row">
                    <div class="col-md-5">

                        <img src="<c:url value="/resources/images/${product.productId}.png"/> " alt="Image" style="width: 100%"/>
                    </div>
                    <div class="col-md-5">
                        <h3>${product.productName}</h3>
                        <h3>${product.productDescription}</h3>
                        <p>
                            <strong>Manefacturer</strong> ${product.productManifacturer}
                        </p>
                        <p>
                            <strong>Category</strong> ${product.productCategory}
                        </p>
                        <p>
                            <strong>Condition</strong> ${product.productCondition}
                        </p>
                        <h4>${product.productPrice} USD</h4>

                    </div>


                </div>
            </div>
        </div>

        <%@include file="/WEB-INF/views/templates/footer.jsp" %>
