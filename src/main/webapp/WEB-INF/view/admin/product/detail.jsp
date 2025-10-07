<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                    <meta name="author" content="Hỏi Dân IT" />
                    <title>Dashboard - Hỏi Dân IT</title>
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage product</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item active">List product</li>
                                    </ol>
                                    <div class="container mt-5">
                                        <div class="d-flex justify-content-between">
                                            <h3>Product detail with id = ${product.id}</h3>
                                            <a class="btn btn-success" href="product/create">Create product</a>
                                        </div>

                                        <hr />

                                        <div class="card" style="width: 60%;">
                                            <div class="card-header">
                                                Information
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${detailProduct.id}</li>
                                                <li class="list-group-item">Name: ${detailProduct.name}</li>
                                                <li class="list-group-item">
                                                    Price:
                                                    <fmt:formatNumber type="number" value="${detailProduct.price}" />
                                                </li>

                                                <li class="list-group-item">Sold: ${detailProduct.sold}</li>
                                                <li class="list-group-item">Short description:
                                                    ${detailProduct.shortDesc}
                                                </li>
                                                <li class="list-group-item">Detail description:
                                                    ${detailProduct.detailDesc}
                                                </li>
                                                <li class="list-group-item">Factory: ${detailProduct.factory}</li>
                                                <li class="list-group-item">Target: ${detailProduct.target}</li>
                                                <!-- <img src="${avatar}" width="160" height="160" class="img-thumbnail"></img> -->
                                            </ul>
                                        </div>
                                        <hr />
                                        <a href="/admin/product" class="btn btn-success">Return</a>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/datatables-simple-demo.js"></script>
                </body>

                </html>