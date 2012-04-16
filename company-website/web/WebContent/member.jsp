<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:view>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>会员专区</title>
	<link href="/style/main.css" rel="stylesheet" type="text/css" />
	<link href="/style/new_ui.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<jsp:include page="header.jsp" />
	<div class="titlebar">
	<div class="title">会员专区</div>
	<div class="titledesc">申请为会员后，登录即可享受会员专属资源。</div>
	</div>
	<div class="component"
		style="width: 200px; height: 250px; margin-left: 30px;" align="left">
	<h:form rendered="#{!loginBean.login}">
		<h:panelGrid columns="2">

			<h:outputText value="会员登录" />
			<h:outputLink value="/member/register.jsf">注册新会员</h:outputLink>
			<h:outputText value="用户名:" />
			<h:inputText id="name" value="#{loginBean.name}" />
			<h:outputText value="密码:" />
			<h:inputSecret value="#{loginBean.pass}" />
			<h:commandButton value="登录" action="#{loginBean.login}" />
			<h:outputText />
			<h:outputText />
			<h:messages globalOnly="true" errorStyle="color: red" />

		</h:panelGrid>
	</h:form> <h:panelGrid rendered="#{loginBean.login}" columns="2">
		<h:outputText value="欢迎" />
		<h:outputText value="#{loginBean.name }" />
	</h:panelGrid></div>
	<h:panelGroup styleClass="component"
		style="width: 300px; height: 250px; padding: 10px">
		<div class="subtitle">会员新闻</div>
		<div style="margin-top: 20px">
		<div class="desc">会员新闻abc</div>
		</div>
	</h:panelGroup>
	<h:panelGroup rendered="#{loginBean.login }" styleClass="component"
		style="width: 300px; height: 250px; padding: 10px">
		<div class="subtitle">会员资源</div>
		<div style="margin-top: 20px">
		<div class="desc">会员新闻abc</div>
		</div>
	</h:panelGroup>
	<jsp:include page="footer.jsp" />
	</body>
	</html>
</f:view>