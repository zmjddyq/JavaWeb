<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@include file="/pages/public/base_path.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%@include file="/pages/public/menu_manager.jsp" %>
</div>

<div id="main">
    <form action="manager/bookServlet">
        <input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <c:if test="${empty param.id}">
                    <td><input name="name" type="text" value="${requestScope.bookMess.name}" placeholder="请输入书名"/></td>
                </c:if>
                <c:if test="${not empty param.id}">
                    <td><input readonly name="name" type="text" value="${requestScope.bookMess.name}" placeholder="请输入书名"/></td>
                </c:if>
                <td><input name="price" type="text" value="${requestScope.bookMess.price}" placeholder="请输入价格"/></td>
                <td><input name="author" type="text" value="${requestScope.bookMess.author}" placeholder="请输入作者"/></td>
                <td><input name="sales" type="text" value="${requestScope.bookMess.sales}" placeholder="请输入销量"/></td>
                <td><input name="stock" type="text" value="${requestScope.bookMess.stock}" placeholder="请输入库存"/></td>
                <td><input type="submit" value="提交"/></td>

            </tr>
        </table>
    </form>


</div>

<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
</div>
</body>
</html>