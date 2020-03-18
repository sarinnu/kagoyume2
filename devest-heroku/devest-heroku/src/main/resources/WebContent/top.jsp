<%@page contentType="text/html" pageEncoding="UTF-8"
		import="kgym.KgymHelper"
		import="javax.servlet.http.Cookie"%>
<%
	KgymHelper kgym=KgymHelper.getInstance();
	boolean view=true;
	Cookie[] cs=request.getCookies();

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>kagoyumeトップ</title>
</head>
<body>
    <h1>ECサイトトップ</h1><br>
    <h2>『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です。</h2><br>
    <form action="Search" method="get">
    	<a>検索キーワード</a>
    	<input type="text" name="item"><br>
    	<input type="submit" value="検索"><br>
    	<input type="hidden" value="ac">
    </form>
    <br>
    <%=kgym.home() %>
</body>
</html>
