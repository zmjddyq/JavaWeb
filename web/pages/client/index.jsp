<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/public/base_path.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#search").click(function () {
                if ($("#min").val() == "" && $("#max").val() == ""){
                    return false;
                }
            });
            $(".add_item").click(function () {
                alert("添加成功");
                location.href = "cartServlet?action=addItem&id=" + $(this).attr("value");
            })
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${!empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userServlet?action=outLogin">注销</a>&nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input id="search" type="submit" value="查询"/>
                <input type="button" onclick="javascrtpt:window.location.href='client/bookServlet?action=page&pageNo=1' " value="全部"/>
            </form>
        </div>

        <div style="text-align: center">
            <c:if test="${not empty sessionScope.cart.totalCount}">
            <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
            <div>
                您刚刚将《<span style="color: red">${sessionScope.lastItem}</span>》加入到了购物车中
            </div>
            </c:if>
            <c:if test="${empty sessionScope.cart.totalCount}">
            <span> </span>
            <div>
                <span style="color: red">当前购物车为空</span>
            </div>
            </c:if>
        </div>

        <%--书目--%>
        <c:forEach items="${requestScope.page.items}" var="item">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/img/default.jpg"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${item.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${item.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${item.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${item.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${item.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="add_item" value="${item.id}">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>


    <%--分页--%>
    <%@include file="/pages/public/paging.jsp" %>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>