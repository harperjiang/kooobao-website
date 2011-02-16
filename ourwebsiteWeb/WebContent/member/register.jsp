<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员专区</title>
<link href="/style/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/header.jsp" />
<div class="titlebar">
<div class="title">注册会员</div>
<div class="titledesc">申请为会员后，登录即可享受会员专属资源。</div>
</div>
<form id="register" name="register" method="post"
	action="/member/register.htm"
	enctype="application/x-www-form-urlencoded">
<div id="register" class="part formLayout"
	style="width: 80%; display: block; margin-left: 20px;" align="center">
<fieldset><legend>填写会员信息</legend> <label>会员类型：</label><select>
	<option value="p">个人用户</option>
	<option value="i">机构用户</option>
</select> <br />
<label for="register_name"> 会员名称：</label><input id="register_name"
	name="register:register_name" type="text" value="" />
(机构会员请提供机构注册名称，个人会员请填写姓名) <br />
<label>Email：</label><input id="register:j_id579204842_1_526e2b09"
	name="register:j_id579204842_1_526e2b09" type="text" value="" />
(提交注册信息后您将收到一封注册信件，请按照信件指示完成注册) <br />
<label>联系人电话：</label><input id="register:j_id579204842_1_526e2b18"
	name="register:j_id579204842_1_526e2b18" type="text" value="" /> <br />
<input id="register:j_id579204842_1_526e2b63"
	name="register:j_id579204842_1_526e2b63" type="submit" value="注册" /></fieldset>
</div>
</form>
<jsp:include page="/footer.jsp" /></body>
</html>