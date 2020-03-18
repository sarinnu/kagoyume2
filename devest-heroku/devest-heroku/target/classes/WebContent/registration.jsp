<%@page import="javax.servlet.http.HttpSession"
		import="kgym.KgymHelper"
		import="kgym.UserData" %>
<%
	KgymHelper kgym=KgymHelper.getInstance();
	HttpSession hs=request.getSession();
	UserData ud=null;
	boolean reinput=false;
	if(request.getParameter("mode")!=null&&request.getParameter("mode").equals("REINPUT")){
		reinput=true;
		ud=(UserData)hs.getAttribute("ud");
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<p>会員登録</p>
	<form action="registration_confirm" method="POST">
		<a>名前</a>
		<input type="text" name="name" value="<% if(reinput){out.print(ud.getName());}%>">
		<a>パスワード</a>
		<input type="password" name="password" size=10 maxlength=8 value="<% if(reinput){out.print(ud.getPassword());}%>">
		<a>メールアドレス</a>
		<input type="text" name="name" value="<% if(reinput){out.print(ud.getMail());}%>">
		<a>住所</a>
		<input type="text" name="address" value="<% if(reinput){out.print(ud.getAddress());}%>">

		<input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
		<input type="submit" value="登録">
	</form>
		<br>
		<%=kgym.home() %>
</body>
</html>