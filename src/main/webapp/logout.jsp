<%-- 
    Document   : logout.jsp
    Created on : 11-Oct-2016, 22:21:14
    Author     : PVerdoukas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout : InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        <header>
        <h1>InstaGrim Logout, see you next time!</h1>
        <h2>Your world in Black and White</h2>
        <%@include file="nav.jsp"%>
        </header>
        <%
            if( request.isRequestedSessionIdValid() )
            session.invalidate();
        %> 
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
