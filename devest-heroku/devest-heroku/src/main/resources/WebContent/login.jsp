<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.HttpSession"
    import="javax.servlet.http.HttpServletResponse"
    import="kgym.KgymHelper"
    import="javax.servlet.http.Cookie"
    import="java.net.URLDecoder"
    import="kgym.UserDataDTO"
    import="kgym.UserDataDAO"%>
<%
	KgymHelper kgym=KgymHelper.getInstance();
	Cookie[] data=request.getCookies();
	String name="";
	if(data!=null){
		String cookieData=data[0].getValue();
		String decodeData=URLDecoder.decode(cookieData,"UTF-8");
		UserDataDTO ud=new UserDataDTO();
		ud.setName(decodeData);
		UserDataDTO resultUd =UserDataDAO.getInstance().search(ud);
		name=resultUd.getName();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(data!=null){ %>
	<a>ようこそ<%= name %>さん</a>
	<br>
	<form action="top" method="get">
		<% Cookie delete=new Cookie("userName","");
			delete.setMaxAge(0);
			response.addCookie(delete);%>
		<input type="submit" value="ログアウト">
	</form>
<%}else{ %>
	<form action="history.back()" method="get">
		<a>メールアドレス</a>
		<input type="text" name="name">
		<a>パスワード</a>
		<input type="password" name="password" size=10 maxlength=8>

		<input type="submit" value="ログイン">
	</form>
<% } %>
		<br>
		<a href="/Registration">新規登録</a>
</body>
</html>