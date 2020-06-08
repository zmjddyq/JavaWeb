<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <style type="text/css">
        .cart_null_info {
            height: 400px;
            display: flex;
        }

        .cart_null_span {
            margin: auto;
            font-size: large;
        }
    </style>
    <%@include file="/pages/public/base_path.jsp" %>
    <script type="text/javascript">
        $(function () {
            //删除确认
            $(".delete_item").click(function () {
                // 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
                /**
                 * confirm是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个确认，一个是取消。
                 * 返回true表示点击了，确认，返回false表示点击取消。
                 */

                return confirm("您确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
                // return false// 阻止元素的默认行为===不提交请求
            })
            //清空确认
            $("#clear_cart").click(function () {
                return confirm("您确定要清空购物车吗？");
            })
            // 给输入框绑定 onchange内容发生改变事件
            $(".updateCount").change(function () {
                // 获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                // 获取商品数量
                var count = this.value;
                if (confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?")) {
                    //发起请求。给服务器保存修改
                    location.href = "cartServlet?action=updateCount&count=" + count + "&id=" + id;
                } else {
                    // defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
                    this.value = this.defaultValue;
                }
            });
        })
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <c:if test="${empty sessionScope.user}">
        <div>
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a>
            <a href="index.jsp">返回</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <%@ include file="/pages/public/menu_user.jsp" %>
    </c:if>
</div>


<div id="main">
    <c:if test="${not empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td>${item.value.name}</td>
                    <td>
                        <input class="updateCount" style="width: 80px;"
                               bookId="${item.value.id}"
                               type="text" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a class="delete_item" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>

        </table>

        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clear_cart" href="cartServlet?action=clearItem">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=creatOrder">去结账</a></span>
        </div>
    </c:if>
    <c:if test="${empty sessionScope.cart.items}">
        <div class="cart_null_info">
            <span class="cart_null_span" left><a href="index.jsp">亲！您的购物车中还没有商品哦，请前往选购商品。</a></span>
        </div>
    </c:if>
</div>
<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>