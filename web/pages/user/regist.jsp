<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@include file="/pages/public/base_path.jsp" %>
    <script type="text/javascript" src="script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        //页面加载完成后
        $(function () {
            //给注册按钮添加单击事件
            $("#sub_btn").click(function () {
                // // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                // 	var usernamecheck = /^[a-z0-9_-]{3,16}$/;
                // 	if (!usernamecheck.test($("#username").val())){
                // 		$(".errorMsg").text("用户名不合法!");
                // 		return false;
                // 	}
                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                if (!usernamecheck.test($("#password").val())) {
                    $(".errorMsg").text("密码不合法!");
                    return false;
                }
                // 验证确认密码：和密码相同
                if ($("#repwd").val() != $("#password").val()) {
                    $(".errorMsg").text("确认密码和密码不一致!");
                    return false;
                }
                // 邮箱验证：xxxxx@xxx.com
                var emailcheck = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                if (!emailcheck.test($("#email").val())) {
                    $(".errorMsg").text("邮箱不合法！");
                    return false;
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $(".errorMsg").text("验证码不能为空！");
                    return false;
                }

                $(".errorMsg").text("");
            })
            // 验证码切换
            $("#code_token").click(function () {
                this.src = "kaptcha.bmp?d=" + new Date();
                // this.src = "kaptcha.bmp";
            })
            //用户名验证
            $("input#username").blur(function () {
                var val = this.value;
                if(val == ""){
                    $(".errorMsg").text("用户名不能为空！");
                    return;
                }
                $.getJSON("<%=basePath%>/userServlet", "action=existsUsername&username=" + val, function (data) {
                    if (data.isIn) {
                        $(".errorMsg").text("用户名已经存在！");
                    } else {
                        // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                        	var usernamecheck = /^[a-z0-9_-]{3,16}$/;
                        	if (!usernamecheck.test(val)){
                        		$(".errorMsg").text("用户名不合法!");
                        	}else {
                                $(".errorMsg").text("用户名可用!");
                            }
                    }
                })
            })

        })

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">${Fail}</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.Username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email" value="${requestScope.Email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" id="code" name="code"/>
                        <img id="code_token" alt="" src="kaptcha.bmp"
                             style="float: right; margin-right: 40px; height: 40px; width: 100px">
                        <br/>
                        <br/>
                        <input type="hidden" name="action" value="regist">
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
</div>
</body>
</html>