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
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage order</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item active">Order / Update</li>
                                    </ol>
                                    <div class="container mt-5">


                                        <hr>
                                        <div class="container mt-5">
                                            <div class="row">
                                                <div class="col-md-6 col-12 mx-auto">
                                                    <h3>Update order</h3>
                                                    <hr />
                                                    <form:form method="post" action="/admin/order/update"
                                                        modelAttribute="order" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>
                                                                <p class="mb-0 mt-4">
                                                                    Order id : ${order.id}
                                                                </p>
                                                            </td>
                                                            <td>
                                                                Price:
                                                                <fmt:formatNumber type="number"
                                                                    value="${order.totalPrice}" /> đ
                                                            </td>
                                                        </tr>
                                                        <div class="row">
                                                            <div class="mb-3" style="display: none;">
                                                                <form:input type="number" class="form-control"
                                                                    path="id" hidden="true" />
                                                            </div>
                                                            <div class="mb-3 col-md-6 col-12">
                                                                <label class="form-label">User:</label>
                                                                <form:input type="number" class="form-control"
                                                                    path="user.id" disabled="true" />
                                                            </div>
                                                            <div class="mb-3 col-md-6 col-12">
                                                                <label class="form-label">Select role</label>
                                                                <form:select class="form-select" path="status">
                                                                    <form:option value="PENDING">PENDING</form:option>
                                                                    <form:option value="SHIPPING">SHIPPING</form:option>
                                                                    <form:option value="COMPLETING">COMPLETING
                                                                    </form:option>
                                                                    <form:option value="CANCEL">CANCEL</form:option>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                        <button class="btn btn-warning">Update</button>
                                                    </form:form>
                                                </div>
                                            </div>

                                        </div>
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