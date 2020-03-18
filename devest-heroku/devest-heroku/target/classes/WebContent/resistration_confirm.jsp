<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpServlet"
        import="kgym.KgymHelper"
        import="kgym.UserData" %>
<%
	KgymHelper kgym=KgymHelper.getInstance();
	HttpSession hs=request.getSession();
	UserData ud=(UserData)hs.getAttribute("ud");
	ArrayList<String> chkList=ud.chkproperties();
%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <% if(chkList.size()==0){ %>
        <h1>登録確認</h1>
        名前:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メール:<%= ud.getMail() %>
        住所:<%= ud.getAddress()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
    <% }else{ %>
        <h1>入力が不完全です</h1>
        <%=kgym.chkinput(chkList) %>
    <% } %>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
        </form>
        <%=kgym.home()%>

</body>
</html>