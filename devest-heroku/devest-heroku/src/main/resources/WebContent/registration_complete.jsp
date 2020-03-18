<%@page import="javax.servlet.http.HttpSession"
        import="kgym.KgymHelper"
        import="kgym.UserData" %>
<%
    KgymHelper kgym = KgymHelper.getInstance();
    UserData ud = (UserData)request.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録結果画面</title>
    </head>
    <body>
        <h1>会員登録完了</h1><br>
        名前:<%= ud.getName()%><br>
        メール:<%= ud.getMail() %><br>
        住所:<%= ud.getAddress() %><br>
        以上の内容で登録しました。<br>
    </body>
    <a href="top.jsp">トップに戻る</a>
</html>
